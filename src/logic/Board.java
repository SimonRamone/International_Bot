package logic;

import java.util.ArrayList;
import java.util.Random;

public class Board {
	public static final int BOARD_SIZE = 15;
	public static boolean firstWord = true;
	public static int wordsOnBoard = 0;
	public static int errorCode = 0;
	public Boolean bingoChecker = false;


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
	
	public void resetFirstWord() {
		firstWord = true;
	}
	
	public int wordsOnBoard() {
		return wordsOnBoard;
	}
	
	public int AddWordsOnBoard() {
		return wordsOnBoard++;
	}
	
	public int RemoveWordsOnBoard() {
		return wordsOnBoard--;
	}

	public boolean isEmpty(int row, int col){		//if board[i][j] has no tiles on it
		if(scrabbleBoard[row][col].getLetterTile() != null){
			return false;
		}
		return true;
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
			case 6:
				return "You don't have enough blank tiles!";
			case 7:
				return "You must place more than one tile!";
			default:
				return "Error code invalid!";
		}
	}
	
	public int getErrorCode() {
		return errorCode;
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
			if(countBlankTilesInWord(word) > countWildCardInFrame(tileFrame)) {
				errorCode = 6;
				return false;
			}
			if(word.length() <= 1) {
				errorCode = 7;
				return false;
			}
		}
		else if(!isFirstWord()) {

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
			if(countBlankTilesInWord(word) > countWildCardInFrame(tileFrame)) {
				errorCode = 6;
				return false;
			}
			if(word.length() <= 1) {
				errorCode = 7;
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
			if(getLettersAlreadyOnBoard(word, row, col, orientation).trim().length() != 0) {
				return true;	//If user input word that uses letters already on board return true
			}

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
		Frame tempFrame = new Frame();
		System.out.println("temp frame1: " + tempFrame);

		// make a temporary frame to hold player's tiles
		for(int i = 0; i < tileFrame.getSize(); i++){
			tempFrame.add(tileFrame.getTile(i));
		}			
		System.out.println("temp frame2: " + tempFrame);
		// used to check if there are enough tiles for the word
		if(tileFrame.getSize() < word.length()){
			System.out.println("temp frameempty: " + tempFrame);
			return false;
		}
		
		char[] wordChars = word.toCharArray();
		try {
			for(int i = 0; i < word.length(); i++) {
				System.out.println(LetterTile.getLetterTile(wordChars[i]).getLetter());
				tempFrame.removeTile(wordChars[i]);
				System.out.println("temp frame3: " + tempFrame);
			}
		} catch (Exception e) {
			return false;
		}
		


		return true;
	}
	
	public int countBlankTilesInWord(String word) {
		if(!word.contains("_")) return 0;
		int n = 0;
		for (int i = 0; i < word.length(); i++) {
			if(word.charAt(i) == '_') n++;
		}
		return n;
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
	
	public String replaceBlankTilesInWord (String word, String blankTileLetters) {
		char[] wordChars = word.toCharArray();
		if(blankTileLetters.length() == 1) { 
			for(int i = 0; i < word.length(); i++) {
				if(word.charAt(i) == '_') {
					wordChars[i] = blankTileLetters.charAt(0);
					break;
				}
			}
		}
		int index = 0;
		if(blankTileLetters.length() == 2) {
			for(int i = 0; i < word.length(); i++) {
				if(word.charAt(i) == '_') {
					wordChars[i] = blankTileLetters.charAt(index);
					index++;
				}
				if(index == 2) break;
			}
		}
		return String.valueOf(wordChars);
	}

	//Returns string which contains the letters that are one the board in the tiles that user wants to place their word
	public String getLettersAlreadyOnBoard(String userWord, int row, int col, char orientation) {
		String lettersOnBoard = "";
		if(orientation == 'v' || orientation == 'V') {
			for (int i = 0; i < userWord.length(); i++) {
				if(!isEmpty(row, col)) lettersOnBoard += getSquare(row, col).getLetterTile().getLetter(); //if the square is not empty, add the letter to the string for return
				else lettersOnBoard += " ";	//add space for empty squares
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
		System.out.println("tiles in hand before refill: " + player.getFrame().getSize());
		bingoChecker = false;
		if(player.getFrame().getSize() < 7){
			if(player.getFrame().getSize() == 0){bingoChecker = true;}
//			player.getFrame().refillFrame();
		}

	}


	public BoardSquare getSquare(int row, int col){
		return scrabbleBoard[row][col];
	}

	public void scoreCalculator(SimplePlayer player, String word, int row, int col, char orientation){
		System.out.println("beginning tiles in frame of calc" + player.getFrame().getSize());
		int wordScore =0;
		int multiplierCounter = 0;	//checks for which word multiplier case it will be.

		if(orientation == '>') {		//across, horizontal calculations
			for(int i = col; i < col+word.length(); i++) {
				wordScore += ((getSquare(row, i).getLetterTile().getScore()) * getSquare(row, i).getLetterMultiplier());
				System.out.println((getSquare(row, i).getLetterTile().getScore()) + "current word score" + wordScore + "word mul" +getSquare(row, i).getWordMultiplier());
				if(getSquare(row, i).getWordMultiplier()==2){
					multiplierCounter += 2;
				}else if(getSquare(row, i).getWordMultiplier() ==3){
					multiplierCounter += 3;

				}
			}
		}
		else if(orientation == 'v' || orientation == 'V'){
			for(int j = row; j < row+word.length(); j++) {
				wordScore += ((getSquare(j, col).getLetterTile().getScore()) * getSquare(j, col).getLetterMultiplier());
				System.out.println((getSquare(j, col).getLetterTile().getScore())+ "current word score" + wordScore + "word mul" +getSquare(j, col).getWordMultiplier());
				if(getSquare(j,col).getWordMultiplier()==2) {
					multiplierCounter += 2;
				}
				else if(getSquare(j, col).getWordMultiplier() ==3){
					multiplierCounter += 3;
				}
			}
		}
		System.out.println("multiplierCounter is :" + multiplierCounter);

		multiplier(multiplierCounter, player, wordScore);
		System.out.println(" word score for this round: " + wordScore);

		if(bingoChecker == true){	//case of bingo: all 7 tiles are used.
			player.score += 50;
		}
		System.out.println("Player current score: " + player.score);

	}

	public void multiplier(int multiplierCounter, SimplePlayer player, int wordScore){
		int wordMultiplier = 1;
		switch (multiplierCounter){
			case 2:
				wordMultiplier = 2;
				player.score += wordScore*wordMultiplier;
				break;

			case 3:
				wordMultiplier = 3;
				player.score += wordScore*wordMultiplier;
				break;
			case 4:				//when the word has two double word score multiplier, the score of the word is doubled, and that score is then doubled again.
				wordScore *= 2;
				wordScore *= 2;
				player.score += wordScore;
				break;
			case 6:			//when the word has two triple word score multiplier, the score of the word is tripled, and that score is then tripled again.
				wordScore *= 3;
				wordScore *= 3;
				player.score += wordScore;
				break;
			case 5:		//this is the case for if word covers a double and a triple letter word multiplier. I didn't find the rule for this online
				wordScore *= 2;		//but based on the previous cases i presumed that it would be (wordScore *2 ) *3;
				wordScore *= 3;
				player.score += wordScore;
				break;
			default:
				player.score += wordScore*wordMultiplier;
		}
	}

	public void additionalWords(SimplePlayer player, String word, int row, int col, char orientation, String existingLetters){
		int wordScore = 0;
		int multiplierCounter = 0;
		int playersTilePointer = 0;
		int iterator;
		if(orientation == '>') {
			for (int i = col; i < col + word.length(); i++) {
				// if it is player's tile, add the score of the additional word
				if (existingLetters.charAt(playersTilePointer) == ' ') {
					iterator = row;
					wordScore = 0;
					playersTilePointer++;
					// if word formed is below and the character is the first letter of the additional word
					if (!getSquare(row + 1, i).isEmpty() && getSquare(row - 1, i).isEmpty()) {
						while (!getSquare(iterator, i).isEmpty()) {
							iterator++;
						}
						// get score of new word and add to player score
						for(int j = row; j < iterator; j++) {
							wordScore += ((getSquare(j, i).getLetterTile().getScore()) * getSquare(j, i).getLetterMultiplier());
							if(getSquare(j, i).getWordMultiplier()==2){
								multiplierCounter += 2;
							}else if(getSquare(j, i).getWordMultiplier() ==3){
								multiplierCounter += 3;
							}
						}
						multiplier(multiplierCounter, player, wordScore);
					}
					// if the character in the coordinate is not the first letter of the additional word
					else if(!getSquare(row - 1, i).isEmpty()){
						iterator = row;
						wordScore = 0;
						// iterate to start of the word
						while (!getSquare(iterator, i).isEmpty()) {
							iterator--;
						}
						int newWordFirstLetter = iterator + 1;
						iterator++;
						// find the length of the word
						while (!getSquare(iterator, i).isEmpty()) {
							iterator++;
						}
						// get score of new word and add to player score
						for(int j = newWordFirstLetter; j < iterator; j++) {
							wordScore += ((getSquare(j, i).getLetterTile().getScore()) * getSquare(j, i).getLetterMultiplier());
							if(getSquare(j,i).getWordMultiplier()==2) {
								multiplierCounter += 2;
							}
							else if(getSquare(j, i).getWordMultiplier() ==3){
								multiplierCounter += 3;
							}
						}
						multiplier(multiplierCounter, player, wordScore);
					}
				}
				else{
					playersTilePointer++;
				}
			}
		}
		else if(orientation == 'v' || orientation == 'V') {
			for (int i = row; i < row + word.length(); i++) {
				if (existingLetters.charAt(playersTilePointer) == ' ') {
					playersTilePointer++;
					// if it is the first letter of the new word
					if (!getSquare(i, col + 1).isEmpty() && getSquare(i, col - 1).isEmpty()) {
						iterator = col;
						while (!getSquare(iterator, i).isEmpty()) {
							iterator++;
						}
						// get score of new word and add to player score
						for(int j = col; j < iterator; j++) {
							wordScore += ((getSquare(i, j).getLetterTile().getScore()) * getSquare(i, j).getLetterMultiplier());
							if(getSquare(i, j).getWordMultiplier()==2){
								multiplierCounter += 2;
							}else if(getSquare(i, j).getWordMultiplier() ==3){
								multiplierCounter += 3;
							}
						}
						multiplier(multiplierCounter, player, wordScore);
					}
					// if the character in the coordinate is not the first letter of the additional word
					else if (!getSquare(i, col - 1).isEmpty()) {
						// start from current row
						iterator = col;
						// iterate to start of the word
						while (!getSquare(iterator, i).isEmpty()) {
							iterator--;
						}
						int newWordFirstLetter = iterator + 1;
						// form the word in array list
						while (!getSquare(iterator, i).isEmpty()) {
							iterator++;
						}
						// get score of new word and add to player score
						for(int j = newWordFirstLetter; j < iterator; j++) {
							wordScore += ((getSquare(i, j).getLetterTile().getScore()) * getSquare(i, j).getLetterMultiplier());
							if(getSquare(i, j).getWordMultiplier()==2){
								multiplierCounter += 2;
							}else if(getSquare(i, j).getWordMultiplier() ==3){
								multiplierCounter += 3;
							}
						}
						multiplier(multiplierCounter, player, wordScore);
					}
				}
				else{
					playersTilePointer++;
				}
			}
		}
	}

	// a challenge function by random
	public boolean challengeWord(){
		Random randomNum = new Random();
		int coinflip = randomNum.nextInt(2);
		if(coinflip == 0){
			return true;
		}
		else{
			return false;
		}
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