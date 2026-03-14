package net.orbit.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.orbit.block.entity.custom.PedestalBlockEntity;
import org.jetbrains.annotations.Nullable;

public class PedestalBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = Block.createCuboidShape(2,0,2,14,13,14);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public PedestalBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PedestalBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof PedestalBlockEntity pedestal) {
                ItemScatterer.spawn(world, pos, pedestal);
                world.updateComparators(pos, this);
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient()) {
            return ActionResult.SUCCESS;
        }

        if(!(world.getBlockEntity(pos) instanceof PedestalBlockEntity pedestalBlockEntity)) {
            return ActionResult.PASS;
        }

        ItemStack playerStackInHand = player.getStackInHand(hand);
        if(pedestalBlockEntity.hasRoom() && !playerStackInHand.isEmpty()) {
            pedestalBlockEntity.push(playerStackInHand.copyWithCount(1));
            world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.75f, 1.2f);
            playerStackInHand.decrement(1);

            world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
            pedestalBlockEntity.markDirty();
        } else if(!player.isSneaking()) {
            if (playerStackInHand.isEmpty()) {
                ItemStack stackOnPedestal = pedestalBlockEntity.peek();
                if (!stackOnPedestal.isEmpty()) {
                    player.setStackInHand(hand, stackOnPedestal);
                    world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.75f, 1.0f);
                    pedestalBlockEntity.pop();

                    world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
                    pedestalBlockEntity.markDirty();
                }
            } else if (playerStackInHand.getItem() == pedestalBlockEntity.peek().getItem()) {
                playerStackInHand.increment(1);
                world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.75f, 1.0f);
                pedestalBlockEntity.pop();

                world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
                pedestalBlockEntity.markDirty();
            }
        } else if(player.isSneaking() && !world.isClient()) {
            player.openHandledScreen(pedestalBlockEntity);
        }

        return ActionResult.CONSUME;
    }
}
