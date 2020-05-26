package pl.grsrpg.action;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.IPlayer;
import pl.grsrpg.entity.Entity;

public class ActionFight extends Action {
    @JsonProperty
    private Enemy entity;

    @Override
    public boolean execute(IPlayer player){
        player.fight(entity);
        return true;
    }

    @JsonIgnore
    @Override
    public String getInfo() {
        return "Fight with: "+ Logger.CYAN+entity.getName()+Logger.RESET;
    }
}
