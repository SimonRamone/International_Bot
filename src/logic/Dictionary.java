package logic;

import java.io.File; 
import java.util.Scanner; 

public class Dictionary {
	
	private final static File DICTIONARY_FILE = new File("src/Collins_Scrabble_Words_2019.txt"); 
	
	public Dictionary() throws Exception{
		initDictionary();
	}
	
	public static void initDictionary() throws Exception {
		Scanner scanner = new Scanner(DICTIONARY_FILE);
		while(scanner.hasNext()) {
			insert(scanner.nextLine());
		}
		scanner.close();
	}
	
	//Method inserts word into tree
	public static void insert(String word){ 
		//needs to be implemented
	}
	
	//Method returns true if word is in tree
	public static boolean search(String word) { 
		return Board.challengeWord();	//this needs to be deleted
	}
	
	public static void main(String args[]) throws Exception { 
		
	} 
	
}
