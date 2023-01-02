package WGames.Controller;

import WGames.Dialog.Dialog;
import WGames.Model.Classes.Army;
import WGames.Model.Classes.Filewriter;
import WGames.Model.Classes.UnitFactory;
import WGames.Model.Units.Unit;
import WGames.WApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class NewArmyController implements Initializable {

    /**
     * Buttons
     */
    @FXML
    private Button addUnitButton;
    @FXML
    private Button back;
    @FXML
    private Button removeUnitButton;
    @FXML
    private Button standardUnitBox;

    /**
     * ComboBox
     */
    @FXML
    private ComboBox<String> unitType;

    /**
     * Text
     */
    @FXML
    private Text standardUnitText;

    /**
     * TextFields
     */
    @FXML
    private TextField amount;
    @FXML
    private TextField armyName;
    @FXML
    private TextField name;
    @FXML
    private TextField health;

    /**
     * Returns to the frontpage
     * @throws IOException IOException
     */
    @FXML
    void backButtonClicked() throws IOException {
        WApplication.changeScene("/Views/FrontPage.fxml");
    }

    /**
     * Method when a certain button is clicked. It checks if all the required fields contains correct information
     * to make a unit.
     * @throws IllegalArgumentException illegal argument exception
     */
    @FXML
    void addUnitButtonClicked() throws IllegalArgumentException{
        try {

            if (name.getText().trim().equals("") && health.getText().trim().equals("") && unitType.getValue() == null && armyName.getText().trim().equals("")){
                throw new IllegalArgumentException("Please fill in the boxes");
            } else if(armyName.getText().trim().equals("")){
                throw new IllegalArgumentException("The army must have a name");
            } else if(armyName.getText().trim().length() > 25 || !armyName.getText().replaceAll("[^a-zA-Z0-9]","").equals(armyName.getText().trim())){
                throw new IllegalArgumentException("The army name can only have 25 characters without special characters");
            } else if(unitType.getValue() == null){
                throw new IllegalArgumentException("Please choose the unit type from the box above");
            } else if(name.getText().trim().equals("")){
                throw new IllegalArgumentException("The unit must have a name");
            }  else if(name.getText().trim().length() > 25 || !name.getText().replaceAll("[^a-zA-Z0-9]","").equals(name.getText().trim())){
                throw new IllegalArgumentException("The unit name can only have 25 characters without special characters");
            } else if(!health.getText().replaceAll("[^0-9]", "").equals(health.getText().trim()) || Objects.equals(health.getText().trim(), "")){
                throw new IllegalArgumentException("The health must be an integer");
            } else if(!amount.getText().replaceAll("[^0-9]", "").equals(amount.getText().trim()) || Objects.equals(amount.getText().trim(), "")){
                throw new IllegalArgumentException("The amount must be an integer between 1 and 100");
            } else if(Integer.parseInt(amount.getText().trim()) < 1 || Integer.parseInt(amount.getText().trim()) > 100){
                throw new IllegalArgumentException("The amount must be between 1 and 100");
            } else{
                UnitFactory unitFactory = new UnitFactory();
                Filewriter filewriter = new Filewriter();

                List<Unit> units = new ArrayList<>();
                Army army = new Army(armyName.getText().trim(), units);

                String unitName = name.getText().trim();
                int unitHealth = Integer.parseInt(health.getText().trim());
                String typeOfUnit = String.valueOf(unitType.getValue());
                int amountOfUnits = Integer.parseInt(amount.getText().trim());

                List<Unit> unit = unitFactory.makeUnits(typeOfUnit, unitName, unitHealth, amountOfUnits);
                standardUnitText.setText(unit.get(0).toString() + " * " + amountOfUnits);

                if (!(new File("src\\main\\resources\\Files\\" + armyName.getText().trim() + ".csv")).exists()) {
                    army.addAll(unit);
                    filewriter.writeArmyInFile(army);
                } else {
                    for(Unit u:unit){
                        filewriter.writeData(armyName.getText().trim(), u);
                    }
                }
            }
        }catch (IllegalArgumentException exception){
                Dialog.warning(exception);
        }
    }

    /**
     * Method when a certain button is clicked. It checks if all the required fields contains correct information
     * to make a standard unit. The health is random number between 1 and 50 and the name is the same as the
     * unit type.
     * @throws IllegalArgumentException illegal argument exception
     */
    @FXML
    public void makeStandardUnit() throws IllegalArgumentException{
        try{

            if (unitType.getValue() == null && armyName.getText().trim().equals("")){
                throw new IllegalArgumentException("Please fill in the required fields ---> name of army and type of unit");
            } else if(armyName.getText().trim().equals("")){
                throw new IllegalArgumentException("The army must have a name");
            } else if(armyName.getText().trim().length() > 25 || !armyName.getText().replaceAll("[^a-zA-Z0-9]","").equals(armyName.getText().trim())){
                throw new IllegalArgumentException("The army name can only have 25 characters without special characters");
            }else if(unitType.getValue() == null){
                throw new IllegalArgumentException("Please choose the unit type from the box above");
            } else if(!amount.getText().replaceAll("[^0-9]", "").equals(amount.getText().trim()) || Objects.equals(amount.getText().trim(), "")){
                throw new IllegalArgumentException("The amount must be an integer between 1 and 100");
            } else if(Integer.parseInt(amount.getText().trim()) < 1 || Integer.parseInt(amount.getText().trim()) > 100){
                throw new IllegalArgumentException("The amount must be between 1 and 100");
            } else{
                UnitFactory unitFactory = new UnitFactory();
                Filewriter filewriter = new Filewriter();

                List<Unit> units = new ArrayList<>();
                Army army = new Army(armyName.getText().trim(), units);

                String typeOfUnit = String.valueOf(unitType.getValue());
                int unitHealth = 13;
                int amountOfUnits = Integer.parseInt(amount.getText().trim());

                List<Unit> unit = unitFactory.makeUnits(typeOfUnit, typeOfUnit, unitHealth, amountOfUnits);
                standardUnitText.setText(unit.get(0).toString() + " * " + amountOfUnits);

                if (!(new File("src\\main\\resources\\Files\\" + armyName.getText().trim() + ".csv")).exists() && unit!=null) {
                    army.addAll(unit);
                    filewriter.writeArmyInFile(army);
                } else {
                    for(Unit u:unit){
                        filewriter.writeData(armyName.getText().trim(), u);
                    }
                }
            }

        }catch (IllegalArgumentException exception){
            Dialog.warning(exception);
        }
    }

    /**
     * Initial method
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> u = FXCollections.observableArrayList("CavalryUnit", "CommanderUnit", "InfantryUnit", "RangedUnit", "BlackMage", "WhiteMage");
        unitType.setItems(u);
    }
}


