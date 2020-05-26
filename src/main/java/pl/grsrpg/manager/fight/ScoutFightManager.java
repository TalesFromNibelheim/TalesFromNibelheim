package pl.grsrpg.manager.fight;

import lombok.NoArgsConstructor;
import pl.grsrpg.player.IPlayer;

@NoArgsConstructor
public class ScoutFightManager extends NormalFightManager {
    public ScoutFightManager(IPlayer player) {
        super(player);
    }
}
