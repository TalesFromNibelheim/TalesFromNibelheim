package pl.grsrpg.card;

import lombok.*;
import pl.grsrpg.entity.Entity;
import pl.grsrpg.player.IPlayer;

@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper =  true)
@AllArgsConstructor
@Getter
@Setter
public class CardFriend extends Card {

    private int capacity;
    private boolean addPoint;
    private float multiplierGold;

    public CardFriend(String name, String description, int capacity, boolean point, float multiplier)
    {
        super(name, description, true);
        this.capacity = capacity;
        this.addPoint = point;
        this.multiplierGold = multiplier;
    }

    @Override
    public boolean execute(IPlayer player) {
        if (this.capacity != 0 && !this.addPoint && this.multiplierGold == 0) {
            player.setEquipmentCapacity(player.getEquipmentCapacity() + capacity);
            player.setFriend(true);
            return true;
        }else if (this.capacity == 0 && this.addPoint && this.multiplierGold == 0){
            player.setAddPoint(true);
            player.setFriend(true);
            return true;
        }else if (this.capacity == 0 && !this.addPoint && this.multiplierGold != 0){
            player.setMultiplierGold(this.multiplierGold);
            player.setFriend(true);
            return true;
        }
        return false;
    }
}