package net.orbit.item.custom.ability;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.orbit.item.ModItems;

public class WindAshAbility extends AshAbility {
    public WindAshAbility() {
      super(10);
    }
    @Override
    public void use(PlayerEntity player, World world, Hand hand, ItemStack stack) {
//        if (!world.isClient &&
//            world instanceof ServerWorld serverWorld) {
//            float speed = 2.f;
//            WindChargeEntity windCharge = new WindChargeEntity(
//                    player,
//                    world,
//                    player.getPos().getX(),
//                    player.getEyePos().getY(),
//                    player.getPos().getZ()
//            );
//            ProjectileEntity.spawnWithVelocity((world1, shooter, stack1) -> windCharge,
//                    serverWorld, stack, player, 0f, speed, 1f);
//
//            if (!player.isCreative()) {
//                EquipmentSlot slot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
//                stack.damage(1, player, slot);
//                if (stack.isEmpty()) {
//                    player.setStackInHand(hand, new ItemStack(ModItems.WIND_ASH));
//                }
//            }
//        }

    }

    @Override
    public String getName() {
        return "wind_ash";
    }
}
