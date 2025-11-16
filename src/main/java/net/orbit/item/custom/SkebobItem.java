package net.orbit.item.custom;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.function.Consumer;

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

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.skebob.skebob.tooltip"));
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }

}

