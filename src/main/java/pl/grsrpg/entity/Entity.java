package pl.grsrpg.entity;

public interface Entity {
    String getName();

    int getHealth();

    int getBaseMaxHealth();

    int getBaseStrength();

    int getBaseAgility();

    int getBaseMagicPoints();

    boolean takeDamage(float damage);

    void setBaseMaxHealth(int maxHealth);

    void setBaseStrength(int strength);

    void setBaseAgility(int agility);

    void setBaseMagicPoints(int magicPoints);
}
