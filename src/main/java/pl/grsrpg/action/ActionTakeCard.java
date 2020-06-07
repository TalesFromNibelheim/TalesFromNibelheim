package pl.grsrpg.action;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import pl.grsrpg.Game;
import pl.grsrpg.card.CardEnemy;
import pl.grsrpg.card.CardItem;
import pl.grsrpg.card.ICard;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.IPlayer;

public class ActionTakeCard extends Action {
    @Override
    public boolean execute(IPlayer player) {
        ICard card = Game.getGame().getBoard().drawCard();
        if(!card.isCarriable())
            return card.execute(player);
        if (!player.addCard(card)) {
            System.out.println("You don't have enough room in your equipment!");
            System.out.println("You lost card: ");
            System.out.println(" Name: " + Logger.YELLOW + card.getName() + Logger.RESET);
            return false;
        }
        return true;
    }

    @JsonIgnore
    @Override
    public String getInfo() {
        return "Take one card";
    }
}
