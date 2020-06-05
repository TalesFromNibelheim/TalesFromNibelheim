package pl.grsrpg.field;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pl.grsrpg.card.ICard;
import pl.grsrpg.player.IPlayer;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Field.class, name = "normal"),
        @JsonSubTypes.Type(value = BossField.class, name = "boss"),
})
public interface IField {
    String getName();

    String getDescription();

    void execute(IPlayer player);

    void setUndefeatedCard(ICard undefeatedCard);

    int getMapLevel();
}
