package pl.grsrpg.manager.fight;

import lombok.NoArgsConstructor;
import pl.grsrpg.player.IPlayer;

@NoArgsConstructor
public class MageFightManager extends NormalFightManager {
    public MageFightManager(IPlayer player) {
        super(player);
    }
}
