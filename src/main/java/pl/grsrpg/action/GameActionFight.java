package pl.grsrpg.action;

import pl.grsrpg.entity.Entity;
import pl.grsrpg.player.Player;

public class GameActionFight extends GameAction {
    private Entity entity;

    @Override
    public boolean execute(Player player){
        return false;
    }
}
