package com.holmityd.hightlight;

import com.holmityd.hightlight.events.client.NewItemPickupEvent;
import com.holmityd.hightlight.handlers.ItemPickupHandler;
import com.holmityd.hightlight.handlers.SophisticatedBackpacksHandler;
import com.holmityd.hightlight.handlers.TooltipHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Highlight implements ModInitializer {
    public static final String MOD_ID = "highlight";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final String SOPHISTICATED_BACKPACKS_MOD_ID = "sophisticatedbackpacks";

    @Override
    public void onInitialize() {
        // Check if SophisticatedBackpacks is loaded
        if (FabricLoader.getInstance().isModLoaded(SOPHISTICATED_BACKPACKS_MOD_ID)) {
            LOGGER.info("SophisticatedBackpacks mod is loaded!");
        } else {
            LOGGER.info("SophisticatedBackpacks mod is not loaded!");
        }

        NewItemPickupEvent.EVENT.register(ItemPickupHandler::newItemPickup);
        ItemTooltipCallback.EVENT.register(TooltipHandler::onItemTooltip);
        
        // Register backpack item pickup handling
        NewItemPickupEvent.EVENT.register((player, stack) -> {
            SophisticatedBackpacksHandler.logBackpackItem(stack);
        });
    }
}