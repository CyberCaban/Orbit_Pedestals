package net.orbit.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.orbit.block.entity.custom.PedestalBlockEntity;
import org.jetbrains.annotations.Nullable;

public class PedestalBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = Block.createCuboidShape(2,0,2,14,13,14);
    public static final MapCodec<PedestalBlock> CODEC = PedestalBlock.createCodec(PedestalBlock::new);

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public PedestalBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PedestalBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onStateReplaced(BlockState state, ServerWorld world, BlockPos pos, boolean moved) {
        if (world.getBlockEntity(pos) instanceof PedestalBlockEntity pedestal) {
            pedestal.markDirty();
        }
        super.onStateReplaced(state, world, pos, moved);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!(world.getBlockEntity(pos) instanceof PedestalBlockEntity pedestalBlockEntity)) {
            return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
        }
        ItemStack playerStackInHand = player.getStackInHand(hand);
        if(pedestalBlockEntity.hasRoom() && !playerStackInHand.isEmpty()) {
            pedestalBlockEntity.push(playerStackInHand.copyWithCount(1));
            world.playSound(player, pos, SoundEvents.BLOCK_VAULT_INSERT_ITEM, SoundCategory.BLOCKS, 1f, 2f);
            playerStackInHand.decrement(1);

            world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
            pedestalBlockEntity.markDirty();
        } else if(!player.isSneaking()) {
            if (playerStackInHand.isEmpty()) {
                ItemStack stackOnPedestal = pedestalBlockEntity.peek();
                player.setStackInHand(Hand.MAIN_HAND, stackOnPedestal);
                world.playSound(player, pos, SoundEvents.BLOCK_VAULT_EJECT_ITEM, SoundCategory.BLOCKS, 1f, 1f);
                pedestalBlockEntity.pop();

                world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
                pedestalBlockEntity.markDirty();
            } else if (playerStackInHand.getItem() == pedestalBlockEntity.peek().getItem()) {
                playerStackInHand.increment(1);
                world.playSound(player, pos, SoundEvents.BLOCK_VAULT_EJECT_ITEM, SoundCategory.BLOCKS, 1f, 1f);
                pedestalBlockEntity.pop();

                world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
                pedestalBlockEntity.markDirty();
            }
        } else if(player.isSneaking() && !world.isClient()) {
            player.openHandledScreen(pedestalBlockEntity);
        }

        return ActionResult.SUCCESS;
    }
}
