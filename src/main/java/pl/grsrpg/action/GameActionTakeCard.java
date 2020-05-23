package pl.grsrpg.action;

import pl.grsrpg.card.Card;
import pl.grsrpg.player.Player;


public class GameActionTakeCard extends GameAction{
    private Card card;

    @Override
    public boolean execute(Player player){
        return true;
    }

    public boolean addCard (Player player){ // do losowania karty
        return true;
    }

}
