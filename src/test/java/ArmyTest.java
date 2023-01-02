import TestResources.UnitsGnotts;
import WGames.Model.Classes.Army;
import WGames.Model.Classes.Filewriter;
import WGames.Model.Units.Unit;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ArmyTest {

    @Test
    public void testConstructorWithName(){
        Army army = new Army("Army");

        assertEquals(army.getName(),"Army");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionEmptyNameConstructorWithName(){
        Army army = new Army("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionNameLengthConstructorWithName(){
        Army army = new Army("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionSymbolNameConstructorWithName(){
        Army army = new Army("+");
    }

    @Test
    public void testConstructorWithNameAndList(){
        List<Unit> units = new ArrayList<>();
        Army army = new Army("Army", units);

        assertEquals(army.getName(),"Army");
        assertEquals(army.getAllUnits().size(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionEmptyNameConstructorWithNameAndList(){
        List<Unit> units = new ArrayList<>();
        Army army = new Army("", units);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionSymbolNameConstructorWithNameAndList(){
        List<Unit> units = new ArrayList<>();
        Army army = new Army("+", units);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionNameLengthConstructorWithNameAndList(){
        List<Unit> units = new ArrayList<>();
        Army army = new Army("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr", units);
    }

    @Test
    public void testAddToArmy() {
        UnitsGnotts unitsGnotts = new UnitsGnotts();

        ArrayList<Unit> gnotts = unitsGnotts.getGnotts();
        gnotts.add(unitsGnotts.getGnott1());

        assertEquals(1, gnotts.size());
    }

    @Test
    public void testAddAllToArmy() {
        UnitsGnotts unitsGnotts = new UnitsGnotts();

        ArrayList<Unit> gnotts = new ArrayList<>();
        Army army = new Army("hei", gnotts);
        army.addAll(unitsGnotts.getSevralGnotts());

        assertEquals(4, army.getAllUnits().size());
    }


    @Test
    public void testRemoveFromArmy() {
        UnitsGnotts unitsGnotts = new UnitsGnotts();

        ArrayList<Unit> gnotts = unitsGnotts.getSevralGnotts();
        gnotts.remove(0);

        assertEquals(3, gnotts.size());
    }

    @Test
    public void testHasUnits() {
        UnitsGnotts unitsGnotts = new UnitsGnotts();

        assertTrue(unitsGnotts.premier().hasUnits());
        assertFalse(unitsGnotts.deuxieme().hasUnits());
    }

    @Test
    public void testGetInfantryUnits(){
        UnitsGnotts unitsGnotts = new UnitsGnotts();

        assertEquals(1,unitsGnotts.premier().getInfantryUnits().size());
    }

    @Test
    public void testGetCommanderUnits(){
        UnitsGnotts unitsGnotts = new UnitsGnotts();

        assertEquals(1,unitsGnotts.premier().getCommanderUnits().size());
    }

    @Test
    public void testCavalryUnits(){
        UnitsGnotts unitsGnotts = new UnitsGnotts();

        assertEquals(1,unitsGnotts.premier().getCavalryUnits().size());
    }

    @Test
    public void testGetRangedUnits(){
        UnitsGnotts unitsGnotts = new UnitsGnotts();
        Filewriter filewriter = new Filewriter();

        assertEquals(1,unitsGnotts.premier().getRangedUnits().size());
    }
}
