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
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;


public class GameStage {

    static String fileName = "";
    static Boolean answer = false;
    static int score = 0;
    static CountryHandler countryHandler = new CountryHandler();

    public static void GameWindow(Stage gameStage){
        addExampleData();
        BorderPane borderPane = new BorderPane();
        GridPane gridPaneTop = new GridPane();
        ImageView imageView = new ImageView();
        GridPane gridPaneBottom = new GridPane();

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

        //gridPaneBottom
        gridPaneBottom.setAlignment(Pos.TOP_CENTER);
        gridPaneBottom.setPadding(new Insets(25,25,25,25));
        gridPaneBottom.setHgap(10);
        gridPaneBottom.setVgap(10);
        gridPaneBottom.setPrefSize(400,400);

        Text countryName = new Text("CountryName");
        countryName.setFont(new Font(30));

        Label scoreLabel = new Label("Score");
        scoreLabel.setFont(new Font(20));
        scoreLabel.setText(Integer.toString(score)+" / "+countryHandler.getAllCountries().size());

        TextField answerField = new TextField();
        Button answerBTN = new Button("Answer");
        answerBTN.setDefaultButton(true);
        gridPaneBottom.add(countryName, 0,0);
        gridPaneBottom.add(answerField,0,1,3,1);
        gridPaneBottom.add(answerBTN,4,1,1,1);
        gridPaneBottom.add(scoreLabel,0,2,1,1);

        //questionText
        Text questionText = new Text("What is the capital in?");
        questionText.setFont(new Font(50));
        gridPaneTop.add(questionText,0,0);

        //imageView
        getQuestion(countryName);
        //src/Images/norway.png
        File file = new File("src/Images/"+fileName);
        System.out.println("src/Images/"+fileName);

        imageView.setImage(new Image(file.toURI().toString()));
        imageView.setFitHeight(400);
        imageView.setFitWidth(600);

        //ButtonActions
        answerBTN.setOnAction(e->{
            getAllQuestions();
            checkAnswer(answerField);
            System.out.println("[AnswerButton pressed]");
            System.out.println("Answer is currently ["+answer+"]");
            if(answer){
                score = score +1;
                scoreLabel.setText(Integer.toString(score)+" / "+countryHandler.getAllCountries().size());
                System.out.println("[Answer is correct]");

            }
            else{
                System.out.println("[Answer is not correct]");
            }
            answer = false;
        });


        gameStage.setTitle("Quiz");
        gameStage.setScene(new Scene(borderPane, 1000, 1000));
        gameStage.show();


    }

    public static void getAllQuestions(){
        List<Country> countries =  countryHandler.getAllCountries();
        for(int i=0; i<countries.size(); i++){
            System.out.println(countries.toString());
        }
    }

    public static void getQuestion(Text countryName){
        Country c = countryHandler.getCountry("norway");
        countryName.setText(c.getCountryName().toString());
        fileName = c.getImageFilePath().toString();
    }

    public static Boolean checkAnswer(TextField answerField){
        Country c = countryHandler.getCountry("norway");
        System.out.println("Answer: "+answerField.getText().toUpperCase());
        System.out.println("Capital answer: "+c.getCapital().toUpperCase());

        if(answerField.getText().toUpperCase().equals(c.getCapital().toUpperCase())){
            System.out.println("[Setting answer to true]");
            answer = true;
        }
        return answer;
    }

    public static void addExampleData(){
        countryHandler.addCountry(new Country("norway", "Oslo", "norway.png"));
        countryHandler.addCountry(new Country("sweden", "stockholm", "sweden.png"));
        countryHandler.addCountry(new Country("denmark", "København", "denmark.png"));
    }
}
