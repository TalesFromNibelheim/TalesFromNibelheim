package pl.grsrpg.player;

import pl.grsrpg.field.Field;

public class GamePlayerMage extends GamePlayer{
    private static final float startMaxHealth = 20;
    private static final float startStrength = 5;
    private static final float startAgility = 2;
    private static final float startMagicPoints = 2;
    private static final int startEquipmentCapacity = 10;

    public GamePlayerMage(String name, Field currentField) {
        super(name, startMaxHealth, startStrength, startAgility, startMagicPoints, startEquipmentCapacity, currentField);
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
