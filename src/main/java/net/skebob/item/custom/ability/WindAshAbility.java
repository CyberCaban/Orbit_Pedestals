package net.skebob.item.custom.ability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class WindAshAbility implements AshAbility {
    @Override
    public void use(PlayerEntity player, World world, Hand hand, ItemStack stack) {
        if (!world.isClient &&
            world instanceof ServerWorld serverWorld) {
            float speed = 2.f;
            WindChargeEntity windCharge = new WindChargeEntity(
                    player,
                    world,
                    player.getPos().getX(),
                    player.getEyePos().getY(),
                    player.getPos().getZ()
            );
            ProjectileEntity.spawnWithVelocity((world1, shooter, stack1) -> windCharge,
                    serverWorld, stack, player, 0f, speed, 1f);

            if (!player.isCreative()) {
                stack.damage(1, player);
            }
        }

    }

    @Override
    public String getName() {
        return "wind_ash";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WindAshAbility) {
            WindAshAbility other = (WindAshAbility) obj;
            return other.getName().equals(this.getName());
        } else {
            return false;
        }
    }
}
