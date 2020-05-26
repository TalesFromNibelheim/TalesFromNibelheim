package pl.grsrpg.card;

import pl.grsrpg.Game;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.player.IPlayer;

public class CardEnemy extends Card {
    private Enemy enemy;
    private int prize;

    @Override
    public boolean execute(IPlayer player) {
        if(!player.fight(enemy)){
            Game.getGame().getBoard().getField(player.getCurrentMapLevel(), player.getCurrentField()).setUndefeatedCard(this);
        }
        return true;
    }
}
