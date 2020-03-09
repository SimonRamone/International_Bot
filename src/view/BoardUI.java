package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logic.Board;

import java.util.concurrent.atomic.AtomicInteger;

public class BoardUI extends Application {
    int BOARD_SIZE = 15;
    Stage window;
    Button bt;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        GridPane gridPane = new GridPane();
        StackPane pane = new StackPane();

        Scene scene = new Scene(pane, 770, 500);
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
                bt[r][c].prefHeightProperty().bind(gridPane.widthProperty());
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

                int finalR = r;
                int finalC = c;
                bt[r][c].setOnMouseClicked((MouseEvent e) -> {
                    StringBuilder str = new StringBuilder();
                    str.append("Coordinate selected: [" + finalR + ", " + finalC + "]");
                    System.out.println(str);
                });

            }
        }



            pane.getChildren().add(gridPane);

        window.setTitle("Scrabble");
        window.setScene(scene);
        window.show();


    }
}
