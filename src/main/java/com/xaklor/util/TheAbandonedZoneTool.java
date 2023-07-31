package com.xaklor.util;

import com.xaklor.TheAbandonedZoneMod;
import net.minecraft.item.*;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class TheAbandonedZoneTool {

    public final Identifier id;
    public final ToolType type;
    public final ToolItem tool;
    public enum ToolType {
        PICKAXE,
        SWORD
    }

    public TheAbandonedZoneTool(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings, String id, ToolType type) {
        this.id = new Identifier(TheAbandonedZoneMod.MOD_ID, id);
        this.type = type;
        switch (this.type) {
            case PICKAXE -> this.tool = new TheAbandonedZonePickaxe(material, attackDamage, attackSpeed, settings);
            default -> this.tool = new SwordItem(material, attackDamage, attackSpeed, settings);
        }
        this.register();
    }

    private void register() {
        Registry.register(Registries.ITEM, this.id, this.tool);
    }
}

class TheAbandonedZonePickaxe extends PickaxeItem {
    public TheAbandonedZonePickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
