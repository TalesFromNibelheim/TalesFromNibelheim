package pl.grsrpg.card;

import pl.grsrpg.player.GamePlayerMage;
import pl.grsrpg.player.GamePlayerScout;
import pl.grsrpg.player.GamePlayerWarrior;
import pl.grsrpg.player.Player;

public class GameCardItem extends GameCard {
    private float health;
    private int strength;
    private int agility;
    private int magicPoints;
    private int gold;
    private int itemValue;
    private float armor;
    private Class profession;

    //TODO CONSTRUCTOR

    public float getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getMagicPoints() {
        return magicPoints;
    }

    public int getGold() {
        return gold;
    }

    public int getItemValue() {
        return itemValue;
    }

    public float getArmor() {
        return armor;
    }

    public Class getProfession() {
        return profession;
    }

    @Override
    public boolean execute(Player player) {

        if ( (player instanceof GamePlayerMage && this.getProfession() == GamePlayerMage.class) ||
              (player instanceof GamePlayerScout && this.getProfession() == GamePlayerScout.class) ||
                (player instanceof GamePlayerWarrior && this.getProfession() == GamePlayerWarrior.class) ) {
            player.addAdditionalMaxHealth(this.getHealth());
            player.addAdditionalAgility(this.getAgility());
            player.addAdditionalMagicPoints(this.getMagicPoints());
            player.addAdditionalStrength(this.getStrength());
            player.addGold(this.getGold());
            //todo co z tym armorem?
            return true;
        }else{
            return false;
        }

    }
}
