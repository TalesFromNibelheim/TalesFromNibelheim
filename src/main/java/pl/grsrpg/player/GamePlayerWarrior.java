package pl.grsrpg.player;

import pl.grsrpg.field.Field;
import pl.grsrpg.utils.DiceRoll;

public class GamePlayerWarrior extends GamePlayer {
    public GamePlayerWarrior(String name, Field currentField) {
        super(name, 20, 15, 5, 0, 20, currentField);
    }

    int knockdown(){
        this.magicPoints  -= 5;
        if(DiceRoll.rollPrivate(1,6) >= 4)
            System.out.println("Zadajesz dodatkowe obrazenia przy uderzeniu:" + "wartosc do ustalenia");
        return 0 ;// tu bedzie ile obrazen jak sie wymysli
    }
}
