package pl.grsrpg.card;

import lombok.*;
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
}
