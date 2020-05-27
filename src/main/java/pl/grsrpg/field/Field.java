package pl.grsrpg.field;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.grsrpg.action.ActionTakeCard;
import pl.grsrpg.action.IAction;
import pl.grsrpg.card.ICard;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.IPlayer;
import pl.grsrpg.utils.DiceRoll;

import java.util.List;

@Getter
@ToString
public class Field implements IField {
    private String name;
    private String description;
    private List<IAction> actions;
    private ICard undefeatedCard;

    @Override
    public void execute(IPlayer player) {
        for(int i = 1; i <= actions.size(); i++){
            IAction action = actions.get(i - 1);
            if(action instanceof ActionTakeCard && undefeatedCard != null){
                System.out.println(Logger.YELLOW+i+". Card: \n  Name: "+Logger.CYAN+undefeatedCard.getName()+Logger.RESET+"\n  Description: "+undefeatedCard.getDescription());
            } else {
                System.out.println(Logger.YELLOW+i+". "+Logger.RESET+action.getInfo());
            }
        }
        int actionNumber = DiceRoll.rollPrivate(1, actions.size());
        System.out.println("You drew action number: "+Logger.YELLOW+actionNumber+Logger.RESET);
        actions.get(actionNumber - 1).execute(player);
    }

    @Override
    public void setUndefeatedCard(ICard undefeatedCard) {
        this.undefeatedCard = undefeatedCard;
    }
}
