package pl.grsrpg.player;

import pl.grsrpg.field.Field;

public class GamePlayerScout extends GamePlayer {
    public GamePlayerScout(String name, Field currentField) {
        super(name, 20, 7, 10, 2, 15, currentField);
    }
}
