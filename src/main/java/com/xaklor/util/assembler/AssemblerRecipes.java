package com.xaklor.util.assembler;

import com.xaklor.TheAbandonedZoneMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class AssemblerRecipes {
    private final ArrayList<AssemblerRecipe> lv1recipes;
    private final ArrayList<AssemblerRecipe> lv2recipes;
    private final ArrayList<AssemblerRecipe> lv3recipes;

    public AssemblerRecipes() {
        lv1recipes = new ArrayList<>() {
            {
                add(new AssemblerRecipe(TheAbandonedZoneMod.INDUSTRIAL_SCRAP.item, Items.IRON_INGOT,
                        8, 8, TheAbandonedZoneMod.SCRAP_PICK.asItem()));
            }
        };
        lv2recipes = new ArrayList<>() {
            {
                add(new AssemblerRecipe(TheAbandonedZoneMod.MALEVOLENT_SPIRIT, Items.COBBLESTONE, Items.CRYING_OBSIDIAN,
                        1, 64, 64, TheAbandonedZoneMod.GREEDY_WELL.item));
                add(new AssemblerRecipe(TheAbandonedZoneMod.BENEVOLENT_SPIRIT, Items.COBBLESTONE, Items.CRYING_OBSIDIAN,
                        1, 64, 64, TheAbandonedZoneMod.WISHING_WELL.item));
                add(new AssemblerRecipe(TheAbandonedZoneMod.ROYAL_HEART, Items.IRON_BLOCK, TheAbandonedZoneMod.INDUSTRIAL_SCRAP.item,
                        2, 4, 8, TheAbandonedZoneMod.ASSEMBLER_ARMS.item));
                add(new AssemblerRecipe(TheAbandonedZoneMod.INDUSTRIAL_SCRAP.item, TheAbandonedZoneMod.BRILLIANT_INGOT, Items.COPPER_INGOT,
                        16, 8, 32, TheAbandonedZoneMod.METAL_MATRIX.item));
                add(new AssemblerRecipe(TheAbandonedZoneMod.SCRAP_PICK.asItem(), TheAbandonedZoneMod.BRILLIANT_INGOT, TheAbandonedZoneMod.METAL_MATRIX.item,
                        1, 32, 2, TheAbandonedZoneMod.BRILLIANT_PICK.asItem()));
                add(new AssemblerRecipe(TheAbandonedZoneMod.HAMMER_HEAD.asItem(), TheAbandonedZoneMod.HAMMER_HANDLE, TheAbandonedZoneMod.RARE_DUST,
                        1, 1, 64, TheAbandonedZoneMod.STAR_HAMMER.asItem()));
            }
        };
        lv3recipes = new ArrayList<>() {
            {
                add(new AssemblerRecipe(TheAbandonedZoneMod.ALCHEMIC_BRICKS.item, TheAbandonedZoneMod.INFERNAL_DUST, TheAbandonedZoneMod.MYSTIC_DUST, TheAbandonedZoneMod.RESONATING_DUST,
                        1, 64, 64, 8, TheAbandonedZoneMod.MIGHT_BLOCK.item));
                add(new AssemblerRecipe(TheAbandonedZoneMod.BRILLIANT_PICK.asItem(), TheAbandonedZoneMod.BLUE_SAPPHIRE, TheAbandonedZoneMod.SAPPHIRE_DUST, TheAbandonedZoneMod.DIAMOND_DUST,
                        1, 32, 64, 64, TheAbandonedZoneMod.BLUE_SAPPHIRE_PICK.asItem()));
                add(new AssemblerRecipe(TheAbandonedZoneMod.BRILLIANT_PICK.asItem(), TheAbandonedZoneMod.RED_SAPPHIRE, TheAbandonedZoneMod.SAPPHIRE_DUST, TheAbandonedZoneMod.DIAMOND_DUST,
                        1, 32, 64, 64, TheAbandonedZoneMod.RED_SAPPHIRE_PICK.asItem()));
                add(new AssemblerRecipe(TheAbandonedZoneMod.BRILLIANT_PICK.asItem(), TheAbandonedZoneMod.YELLOW_SAPPHIRE, TheAbandonedZoneMod.SAPPHIRE_DUST, TheAbandonedZoneMod.DIAMOND_DUST,
                        1, 32, 64, 64, TheAbandonedZoneMod.YELLOW_SAPPHIRE_PICK.asItem()));
                add(new AssemblerRecipe(TheAbandonedZoneMod.BRILLIANT_PICK.asItem(), TheAbandonedZoneMod.GREEN_SAPPHIRE, TheAbandonedZoneMod.SAPPHIRE_DUST, TheAbandonedZoneMod.DIAMOND_DUST,
                        1, 32, 64, 64, TheAbandonedZoneMod.GREEN_SAPPHIRE_PICK.asItem()));
                add(new AssemblerRecipe(TheAbandonedZoneMod.BRILLIANT_PICK.asItem(), TheAbandonedZoneMod.CYAN_SAPPHIRE, TheAbandonedZoneMod.SAPPHIRE_DUST, TheAbandonedZoneMod.DIAMOND_DUST,
                        1, 32, 64, 64, TheAbandonedZoneMod.CYAN_SAPPHIRE_PICK.asItem()));
                add(new AssemblerRecipe(TheAbandonedZoneMod.BRILLIANT_PICK.asItem(), TheAbandonedZoneMod.PURPLE_SAPPHIRE, TheAbandonedZoneMod.SAPPHIRE_DUST, TheAbandonedZoneMod.DIAMOND_DUST,
                        1, 32, 64, 64, TheAbandonedZoneMod.PURPLE_SAPPHIRE_PICK.asItem()));
                add(new AssemblerRecipe(TheAbandonedZoneMod.BRILLIANT_PICK.asItem(), TheAbandonedZoneMod.PINK_SAPPHIRE, TheAbandonedZoneMod.SAPPHIRE_DUST, TheAbandonedZoneMod.DIAMOND_DUST,
                        1, 32, 64, 64, TheAbandonedZoneMod.PINK_SAPPHIRE_PICK.asItem()));
                add(new AssemblerRecipe(Items.ANVIL, TheAbandonedZoneMod.MIGHT_BLOCK.item, TheAbandonedZoneMod.STARDUST, TheAbandonedZoneMod.NETHERITE_DUST,
                        8, 8, 32, 64, TheAbandonedZoneMod.HAMMER_HEAD));
                add(new AssemblerRecipe(TheAbandonedZoneMod.STEEL_BONE, TheAbandonedZoneMod.ROYAL_HEART, TheAbandonedZoneMod.SAPPHIRE_DUST, TheAbandonedZoneMod.GEMSTONE_BLEND,
                        64, 64, 64, 64, TheAbandonedZoneMod.HAMMER_HANDLE));

                // player "head"
            }
        };

    }

    public Item LookupRecipe(ItemStack in1, ItemStack in2) {
        for (AssemblerRecipe recipe : lv1recipes) {
            if (recipe.input1 == in1.getItem() && recipe.input2 == in2.getItem() &&
                    recipe.amount1 == in1.getCount() && recipe.amount2 == in2.getCount())
                return recipe.output;
        }
        return null;
    }
    public Item LookupRecipe(ItemStack in1, ItemStack in2, ItemStack in3) {
        for (AssemblerRecipe recipe : lv2recipes) {
            if (recipe.input1 == in1.getItem() && recipe.input2 == in2.getItem() && recipe.input3 == in3.getItem() &&
                    recipe.amount1 == in1.getCount() && recipe.amount2 == in2.getCount() && recipe.amount3 == in3.getCount())
                return recipe.output;
        }
        return LookupRecipe(in1, in2);
    }
    public Item LookupRecipe(ItemStack in1, ItemStack in2, ItemStack in3, ItemStack in4) {
        for (AssemblerRecipe recipe : lv3recipes) {
            if (recipe.input1 == in1.getItem() && recipe.input2 == in2.getItem() && recipe.input3 == in3.getItem() && recipe.input4 == in4.getItem() &&
                    recipe.amount1 == in1.getCount() && recipe.amount2 == in2.getCount() && recipe.amount3 == in3.getCount() && recipe.amount4 == in4.getCount())
                return recipe.output;
        }
        return LookupRecipe(in1, in2, in3);
    }
}

class AssemblerRecipe {
    public Item input1;
    public Item input2;
    public Item input3;
    public Item input4;
    public int amount1;
    public int amount2;
    public int amount3;
    public int amount4;
    public Item output;
    public AssemblerRecipe(Item i1, Item i2, int a1, int a2, Item out) {
        input1 = i1; input2 = i2;
        amount1 = a1; amount2 = a2;
        output = out;
    }
    public AssemblerRecipe(Item i1, Item i2, Item i3, int a1, int a2, int a3, Item out) {
        input1 = i1; input2 = i2; input3 = i3;
        amount1 = a1; amount2 = a2; amount3 = a3;
        output = out;
    }
    public AssemblerRecipe(Item i1, Item i2, Item i3, Item i4, int a1, int a2, int a3, int a4, Item out) {
        input1 = i1; input2 = i2; input3 = i3; input4 = i4;
        amount1 = a1; amount2 = a2; amount3 = a3; amount4 = a4;
        output = out;
    }
}