package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import logic.Board;
import logic.LetterTile;
import logic.Pool;
import logic.ScrabblePlayer;
import logic.Scrabble;

import java.util.concurrent.atomic.AtomicInteger;

public class BoardUI extends Application {
    int BOARD_SIZE = 16;
    Stage window;
    Scene scene1, scene2;
	int numberOfPlayers = 0;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	Pool P = new Pool();
    	Board B = new Board();
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
        
        startGameHBox.getChildren().addAll(btnReturn, btnStartGame);    //scene where u choose to start game or return to scene 1.
        startGameHBox.setSpacing(6);
        startGameHBox.setAlignment(Pos.CENTER);

        chooseNumberOfPlayersVBox.getChildren().addAll(label1Scene1, numberOfPlayersHBox);//first scene of choose amount of players, with 2,3,4 buttons below.
        chooseNumberOfPlayersVBox.setSpacing(5);
        chooseNumberOfPlayersVBox.setAlignment(Pos.CENTER);
        enterNamesVBox.setSpacing(5);
        enterNamesVBox.setAlignment(Pos.CENTER);
        
        layoutScene1.getChildren().addAll(chooseNumberOfPlayersVBox, enterNamesVBox);
        
        btnTwoPlayers.setOnAction(e -> {    //once numofPlayers chosen, second scene displays enter player names + two buttons start / return
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
        
        btnReturn.setOnAction(e -> {        //return clears the scenes and remake them like it's during the initialisation stage.
        	chooseNumberOfPlayersVBox.getChildren().clear();
        	chooseNumberOfPlayersVBox.getChildren().addAll(label1Scene1, numberOfPlayersHBox);
        	enterNamesVBox.getChildren().clear();
        	layoutScene1.getChildren().clear();
        	layoutScene1.getChildren().addAll(chooseNumberOfPlayersVBox, enterNamesVBox);
        	numberOfPlayers = 0;
        });

        scene1 = new Scene(layoutScene1, 250, 250);

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
        
        scores.getChildren().addAll(playerName, playerScore);// horizontal: name + score
        scoreBoard.getChildren().addAll(scoreTitle, scores);    //vertical: scoreboard + scores hbox

        Label label1 = new Label("Input");
        TextField input = new TextField();
        input.setTextFormatter(new TextFormatter<>((change) -> {    //to uppercase input
            change.setText(change.getText().toUpperCase());
            return change;
        }));

        Label label2 = new Label("Row");
        TextField xCoord = new TextField();
        xCoord.setPrefWidth(100);

        Label label3 = new Label("Col");
        TextField yCoord = new TextField();
        yCoord.setPrefWidth(100);

        Button submit = new Button("Submit");

        Button skip = new Button("Skip");
        Button challenge = new Button("Challenge!");
        Button clear = new Button("DEL");
        
        Label labelOrientation = new Label("Angle");
        Button across = new Button("Across");
        Button down = new Button("Down");

        GridPane inputArea = new GridPane();
        inputArea.setPadding(new Insets(10, 10, 10, 30));
        inputArea.setHgap(20);
        inputArea.setVgap(20);
        HBox coords = new HBox();
        coords.setSpacing(10);
        coords.getChildren().addAll(label2, xCoord, label3, yCoord);
        inputArea.add(label1, 0 , 0);
        input.setPrefWidth(200);
        xCoord.setPrefWidth(50);
        yCoord.setPrefWidth(50);
        inputArea.add(input, 1, 0);
        inputArea.add(clear, 2, 0);
        inputArea.add(coords, 0,2,2,1);

        HBox orientationButtons = new HBox();
        orientationButtons.getChildren().addAll(across, down);
        orientationButtons.setSpacing(30);
        inputArea.add(labelOrientation, 0, 1);
        inputArea.add(orientationButtons, 1, 1, 3, 1);
        
        HBox inputButtons = new HBox();
        inputButtons.getChildren().addAll(submit, skip, challenge);
        inputButtons.setSpacing(30);
        inputArea.add(inputButtons, 0 , 4, 3, 1);

        TextArea textArea = new TextArea();
        inputArea.prefHeightProperty().bind(window.heightProperty().multiply(0.3));
        scoreBoard.prefHeightProperty().bind(window.heightProperty().multiply(0.2));
        textArea.prefHeightProperty().bind(window.heightProperty().multiply(0.5));
        textArea.setEditable(false);
        AtomicInteger playersTurn = new AtomicInteger();
        playersTurn.set(0);

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        btnStartGame.setOnAction(e -> {
            switch(numberOfPlayers) {
                case 2:
                    scrabblePlayers.addPlayer(playerOneName.getText());
                    scrabblePlayers.addPlayer(playerTwoName.getText());
                    player1.setText("[1]" + scrabblePlayers.getPlayer(0).getName());
                    player2.setText("[2]" + scrabblePlayers.getPlayer(1).getName());
                    score1.setText("{" + scrabblePlayers.getPlayer(0).getScore() + "}");
                    score2.setText("{" + scrabblePlayers.getPlayer(1).getScore() + "}");
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
                    score1.setText("{" + scrabblePlayers.getPlayer(0).getScore() + "}");
                    score2.setText("{" + scrabblePlayers.getPlayer(1).getScore() + "}");
                    score3.setText("{" + scrabblePlayers.getPlayer(2).getScore() + "}");
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
                    score1.setText("{" + scrabblePlayers.getPlayer(0).getScore() + "}");
                    score2.setText("{" + scrabblePlayers.getPlayer(1).getScore() + "}");
                    score3.setText("{" + scrabblePlayers.getPlayer(2).getScore() + "}");
                    score4.setText("{" + scrabblePlayers.getPlayer(3).getScore() + "}");
                    playerName.getChildren().addAll(player1, player2, player3, player4);
                    playerScore.getChildren().addAll(score1, score2, score3, score4);
                    primaryStage.centerOnScreen();
                    primaryStage.setScene(scene2);
                    primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
                    primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 4);
                    break;
            }

            StringBuilder str = new StringBuilder();    //print players hand
            str.append(scrabblePlayers.players.get(0).getName() + "'s Turn\n" + scrabblePlayers.players.get(0).getFrame() + "\n");
            textArea.appendText(str.toString());

        });

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
        for (int r = 0; r < BOARD_SIZE; r++) {      //creating board of buttons
            for (int c = 0; c < BOARD_SIZE; c++) {
                bt[r][c] = new Button();
                bt[r][c].prefWidthProperty().bind(gridPane.widthProperty());
                bt[r][c].prefHeightProperty().bind(gridPane.heightProperty());
                gridPane.add(bt[r][c], c, r);

                if(r > 14 || c > 14){
                    if(r ==  15 && c < 15){ //setting row and column indicator.
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
                else{   //colour to indicate type of board square
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


                if(r < 15 && c < 15){       //mouse click on button returns coordinates
                    int finalR = r;
                    int finalC = c + 65;
                    bt[r][c].setOnMouseClicked((MouseEvent e) -> {
                        StringBuilder str = new StringBuilder();
                        str.append("Coordinate selected: [" + finalR + ", " + (char)finalC + "] \n");
                        textArea.appendText(str.toString());

                        xCoord.setText(String.valueOf(finalR)); //in input horizontal bar: xy_
                        yCoord.setText(String.valueOf((char)finalC));
                        input.setText(String.valueOf((char)finalC) + String.valueOf(finalR) + " ");
                    });
                }
            }
        }
        
        across.setOnAction(e -> {
        	input.setText(yCoord.getText() + xCoord.getText() + " A ");
        });

        down.setOnAction(e -> {
        	input.setText(yCoord.getText() + xCoord.getText() + " D ");
        });
        
        clear.setOnAction(e -> {
        	input.setText("");
        });
        
        submit.setOnAction(e -> {
            if(input.getText().equals("HELP")){
                StringBuilder str = new StringBuilder();        //help instruction
                str.append("1) INPUT : <grid ref> <across/down> <word> \n\n<grid ref> is the position for the first letter\n<across/down> is the direction of word placement\n<word> is the word to be placed.\n\n2) EXCHANGE <letters>\nexchanges these letters from the frame\nwith random letters from the pool\n");
                textArea.appendText(str.toString());
            }

            if(input.getText().equals("PASS")){
                StringBuilder str = new StringBuilder();
                playersTurn.getAndIncrement();
                if(playersTurn.get() == numberOfPlayers){   //iterates between 0-#of players
                    playersTurn.set(0);
                }
                str.append("Next player's turn...\n" + scrabblePlayers.getPlayer(playersTurn.get()).getName() +"'s Turn\n" + scrabblePlayers.getPlayer(playersTurn.get()).getFrame() + "\n");
                textArea.appendText(str.toString());
                input.setText("");
            }

            if(input.getText().equals("QUIT")){
                Platform.exit();
            }

            String userInput = input.getText();
            String[] parsedInput = userInput.split(" ");
            if(parsedInput[0].equals("EXCHANGE")){  //swaping player tiles
                char[] ch = userInput.toCharArray();
                for(int i = 9; i < ch.length; i++){
                    if(ch[i] != ' '){
                        LetterTile addToPool = scrabblePlayers.getPlayer(playersTurn.get()).getFrame().getTile(ch[i]);
                        LetterTile tile = scrabblePlayers.getPlayer(playersTurn.get()).getFrame().getTile(ch[i]);
                        scrabblePlayers.getPlayer(playersTurn.get()).getFrame().removeTile(tile);
                        P.LetterPool.add(addToPool);
                        scrabblePlayers.getPlayer(playersTurn.get()).getFrame().refillFrame();
                    }
                }
                StringBuilder str = new StringBuilder();
                str.append(scrabblePlayers.getPlayer(playersTurn.get()).getFrame() + "\n");
                textArea.appendText(str.toString());
                input.setText("");
            }
            else {// error checking & word placement
            	parsedInput = userInput.split(" ");
            	String word = parsedInput[2];   //third string = word
            	StringBuilder rowString = new StringBuilder();
            	parsedInput[0] += " ";
            	System.out.println(parsedInput[0] +"| " + parsedInput[1] + " " + parsedInput[2]);
            	rowString.append(parsedInput[0].charAt(1));
            	rowString.append(parsedInput[0].charAt(2));
            	int row = Integer.parseInt(rowString.toString().trim());
            	int col = parsedInput[0].charAt(0) - 65;
            	char orientation =  parsedInput[1].charAt(0);
            	if(orientation == 'A') orientation = '>';
            		else orientation = 'V';
            	if(!B.isValid(word, row, col, orientation, scrabblePlayers.getPlayer(playersTurn.get()).getFrame())) {
            		StringBuilder str = new StringBuilder();
                    str.append(B.getError() + "\n" + "Review your input and try again.\n" + word + row + " " + col + " " + orientation);
                    textArea.appendText(str.toString());
            	} 
            	else if (B.isValid(word, row, col, orientation, scrabblePlayers.getPlayer(playersTurn.get()).getFrame())) {
            		B.placeWord(scrabblePlayers.getPlayer(playersTurn.get()), word, row, parsedInput[0].charAt(0), orientation);
            		StringBuilder str = new StringBuilder();


                    B.scoreCalculator(scrabblePlayers.getPlayer(playersTurn.get()), word, row, parsedInput[0].charAt(0), orientation);
                    playersTurn.getAndIncrement();
                    if(playersTurn.get() == numberOfPlayers){
                        playersTurn.set(0);
                    }
                    str.append("Next player's turn...\n" + scrabblePlayers.getPlayer(playersTurn.get()).getName() +"'s Turn\n" + scrabblePlayers.getPlayer(playersTurn.get()).getFrame() + "\n");
                    textArea.appendText(str.toString());
                    if(orientation == '>') {
                    	for(int i = col; i < col+word.length(); i++) {
                    		bt[row][i].setText(B.getSquare(row, i).getLetter());
                    	}
                    }
                    else {
                    	for(int i = row; i < row+word.length(); i++) {
                    		bt[i][col].setText(B.getSquare(i, col).getLetter());
                    	}
                    }

                    input.setText("");
                    switch(numberOfPlayers) {
                        case 2:
                            score1.setText(" {" + scrabblePlayers.getPlayer(0).getScore() + "}");
                            score2.setText(" {" + scrabblePlayers.getPlayer(1).getScore() + "}");
                            break;
                        case 3:
                            score1.setText(" {" + scrabblePlayers.getPlayer(0).getScore() + "}");
                            score2.setText(" {" + scrabblePlayers.getPlayer(1).getScore() + "}");
                            score3.setText(" {" + scrabblePlayers.getPlayer(2).getScore() + "}");
                            break;
                        case 4:
                            score1.setText(" {" + scrabblePlayers.getPlayer(0).getScore() + "}");
                            score2.setText(" {" + scrabblePlayers.getPlayer(1).getScore() + "}");
                            score3.setText(" {" + scrabblePlayers.getPlayer(2).getScore() + "}");
                            score4.setText(" {" + scrabblePlayers.getPlayer(3).getScore() + "}");
                            break;
                    }
            	}

            }
        });

        rootPane.getChildren().addAll(gridPane);

        window.setTitle("Scrabble");
        window.setScene(scene1);
        window.show();

    }
}
