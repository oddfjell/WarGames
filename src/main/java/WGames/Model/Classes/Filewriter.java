package WGames.Model.Classes;

import WGames.Dialog.Dialog;
import WGames.Model.Units.Unit;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Filewriter {

    /**
     * Method to write an army to a file. The method takes the army as its parameter.
     * The BufferedWriter class will write the information in a path with the name of the army.
     * It will write the army name on the first line and the sorted unitsList the other lines.
     * @param army army
     * @throws IllegalArgumentException illegal argument exception
     */
    public void writeArmyInFile(Army army) throws IllegalArgumentException{
        if(army.getAllUnits().size() > 0){
            try{

                BufferedWriter bw = new BufferedWriter(
                        new FileWriter("src\\main\\resources\\Files\\" + army.getName() + ".csv"));

                List<Unit> unitsList = army.getAllUnits().stream()
                        .sorted(Comparator.comparing(Unit::getID).thenComparing(Unit::getName).thenComparing(Unit::getHealth))
                        .collect(Collectors.toList());

                bw.write(army.getName() + "\n");

                for (Unit unit : unitsList) {
                    bw.write(unit.getID() + "," + unit.getName() + "," + unit.getHealth() + "\n");
                }

                bw.close();

            }catch (Exception ex){
                Dialog.warning(ex);
            }
        } else{
            throw new IllegalArgumentException("The army cannot be empty");
        }
    }

    /**
     * Method to make an army from an already existing written army. It uses BufferedReader and checks
     * all the sentences except the first (the army name) to put them into a new army. For each line
     * it will check which unit type to make a new unit of that type.
     * It also checks if the path name ends on csv
     * @param file file
     * @return army
     * @throws IllegalArgumentException illegal argument exception
     * @throws ExceptionInInitializerError ExceptionInInitializerError
     */
    public Army makeArmyFromFile(File file) throws IllegalArgumentException, ExceptionInInitializerError{

        if(file==null){
            throw new IllegalArgumentException("You did not choose a file");
        }
        else{
            String[] path = file.getPath().toString().split("\\.");
            if(path[path.length -1].toLowerCase().equals("csv")){

                List<Unit> u = new ArrayList<>();
                Army army;

                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));

                    String armyName = br.readLine();

                    List<String> theUnits = br.lines().skip(0).collect(Collectors.toList());

                    army = new Army(armyName, u);

                    for (String theUnit : theUnits) {
                        if(!Objects.equals(theUnit, "")){
                            String[] unitStats = theUnit.trim().split(",");
                            if(unitStats.length == 3){
                                if(!Objects.equals(unitStats[0], "")&& unitStats[2].matches("[0-9]+") && !Objects.equals(unitStats[1], "") && !Objects.equals(unitStats[2], "")){
                                    UnitFactory unitFactory = new UnitFactory();
                                    u.add(unitFactory.getUnit(unitStats[0], unitStats[1], Integer.parseInt(unitStats[2])));
                                }
                            }else{
                                throw new IllegalArgumentException("Illegal format");
                            }
                        } else{
                            throw new IllegalArgumentException("Illegal format");
                        }
                    }

                    br.close();

                }catch(Exception ex){
                    Dialog.warning(ex);
                    return new Army("");
                }
                return army;

            } else{
                throw new IllegalArgumentException("This is not a csv-file");
            }
        }


    }

    /**
     * Method to add a new line to an already existing file or make a new file
     * @param armyName armyName
     * @param unit unit
     * @throws IllegalArgumentException illegal argument exception
     */
    public void writeData(String armyName, Unit unit) throws IllegalArgumentException{

        PrintWriter csvWriter;
        try
        {
            File file = new File("src\\main\\resources\\Files\\" + armyName + ".csv");

            csvWriter = new  PrintWriter(new FileWriter(file,true));

            String unitInformation = unit.getID() + "," + unit.getName() + "," + unit.getHealth();

            csvWriter.println(unitInformation);

            csvWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Dialog.warning(e);
        }
    }

}