package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MenuScene {

    static String radioButtonChoice;
    final static ComboBox chooseCategory = new ComboBox();
    final static ToggleGroup chooseGameTypeTogle = new ToggleGroup();

    public static void MenuWindow(Stage mainStage){

        radioButtonChoice = "";
        BorderPane borderPane = new BorderPane();
        GridPane gridPaneCenter = new GridPane();
        VBox vBoxMenu = new VBox();
        HBox hboxChooseContinentAndStart = new HBox();
        HBox hBoxChooseGameType = new HBox();
        GridPane gridPaneBottom = new GridPane();

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
        borderPane.setBottom(gridPaneBottom);

        /**
         * gridPaneCenter
         * */
        gridPaneCenter.setAlignment(Pos.CENTER);
        gridPaneCenter.setVgap(10);
        gridPaneCenter.setHgap(10);

        gridPaneCenter.add(vBoxMenu,0,0,1,1);

        /**
         * gridPaneBottom
         * */
        Label madeByLabel = new Label("Made By: Alexander Bredesen /2018");
        madeByLabel.setFont(new Font(15));
        gridPaneBottom.setAlignment(Pos.BOTTOM_RIGHT);
        gridPaneBottom.setVgap(10);
        gridPaneBottom.setHgap(10);
        gridPaneBottom.setPadding(new Insets(10,20,10,10));

        gridPaneBottom.add(madeByLabel,0,0,1,1);


        /**
         * vBoxMenu
         * */

        vBoxMenu.getChildren().addAll(hboxChooseContinentAndStart,hBoxChooseGameType);

        //choose continent
        chooseCategory.getItems().addAll(
                "All Countries",
                "Europe",
                "Asia",
                "North America",
                "South America",
                "Africa",
                "Oceania"
        );
        chooseCategory.setPrefSize(250,60);
        chooseCategory.setStyle("-fx-font-size: 20");
        chooseCategory.setPromptText("Choose Category");

        //startButton
        Button startBtn = new Button("Start");
        startBtn.setFont(new Font(20));
        startBtn.setPrefSize(100,60);


        //radioButtonListener
        chooseGameTypeTogle.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if(chooseGameTypeTogle.getSelectedToggle() != null){
                    RadioButton radioButton = (RadioButton) chooseGameTypeTogle.getSelectedToggle();
                    radioButtonChoice = radioButton.getText();
                }
            }
        });
        //RadioButtons
        RadioButton guessCapital = new RadioButton("CapitalGame");
        RadioButton guessFlag = new RadioButton("FlagGame");
        guessCapital.setToggleGroup(chooseGameTypeTogle);
        guessFlag.setToggleGroup(chooseGameTypeTogle);


        //HBOX CONTINENT
        hboxChooseContinentAndStart.getChildren().addAll(chooseCategory,startBtn);
        hboxChooseContinentAndStart.setPadding(new Insets(10));
        hboxChooseContinentAndStart.setSpacing(10);

        //HBOX GAMETYPE
        hBoxChooseGameType.getChildren().addAll(guessCapital, guessFlag);
        hBoxChooseGameType.setPadding(new Insets(10));
        hBoxChooseGameType.setSpacing(25);

        startBtn.setOnAction(e->{
            System.out.println("[START] is pressed\n");
            chooceContinent(mainStage);
        });

        mainStage.show();
    }

    public static void chooceContinent(Stage mainStage){
        if(chooseCategory.getValue() != null) {
            String currentCategoryChoice = chooseCategory.getValue().toString();
            System.out.println("Category ["+currentCategoryChoice+"]");
            System.out.println("GameType ["+radioButtonChoice+"]\n");
            GameScene.GameWindow(mainStage, currentCategoryChoice, radioButtonChoice);
            }
        else{
            System.out.println("[error] couldn't choose a category or gametype");
        }
    }
}
