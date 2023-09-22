package com.xaklor.util;

import com.xaklor.util.disintegrator.DisintegratorScreenHandler;
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
        if (this.handler.isOn()) {
            k = this.handler.getWorkProgress() / 8;
            // progress arrow
            context.drawTexture(TEXTURE, i + 101, j + 35, 176, 0, 24 - k + 1, 16);
            // fuel arrow
            context.drawTexture(TEXTURE, i + 44, j + 20, 176, 17, 32, 47);
        }
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
