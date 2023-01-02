import WGames.Model.Classes.Terrain;
import WGames.Model.Units.Mages.BlackMage;
import WGames.Model.Units.Unit;
import WGames.Model.Units.Standard.CavalryUnit;
import WGames.Model.Units.Standard.CommanderUnit;
import WGames.Model.Units.Standard.InfantryUnit;
import WGames.Model.Units.Standard.RangedUnit;
import TestResources.UnitsNotGnotts;
import org.junit.Test;
import static org.junit.Assert.*;

public class UnitsTest {

    @Test
    public void testUnitProperties(){
        UnitsNotGnotts unitsNotGnotts = new UnitsNotGnotts();

        CommanderUnit u = unitsNotGnotts.getDictator();

        assertEquals("Borat", u.getName());
        assertEquals(5, u.getHealth());
        assertEquals(25, u.getAttack());
        assertEquals(15, u.getArmor());
    }


    @Test
    public void testAttack(){
        UnitsNotGnotts unitsNotGnotts = new UnitsNotGnotts();

        RangedUnit u1 = unitsNotGnotts.getBard();
        CavalryUnit u2 = unitsNotGnotts.getMountedForce();
        u2.attack(u1);

        assertEquals(12, u1.getHealth());
    }

    @Test
    public void testSetHealth(){
        UnitsNotGnotts unitsNotGnotts = new UnitsNotGnotts();

        RangedUnit u = unitsNotGnotts.getBard();
        u.setHealth(88);

        assertEquals(88, u.getHealth());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testExceptionName(){
        Unit rangedUnit = new RangedUnit("",1,1,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionNameLength(){
        Unit rangedUnit = new RangedUnit("Robin Hood of the Hood by the Hood Hood",1,1,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionNameSymbols(){
        Unit rangedUnit = new RangedUnit("Robin Hood++",1,1,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionHealth0(){
        Unit rangedUnit = new RangedUnit("Robin Hood",0,1,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionHealthOver500(){
        Unit rangedUnit = new RangedUnit("Robin Hood",501,1,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionAttack0(){
        Unit rangedUnit = new RangedUnit("Robin Hood",1,-0,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionAttackOver100(){
        Unit rangedUnit = new RangedUnit("Robin Hood",1,101,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionArmorNegative(){
        Unit rangedUnit = new RangedUnit("Robin Hood",1,1,-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionArmorOver100(){
        Unit rangedUnit = new RangedUnit("Robin Hood",1,1,101);
    }

    @Test
    public void testRangedAttackBonus(){
        RangedUnit rangedUnit = new RangedUnit("ra", 20);

        assertEquals(4, rangedUnit.getAttackBonus(Terrain.HILL));
        assertEquals(2, rangedUnit.getAttackBonus(Terrain.FOREST));
        assertEquals(3, rangedUnit.getAttackBonus(Terrain.PLAINS));
    }

    @Test
    public void testRangedResistBonus(){
        RangedUnit rangedUnit = new RangedUnit("ra", 200);
        BlackMage blackMage = new BlackMage("bl", 100, 100);

        blackMage.attack(rangedUnit, Terrain.PLAINS);
        assertEquals(200-29+8+6, rangedUnit.getHealth());

        blackMage.attack(rangedUnit, Terrain.PLAINS);
        assertEquals(185-29+8+4, rangedUnit.getHealth());

        blackMage.attack(rangedUnit, Terrain.PLAINS);
        assertEquals(168-29+8+2, rangedUnit.getHealth());
    }

    @Test
    public void testInfantryAttackBonus(){
        InfantryUnit infantryUnit = new InfantryUnit("in", 20);

        assertEquals(3, infantryUnit.getAttackBonus(Terrain.FOREST));
        assertEquals(2, infantryUnit.getAttackBonus(Terrain.PLAINS));
    }

    @Test
    public void testInfantryResistBonus(){
        InfantryUnit infantryUnit = new InfantryUnit("in", 200);
        BlackMage blackMage = new BlackMage("bl", 100, 100);

        blackMage.attack(infantryUnit, Terrain.FOREST);
        assertEquals(200-33+10+2, infantryUnit.getHealth());

        blackMage.attack(infantryUnit, Terrain.HILL);
        assertEquals(179-33+10+1, infantryUnit.getHealth());
    }

    @Test
    public void testCavalryAttackBonus(){
        CavalryUnit cavalryUnit = new CavalryUnit("ca", 20);
        BlackMage blackMage = new BlackMage("bl", 100, 100);

        assertEquals(7, cavalryUnit.getAttackBonus(Terrain.PLAINS));

        cavalryUnit.attack(blackMage);
        assertEquals(3, cavalryUnit.getAttackBonus(Terrain.PLAINS));
        assertEquals(2, cavalryUnit.getAttackBonus(Terrain.FOREST));
    }

    @Test
    public void testCavalryResistBonus(){
        CavalryUnit cavalryUnit = new CavalryUnit("ca", 200);
        BlackMage blackMage = new BlackMage("bl", 100, 100);

        blackMage.attack(cavalryUnit, Terrain.FOREST);
        assertEquals(200-33+12, cavalryUnit.getHealth());

        blackMage.attack(cavalryUnit, Terrain.HILL);
        assertEquals(179-33+13, cavalryUnit.getHealth());
    }


}






