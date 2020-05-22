package pl.grsrpg.entity;

public interface Entity {
    String getName();

    float getHealth();

    float getBaseMaxHealth();

    float getBaseStrength();

    float getBaseAgility();

    float getBaseMagicPoints();

    boolean attack(Entity enemy);
}
