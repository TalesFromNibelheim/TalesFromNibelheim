package pl.grsrpg.entity;

public interface Entity {
    String getName();

    float getHealth();

    float getMaxHealth();

    float getStrength();

    float getAgility();

    float getMagicPoints();

    boolean attack(Entity enemy);
}
