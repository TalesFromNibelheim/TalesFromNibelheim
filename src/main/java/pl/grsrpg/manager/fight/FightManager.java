package pl.grsrpg.manager.fight;

import pl.grsrpg.entity.Boss;
import pl.grsrpg.entity.Enemy;

public interface FightManager {
    boolean fight(Enemy enemy);

    boolean fight(Boss boss);
}
