import WGames.Model.Classes.Terrain;
import WGames.Model.Units.Mages.BlackMage;
import WGames.Model.Units.Mages.WhiteMage;
import WGames.Model.Units.Standard.RangedUnit;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MageTest {

    @Test
    public void makeMageTest(){
        WhiteMage whiteMage = new WhiteMage("wh", 9, 9, 9, 10);
        BlackMage blackMage = new BlackMage("bl",9,9,9,9);

        assertEquals("BlackMage",blackMage.getID());
        assertEquals("WhiteMage",whiteMage.getID());
    }

    @Test
    public void makeBlackMageTest(){
        BlackMage blackMage1 = new BlackMage("bl",9,9,9,9);
        BlackMage blackMage2 = new BlackMage("bl",9,9);
        BlackMage blackMage3 = new BlackMage("bl",9);


        assertEquals("BlackMage",blackMage1.getID());
        assertEquals("BlackMage",blackMage2.getID());
        assertEquals("BlackMage",blackMage3.getID());

        assertEquals(9,blackMage1.getHealth());
        assertEquals(9,blackMage2.getHealth());
        assertEquals(9,blackMage3.getHealth());

        assertEquals(9,blackMage1.getMana());
        assertEquals(100,blackMage3.getMana());

    }

    @Test
    public void blackMageAttack(){
        BlackMage blackMage = new BlackMage("bl",100);
        RangedUnit rangedUnit = new RangedUnit("ra", 100);

        blackMage.attack(rangedUnit, Terrain.DESERT);
        assertEquals(63, blackMage.getMana());
        assertEquals(77, rangedUnit.getHealth());

        blackMage.attack(rangedUnit, Terrain.DESERT);
        assertEquals(26, blackMage.getMana());
        assertEquals(52, rangedUnit.getHealth());

        blackMage.attack(rangedUnit, Terrain.DESERT);
        assertEquals(0, blackMage.getHealth());
        assertEquals(36, rangedUnit.getHealth());

    }

    @Test
    public void whiteMageHeal(){
        WhiteMage whiteMage = new WhiteMage("wh",100);
        RangedUnit rangedUnit = new RangedUnit("ra", 100);

        whiteMage.attack(rangedUnit, Terrain.DESERT);
        assertEquals(73, whiteMage.getMana());
        assertEquals(127, rangedUnit.getHealth());

        whiteMage.attack(rangedUnit, Terrain.DESERT);
        assertEquals(46, whiteMage.getMana());
        assertEquals(154, rangedUnit.getHealth());

        whiteMage.attack(rangedUnit, Terrain.DESERT);
        assertEquals(19, whiteMage.getMana());
        assertEquals(181, rangedUnit.getHealth());

        whiteMage.attack(rangedUnit, Terrain.DESERT);
        assertEquals(0, whiteMage.getHealth());
        assertEquals(200, rangedUnit.getHealth());

    }

    @Test
    public void attackBonus(){
        BlackMage blackMage = new BlackMage("bl",100);

        assertEquals(12, blackMage.getAttackBonus(Terrain.DESERT));
        assertEquals(8, blackMage.getAttackBonus(Terrain.FOREST));
        assertEquals(4, blackMage.getAttackBonus(Terrain.PLAINS));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionMana(){
        BlackMage blackMage = new BlackMage("bl",100, 1000);
    }
}
