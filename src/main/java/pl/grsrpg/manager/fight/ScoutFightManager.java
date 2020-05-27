package pl.grsrpg.manager.fight;

import lombok.NoArgsConstructor;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.IPlayer;
import pl.grsrpg.player.PlayerWarrior;
import pl.grsrpg.utils.IOUtils;
import pl.grsrpg.utils.DiceRoll;


@NoArgsConstructor
public class ScoutFightManager extends NormalFightManager {
    public ScoutFightManager(IPlayer player) {
        super(player);
    }
}
