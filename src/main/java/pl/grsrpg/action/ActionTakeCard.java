package pl.grsrpg.action;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import pl.grsrpg.Game;
import pl.grsrpg.card.ICard;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.IPlayer;

public class ActionTakeCard extends Action {
    @Override
    public boolean execute(IPlayer player){
        return player.addCard(Game.getGame().getBoard().drawCard());
    }

    @JsonIgnore
    @Override
    public String getInfo() {
        return "Take one card";
    }
}
