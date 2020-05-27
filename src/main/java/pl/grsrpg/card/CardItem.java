package pl.grsrpg.card;

import lombok.*;
import pl.grsrpg.player.PlayerMage;
import pl.grsrpg.player.PlayerScout;
import pl.grsrpg.player.PlayerWarrior;
import pl.grsrpg.player.IPlayer;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CardItem extends Card {
    private int health;
    private int strength;
    private int agility;
    private int magicPoints;
    private int gold;
    private int itemValue;
    private float armor;
    private Class profession = null;

    @Override
    public boolean execute(IPlayer player) {
        if (this.gold == 0 && player.addCard(this) &&
                (this.profession == null || this.profession == player.getClass() ) ) {
            player.addAdditionalMaxHealth(this.getHealth());
            player.addAdditionalAgility(this.getAgility());
            player.addAdditionalMagicPoints(this.getMagicPoints());
            player.addAdditionalStrength(this.getStrength());
            player.addArmor(this.armor);
            return true;
        } else if (this.gold != 0) {
            player.addGold(this.gold);
            return true;
        } else {
            return false;
        }
    }
}
