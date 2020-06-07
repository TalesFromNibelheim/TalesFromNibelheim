package pl.grsrpg.playertest;

import org.junit.Test;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.entity.Entity;
import pl.grsrpg.player.Player;
import pl.grsrpg.player.PlayerWarrior;
import pl.grsrpg.utils.Attribute;

import static org.junit.Assert.assertEquals;


public class PlayerWarriorTest {

    @Test
    public void getStartAttributeTest()
    {
        Player player = new PlayerWarrior("warrior");
        assertEquals(20, player.getStartAttribute(Attribute.AGILITY));
        assertEquals(30, player.getStartAttribute(Attribute.STRENGTH));
        assertEquals(120, player.getStartAttribute(Attribute.MAXHEALTH));
        assertEquals(20, player.getStartAttribute(Attribute.MAGICPOINTS));
    }

    @Test
    public void knockoutTest()
    {
        PlayerWarrior playerWarrior = new PlayerWarrior("warrior");
        playerWarrior.setBaseStrength(20);
        assertEquals(5,playerWarrior.knockdown(), 1.01);
        assertEquals(20, playerWarrior.getMagicPoints(), 0.01);
    }

    @Test
    public void cleaveTest()
    {
        PlayerWarrior playerWarrior = new PlayerWarrior("warrior");
        playerWarrior.setBaseStrength(20);
        Entity enemy = new Enemy();
        assertEquals(40,playerWarrior.cleave(enemy), 1.01);
    }

}
