public class Board {
    public static int boardSize = 15;
    public static int tileStart = 7;

    public BoardSquare[][] scrabbleBoard = new BoardSquare[boardSize][boardSize];


    public Board(){
        initBoard();
    }

    public void initBoard(){
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
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


    public static void main(String[] args) {
        Board b = new Board();

        System.out.println(b.scrabbleBoard[1][1].getLetter());
        b.scrabbleBoard[1][1].setLetter('A');
        System.out.println(b.scrabbleBoard[1][1].getLetter());

    }

}
