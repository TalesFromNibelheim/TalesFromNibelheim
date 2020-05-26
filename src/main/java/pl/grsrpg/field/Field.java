package pl.grsrpg.field;

import lombok.Getter;
import lombok.ToString;
import pl.grsrpg.action.IAction;
import pl.grsrpg.player.IPlayer;
import pl.grsrpg.utils.DiceRoll;

import java.util.List;

@Getter
@ToString
public class Field implements IField {
    private String name;
    private String description;
    private List<IAction> actions;

    @Override
    public void execute(IPlayer player) {
        int actionNumber = DiceRoll.rollPublic(1, actions.size());
        actions.get(actionNumber - 1).execute(player);
    }
}
