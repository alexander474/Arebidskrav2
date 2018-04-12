package GUI;

import Quiz.Country;
import Quiz.CountryHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.util.Random;

public class FlagGameScene {

    static String fileName = "";
    static Boolean answer = false;
    static int score; //how many right answers
    static int questionNumber; //what question your'e currently at
    static int totalQuestions; //how many questions that exists
    static CountryHandler countryHandler = new CountryHandler();
    static Country currentCountry;
    static Country randomCountry;
    static String radioButtonChoice;

    static ImageView imageViewOne = new ImageView();
    static ImageView imageViewTwo = new ImageView();
    static ImageView imageViewThree = new ImageView();
    static ImageView imageViewFour = new ImageView();

    static RadioButton radioButtonOne = new RadioButton();
    static RadioButton radioButtonTwo = new RadioButton();
    static RadioButton radioButtonThree = new RadioButton();
    static RadioButton radioButtonFour = new RadioButton();


    public static void GameWindow(Stage mainStage, String gameMode){
        score = 0; //how many right answers
        questionNumber = 0; //what question your'e currently at
        totalQuestions = 0; //how many questions that exists
        radioButtonChoice = "";

        BorderPane borderPane = new BorderPane();
        ButtonBar buttonBar = new ButtonBar();
        GridPane gridPaneTop = new GridPane();
        GridPane gridPaneTopText = new GridPane();
        VBox vBoxCenter = new VBox();
        HBox hBoxAlternativeOne = new HBox();
        HBox hBoxAlternativeTwo = new HBox();
        HBox hBoxAlternativeThree = new HBox();
        HBox hBoxAlternativeFour = new HBox();
        GridPane gridPaneBottom = new GridPane();

        //stageoptions
        mainStage.setTitle("Flag Quiz");
        Scene gameScene = new Scene(borderPane, 1000,1000);
        mainStage.setScene(gameScene);

        //css
        gameScene.getStylesheets().add("GUI/Main.css");

        /**
         * BorderPane
         * */
        borderPane.setTop(gridPaneTop);
        borderPane.setCenter(vBoxCenter);
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
        Label countryName = new Label("CountryName");
        countryName.setFont(new Font(50));
        countryName.setPrefWidth(600);
        countryName.setAlignment(Pos.CENTER);


        //ContinentNameDisplay
        Label continentName = new Label("ContinentName");
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


        gridPaneTopText.add(countryName,0,0,2,1);
        gridPaneTopText.add(continentName, 0,1,1,2);
        gridPaneTopText.add(questionNumberText,1,1,1,1);
        gridPaneTopText.add(currentQuestionNumberText,1,3,1,1);

        /**
         * vBoxCenter
         * */
        vBoxCenter.setAlignment(Pos.CENTER);
        vBoxCenter.getChildren().addAll(hBoxAlternativeOne,hBoxAlternativeTwo,hBoxAlternativeThree,hBoxAlternativeFour);
        vBoxCenter.setSpacing(10);


        final ToggleGroup chooseImage = new ToggleGroup();
        chooseImage.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if(chooseImage.getSelectedToggle() != null){
                    RadioButton radioButton = (RadioButton) chooseImage.getSelectedToggle();
                    System.out.println(radioButton.getText());
                    radioButtonChoice = radioButton.getText();
                }
            }
        });

        radioButtonOne.setToggleGroup(chooseImage);
        radioButtonTwo.setToggleGroup(chooseImage);
        radioButtonThree.setToggleGroup(chooseImage);
        radioButtonFour.setToggleGroup(chooseImage);


        hBoxAlternativeOne.getChildren().addAll(imageViewOne, radioButtonOne);
        hBoxAlternativeTwo.getChildren().addAll(imageViewTwo, radioButtonTwo);
        hBoxAlternativeThree.getChildren().addAll(imageViewThree, radioButtonThree);
        hBoxAlternativeFour.getChildren().addAll(imageViewFour, radioButtonFour);
        hBoxAlternativeOne.setAlignment(Pos.CENTER);
        hBoxAlternativeTwo.setAlignment(Pos.CENTER);
        hBoxAlternativeThree.setAlignment(Pos.CENTER);
        hBoxAlternativeFour.setAlignment(Pos.CENTER);


        imageViewOne.setFitWidth(200);
        imageViewOne.setFitHeight(150);
        imageViewTwo.setFitWidth(200);
        imageViewTwo.setFitHeight(150);
        imageViewThree.setFitWidth(200);
        imageViewThree.setFitHeight(150);
        imageViewFour.setFitWidth(200);
        imageViewFour.setFitHeight(150);

        /**
         * GridpaneBottom
         * */
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

        gridPaneBottom.add(answerBTN,4,0,1,1);
        gridPaneBottom.add(scoreLabel,0,0,2,3);


        /**
         * Gets the first question
         * */
        getCurrentQuestion(countryName,continentName, gameMode);
        currentQuestionNumberText.setText(((questionNumber+1)+" of "+totalQuestions));
        scoreLabel.setText("Correct answers: "+Integer.toString(score)+" of "+totalQuestions);

        answerBTN.setOnAction(e->{
            checkAnswer();
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
                getCurrentQuestion(countryName,continentName, gameMode);
                currentQuestionNumberText.setText(((questionNumber+1)+" of "+totalQuestions));
            }
            else{
                answerBTN.setDisable(true);
                scoreLabel.setText("Thank you for playing, you managed to get " + Integer.toString(score)+" of "+totalQuestions+" questions right");
            }
            answer = false;
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


    public static void getQuestions(String gameMode){
        if(gameMode.equals("AllCountries")){
            currentCountry = countryHandler.getAllCountries().get(questionNumber);
            totalQuestions = countryHandler.getAllCountries().size();
        }
        else{
            currentCountry = countryHandler.getAllCountriesInContinent(gameMode).get(questionNumber);
            totalQuestions = countryHandler.getAllCountriesInContinent(gameMode).size();
        }
    }

    /**
     * Gets the current question and gets the proper information for that question/country
     * */
    public static void getCurrentQuestion(Label countryName, Label continentName, String gameMode){
        getQuestions(gameMode);
        countryName.setText(currentCountry.getCountryName());
        continentName.setText("("+currentCountry.getContinent()+")");
        fileName = currentCountry.getImageFilePath();
        File imageFilePath = new File("src/Images/"+fileName);
        imageViewOne.setImage(new Image(imageFilePath.toURI().toString()));
        radioButtonOne.setText(currentCountry.getCountryName());

        getRandomImage(gameMode,imageViewTwo, radioButtonTwo);
        getRandomImage(gameMode,imageViewThree, radioButtonTwo);
        getRandomImage(gameMode,imageViewFour, radioButtonThree);


        System.out.println("ImageFileName: ["+fileName+"]");
        System.out.println("ImageFilePath: ["+imageFilePath.toURI().toString()+"]");
    }

    public static void getRandomImage(String gameMode, ImageView setRandomImage, RadioButton radioButton){
        Random rand = new Random();
        int num = rand.nextInt(totalQuestions)+1;

        if(gameMode.equals("AllCountries")){
            randomCountry = countryHandler.getAllCountries().get(num);
        }
        else{
            randomCountry = countryHandler.getAllCountriesInContinent(gameMode).get(num);
        }

        File imageFilePath = new File("src/Images/"+randomCountry.getImageFilePath());
        setRandomImage.setImage(new Image(imageFilePath.toURI().toString()));
        radioButton.setText(randomCountry.getCountryName());
    }

    public static Boolean checkAnswer(){

        System.out.println("CountryName: ["+currentCountry.getCountryName().toUpperCase()+"]");
        System.out.println("Answer: ["+radioButtonChoice.toUpperCase()+"]");
        System.out.println("Capital answer: ["+currentCountry.getCapital().toUpperCase()+"]");

        if(radioButtonChoice.toUpperCase().equals(currentCountry.getCountryName().toUpperCase())){
            answer = true;
        }
        return answer;
    }
}
