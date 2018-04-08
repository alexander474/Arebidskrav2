package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MenuScene {

    public static void MenuWindow(Stage mainStage){

        BorderPane borderPane = new BorderPane();
        GridPane gridPaneCenter = new GridPane();
        VBox vBoxMenuButtons = new VBox();

        //stageoptions
        mainStage.setTitle("Quiz");
        Scene menuScene = new Scene(borderPane, 1000,1000);
        mainStage.setScene(menuScene);

        //css
        menuScene.getStylesheets().add("GUI/Main.css");

        /**
         * BorderPane
         * */
        borderPane.setCenter(gridPaneCenter);

        /**
         * gridPaneCenter
         * */
        gridPaneCenter.setAlignment(Pos.CENTER);
        gridPaneCenter.setVgap(10);
        gridPaneCenter.setHgap(10);

        gridPaneCenter.add(vBoxMenuButtons,0,0,1,1);

        /**
         * vBoxMenuButtons
         * */
        Button AllCountries = new Button("All Countries");
        AllCountries.setFont(new Font(20));

        vBoxMenuButtons.getChildren().add(AllCountries);

        AllCountries.setOnAction(e->{
            GameScene.GameWindow(mainStage);

        });





        mainStage.show();
    }
}
