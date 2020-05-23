package pl.grsrpg.action;

import pl.grsrpg.player.Player;
import pl.grsrpg.utils.Attribute;

public class GameActionCoach extends GameAction {

    protected float price;

    @Override
    public boolean execute (Player player){
        return true;
    }

    private int getPrice(Player player, Attribute attribute){
        switch (attribute){
            case AGILITY:
                return (int) (price * Math.log(player.getAgility()));
            case STRENGTH:
                return (int) (price * Math.log(player.getStrength()));
            case MAXHEALTH:
                return (int) (price * Math.log(player.getMaxHealth()));
            case MAGICPOINTS:
                return (int) (price * Math.log(player.getMagicPoints()));
            default:
                return 0;
        }
    }

}
