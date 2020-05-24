package pl.grsrpg.manager.fight;

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
