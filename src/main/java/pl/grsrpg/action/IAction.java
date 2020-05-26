package pl.grsrpg.action;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pl.grsrpg.player.IPlayer;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ActionCoach.class, name = "coach"),
        @JsonSubTypes.Type(value = ActionFight.class, name = "fight"),
        @JsonSubTypes.Type(value = ActionTakeCard.class, name = "card")
})
public interface IAction {

    boolean execute(IPlayer player);

}
