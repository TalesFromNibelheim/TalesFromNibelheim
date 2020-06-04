package pl.grsrpg.entity;

public interface Entity {
    String getName();

    float getHealth();

    int getBaseMaxHealth();

    int getBaseStrength();

    int getBaseAgility();

    int getBaseMagicPoints();

    void takeDamage(float damage);

    void setBaseMaxHealth(int maxHealth);

    void setBaseStrength(int strength);

    void setBaseAgility(int agility);

    void setBaseMagicPoints(int magicPoints);
}
