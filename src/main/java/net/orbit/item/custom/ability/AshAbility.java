package net.orbit.item.custom.ability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public abstract class AshAbility {
    private int MAX_ACTIVATION_TIME;
    public AshAbility() {
      this.MAX_ACTIVATION_TIME = 30;
    }
    public AshAbility(int maxActivationTime) {
      this.MAX_ACTIVATION_TIME = maxActivationTime;
    }
    public int getActivationTime() {
      return MAX_ACTIVATION_TIME;
    }
    public abstract void use(PlayerEntity player, World world, Hand hand, ItemStack stack);
    public abstract String getName();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AshAbility) {
            AshAbility other = (AshAbility) obj;
            return other.getName().equals(this.getName());
        } else {
            return false;
        }
    }
}
