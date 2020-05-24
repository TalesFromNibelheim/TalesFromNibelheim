package pl.grsrpg.card;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import pl.grsrpg.player.Player;

@Getter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GameCardEnemy.class, name = "enemy"),
        @JsonSubTypes.Type(value = GameCardFriend.class, name = "friend"),
        @JsonSubTypes.Type(value = GameCardItem.class, name = "item")
})
public abstract class GameCard implements Card {
    private String name;
    private String description;

    @Override
    public boolean execute(Player player) {
        return false;
    }
}
