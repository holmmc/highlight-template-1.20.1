package com.holmityd.hightlight.mixin;

import com.holmityd.hightlight.render.NewItemMarkRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.network.chat.Component;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMixin extends Screen {
    protected AbstractContainerScreenMixin(Component titleIn) { super(titleIn); }

    @Inject(method = "renderSlot", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphics;renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V", shift = Shift.AFTER))
    public void renderSlot(GuiGraphics graphics, Slot slot, CallbackInfo info) {
        NewItemMarkRenderer.setCapturedGuiGraphics(graphics);
        if (slot.container instanceof Inventory) {
            if (slot.hasItem() && ((Inventory)slot.container).items.contains(slot.getItem())) {
                NewItemMarkRenderer.renderNewItemMark(graphics.pose(), slot);
            }
        }
    }
}