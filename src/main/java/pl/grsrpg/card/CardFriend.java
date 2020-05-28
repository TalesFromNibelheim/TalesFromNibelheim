package pl.grsrpg.card;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.grsrpg.entity.Entity;
import pl.grsrpg.player.IPlayer;

@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper =  true)
public class CardFriend extends Card {
    private Entity entity;

    @Override
    public boolean execute(IPlayer player) {
        return false;
    }
}
