package pl.grsrpg.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enemy implements Entity {
    protected final String name;
    protected int health;
    protected int baseMaxHealth;

    protected int baseStrength;
    protected int baseAgility;
    protected int baseMagicPoints;

    public Enemy(String name, int baseMaxHealth, int baseStrength, int baseAgility, int baseMagicPoints) {
        this.name = name;
        this.health = baseMaxHealth;
        this.baseMaxHealth = baseMaxHealth;
        this.baseStrength = baseStrength;
        this.baseAgility = baseAgility;
        this.baseMagicPoints = baseMagicPoints;
    }

    @Override
    public boolean takeDamage(float damage) {
        return false;
    }
}
