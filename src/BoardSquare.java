public class BoardSquare {
    private int wordMultiplier;
    private int letterMultiplier;
    private LetterTile letter;

    public BoardSquare(LetterTile letter, int letterMultiplier, int wordMultiplier){
        this.letter = letter;
        this.letterMultiplier = letterMultiplier;
        this.wordMultiplier = wordMultiplier;
    }

    public int getLetterMultiplier() {
        return letterMultiplier;
    }

    public int getWordMultiplier(){
        return wordMultiplier;
    }

    public BoardSquare setTile(LetterTile selectedTile){
        this.letter = selectedTile;
        return null;
    }

    public LetterTile getLetterTile(){
        return letter;
    }

    /*
     * Types of letter scores -
     * Double letter score, triple letter score, double word score, triple word score.
     * All are single use.
     * First word score ( doubles word score ) at coordinate [7,7].
     * If use up all 7 tiles for a word in a turn, award 50 bonus points.
     */

    public static BoardSquare normalSquare(){
        return new BoardSquare(null,1, 1);
    }

    public static BoardSquare doubleLetterScore(){
        return new BoardSquare(null,2, 1);
    }

    public static BoardSquare tripleLetterScore(){
        return new BoardSquare(null,3, 1);
    }

    public static BoardSquare doubleWordScore(){
        return new BoardSquare(null,1, 2);
    }

    public static BoardSquare tripleWordScore(){
        return new BoardSquare(null,1, 3);
    }
}
