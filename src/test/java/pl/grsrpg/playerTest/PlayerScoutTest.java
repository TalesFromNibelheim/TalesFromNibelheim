package pl.grsrpg.playerTest;


import org.junit.Test;
import pl.grsrpg.player.Player;
import pl.grsrpg.player.PlayerScout;
import pl.grsrpg.utils.Attribute;

import static org.junit.Assert.*;

public class PlayerScoutTest {

    @Test
    public void criticalArrowTest()
    {
        PlayerScout player = new PlayerScout("scout");
        player.setAdditionalAgility(50);
        assertEquals(50, player.getAdditionalAgility(), 0.01);
        assertEquals(300, player.criticalArrow(), 101);
    }

    @Test
    public void seriesOfArrowsTest()
    {
        PlayerScout player = new PlayerScout("scout");
        player.setAdditionalAgility(50);
        assertEquals(240, player.seriesOfArrows(), 121);
    }

    @Test
    public void repeatableArrowTest()
    {
        PlayerScout player = new PlayerScout("scout");
        player.setAdditionalAgility(50);
        assertEquals(160, player.repeatableArrow(), 0.01);
    }

    @Test
    public void basicAttackTest()
    {
        PlayerScout player = new PlayerScout("scout");
        player.setAdditionalAgility(50);
        assertEquals(16, player.basicAttack(), 1);
    }

    @Test
    public void getStartAttributeTest()
    {
        Player player = new PlayerScout();
        assertEquals(30, player.getStartAttribute(Attribute.AGILITY), 0.01);
        assertEquals(20, player.getStartAttribute(Attribute.STRENGTH), 0.01);
        assertEquals(20, player.getStartAttribute(Attribute.MAGICPOINTS), 0.01 );
        assertEquals(100, player.getStartAttribute(Attribute.MAXHEALTH), 0.01 );
    }
}
