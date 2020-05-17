package pl.grsrpg.utils;

import java.util.Random;

public class DiceRoll {
    private static final Random random = new Random();

    public int rollDice(){
        return random.nextInt(6)+1;
    }
}
