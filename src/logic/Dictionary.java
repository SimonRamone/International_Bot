package logic;

import java.io.File; 
import java.util.Scanner; 

public class Dictionary {
	
	private final static File DICTIONARY_FILE = new File("Collins_Scrabble_Words_2019.txt");
	static final int ALPHABET_SIZE = 26;

	static final class TrieNode {
		TrieNode[] children = new TrieNode[ALPHABET_SIZE];
		boolean isEndOfWord;		//true if node represents end of word

		TrieNode() {
			isEndOfWord = false;
			for (int i = 0; i < ALPHABET_SIZE; i++)
				children[i] = null;
		}
	}

	public Dictionary() throws Exception{
		root = new TrieNode();
		initDictionary();
	}
	
	public static void initDictionary() throws Exception {
		Scanner scanner = new Scanner(DICTIONARY_FILE);
		while(scanner.hasNext()) {
			insert(scanner.nextLine());
		}
		scanner.close();
	}

	static TrieNode root;

	public static void insert(String word){ 	//inserts dictionary words into our trie
		int level, index;
		int length = word.length();

		TrieNode current = root;

		for(level =0; level < length; level++){
			index = word.charAt(level) - 'A';
			if (current.children[index] == null) {
				current.children[index] = new TrieNode();
			}

			current = current.children[index];
		}

		current.isEndOfWord = true;
	}
											
	public static boolean search(String word) {		//returns true if word is in trie
		int level;
		int length = word.length();
		int index;
		TrieNode current = root;
		word = word.toUpperCase();

		for (level = 0; level < length; level++)
		{
			index = word.charAt(level) - 'A';

			if (current.children[index] == null)
				return false;

			current = current.children[index];
		}

		return (current != null && current.isEndOfWord);
	}
	
	public static void main(String args[]) throws Exception { 
		root = new TrieNode();

		initDictionary();
		if(search("SUPEREFFICIENCY") == true) {
			System.out.println("found");
		}else{
			System.out.println("not found");
		}

		if(search("RubbishText") == true) {
			System.out.println("found 2");
		}else{
			System.out.println("not found 2");
		}

		if(search("caPItAl") == true) {
			System.out.println("found 3");
		}else{
			System.out.println("not found 3");
		}

		if(search("Overwatch") == true) {
			System.out.println("found 4");
		}else{
			System.out.println("not found 4");
		}
	}


}
