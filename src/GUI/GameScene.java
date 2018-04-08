package GUI;

import Quiz.Country;
import Quiz.CountryHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;


public class GameScene {

    static String fileName = "";
    static Boolean answer = false;
    static int score = 0;
    static int questionNumber = 0;
    static CountryHandler countryHandler = new CountryHandler();
    public enum gameMode{
        Allcountries,
        Europe,
        Asia,
        Oceania,
        NorthAmerica,
        SouthAmerica
    }

    public static void GameWindow(Stage mainStage, String gameMode){
        

        BorderPane borderPane = new BorderPane();
        GridPane gridPaneTop = new GridPane();
        GridPane gridPaneTopText = new GridPane();
        ButtonBar buttonBar = new ButtonBar();
        ImageView imageView = new ImageView();
        GridPane gridPaneBottom = new GridPane();

        //stageoptions
        mainStage.setTitle("Quiz");
        Scene gameScene = new Scene(borderPane, 1000,1000);
        mainStage.setScene(gameScene);

        //css
        gameScene.getStylesheets().add("GUI/Main.css");
        gridPaneBottom.getStyleClass().add("bottom");
        gridPaneTop.getStyleClass().add("top");

        /**
         * BorderPane
         * */
        borderPane.setCenter(imageView);
        borderPane.setTop(gridPaneTop);
        borderPane.setBottom(gridPaneBottom);
        //borderPane.setPadding(new Insets(0,0,0,0));

        /**
         * gridPaneTop
         * */
        gridPaneTop.setAlignment(Pos.CENTER);
        gridPaneTop.add(buttonBar,0,0,1,1);
        gridPaneTop.add(gridPaneTopText,0,1,1,1);

        /**
         * ButtonBar
         * */
        Button menuBtn = new Button("Menu");
        menuBtn.setFont(new Font(15));
        buttonBar.getButtons().add(menuBtn);
        buttonBar.setPadding(new Insets(10,0,0,0));

        /**
         * gridPaneTopText
         * */
        //questionText
        Label questionText = new Label("What is the capital in?");
        questionText.setFont(new Font(50));
        questionText.setPrefWidth(600);
        questionText.setAlignment(Pos.CENTER);

        //CountryNameDisplay
        Label countryName = new Label("CountryName");
        countryName.setFont(new Font(30));
        countryName.setPrefWidth(300);
        countryName.setWrapText(true);
        countryName.setAlignment(Pos.CENTER);

        //ContinentNameDisplay
        Label continentName = new Label("ContinentName");
        continentName.setFont(new Font(30));
        continentName.setPrefWidth(300);
        continentName.setAlignment(Pos.CENTER);

        //NumberTrackerText
        Label questionNumberText = new Label("Question: ");
        Label currentQuestionNumberText = new Label((questionNumber+1)+" of "+countryHandler.getAllCountries().size());
        questionNumberText.setFont(new Font(30));
        questionNumberText.setPrefWidth(300);
        questionNumberText.setAlignment(Pos.CENTER);
        currentQuestionNumberText.setFont(new Font(30));
        currentQuestionNumberText.setPrefWidth(300);
        currentQuestionNumberText.setAlignment(Pos.CENTER);

        gridPaneTopText.setAlignment(Pos.CENTER);
        gridPaneTopText.setPadding(new Insets(30,25,25,25));
        gridPaneTopText.setHgap(50);
        gridPaneTopText.setVgap(10);


        gridPaneTopText.add(questionText,0,0,2,1);
        gridPaneTopText.add(countryName, 0,1,1,2);
        gridPaneTopText.add(questionNumberText,1,1,1,1);
        gridPaneTopText.add(currentQuestionNumberText,1,3,1,1);
        gridPaneTopText.add(continentName,0,3,1,1);

        /**
         * ImageView
         * */
        //imageView
        getCurrentQuestion(countryName,continentName,imageView);
        System.out.println("src/Images/"+fileName);

        imageView.setFitHeight(400);
        imageView.setFitWidth(600);

        /**
         * GridpaneBottom
         * */
        //AnswerField
        TextField answerField = new TextField();
        answerField.setPrefWidth(300);
        answerField.setFont(new Font(20));

        //Buttons
        Button answerBTN = new Button("Answer");
        answerBTN.setDefaultButton(true);
        answerBTN.setFont(new Font(20));
        answerBTN.setPrefWidth(300);

        //ScoreLabel
        Label scoreLabel = new Label("Score");
        scoreLabel.setFont(new Font(20));
        scoreLabel.setPrefWidth(300);
        scoreLabel.setWrapText(true);
        scoreLabel.setText("Correct answers: "+Integer.toString(score)+" of "+countryHandler.getAllCountries().size());

        gridPaneBottom.setAlignment(Pos.CENTER);
        gridPaneBottom.setPadding(new Insets(25,25,200,25));
        gridPaneBottom.setHgap(10);
        gridPaneBottom.setVgap(10);


        gridPaneBottom.add(answerField,0,0,2,1);
        gridPaneBottom.add(answerBTN,2,0,1,1);
        gridPaneBottom.add(scoreLabel,0,1,2,3);

        //ButtonActions
        answerBTN.setOnAction(e->{
            checkAnswer(answerField, countryName);
            System.out.println("\n[AnswerButton pressed]");
            System.out.println("Answer is currently ["+answer+"]");
            if(answer){
                score = score +1;
                scoreLabel.setText("Correct answers: "+Integer.toString(score)+" of "+countryHandler.getAllCountries().size());
                System.out.println("[Answer is correct]\n");
            }
            else{
                System.out.println("\n[Answer is not correct]\n");

            }

            questionNumber = questionNumber +1;
            if(questionNumber <= countryHandler.getAllCountries().size()-1){
                getCurrentQuestion(countryName,continentName,imageView);
                currentQuestionNumberText.setText(((questionNumber+1)+" of "+countryHandler.getAllCountries().size()));
            }
            else{
                answerBTN.setDisable(true);
                scoreLabel.setText("Thank you for playing, you managed to get " + Integer.toString(score)+" of "+countryHandler.getAllCountries().size()+" questions right");
            }
            answer = false;
            answerField.setText("");
            System.out.println("Quest num: "+questionNumber);
        });

        menuBtn.setOnAction(e->{
            MenuScene.MenuWindow(mainStage);
        });


        mainStage.show();


    }

    public static void getCurrentQuestion(Label countryName, Label continentName, ImageView currentImage){
        Country currentCountry = countryHandler.getAllCountries().get(questionNumber);
        countryName.setText(currentCountry.getCountryName());
        continentName.setText("("+currentCountry.getContinent()+")");
        fileName = currentCountry.getImageFilePath();
        System.out.println("Current country image name: "+currentCountry.getImageFilePath());

        File imageFilePath = new File("src/Images/"+fileName);
        System.out.println("src/Images/"+fileName);
        currentImage.setImage(new Image(imageFilePath.toURI().toString()));
    }

    public static Boolean checkAnswer(TextField answerField, Label countryName){
        Country c = countryHandler.getCountry(countryName.getText().toUpperCase());

        System.out.println("CountryName: "+countryName.getText().toUpperCase());
        System.out.println("Answer: "+answerField.getText().toUpperCase());
        System.out.println("Capital answer: "+c.getCapital().toUpperCase());

        if(answerField.getText().toUpperCase().equals(c.getCapital().toUpperCase())){
            System.out.println("[Setting answer to true]");
            answer = true;
        }
        return answer;
    }
}
