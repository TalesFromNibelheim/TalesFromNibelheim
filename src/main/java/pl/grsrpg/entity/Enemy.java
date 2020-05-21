package pl.grsrpg.entity;

public class Enemy implements Entity {
    protected final String name;
    protected float health;
    protected float maxHealth;

    protected float strength;
    protected float agility;
    protected float magicPoints;

    public Enemy(String name, float maxHealth, float strength, float agility, float magicPoints) {
        this.name = name;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.strength = strength;
        this.agility = agility;
        this.magicPoints = magicPoints;
    }

    @Override
    public boolean attack(Entity enemy) {
        return false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public float getMaxHealth() {
        return maxHealth;
    }

    @Override
    public float getStrength() {
        return strength;
    }

    @Override
    public float getAgility() {
        return agility;
    }

    @Override
    public float getMagicPoints() {
        return magicPoints;
    }
}
