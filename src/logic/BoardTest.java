package logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    Frame F;
    Pool P;
    ScrabblePlayer S;
    Board B;

    @BeforeEach
    void setUp() throws Exception {
        P = new Pool();
        F = new Frame(P);
        S = new ScrabblePlayer(P);
        B = new Board();
        S.addPlayer("Player1");
        S.players.get(0).playerFrame.emptyFrame();
        S.players.get(0).playerFrame.add(LetterTile.tileA);
        S.players.get(0).playerFrame.add(LetterTile.tileBlank);
        S.players.get(0).playerFrame.add(LetterTile.tileBlank);
        S.players.get(0).playerFrame.add(LetterTile.tileP);
        S.players.get(0).playerFrame.add(LetterTile.tileL);
        S.players.get(0).playerFrame.add(LetterTile.tileE);
        S.players.get(0).playerFrame.add(LetterTile.tileS);

    }

    @Test
    void testInitBoard(){   //test to check if special blocks are of the correct type.
        assertEquals(2, B.scrabbleBoard[3][0].getLetterMultiplier());
        assertEquals(3, B.scrabbleBoard[13][5].getLetterMultiplier());
        assertEquals(3, B.scrabbleBoard[7][14].getWordMultiplier());
        assertEquals(2, B.scrabbleBoard[2][2].getWordMultiplier());
        assertEquals(1, B.scrabbleBoard[0][2].getWordMultiplier());
        assertEquals(1, B.scrabbleBoard[13][14].getLetterMultiplier());
    }

    @Test
    void testSetLetterTile(){       //setter and getter test for letter tile on board
        B.scrabbleBoard[0][0].setTile(LetterTile.tileA);
        B.scrabbleBoard[7][7].setTile(LetterTile.tileH);
        B.scrabbleBoard[9][12].setTile(LetterTile.tileZ);

        assertEquals('A', B.scrabbleBoard[0][0].getLetterTile().getLetter());
        assertEquals('H', B.scrabbleBoard[7][7].getLetterTile().getLetter());
        assertEquals('Z', B.scrabbleBoard[9][12].getLetterTile().getLetter());
        assertTrue(B.scrabbleBoard[13][4].isEmpty());
    }

    @Test
    void testResetBoard(){
        B.scrabbleBoard[0][0].setTile(LetterTile.tileA);
        B.scrabbleBoard[7][7].setTile(LetterTile.tileH);
        B.resetBoard();
        assertTrue(B.scrabbleBoard[0][0].isEmpty());
        assertTrue(B.scrabbleBoard[7][7].isEmpty());
    }

    @Test
    void testSetSquare(){
        B.setSquare(1,2,LetterTile.tileG);
        B.setSquare(6,6,LetterTile.tileU);
        assertEquals('G', B.getSquare(1,2).getLetterTile().getLetter());
        assertEquals('U', B.getSquare(6,6).getLetterTile().getLetter());
    }

    @Test
    void testWordCheck(){
        assertTrue(B.hasWildCardInFrame(S.players.get(0).getFrame()));      //wildcard (blank) checks
        assertEquals(2,B.countWildCardInFrame(S.players.get(0).getFrame()));//#of wildcard check

        assertTrue(B.wordCheck("APPLE", S.players.get(0).getFrame()));//test if players are allowed to make such words
        assertTrue(B.wordCheck("LEASON", S.players.get(0).getFrame()));
        try {   //checks that if player has letters for word orange
            B.wordCheck("ORANGE", S.players.get(0).getFrame());
        } catch(IllegalArgumentException ex) {}
    }

    @Test
    void testIsBound(){
        try {   //out of bound error checks
            B.isBound(0,10,'>', 6);
        } catch(IllegalArgumentException ex) {}

        try {
            B.isBound(15,15);
        } catch(IllegalArgumentException ex) {}
    }




}
