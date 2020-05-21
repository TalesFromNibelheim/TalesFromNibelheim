package pl.grsrpg.player;

import pl.grsrpg.field.Field;

public class GamePlayerMage extends GamePlayer{
    public GamePlayerMage(String name, Field currentField) {
        super(name, 20, 5, 2, 10, 10, currentField);
    }
}
