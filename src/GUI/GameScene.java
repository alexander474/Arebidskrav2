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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.File;
import java.util.List;

public class GameScene {

    static String imageFileName = "";
    static Boolean answer = false;
    static int score; //how many right answers
    static int questionNumber; //what question your'e currently at
    static int totalQuestions; //how many questions that exists
    static CountryHandler countryHandler = new CountryHandler();
    static Country currentCountry;
    static ImageView imageView;

    static Label questionText;
    static Label countryName;
    static Label continentName;

    public static void GameWindow(Stage mainStage, String questionCategory, String gameType){
        score = 0; //how many right answers
        questionNumber = 0; //what question your'e currently at
        totalQuestions = 0; //how many questions that exists

        BorderPane borderPane = new BorderPane();
        GridPane gridPaneTop = new GridPane();
        GridPane gridPaneTopText = new GridPane();
        ButtonBar buttonBar = new ButtonBar();
        imageView = new ImageView();
        GridPane gridPaneBottom = new GridPane();

        //stageoptions
        mainStage.setTitle("Capital Quiz");
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
        questionText = new Label("QuestionText");
        questionText.setFont(new Font(50));
        questionText.setPrefWidth(600);
        questionText.setAlignment(Pos.CENTER);

        //CountryNameDisplay
        countryName = new Label();
        countryName.setFont(new Font(30));
        countryName.setPrefWidth(300);
        countryName.setWrapText(true);
        countryName.setAlignment(Pos.CENTER);

        //ContinentNameDisplay
        continentName = new Label("ContinentName");
        continentName.setFont(new Font(30));
        continentName.setPrefWidth(300);
        continentName.setAlignment(Pos.CENTER);

        //NumberTrackerText
        Label questionNumberText = new Label("Question: ");
        Label currentQuestionNumberText = new Label((questionNumber+1)+" of "+totalQuestions);
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
        scoreLabel.setText("Correct answers: "+Integer.toString(score)+" of "+totalQuestions);

        gridPaneBottom.setAlignment(Pos.CENTER);
        gridPaneBottom.setPadding(new Insets(25,25,200,25));
        gridPaneBottom.setHgap(10);
        gridPaneBottom.setVgap(10);

        gridPaneBottom.add(answerField,0,0,2,1);
        gridPaneBottom.add(answerBTN,2,0,1,1);
        gridPaneBottom.add(scoreLabel,0,1,2,3);

        /**
         * Gets the first question
         * */
        getCurrentQuestion(questionCategory, gameType);
        currentQuestionNumberText.setText(((questionNumber+1)+" of "+totalQuestions));
        scoreLabel.setText("Correct answers: "+Integer.toString(score)+" of "+totalQuestions);

        /**
         * Running the answerbutton and checking if the answer is right or wrong and gets the next question
         * */
        answerBTN.setOnAction(e->{
            checkAnswer(answerField, gameType);
            System.out.println("-[AnswerButton pressed]-");
            if(answer){
                score = score +1;
                scoreLabel.setText("Correct answers: "+Integer.toString(score)+" of "+totalQuestions);
                System.out.println("answer is: ["+answer+"]");
                System.out.println("---------------------------------------\n");
            }
            else{
                System.out.println("answer is: ["+answer+"]");
                System.out.println("---------------------------------------\n");
            }

            questionNumber = questionNumber +1;
            if(questionNumber <= totalQuestions-1){
                getCurrentQuestion(questionCategory, gameType);
                currentQuestionNumberText.setText(((questionNumber+1)+" of "+totalQuestions));
            }
            else{
                answerBTN.setDisable(true);
                scoreLabel.setText("Thank you for playing, you managed to get " + Integer.toString(score)+" of "+totalQuestions+" questions right");
            }
            answer = false;
            answerField.setText("");
            System.out.println("Quest num: "+(questionNumber+1));
        });

        menuBtn.setOnAction(e->{
            score = 0; //how many right answers
            questionNumber = 0; //what question your'e currently at
            totalQuestions = 0; //how many questions that exists
            MenuScene.MenuWindow(mainStage);
        });


        mainStage.show();


    }

    /**
     * gets the currentquestion based on which category is chosen
     * */
    public static void getQuestions(String questionCategory){
        if(questionCategory.equals("All Countries")){
            currentCountry = countryHandler.getAllCountries().get(questionNumber);
            totalQuestions = countryHandler.getAllCountries().size();
        }
        else{
            currentCountry = countryHandler.getAllCountriesInContinent(questionCategory).get(questionNumber);
            totalQuestions = countryHandler.getAllCountriesInContinent(questionCategory).size();
        }
    }
    /**
     * Gets the current question and gets the proper information for that question/country
     * */
    public static void getCurrentQuestion(String questionCategory, String gameType){
        getQuestions(questionCategory);
        continentName.setText("("+currentCountry.getContinent()+")");


        if(gameType.equals("CapitalGame")){
            countryName.setText(currentCountry.getCountryName());
            questionText.setText("What is the capital in?");
        }
        else if(gameType.equals("FlagGame")){
            questionText.setText("Which country has this flag?");
            questionText.setFont(new Font(40));
            questionText.setPrefWidth(600);
        }
        imageFileName = currentCountry.getImageFilePath();
        File imageFilePath = new File("src/Images/"+imageFileName);
        imageView.setImage(new Image(imageFilePath.toURI().toString()));

        System.out.println("ImageFileName: ["+imageFileName+"]");
        System.out.println("ImageFilePath: ["+imageFilePath.toURI().toString()+"]");
    }


    /**
     * Checks the answer against the input value and returns true if the answer i correct
     * */
    public static Boolean checkAnswer(TextField answerField, String gameType) {

        System.out.println("CountryName: [" + currentCountry.getCountryName().toUpperCase() + "]");
        System.out.println("Answer: [" + answerField.getText().toUpperCase() + "]");
        System.out.println("Capital answer: [" + currentCountry.getCapital().toUpperCase() + "]");

        if (gameType.equals("CapitalGame")) {
            if (answerField.getText().toUpperCase().equals(currentCountry.getCapital().toUpperCase())) {
                answer = true;
            }
        }
        else if (gameType.equals("FlagGame")) {
            if (answerField.getText().toUpperCase().equals(currentCountry.getCountryName().toUpperCase())) {
                answer = true;
            }
        }
        return answer;
    }
}
