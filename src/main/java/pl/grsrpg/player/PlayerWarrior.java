package pl.grsrpg.player;

import lombok.NoArgsConstructor;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.manager.fight.WarriorFightManager;
import pl.grsrpg.utils.DiceRoll;
import pl.grsrpg.entity.Entity;

@NoArgsConstructor
public class PlayerWarrior extends Player {
    private static final int startMaxHealth = 20;
    private static final int startStrength = 5;
    private static final int startAgility = 2;
    private static final int startMagicPoints = 2;
    private static final int startEquipmentCapacity = 10;

    public PlayerWarrior(String name) {
        super(name, startMaxHealth, startStrength, startAgility, startMagicPoints, startEquipmentCapacity);
        this.fightManager = new WarriorFightManager(this);
    }

    float knockdown(){
        this.baseMagicPoints  -= 5;
        if(DiceRoll.rollPrivate(1,6) >= 4)
            System.out.println("You deal additional damage " + ( 2.5 * this.additionalStrength ) );
        return 2*this.additionalStrength;
    }

    float cleave(Entity entity){
        this.baseMagicPoints -= 5;
        if(this.health < this.health*0.3) return 2.5F * (this.additionalStrength + startStrength);
        if(entity.getHealth() < entity.getBaseMaxHealth()*0.2) return entity.getHealth();
        return 2F* (this.additionalStrength + startStrength);
    }

    float blessingOfTheShield(int numberOfTour, int numberOfAdditionalArmor ){
        this.baseMagicPoints -= 5;
        numberOfTour = DiceRoll.rollPrivate(2,4);
        numberOfAdditionalArmor = DiceRoll.rollPrivate(5,30);
        System.out.println(Logger.YELLOW + "God supports you. You gain " + numberOfAdditionalArmor + " additional armor." + Logger.RESET);
        return 0F;
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

    public static String getStartDescription() {
        return " Start Attributes: \n" +
                "  Max Health: " + Logger.YELLOW + startMaxHealth + "\n" + Logger.RESET +
                "  Strength: " + Logger.YELLOW + startStrength + "\n" + Logger.RESET +
                "  Agility: " + Logger.YELLOW + startAgility + "\n" + Logger.RESET +
                "  Magic Points: " + Logger.YELLOW + startMagicPoints + "\n" + Logger.RESET +
                "  Equipment Capacity: " + Logger.YELLOW + startEquipmentCapacity + Logger.RESET;
    }
}
