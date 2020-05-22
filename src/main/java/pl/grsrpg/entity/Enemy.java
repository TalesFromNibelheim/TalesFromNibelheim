package pl.grsrpg.entity;

import lombok.Getter;

@Getter
public class Enemy implements Entity {
    protected final String name;
    protected float health;
    protected float baseMaxHealth;

    protected float baseStrength;
    protected float baseAgility;
    protected float baseMagicPoints;

    public Enemy(String name, float baseMaxHealth, float baseStrength, float baseAgility, float baseMagicPoints) {
        this.name = name;
        this.health = baseMaxHealth;
        this.baseMaxHealth = baseMaxHealth;
        this.baseStrength = baseStrength;
        this.baseAgility = baseAgility;
        this.baseMagicPoints = baseMagicPoints;
    }

    @Override
    public boolean attack(Entity enemy) {
        return false;
    }
}
