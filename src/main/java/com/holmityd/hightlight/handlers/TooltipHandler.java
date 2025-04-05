package com.holmityd.hightlight.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class TooltipHandler {

    public static void onItemTooltip(ItemStack itemStack, TooltipFlag ignoredTooltipFlag, List<Component> ignoredComponents) {
        if (Minecraft.getInstance().screen instanceof AbstractContainerScreen<?> invScreen) {
            for (Slot slot : invScreen.getMenu().slots) {
                if (slot.getItem().equals(itemStack)) {
                    ItemPickupHandler.clearMarkedSlot(slot.getContainerSlot());
                    return;
                }
            }
        }
    }

}
