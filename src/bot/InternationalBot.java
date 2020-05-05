import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class InternationalBot implements BotAPI {

    // The public API of Bot must not change
    // This is ONLY class that you can edit in the program
    // Rename Bot to the name of your team. Use camel case.
    // Bot may not alter the state of the game objects
    // It may only inspect the state of the board and the player objects

    private static String inputFileName = "csw.txt";  //Dictionary file
    private static final int[] TILE_VALUE = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    ArrayList<WordStruct> highestScoringWords = new ArrayList<WordStruct>();
    ArrayList<WordStruct> wordsApplicable = new ArrayList<WordStruct>();
    ArrayList<BoardSquare> anchorTiles = new ArrayList<BoardSquare>();
    ArrayList<Character> allLettersOnBoard = new ArrayList<Character>();

    private PlayerAPI me;
    private OpponentAPI opponent;
    private BoardAPI board;
    private UserInterfaceAPI info;
    private DictionaryAPI dictionary;
    private int turnCount;

    InternationalBot(PlayerAPI me, OpponentAPI opponent, BoardAPI board, UserInterfaceAPI ui, DictionaryAPI dictionary) throws FileNotFoundException {
        makeHighestScoringWordsList();
        this.me = me;
        this.opponent = opponent;
        this.board = board;
        this.info = ui;
        this.dictionary = dictionary;
        turnCount = 0;
    }

    public String getCommand() {
        // Add your code here to input your commands
        // Your code must give the command NAME <botname> at the start of the game
        String command = "";
        switch (turnCount) {
            case 0:
                command = "NAME InternationalBot";
                break;
            default:
                command = playGame();
                break;
        }
        turnCount++;
        return command;
    }

    public static class WordStruct implements Comparable<WordStruct> {
        String word;
        int score;

        public WordStruct(String w, int s) {
            word = w;
            score = s;
        }

        public void setScore(int s) {
            score = s;
        }

        public void setWord(String w) {
            word = w;
        }

        public int getScore() {
            return score;
        }

        public String getWord() {
            return word;
        }

        public int compareTo(WordStruct w) {

            return WordStruct.compare(getScore(), w.getScore());
        }

        private static int compare(int score2, int score3) {
            if (score2 < score3) {
                return 1;
            }
            if (score2 == score3) {
                return 0;
            }
            return -1;
        }
    }
    
    public static class BoardSquare {
        int row;
        int col;

        public BoardSquare(int r, int c) {
            row = r;
            col = c;
        }

        public int getCol() {
            return col;
        }

        public int getRow() {
            return row;
        }

    }

    public String playGame() {
        if (board.isFirstPlay()) {
            return placeFirstWord();
        }
        
        int allLettersOnBoardSize = allLettersOnBoard.size();
        searchBoard();
        //Pass if board hasn't changed
        if(allLettersOnBoardSize == allLettersOnBoard.size()) return "PASS";
        
        wordsApplicable.clear();
        makeWordsFromFrame();
        
        return placeWord();
    }
    
    //used for turns after the first word has been placed
    public String placeWord() {
    	String command = "";
    	for(int i = 0; i < wordsApplicable.size(); i++) {
    		for(int j = 0; j < anchorTiles.size(); j++) {
    			String word = wordsApplicable.get(i).getWord();
    				if(isValid(word, anchorTiles.get(j).getRow(), 			
    					anchorTiles.get(j).getCol(), 'A', me.getFrameAsString())) {  //if word is valid across
	    				if(anchorTiles.get(j).getCol() >= 1 && anchorTiles.get(j).getCol() + word.length() < Board.BOARD_SIZE) { //if word is not on board edge
	    					if(!board.getSquareCopy(anchorTiles.get(j).getRow(), 	
	    						anchorTiles.get(j).getCol()-1).isOccupied()) {		//if there is no tile before word
		    					if(!board.getSquareCopy(anchorTiles.get(j).getRow(), 
		        						anchorTiles.get(j).getCol()+word.length()).isOccupied()) {	//if there is no tile after word
			    					char col = (char) (anchorTiles.get(j).getCol() + 65);
			    					int row = anchorTiles.get(j).getRow()+1;
			    					command = command + col;
			    					command = command + row + " ";
			    					command = command + "A";
			    					return command + " " + word;
		    					}
	    					}
	    				}
	    				else if(anchorTiles.get(j).getCol() == 0) {		//if word is on left edge
	
	        					if(!board.getSquareCopy(anchorTiles.get(j).getRow(), 
	            						anchorTiles.get(j).getCol()+word.length()).isOccupied()) {  //if there is no tile after word
	    	    					char col = (char) (anchorTiles.get(j).getCol() + 65);
	    	    					int row = anchorTiles.get(j).getRow()+1;
	    	    					command = command + col;
	    	    					command = command + row + " ";
	    	    					command = command + "A";
	    	    					return command + " " + word;
	        					}
	        					
	    				}
	    				else if(anchorTiles.get(j).getCol() == Board.BOARD_SIZE-1) {	//if word is on right edge
	    					if(!board.getSquareCopy(anchorTiles.get(j).getRow(), 
	        						anchorTiles.get(j).getCol()-1).isOccupied()) {	//if there is no tile before word
	    	    					char col = (char) (anchorTiles.get(j).getCol() + 65);
	    	    					int row = anchorTiles.get(j).getRow()+1;
	    	    					command = command + col;
	    	    					command = command + row + " ";
	    	    					command = command + "A";
	    	    					return command + " " + word;
	        					}
	        					
	    				}
    				
    			}
    			if(isValid(word, anchorTiles.get(j).getRow(), 			//if word is valid down
    					anchorTiles.get(j).getCol(), 'D', me.getFrameAsString())) {
    				if(anchorTiles.get(j).getRow() >= 1 && anchorTiles.get(j).getRow() + word.length() < Board.BOARD_SIZE) {  //if word is not on board edge
    					if(!board.getSquareCopy(anchorTiles.get(j).getRow()-1, 
    						anchorTiles.get(j).getCol()).isOccupied()) {	//if there is no tile before word
    					if(!board.getSquareCopy(anchorTiles.get(j).getRow() + word.length(), 
    						anchorTiles.get(j).getCol()).isOccupied()) {	//if the is no tile after word
    						char col = (char) (anchorTiles.get(j).getCol() + 65);
    						int row = anchorTiles.get(j).getRow()+1;
    						command = command + col;
    						command = command + row + " ";
    						command = command + "D";
    						return command + " " + word;
    					}
    					
    					}
    				}
    				else if(anchorTiles.get(j).getRow() == 0) {	//if word is on top edge
        					if(!board.getSquareCopy(anchorTiles.get(j).getRow() + word.length(), 
        						anchorTiles.get(j).getCol()).isOccupied()) { //if there is no tile after word
        						char col = (char) (anchorTiles.get(j).getCol() + 65);
        						int row = anchorTiles.get(j).getRow()+1;
        						command = command + col;
        						command = command + row + " ";
        						command = command + "D";
        						return command + " " + word;
        					}
        					
       
    				}
    				else if(anchorTiles.get(j).getRow() == Board.BOARD_SIZE-1) { //if word is on bottom edge
    					if(!board.getSquareCopy(anchorTiles.get(j).getRow()-1, 
        						anchorTiles.get(j).getCol()).isOccupied()) {	//if there is no tile before word
        		
        						char col = (char) (anchorTiles.get(j).getCol() + 65);
        						int row = anchorTiles.get(j).getRow()+1;
        						command = command + col;
        						command = command + row + " ";
        						command = command + "D";
        						return command + " " + word;
        					
        					
        					}
    				}
    				
    			}
    		} 
    	} 	
    	return "PASS";	//pass turn if no word with suitable anchor square was found
    }
    
    //checks if word and word placement is valid
	public boolean isValid(String word, int row, int col, char orientation, String tileFrame){
		if(!isBound(row, col, orientation, word.length())) {
			return false;
		}
		if(conflictsWithExistingLetters(word, getLettersAlreadyOnBoard(word, row, col, orientation))) {
			return false;
		}
		word = removeRedundantLettersFromWord(word, row, col, orientation);
		if(!wordCheck(word, tileFrame)) {
			return false;
		}
		if(word.length() <= 1) {
			return false;
		}
	return true;
}
	//Checks if frame has necessary tiles for word
	public boolean wordCheck(String word, String tileFrame) {			
		// used to check if there are enough tiles for the word
		if(tileFrame.length() < word.length()){
			return false;
		}
		
        int length = word.length();
        int foundLetter = 0;
        char[] n = tileFrame.toCharArray();
        
        if (word.length() <= n.length) {
            StringBuilder str = new StringBuilder(word);
            //System.out.println("this is: " + currentWord);
            //System.out.println("enters if");
            for (int i = 0; i < n.length; i++) {
                for (int j = 0; j < str.length(); j++) {
                    if (n[i] == str.charAt(j)) {
                        str.deleteCharAt(j);
                        foundLetter++;
                        j = 50;
                    }
                }
            }
        }
        if (foundLetter == length) {
            return true;
        }
        return false;
	}
	
	//Returns string which contains the letters that are one the board in the tiles that user wants to place their word
	public String getLettersAlreadyOnBoard(String userWord, int row, int col, char orientation) {
		String lettersOnBoard = "";
		if(orientation == 'D') {
			for (int i = 0; i < userWord.length(); i++) {
				if(!isEmpty(row, col)) lettersOnBoard += board.getSquareCopy(row, col).getTile().getLetter(); //if the square is not empty, add the letter to the string for return
				else lettersOnBoard += " ";	//add space for empty squares
				row++;
			}
		}
		//when horizontal word
		else {
			for (int i = 0; i < userWord.length(); i++) {
				if(!isEmpty(row, col)) lettersOnBoard += board.getSquareCopy(row, col).getTile().getLetter();
				else lettersOnBoard += " ";
				col++;
			}
		}
		System.out.println("Letters on board: " + lettersOnBoard);
		return lettersOnBoard;
	}
	
	public boolean isEmpty(int row, int col){
		return !board.getSquareCopy(row, col).isOccupied();
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
	
	public boolean conflictsWithExistingLetters(String word, String existingLetters) {
		for(int i = 0; i < word.length(); i++) {
			if(existingLetters.charAt(i) != ' ' && word.charAt(i) != existingLetters.charAt(i)) return true;
		}
		return false;
	}
	
	public boolean isBound(int row, int col, char orientation, int wordLength){
		if(row > 14 || row < 0){
			return false;
		}
		if(col > 14 || col < 0){
			return false;
		}
		if(orientation == 'A'){
			if( (col + wordLength - 1) > 14){
				return false;
			}
		}
		if(orientation == 'D'){
			if( (row + wordLength - 1) > 14){
				return false;
			}
		}

		return true;
	}


    //returns score of word based on the letters
    private static int getWordScore(String word) {
        int wordScore = 0;
        for (int i = 0; i < word.length(); i++) {
            int letterValue = TILE_VALUE[word.charAt(i) - 65];
            wordScore += letterValue;
        }
        return wordScore;
    }

    //copies all dictionary word into arraylist and sorts list by word score.
    public void makeHighestScoringWordsList() throws FileNotFoundException {
        File inputFile = new File(inputFileName);
        Scanner in = new Scanner(inputFile);
        while (in.hasNextLine()) {
            String word = in.nextLine();
            WordStruct node = new WordStruct(word, getWordScore(word));
            highestScoringWords.add(node);
        }
        in.close();
        sortHighestScoringWordsList();
    }

    public void sortHighestScoringWordsList() {
        Collections.sort(highestScoringWords);
    }

    public String makeFirstWord() {
        // search through arraylist for a possible word that contains all the letters in my frame
        String myFrame = me.getFrameAsString();
        char[] n = myFrame.toCharArray();
        int index = 0;
        int foundLetter = 0;
        //System.out.println(n);
        while (index < highestScoringWords.size()) {

            String currentWord = highestScoringWords.get(index).getWord();
            int length = currentWord.length();
            foundLetter = 0;

            if (currentWord.length() <= n.length) {
                StringBuilder str = new StringBuilder(currentWord);
                //System.out.println("this is: " + currentWord);
                //System.out.println("enters if");
                for (int i = 0; i < n.length; i++) {
                    for (int j = 0; j < str.length(); j++) {
                        if (n[i] == str.charAt(j)) {
                            str.deleteCharAt(j);
                            foundLetter++;
                            j = 50;
                        }
                    }
                }
            }
            if (foundLetter == length) {
                return currentWord;
            }
            index++;
        }

        return null;
    }

    public String placeFirstWord() {
        String word = makeFirstWord();
        if(word == null || word.isEmpty() ||  word == "" || word == " ") return "PASS";
        return "H8 A " + word;
    }

    public void searchBoard() {  //records anchor tiles and tiles with existing letters
    	anchorTiles.clear();
    	allLettersOnBoard.clear();
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                if (board.getSquareCopy(i, j).isOccupied()) {
                    allLettersOnBoard.add(board.getSquareCopy(i, j).getTile().getLetter());
                }
                else if(hasAdjacentTiles(i, j)) anchorTiles.add(new BoardSquare(i, j));
            }
        }
    }

    public boolean hasAdjacentTiles(int i, int j) {
        if (i == 0 && j == 0) {
            return board.getSquareCopy(i + 1, j).isOccupied() || board.getSquareCopy(i, j + 1).isOccupied();
        } else if (i == 0 && j > 0 && j < 14) {
            return board.getSquareCopy(i + 1, j).isOccupied() || board.getSquareCopy(i, j + 1).isOccupied() || board.getSquareCopy(i, j - 1).isOccupied();
        } else if (i == 0 && j == 14) {
            return board.getSquareCopy(i + 1, j).isOccupied() || board.getSquareCopy(i, j - 1).isOccupied();
        } else if (i == 14 && j == 0) {
            return board.getSquareCopy(i - 1, j).isOccupied() || board.getSquareCopy(i, j + 1).isOccupied();
        } else if (i == 14 && j > 0 && j < 14) {
            return board.getSquareCopy(i - 1, j).isOccupied() || board.getSquareCopy(i, j + 1).isOccupied()
                    || board.getSquareCopy(i, j - 1).isOccupied();
        } else if (j == 0 && i > 0 && i < 14) {
            return board.getSquareCopy(i + 1, j).isOccupied() || board.getSquareCopy(i - 1, j).isOccupied();
        } else if (j == 0 && i == 14) {
            return board.getSquareCopy(i - 1, j).isOccupied() || board.getSquareCopy(i, j + 1).isOccupied();
        } else if (j == 14 && i > 0 && i < 14) {
            return board.getSquareCopy(i + 1, j).isOccupied() || board.getSquareCopy(i - 1, j).isOccupied();
        } else if (j == 14 && i == 14) {
            return board.getSquareCopy(i - 1, j).isOccupied() || board.getSquareCopy(i, j - 1).isOccupied();
        } else {
            return board.getSquareCopy(i + 1, j).isOccupied() || board.getSquareCopy(i - 1, j).isOccupied()
                    || board.getSquareCopy(i, j - 1).isOccupied() || board.getSquareCopy(i, j + 1).isOccupied();
        }

    }

    public void makeWordsFromFrame() {
        String myFrame = me.getFrameAsString() + allLettersOnBoard;
        StringBuilder tiles = new StringBuilder(myFrame);

       // System.out.println(tiles.toString());
        // includes all the possible letters to use that are located on board
        int index = 0;
        StringBuilder searchWord = new StringBuilder();

        while (index < highestScoringWords.size()) {
            String currentWord = highestScoringWords.get(index).getWord();
            //System.out.println("Current Word: " + currentWord);
            // compare each character from a word from highestScoringWords to tiles in frame.
            // If character found, remove letter from frame and increment searchWordLen
            for (int i = 0; i < currentWord.length(); i++) {
                for (int j = 0; j < tiles.length(); j++) {
                    if (currentWord.charAt(i) == tiles.charAt(j)) {
                        searchWord.append(currentWord.charAt(i));
                        tiles.deleteCharAt(j);
                        break;
                    }
                }
            }

            //System.out.println("Searched Word: " + searchWord);

            // check if the word that have been sourced is the same as the current word. If it is, add to list
            if (searchWord.toString().equals(currentWord)) {
                WordStruct word = new WordStruct(searchWord.toString(), getWordScore(searchWord.toString()));
                wordsApplicable.add(word);
                //System.out.println("words Applicable added: " + wordsApplicable.get(0).getWord());
            }


            //reset variables to prepare for next search
            searchWord.setLength(0);
            tiles = new StringBuilder(myFrame);
            index++;

        }

    }
}