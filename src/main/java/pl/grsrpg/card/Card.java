package pl.grsrpg.card;

import lombok.Getter;
import pl.grsrpg.player.IPlayer;

@Getter
public abstract class Card implements ICard {
    private String name;
    private String description;

    @Override
    public boolean execute(IPlayer player) {
        return false;
    }
}
