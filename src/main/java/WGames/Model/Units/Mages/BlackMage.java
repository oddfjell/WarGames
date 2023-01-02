package WGames.Model.Units.Mages;

import WGames.Model.Classes.Terrain;
import WGames.Model.Units.Unit;

public class BlackMage extends Mage {

    /**
     * Constructor of the BlackMage class
     * @param name name
     * @param health health
     * @param attack attack
     * @param armor armor
     * @param mana mana
     */
    public BlackMage(String name, int health, int attack, int armor, int mana){
        super(name, health, attack, armor, mana);
    }

    /**
     * Constructor of the BlackMage class with defined values for attack and armor
     * @param name name
     * @param health health
     * @param mana mana
     */
    public BlackMage(String name, int health, int mana){
        super(name, health, 25, 5, mana);
    }

    /**
     * Constructor of the BlackMage class with defined values for attack, armor and mana
     * @param name name
     * @param health health
     */
    public BlackMage(String name, int health){
        super(name, health, 25, 5, 100);
    }

    /**
     * Altered attack method from the Unit class
     * The BlackMage attacks as normal, however, the mage uses mana to attack. If the mage starts
     * with 50 mana, then it can only do up to 50 damage.
     * WhiteMage and BlackMage falls to zero health if they have zero mana
     * @param opponent opponent
     */
    @Override
    public void attack(Unit opponent){
        Terrain defaultTerrain = Terrain.DESERT;
        int unitAttack = this.getAttack() + this.getAttackBonus(defaultTerrain);
        if (unitAttack > (this.getArmor() + this.getResistBonus(defaultTerrain))){
            int manaAttack;
            if(unitAttack < this.getMana()){
                this.setMana(this.getMana() - unitAttack);
                manaAttack = unitAttack;
            } else{
                manaAttack = getMana();
                this.setHealth(0);
            }
            int opponentHealth = opponent.getHealth() - manaAttack + (opponent.getArmor()+ opponent.getResistBonus(defaultTerrain));
            opponent.setHealth(opponentHealth);
        }
    }

    /**
     * Altered attack method from the Unit class with terrain
     * The BlackMage attacks as normal, however, the mage uses mana to attack. If the mage starts
     * with 50 mana, then it can only do up to 50 damage.
     *  WhiteMage and BlackMage falls to zero health if they have zero mana
     * @param opponent opponent
     * @param terrain terrain
     */
    @Override
    public void attack(Unit opponent, Terrain terrain){
        int unitAttack = this.getAttack() + this.getAttackBonus(terrain);
        if (unitAttack > (this.getArmor() + this.getResistBonus(terrain))){
            int manaAttack;
            if(unitAttack < this.getMana()){
                this.setMana(this.getMana() - unitAttack);
                manaAttack = unitAttack;
            }else if(this.getMana() == 0){
                manaAttack = 1;
            } else{
                manaAttack = getMana();
                this.setHealth(0);
            }
            int opponentHealth = opponent.getHealth() - manaAttack + (opponent.getArmor()+ opponent.getResistBonus(terrain));
            opponent.setHealth(opponentHealth);
        }
    }

    /**
     * the abstract method from Unit has now a body
     * Provides identification the unit
     * @return ID
     */
    @Override
    public String getID() {
        return "BlackMage";
    }
}
