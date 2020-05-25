package pl.grsrpg.action;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pl.grsrpg.player.Player;
import pl.grsrpg.entity.Entity;

public class GameActionFight extends GameAction {
    private Entity entity;

    @Override
    public boolean execute(Player player){
        return true;
    }

}
