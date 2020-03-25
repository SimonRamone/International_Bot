package logic;

import logic.Board;
import view.BoardUI;

import logic.LetterTile;
import logic.Pool;
import logic.ScrabblePlayer;

public class Scrabble {
    private static final Board B = new Board();
    public int score = 0;

    Pool p = new Pool();
    ScrabblePlayer humanPlayers = new ScrabblePlayer(p);
    Board b = new Board();
   // BoardUI game = new BoardUI();



    public void scoreCalculator(SimplePlayer player, String word, int row, int col, char orientation){
        //this is put into the Board.java function.

        int wordScore =0;
        int wordMultiplier =0;
        if(orientation == '>') {
            for(int i = col; i < col+word.length(); i++) {
                    wordScore += (b.getSquare(row, col).getLetterTile().getScore()) * b.getSquare(row, col).getLetterMultiplier();
                }
            }
        else if(orientation == 'v'){
            for(int i = row; i < row+word.length(); i++) {
                wordScore += (b.getSquare(row, col).getLetterTile().getScore()) * b.getSquare(row, col).getLetterMultiplier();
            }
        }

        for (int i=0; i<word.length();i++){
            if(b.getSquare(row, col).getWordMultiplier()==2) {
                wordMultiplier = 2;
            }else if(b.getSquare(row, col).getWordMultiplier() ==3){
                wordMultiplier = 3;
            }else{wordMultiplier =1;}
        }

        player.score = wordScore*wordMultiplier;
        System.out.println("scrabble score : " + player.score);

    }

    public static void main(String[] args){

    }
}
