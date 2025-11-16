package net.orbit_pedestals.item.custom.ability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ArrowAshAbilityEX extends AshAbility {
    public ArrowAshAbilityEX() {
        super(5);
    }
    @Override
    public void use(PlayerEntity player, World world, Hand hand, ItemStack stack) {
//        if (!world.isClient && world instanceof ServerWorld serverWorld) {
//            float speed = 2.0f;
//            float spreadAngle = 10.0f; // Угол разброса в градусах
//
//            // Основные направления для трех стрел
//            float[] yawOffsets = {0f, spreadAngle, -spreadAngle};
//            float[] pitchOffsets = {0f, 0f, 0f}; // Можно добавить вертикальный разброс
//
//            for (int i = 0; i < 3; i++) {
//                ArrowEntity arrow = new ArrowEntity(EntityType.ARROW,world);
//                arrow.setPosition(player.getX(), player.getEyeY(), player.getZ());
//                arrow.setOwner(player);
//                arrow.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
//
//                // Вычисляем направление с учетом смещения
//                float yaw = player.getYaw() + yawOffsets[i];
//                float pitch = player.getPitch() + pitchOffsets[i];
//
//                // Устанавливаем скорость стрелы
//                Vec3d velocity = player.getRotationVec(1.0f)
//                        .rotateY((float) Math.toRadians(yawOffsets[i]))
//                        .normalize()
//                        .multiply(speed);
//
//                arrow.setVelocity(velocity);
//
//                // Добавляем стрелу в мир
//                world.spawnEntity(arrow);
//            }
//
//            if (!player.isCreative()) {
//                EquipmentSlot slot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
//                stack.damage(1, player, slot);
//                if (stack.isEmpty()) {
//                    player.setStackInHand(hand, new ItemStack(ModItems.ARROW_EX_ASH));
//                }
//            }
//        }
    }

    @Override
    public String getName() {
        return "arrow_ex_ash";
    }
}