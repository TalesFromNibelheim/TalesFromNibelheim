package pl.grsrpg.player;

import pl.grsrpg.field.Field;
import pl.grsrpg.utils.DiceRoll;

public class GamePlayerWarrior extends GamePlayer {
    private static final float startMaxHealth = 20;
    private static final float startStrength = 5;
    private static final float startAgility = 2;
    private static final float startMagicPoints = 2;
    private static final int startEquipmentCapacity = 10;

    public GamePlayerWarrior(String name, Field currentField) {
        super(name, startMaxHealth, startStrength, startAgility, startMagicPoints, startEquipmentCapacity, currentField);
    }

    int knockdown(){
        this.baseMagicPoints  -= 5;
        if(DiceRoll.rollPrivate(1,6) >= 4)
            System.out.println("Zadajesz dodatkowe obrazenia przy uderzeniu:" + "wartosc do ustalenia");
        return 0 ;// tu bedzie ile obrazen jak sie wymysli
    }

    public static float getStartMaxHealth() {
        return startMaxHealth;
    }

    public static float getStartStrength() {
        return startStrength;
    }

    public static float getStartAgility() {
        return startAgility;
    }

    public static float getStartMagicPoints() {
        return startMagicPoints;
    }

    public static int getStartEquipmentCapacity() {
        return startEquipmentCapacity;
    }

    public static String getStartDescription(){
        return  " Start Attributes: \n"+
                " Max Health: "+startMaxHealth+"\n"+
                " Strength : "+startStrength+"\n"+
                " Agility : "+startAgility+"\n"+
                " Magic Points : "+startMagicPoints+"\n"+
                " Equipment Capacity : "+startEquipmentCapacity;
    }

}
