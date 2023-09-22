package com.xaklor.util;

import com.xaklor.util.alchemybox.AlchemyBoxScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class AlchemyBoxScreen extends HandledScreen<AlchemyBoxScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("the_abandoned_zone", "textures/gui/container/alchemy_box.png");

    public AlchemyBoxScreen(AlchemyBoxScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int k;
        int i = this.x;
        int j = this.y;
        context.drawTexture(TEXTURE, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);

        // fuel charges
        context.drawTexture(TEXTURE, i + 45, j + 18, 0, 166, 14 * this.handler.getFuelCharges() - 4, 10);

        if (this.handler.isOn()) {
            k = this.handler.getWorkProgress() / 8;
            // left progress arrow
            context.drawTexture(TEXTURE, i + 46, j + 43, 176, 0, 24 - k + 1, 16);

            // right progress arrow
            context.drawTexture(TEXTURE, i + 106, j + 43, 176, 17, 24, 16);
            context.drawTexture(TEXTURE, i + 106, j + 43, 176, 34, k - 1, 16);
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
