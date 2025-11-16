package net.orbit.item.custom;

import net.minecraft.component.ComponentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.orbit.item.ModComponents;
import net.orbit.item.custom.ability.AshAbility;

public class AoWItem extends Item {
    private final AshAbility ability;
    public AoWItem(Settings settings, AshAbility ability) {
        super(settings);
        this.ability = ability;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ability.use(user, world, hand, user.getStackInHand(hand));
        return super.use(world, user, hand);
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        ItemStack stackInSlot = slot.getStack();
        if (stackInSlot.isIn(ItemTags.SWORDS)) {
            if (!stackInSlot.contains(ModComponents.AOW_INFUSABLE)) {
                stackInSlot.set((ComponentType<String>) ModComponents.AOW_INFUSABLE, ability.getName());
                stack.decrement(1);
            }
            // returns true cuz item swap isn't needed
            return true;
        }
        return false;
    }

    public AshAbility getAbility() {
        return ability;
    }
}
