package com.holmityd.hightlight.render;

import com.holmityd.hightlight.Highlight;
import com.holmityd.hightlight.handlers.ItemPickupHandler;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;

public class NewItemMarkRenderer {
    private static final ResourceLocation NEW_ITEM_MARKS = new ResourceLocation(Highlight.MOD_ID, "textures/gui/newitemmarks.png");
    private static final int IMAGE_SIZE = 16;
    private static final float ANIMATION_SPEED = 1f;
    private static final float CYCLE_DURATION = 1000.0f / ANIMATION_SPEED;
    private static GuiGraphics capturedGuiGraphics;

    public static void setCapturedGuiGraphics(GuiGraphics guiGraphics){
        capturedGuiGraphics = guiGraphics;
    }

    public static void renderNewItemMark(PoseStack poseStack, Slot slot) {
        Minecraft mc = Minecraft.getInstance();
        assert mc.player != null;
        if (!mc.player.isCreative()) {
            if (ItemPickupHandler.getMarkedSlots().contains(slot.getContainerSlot()) && slot.hasItem()) {
                render(capturedGuiGraphics, poseStack, slot.getItem(), slot.x, slot.y);
            } else {
                ItemPickupHandler.clearMarkedSlot(slot.getContainerSlot());
            }
        }
    }

    private static void render(GuiGraphics guiGraphics, PoseStack poseStack, @NotNull ItemStack item, int x, int y) {
        if (item.isEmpty()) {
            return;
        }

        float timeOffset = (Util.getMillis() % CYCLE_DURATION) / CYCLE_DURATION;

        poseStack.pushPose();
        poseStack.translate(0, -easeInOutQuadratic(0, 0.4f, timeOffset), 410);

        guiGraphics.blit(NEW_ITEM_MARKS, x, y, 0, 0, IMAGE_SIZE / 2, IMAGE_SIZE / 2, IMAGE_SIZE, IMAGE_SIZE);

        poseStack.popPose();
    }

    private static float easeInOutQuadratic(float a, float b, float t) {
        t *= 2.0f;
        if (t < 1.0f) {
            return a + (b - a) * 0.5f * t * t;
        } else {
            t -= 1.0f;
            return a + (b - a) * 0.5f * (t * (t - 2.0f) - 1.0f);
        }
    }
}
