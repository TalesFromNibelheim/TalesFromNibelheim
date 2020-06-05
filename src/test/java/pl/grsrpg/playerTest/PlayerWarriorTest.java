package pl.grsrpg.playerTest;


import junit.framework.TestSuite;
import org.junit.Test;
import pl.grsrpg.player.Player;
import pl.grsrpg.player.PlayerMage;
import pl.grsrpg.player.PlayerWarrior;
import pl.grsrpg.utils.Attribute;

import java.util.List;

import static org.junit.Assert.*;

public class PlayerWarriorTest {

    @Test
    public void getStartAttributeTest()
    {
        Player player = new PlayerWarrior();
        assertEquals(2, player.getStartAttribute(Attribute.AGILITY));
        assertEquals(5, player.getStartAttribute(Attribute.STRENGTH));
        assertEquals(20, player.getStartAttribute(Attribute.MAXHEALTH));
        assertEquals(2, player.getStartAttribute(Attribute.MAGICPOINTS));
    }
}
