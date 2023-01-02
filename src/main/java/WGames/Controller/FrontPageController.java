package WGames.Controller;

import WGames.WApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class FrontPageController{

    /**
     * Buttons
     */
    @FXML
    private Button addArmyFromFileButton;
    @FXML
    private Button commenceBattleButton;
    @FXML
    private Button exit;
    @FXML
    private Button newArmyButton;
    @FXML
    private Button seeArmiesButton;

    /**
     * Sends the user to the AddArmiesFromFile page
     * @throws IOException IOException
     */
    @FXML
    void addArmyFromFileButtonClicked() throws IOException {
        WApplication.changeScene("/Views/AddArmiesFromFile.fxml");
    }

    /**
     * Sends the user to the Battle page
     * @throws IOException IOException
     */
    @FXML
    void commenceBattleButtonClicked() throws IOException {
        WApplication.changeScene("/Views/Battle.fxml");
    }

    /**
     * Exits the game
     * @throws IOException IOException
     */
    @FXML
    void exitTheGame(){
        System.exit(0);
    }

    /**
     * Sends the user to the NewArmy page
     * @throws IOException IOException
     */
    @FXML
    void newArmyButtonClicked() throws IOException{
        WApplication.changeScene("/Views/NewArmy.fxml");
    }

    /**
     * Sends the user to the SeeArmies page
     * @throws IOException IOException
     */
    @FXML
    void seeArmiesButtonClicked() throws IOException {
        WApplication.changeScene("/Views/SeeArmies.fxml");
    }
}
