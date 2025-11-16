package net.orbit.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.ExplosionBehavior;

import java.util.Optional;
import java.util.function.Function;

public class GlintstoneProjectileEntity extends ExplosiveProjectileEntity implements FlyingItemEntity {
    private static final ExplosionBehavior EXPLOSION_BEHAVIOR = new AdvancedExplosionBehavior(false, false, Optional.of(0f), Registries.BLOCK.getOptional(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity()));
    public GlintstoneProjectileEntity(EntityType<? extends ExplosiveProjectileEntity> type, World world) {
        super(type, world);
    }
    public GlintstoneProjectileEntity(World world, double x, double y, double z) {
        super(ModEntities.GLINTSTONE_PROJECTILE_ENTITY, x, y, z, world);
    }
    public GlintstoneProjectileEntity(World world, LivingEntity owner) {
        super(ModEntities.GLINTSTONE_PROJECTILE_ENTITY, owner.getX(), owner.getEyePos().getY() - 0.1, owner.getZ(), world);
        this.setOwner(owner);
    }
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        createExplosion(entityHitResult.getPos());
        if (!this.getWorld().isClient && this.getWorld() instanceof ServerWorld serverWorld) {
            if (entityHitResult.getType() == HitResult.Type.ENTITY) {
                Entity entity = entityHitResult.getEntity();
                if (entity != this.getOwner()) {
                    entity.damage(
                            serverWorld,
                            this.getDamageSources().magic(),
                            6
                    );
                    this.getWorld().sendEntityStatus(this, (byte)3);
                    this.getWorld().playSound(null, entity.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
            }
        }
    }

    @Override
    protected boolean isBurning() {
        return false;
    }

    @Override
    protected ParticleEffect getParticleType() {
        return null;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getVelocity().equals(Vec3d.ZERO)) {
            this.setYaw((float)(Math.atan2(this.getVelocity().x, this.getVelocity().z) * 180.0D / Math.PI));
            float horizontalVelocity = (float)Math.sqrt(this.getVelocity().x * this.getVelocity().x + this.getVelocity().z * this.getVelocity().z);
            this.setPitch((float)(Math.atan2(this.getVelocity().y, horizontalVelocity) * 180.0D / Math.PI));

            this.lastYaw = this.getYaw();
            this.lastPitch = this.getPitch();
        }
        // Spawn particles
        if (this.getWorld().isClient) {
            for (int i = 0; i < 2; i++) {
                this.getWorld().addParticleClient(ParticleTypes.END_ROD,
                        this.getX() + (this.random.nextDouble() - 0.5) * 0.2,
                        this.getY() + (this.random.nextDouble() - 0.5) * 0.2,
                        this.getZ() + (this.random.nextDouble() - 0.5) * 0.2,
                        0, 0, 0);

                this.getWorld().addParticleClient(ParticleTypes.ELECTRIC_SPARK,
                        this.getX() + (this.random.nextDouble() - 0.5) * 0.2,
                        this.getY() + (this.random.nextDouble() - 0.5) * 0.2,
                        this.getZ() + (this.random.nextDouble() - 0.5) * 0.2,
                        0, 0, 0);
            }
        }
    }

    @Override
    public Direction getFacing() {
        return super.getFacing();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        createExplosion(blockHitResult.getPos());
        if (!this.getWorld().isClient && this.getWorld() instanceof ServerWorld serverWorld) {
            this.getWorld().playSound(null, blockHitResult.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            this.kill(serverWorld);
        }
    }

    protected void createExplosion(Vec3d pos) {
//        this.getWorld().createExplosion(this, null, EXPLOSION_BEHAVIOR,
//                pos.getX(), pos.getY(), pos.getZ(), 0f, false,
//                World.ExplosionSourceType.TRIGGER, ParticleTypes.SOUL_FIRE_FLAME, ParticleTypes.END_ROD,
//                SoundEvents.ENTITY_WIND_CHARGE_WIND_BURST);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
    }

    protected void onBlockCollision(BlockState state) {
        super.onBlockCollision(state);
//        if (!this.getWorld().isClient() && this.getWorld() instanceof ServerWorld serverWorld) {
//            this.getWorld().sendEntityStatus(this, (byte)3);
//            this.kill(serverWorld);
//            BlockPos blockPos = this.getBlockPos();
//            this.getWorld().playSound(null, blockPos, SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
//        }
    }

    @Override
    public ItemStack getStack() {
        return ItemStack.EMPTY;
    }
}
