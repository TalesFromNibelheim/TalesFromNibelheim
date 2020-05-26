package pl.grsrpg.manager.fight;

import lombok.NoArgsConstructor;
import pl.grsrpg.entity.Boss;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.player.IPlayer;

@NoArgsConstructor
public abstract class NormalFightManager implements FightManager{
    protected IPlayer player;

    public NormalFightManager(IPlayer player) {
        this.player = player;
    }

    @Override
    public boolean fight(Enemy enemy) {
        return false;
    }

    @Override
    public boolean fight(Boss boss) {
        return false;
    }
}
