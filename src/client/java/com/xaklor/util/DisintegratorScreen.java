package com.xaklor.util;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DisintegratorScreen extends HandledScreen<DisintegratorScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("the_abandoned_zone", "textures/gui/container/disintegrator.png");

    public DisintegratorScreen(DisintegratorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int k;
        int i = this.x;
        int j = this.y;
        context.drawTexture(TEXTURE, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        /* progression arrow
        if (((AbstractFurnaceScreenHandler)this.handler).isBurning()) {
            k = ((AbstractFurnaceScreenHandler)this.handler).getFuelProgress();
            context.drawTexture(this.background, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        progression flame
        k = ((AbstractFurnaceScreenHandler)this.handler).getCookProgress();
        context.drawTexture(TEXTURE, i + 79, j + 34, 176, 14, k + 1, 16);
         */

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
