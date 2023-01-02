package WGames.Controller;

import WGames.Dialog.Dialog;
import WGames.Model.Classes.Army;
import WGames.Model.Classes.Battle;
import WGames.Model.Classes.Filewriter;
import WGames.Model.Classes.Terrain;
import WGames.WApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class BattleController implements Initializable {

    private Army armyOne;
    private Army armyTwo;
    private Battle battle;
    private Filewriter filewriter = new Filewriter();
    private  ImageView imageView;
    private Terrain terrain;
    private int speed = 1000;

    /**
     * Buttons
     */
    @FXML
    private Button back;
    @FXML
    private Button confirmTerrainButton;
    @FXML
    private Button fight;
    @FXML
    private Button fileUploadButton;
    @FXML
    private Button restartButton;

    @FXML
    private Button normalSpeedButton;
    @FXML
    private Button oneQuarterSpeedButton;
    @FXML
    private Button quadrupleSpeedButton;
    @FXML
    private Button quadrupleQuadrupleSpeedButton;

    /**
     * ComboBox
     */
    @FXML
    private ComboBox terrainType;

    /**
     * TableColumns
     */
    @FXML
    private TableColumn healthArmyOne;
    @FXML
    private TableColumn healthArmyTwo;
    @FXML
    private TableColumn nameArmyOne;
    @FXML
    private TableColumn nameArmyTwo;
    @FXML
    private TableColumn unitTypeArmyOne;
    @FXML
    private TableColumn unitTypeArmyTwo;

    /**
     * TableViews
     */
    @FXML
    private TableView armyOneTable;
    @FXML
    private TableView armyTwoTable;

    /**
     * Texts
     */
    @FXML
    private Text armyOneName;
    @FXML
    private Text armyTwoName;
    @FXML
    private Text battleAction;
    @FXML
    private Text terrainSelect;
    @FXML
    private Text terrainText;

    /**
     * Pane to hold an image of the selected terrain
     */
    @FXML
    private Pane terrainImage;

    /**
     * Changes the speed of the battle when a certain button in clicked
     */
    @FXML
    public void normalSpeed(){
        speed = 1000;
    }
    @FXML
    public void oneQuarterSpeed(){
        speed = 4000;
    }
    @FXML
    public void quadrupleSpeed(){speed = 250;}
    @FXML
    public void quadrupleQuadrupleSpeed(){
        speed = 62;
    }

    /**
     * Returns to the frontpage
     * @throws IOException IOException
     */
    @FXML
    void backButtonClicked() throws IOException {
        WApplication.changeScene("/Views/FrontPage.fxml");
    }

    /**
     *  When a certain button is clicked this method sets/changes the terrain image. If a terrain
     *  is not chosen it sets the DESERT as the default. It uses the setTerrainImage method
     */
    @FXML
    public void confirmTerrain(){
        if(terrainType.getValue()==null){
            terrain = Terrain.DESERT;
        } else{
            terrain = (Terrain) terrainType.getValue();
        }
        if(armyOne != null && armyTwo != null){
            battle = new Battle(armyOne,armyTwo, terrain);
        }
        setTerrainImage(terrain);
        terrainText.setText(String.valueOf(terrain));
    }

    /**
     * This method sets/changes the terrain image.
     * @param terrain terrain
     */
    public void setTerrainImage(Terrain terrain){
        terrainImage.getChildren().remove(imageView);
        imageView = new ImageView(new Image(BattleController.class.getResourceAsStream("/Pictures/" + terrain + ".png")));
        if(terrain.equals(Terrain.FOREST)){//terrain.equals(Terrain.FOREST) || terrain.equals(Terrain.PLAINS)
            imageView.fitWidthProperty().bind(terrainImage.widthProperty());
            imageView.fitHeightProperty().bind(terrainImage.heightProperty());
        }
        terrainImage.getChildren().add(imageView);
    }

    /**
     * When a certain button is clicked this method opens the file explorer. There you choose a csv-file
     * containing an army.Then it tells you to choose another army. The armies must have a name and contain
     * at least one unit. These armies are added to the tables.
     */
    @FXML
    public void fileUpload() {
        try{

            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV-files","*.csv", "*.CSV", "*.Csv"));
            fileChooser.setInitialDirectory(new File("src\\main\\resources\\Files"));

            armyOne = filewriter.makeArmyFromFile(fileChooser.showOpenDialog(WApplication.primaryStage));
            if(armyOne.getName() == null || armyOne.getName().equals("")){
                throw new IllegalCallerException("An army without a name is not legal");
            }else if(armyOne.getAllUnits().size() == 0) {
                throw new IllegalCallerException("Empty army");
            }

            Dialog.information("Now choose another army to battle " + armyOne.getName());

            armyTwo = filewriter.makeArmyFromFile(fileChooser.showOpenDialog(WApplication.primaryStage));
            if(armyTwo.getName() == null || armyTwo.getName().equals("")){
                throw new IllegalCallerException("An army without a name is not legal");
            }else if(armyTwo.getAllUnits().size() == 0) {
                throw new IllegalCallerException("Empty army");
            }

            if(terrainType.getValue()==null){
                terrain = Terrain.DESERT;
            } else{
                terrain = (Terrain) terrainType.getValue();
            }

            setTerrainImage(terrain);
            battle = new Battle(armyOne,armyTwo, terrain);
            terrainText.setText(String.valueOf(terrain));
            armyOneName.setText(armyOne.getName());
            armyTwoName.setText(armyTwo.getName());

            updateTables();

        } catch (Exception exception){
            Dialog.warning(exception);
        }

    }

    /**
     * You can se information about a certain unit in the armyOneTable
     * @param click armyOneTable
     */
    @FXML
    private void handleClickTableViewOne(MouseEvent click) {
        if(armyOne!= null){
            Object o = armyOneTable.getSelectionModel().getSelectedItem();
            Dialog.tableInformation(o);
        }
    }

    /**
     * You can se information about a certain unit in the armyTwoTable
     * @param click armyTwoTable
     */
    @FXML
    private void handleClickTableViewTwo(MouseEvent click) {
        if(armyTwo!= null){
            Object o = armyTwoTable.getSelectionModel().getSelectedItem();
            Dialog.tableInformation(o);
        }
    }

    /**
     * Method which sets and refreshes the tables with the armies
     */
    @FXML
    public void updateTables(){
        if(armyOne != null){

            unitTypeArmyOne.setCellValueFactory(new PropertyValueFactory<>("ID"));
            nameArmyOne.setCellValueFactory(new PropertyValueFactory<>("name"));
            healthArmyOne.setCellValueFactory(new PropertyValueFactory<>("health"));

            armyOneTable.setItems(FXCollections.observableArrayList(armyOne.getAllUnits()));

        }
        if(armyTwo != null){

            unitTypeArmyTwo.setCellValueFactory(new PropertyValueFactory<>("ID"));
            nameArmyTwo.setCellValueFactory(new PropertyValueFactory<>("name"));
            healthArmyTwo.setCellValueFactory(new PropertyValueFactory<>("health"));

            armyTwoTable.setItems(FXCollections.observableArrayList(armyTwo.getAllUnits()));

        }

        armyOneTable.refresh();
        armyTwoTable.refresh();
    }

    /**
     * When a certain button is clicked this method clears the tables and fills them with the same armies.
     * It also sets the speed to normal. During a battle you cannot click this button.
     */
    @FXML
    public void restart(){
        armyOneTable.getItems().clear();
        armyTwoTable.getItems().clear();

        armyOne = filewriter.makeArmyFromFile(new File("src\\main\\resources\\Files\\" + armyOne.getName() + ".csv"));
        armyTwo = filewriter.makeArmyFromFile(new File("src\\main\\resources\\Files\\" + armyTwo.getName() + ".csv"));

        speed = 1000;

        battle = new Battle(armyOne,armyTwo, terrain);

        updateTables();
    }

    /**
     * When a certain button is clicked this method will commence the battle. It constantly changes the
     * battleAction text so that the user can se how the battle is played
     * @throws InterruptedException Interrupted exception
     * @throws FileNotFoundException File not found exception
     */
    @FXML
    public void theBattle() throws InterruptedException, FileNotFoundException {

        try {
            if(battle == null || armyOne == null || armyTwo == null){
                throw new IllegalArgumentException("You must choose armies before the war can start");
            } else if(armyOne.getAllUnits().size() == 0 || armyTwo.getAllUnits().size() == 0 ){
                throw new IllegalArgumentException("You cannot have a battle with an empty army");
            }

            restartButton.setDisable(true);
            fileUploadButton.setDisable(true);

            //if(armyOne.getAllUnits().size() != 0 && armyTwo.getAllUnits().size() != 0 && armyOne.getRandom() != null && armyTwo.getRandom() != null){

                new Thread(()->{

                    while(armyOne.getAllUnits().size() != 0 && armyTwo.getAllUnits().size() != 0 && armyOne.getRandom() != null && armyTwo.getRandom() != null){
                        if(battle.slowSimulate().equals("stalemate")){
                            battleAction.setText(battle.slowSimulate());
                            break;
                        }
                        battleAction.setText(battle.slowSimulate());
                        updateTables();
                        try {
                            Thread.sleep(speed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Dialog.warning(e);
                        }
                    }

                    if (armyOne.getAllUnits().size() == 0 && armyTwo.getAllUnits().size() != 0){
                        battleAction.setText(armyTwo.getName() + " won with " + armyTwo.getAllUnits().size() + " units left");
                    } else if(armyTwo.getAllUnits().size() == 0 && armyOne.getAllUnits().size() != 0){
                        battleAction.setText(armyOne.getName() + " won with " + armyOne.getAllUnits().size() + " units left");
                    } else if (armyOne.getAllUnits().size() == 1 && armyTwo.getAllUnits().size() == 1 && !battleAction.getText().equals("stalemate")){
                        battleAction.setText("restarted");
                    }

                    restartButton.setDisable(false);
                    fileUploadButton.setDisable(false);

                }).start();

        } catch (Exception exception){
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
        ObservableList<Terrain> u = FXCollections.observableArrayList(Terrain.values());
        terrainType.setItems(u);
        restartButton.setDisable(true);
    }
}
