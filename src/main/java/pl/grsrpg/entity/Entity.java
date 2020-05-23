package pl.grsrpg.entity;

public interface Entity {
    String getName();

    float getHealth();

    float getBaseMaxHealth();

    float getBaseStrength();

    float getBaseAgility();

    float getBaseMagicPoints();

    boolean takeDamage(float damage);

    void setBaseMaxHealth(float maxHealth);

    void setBaseStrength(float strength);

    void setBaseAgility(float agility);

    void setBaseMagicPoints(float magicPoints);
}
