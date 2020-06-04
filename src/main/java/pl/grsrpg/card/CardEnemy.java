package pl.grsrpg.card;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.grsrpg.Game;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.player.IPlayer;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CardEnemy extends Card {
    private Enemy enemy;
    private int prize;

    public CardEnemy(String name, String description, Enemy enemy, int prize) {
        super(name, description, false);
        this.enemy = enemy;
        this.prize = prize;
    }

    @Override
    public boolean execute(IPlayer player) {
        if (!player.fight(enemy.clone())) {
            Game.getGame().getBoard().getField(player.getCurrentMapLevel(), player.getCurrentField()).setUndefeatedCard(this);
        } else {
            player.addGold(prize);
        }
        return true;
    }
}
