package pl.grsrpg.entityTest;

import org.junit.Test;
import pl.grsrpg.entity.Enemy;

import static org.junit.Assert.*;

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
