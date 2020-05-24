package pl.grsrpg.action;

import pl.grsrpg.player.Player;
import pl.grsrpg.utils.Attribute;


public class GameActionCoach extends GameAction {

    protected String name;
    protected float price;
    protected float multiplier;

    @Override
    public boolean execute (Player player){
        return true;
    }

    private int getPrice(Player player, Attribute attribute){
        switch (attribute){
            case AGILITY:
                return (int) (price * (Math.exp(player.getBaseAgility())*1/2) * multiplier);
            case STRENGTH:
                return (int) (price * (Math.exp(player.getBaseStrength())*1/2) * multiplier);
            case MAXHEALTH:
                return (int) (price * (Math.exp(player.getBaseMaxHealth())*1/2) * multiplier);
            case MAGICPOINTS:
                return (int) (price * (Math.exp(player.getBaseMagicPoints())*1/2) * multiplier);
            default:
                return 0;
        }
    }

    private void priceList(Player player){
        System.out.println("Strength + 1  (price: " + getPrice(player , Attribute.STRENGTH ) + ")" );
        System.out.println("Agility + 1  (price: " + getPrice(player , Attribute.AGILITY ) + ")" );
        System.out.println("Magic Points + 1  (price: " + getPrice(player , Attribute.MAGICPOINTS ) + ")" );
        System.out.println("Max Health + 1  (price: " + getPrice(player , Attribute.MAXHEALTH ) + ")" );
    }

    public void textMenu(Player player){
        System.out.println("You meet " + name + " on your way." );
        System.out.println("Hello. Do u want to improve your attributes for a small fee?" );
        priceList(player);
    }

}
