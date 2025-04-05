package com.holmityd.hightlight.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ItemPickupHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemPickupHandler.class);
    private static final Set<Integer> markedSlots = new HashSet<>();

    public static void newItemPickup(UUID uuid, ItemStack itemStack) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) {
            return;
        }

        Player player = mc.level.getPlayerByUUID(uuid);
        if (player == null || !player.equals(mc.player)) {
            return;
        }

        handlePreItemPickup(player, itemStack);
    }

    private static void handlePreItemPickup(@NotNull Player player, @NotNull ItemStack item) {
        int slot = player.getInventory().findSlotMatchingItem(item);

        if (slot == -1) {
            slot = player.getInventory().getFreeSlot();
        }

        if (slot != -1) {
            markedSlots.add(slot);
            LOGGER.info("NewItemPickupEvent: {}", markedSlots);
        }
    }

    public static Set<Integer> getMarkedSlots(){
        return markedSlots;
    }

    public static void clearMarkedSlot(int containerSlot) {
        markedSlots.remove(containerSlot);
    }
}