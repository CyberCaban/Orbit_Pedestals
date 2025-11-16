package net.skebob.item.custom.ability;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.skebob.item.ModItems;

public class ArrowAshAbility extends AshAbility {
    public ArrowAshAbility() {
        super(5);
    }
    @Override
    public void use(PlayerEntity player, World world, Hand hand, ItemStack stack) {
//        if (!world.isClient &&
//                world instanceof ServerWorld serverWorld) {
//            float speed = 2.f;
//            ArrowEntity arrow = new ArrowEntity(EntityType.ARROW, world);
//            arrow.setPosition(player.getPos().getX(), player.getEyePos().getY(), player.getPos().getZ());
//            arrow.setOwner(player);
//            arrow.setVelocity(player, player.getPitch(), player.getYaw(), 0.0f, speed, 1.0f);
//            arrow.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
//            ProjectileEntity.spawnWithVelocity((world1, shooter, stack1) -> arrow,
//                    serverWorld, stack, player, 0f, speed, 1f);
//
//            if (!player.isCreative()) {
//                EquipmentSlot slot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
//                stack.damage(1, player, slot);
//                if (stack.isEmpty()) {
//                    player.setStackInHand(hand, new ItemStack(ModItems.ARROW_ASH));
//                }
//            }
//        }

    }

    @Override
    public String getName() {
        return "arrow_ash";
    }
}
