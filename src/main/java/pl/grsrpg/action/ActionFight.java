package pl.grsrpg.action;

import pl.grsrpg.player.IPlayer;
import pl.grsrpg.entity.Entity;

public class ActionFight extends Action {
    private Entity entity;

    @Override
    public boolean execute(IPlayer player){
        player.fight(entity);
        return true;
    }

}
