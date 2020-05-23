package pl.grsrpg.card;

import pl.grsrpg.entity.Enemy;
import pl.grsrpg.player.Player;

public class GameCardEnemy extends GameCard {
    private Enemy enemy;
    private int prize;

    @Override
    public boolean execute(Player player) {
        //TODO walka
        return true;
    }
}
