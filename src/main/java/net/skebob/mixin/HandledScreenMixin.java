package net.skebob.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.sound.SoundEvents;
import net.skebob.item.ModComponents;
import net.skebob.item.ModItems;
import net.skebob.item.custom.ability.AoWAbilities;
import net.skebob.item.custom.ability.AshAbility;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin (ScreenHandler.class)
public class HandledScreenMixin {
    @Inject(method = "onSlotClick", at = @At("HEAD"), cancellable = true)
    private void onSlotClick(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {
//        if (slotIndex >= 0) {
//            ScreenHandler handler = (ScreenHandler) (Object) this;
//            Slot slot = handler.getSlot(slotIndex);
//            ItemStack stack = slot.getStack();
//            boolean isRightClick = button == 1;
//            if (!stack.isEmpty() &&
//                    stack.contains(ModComponents.AOW_INFUSABLE) &&
//                    isRightClick) {
//               String abilityId = (String) stack.get(ModComponents.AOW_INFUSABLE);
//               AshAbility ability = AoWAbilities.ABILITIES.get(abilityId);
//               Item item = ModItems.AOW_ABILITIES.get(ability);
//               ItemStack newItem = new ItemStack(item, 1);
//
//               if (!player.getInventory().insertStack(newItem)) {
//                   player.dropItem(newItem, false);
//               }
//               stack.remove(ModComponents.AOW_INFUSABLE);
//               ci.cancel();
//               player.playSound(SoundEvents.BLOCK_ANVIL_DESTROY, 1.0F, 1.0F);
//            }
//        }
    }
}
