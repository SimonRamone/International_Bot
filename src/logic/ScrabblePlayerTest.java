package logic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScrabblePlayerTest{

    Frame f = new Frame(new Pool());
    Pool P = new Pool();
    ScrabblePlayer s = new ScrabblePlayer(P);

    @Test
    void TestGetName(){
        s.addPlayer("Jack Liu   ");
        assertEquals("Jack Liu", s.players.get(0).getName());
    }
    @Test
    void TestResetPlayer(){
        s.addPlayer("Jack Liu   ");
        s.resetPlayer(0, "bob");        //player at index 0
        s.addPlayer("out of bound");       // player at index 1
        assertEquals("bob", s.players.get(0).getName());

//        try {   //checks for non existing player at position 2
//            s.removePlayer(2);        //java automatically throws out of bounds exception
//        } catch(IllegalArgumentException ex) {}

    }
    @Test
    void TestSetAndGetScore(){
    	s.addPlayer("Jack Liu   ");
        s.players.get(0).setScore(25);
        assertEquals(25, s.players.get(0).getScore());
    }

    @Test
    void TestResetScore(){
        s.addPlayer("bob builder ");
        s.players.get(0).setScore(123);
        s.players.get(0).resetScore();
        assertEquals(0, s.players.get(0).getScore());
    }

    @Test
    void TestUpdateScore(){
        s.addPlayer("bob builder ");
        s.players.get(0).setScore(123);
        s.players.get(0).updateScore(26);
        s.players.get(0).updateScore(1);
        assertEquals(150, s.players.get(0).getScore());
    }

    @Test
    void TestAddAndRemovePlayer(){
        try {   //checks that no players can be removed when list empty
            s.removePlayer(0);
        } catch(IllegalArgumentException ex) {}

        s.addPlayer("bob");
        s.addPlayer("Jack ");
        s.addPlayer("Jerald");
        s.addPlayer("Simonas");
        s.removePlayer(1);
        s.removePlayer(0);
        assertEquals(2, s.players.size());
    }

    @Test
    void TestFullList(){
        s.addPlayer("bob");
        s.addPlayer("Jack ");
        s.addPlayer("Jerald");
        s.addPlayer("Simonas");
        try {       //checks for too many players entered exception
            s.addPlayer("extra");
        } catch(IllegalArgumentException ex) {}
    }
}