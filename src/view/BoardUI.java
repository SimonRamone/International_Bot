package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import logic.Board;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BoardUI extends Application {
    int BOARD_SIZE = 15;
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
        VBox scoreBoard = new VBox(new Label("SCOREBOARD"), scores);

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

        TextArea textArea = new TextArea();
        VBox vbox2 = new VBox(textArea, scoreBoard);
        vbox2.prefWidthProperty().bind(window.widthProperty().multiply(0.5));

        HBox hbox = new HBox(vbox1, vbox2);
        Scene scene = new Scene(hbox, 770, 500);

        Board board = new Board();

        Button[][] bt = new Button[BOARD_SIZE][BOARD_SIZE];
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

                int finalR = r;
                int finalC = c;
                bt[r][c].setOnMouseClicked((MouseEvent e) -> {
                    StringBuilder str = new StringBuilder();
                    str.append("Coordinate selected: [" + finalR + ", " + finalC + "] \n");
                    textArea.appendText(str.toString());
                });

            }
        }

        rootPane.getChildren().addAll(gridPane);

        window.setTitle("Scrabble");
        window.setScene(scene);
        window.show();


    }
}
