package WGames;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class WApplication extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        WApplication.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image(WApplication.class.getResourceAsStream("/Pictures/moogle.png")));
        FXMLLoader fxmlLoader = new FXMLLoader(WApplication.class.getResource("/Views/FrontPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());//,1200,600
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("JavaFX Scene");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Change scene.
     *
     * Change to given fxml view
     * @param fxml the fxml
     * @throws IOException the io exception
     */
    public static void changeScene(String fxml) throws IOException {
        Parent parent = FXMLLoader.load(WApplication.class.getResource(fxml));
        Stage stage = WApplication.primaryStage;
        stage.getScene().setRoot(parent);
    }
}
