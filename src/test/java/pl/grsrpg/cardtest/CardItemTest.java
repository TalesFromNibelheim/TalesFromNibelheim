package pl.grsrpg.cardtest;

import org.junit.Test;
import pl.grsrpg.card.Card;
import pl.grsrpg.card.CardItem;
import pl.grsrpg.player.Player;
import pl.grsrpg.player.PlayerMage;
import pl.grsrpg.player.PlayerScout;
import pl.grsrpg.player.PlayerWarrior;

import static junit.framework.TestCase.assertEquals;

public class CardItemTest {

    @Test
    public void increaseAttributesWarriorTest(){
        Card card = new CardItem("1", "", true, 1,2,3,4,0,2,3, PlayerWarrior.class);
        Player player = new PlayerWarrior();
        player.setEqCapacity(2);
        card.execute(player);
        assertEquals( 3, player.getAdditionalAgility(),0.01);
        assertEquals(1, player.getAdditionalMaxHealth(),0.01);
        assertEquals(2, player.getAdditionalStrength(),0.01);
        assertEquals(4, player.getAdditionalMagicPoints(),0.01);
        assertEquals(3, player.getArmor(),0.01);
    }
    @Test
    public void increaseAttributesScoutTest()
    {
        Card card = new CardItem("1", "", true, 7,4,1,2,0,10,3, PlayerScout.class);
        Player player = new PlayerScout();
        player.setEqCapacity(2);
        card.execute(player);
        assertEquals( 1, player.getAdditionalAgility(),0.01);
        assertEquals(7, player.getAdditionalMaxHealth(),0.01);
        assertEquals(4, player.getAdditionalStrength(),0.01);
        assertEquals(2, player.getAdditionalMagicPoints(),0.01);
        assertEquals(3, player.getArmor(),0.01);
    }
    @Test
    public void increaseAttributesMageTest()
    {
        Card card = new CardItem("1", "", true, 8,7,6,5,0,11,4, PlayerScout.class);
        Player player = new PlayerScout();
        player.setEqCapacity(2);
        card.execute(player);
        assertEquals( 6, player.getAdditionalAgility(),0.01);
        assertEquals(8, player.getAdditionalMaxHealth(),0.01);
        assertEquals(7, player.getAdditionalStrength(),0.01);
        assertEquals(5, player.getAdditionalMagicPoints(),0.01);
        assertEquals(4, player.getArmor(),0.01);
    }
    @Test
    public void failIncreaseAttributesTest()
    {
        Card card = new CardItem("1", "", true, 8,7,6,5,0,11,4, PlayerWarrior.class);
        Player player = new PlayerScout();
        card.execute(player);
        assertEquals( 0, player.getAdditionalAgility(),0.01);
        assertEquals(0, player.getAdditionalMaxHealth(),0.01);
        assertEquals(0, player.getAdditionalStrength(),0.01);
        assertEquals(0, player.getAdditionalMagicPoints(),0.01);
        assertEquals(0, player.getArmor(),0.01);
    }
    @Test
    public void addGoldFromCardItemToWarriorTest()
    {
        Card card = new CardItem("1", "", false, 0,0,0,0,1000,1000,0,null);
        Player player = new PlayerWarrior();
        card.execute(player);
        assertEquals(player.getGold(),1000);
    }
    @Test
    public void addGoldFromCardItemToScoutTest()
    {
        Card card = new CardItem("1", "", false, 0,0,0,0,1000,1000,0,null);
        Player player = new PlayerScout();
        card.execute(player);
        assertEquals(player.getGold(),1000);
    }
    @Test
    public void addGoldFromCardItemToMageTest()
    {
        Card card = new CardItem("1", "", false, 0,0,0,0,1000,1000,0,null);
        Player player = new PlayerMage();
        card.execute(player);
        assertEquals(player.getGold(),1000);
    }
}
