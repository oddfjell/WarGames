package WGames.Model.Classes;

import WGames.Model.Units.Unit;

import java.util.Random;

public class Battle {
    private Army armyOne;
    private Army armyTwo;
    private Terrain terrain;

    /**
     * constructor of the battle class
     * @param armyOne armyOne
     * @param armyTwo armyTwo
     * @param terrain terrain
     * @throws IllegalStateException illegal state exception
     */
    public Battle(Army armyOne, Army armyTwo, Terrain terrain) throws IllegalStateException{
        if(!armyOne.getName().trim().equals(armyTwo.getName().trim())){
            this.armyOne = armyOne;
            this.armyTwo = armyTwo;
            this.terrain = terrain;
        } else {
            throw new IllegalStateException("You cannot make an army attack itself");
        }
    }

    /**
     * method to get a random army to attack a random hostile unit with their own random unit
     * the simulation loops the random process until an army does not have any more units
     * than it returns the victor team
     * @return the victorious army
     */
    public Army simulate(){
        while(armyOne.getAllUnits().size() != 0 && armyTwo.getAllUnits().size() != 0){

            if(armyOne.getAllUnits().size() == 1 && armyTwo.getAllUnits().size() == 1 && armyOne.getAllUnits().get(0).getID().equals("WhiteMage") && armyTwo.getAllUnits().get(0).getID().equals("WhiteMage")){
                return null;
            } else{
                Random random = new Random();

                Unit unitFromArmyOne = armyOne.getRandom();
                Unit unitFromArmyTwo = armyTwo.getRandom();

                int whoIsFirst = random.nextInt(2);

                switch (whoIsFirst) {
                    case 0 -> {
                        unitFromArmyOne.attack(unitFromArmyTwo, terrain);
                        if (unitFromArmyTwo.getHealth() <= 0) {
                            armyTwo.remove(unitFromArmyTwo);
                        }
                    }
                    case 1 -> {
                        unitFromArmyTwo.attack(unitFromArmyOne, terrain);
                        if (unitFromArmyOne.getHealth() <= 0) {
                            armyOne.remove(unitFromArmyOne);
                        }
                    }
                }
            }
        }

        if (armyOne.getAllUnits().size() == 0){
            return armyTwo;
        } else{
            return armyOne;
        }
    }

    /**
     * Method to get a random army to attack a random hostile unit with their own random unit. It returns
     * different strings if the unit is attacked, healed or killed.
     * If the attacker is a WhiteMage it heals a unit from their army.
     * @return String of unit vs unit event
     * @throws IllegalStateException illegal state exception
     */
    public String slowSimulate() throws IllegalStateException{

        Random random = new Random();
        Unit unitFromArmyOne = armyOne.getRandom();
        Unit unitFromArmyTwo = armyTwo.getRandom();

        /**
         * Extra removal since the BattleController did not always remove the unit when it was null
         */
        if (unitFromArmyOne == null){
            armyOne.remove(unitFromArmyOne);
            return "";
        }else if (unitFromArmyTwo == null){
            armyTwo.remove(unitFromArmyTwo);
            return "";
        } else if(armyOne.getAllUnits().size() == 1 && armyTwo.getAllUnits().size() == 1 && unitFromArmyOne.getID().equals("WhiteMage") && unitFromArmyTwo.getID().equals("WhiteMage")){
            return "stalemate";
        } else{
            int whoIsFirst = random.nextInt(2);

            Unit attacker;
            Unit victim;
            Army attackerArmy;
            Army victimArmy;

            switch (whoIsFirst){
                case 0 -> {
                    attacker = unitFromArmyOne;
                    victim = unitFromArmyTwo;
                    attackerArmy = armyOne;
                    victimArmy = armyTwo;
                }
                case 1 -> {
                    attacker = unitFromArmyTwo;
                    victim = unitFromArmyOne;
                    attackerArmy = armyTwo;
                    victimArmy = armyOne;
                }
                default -> throw new IllegalStateException("Unexpected value: " + whoIsFirst);
            }

            if(attacker.getID().equals("WhiteMage")){
                Unit patientFromSameArmy = attackerArmy.getRandom();
                attacker.attack(patientFromSameArmy);
                if(attacker.getHealth()==0){
                    attackerArmy.remove(attacker);
                    return attacker.getName() + " heals " + patientFromSameArmy.getName() + " and dies";
                }
                return attacker.getName() + " heals " + patientFromSameArmy.getName() + " for " + (attacker.getAttack() + attacker.getAttackBonus(terrain)) + " hp";
            } else{
                attacker.attack(victim, terrain);

                if(victim.getHealth() <= 0 && attacker.getHealth() <= 0){
                    victimArmy.remove(victim);
                    attackerArmy.remove(attacker);
                    return attacker.getName() + " kills " + victim.getName() + " (" + victimArmy.getName() + ") and dies";
                } else if(victim.getHealth() <= 0 && attacker.getHealth() > 0){
                    victimArmy.remove(victim);
                    return attacker.getName() + " kills " + victim.getName() + " (" + victimArmy.getName() + ")";
                } else if(victim.getHealth() > 0 && attacker.getHealth() <= 0){
                    attackerArmy.remove(attacker);
                    return attacker.getName() + " attacks " + victim.getName() + " and dies";
                } else{
                    return attacker.getName() + " attacks " + victim.getName();
                }
            }
        }
    }

    /**
     * toString method
     * @return String
     */
    @Override
    public String toString(){
        return armyOne.toString() + " versus " + armyTwo.toString();
    }
}
