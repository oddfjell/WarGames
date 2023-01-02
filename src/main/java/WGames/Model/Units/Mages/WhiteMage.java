package WGames.Model.Units.Mages;

import WGames.Model.Classes.Terrain;
import WGames.Model.Units.Unit;

public class WhiteMage extends Mage {

    /**
     * Constructor of the WhiteMage class
     * @param name name
     * @param health health
     * @param attack attack
     * @param armor armor
     * @param mana mana
     */
    public WhiteMage(String name, int health, int attack, int armor, int mana){
        super(name, health, attack, armor, mana);
    }

    /**
     * Constructor of the WhiteMage class with defined values for attack and armor
     * @param name name
     * @param health health
     * @param mana mana
     */
    public WhiteMage(String name, int health, int mana){
        super(name, health, 15, 5, mana);
    }

    /**
     * Constructor of the WhiteMage class with defined values for attack, armor and mana
     * @param name name
     * @param health health
     */
    public WhiteMage(String name, int health){
        super(name, health, 15, 5, 100);
    }

    /**
     * Altered attack method from the Unit class
     * The WhiteMage unit is a healer, so instead of attacking an opponent the mage heals a
     * companion. The healing goes through the armor of the companion and removes mana from
     * the mage. If the mage has less mana than healing ability, the mage uses the last mana
     * to heal.
     * WhiteMage and BlackMage falls to zero health if they have zero mana
     * @param companion companion
     */
    @Override
    public void attack(Unit companion){
        Terrain defaultTerrain = Terrain.DESERT;
        int unitHeal = this.getAttack() + this.getAttackBonus(defaultTerrain);

        int heal;
        if(unitHeal < this.getMana()){
            this.setMana(this.getMana() - unitHeal);
            heal = unitHeal;
        }else{
            heal = getMana();
            this.setHealth(0);
        }

        int companionHealth = companion.getHealth() + heal;
        companion.setHealth(companionHealth);
    }

    /**
     * Altered attack method from the Unit class with terrain
     * The WhiteMage unit is a healer, so instead of attacking an opponent the mage heals a
     * companion. The healing goes through the armor of the companion and removes mana from
     * the mage. If the mage has less mana than healing ability, the mage uses the last mana
     * to heal.
     * WhiteMage and BlackMage falls to zero health if they have zero mana
     * @param companion companion
     * @param terrain terrain
     */
    @Override
    public void attack(Unit companion, Terrain terrain){
        int unitHeal = this.getAttack() + this.getAttackBonus(terrain);

        int heal;
        if(unitHeal < this.getMana()){
            this.setMana(this.getMana() - unitHeal);
            heal = unitHeal;
        }else{
            heal = getMana();
            this.setHealth(0);
        }

        int companionHealth = companion.getHealth() + heal;
        companion.setHealth(companionHealth);
    }

    /**
     * the abstract method from Unit has now a body
     * Provides identification the unit
     * @return ID
     */
    @Override
    public String getID() {
        return "WhiteMage";
    }

}
