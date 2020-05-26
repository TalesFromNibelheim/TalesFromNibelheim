package pl.grsrpg.card;

import lombok.ToString;
import pl.grsrpg.entity.Entity;
import pl.grsrpg.player.IPlayer;

@ToString
public class CardFriend extends Card {
    private Entity entity;

    @Override
    public boolean execute(IPlayer player) {
        return false;
    }
}
