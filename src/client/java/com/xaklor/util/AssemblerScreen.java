package com.xaklor.util;

import com.xaklor.util.assembler.AssemblerScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class AssemblerScreen extends HandledScreen<AssemblerScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("the_abandoned_zone", "textures/gui/container/assembler.png");

    public AssemblerScreen(AssemblerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int k;
        int i = this.x;
        int j = this.y;
        context.drawTexture(TEXTURE, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        // Xs
        if (this.handler.getLevel() == 1) {
            context.drawTexture(TEXTURE, i + 44,  j + 55, 176, 41, 16, 16);
            context.drawTexture(TEXTURE, i + 116, j + 55, 176, 41, 16, 16);
        } else if (this.handler.getLevel() == 2) {
            context.drawTexture(TEXTURE, i + 116, j + 55, 176, 41, 16, 16);
        }
        if (this.handler.isOn()) {
            k = this.handler.getWorkProgress() / 11;
            // progress bar
            context.drawTexture(TEXTURE, i + 146, j + 15 + k, 176, 57, 4, 55 - k + 1);
            // arrows
            context.drawTexture(TEXTURE, i + 51,  j + 33, 176, 0, 19, 21);
            context.drawTexture(TEXTURE, i + 106, j + 33, 195, 0, 19, 21);
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
