package pl.grsrpg.card;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.grsrpg.player.PlayerMage;
import pl.grsrpg.player.PlayerScout;
import pl.grsrpg.player.PlayerWarrior;
import pl.grsrpg.player.IPlayer;

@Getter
@ToString
@Setter
public class CardItem extends Card {
    private int health;
    private int strength;
    private int agility;
    private int magicPoints;
    private int gold;
    private int itemValue;
    private float armor;
    private Class profession;

    @Override
    public boolean execute(IPlayer player) {

        if ( (player instanceof PlayerMage && this.getProfession() == PlayerMage.class) ||
              (player instanceof PlayerScout && this.getProfession() == PlayerScout.class) ||
                (player instanceof PlayerWarrior && this.getProfession() == PlayerWarrior.class) ) {
            player.addAdditionalMaxHealth(this.getHealth());
            player.addAdditionalAgility(this.getAgility());
            player.addAdditionalMagicPoints(this.getMagicPoints());
            player.addAdditionalStrength(this.getStrength());
            player.addGold(this.getGold());
            //todo co z tym armorem?
            if(gold != 0)
                player.addCard(this);
            return true;
        }else{
            return false;
        }
    }
}
