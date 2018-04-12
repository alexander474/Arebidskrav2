package GUI;

import Quiz.CountryHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        MenuScene.MenuWindow(primaryStage); //starting the application by displaying the MenuScene
    }


    public static void main(String[] args) {
        launch(args);
    }
}
