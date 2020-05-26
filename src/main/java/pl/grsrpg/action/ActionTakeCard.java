package pl.grsrpg.action;

import pl.grsrpg.card.ICard;
import pl.grsrpg.player.IPlayer;


public class ActionTakeCard extends Action {
    private ICard card;

    @Override
    public boolean execute(IPlayer player){
        return card.execute(player);
    }
}
