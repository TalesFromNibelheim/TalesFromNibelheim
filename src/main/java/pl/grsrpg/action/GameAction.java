package pl.grsrpg.action;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

@Getter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GameActionCoach.class, name = "coach"),
        @JsonSubTypes.Type(value = GameActionFight.class, name = "fight"),
        @JsonSubTypes.Type(value = GameActionTakeCard.class, name = "card")
})
public abstract class GameAction implements Action {
}
