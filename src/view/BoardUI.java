package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Screen;
import javafx.stage.Stage;
import logic.Board;
import logic.Pool;
import logic.ScrabblePlayer;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BoardUI extends Application {
    int BOARD_SIZE = 16;
    Stage window;
    Scene scene1, scene2;
	int numberOfPlayers = 0;
	int i = 1;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	Pool P = new Pool();
		ScrabblePlayer scrabblePlayers = new ScrabblePlayer(P);
		
        window = primaryStage;
        
        VBox playerName = new VBox();
        VBox playerScore = new VBox();
        Label player1 = new Label(), player2 = new Label(), player3 = new Label(), player4 = new Label(), 
        		score1 = new Label(), score2 = new Label(), score3 = new Label(), score4 = new Label();
        
        //First scene - player name input
        VBox layoutScene1 = new VBox();
        VBox chooseNumberOfPlayersVBox = new VBox();
        VBox enterNamesVBox = new VBox();
        HBox startGameHBox = new HBox();
        
        Label label1Scene1 = new Label("Choose Amount of Players");
        Label label2Scene1 = new Label("Enter Player Names");
        Button btnStartGame = new Button("Start Game");
        Button btnReturn = new Button("Return");
        btnStartGame.setMinWidth(97);
        btnReturn.setMinWidth(97);
        
        Button btnTwoPlayers = new Button("2");
        Button btnThreePlayers = new Button("3");
        Button btnFourPlayers = new Button("4");
        HBox numberOfPlayersHBox = new HBox(btnTwoPlayers, btnThreePlayers, btnFourPlayers);
        numberOfPlayersHBox.setSpacing(5);
        numberOfPlayersHBox.setAlignment(Pos.CENTER);
        
        TextField playerOneName = new TextField("Player 1");  
        TextField playerTwoName = new TextField("Player 2");
        TextField playerThreeName = new TextField("Player 3");
        TextField playerFourName = new TextField("Player 4");
        playerOneName.setMaxWidth(200);
        playerTwoName.setMaxWidth(200);
        playerThreeName.setMaxWidth(200);
        playerFourName.setMaxWidth(200);
        
        layoutScene1.setSpacing(5);
        layoutScene1.setAlignment(Pos.CENTER);
        
        startGameHBox.getChildren().addAll(btnReturn, btnStartGame);
        startGameHBox.setSpacing(6);
        startGameHBox.setAlignment(Pos.CENTER);
        
        chooseNumberOfPlayersVBox.getChildren().addAll(label1Scene1, numberOfPlayersHBox);
        chooseNumberOfPlayersVBox.setSpacing(5);
        chooseNumberOfPlayersVBox.setAlignment(Pos.CENTER);
        enterNamesVBox.setSpacing(5);
        enterNamesVBox.setAlignment(Pos.CENTER);
        
        layoutScene1.getChildren().addAll(chooseNumberOfPlayersVBox, enterNamesVBox);
        
        btnTwoPlayers.setOnAction(e -> {
        	enterNamesVBox.getChildren().clear();
        	chooseNumberOfPlayersVBox.getChildren().clear();
        	enterNamesVBox.getChildren().addAll(label2Scene1, playerOneName, playerTwoName, startGameHBox);
        	numberOfPlayers = 2;
        });
        
        btnThreePlayers.setOnAction(e -> {
        	enterNamesVBox.getChildren().clear();
        	chooseNumberOfPlayersVBox.getChildren().clear();
        	enterNamesVBox.getChildren().addAll(label2Scene1, playerOneName, playerTwoName, playerThreeName, startGameHBox);
        	numberOfPlayers = 3;
        });
        
        btnFourPlayers.setOnAction(e -> {
        	enterNamesVBox.getChildren().clear();
        	chooseNumberOfPlayersVBox.getChildren().clear();
        	enterNamesVBox.getChildren().addAll(label2Scene1, playerOneName, playerTwoName, playerThreeName, playerFourName, startGameHBox);
        	numberOfPlayers = 4;
        });
        
        btnReturn.setOnAction(e -> {
        	chooseNumberOfPlayersVBox.getChildren().clear();
        	chooseNumberOfPlayersVBox.getChildren().addAll(label1Scene1, numberOfPlayersHBox);
        	enterNamesVBox.getChildren().clear();
        	layoutScene1.getChildren().clear();
        	layoutScene1.getChildren().addAll(chooseNumberOfPlayersVBox, enterNamesVBox);
        	numberOfPlayers = 0;
        });

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        btnStartGame.setOnAction(e -> {
        	switch(numberOfPlayers) {
        		case 2:
        			scrabblePlayers.addPlayer(playerOneName.getText());
        			scrabblePlayers.addPlayer(playerTwoName.getText());
        			player1.setText("[1]" + scrabblePlayers.getPlayer(0).getName());
                	player2.setText("[2]" + scrabblePlayers.getPlayer(1).getName());
                	score1.setText(" 0");
                	score2.setText(" 0");
                	playerName.getChildren().addAll(player1, player2);
                    playerScore.getChildren().addAll(score1, score2);
                    primaryStage.setScene(scene2);
                    primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
                    primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 4);
        			break;
        		case 3:
        			scrabblePlayers.addPlayer(playerOneName.getText());
        			scrabblePlayers.addPlayer(playerTwoName.getText());
        			scrabblePlayers.addPlayer(playerThreeName.getText());
        			player1.setText("[1]" + scrabblePlayers.getPlayer(0).getName());
                	player2.setText("[2]" + scrabblePlayers.getPlayer(1).getName());
                	player3.setText("[3]" + scrabblePlayers.getPlayer(2).getName());
                	score1.setText(" 0");
                	score2.setText(" 0");
                	score3.setText(" 0");
                	playerName.getChildren().addAll(player1, player2, player3);
                    playerScore.getChildren().addAll(score1, score2, score3);
                    primaryStage.centerOnScreen();
	        		primaryStage.setScene(scene2);
                    primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
                    primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 4);
	    			break;
        		case 4:
        			scrabblePlayers.addPlayer(playerOneName.getText());
        			scrabblePlayers.addPlayer(playerTwoName.getText());
        			scrabblePlayers.addPlayer(playerThreeName.getText());
        			scrabblePlayers.addPlayer(playerFourName.getText());
        			player1.setText("[1]" + scrabblePlayers.getPlayer(0).getName());
                	player2.setText("[2]" + scrabblePlayers.getPlayer(1).getName());
                	player3.setText("[3]" + scrabblePlayers.getPlayer(2).getName());
                	player4.setText("[4]" + scrabblePlayers.getPlayer(3).getName());
                	score1.setText(" 0");
                	score2.setText(" 0");
                	score3.setText(" 0");
                	score4.setText(" 0");
                	playerName.getChildren().addAll(player1, player2, player3, player4);
                    playerScore.getChildren().addAll(score1, score2, score3, score4);
                    primaryStage.centerOnScreen();
        			primaryStage.setScene(scene2);
                    primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
                    primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 4);
        			break;
        	}	
        	
        });
        
        scene1 = new Scene(layoutScene1, 750, 500);
        
        
        //Second scene - Scrabble board
        GridPane gridPane = new GridPane();
        StackPane rootPane = new StackPane();
        VBox scrabbleBoard = new VBox(rootPane);
        scrabbleBoard.prefWidthProperty().bind(window.widthProperty().multiply(1));

        HBox scores = new HBox();

        Label scoreTitle = new Label("SCOREBOARD");
        VBox scoreBoard = new VBox();

        scoreBoard.setAlignment(Pos.CENTER);
        scores.setAlignment(Pos.CENTER);
        
        scores.getChildren().addAll(playerName, playerScore);
        scoreBoard.getChildren().addAll(scoreTitle, scores);

        Label label1 = new Label("Input");
        TextField word = new TextField();

        Label label2 = new Label("Row");
        TextField xCoord = new TextField();
        xCoord.setPrefWidth(100);

        Label label3 = new Label("Column");
        TextField yCoord = new TextField();
        yCoord.setPrefWidth(100);

        Button submit = new Button("Submit");
        
        submit.setOnAction(e -> {
        	switch(numberOfPlayers) {
    		case 2:
            	score1.setText(" " + scrabblePlayers.getPlayer(0).getScore());
            	score2.setText(" " + scrabblePlayers.getPlayer(1).getScore());
    			break;
    		case 3:
    			score1.setText(" " + scrabblePlayers.getPlayer(0).getScore());
            	score2.setText(" " + scrabblePlayers.getPlayer(1).getScore());
            	score3.setText(" " + scrabblePlayers.getPlayer(2).getScore());
    			break;
    		case 4:
    			score1.setText(" " + scrabblePlayers.getPlayer(0).getScore());
            	score2.setText(" " + scrabblePlayers.getPlayer(1).getScore());
            	score3.setText(" " + scrabblePlayers.getPlayer(2).getScore());
            	score4.setText(" " + scrabblePlayers.getPlayer(3).getScore());
    			break;
    	}
        });
        
        Button skip = new Button("Skip");
        Button challenge = new Button("Challenge!");

        GridPane inputArea = new GridPane();
        inputArea.setPadding(new Insets(10, 10, 10, 30));
        inputArea.setHgap(20);
        inputArea.setVgap(20);
        HBox coords = new HBox();
        coords.setSpacing(10);
        coords.getChildren().addAll(label2, xCoord, label3, yCoord);
        inputArea.add(label1, 0 , 0);
        word.setPrefWidth(200);
        xCoord.setPrefWidth(50);
        yCoord.setPrefWidth(50);
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

        VBox informationBox = new VBox(scoreBoard, inputArea, textArea);
        informationBox.prefWidthProperty().bind(window.widthProperty().multiply(0.5));
        
        HBox hbox = new HBox(scrabbleBoard, informationBox);
        scene2 = new Scene(hbox, 1200, 700);

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
        window.setScene(scene1);
        window.show();


    }
}
