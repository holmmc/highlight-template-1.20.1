package com.holmityd.hightlight.mixin;

import com.holmityd.hightlight.Highlight;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.p3pp3rf1y.sophisticatedcore.upgrades.magnet.MagnetUpgradeWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MagnetUpgradeWrapper.class)
public class MagnetUpgradeWrapperMixin {
    @Inject(method = "tryToInsertItem", at = @At("HEAD"))
    private void beforeTryToInsertItem(ItemEntity itemEntity, CallbackInfoReturnable<Boolean> cir) {
        ItemStack stack = itemEntity.getItem().copy(); // Make a copy of the ItemStack
        if (!stack.isEmpty()) {
            Highlight.LOGGER.info("Magnet attempting to pick up: {} x{}",
                stack.getItem().getName(stack).getString(),
                stack.getCount());
        }
    }

    @Inject(method = "tryToInsertItem", at = @At("HEAD"))
    private void onTryToInsertItem(ItemEntity itemEntity, CallbackInfoReturnable<Boolean> cir) {
            ItemStack stack = itemEntity.getItem();
            Highlight.LOGGER.info("Magnet picked up: {} x{}",
                itemEntity,
                stack.getCount());
    }
}