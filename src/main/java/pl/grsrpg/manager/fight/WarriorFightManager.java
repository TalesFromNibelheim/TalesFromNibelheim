package pl.grsrpg.manager.fight;

import lombok.NoArgsConstructor;
import pl.grsrpg.player.IPlayer;

@NoArgsConstructor
public class WarriorFightManager extends NormalFightManager{
    public WarriorFightManager(IPlayer player) {
        super(player);
    }
}
