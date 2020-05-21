package pl.grsrpg.board;

import pl.grsrpg.entity.Entity;
import pl.grsrpg.player.Player;

public interface Board {
    void fight(Player player, Entity enemy);
}
