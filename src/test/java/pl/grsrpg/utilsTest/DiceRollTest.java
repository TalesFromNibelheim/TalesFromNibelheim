package pl.grsrpg.utilsTest;

import junit.framework.TestSuite;
import org.junit.Test;
import pl.grsrpg.utils.DiceRoll;

import static org.junit.Assert.*;
import static pl.grsrpg.utils.DiceRoll.rollPrivate;
import static pl.grsrpg.utils.DiceRoll.rollPublic;

public class DiceRollTest {

    @Test
    public void rollPublicTest()
    {
        assertEquals(3, rollPublic(1, 6,true), 4);
        assertEquals(3, rollPublic(1, 6,false), 3);
        assertEquals(5,rollPublic(2,5,false), 5);
    }

    @Test
    public void rollPrivateTest()
    {
        assertEquals(3, rollPrivate(1, 6), 3);
        assertEquals(6, rollPrivate(2, 6), 6);
        assertEquals(9, rollPrivate(2, 6), 9);
    }
}
