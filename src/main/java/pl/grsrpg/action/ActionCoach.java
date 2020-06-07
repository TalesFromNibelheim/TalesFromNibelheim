package pl.grsrpg.action;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.grsrpg.card.CardItem;
import pl.grsrpg.card.ICard;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.IPlayer;
import pl.grsrpg.player.PlayerMage;
import pl.grsrpg.player.PlayerScout;
import pl.grsrpg.player.PlayerWarrior;
import pl.grsrpg.utils.Attribute;
import pl.grsrpg.utils.DiceRoll;
import pl.grsrpg.utils.IOUtils;

import java.util.ArrayList;
import java.util.List;


public class ActionCoach extends Action {
    @JsonProperty
    protected String name;
    @JsonProperty
    protected float price;
    @JsonProperty
    protected float multiplier;

    @Override
    public boolean execute(IPlayer player) {
        textMenu(player);
        return true;
    }

    private int getPrice(IPlayer player, Attribute attribute) {
        switch (attribute) {
            case AGILITY:
                return (int) (price + (Math.pow(player.getBaseAgility() - player.getStartAttribute(Attribute.AGILITY), 2)) * multiplier);
            case STRENGTH:
                return (int) (price + (Math.pow(player.getBaseStrength() - player.getStartAttribute(Attribute.STRENGTH), 2)) * multiplier);
            case MAXHEALTH:
                return (int) (price + (Math.pow(player.getBaseMaxHealth() - player.getStartAttribute(Attribute.MAXHEALTH), 2)) * multiplier);
            case MAGICPOINTS:
                return (int) (price + (Math.pow(player.getBaseMagicPoints() - player.getStartAttribute(Attribute.MAGICPOINTS), 2)) * multiplier);
            default:
                return 0;
        }
    }

    private void priceList(IPlayer player) {
        System.out.println(Logger.CYAN + "1." + " Strength + 1  (" + Logger.RED + "price: " + getPrice(player, Attribute.STRENGTH) + Logger.WHITE + ")");
        System.out.println(Logger.CYAN + "2." + " Agility + 1  (" + Logger.RED + "price: " + getPrice(player, Attribute.AGILITY) + Logger.WHITE + ")");
        System.out.println(Logger.CYAN + "3." + " Magic Points + 1  (" + Logger.RED + "price: " + getPrice(player, Attribute.MAGICPOINTS) + Logger.WHITE + ")");
        System.out.println(Logger.CYAN + "4." + " Max Health + 1  (" + Logger.RED + "price: " + getPrice(player, Attribute.MAXHEALTH) + Logger.WHITE + ")");
    }

    private boolean checkPlayerGold(IPlayer player, Attribute attribute) {
        switch (attribute) {
            case AGILITY:
                return player.removeGold(getPrice(player, Attribute.AGILITY));
            case STRENGTH:
                return (player.removeGold(getPrice(player, Attribute.STRENGTH)));
            case MAXHEALTH:
                return (player.removeGold(getPrice(player, Attribute.MAXHEALTH)));
            case MAGICPOINTS:
                return (player.removeGold(getPrice(player, Attribute.MAGICPOINTS)));
            default:
                return false;
        }
    }

    private void improveAttributes(IPlayer player, Attribute choice, int number) {
        switch (choice) {
            case AGILITY:
                player.setBaseAgility(player.getBaseAgility() + number);
                break;
            case STRENGTH:
                player.setBaseStrength(player.getBaseStrength() + number);
                break;
            case MAXHEALTH:
                player.setBaseMaxHealth(player.getBaseMaxHealth() + number);
                break;
            case MAGICPOINTS:
                player.setBaseMagicPoints(player.getBaseMagicPoints() + number);
                break;
        }
    }

    private void attributesChoice(IPlayer player) {
        boolean work = true;
        boolean luckyMan = false;
        if (DiceRoll.luckyRoll()) luckyMan = true;
        while (work) {
            Attribute choice = null;
            while (choice == null) {
                priceList(player);
                choice = Attribute.fromId(IOUtils.nextInt());
            }
            if (checkPlayerGold(player, choice)) {
                if (!luckyMan) {
                    System.out.println("Training was successful. Do you want to see your attributes now?");
                    improveAttributes(player, choice, 1);
                } else {
                    improveAttributes(player, choice, 2);
                    System.out.println("Wow you're very smart. Training was successful. Do you want to see your attributes now?");
                    luckyMan = false;
                }
                System.out.print(Logger.YELLOW + "1. " + Logger.RESET + "Yes/" + Logger.YELLOW + "2. " + Logger.RESET + "No (default: 2) ");
                int menuChoice = IOUtils.nextInt();
                if (menuChoice == 1) System.out.println(player.getInfo());
            } else System.out.println("Sorry you don't have gold enough.");
            System.out.println("Do you want to continue training?");
            System.out.print(Logger.YELLOW + "1. " + Logger.RESET + "Yes/" + Logger.YELLOW + "2. " + Logger.RESET + "No (default: 2) ");
            int menuChoice = IOUtils.nextInt();
            if (menuChoice == 2)
                work = false;
        }
    }

    private void sellItems(IPlayer player) {
        boolean work = true;
        List<ICard> cards = new ArrayList<>(player.getCards());
        cards.removeIf(card -> !(card instanceof CardItem));
        if(cards.isEmpty()){
            System.out.println("You don't have any items!");
            return;
        }
        while(work){
            System.out.println("Your items:");
            int i = 1;
            for(ICard card : cards){
                System.out.println(Logger.YELLOW + (i++) + ". " + Logger.RESET);
                ((CardItem)card).showItemInfo();
            }
            System.out.println(Logger.YELLOW + i + ". " + Logger.RESET + "Don't sell anything.");
            System.out.print("Choose item to sell: ");
            int choice = IOUtils.nextInt() - 1;
            choice = (choice < 0 ? cards.size() : choice);
            if(choice < cards.size()){
                ICard card = cards.get(choice);
                int itemValue = ((CardItem)card).getItemValue();
                if(player.removeCard(card.getName()) != null){
                    player.addGold(itemValue);
                    cards.remove(choice);
                }
            }
            System.out.println("Do you want to sell more items?");
            System.out.print(Logger.YELLOW + "1. " + Logger.RESET + "Yes/" + Logger.YELLOW + "2. " + Logger.RESET + "No (default: 2) ");
            int menuChoice = IOUtils.nextInt();
            if(menuChoice == 2)
                work = false;
        }
    }

    private void textMenu(IPlayer player) {
        System.out.println("You meet " + Logger.CYAN + name + Logger.RESET + " on your way.");
        System.out.println("Hello. Do you want to:");
        System.out.println(Logger.YELLOW + "1. " + Logger.RESET + "Improve your attributes for a small fee?");
        System.out.println(Logger.YELLOW + "2. " + Logger.RESET + "Sell your items?");
        System.out.println(Logger.YELLOW + "3. " + Logger.RESET + "Abort dealing with me?");
        System.out.print("Your choice: ");
        int menuChoice = IOUtils.nextInt();
        if (menuChoice == 1){
            attributesChoice(player);
            player.restore();
        }
        else if (menuChoice == 2) sellItems(player);
        System.out.println("See you soon!");
    }

    @JsonIgnore
    @Override
    public String getInfo() {
        return "Meet Coach: " + Logger.CYAN + name + Logger.RESET;
    }
}
