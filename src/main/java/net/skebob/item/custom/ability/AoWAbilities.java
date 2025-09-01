package net.skebob.item.custom.ability;

import java.util.HashMap;
import java.util.Map;

public class AoWAbilities {
    public static final Map<String, AshAbility> ABILITIES = new HashMap<>();

    public static void registerAbility(AshAbility ability) {
        ABILITIES.put(ability.getName(), ability);
    }
    public static void registerAbilities() {
        registerAbility(new WindAshAbility());
        registerAbility(new ArrowAshAbility());
        registerAbility(new ArrowAshAbilityEX());
    }
}
