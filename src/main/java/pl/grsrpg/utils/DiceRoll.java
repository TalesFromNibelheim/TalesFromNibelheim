package pl.grsrpg.utils;

import pl.grsrpg.logger.Logger;

import java.util.Random;

public class DiceRoll {
    private static final Random generator = new Random();

    public static int rollPublic(int numberOfDices, int numberOnDice, boolean addPoints) {
        int sumFromDices = 0;
        boolean combo = true;
        int first = generator.nextInt(numberOnDice) + 1;
        System.out.println("Your roll: " + Logger.YELLOW + first + Logger.RESET);
        sumFromDices += first;
        for (int i = 1; i < numberOfDices; i++) {
            int rand = generator.nextInt(numberOnDice) + 1;
            System.out.println("Your roll: " + Logger.YELLOW + rand + Logger.RESET);
            if (first != rand) combo = false;
            sumFromDices += rand;
        }
        if (combo && numberOfDices > 1) {
            sumFromDices += first;
            System.out.println("So Lucky! You get " + Logger.YELLOW + first + Logger.RESET + " stitches extra to your result!");
        }
        if (numberOfDices > 1)
            System.out.println(Logger.RED + "Together: " + Logger.YELLOW + sumFromDices + Logger.RESET);
        if (addPoints)
            return (sumFromDices + 1);
        else
            return sumFromDices;
    }

    public static int rollPrivate(int numberOfDices, int numberOnDice) {
        int sumFromDices = 0;
        int rand = generator.nextInt(numberOnDice) + 1;
        sumFromDices += rand;
        for (int i = 1; i < numberOfDices; i++) {
            rand = generator.nextInt(numberOnDice) + 1;
            sumFromDices += rand;
        }
        return sumFromDices;
    }

    public static boolean luckyRoll() {
        return ((generator.nextInt(6)) + 1) == 6;
    }

}
