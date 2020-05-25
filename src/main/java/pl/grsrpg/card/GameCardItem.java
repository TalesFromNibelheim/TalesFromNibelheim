package pl.grsrpg.card;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.grsrpg.player.GamePlayerMage;
import pl.grsrpg.player.GamePlayerScout;
import pl.grsrpg.player.GamePlayerWarrior;
import pl.grsrpg.player.Player;

@Getter
@ToString
@Setter
public class GameCardItem extends GameCard {
    private int health;
    private int strength;
    private int agility;
    private int magicPoints;
    private int gold;
    private int itemValue;
    private float armor;
    private Class profession;

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
            if(gold != 0)
                player.addCard(this);
            return true;
        }else{
            return false;
        }
    }
}
