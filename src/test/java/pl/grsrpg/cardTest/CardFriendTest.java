package pl.grsrpg.cardTest;

import org.junit.Test;
import pl.grsrpg.card.Card;
import pl.grsrpg.card.CardFriend;
import pl.grsrpg.player.Player;
import pl.grsrpg.player.PlayerMage;

import static junit.framework.TestCase.*;

public class CardFriendTest {

    @Test
    public void executeCardWithCapacity() {
        Card card = new CardFriend(10, false, 0);
        Player player = new PlayerMage();
        player.setEqCapacity(5);
        assertEquals(5, player.getEqCapacity());
        assertFalse(player.hasFriend());
        card.execute(player);
        assertEquals(15, player.getEqCapacity());
        assertTrue(player.hasFriend());
    }

    @Test
    public void executeCardWithAddPoint() {
        Card card = new CardFriend(0, true, 0);
        Player player = new PlayerMage();
        player.setEqCapacity(5);
        card.execute(player);
        assertTrue(player.hasFriend());
        assertTrue(player.hasAdditionalPoint());
    }

    @Test
    public void executeCardWithMultiplierGold() {
        Card card = new CardFriend(0, false, (float) 1.2);
        Player player = new PlayerMage();
        assertEquals(1, player.getGoldMultiplier(), 0.01);
        assertTrue(card.execute(player));
        assertEquals(1.2, player.getGoldMultiplier(), 0.01);
    }

    @Test
    public void failCard() {
        Card card = new CardFriend(10, true, 0);
        Player player = new PlayerMage();
        player.setEqCapacity(5);
        card.execute(player);
        assertEquals(15, player.getEqCapacity());
        assertFalse(player.hasAdditionalPoint());
        assertEquals(1, player.getGoldMultiplier(), 0.01);
    }
}
