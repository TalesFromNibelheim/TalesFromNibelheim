package pl.grsrpg.card;

import pl.grsrpg.entity.Enemy;
import pl.grsrpg.player.IPlayer;

public class CardEnemy extends Card {
    private Enemy enemy;
    private int prize;

    @Override
    public boolean execute(IPlayer player) {
        //TODO walka
        return true;
    }
}
