package pl.grsrpg.entitytest;

import org.junit.Test;
import pl.grsrpg.entity.Boss;

import static org.junit.Assert.assertEquals;


public class BossTest {

    @Test
    public void headHunterTest()
    {
        Boss boss = new Boss();
         assertEquals(12, boss.headHunter(10), 2.01);
    }

    @Test
    public void rageTest()
    {
        Boss boss = new Boss("boss", 100, 20,20,20);
        boss.setMultiplier(2);
        boss.setHealth((float) (0.1*boss.getBaseMaxHealth()));
        assertEquals(15, boss.rage(10), 0.01);
        boss.setHealth(100);
        assertEquals(10, boss.rage(10), 0.01);
    }

    @Test
    public void finalShotTest()
    {
        Boss boss = new Boss("boss", 100, 20,20,20);
        boss.setMultiplier(2);
        assertEquals(40, boss.finalShot(10),0.01);
    }
}
