package WGames.Controller;

import WGames.Dialog.Dialog;
import WGames.Model.Classes.Army;
import WGames.Model.Classes.Filewriter;
import WGames.WApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AddArmyFromFileController implements Initializable {

    /**
     * Buttons
     */
    @FXML
    private Button back;
    @FXML
    private Button fileUploadButton;

    /**
     * Text
     */
    @FXML
    private Text unitAdded;

    /**
     * Returns to the frontpage
     * @throws IOException IOException
     */
    @FXML
    void backButtonClicked() throws IOException {
        WApplication.changeScene("/Views/FrontPage.fxml");
    }

    /**
     * When a certain button is clicked this method opens the file explorer. There you choose a csv-file
     * containing an army. If the army is not empty, it will be added to resources
     */
    @FXML
    public void fileUpload() {
        try{
            unitAdded.setVisible(false);
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV-files","*.csv", "*.CSV", "*.Csv"));
            Filewriter filewriter = new Filewriter();
            Army army = filewriter.makeArmyFromFile(fileChooser.showOpenDialog(WApplication.primaryStage));
            if (army != null) {
                filewriter.writeArmyInFile(army);
                unitAdded.setVisible(true);
            }

        }catch (Exception exception){
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
        unitAdded.setVisible(false);
    }
}
