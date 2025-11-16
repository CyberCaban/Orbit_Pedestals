package net.orbit_pedestals.item.custom.ability;

import java.util.HashMap;
import java.util.Map;

public class AoWAbilities {
    public static final Map<String, AshAbility> ABILITIES = new HashMap<>();
    public static final AshAbility WIND_ASH_ABILITY = registerAbility(new WindAshAbility());
    public static final AshAbility ARROW_ASH_ABILITY = registerAbility(new ArrowAshAbility());
    public static final AshAbility ARROW_EX_ASH_ABILITY = registerAbility(new ArrowAshAbilityEX());
    public static final AshAbility GLINTSTONE_ASH_ABILITY = registerAbility(new GlintstoneAshAbility());

    public static AshAbility registerAbility(AshAbility ability) {
        ABILITIES.put(ability.getName(), ability);
        return ability;
    }
    public static void registerAbilities() {
    }
}
