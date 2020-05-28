package pl.grsrpg.card;

import lombok.*;
import pl.grsrpg.player.IPlayer;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public abstract class Card implements ICard {
    protected String name;
    protected String description;

    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean execute(IPlayer player) {
        return false;
    }
}
