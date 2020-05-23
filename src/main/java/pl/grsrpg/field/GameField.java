package pl.grsrpg.field;

import lombok.Getter;
import lombok.ToString;
import pl.grsrpg.action.Action;

import java.util.List;

@Getter
@ToString
public class GameField implements Field{
    private String name;
    private String description;
    private List<Action> actions;
}
