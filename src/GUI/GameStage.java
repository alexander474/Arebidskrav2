package GUI;

import Quiz.Country;
import Quiz.CountryHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;


public class GameStage {

    static String fileName = "";
    static Boolean answer = false;
    static int score = 0;
    static int questionNumber = 0;
    static CountryHandler countryHandler = new CountryHandler();

    public static void GameWindow(Stage gameStage){
        BorderPane borderPane = new BorderPane();
        GridPane gridPaneTop = new GridPane();
        ImageView imageView = new ImageView();
        GridPane gridPaneBottom = new GridPane();

        //stageoptions
        gameStage.setTitle("Quiz");
        Scene gameScene = new Scene(borderPane, 1000,1000);
        gameStage.setScene(gameScene);

        //css
        gameScene.getStylesheets().add("GUI/Main.css");

        //borderPane
        borderPane.setCenter(imageView);
        borderPane.setTop(gridPaneTop);
        borderPane.setBottom(gridPaneBottom);
        borderPane.setPadding(new Insets(25,25,15,25));

        //gridPaneTop
        gridPaneTop.setAlignment(Pos.CENTER);
        gridPaneBottom.setPadding(new Insets(25,25,25,25));
        gridPaneTop.setHgap(10);
        gridPaneTop.setVgap(10);
        gridPaneTop.setPrefHeight(200);

        //CountryNameDisplay
        Text countryName = new Text("CountryName");
        countryName.setFont(new Font(30));

        //NumberTrackerText
        Text currentQuestionNumberText = new Text("Question: "+(questionNumber+1));
        currentQuestionNumberText.setFont(new Font(30));

        //ScoreLabel
        Label scoreLabel = new Label("Score");
        scoreLabel.setFont(new Font(20));
        scoreLabel.setPrefWidth(300);
        scoreLabel.setWrapText(true);
        scoreLabel.setText("Correct answers: "+Integer.toString(score)+" of "+countryHandler.getAllCountries().size());

        //AnswerField
        TextField answerField = new TextField();
        answerField.setPrefWidth(300);
        answerField.setFont(new Font(20));



        //Buttons
        Button answerBTN = new Button("Answer");
        answerBTN.setDefaultButton(true);
        answerBTN.setFont(new Font(20));
        answerBTN.setPrefWidth(300);

        //gridPaneBottom
        gridPaneBottom.setAlignment(Pos.TOP_CENTER);
        gridPaneBottom.setPadding(new Insets(25,50,25,25));
        gridPaneBottom.setHgap(10);
        gridPaneBottom.setVgap(10);
        gridPaneBottom.setPrefSize(300,300);

        gridPaneBottom.add(countryName, 0,0,1,1);
        gridPaneBottom.add(currentQuestionNumberText,2,0,1,1);
        gridPaneBottom.add(answerField,0,1,2,1);
        gridPaneBottom.add(answerBTN,2,1,1,1);
        gridPaneBottom.add(scoreLabel,0,2,2,3);

        //questionText
        Text questionText = new Text("What is the capital in?");
        questionText.setFont(new Font(50));
        gridPaneTop.add(questionText,0,0);

        //imageView
        getCurrentQuestion(countryName, imageView);
        System.out.println("src/Images/"+fileName);

        //imageView.setImage(new Image(imageFilePath.toURI().toString()));
        imageView.setFitHeight(400);
        imageView.setFitWidth(600);

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
                getCurrentQuestion(countryName, imageView);
                currentQuestionNumberText.setText("Question: "+(questionNumber+1));
            }
            else{
                answerBTN.setDisable(true);
                scoreLabel.setText("Thank you for playing, you managed to get " + Integer.toString(score)+" of "+countryHandler.getAllCountries().size()+" questions right");
            }
            answer = false;
            answerField.setText("");
            System.out.println("Quest num: "+questionNumber);
        });


        gameStage.show();


    }

    public static void getCurrentQuestion(Text countryName, ImageView currentImage){
        Country currentCountry = countryHandler.getAllCountries().get(questionNumber);
        countryName.setText(currentCountry.getCountryName());
        fileName = currentCountry.getImageFilePath();
        System.out.println("Current country image name: "+currentCountry.getImageFilePath());

        File imageFilePath = new File("src/Images/"+fileName);
        System.out.println("src/Images/"+fileName);
        currentImage.setImage(new Image(imageFilePath.toURI().toString()));
    }

    public static Boolean checkAnswer(TextField answerField, Text countryName){
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
