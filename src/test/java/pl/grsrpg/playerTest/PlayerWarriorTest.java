package pl.grsrpg.playerTest;


import junit.framework.TestSuite;
import org.junit.Test;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.entity.Entity;
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

    @Test
    public void knockoutTest()
    {
        PlayerWarrior playerWarrior = new PlayerWarrior();
        playerWarrior.setBaseStrength(20);
        assertEquals(5,playerWarrior.knockdown(), 1.01);
        assertEquals(0, playerWarrior.getMagicPoints(), 0.01);
    }

    @Test
    public void cleaveTest()
    {
        PlayerWarrior playerWarrior = new PlayerWarrior();
        playerWarrior.setBaseStrength(20);
        Entity enemy = new Enemy();
        assertEquals(40,playerWarrior.cleave(enemy), 1.01);
    }

}
