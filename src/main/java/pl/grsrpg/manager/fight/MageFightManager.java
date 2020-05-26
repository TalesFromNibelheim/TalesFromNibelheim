package pl.grsrpg.manager.fight;

import lombok.NoArgsConstructor;
import pl.grsrpg.player.IPlayer;
import pl.grsrpg.entity.Enemy;
@NoArgsConstructor
public class MageFightManager extends NormalFightManager {
    public MageFightManager(IPlayer player) {
        super(player);
    }

    @Override
    public boolean fight(Enemy enemy) { return true; }

}
