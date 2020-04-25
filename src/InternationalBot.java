import bot.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class InternationalBot implements BotAPI {

    // The public API of Bot must not change
    // This is ONLY class that you can edit in the program
    // Rename Bot to the name of your team. Use camel case.
    // Bot may not alter the state of the game objects
    // It may only inspect the state of the board and the player objects

	private static String inputFileName = "/Users/simonas/eclipse-workspace/Scrabble Bot/src/Collins_Scrabble_Words_2019.txt";
	private static final int[] TILE_VALUE = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
	ArrayList<WordNode> highestScoringWords = new ArrayList<WordNode>();
	
	
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
            case 1:
                command = "PASS";
                break;
            case 2:
                command = "HELP";
                break;
            case 3:
                command = "SCORE";
                break;
            case 4:
                command = "POOL";
                break;
            default:
            	System.out.println("END");
            	
                command = "H8 A AN";
                break;
        }
        turnCount++;
        return command;
    }
    
		public static class WordNode implements Comparable<WordNode>{
			String word;
			int score;
			
			public WordNode(String w, int s){
				word = w;
				score = s;
			}
			
			public void setScore(int s){score = s;}
			public void setWord(String w){word = w;}
			
			public int getScore(){return score;}
			public String getWord(){return word;}
			
			public int compareTo(WordNode node) {
				
				   return WordNode.compare(getScore(), node.getScore());
			}

			private static int compare(int score2, int score3) {
				if(score2 < score3) {
		            return 1;           
		        }
		        if(score2 == score3) {
		            return 0;           
		        }
		        return -1;
			}
		}
		
		private static int getWordScore(String word) {
			int wordScore = 0;
			for (int i = 0; i<word.length(); i++) {
				int letterValue = TILE_VALUE[word.charAt(i)-65];
				wordScore += letterValue;
			}
			return wordScore;
		}
		
		public void makeHighestScoringWordsList() throws FileNotFoundException {
			File inputFile = new File(inputFileName);
			Scanner in = new Scanner(inputFile);
			while (in.hasNextLine()) {
				String word = in.nextLine();
				WordNode node = new WordNode(word, getWordScore(word));
				highestScoringWords.add(node);
			}
			in.close();
			sortHighestScoringWordsList();
		}
		
		public void sortHighestScoringWordsList() {
			Collections.sort(highestScoringWords);
		}
		
		
		
		
	public static void main(String[] args) {
		
	}


}
