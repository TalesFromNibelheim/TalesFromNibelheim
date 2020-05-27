package pl.grsrpg.player;

import lombok.NoArgsConstructor;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.manager.fight.ScoutFightManager;
import pl.grsrpg.utils.DiceRoll;

@NoArgsConstructor
public class PlayerScout extends Player {
    private static final int startMaxHealth = 20;
    private static final int startStrength = 5;
    private static final int startAgility = 2;
    private static final int startMagicPoints = 2;
    private static final int startEquipmentCapacity = 10;
    int magicPoints;

    public PlayerScout(String name, int currentField) {
        super(name, startMaxHealth, startStrength, startAgility, startMagicPoints, startEquipmentCapacity, currentField);
        this.fightManager = new ScoutFightManager(this);
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


    public float CriticalArrrow(){
        magicPoints -= 5;
        return 0.5F*(getBaseAgility() + getAdditionalAgility());
    }

    public float seriesOfArrows(){
        magicPoints -= 5;
        return  0.2F*(getBaseAgility() + getAdditionalAgility())* DiceRoll.rollPrivate(3,2);
    }

    public float repetableArrow(){
        magicPoints -= 5;
        return  2*(getBaseAgility() + getAdditionalAgility());
    }

    public float basicAttack(){
        return(getBaseAgility() + getAdditionalAgility())*0.2F;
    }





    public static String getStartDescription() {
        return " Start Attributes: \n" +
                "  Max Health: " + Logger.YELLOW + startMaxHealth + "\n" + Logger.RESET +
                "  Strength: " + Logger.YELLOW + startStrength + "\n" + Logger.RESET +
                "  Agility: " + Logger.YELLOW + startAgility + "\n" + Logger.RESET +
                "  Magic Points: " + Logger.YELLOW + startMagicPoints + "\n" + Logger.RESET +
                "  Equipment Capacity: " + Logger.YELLOW + startEquipmentCapacity + Logger.RESET;
    }
}
