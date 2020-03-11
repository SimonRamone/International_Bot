package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import logic.Board;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BoardUI extends Application {
    int BOARD_SIZE = 16;
    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        GridPane gridPane = new GridPane();
        StackPane rootPane = new StackPane();
        VBox vbox1 = new VBox(rootPane);
        vbox1.prefWidthProperty().bind(window.widthProperty().multiply(1));

        VBox playerName = new VBox();
        VBox playerScore = new VBox();
        HBox scores = new HBox(playerName, playerScore);

        Label scoreTitle = new Label("SCOREBOARD");
        VBox scoreBoard = new VBox(scoreTitle, scores);

        scoreBoard.setAlignment(Pos.CENTER);
        scores.setAlignment(Pos.CENTER);

        Label player1 = new Label("Player 1: ");
        Label score1 = new Label();
        score1.setText("Score: 10");

        Label player2 = new Label("Player 2: ");
        Label score2 = new Label();
        score2.setText("Score: 20");

        Label player3 = new Label("Player 3: ");
        Label score3 = new Label();
        score3.setText("Score: 30");

        Label player4 = new Label("Player 4: ");
        Label score4 = new Label();
        score4.setText("Score: 40");

        playerName.getChildren().addAll(player1, player2, player3, player4);
        playerScore.getChildren().addAll(score1, score2, score3, score4);

        Label label1 = new Label("User Input");
        TextField word = new TextField();

        Label label2 = new Label("Row");
        TextField xCoord = new TextField();
        xCoord.setPrefWidth(100);

        Label label3 = new Label("Column");
        TextField yCoord = new TextField();
        yCoord.setPrefWidth(100);

        Button submit = new Button("Submit");
        Button skip = new Button("Skip");
        Button challenge = new Button("Challenge!");

        GridPane inputArea = new GridPane();
        inputArea.setPadding(new Insets(30, 10, 10, 30));
        inputArea.setHgap(20);
        inputArea.setVgap(20);
        HBox coords = new HBox();
        coords.setSpacing(10);
        coords.getChildren().addAll(label2, xCoord, label3, yCoord);
        inputArea.add(label1, 0 , 0);
        inputArea.add(word, 1, 0);
        inputArea.add(coords, 0,2,2,1);

        HBox inputButtons = new HBox();
        inputButtons.getChildren().addAll(submit, skip, challenge);
        inputButtons.setSpacing(30);
        inputArea.add(inputButtons, 0 , 4, 3, 1);

        TextArea textArea = new TextArea();
        inputArea.prefHeightProperty().bind(window.heightProperty().multiply(0.3));
        scoreBoard.prefHeightProperty().bind(window.heightProperty().multiply(0.2));
        textArea.prefHeightProperty().bind(window.heightProperty().multiply(0.5));

        VBox vbox2 = new VBox(scoreBoard, inputArea, textArea);
        vbox2.prefWidthProperty().bind(window.widthProperty().multiply(0.5));

        HBox hbox = new HBox(vbox1, vbox2);
        Scene scene = new Scene(hbox, 770, 500);

        Board board = new Board();

        Button[][] bt = new Button[BOARD_SIZE][BOARD_SIZE];
        int countX = 65;
        int countY = 0;
        /*
        normal square - white
        double letter score - lightblue
        triple letter score - darkblue
        double word score - purple
        triple word score - red
         */
        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                bt[r][c] = new Button();
                bt[r][c].prefWidthProperty().bind(gridPane.widthProperty());
                bt[r][c].prefHeightProperty().bind(gridPane.heightProperty());
                gridPane.add(bt[r][c], c, r);

                if(r > 14 || c > 14){
                    if(r ==  15 && c < 15){
                        bt[r][c].setText(String.valueOf((char)countX));
                        countX++;
                    }
                    else if(c == 15 && r < 15){
                        bt[r][c].setText(String.valueOf(countY));
                        countY++;
                    }
                    else{
                        bt[r][c].setText(" ");
                    }
                }
                else{
                    if(board.scrabbleBoard[r][c].getLetterMultiplier() == 1 && board.scrabbleBoard[r][c].getWordMultiplier() == 1) {
                        bt[r][c].setStyle("-fx-base: white;");
                    }
                    if(board.scrabbleBoard[r][c].getLetterMultiplier() == 2 && board.scrabbleBoard[r][c].getWordMultiplier() == 1) {
                        bt[r][c].setStyle("-fx-base: #ADD8E6;");
                    }
                    if(board.scrabbleBoard[r][c].getLetterMultiplier() == 3 && board.scrabbleBoard[r][c].getWordMultiplier() == 1) {
                        bt[r][c].setStyle("-fx-base: #00008B;");
                    }
                    if(board.scrabbleBoard[r][c].getLetterMultiplier() == 1 && board.scrabbleBoard[r][c].getWordMultiplier() == 2) {
                        bt[r][c].setStyle("-fx-base: #FF6347;");
                    }
                    if(board.scrabbleBoard[r][c].getLetterMultiplier() == 1 && board.scrabbleBoard[r][c].getWordMultiplier() == 3) {
                        bt[r][c].setStyle("-fx-base: #9370DB;");
                    }
                    if(r == 7 && c == 7){
                        bt[r][c].setStyle("-fx-font-size: 120%; -fx-base: #FF6347;");
                        bt[r][c].setText("\u2B50");
                    }
                }

                if(r < 15 && c < 15){
                    int finalR = r;
                    int finalC = c + 65;
                    bt[r][c].setOnMouseClicked((MouseEvent e) -> {
                        StringBuilder str = new StringBuilder();
                        str.append("Coordinate selected: [" + finalR + ", " + (char)finalC + "] \n");
                        textArea.appendText(str.toString());

                        xCoord.setText(String.valueOf(finalR));
                        yCoord.setText(String.valueOf((char)finalC));
                    });
                }
            }
        }

        rootPane.getChildren().addAll(gridPane);

        window.setTitle("Scrabble");
        window.setScene(scene);
        window.show();


    }
}
