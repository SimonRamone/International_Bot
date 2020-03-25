package logic;

import java.util.ArrayList;

public class Board {
	public static final int BOARD_SIZE = 15;
	public static boolean firstWord = true;
	public static int errorCode = 0;

	public BoardSquare[][] scrabbleBoard = new BoardSquare[BOARD_SIZE][BOARD_SIZE];

	public Board(){
		initBoard();
	}

	public void initBoard(){
		for(int i = 0; i < BOARD_SIZE; i++){
			for(int j = 0; j < BOARD_SIZE; j++){
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
		scrabbleBoard[13][9] = BoardSquare.tripleLetterScore();
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

	public boolean isFirstWord(){
		return firstWord;
	}

	public void setFirstWord() {
		firstWord = false;
	}

	public boolean isEmpty(int row, int col){		//if board[i][j] has no tiles on it
		if(scrabbleBoard[row][col].getLetterTile() == null){
			return true;
		}
		else{
			return false;
		}
	}

	// check if coordinates are in bound of the board
	public boolean isBound(int row, int col){
		if(row > 14 || row < 0){
			return false;
		}

		if(col > 14 || col < 0){
			return false;
		}

		return true;
	}

	// check if word can fit in the board
	public boolean isBound(int row, int col, char orientation, int wordLength){
		if(row > 14 || row < 0){
			return false;
		}
		if(col > 14 || col < 0){
			return false;
		}
		if(orientation == '>'){
			if( (col + wordLength - 1) > 14){
				return false;
			}
		}
		if(orientation == 'v' || orientation == 'V'){
			if( (row + wordLength - 1) > 14){
				return false;
			}
		}

		return true;
	}

	public String getError() {
		switch(errorCode) {
			case 0:
				return "No errors found.";
			case 1:
				return "Word is not within the bounds of the board!";
			case 2:
				return "Word contains atleast one letter that is not in your frame or is not on the board!";
			case 3:
				return "Word must be placed on center star!";
			case 4:
				return "Word conflicts with letters that are already on the board!";
			case 5:
				return "Word must connect with atleast one letter that is already on the board!";
			default:
				return "Error code invalid!";
		}
	}

	//Checks whether user input is valid
	public boolean isValid(String word, int row, int col, char orientation, Frame tileFrame){
		if(isFirstWord()) {
			if(!isBound(row, col, orientation, word.length())) {
				errorCode = 1;
				return false;
			}
			if(!wordCheck(word, tileFrame)) {
				errorCode = 2;
				return false;
			}
			if(!inCentreOfBoard(word, row, col, orientation)) {
				errorCode = 3;
				return false;
			}
		}
		if(!isFirstWord()) {
			if(!isBound(row, col, orientation, word.length())) {
				errorCode = 1;
				return false;
			}
			if(conflictsWithExistingLetters(word, getLettersAlreadyOnBoard(word, row, col, orientation))) {
				errorCode = 4;
				return false;			
			}
			if(!connectsWithOtherWords(word, row, col, orientation)) {
				errorCode = 5;
				return false;
			}
			word = removeRedundantLettersFromWord(word, row, col, orientation);
			if(!wordCheck(word, tileFrame)) {
				errorCode = 2;
				return false;
			}
		}
		errorCode = 0;
		return true;
	}

	public boolean hasWildCardInFrame(Frame tileFrame){		//if player's hand has blank tile
		boolean hasWildCard = false;
		for(int i = 0; i < tileFrame.getSize(); i++){		//tileFrame == Arraylist for which contains drawn tiles.
			if (tileFrame.containsTile(LetterTile.tileBlank)) {
				hasWildCard = true;
			}
		}
		return hasWildCard;
	}

	public int countWildCardInFrame(Frame tileFrame){// #of blank tiles
		int count = 0;
		for(int i = 0; i < tileFrame.getSize(); i++){
			if (tileFrame.getTile(i) == LetterTile.tileBlank) {
				count++;
			}
		}
		return count;
	}

	public boolean conflictsWithExistingLetters(String word, String existingLetters) {
		for(int i = 0; i < word.length(); i++) {
			if(existingLetters.charAt(i) != ' ' && word.charAt(i) != existingLetters.charAt(i)) return true;
		}
		return false;
	}

	public boolean inCentreOfBoard(String word, int row, int col, char orientation) {
		if(orientation == '>') {
			for(int i = 0; i < word.length(); i++) {
				if(row == 7 && col == 7) return true;
				col++;
			}
		}
		if(orientation == 'V' || orientation == 'v') {
			for(int i = 0; i < word.length(); i++) {
				if(row == 7 && col == 7) return true;
				row++;
			}
		}
		return false;
	}

	public boolean connectsWithOtherWords(String word, int row, int col, char orientation) {
		if(isBound(row, col, orientation, word.length())) {
			if(!getLettersAlreadyOnBoard(word, row, col, orientation).isEmpty()) return true;	//If user input word that uses letters already on board return true
			
			//Checks for letters above and below input word
			if(orientation == '>') {
				for(int i = 0; i < word.length(); i++) {
					if(row > 0 && !isEmpty(row-1, col)) return true;
					if (row < 14 && !isEmpty(row+1, col)) return true;
					col++;
				}
			}
			
			//Checks for letters on right and left side of input word
			if(orientation == 'v' || orientation == 'V') {
				for(int i = 0; i < word.length(); i++) {
					if(col > 0 && !isEmpty(row, col-1)) return true;
					if (col < 14 && !isEmpty(row, col+1)) return true;
					row++;
				}
			}
			return false;
		}
		return false;
	}

	// check if the frame contains the required tiles for the word
	public boolean wordCheck(String word, Frame tileFrame) {
		ArrayList<LetterTile> tempFrame = new ArrayList<LetterTile>();

		System.out.println("Word Check: " + word);

		// make a temporary frame to hold player's tiles
		for(int i = 0; i < tileFrame.getSize(); i++){
			tempFrame.add(tileFrame.getTile(i));
		}

		boolean valid = true;
		int wildCardCount = countWildCardInFrame(tileFrame);
		int foundTilesCount = 0;

		StringBuilder userWord = new StringBuilder(word);	//SB stores word in an array.

		// if there is wildcard in the player's frame, increment the foundTilesCount
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

			// used to check if there are enough tiles
			if(foundTilesCount + wildCardCount < userWord.length()){
				valid = false;
			}
		}
		else{
			// else when there is no wildcard in the player's frame
			for (int i = 0; i < userWord.length(); i++) {
				for(int j = 0; j < tempFrame.size(); j++){
					if (userWord.charAt(i) == tempFrame.get(j).getLetter()) {
						tempFrame.remove(j);
						foundTilesCount++;
						break;
					}
				}
			}

			// used to check if there are enough tiles for the word
			if(foundTilesCount < userWord.length()){
				valid = false;
			}
		}

		return valid;
	}



	public Frame wildCardSetLetter(Frame tileFrame, String word){	//sets blank tile to necessary letter
		ArrayList<LetterTile> tempFrame = new ArrayList<LetterTile>();
		for(int i = 0; i < tileFrame.getSize(); i++){		//adds player hand into a frame
			tempFrame.add(tileFrame.getTile(i));
		}

		StringBuilder userWord = new StringBuilder(word);	//stores word in an array

		for (int i = 0; i < tileFrame.getSize(); i++) {
			for(int j = 0; j < userWord.length(); j++){
				if(tileFrame.getTile(i).getLetter() == userWord.charAt(j)){
					userWord.deleteCharAt(j);			//if hand has correct letter from word, that letter in word array is removed
					break;
				}
			}
		}

		for(int i = 0; i < countWildCardInFrame(tileFrame); i++){ //sets the blank tile to the required letter for the word
			tileFrame.getTile('_').setLetter(userWord.charAt(i));
		}

		return tileFrame;
	}

	//Returns string which contains the letters that are one the board in the tiles that user wants to place their word
	public String getLettersAlreadyOnBoard(String userWord, int row, int col, char orientation) {
		String lettersOnBoard = "";
		if(orientation == 'v' || orientation == 'V') {
			for (int i = 0; i < userWord.length(); i++) {
				if(!isEmpty(row, col)) lettersOnBoard += getSquare(row, col).getLetterTile().getLetter();	//if the square is not empty, add it to the string for return
				else lettersOnBoard += " ";		//add space for empty squares
				row++;
			}
		}
		//when horizontal word
		else {
			for (int i = 0; i < userWord.length(); i++) {
				if(!isEmpty(row, col)) lettersOnBoard += getSquare(row, col).getLetterTile().getLetter();
				else lettersOnBoard += " ";
				col++;
			}
		}
		System.out.println("Letters on board: " + lettersOnBoard);
		return lettersOnBoard;
	}
	
	//removes the letters that are on the board from the user input word
	public String removeRedundantLettersFromWord(String userWord, int row, int col, char orientation) {
		System.out.println("Word before remove: " + userWord);
		int letterIndex = 0;
		String lettersOnBoard = getLettersAlreadyOnBoard(userWord, row, col, orientation);
		StringBuilder word = new StringBuilder(userWord);		//new word without letters that are on board
		for (int i = 0; i < lettersOnBoard.length(); i++) {
			if(word.charAt(letterIndex) == lettersOnBoard.charAt(i)) {	//because users word gets shorter everytime you remove a letter a separate index for it must be used
				word.deleteCharAt(letterIndex);
			} else letterIndex++;
			System.out.println("Word WHILE remove: " + word.toString());
			System.out.println("LETTERINDEX: " + letterIndex);
		}
		System.out.println("Word after remove: " + word.toString());
		return word.toString();
	}

	public void placeWord(SimplePlayer player, String userWord, int row, char colChar, char orientation){
		int colInteger = colChar - 65; // change from ASCII to integer
		int wordLength = userWord.length();
		int letterIndex = 0;
		if(!isValid(userWord, row, colInteger, orientation, player.getFrame())){
			throw new IllegalArgumentException("1 or more errors with input!");
		}
		else{
			if(!isFirstWord()) userWord = removeRedundantLettersFromWord(userWord, row, colInteger, orientation);
			else setFirstWord();

			if(hasWildCardInFrame(player.getFrame())) wildCardSetLetter(player.getFrame(), userWord);

			if(orientation == 'v' || orientation == 'V') {
				for (int i = 0; i < wordLength; i++) {
					if(isEmpty(row, colInteger)) {
						setSquare(row, colInteger, player.getFrame().getTile(userWord.charAt(letterIndex)));
						player.getFrame().removeTile(player.getFrame().getTile(userWord.charAt(letterIndex)));
						row++;
						letterIndex++;	//only incremented when a letter is placed, to account for the letters that have been removed
					}
					else {
						row++;
					}
				}
			}
			if(orientation == '>'){
				for (int i = 0; i < wordLength; i++) {
					if(isEmpty(row, colInteger)) {
						setSquare(row, colInteger, player.getFrame().getTile(userWord.charAt(letterIndex)));
						player.getFrame().removeTile(player.getFrame().getTile(userWord.charAt(letterIndex)));
						colInteger++;
						letterIndex++;
					}
					else {
						colInteger++;
					}
				}
			}
		}

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
		for (int j=0;j<BOARD_SIZE;j++){
			int ascii = x + j;
			System.out.print("{" + (char)ascii + " } ");
		}
		System.out.println();
		for(int i = 0; i < BOARD_SIZE; i++){
			for(int j = 0; j < BOARD_SIZE; j++){
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