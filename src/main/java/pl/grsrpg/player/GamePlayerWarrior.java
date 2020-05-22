package pl.grsrpg.player;

import pl.grsrpg.field.Field;

public class GamePlayerWarrior extends GamePlayer {
    public GamePlayerWarrior(String name, Field currentField) {
        super(name, 20, 15, 5, 0, 20, currentField);
    }
}
