package pl.grsrpg.action;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.ToString;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.IPlayer;

@AllArgsConstructor
public class ActionFight extends Action {
    @JsonProperty
    private Enemy entity;
    @JsonProperty
    private int prize;

    @Override
    public boolean execute(IPlayer player) {
        if (player.fight(entity.clone())) {
            player.addGold(prize);
        }
        return true;
    }

    @JsonIgnore
    @Override
    public String getInfo() {
        return "Fight with: " + Logger.CYAN + entity.getName() + Logger.RESET;
    }
}
