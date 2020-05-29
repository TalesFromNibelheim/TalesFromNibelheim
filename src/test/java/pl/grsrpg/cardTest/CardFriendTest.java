package pl.grsrpg.cardTest;

import org.junit.Test;
import pl.grsrpg.card.Card;
import pl.grsrpg.card.CardFriend;
import pl.grsrpg.player.Player;
import pl.grsrpg.player.PlayerMage;

import static junit.framework.TestCase.*;

public class CardFriendTest {

    @Test
    public void executeCardWithCapacity()
    {
        Card card = new CardFriend(10,false,0);
        Player player = new PlayerMage();
        player.setEquipmentCapacity(5);
        assertEquals(5, player.getEquipmentCapacity());
        assertFalse(player.getFriend());
        card.execute(player);
        assertEquals(15, player.getEquipmentCapacity());
        assertTrue(player.getFriend());
    }
    @Test
    public void executeCardWithAddPoint()
    {
        Card card = new CardFriend(0,true,0);
        Player player = new PlayerMage();
        player.setEquipmentCapacity(5);
        card.execute(player);
        assertTrue(player.getFriend());
        assertTrue(player.getAddPoint());
    }
    @Test
    public void executeCardWithMultiplierGold()
    {
        Card card = new CardFriend(0,false, (float) 1.2);
        Player player = new PlayerMage();
        assertEquals(1, player.getMultiplierGold(), 0.01);
        assertTrue(card.execute(player));
        assertEquals(1.2, player.getMultiplierGold(), 0.01);
    }
    @Test
    public void failCard()
    {
        Card card = new CardFriend(10,true, 0);
        Player player = new PlayerMage();
        player.setEquipmentCapacity(5);
        card.execute(player);
        assertEquals(5, player.getEquipmentCapacity());
        assertFalse(player.getAddPoint());
        assertEquals(1, player.getMultiplierGold(), 0.01);
    }
}
