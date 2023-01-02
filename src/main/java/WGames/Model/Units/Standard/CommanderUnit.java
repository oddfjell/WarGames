package WGames.Model.Units.Standard;

public class CommanderUnit extends CavalryUnit {

    /**
     * Constructor of the CommanderUnit class
     * @param name name
     * @param health health
     * @param attack attack
     * @param armor armor
     */
    public CommanderUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Constructor of the CommanderUnit class with defined values for attack and armor
     * @param name name
     * @param health health
     */
    public CommanderUnit(String name, int health) {
        super(name, health, 25, 15);
    }

    /**
     * the abstract method from Unit has now a body
     * Provides identification the unit
     * @return ID
     */
    @Override
    public String getID(){
        return "CommanderUnit";
    }
}
