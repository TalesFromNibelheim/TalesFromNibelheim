package pl.grsrpg.player;

import lombok.NoArgsConstructor;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.manager.fight.ScoutFightManager;
import pl.grsrpg.utils.DiceRoll;
import pl.grsrpg.utils.Attribute;

@NoArgsConstructor
public class PlayerScout extends Player {
    private static final int startMaxHealth = 100;
    private static final int startStrength = 20;
    private static final int startAgility = 30;
    private static final int startMagicPoints = 20;
    private static final int startEquipmentCapacity = 5;

    public PlayerScout(String name) {
        super(name, startMaxHealth, startStrength, startAgility, startMagicPoints, startEquipmentCapacity);
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


    public float criticalArrow() {
        this.fightMagicPoints -= 5;
        return 0.5F * (this.getAgility());
    }

    public float seriesOfArrows() {
        this.fightMagicPoints -= 5;
        return 0.2F * (this.getAgility()) * DiceRoll.rollPrivate(3, 2);
    }

    public float repeatableArrow() {
        this.fightMagicPoints -= 5;
        return 2 * (this.getAgility());
    }

    public float basicAttack() {
        return (this.getAgility()) * 0.2F;
    }


    public static String getStartDescription() {
        return " Start Attributes: \n" +
                "  Max Health: " + Logger.YELLOW + startMaxHealth + "\n" + Logger.RESET +
                "  Strength: " + Logger.YELLOW + startStrength + "\n" + Logger.RESET +
                "  Agility: " + Logger.YELLOW + startAgility + "\n" + Logger.RESET +
                "  Magic Points: " + Logger.YELLOW + startMagicPoints + "\n" + Logger.RESET +
                "  Equipment Capacity: " + Logger.YELLOW + startEquipmentCapacity + Logger.RESET;
    }

    @Override
    public int getStartAttribute(Attribute attribute) {
        switch (attribute) {
            case AGILITY:
                return PlayerScout.getStartAgility();
            case STRENGTH:
                return PlayerScout.getStartStrength();
            case MAXHEALTH:
                return PlayerScout.getStartMaxHealth();
            case MAGICPOINTS:
                return PlayerScout.getStartMagicPoints();
            default:
                return 0;
        }
    }
}
