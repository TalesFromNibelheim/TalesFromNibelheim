package pl.grsrpg.card;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pl.grsrpg.player.Player;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GameCardEnemy.class, name = "enemy"),
        @JsonSubTypes.Type(value = GameCardFriend.class, name = "friend"),
        @JsonSubTypes.Type(value = GameCardItem.class, name = "item")
})
public interface Card {
    boolean execute(Player player);

    String getName();

    String getDescription();
}
