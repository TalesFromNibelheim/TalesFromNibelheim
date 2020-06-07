package pl.grsrpg.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.grsrpg.Game;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.IPlayer;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Setter
public class CardEnemy extends Card {
    @JsonProperty
    private Enemy enemy;
    @JsonProperty
    private int reward;

    public CardEnemy(String name, String description, Enemy enemy, int reward) {
        super(name, description, false);
        this.enemy = enemy;
        this.reward = reward;
    }

    @Override
    public boolean execute(IPlayer player) {
        if (!player.fight(enemy.clone())) {
            Game.getGame().getBoard().getField(player.getCurrentMapLevel(), player.getCurrentField()).setUndefeatedCard(this);
        } else {
            System.out.println("You received " + Logger.YELLOW + reward + Logger.RESET + " gold for winning this fight!");
            player.addGold(reward);
        }
        return true;
    }
}
