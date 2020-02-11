public class BoardSquare {
    private int wordMultiplier;
    private int letterMultiplier;
//    private int row;
//    private int column;
    private char letter;

    public BoardSquare(char letter, int letterMultiplier, int wordMultiplier){
        this.letter = letter;
//        this.row = row;
//        this.column = column;
        this.letterMultiplier = letterMultiplier;
        this.wordMultiplier = wordMultiplier;
    }

    public char getLetter(){
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    /*
     * Types of letter scores -
     * Double letter score, triple letter score, double word score, triple word score.
     * All are single use.
     * First word score ( doubles word score ) at coordinate [7,7].
     * If use up all 7 tiles for a word in a turn, award 50 bonus points.
     */

    public static BoardSquare normalSquare(){
        return new BoardSquare( ' ', 1, 1);
    }

    public static BoardSquare doubleLetterScore(){
        return new BoardSquare(' ', 2, 1);
    }

    public static BoardSquare tripleLetterScore(){
        return new BoardSquare(' ', 3, 1);
    }

    public static BoardSquare doubleWordScore(){
        return new BoardSquare(' ', 1, 2);
    }

    public static BoardSquare tripleWordScore(){
        return new BoardSquare(' ', 1, 3);
    }
}
