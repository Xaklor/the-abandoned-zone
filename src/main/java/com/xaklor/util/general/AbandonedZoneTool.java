package com.xaklor.util.general;

import com.xaklor.TheAbandonedZoneMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;

public class AbandonedZoneTool implements ItemConvertible {

    public final Identifier id;
    public final ToolType type;
    public final ToolItem tool;
    public List<Enchantment> incompatibleEnchants = null;

    public enum ToolType {
        PICKAXE,
        SWORD
    }

    public AbandonedZoneTool(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings, String id, ToolType type) {
        this(material, attackDamage, attackSpeed, settings, id, type, (List<Enchantment>) null);
    }

    public AbandonedZoneTool(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings, String id, ToolType type, List<Enchantment> incompatibleEnchants) {
        this.incompatibleEnchants = incompatibleEnchants;
        this.id = new Identifier(TheAbandonedZoneMod.MOD_ID, id);
        this.type = type;
        switch (this.type) {
            case PICKAXE -> this.tool = new PickaxeItem(material, attackDamage, attackSpeed, settings);
            default -> this.tool = new SwordItem(material, attackDamage, attackSpeed, settings);
        }
        this.register();
    }

    public AbandonedZoneTool(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings, String id, ToolType type, Enchantment... incompatibleEnchants) {
        this(material, attackDamage, attackSpeed, settings, id, type, List.of(incompatibleEnchants));
    }

    private void register() {
        Registry.register(Registries.ITEM, this.id, this.tool);
        if (this.incompatibleEnchants != null)
            TheAbandonedZoneMod.INCOMPATIBLE_ENCHANT_RULES.put(this.tool, this.incompatibleEnchants);
    }

    @Override
    public Item asItem() {
        return this.tool;
    }
    
}
