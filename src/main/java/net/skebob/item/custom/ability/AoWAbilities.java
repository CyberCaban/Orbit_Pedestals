package net.skebob.item.custom.ability;

import java.util.HashMap;
import java.util.Map;

public class AoWAbilities {
    public static final Map<String, AshAbility> abilities = new HashMap<String, AshAbility>();

    public static void registerAbility(AshAbility ability) {
        abilities.put(ability.getName(), ability);
    }
    public static void registerAbilities() {
        registerAbility(new WindAshAbility());
    }
}
