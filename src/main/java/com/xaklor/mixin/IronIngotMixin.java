package com.xaklor.mixin;

import com.xaklor.TheAbandonedZoneMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * grants portal generation powers to the iron ingot
 */
@Mixin(Item.class)
public class IronIngotMixin {

    private static BlockPattern PORTAL_PATTERN;

    @Inject(at = @At("HEAD"), method = "useOnBlock", cancellable = true)
    private void createPortal (ItemUsageContext context, CallbackInfoReturnable<ActionResult> info) {
        ItemStack stack = context.getStack();
        World world = context.getWorld();
        if (stack.isOf(Items.IRON_INGOT) && !world.isClient) {
            BlockPos blockPos;
            BlockState blockState = world.getBlockState(blockPos = context.getBlockPos());
            if (blockState.isOf(Blocks.IRON_BLOCK)) {
                stack.decrement(1);
                BlockPattern.Result result = getPortalPattern().searchAround(world, blockPos);
                if (result != null) {
                    BlockPos corner = switch (result.getUp()) {
                        case SOUTH -> result.getFrontTopLeft().add(-3, 0, -3);
                        case WEST ->  result.getFrontTopLeft().add(1,  0, -3);
                        case EAST ->  result.getFrontTopLeft().add(-3, 0, 1);
                        default ->    result.getFrontTopLeft().add(1,  0, 1);
                    };
                    for (int i = 0; i < 3; ++i) {
                        for (int j = 0; j < 3; ++j) {
                            world.setBlockState(corner.add(i, 0, j), TheAbandonedZoneMod.ABANDONED_PORTAL.getDefaultState(), Block.NOTIFY_LISTENERS);
                        }
                    }
                }
                info.setReturnValue(ActionResult.CONSUME);
            }
        }
    }

    private static BlockPattern getPortalPattern() {
        if (PORTAL_PATTERN == null) {
            PORTAL_PATTERN = BlockPatternBuilder.start().aisle("?SIS?", "SAAAS", "IAAAI", "SAAAS", "?SIS?")
                    .where('?', CachedBlockPosition.matchesBlockState(BlockStatePredicate.ANY))
                    .where('S', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.STONE_BRICKS)))
                    .where('I', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK)))
                    .where('A', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.AIR)))
                    .build();
        }
        return PORTAL_PATTERN;
    }
}
