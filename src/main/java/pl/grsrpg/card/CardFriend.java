package pl.grsrpg.card;

import lombok.*;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.IPlayer;
import pl.grsrpg.utils.IOUtils;

@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Getter
@Setter
public class CardFriend extends Card {

    private int capacity;
    private boolean addPoint;
    private float multiplierGold;

    public CardFriend(String name, String description, int capacity, boolean point, float multiplier) {
        super(name, description, true);
        this.capacity = capacity;
        this.addPoint = point;
        this.multiplierGold = multiplier;
    }

    @Override
    public boolean execute(IPlayer player) {
        System.out.println("You met friendly: ");
        this.getCardInfo();
        if(player.hasFriend()){
            System.out.println(player.getCurrentFriendStats());
            System.out.println("Do you want to change your friend?");
            System.out.print(Logger.YELLOW + "1. " + Logger.RESET + "Yes/" + Logger.YELLOW + "2. " + Logger.RESET + "No (default: 2) ");
            int menuChoice = IOUtils.nextInt();
            if (menuChoice == 2)
                return false;
            player.setFriend(false);
        }
        if (this.capacity != 0) {
            player.setAdditionalEqCapacity(capacity);
            player.setFriend(true);
            return true;
        } else if (this.addPoint) {
            player.setAdditionalPoint(true);
            player.setFriend(true);
            return true;
        } else if (this.multiplierGold != 0F) {
            player.setGoldMultiplier(this.multiplierGold);
            player.setFriend(true);
            return true;
        }
        return false;
    }

    private void getCardInfo(){
        System.out.println("Name: " + Logger.CYAN + this.getName() + Logger.RESET);
        System.out.println("Description: " + this.getDescription() + Logger.RESET);
        System.out.println("Stats: ");
        if (capacity != 0)
            System.out.println(" Additional capacity: " + Logger.YELLOW + capacity + Logger.RESET);
        if (addPoint)
            System.out.println(Logger.YELLOW + " +1 " + Logger.RESET + "to dice roll!");
        if (multiplierGold != 0F)
            System.out.println(" Gold multiplier: " + Logger.YELLOW + multiplierGold + Logger.RESET);
    }
}