package pl.grsrpg.card;

import lombok.Getter;
import pl.grsrpg.player.Player;

@Getter
public abstract class GameCard implements Card {
    private String name;
    private String description;

    @Override
    public boolean execute(Player player) {
        return false;
    }
}
