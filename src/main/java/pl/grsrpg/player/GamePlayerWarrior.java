package pl.grsrpg.player;

import pl.grsrpg.field.Field;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.manager.fight.WarriorFightManager;
import pl.grsrpg.utils.DiceRoll;
import pl.grsrpg.entity.Entity;

public class GamePlayerWarrior extends GamePlayer {
    private static final int startMaxHealth = 20;
    private static final int startStrength = 5;
    private static final int startAgility = 2;
    private static final int startMagicPoints = 2;
    private static final int startEquipmentCapacity = 10;

    public GamePlayerWarrior(String name, Field currentField) {
        super(name, startMaxHealth, startStrength, startAgility, startMagicPoints, startEquipmentCapacity, currentField);
        this.fightManager = new WarriorFightManager();
    }

    public float knockdown(){
        this.baseMagicPoints  -= 5;
        if(DiceRoll.rollPrivate(1,6) >= 4){
            System.out.println("You dealt additional damage " + ( 2.5F * this.additionalStrength +0.4F*baseStrength ) + "and knockdown enemy.");
            return ( 2.5F * this.additionalStrength +0.2F*baseStrength );
        }

        return 2*this.additionalStrength + 0.3F*baseStrength;
    }

    public float cleave(Entity entity){
        this.baseMagicPoints -= 5;
        if(this.health < this.health*0.3){
            System.out.println("You deal amage " + 2.5F * (this.additionalStrength + startStrength*0.4) + ".");
            return 2.5F * (this.additionalStrength + startStrength);
        }
        if(entity.getHealth() < entity.getBaseMaxHealth()*0.2){
            System.out.println("AMAZING! You dealt " + ( entity.getHealth()) + " damage.");
            return entity.getHealth();
        }
        System.out.println("You dealt " + ( 2F* (this.additionalStrength + startStrength) ) + " damage.");
        return 2F* (this.additionalStrength + startStrength);
    }

    public void blessingOfTheShield(){
        this.baseMagicPoints -= 5;
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
