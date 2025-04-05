package com.holmityd.hightlight.mixin;

import com.holmityd.hightlight.Highlight;
import com.holmityd.hightlight.events.client.NewItemPickupEvent;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(at = @At("HEAD"), method = "onItemPickup")
    private void onItemPickup(ItemEntity itemEntity, CallbackInfo info) {
        Highlight.LOGGER.info("Item pickup by player: {} - Item: {}", this, itemEntity.getItem());
        if ((LivingEntity)(Object)this instanceof Player) {
            Player player = (Player)(Object)this;
            NewItemPickupEvent.EVENT.invoker().onItemPickup(player.getUUID(), itemEntity.getItem());
        }
    }
}