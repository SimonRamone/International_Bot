package Logic;

import java.util.Scanner;

public class Board {
    public static int boardSize = 15;
    public static int tileStart = 7;
    
    public static int wordsOnBoard;

    public BoardSquare[][] scrabbleBoard = new BoardSquare[boardSize][boardSize];


    public Board(){
        initBoard();
        wordsOnBoard = 0;
    }

    public void initBoard(){
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                scrabbleBoard[i][j] = BoardSquare.normalSquare();
            }
        }

        // 24 squares contains the double letter multiplier
        scrabbleBoard[3][0] = BoardSquare.doubleLetterScore();
        scrabbleBoard[11][0] = BoardSquare.doubleLetterScore();
        scrabbleBoard[6][2] = BoardSquare.doubleLetterScore();
        scrabbleBoard[8][2] = BoardSquare.doubleLetterScore();
        scrabbleBoard[0][3] = BoardSquare.doubleLetterScore();
        scrabbleBoard[7][3] = BoardSquare.doubleLetterScore();
        scrabbleBoard[14][3] = BoardSquare.doubleLetterScore();
        scrabbleBoard[2][6] = BoardSquare.doubleLetterScore();
        scrabbleBoard[6][6] = BoardSquare.doubleLetterScore();
        scrabbleBoard[8][6] = BoardSquare.doubleLetterScore();
        scrabbleBoard[12][6] = BoardSquare.doubleLetterScore();
        scrabbleBoard[3][7] = BoardSquare.doubleLetterScore();
        scrabbleBoard[11][7] = BoardSquare.doubleLetterScore();
        scrabbleBoard[2][8] = BoardSquare.doubleLetterScore();
        scrabbleBoard[6][8] = BoardSquare.doubleLetterScore();
        scrabbleBoard[8][8] = BoardSquare.doubleLetterScore();
        scrabbleBoard[12][8] = BoardSquare.doubleLetterScore();
        scrabbleBoard[0][11] = BoardSquare.doubleLetterScore();
        scrabbleBoard[7][11] = BoardSquare.doubleLetterScore();
        scrabbleBoard[14][11] = BoardSquare.doubleLetterScore();
        scrabbleBoard[6][12] = BoardSquare.doubleLetterScore();
        scrabbleBoard[8][12] = BoardSquare.doubleLetterScore();
        scrabbleBoard[3][14] = BoardSquare.doubleLetterScore();
        scrabbleBoard[11][14] = BoardSquare.doubleLetterScore();

        // 12 squares contain the triple letter multiplier
        scrabbleBoard[1][5] = BoardSquare.tripleLetterScore();
        scrabbleBoard[1][9] = BoardSquare.tripleLetterScore();
        scrabbleBoard[5][1] = BoardSquare.tripleLetterScore();
        scrabbleBoard[5][5] = BoardSquare.tripleLetterScore();
        scrabbleBoard[5][9] = BoardSquare.tripleLetterScore();
        scrabbleBoard[5][13] = BoardSquare.tripleLetterScore();
        scrabbleBoard[9][1] = BoardSquare.tripleLetterScore();
        scrabbleBoard[9][5] = BoardSquare.tripleLetterScore();
        scrabbleBoard[9][9] = BoardSquare.tripleLetterScore();
        scrabbleBoard[9][13] = BoardSquare.tripleLetterScore();
        scrabbleBoard[13][5] = BoardSquare.tripleLetterScore();
        scrabbleBoard[1][9] = BoardSquare.tripleLetterScore();

        // 8 squares contain the triple word multiplier
        scrabbleBoard[0][0] = BoardSquare.tripleWordScore();
        scrabbleBoard[0][7] = BoardSquare.tripleWordScore();
        scrabbleBoard[0][14] = BoardSquare.tripleWordScore();
        scrabbleBoard[7][0] = BoardSquare.tripleWordScore();
        scrabbleBoard[7][14] = BoardSquare.tripleWordScore();
        scrabbleBoard[14][0] = BoardSquare.tripleWordScore();
        scrabbleBoard[14][7] = BoardSquare.tripleWordScore();
        scrabbleBoard[14][14] = BoardSquare.tripleWordScore();

        // 17 squares contain the double word multiplier
        scrabbleBoard[1][1] = BoardSquare.doubleWordScore();
        scrabbleBoard[1][13] = BoardSquare.doubleWordScore();
        scrabbleBoard[2][2] = BoardSquare.doubleWordScore();
        scrabbleBoard[2][12] = BoardSquare.doubleWordScore();
        scrabbleBoard[3][3] = BoardSquare.doubleWordScore();
        scrabbleBoard[3][11] = BoardSquare.doubleWordScore();
        scrabbleBoard[4][4] = BoardSquare.doubleWordScore();
        scrabbleBoard[4][10] = BoardSquare.doubleWordScore();
        scrabbleBoard[7][7] = BoardSquare.doubleWordScore();
        scrabbleBoard[10][4] = BoardSquare.doubleWordScore();
        scrabbleBoard[10][10] = BoardSquare.doubleWordScore();
        scrabbleBoard[11][3] = BoardSquare.doubleWordScore();
        scrabbleBoard[11][11] = BoardSquare.doubleWordScore();
        scrabbleBoard[12][2] = BoardSquare.doubleWordScore();
        scrabbleBoard[12][12] = BoardSquare.doubleWordScore();
        scrabbleBoard[13][1] = BoardSquare.doubleWordScore();
        scrabbleBoard[13][13] = BoardSquare.doubleWordScore();

    }

    public void resetBoard(){
        initBoard();
    }
    
    public void setSquare(int row, int col, LetterTile letter) {
    	scrabbleBoard[row][col].setTile(letter);
    }
    
    public void placeTile(Frame tileFrame, Board b, int row, int col) {
    	int index = -1;
    	char letter = '#';
    	
    	Scanner input = new Scanner(System.in);
    	
    	System.out.println("Your frame: ");
		System.out.println(tileFrame);
		System.out.println("Pick a tile from your frame: ");
		if(input.hasNextInt()) {
			index = input.nextInt() - 1;
			letter = tileFrame.getTile(index).getLetter();
		}
			else if (input.hasNext()) letter = input.next().toUpperCase().charAt(0);
			else {
				input.close();
				throw new IllegalArgumentException ("Invalid input.");
			}
		while(index > tileFrame.getSize() || !tileFrame.containsTile(letter)) {
			System.out.println("Tile does not exist! ");
			System.out.println("Pick a tile from your frame: ");
			if(input.hasNextInt()) index = input.nextInt() - 1;
				else if (input.hasNext()) letter = input.next().toUpperCase().charAt(0);
				else {
					input.close();
					throw new IllegalArgumentException ("Invalid input.");
				}
		
		}
		
		setSquare(row, col, tileFrame.getTile(index));
		tileFrame.removeTile(index);
		PrintBoard(b);
		input.close();
    }
    
    public void placeWord(SimplePlayer player, Board b) {
    	int row, col;
    	Boolean submit = false;
    	String rightOrDown = "";
    	Scanner input = new Scanner(System.in);
    	System.out.println("Do you want to skip your turn? Enter 'YES' or 'NO':");
	    if(input.next().toUpperCase() == "NO") {
	    	System.out.println("Enter row: ");
			if(input.hasNextInt()) {
				row = input.nextInt();
			}	else {
					input.close();
					throw new IllegalArgumentException ("Invalid input.");
				}
			
			System.out.println("Enter column: ");
			if(input.hasNext()) {
				col = input.next().toUpperCase().charAt(0) - 65;
			}	else {
					input.close();
					throw new IllegalArgumentException ("Invalid input.");
				}
			
	    	while(!scrabbleBoard[row][col].isEmpty() || row > boardSize || row < 0 || col < 0 || col > boardSize) {
	    		System.out.println("Square is occupied or out of bounds!");
	    		System.out.println("Enter row: ");
				if(input.hasNextInt()) {
					row = input.nextInt();
				}	else {
						input.close();
						throw new IllegalArgumentException ("Invalid input.");
					}
				
				System.out.println("Enter column: ");
				if(input.hasNext()) {
					col = input.next().toUpperCase().charAt(0) - 65;
				}	else {
						input.close();
						throw new IllegalArgumentException ("Invalid input.");
					}
	    	}
	    	
			placeTile(player.getFrame(), b, row, col);
			System.out.println("Enter 'SUBMIT' to submit your word.");
			if(input.next().toUpperCase() == "SUBMIT") submit = true;
	    	if(!submit) {
	    		System.out.println("Select next tile's position. Enter 'RIGHT' or 'DOWN'.");
	    		rightOrDown = input.next().toUpperCase();
	    		if(rightOrDown == "RIGHT" && scrabbleBoard[row][col+1].isEmpty() && col+1 < boardSize) {
	    			col++;
	    			placeTile(player.getFrame(), b, row, col);
	    		}
	    		if(rightOrDown == "DOWN" && scrabbleBoard[row+1][col].isEmpty() && row+1 < boardSize) {
	    			row++;
	    			placeTile(player.getFrame(), b, row, col);
	    		}	
	    		if(rightOrDown == "RIGHT" && !scrabbleBoard[row][col+1].isEmpty() && col+2 < boardSize) {
	    			col += 2;
	    			placeTile(player.getFrame(), b, row, col);
	    		}
	    		if(rightOrDown == "DOWN" && !scrabbleBoard[row+1][col].isEmpty() && row+2 < boardSize) {
	    			row += 2;
	    			placeTile(player.getFrame(), b, row, col);
	    		}
	    	}
			
	    	System.out.println("Enter 'SUBMIT' to submit your word.");
			if(input.next().toUpperCase() == "SUBMIT") submit = true;
			
			if(rightOrDown == "DOWN") {
				while(!player.getFrame().isEmpty() && row < boardSize && col < boardSize && !submit) {
		    		if(scrabbleBoard[row+1][col].isEmpty() && row+1 < boardSize) {
		    			row++;
		    			placeTile(player.getFrame(), b, row, col);
		    		}	
		    		else if(!scrabbleBoard[row+1][col].isEmpty() && row+2 < boardSize) {
		    			row += 2;
		    			placeTile(player.getFrame(), b, row, col);
		    		}
		    		System.out.println("Enter 'SUBMIT' to submit your word.");
		    		if(input.next().toUpperCase() == "SUBMIT") submit = true;
				}
			}
	    	
			if(rightOrDown == "RIGHT") {
				while(!player.getFrame().isEmpty() && row < boardSize && col < boardSize && !submit) {
					if(rightOrDown == "RIGHT" && scrabbleBoard[row][col+1].isEmpty() && col+1 < boardSize) {
		    			col++;
		    			placeTile(player.getFrame(), b, row, col);
		    		}
					else if(rightOrDown == "RIGHT" && !scrabbleBoard[row][col+1].isEmpty() && col+2 < boardSize) {
		    			col += 2;
		    			placeTile(player.getFrame(), b, row, col);
		    		}
		    		System.out.println("Enter 'SUBMIT' to submit your word.");
		    		if(input.next().toUpperCase() == "SUBMIT") submit = true;
				}
			}
	    	wordsOnBoard++;
			input.close();
    	}
    	
    	else input.close();
    }

    public BoardSquare getSquare(int row, int col){
        return scrabbleBoard[row][col];
    }

    public static void main(String[] args) {
        Board b = new Board();
        PrintBoard(b);
    }

    public static void PrintBoard(Board b){
        int x =65;
        for (int j=0;j<boardSize;j++){
            int ascii = x + j;
            System.out.print("{" + (char)ascii + " } ");
        }
        System.out.println();
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                System.out.print("|");
                if (i==7 && j==7){System.out.print("★");}
                else if (b.scrabbleBoard[i][j].getLetterMultiplier() == 2){
                    System.out.print("DL");     //DL indicates double letter score
                }
                else if (b.scrabbleBoard[i][j].getLetterMultiplier() == 3){
                    System.out.print("TL");     //TL indicates Triple letter score
                }
                else if (b.scrabbleBoard[i][j].getWordMultiplier() == 3){
                    System.out.print("TW");     //TW indicates Triple word score
                }
                else if (b.scrabbleBoard[i][j].getWordMultiplier() == 2){
                    System.out.print("DW");    //DW indicates Double word score
                }else {
                    System.out.print("  ");
                }
                System.out.print("| ");
            }
            System.out.println("-"+i);
        }
    }

}
