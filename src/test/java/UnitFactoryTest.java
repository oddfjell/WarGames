import TestResources.UnitsGnotts;
import WGames.Model.Classes.Army;
import WGames.Model.Classes.UnitFactory;
import WGames.Model.Units.Standard.RangedUnit;
import WGames.Model.Units.Unit;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UnitFactoryTest {

    @Test
    public void unitFactoryTest(){
        UnitsGnotts unitsGnotts = new UnitsGnotts();
        UnitFactory unitFactory = new UnitFactory();

        Army army = unitsGnotts.premier();

        for(int i = 0; i < 2; i++){
            army.add(unitFactory.getUnit("RangedUnit", "Geir", 100));
        }

        assertEquals(6, army.getAllUnits().size());

    }

    @Test
    public void unitFactorySeveralUnitsTest(){
        UnitFactory unitFactory = new UnitFactory();

        RangedUnit rangedUnit = new RangedUnit("GEIR", 7);
        List<Unit> units =  unitFactory.makeUnits(rangedUnit.getID(), rangedUnit.getName(), rangedUnit.getHealth(), 10);
        Army army = new Army("TheGEIRS", units);

        assertEquals(army.getAllUnits().size(), 10);
    }

}
