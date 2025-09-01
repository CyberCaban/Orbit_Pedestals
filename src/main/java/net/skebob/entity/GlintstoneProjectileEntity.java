package net.skebob.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GlintstoneProjectileEntity extends ExplosiveProjectileEntity implements FlyingItemEntity {
    public GlintstoneProjectileEntity(EntityType<? extends ExplosiveProjectileEntity> type, World world) {
        super(type, world);
    }
    public GlintstoneProjectileEntity(World world, double x, double y, double z) {
        super(ModEntities.GLINTSTONE_PROJECTILE_ENTITY, x, y, z, world);
    }
    public GlintstoneProjectileEntity(World world, LivingEntity owner) {
        super(ModEntities.GLINTSTONE_PROJECTILE_ENTITY, owner.getX(), owner.getEyeY(), owner.getZ(), world);
    }
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if (!this.getWorld().isClient() && this.getWorld() instanceof ServerWorld serverWorld) {
            entity.damage(serverWorld, this.getDamageSources().thrown(this, this.getOwner()), 4);
            this.discard();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        BlockPos blockPos = blockHitResult.getBlockPos();
        this.getWorld().playSound(null, blockPos, SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    protected void onBlockCollision(BlockState state) {
        super.onBlockCollision(state);
        if (!this.getWorld().isClient() && this.getWorld() instanceof ServerWorld serverWorld) {
            this.getWorld().sendEntityStatus(this, (byte)3);
            this.kill(serverWorld);
        }
    }

    @Override
    public ItemStack getStack() {
        return ItemStack.EMPTY;
    }
}
