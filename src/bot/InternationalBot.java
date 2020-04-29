package bot;

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

    private static String inputFileName = "C:\\Users\\Jerald Choon\\IdeaProjects\\International-Bot\\src\\bot\\Collins_Scrabble_Words_2019.txt";
    private static final int[] TILE_VALUE = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    ArrayList<WordNode> highestScoringWords = new ArrayList<WordNode>();
    ArrayList<WordNode> wordsApplicable = new ArrayList<WordNode>();
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

                for (int i = 0; i < 15; i++) {
                    for (int j = 0; j < 15; j++) {
                        System.out.print(board.getSquareCopy(i, j).isOccupied());
                    }
                    System.out.println();
                }
                System.out.println("8, 8: " + hasAdjacentTiles(7, 7));
                System.out.println("7, 8: " + hasAdjacentTiles(6, 7));
                System.out.println("6, 8: " + hasAdjacentTiles(5, 7));
                System.out.println("5, 8: " + hasAdjacentTiles(4, 7));
                System.out.println("9, 8: " + hasAdjacentTiles(8, 7));
                System.out.println("10, 8: " + hasAdjacentTiles(9, 7));
                System.out.println("11, 8: " + hasAdjacentTiles(10, 7));
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

    public String playGame() {
        if (!board.getSquareCopy(7, 7).isOccupied()) {
            return placeFirstWord();
        }

        makeWordsFromFrame();
//        for(int i = 0; i < wordsApplicable.size(); i++){
//            System.out.println("here");
//            System.out.println(wordsApplicable.get(i).getWord());
//        }

        return "H9 A " + wordsApplicable.get(0).getWord();
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
                } else if (hasAdjacentTiles(i, j)) {
                    anchorTiles.add(board.getSquareCopy(i, j));
                }
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
        searchBoard();
        String myFrame = me.getFrameAsString() + lettersOnBoard;
        StringBuilder tiles = new StringBuilder(myFrame);

        System.out.println(tiles.toString());
        // includes all the possible letters to use that are located on board
        int index = 0;
        StringBuilder searchWord = new StringBuilder();

        while (index < highestScoringWords.size()) {
            String currentWord = highestScoringWords.get(index).getWord();
            System.out.println("Current Word: " + currentWord);
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

            System.out.println("Searched Word: " + searchWord);

            // check if the word that have been sourced is the same as the current word. If it is, add to list
            if (searchWord.toString().equals(currentWord)) {
                WordNode node = new WordNode(searchWord.toString(), getWordScore(searchWord.toString()));
                wordsApplicable.add(node);
                System.out.println("words Applicable added: " + wordsApplicable.get(0).getWord());
            }


            //reset variables to prepare for next search
            searchWord.setLength(0);
            tiles = new StringBuilder(myFrame);
            index++;

        }

    }
}