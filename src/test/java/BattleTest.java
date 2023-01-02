import TestResources.UnitsGnotts;
import TestResources.UnitsNotGnotts;
import WGames.Model.Classes.Army;
import WGames.Model.Classes.Battle;
import WGames.Model.Classes.Terrain;
import WGames.Model.Units.Mages.WhiteMage;
import WGames.Model.Units.Standard.RangedUnit;
import WGames.Model.Units.Unit;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BattleTest {


    @Test
    public void battleTest() throws InterruptedException {
        UnitsNotGnotts unitsNotGnotts = new UnitsNotGnotts();
        UnitsGnotts unitsGnotts = new UnitsGnotts();

        Army notGnotts = unitsNotGnotts.premier();
        Army gnotts = unitsGnotts.premier();
        Battle war = new Battle(notGnotts, gnotts, Terrain.FOREST);
        Army winner = war.simulate();

        assertTrue(winner.getName().equals(notGnotts.getName()) || winner.getName().equals(gnotts.getName()));
    }

    @Test
    public void slowBattleTest() throws InterruptedException {
        List<Unit> units = new ArrayList<>();
        List<Unit> units2 = new ArrayList<>();

        Army army1 = new Army("1", units);
        Army army2 = new Army("2", units2);

        Battle battle = new Battle(army1,army2,Terrain.DESERT);

        army1.add(new RangedUnit("ra", 100, 1,100));
        army2.add(new RangedUnit("ra",100, 1, 100));

        assertTrue(battle.slowSimulate().equals("ra attacks ra"));

    }

    @Test
    public void slowBattleDeathTest() throws InterruptedException {
        List<Unit> units = new ArrayList<>();
        List<Unit> units2 = new ArrayList<>();

        Army army1 = new Army("1", units);
        Army army2 = new Army("2", units2);

        Battle battle = new Battle(army1,army2,Terrain.DESERT);

        army1.add(new RangedUnit("ra", 1, 100,1));
        army2.add(new RangedUnit("ra",1, 100, 1));

        String fight = battle.slowSimulate();
        if(army2.getAllUnits().size() != 0){
            assertTrue(fight.equals("ra kills ra (1)"));
        }else{
            assertTrue(fight.equals("ra kills ra (2)"));
        }
    }

    @Test
    public void slowBattleHealTest() throws InterruptedException {
        List<Unit> units = new ArrayList<>();
        List<Unit> units2 = new ArrayList<>();

        Army army1 = new Army("1", units);
        Army army2 = new Army("2", units2);

        Battle battle = new Battle(army1,army2,Terrain.DESERT);

        army1.add(new WhiteMage("wh", 1, 1,1,20));
        army1.add(new WhiteMage("wh", 1, 1,1,20));
        army2.add(new WhiteMage("wh", 1, 1,1,20));
        army2.add(new WhiteMage("wh", 1, 1,1,20));

        assertTrue(battle.slowSimulate().equals("wh heals wh for 13 hp"));
    }

    @Test
    public void testWhiteMageStalemateSimulate(){
        List<Unit> units = new ArrayList<>();
        List<Unit> units2 = new ArrayList<>();

        Army army1 = new Army("1", units);
        Army army2 = new Army("2", units2);

        Battle battle = new Battle(army1,army2,Terrain.DESERT);

        army1.add(new WhiteMage("wh", 3));
        army2.add(new WhiteMage("wh",3));

        assertEquals(null, battle.simulate());
    }

    @Test
    public void testWhiteMageStalemateSlowSimulate(){
        List<Unit> units = new ArrayList<>();
        List<Unit> units2 = new ArrayList<>();

        Army army1 = new Army("1", units);
        Army army2 = new Army("2", units2);

        Battle battle = new Battle(army1,army2,Terrain.DESERT);

        army1.add(new WhiteMage("wh", 3));
        army2.add(new WhiteMage("wh",3));

        assertEquals("stalemate", battle.slowSimulate());
    }

    @Test(expected = IllegalStateException.class)
    public void testExceptionSelfAttack(){
        List<Unit> units = new ArrayList<>();

        Army army1 = new Army("1", units);

        Battle battle = new Battle(army1,army1,Terrain.DESERT);
    }

    @Test
    public void testNullSlowSimulate(){
        List<Unit> units = new ArrayList<>();
        List<Unit> units2 = new ArrayList<>();

        Army army1 = new Army("1", units);
        Army army2 = new Army("2", units2);

        Battle battle = new Battle(army1,army2,Terrain.DESERT);
        Battle battle2 = new Battle(army2,army1,Terrain.DESERT);

        army2.add(new WhiteMage("wh",3));

        assertEquals("", battle.slowSimulate());
        assertEquals("", battle2.slowSimulate());
    }
}
