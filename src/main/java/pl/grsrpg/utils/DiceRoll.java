package pl.grsrpg.utils;

import pl.grsrpg.Game;

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
            System.out.println("Wyrzuciles na kostce: " + rand);
            if(first != rand) combo = false;
            sumFromDices += rand;
        }
        if(combo && numberOfDices > 1){
            sumFromDices += first;
            System.out.println("Szczesciarz! Dostajesz dodatkowe oczka: " + first );
        }
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
}
