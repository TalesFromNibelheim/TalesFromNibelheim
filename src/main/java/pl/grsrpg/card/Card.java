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
    protected boolean carriable;

    public Card(String name, String description, boolean carriable) {
        this.name = name;
        this.description = description;
        this.carriable = carriable;
    }

    @Override
    public boolean execute(IPlayer player) {
        return false;
    }
}
