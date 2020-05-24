package pl.grsrpg.manager.fight;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pl.grsrpg.card.GameCardEnemy;
import pl.grsrpg.card.GameCardFriend;
import pl.grsrpg.card.GameCardItem;
import pl.grsrpg.entity.Boss;
import pl.grsrpg.entity.Enemy;

public abstract class NormalFightManager implements FightManager{
    @Override
    public boolean fight(Enemy enemy) {
        return false;
    }

    @Override
    public boolean fight(Boss boss) {
        return false;
    }
}
