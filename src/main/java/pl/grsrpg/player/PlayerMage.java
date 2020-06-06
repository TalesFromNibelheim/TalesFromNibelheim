package pl.grsrpg.player;

import lombok.NoArgsConstructor;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.manager.fight.MageFightManager;
import pl.grsrpg.utils.DiceRoll;
import pl.grsrpg.utils.Attribute;

@NoArgsConstructor
public class PlayerMage extends Player {
    private static final int startMaxHealth = 20;
    private static final int startStrength = 5;
    private static final int startAgility = 2;
    private static final int startMagicPoints = 2;
    private static final int startEquipmentCapacity = 10;

    public PlayerMage(String name) {
        super(name, startMaxHealth, startStrength, startAgility, startMagicPoints, startEquipmentCapacity);
        this.fightManager = new MageFightManager(this);
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

    public float thunder() {
        this.magicPoints -= 5;
        float dmg = 0.7F * (2.5F * this.getMagicPoints());
        System.out.println("You deal " + (dmg) + " damage and stun enemy.");
        return dmg;
    }

    public float fireBall() {
        this.magicPoints -= 5;
        float dmg = (4F * this.getMagicPoints());
        System.out.println("You deal " + (dmg) + " damage.");
        return dmg;
    }

    public float iceChain() {
        if(this.magicPoints >=5 ){
            this.magicPoints -= 5;
            float dmg = (this.getMagicPoints()) * (DiceRoll.rollPrivate(1, 6));
            System.out.println("You deal " + (dmg) + " damage.");
            return dmg;
        }else{


            return - 1;
        }

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
                return PlayerMage.getStartAgility();
            case STRENGTH:
                return PlayerMage.getStartStrength();
            case MAXHEALTH:
                return PlayerMage.getStartMaxHealth();
            case MAGICPOINTS:
                return PlayerMage.getStartMagicPoints();
            default:
                return 0;
        }
    }

}
