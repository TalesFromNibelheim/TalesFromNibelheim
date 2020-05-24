package pl.grsrpg.player;

import pl.grsrpg.field.Field;
import pl.grsrpg.manager.fight.ScoutFightManager;

public class GamePlayerScout extends GamePlayer {
    private static final int startMaxHealth = 20;
    private static final int startStrength = 5;
    private static final int startAgility = 2;
    private static final int startMagicPoints = 2;
    private static final int startEquipmentCapacity = 10;

    public GamePlayerScout(String name, Field currentField) {
        super(name, startMaxHealth, startStrength, startAgility, startMagicPoints, startEquipmentCapacity, currentField);
        this.fightManager = new ScoutFightManager();
    }

    public static int getStartMaxHealth() {
        return startMaxHealth;
    }

    public static int getStartStrength() {
        return startStrength;
    }

    public static int getStartAgility() {
        return startAgility;
    }

    public static int getStartMagicPoints() {
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
