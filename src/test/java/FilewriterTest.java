import WGames.Model.Classes.Army;
import WGames.Model.Classes.Filewriter;
import TestResources.UnitsNotGnotts;
import WGames.Model.Units.Mages.WhiteMage;
import WGames.Model.Units.Standard.RangedUnit;
import WGames.Model.Units.Unit;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FilewriterTest {

    @Test
    public void writeArmyInFileTest(){
        Filewriter filewriter = new Filewriter();
        UnitsNotGnotts unitsNotGnotts = new UnitsNotGnotts();

        filewriter.writeArmyInFile(unitsNotGnotts.premier());
        File file = new File("src\\main\\resources\\Files\\f4.csv");

        assertTrue(file.isFile());
        file.delete();
    }

    @Test
    public void makeArmyFromFileTest(){
        Filewriter filewriter = new Filewriter();

        File file = new File("src\\main\\resources\\Files\\testArmy.csv");
        Army army = filewriter.makeArmyFromFile(file);

        assertEquals(army.getName(), "testArmy");
    }

    @Test
    public void testWriteData(){
        Filewriter filewriter = new Filewriter();

        File file = new File("src\\main\\resources\\Files\\testArmy.csv");
        RangedUnit rangedUnit = new RangedUnit("j", 10);
        Army army1 = filewriter.makeArmyFromFile(file);

        filewriter.writeData("testArmy", rangedUnit);

        Army army2 = filewriter.makeArmyFromFile(file);

        assertEquals(army1.getAllUnits().size() + 1, army2.getAllUnits().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionWriteEmptyArmy(){
        Filewriter filewriter = new Filewriter();

        List<Unit> units = new ArrayList<>();
        Army army = new Army("1", units);

        filewriter.writeArmyInFile(army);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionWriteNoNameArmy(){
        Filewriter filewriter = new Filewriter();

        List<Unit> units = new ArrayList<>();
        units.add(new WhiteMage("wh",7));
        Army army = new Army("", units);

        filewriter.writeArmyInFile(army);
    }

    @Test(expected = ExceptionInInitializerError.class)
    public void testExceptionWriteNull(){
        Filewriter filewriter = new Filewriter();
        File file = new File("src\\main\\resources\\Files\\empty.csv");

        filewriter.makeArmyFromFile(file);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionWriteNonCSV(){
        Filewriter filewriter = new Filewriter();
        File file = new File("src\\main\\resources\\Files");

        filewriter.makeArmyFromFile(file);
    }
}
