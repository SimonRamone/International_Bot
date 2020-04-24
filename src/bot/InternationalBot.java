
// International Bot 17205786, 18439314, 18763829
package bot;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class InternationalBot implements BotAPI {

    // International Bot 17205786, 18439314, 18763829
    // The public API of Bot must not change
    // This is ONLY class that you can edit in the program
    // Rename Bot to the name of your team. Use camel case.
    // Bot may not alter the state of the game objects
    // It may only inspect the state of the board and the player objects

    private static String inputFileName = "C:\\Users\\jackl\\Downloads\\BasicBots3\\Collins_Scrabble_Words_2019.txt";
    private static final int[] TILE_VALUE = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    ArrayList<WordNode> highestScoringWords = new ArrayList<WordNode>();
    ArrayList<Square> anchorTiles = new ArrayList<Square>();
    ArrayList<Character> lettersOnBoard = new ArrayList<Character>();

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
//            case 1:
//                command = "PASS";
//                break;
//            case 2:
//                command = "HELP";
//                break;
//            case 3:
//                command = "SCORE";
//                break;
//            case 4:
//                command = "POOL";
//                break;
            default:
                //System.out.println("END");

                command = playGame();
                break;
        }
        turnCount++;
        return command;
    }

    public static class WordNode implements Comparable<WordNode> {
        String word;
        int score;

        public WordNode(String w, int s) {
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

        public int compareTo(WordNode node) {

            return WordNode.compare(getScore(), node.getScore());
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

    public String playGame(){
        if(board.isFirstPlay()){
            return placeFirstWord();
        }

        return "H9 A " + makeFirstWord();
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
            WordNode node = new WordNode(word, getWordScore(word));
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
        return "H8 A " + word;
    }

    public void searchBoard() {  //records anchor tiles and tiles with existing letters
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                if (board.getSquareCopy(i, j).isOccupied()) {
                    lettersOnBoard.add(board.getSquareCopy(i, j).getTile().getLetter());
                } else if (hasAdjacentTiles(i,j)){
                    anchorTiles.add(board.getSquareCopy(i,j));
                }
            }
        }
    }

    public boolean hasAdjacentTiles(int i, int j) {
        if(board.getSquareCopy(i+1, j).isOccupied()) return true;
        if(board.getSquareCopy(i-1, j).isOccupied()) return true;
        if(board.getSquareCopy(i, j-1).isOccupied()) return true;
        if(board.getSquareCopy(i, j+1).isOccupied()) return true;
        return false;
    }



    public static void main(String[] args) {

    }


}