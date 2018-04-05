package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //scene.getStylesheets().add("Main.css");
        Stage gameWindow = new Stage();
        GameStage.GameWindow(gameWindow);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
