package pl.grsrpg.player;

import pl.grsrpg.card.Card;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.entity.Entity;
import pl.grsrpg.field.Field;

public interface Player extends Entity {
    void recalculateAttributes();

    void move(Field field);

    String getInfo();

    String getItemsInfo();

    boolean addCard(Card card);

    Card removeCard(String name);

    int getEquipmentCapacity();

    int getGold();

    void setGold(int gold);

    boolean removeGold(int amount);

    Field getCurrentField();

    void fight(Entity entity);

    int getAdditionalMaxHealth();

    int getAdditionalStrength();

    int getAdditionalAgility();

    int getAdditionalMagicPoints();

    void setAdditionalMaxHealth(int maxHealth);

    void setAdditionalStrength(int strength);

    void setAdditionalAgility(int agility);

    void setAdditionalMagicPoints(int magicPoints);

    int getCurrentMapLevel();

    void addGold(int value);

    void addAdditionalMaxHealth(int maxHealth);

    void addAdditionalStrength(int strength);

    void addAdditionalAgility(int agility);

    boolean dodge ();

    void addAdditionalMagicPoints(int magicPoints);
}
