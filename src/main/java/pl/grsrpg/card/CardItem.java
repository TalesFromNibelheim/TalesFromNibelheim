package pl.grsrpg.card;

import lombok.*;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.IPlayer;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CardItem extends Card {
    private int health;
    private int strength;
    private int agility;
    private int magicPoints;
    private int gold;
    private int itemValue;
    private float armor;
    private Class profession = null;

    public CardItem(String name, String description, boolean carriable, int health, int strength, int agility, int magicPoints, int gold, int itemValue, float armor, Class profession) {
        super(name, description, carriable);
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.magicPoints = magicPoints;
        this.gold = gold;
        this.itemValue = itemValue;
        this.armor = armor;
        this.profession = profession;
    }

    @Override
    public boolean execute(IPlayer player) {
        System.out.println("You found item: ");
        this.showItemInfo();
        if (carriable && (this.profession == null || this.profession == player.getClass())) {
            player.addAdditionalMaxHealth(this.getHealth());
            player.addAdditionalAgility(this.getAgility());
            player.addAdditionalMagicPoints(this.getMagicPoints());
            player.addAdditionalStrength(this.getStrength());
            player.addArmor(this.armor);
        } else {
            player.addGold(this.gold);
        }
        return true;
    }

    public void showItemInfo() {
        System.out.println("Name: " + Logger.CYAN + this.getName() + Logger.RESET);
        System.out.println("Description: " + this.getDescription() + Logger.RESET);
        System.out.println("Stats: ");
        if (health != 0)
            System.out.println(" Additional health: " + Logger.YELLOW + health + Logger.RESET);
        if (strength != 0)
            System.out.println(" Additional strength: " + Logger.YELLOW + strength + Logger.RESET);
        if (agility != 0)
            System.out.println(" Additional agility: " + Logger.YELLOW + agility + Logger.RESET);
        if (magicPoints != 0)
            System.out.println(" Additional magic points: " + Logger.YELLOW + magicPoints + Logger.RESET);
        if (armor != 0F)
            System.out.println(" Additional armor: " + Logger.YELLOW + armor + Logger.RESET);
        if(profession != null)
             System.out.println(" For class: " + Logger.YELLOW + profession.getSimpleName() + Logger.RESET);
        System.out.println(" Item value: " + Logger.YELLOW + itemValue + Logger.RESET);
    }
}
