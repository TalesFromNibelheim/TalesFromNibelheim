package pl.grsrpg.utils;

import pl.grsrpg.Game;
import pl.grsrpg.logger.Logger;

import java.util.Random;

public class DiceRoll {
    private static final Random generator = new Random();

    public static int rollPublic(int numberOfDices, int numberOnDice) {
        int sumFromDices = 0;
        boolean combo = true;
        int rand = (generator.nextInt()% numberOnDice ) + 1;
        int first = rand;
        sumFromDices += rand;
        for(int i = 1 ; i < numberOfDices; i++){
            rand = (generator.nextInt()% numberOnDice ) + 1;
            System.out.println("Your roll: " +  Logger.YELLOW + rand);
            if(first != rand) combo = false;
            sumFromDices += rand;
        }
        if(combo && numberOfDices > 1){
            sumFromDices += first;
            System.out.println("So Lucky! You get " + Logger.YELLOW + first + Logger.WHITE  + " extra stitches to your result!" );
        }
        System.out.println("So Lucky! You get " + Logger.YELLOW + first + Logger.WHITE + " stitches extra to your result!" );
        System.out.println( Logger.RED + "Together: " + Logger.YELLOW + sumFromDices);
        return sumFromDices;
    }

    public static int rollPrivate(int numberOfDices, int numberOnDice) {
        int sumFromDices = 0;
        int rand = (generator.nextInt()% numberOnDice ) + 1;
        sumFromDices += rand;
        for(int i = 1 ; i < numberOfDices; i++){
            rand = (generator.nextInt()% numberOnDice ) + 1;
            sumFromDices += rand;
        }
        return sumFromDices;
    }

    public static boolean luckyRoll() {
        int rand = (generator.nextInt()% 6 ) + 1;
        if(rand == 6) return true;
        return false;
    }

}
