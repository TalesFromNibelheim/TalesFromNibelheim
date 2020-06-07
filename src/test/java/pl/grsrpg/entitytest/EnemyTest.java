package pl.grsrpg.entitytest;

import org.junit.Test;
import pl.grsrpg.entity.Enemy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EnemyTest {

    @Test
    public void takeDamageTest()
    {
        Enemy enemy = new Enemy("enemy", 100,100,100,100);
        enemy.takeDamage(25);
        assertEquals(75, enemy.getHealth(), 0.01 );
    }

    @Test
    public void cloneTest()
    {
        Enemy enemy = new Enemy("enemy", 100,100,100,100);
        assertNotNull(enemy.clone());
    }
}
