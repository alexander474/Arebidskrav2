package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;

public class GameStage {

    public static void GameWindow(Stage gameStage){
        BorderPane borderPane = new BorderPane();
        GridPane gridPaneTop = new GridPane();
        ImageView imageView = new ImageView();

        //borderPane
        borderPane.setCenter(imageView);
        borderPane.setTop(gridPaneTop);
        borderPane.setPadding(new Insets(25,25,25,25));

        //gridPaneTop
        gridPaneTop.setHgap(10);
        gridPaneTop.setVgap(10);
        gridPaneTop.setAlignment(Pos.CENTER);

        //imageView



        //questionText
        Text questionText = new Text("Question Text");
        gridPaneTop.add(questionText,0,0);

        //Picture
        FileInputStream input = new FileInputStream("norway");
        Image countryImage = new Image(input);
        imageView.setImage(countryImage);


        gameStage.setTitle("Quiz");
        gameStage.setScene(new Scene(borderPane, 1000, 1000));
        gameStage.show();
    }
}
