package net.skebob.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SkebobItem extends Item {
    public SkebobItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        user.heal(2f);
        user.getHungerManager().add(2, 0);
        user.playSound(SoundEvents.BLOCK_PISTON_EXTEND);
        if (!user.isCreative()) user.getStackInHand(hand).decrement(1);
        return ActionResult.SUCCESS;
    }
}

