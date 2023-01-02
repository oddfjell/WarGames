package WGames.Model.Units.Mages;

import WGames.Model.Classes.Terrain;
import WGames.Model.Units.Unit;

public abstract class Mage extends Unit {

    private int mana;

    /**
     * The constructor of the Mage class
     * Mage inherits from unit, but has a slight difference. They use mana to attack
     * @param name name
     * @param health health
     * @param attack attack
     * @param armor  armor
     * @param mana mana
     * @throws IllegalArgumentException illegal argument exception
     */
    public Mage(String name, int health, int attack, int armor, int mana) throws IllegalArgumentException {
        super(name, health, attack, armor);
        if(mana > 0 && mana <= 500){
            this.mana = mana;
        }else{
            throw new IllegalArgumentException("The mana must be a positive integer between 1 and 500");
        }
    }

    /**
     * gets the mana of the unit
     */
    public int getMana() {
        return mana;
    }

    /**
     * sets the mana of the unit
     * @param mana mana
     */
    public void setMana(int mana){
        this.mana = mana;
    }

    /**
     * the abstract method from Unit has now a body
     * If the unit attacks in the terrain "DESERT", it gains a little attack bonus
     * If the unit attacks in the terrain "PLAINS", it loses some of its attack bonus
     * @param terrain terrain
     * @return attackBonus
     */
    @Override
    public int getAttackBonus(Terrain terrain) {
        if(terrain.equals(Terrain.DESERT)){
            return 12;
        } else if(terrain.equals(Terrain.PLAINS)){
            return 4;
        } else{
            return 8;
        }
    }

    /**
     * the abstract method from Unit has now a body
     * Mages does not have good armor
     * @param terrain terrain
     * @return resistBonus
     */
    @Override
    public int getResistBonus(Terrain terrain) {
        return 0;
    }

    /**
     * toString method
     * @return String
     */
    @Override
    public String toString(){
        return this.getID() + " || Name: " + this.getName() + " || Health: " + this.getHealth() + " || Attack: " + this.getAttack() + " || Armor: " + this.getArmor() + "\n|| Mana: " + this.getMana() + "\n";
    }
}
