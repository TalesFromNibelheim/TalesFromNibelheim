package pl.grsrpg.card;

import lombok.ToString;
import pl.grsrpg.entity.Entity;
import pl.grsrpg.player.Player;

@ToString
public class GameCardFriend extends GameCard {
    private Entity entity;

    @Override
    public boolean execute(Player player) {
        return false;
    }
}
