module Wargames {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;
    requires java.logging;
    requires com.opencsv;
    requires org.apache.commons.lang3;

    opens WGames to javafx.fxml;
    exports WGames;

    opens WGames.Controller to javafx.fxml;
    exports WGames.Controller;

    opens TestResources to test;
    exports TestResources;

    opens WGames.Dialog to javafx.fxml;
    exports WGames.Dialog;


    opens WGames.Model.Units to javafx.fxml;
    exports WGames.Model.Units;

    opens WGames.Model.Units.Mages to javafx.fxml;
    exports WGames.Model.Units.Mages;

    opens WGames.Model.Classes to javafx.fxml;
    exports WGames.Model.Classes;
    exports WGames.Model.Units.Standard;
    opens WGames.Model.Units.Standard to javafx.fxml;


}