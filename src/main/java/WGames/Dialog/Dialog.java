package WGames.Dialog;

import WGames.WApplication;
import javafx.scene.control.Alert;


public class Dialog {

    /**
     * Displays an error message
     * @param exception exceptionwarning
     */
    public static void warning(Exception exception){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("");
        alert.setContentText(exception.getMessage());
        alert.initOwner(WApplication.primaryStage);

        alert.showAndWait();
    }

    /**
     * Displays an information message
     * @param string information
     */
    public static void information(String string){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText("");
        alert.setContentText(string);
        alert.initOwner(WApplication.primaryStage);

        alert.showAndWait();
    }

    /**
     * Displays an information message about the table and row the user
     * has pressed
     * @param object object
     */
    public static void tableInformation(Object object){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("TABLE INFORMATION");
        alert.setHeaderText("Information about the unit");
        alert.setContentText(String.valueOf(object));
        alert.initOwner(WApplication.primaryStage);

        alert.showAndWait();
    }

}
