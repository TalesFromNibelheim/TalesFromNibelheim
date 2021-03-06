package pl.grsrpg.action;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.IPlayer;

@AllArgsConstructor
@NoArgsConstructor
public class ActionFight extends Action {
    @JsonProperty
    private Enemy entity;
    @JsonProperty
    private int reward;

    @Override
    public boolean execute(IPlayer player) {
        if (player.fight(entity.clone())) {
            System.out.println("You received " + Logger.YELLOW + reward + Logger.RESET + " gold for winning this fight!");
            player.addGold(reward);
        }
        return true;
    }

    @JsonIgnore
    @Override
    public String getInfo() {
        return "Fight with: " + Logger.CYAN + entity.getName() + Logger.RESET;
    }
}
