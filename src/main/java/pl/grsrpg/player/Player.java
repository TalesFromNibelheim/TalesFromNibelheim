package pl.grsrpg.player;

import pl.grsrpg.card.Card;
import pl.grsrpg.field.Field;

public interface Player {
    void recalculateAttributes();

    void move(Field field);

    String getInfo();

    boolean addCard(Card card);

    Card removeCard(String name);

    int getInventorySize();

    int getGold();

    boolean removeGold(int amount);

    Field getCurrentField();
}
