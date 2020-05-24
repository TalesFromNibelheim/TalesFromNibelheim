package pl.grsrpg.field;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.ToString;
import pl.grsrpg.action.Action;

import java.util.List;

@Getter
@ToString
@JsonTypeName("normal")
public class GameField implements Field{
    private String name;
    private String description;
/*    private List<Action> actions;*/

/*    public GameField(String name, String description) {
        this.name = name;
        this.description = description;
    }*/
}
