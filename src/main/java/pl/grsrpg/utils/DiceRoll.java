package pl.grsrpg.utils;

import pl.grsrpg.Game;

import java.util.Random;

public class DiceRoll {
    private static final Random random = new Random();

    public static int rollDice() {
        return random.nextInt(Game.getConfig().getDiceMax() + 1) + Game.getConfig().getDiceMin();
    }
}
