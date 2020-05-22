package pl.grsrpg.player;

import pl.grsrpg.card.Card;
import pl.grsrpg.entity.Entity;
import pl.grsrpg.field.Field;

public interface Player extends Entity {
    void recalculateAttributes();

    void move(Field field);

    String getInfo();

    boolean addCard(Card card);

    Card removeCard(String name);

    int getInventorySize();

    int getGold();

    boolean removeGold(int amount);

    Field getCurrentField();

    void fight(Entity entity);

    float getAdditionalMaxHealth();

    float getAdditionalStrength();

    float getAdditionalAgility();

    float getAdditionalMagicPoints();
}
