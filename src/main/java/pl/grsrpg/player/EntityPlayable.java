package pl.grsrpg.player;

import pl.grsrpg.card.Card;
import pl.grsrpg.entity.EntityFightable;

public interface EntityPlayable {

    boolean addCard(Card card);

    Card removeCard(String name);

    void recalculateAttributes();

    boolean fight(EntityFightable enemy);

}
