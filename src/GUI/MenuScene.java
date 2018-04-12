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
    public static void MenuWindow(Stage mainStage){

        radioButtonChoice = "";
        BorderPane borderPane = new BorderPane();
        GridPane gridPaneCenter = new GridPane();
        VBox vBoxMenu = new VBox();
        HBox hboxChooseContinentAndStart = new HBox();
        HBox hBoxChooseGameType = new HBox();

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

        gridPaneCenter.add(vBoxMenu,0,0,1,1);

        /**
         * vBoxMenu
         * */

        vBoxMenu.getChildren().addAll(hboxChooseContinentAndStart,hBoxChooseGameType);

        //choose continent
        final ComboBox chooseContinent = new ComboBox();
        chooseContinent.getItems().addAll(
                "All Countries",
                "Europe",
                "Asia",
                "North America",
                "South America",
                "Africa",
                "Oceania"
        );
        chooseContinent.setPrefSize(250,60);
        chooseContinent.setStyle("-fx-font-size: 20");
        chooseContinent.setPromptText("Choose Category");

        //startButton
        Button startBtn = new Button("Start");
        startBtn.setFont(new Font(20));
        startBtn.setPrefSize(100,60);


        //radioButton
        final ToggleGroup chooseGameTypeTogle = new ToggleGroup();
        chooseGameTypeTogle.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if(chooseGameTypeTogle.getSelectedToggle() != null){
                    RadioButton radioButton = (RadioButton) chooseGameTypeTogle.getSelectedToggle();
                    System.out.println(radioButton.getText());
                    radioButtonChoice = radioButton.getText();
                }
            }
        });
        RadioButton guessCapital = new RadioButton("CapitalGame");
        RadioButton guessFlag = new RadioButton("FlagGame");
        guessCapital.setToggleGroup(chooseGameTypeTogle);
        guessFlag.setToggleGroup(chooseGameTypeTogle);


        //HBOX CONTINENT
        hboxChooseContinentAndStart.getChildren().addAll(chooseContinent,startBtn);
        hboxChooseContinentAndStart.setPadding(new Insets(10));
        hboxChooseContinentAndStart.setSpacing(10);

        //HBOX GAMETYPE
        hBoxChooseGameType.getChildren().addAll(guessCapital, guessFlag);
        hBoxChooseGameType.setPadding(new Insets(10));
        hBoxChooseGameType.setSpacing(25);

        startBtn.setOnAction(e->{
            System.out.println("[START] is pressed\n");
            if(chooseContinent.getValue() != null && !chooseContinent.getValue().toString().isEmpty()) {

                String currentCategoryChoice = chooseContinent.getValue().toString();
                if(radioButtonChoice.equals("CapitalGame")) {
                    switch (currentCategoryChoice) {
                        case "All Countries":
                            CapitalGameScene.GameWindow(mainStage, "AllCountries");
                            break;
                        case "Europe":
                            CapitalGameScene.GameWindow(mainStage, "Europe");
                            break;
                        case "Asia":
                            CapitalGameScene.GameWindow(mainStage, "Asia");
                            break;
                        case "North America":
                            CapitalGameScene.GameWindow(mainStage, "North America");
                            break;
                        case "South America":
                            CapitalGameScene.GameWindow(mainStage, "South America");
                            break;
                        case "Africa":
                            CapitalGameScene.GameWindow(mainStage, "Africa");
                            break;
                        case "Oceania":
                            CapitalGameScene.GameWindow(mainStage, "Oceania");
                            break;

                    }
                }


                if(radioButtonChoice.equals("FlagGame")){
                    switch (currentCategoryChoice) {
                        case "All Countries":
                            FlagGameScene.GameWindow(mainStage, "AllCountries");
                            break;
                        case "Europe":
                            FlagGameScene.GameWindow(mainStage, "Europe");
                            break;
                        case "Asia":
                            FlagGameScene.GameWindow(mainStage, "Asia");
                            break;
                        case "North America":
                            FlagGameScene.GameWindow(mainStage, "North America");
                            break;
                        case "South America":
                            FlagGameScene.GameWindow(mainStage, "South America");
                            break;
                        case "Africa":
                            FlagGameScene.GameWindow(mainStage, "Africa");
                            break;
                        case "Oceania":
                            FlagGameScene.GameWindow(mainStage, "Oceania");
                            break;
                    }
                }
            }
            else{
                    System.out.println("[Could't start]\n");
            }

        });

        mainStage.show();
    }
}
