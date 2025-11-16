package net.orbit_pedestals.item.custom.ability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.orbit_pedestals.OrbitPedestals;
import net.orbit_pedestals.entity.GlintstoneProjectileEntity;

public class GlintstoneAshAbility extends AshAbility  {
    public GlintstoneAshAbility() {
        super(10);
    }

    @Override
    public void use(PlayerEntity player, World world, Hand hand, ItemStack stack) {
        if (!world.isClient) {
            GlintstoneProjectileEntity glintstoneProjectile = new GlintstoneProjectileEntity(world, player);
            glintstoneProjectile.setVelocity(player, player.getPitch(), player.getYaw(), 0f, 1.5f, 0f);
            world.spawnEntity(glintstoneProjectile);
            OrbitPedestals.LOGGER.info("Glintstone Ash Ability");
        }
    }

    @Override
    public String getName() {
        return "glintstone_ash";
    }

}
