package pl.grsrpg.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Boss extends Enemy {
    public Boss(String name, int baseMaxHealth, int baseStrength, int baseAgility, int baseMagicPoints) {
        super(name, baseMaxHealth, baseStrength, baseAgility, baseMagicPoints);
    }
}
