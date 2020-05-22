package pl.grsrpg.utils;

import pl.grsrpg.Game;

import java.util.Random;

public class DiceRoll {
    private static final Random random = new Random();

    public static int rollDiceMove() {
        return random.nextInt(Game.getConfig().getDiceMax() - Game.getConfig().getDiceMin()) + Game.getConfig().getDiceMin();
    }

    public static int rollDice(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public static int rollDiceStandard(){
        return rollDice(1, 6);
    }
}
