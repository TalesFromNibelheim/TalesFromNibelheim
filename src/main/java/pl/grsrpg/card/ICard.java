package pl.grsrpg.card;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pl.grsrpg.player.IPlayer;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CardEnemy.class, name = "enemy"),
        @JsonSubTypes.Type(value = CardFriend.class, name = "friend"),
        @JsonSubTypes.Type(value = CardItem.class, name = "item")
})
public interface ICard {
    boolean execute(IPlayer player);

    String getName();

    String getDescription();

    boolean isCarriable();
}
