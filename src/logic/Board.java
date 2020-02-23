package logic;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    public static int boardSize = 15;
    
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

    public boolean isFirstTurn(int turn){
    	if(turn == 1){
    		return true;
		}

    	return false;
	}

    public boolean isEmpty(int row, int col){
    	if(scrabbleBoard[row][col].getLetterTile() == null){
    		return true;
		}
    	else{
			return false;
		}
	}

	public boolean isBound(int row, int col){
    	if(row > 14 || row < 0){
    		return false;
		}

    	if(col > 14 || col < 0){
    		return false;
		}

    	return true;
	}

	public boolean isValid(int wordLength, int row, int col, char orientation){

    	if(orientation == '>'){
			for(int j = col ; j < wordLength; j++){
				if (!isEmpty(row, j)) {
					return false;
				}
			}
		}
    	else{
			for(int i = row; i < wordLength; i++){
				if (!isEmpty(i, col)) {
					return false;
				}
			}
		}
		return true;

	}

	public boolean hasWildCardInFrame(Frame tileFrame){
    	boolean hasWildCard = false;
    	for(int i = 0; i < tileFrame.getSize(); i++){
			if (tileFrame.containsTile(LetterTile.tileBlank)) {
				hasWildCard = true;
			}
		}
    	return hasWildCard;
	}

	public int countWildCardInFrame(Frame tileFrame){
    	int count = 0;
		for(int i = 0; i < tileFrame.getSize(); i++){
			if (tileFrame.getTile(i) == LetterTile.tileBlank) {
				count++;
			}
		}
		return count;
	}

	public boolean wordCheck(String word, Frame tileFrame) {
		ArrayList<LetterTile> tempFrame = new ArrayList<LetterTile>();

		for(int i = 0; i < tileFrame.getSize(); i++){
			tempFrame.add(tileFrame.getTile(i));
		}

		boolean valid = true;
		int wildCardCount = countWildCardInFrame(tileFrame);
		int foundTilesCount = 0;

		StringBuilder userWord = new StringBuilder(word);

		if(hasWildCardInFrame(tileFrame)){
			for (int i = 0; i < userWord.length(); i++) {
				for(int j = 0; j < tempFrame.size(); j++){
					if (userWord.charAt(i) == tempFrame.get(j).getLetter()) {
						tempFrame.remove(j);
						foundTilesCount++;
						break;
					}
				}
			}

			if(foundTilesCount + wildCardCount != userWord.length()){
				valid = false;
			}
		}
		else{
			for (int i = 0; i < userWord.length(); i++) {
				for(int j = 0; j < tempFrame.size(); j++){
					if (userWord.charAt(i) == tempFrame.get(j).getLetter()) {
						tempFrame.remove(j);
						foundTilesCount++;
						break;
					}
				}
			}

			if(foundTilesCount != userWord.length()){
				valid = false;
			}
		}

		return valid;
	}

	public Frame wildCardSetLetter(Frame tileFrame, String word){
		ArrayList<LetterTile> tempFrame = new ArrayList<LetterTile>();
		for(int i = 0; i < tileFrame.getSize(); i++){
			tempFrame.add(tileFrame.getTile(i));
		}

		StringBuilder userWord = new StringBuilder(word);

		for (int i = 0; i < tileFrame.getSize(); i++) {
			for(int j = 0; j < userWord.length(); j++){
				if(tileFrame.getTile(i).getLetter() == userWord.charAt(j)){
					userWord.deleteCharAt(j);
					break;
				}
			}
		}

		for(int i = 0; i < countWildCardInFrame(tileFrame); i++){
			tileFrame.getTile('_').setLetter(userWord.charAt(i));
		}

		return tileFrame;
	}

	
	public String getLettersAlreadyOnBoard(String userWord, int row, int col, char orientation) {
		String lettersOnBoard = "";
		if(orientation == 'v' || orientation == 'V') {
			for (int i = 0; i < userWord.length(); i++) {
				if(!isEmpty(row, col)) lettersOnBoard += getSquare(row, col).getLetterTile().getLetter();
				else lettersOnBoard += "*";
				row++;
			}
		}
		else {
			for (int i = 0; i < userWord.length(); i++) {
				if(!isEmpty(row, col)) lettersOnBoard += getSquare(row, col).getLetterTile().getLetter();
				else lettersOnBoard += " ";
				col++;
			}
		}
		System.out.println(lettersOnBoard);
		return lettersOnBoard;
	}
	
	public String removeRedundantLettersFromWord(String userWord, int row, int col, char orientation) {
		String lettersOnBoard = getLettersAlreadyOnBoard(userWord, row, col, orientation);
		StringBuilder word = new StringBuilder(userWord);
		for (int i = 0; i < lettersOnBoard.length(); i++) {
			if(userWord.charAt(i) == lettersOnBoard.charAt(i)) word.deleteCharAt(i);
		}
		return word.toString();
	}

	public void placeWord(SimplePlayer player, String userWord, int row, char colChar, char orientation){
		int colInteger = colChar - 65; // change from ASCII to integer
		System.out.println(player.getFrame());
    	if(!isBound(row, colInteger)){
    		System.out.print("Please select coordinates that are within the scrabble board"); // throw an exception?
		}
    	if(!isValid(userWord.length(), row, colInteger, orientation)){
    		System.out.println("Not enough space to fit this word here. Try again"); // throw an exception?
		}
    	
    	userWord = removeRedundantLettersFromWord(userWord, row, colInteger, orientation);
    	System.out.println(userWord);
    	
    	if(!wordCheck(userWord, player.getFrame())){
    		System.out.println("Please only use tiles from your frame"); // throw an exception?
		}
    	else{
			if(hasWildCardInFrame(player.getFrame())){
				wildCardSetLetter(player.getFrame(), userWord);
			}
			System.out.println(player.getFrame());
    		if(orientation == 'v' || orientation == 'V') {
    			for (int i = 0; i < userWord.length(); i++) {
    				if(isEmpty(row, colInteger)) {
    					setSquare(row, colInteger, player.getFrame().getTile(userWord.charAt(i)));
    					player.getFrame().removeTile(player.getFrame().getTile(userWord.charAt(i)));
    					row++;
    				}
    				else {
    					setSquare(row+1, colInteger, player.getFrame().getTile(userWord.charAt(i)));
    					player.getFrame().removeTile(player.getFrame().getTile(userWord.charAt(i)));
    					row += 2;
    				}
    			}
    		}
    		else {
    			for (int i = 0; i < userWord.length(); i++) {
    				if(isEmpty(row, colInteger)) {
    					setSquare(row, colInteger, player.getFrame().getTile(userWord.charAt(i)));
    					player.getFrame().removeTile(player.getFrame().getTile(userWord.charAt(i)));
    					colInteger++;
    				}
    				else {
    					setSquare(row, colInteger+1, player.getFrame().getTile(userWord.charAt(i)));
    					player.getFrame().removeTile(player.getFrame().getTile(userWord.charAt(i)));
    					colInteger += 2;
    				}
    			}
    		}
		}
    	System.out.println(player.getFrame());

    	if(player.getFrame().getSize() < 7){
			player.getFrame().refillFrame();
		}

    }


    public BoardSquare getSquare(int row, int col){
        return scrabbleBoard[row][col];
    }

    public static void main(String[] args) {
    	
    }

    public void PrintBoard(){
        int x =65;
        for (int j=0;j<boardSize;j++){
            int ascii = x + j;
            System.out.print("{" + (char)ascii + " } ");
        }
        System.out.println();
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                System.out.print("|");
                if (i==7 && j==7 && scrabbleBoard[7][7].isEmpty()){System.out.print(" *");}
                else if (scrabbleBoard[i][j].getLetterMultiplier() == 2 && scrabbleBoard[i][j].isEmpty()){
                    System.out.print("DL");     //DL indicates double letter score
                }
                else if (scrabbleBoard[i][j].getLetterMultiplier() == 3 && scrabbleBoard[i][j].isEmpty()){
                    System.out.print("TL");     //TL indicates Triple letter score
                }
                else if (scrabbleBoard[i][j].getWordMultiplier() == 3 && scrabbleBoard[i][j].isEmpty()){
                    System.out.print("TW");     //TW indicates Triple word score
                }
                else if (scrabbleBoard[i][j].getWordMultiplier() == 2 && scrabbleBoard[i][j].isEmpty()){
                    System.out.print("DW");    //DW indicates Double word score
                }
                else if (!scrabbleBoard[i][j].isEmpty()){
                    System.out.print(" " + scrabbleBoard[i][j].getLetterTile().getLetter());    //DW indicates Double word score
                }
                else {
                    System.out.print("  ");
                }
                System.out.print("| ");
            }
            System.out.println("-"+i);
        }
    }

}
