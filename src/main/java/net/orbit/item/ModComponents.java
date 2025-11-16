package net.orbit.item;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.orbit.OrbitPedestals;

public class ModComponents {
  public static final ComponentType<?> AOW_INFUSABLE = Registry.register(
      Registries.DATA_COMPONENT_TYPE,
      Identifier.of(OrbitPedestals.MOD_ID, "aow_infusable"),
      ComponentType.<String>builder().codec(Codec.STRING).build());
  public static boolean isAowInfusable(ItemStack itemStack) {
    return itemStack.contains(AOW_INFUSABLE);
  }

  public static final ComponentType<?> AOW_ACTIVATION_TIME = Registry.register(
      Registries.DATA_COMPONENT_TYPE,
      Identifier.of(OrbitPedestals.MOD_ID, "aow_activation_time"),
      ComponentType.<Integer>builder().codec(Codec.INT).build());
  public static boolean hasActivationTime(ItemStack itemStack) {
    return itemStack.contains(AOW_ACTIVATION_TIME);
  }

  public static final ComponentType<?> AOW_IS_ACTIVATING = Registry.register(
      Registries.DATA_COMPONENT_TYPE,
      Identifier.of(OrbitPedestals.MOD_ID, "aow_is_activating"),
      ComponentType.<Boolean>builder().codec(Codec.BOOL).build());
  public static boolean isActivating(ItemStack itemStack) {
    return itemStack.contains(AOW_IS_ACTIVATING);
  }

  public static void startActivating(ItemStack itemStack) {
    itemStack.set((ComponentType<Boolean>) AOW_IS_ACTIVATING, true);
    itemStack.set((ComponentType<Integer>) AOW_ACTIVATION_TIME, 0);
  }
  public static void stopActivating(ItemStack itemStack) {
    itemStack.remove(AOW_ACTIVATION_TIME);
    itemStack.remove(AOW_IS_ACTIVATING);
  }

  public static void registerModComponents() {
    OrbitPedestals.LOGGER.info("Registering ModComponents for: " + OrbitPedestals.MOD_ID);
  }
}
