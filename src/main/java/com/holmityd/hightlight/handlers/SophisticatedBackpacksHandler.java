package com.holmityd.hightlight.handlers;

import com.holmityd.hightlight.Highlight;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;

public class SophisticatedBackpacksHandler {
    public static boolean isBackpack(ItemStack stack) {
        return stack.getItem() instanceof BackpackItem;
    }

    public static void logBackpackItem(ItemStack stack) {
        if (!isBackpack(stack)) return;
        
        Highlight.LOGGER.info("Found backpack: {}", 
            stack.getItem().getName(stack).getString());
    }

    public static void checkBackpackPickup(Player player, ItemStack pickedUpStack) {
        player.getInventory().items.forEach(itemStack -> {
            if (isBackpack(itemStack)) {
                Highlight.LOGGER.info("Player has backpack and picked up: {} x{}", 
                    pickedUpStack.getItem().getName(pickedUpStack).getString(),
                    pickedUpStack.getCount());
            }
        });
    }
}