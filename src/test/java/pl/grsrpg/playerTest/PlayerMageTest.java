package pl.grsrpg.playerTest;

import org.junit.Test;
import pl.grsrpg.card.Card;
import pl.grsrpg.card.CardItem;
import pl.grsrpg.card.ICard;
import pl.grsrpg.player.Player;
import pl.grsrpg.player.PlayerMage;
import junit.framework.TestSuite;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlayerMageTest {

    @Test
    public void addCardTest() {
        Player player = new PlayerMage();
        player.setEquipmentCapacity(1);
        Card card = new CardItem("1", "", true,  1, 2, 3, 4, 0, 2, 3, PlayerMage.class);
        assertTrue(player.addCard(card));
        Card card1 = new CardItem("2", "", true, 3, 4, 5, 7, 0, 4, 3, PlayerMage.class);
        assertFalse(player.addCard(card1));
    }

    @Test
    public void failAddCardTest() {
        Player player = new PlayerMage();
        player.setEquipmentCapacity(0);
        Card card = new CardItem("1", "", true, 1, 2, 3, 4, 0, 2, 3, PlayerMage.class);
        assertFalse(player.addCard(card));
    }

    @Test
    public void removeCardTest() {
        Player player = new PlayerMage();
        player.setEquipmentCapacity(10);
        Card card = new CardItem("1", "", true, 1, 2, 3, 4, 0, 2, 3, PlayerMage.class);
        Card card1 = new CardItem("2", "", true, 3, 4, 5, 7, 0, 4, 3, PlayerMage.class);
        Card card2 = new CardItem("3", "", true, 4, 5, 6, 4, 0, 1, 4, PlayerMage.class);
        card.setName("C1");
        card1.setName("C2");
        card2.setName("C3");
        player.addCard(card);
        player.addCard(card1);
        player.addCard(card2);
        player.removeCard("C1");
        List<ICard> tempCard = player.getCards();
        for (ICard iCard : tempCard) assertNotEquals(iCard.getName(), card.getName());
        player.removeCard("C2");
        for (ICard iCard : tempCard) assertNotEquals(iCard.getName(), card1.getName());
        player.removeCard("C3");
        for (ICard iCard : tempCard) assertNotEquals(iCard.getName(), card2.getName());
    }

    //TODO te testy sie cos psują a to git testy są
    @Test
    public void recalculateAttributesTest() {
        Player player = new PlayerMage();
        player.setEquipmentCapacity(3);
        Card card = new CardItem("1", "", true, 1, 2, 3, 4, 0, 2, 3, PlayerMage.class);
        Card card1 = new CardItem("2", "", true, 3, 4, 5, 7, 0, 4, 3, PlayerMage.class);
        Card card2 = new CardItem("3", "", true, 4, 5, 6, 4, 0, 1, 4, PlayerMage.class);
        player.addCard(card);
        player.addCard(card1);
        player.addCard(card2);
        player.recalculateAttributes();
        assertEquals(8, player.getAdditionalMaxHealth(), 0.01);
        assertEquals(11, player.getAdditionalStrength(), 0.01);
        assertEquals(14, player.getAdditionalAgility(), 0.01);
        assertEquals(15, player.getAdditionalMagicPoints(), 0.01);
        assertEquals(10, player.getArmor(), 0.01);
    }

    @Test
    public void AdditionalTest() {
        Player player = new PlayerMage();
        player.setEquipmentCapacity(2);
        ICard card = new CardItem("1", "", true, 1, 2, 3, 4, 0, 2, 3, PlayerMage.class);
        player.addCard(card);
        player.recalculateAttributes();
        assertEquals(1, player.getAdditionalMaxHealth(), 0.01);
        assertEquals(2, player.getAdditionalStrength(), 0.01);
        assertEquals(3, player.getAdditionalAgility(), 0.01);
        assertEquals(4, player.getAdditionalMagicPoints(), 0.01);
        assertEquals(3, player.getArmor(), 0.01);
    }

    @Test
    public void removeGoldTest() {
        Player player = new PlayerMage();
        player.setGold(100);
        assertEquals(100, player.getGold(), 0.01);
        assertTrue(player.removeGold(50));
        assertEquals(50, player.getGold(), 0.01);
        assertFalse(player.removeGold(70));
        assertTrue(player.removeGold(50));
        assertEquals(0, player.getGold(), 0.01);
    }
}