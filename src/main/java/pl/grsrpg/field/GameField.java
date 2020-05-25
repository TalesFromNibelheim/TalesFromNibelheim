package pl.grsrpg.field;

import lombok.Getter;
import lombok.ToString;
import pl.grsrpg.action.Action;
import pl.grsrpg.player.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class GameField implements Field{
    private String name;
    private String description;
    private List<Action> actions = new ArrayList<>();

    @Override
    public void execute(Player player) {
        for(Action action : actions){
            action.execute(player);
        }
    }
}
