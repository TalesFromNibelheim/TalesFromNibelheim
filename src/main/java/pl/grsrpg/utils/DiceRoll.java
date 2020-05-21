package pl.grsrpg.utils;

import pl.grsrpg.Game;

import java.util.Random;

public class DiceRoll {
    private static final Random random = new Random();

    public static int rollDiceMove() {
        return random.nextInt(Game.getConfig().getDiceMax() + 1) + Game.getConfig().getDiceMin();
    }

    public static int rollDic(int min, int max) {
        return random.nextInt(max + 1) + min;
    }
}
