package pl.grsrpg.card;

import lombok.Getter;
import lombok.Setter;
import pl.grsrpg.player.IPlayer;

@Getter
@Setter
public abstract class Card implements ICard {
    private String name;
    private String description;

    @Override
    public boolean execute(IPlayer player) {
        return false;
    }
}
