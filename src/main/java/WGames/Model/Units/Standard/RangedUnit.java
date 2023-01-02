package WGames.Model.Units.Standard;

import WGames.Model.Classes.Terrain;
import WGames.Model.Units.Unit;

public class RangedUnit extends Unit {

    /**
     * Constructor of the RangedUnit class
     * @param name name
     * @param health health
     * @param attack attack
     * @param armor armor
     */
    public RangedUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Constructor of the RangedUnit class with defined values for attack and armor
     * @param name name
     * @param health health
     */
    public RangedUnit(String name, int health) {
        super(name, health, 15, 8);
    }

    /**
     * the abstract method from Unit has now a body
     * If the unit attacks in the terrain "HILL", it gains a little attack bonus
     * If the unit attacks in the terrain "FOREST", it loses some of its attack bonus
     * @return attackBonus
     */
    @Override
    public int getAttackBonus(Terrain terrain){
        if(terrain.equals(Terrain.HILL)){
            return 4;
        } else if(terrain.equals(Terrain.FOREST)){
            return 2;
        } else{
            return 3;
        }
    }

    /**
     * the abstract method from Unit has now a body
     * I used int attacked to change the resistance bonus value
     * @return resistBonus
     */
    int attacked = -1;
    @Override
    public int getResistBonus(Terrain terrain){
        attacked++;
        switch (attacked){
            case 0: return 6;
            case 1: return 4;
            default: return 2;
        }

    }

    /**
     * the abstract method from Unit has now a body
     * Provides identification the unit
     * @return ID
     */
    @Override
    public String getID(){
        return "RangedUnit";
    }

}
