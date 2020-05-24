package pl.grsrpg.card;

import pl.grsrpg.entity.Entity;
import pl.grsrpg.player.Player;

public class GameCardFriend extends GameCard {
    Entity entity;

    @Override
    public boolean execute(Player player) {
        return false;
    }
}
