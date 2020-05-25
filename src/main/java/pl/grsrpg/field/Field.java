package pl.grsrpg.field;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pl.grsrpg.player.Player;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GameField.class, name = "normal"),
        @JsonSubTypes.Type(value = BossGameField.class, name = "boss"),
})
public interface Field {
    String getName();

    String getDescription();

    void execute(Player player);
}
