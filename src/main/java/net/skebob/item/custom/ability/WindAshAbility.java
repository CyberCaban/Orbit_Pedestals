package net.skebob.item.custom.ability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class WindAshAbility implements AshAbility {
    @Override
    public void use(PlayerEntity player, World world, Hand hand, ItemStack stack) {
        player.sendMessage(Text.literal("Wind Ash"), true);
    }

    @Override
    public String getName() {
        return "Wind Ash";
    }
}
