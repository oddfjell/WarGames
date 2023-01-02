package WGames.Controller;

import WGames.Dialog.Dialog;
import WGames.Model.Classes.Army;
import WGames.Model.Classes.Filewriter;
import WGames.WApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class SeeArmiesController{

    private Army army;

    /**
     * Buttons
     */
    @FXML
    private Button back;
    @FXML
    private Button fileUploadButton;

    /**
     * TableColumns
     */
    @FXML
    private TableColumn theArmyTableHealth;
    @FXML
    private TableColumn theArmyTableName;
    @FXML
    private TableColumn theArmyTableType;

    /**
     * TableView
     */
    @FXML
    private TableView theArmyTable;

    /**
     * Text
     */
    @FXML
    private Text nameOfTheArmies;

    /**
     * Returns to the frontpage
     * @throws IOException IOException
     */
    @FXML
    void backButtonClicked() throws IOException {
        WApplication.changeScene("/Views/FrontPage.fxml");
    }

    /**
     *  When a certain button is clicked this method opens the file explorer. There you choose a csv-file
     *  containing an army. Then it fills the TableView with the troops
     */
    @FXML
    public void fileUpload() {
        try{
            Filewriter filewriter = new Filewriter();
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV-files","*.csv", "*.CSV", "*.Csv"));
            fileChooser.setInitialDirectory(new File("src\\main\\resources\\Files"));

            army = filewriter.makeArmyFromFile(fileChooser.showOpenDialog(WApplication.primaryStage));
            nameOfTheArmies.setText(army.getName());

            if(army != null){
                theArmyTableType.setCellValueFactory(new PropertyValueFactory<>("ID"));
                theArmyTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
                theArmyTableHealth.setCellValueFactory(new PropertyValueFactory<>("health"));

                theArmyTable.setItems(FXCollections.observableArrayList(army.getAllUnits()));
            }
        } catch (Exception exception){
            Dialog.warning(exception);
        }
    }
}
