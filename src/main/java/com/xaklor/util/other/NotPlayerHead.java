package com.xaklor.util.other;

import com.xaklor.TheAbandonedZoneMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class NotPlayerHead extends HorizontalFacingBlock implements Equipment {
    protected static final VoxelShape SHAPE = createCuboidShape(4, 0, 4,  12, 8, 12);

    public final Item item;
    public final Identifier ID = new Identifier(TheAbandonedZoneMod.MOD_ID, "not_player_head");
    public NotPlayerHead(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
        item = new BlockItem(this, new FabricItemSettings().maxCount(1)) {
            @Override
            public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
                super.inventoryTick(stack, world, entity, slot, selected);
                if (entity.isPlayer() && ((PlayerEntity) entity).getEquippedStack(EquipmentSlot.HEAD) == stack) {
                    ItemStack newHead = new ItemStack(Items.PLAYER_HEAD);
                    NbtCompound name = new NbtCompound();
                    name.putString("SkullOwner", entity.getEntityName());
                    newHead.setNbt(name);
                    entity.equipStack(EquipmentSlot.HEAD, newHead);
                }
            }
            @Override
            public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
                user.equipStack(EquipmentSlot.HEAD, user.getStackInHand(hand));
                return TypedActionResult.success(new ItemStack(Blocks.AIR), world.isClient());
            }
        };
        register();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
    }


    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    private void register() {
        Registry.register(Registries.BLOCK, ID, this);
        Registry.register(Registries.ITEM, ID, this.item);
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }
}