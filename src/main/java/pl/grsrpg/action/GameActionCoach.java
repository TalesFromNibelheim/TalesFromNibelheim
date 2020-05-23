package pl.grsrpg.action;

import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.Player;
import pl.grsrpg.utils.Attribute;
import pl.grsrpg.utils.IOUtils;


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
                return (int) (price * (Math.exp(player.getBaseStrength())*1/2) * multiplier);
            case MAGICPOINTS:
                return (int) (price * (Math.exp(player.getBaseStrength())*1/2) * multiplier);
            default:
                return 0;
        }
    }



    private void priceList(Player player){
        System.out.println("1. Strength + 1  (" + Logger.RED + "price: " + getPrice(player , Attribute.STRENGTH) + Logger.WHITE + ")" );
        System.out.println("2. Agility + 1  (" + Logger.RED + "price: " + getPrice(player , Attribute.AGILITY) + Logger.WHITE + ")" );
        System.out.println("3. Magic Points + 1  (" + Logger.RED + "price: " + getPrice(player , Attribute.MAGICPOINTS) + Logger.WHITE + ")" );
        System.out.println("4. Max Health + 1  (" + Logger.RED + "price: " + getPrice(player , Attribute.MAXHEALTH) +  Logger.WHITE + ")" );
    }

    private boolean checkPlayerGold(Player player, Attribute attribute){
        switch (attribute){
            case AGILITY:
                return (player.getGold() >= getPrice(player,Attribute.AGILITY));
            case STRENGTH:
                return (player.getGold() >= getPrice(player,Attribute.STRENGTH));
            case MAXHEALTH:
                return(player.getGold() >= getPrice(player,Attribute.MAXHEALTH));
            case MAGICPOINTS:
                return(player.getGold() >= getPrice(player,Attribute.MAGICPOINTS));
            default:
                return false;
        }
    }

    public void textMenu(Player player){
        System.out.println("You meet " + name + " on your way." );
        System.out.println("Hello. Do u want to improve your attributes for a small fee?" );
        priceList(player);
        Attribute choice = Attribute.fromId(IOUtils.getScanner().nextInt());
        while(choice == null){ choice = Attribute.fromId(IOUtils.getScanner().nextInt());}
        if(checkPlayerGold(player, choice)){

        }




        
    }

}
