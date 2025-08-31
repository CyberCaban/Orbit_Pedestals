package net.skebob.item.custom.ability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public interface AshAbility {
    void use(PlayerEntity player, World world, Hand hand, ItemStack stack);
    String getName();
}
