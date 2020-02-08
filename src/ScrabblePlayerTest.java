//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ScrabblePlayerTest {
//
//    Frame f = new Frame(new Pool());
//    ScrabblePlayer s = new ScrabblePlayer();
//    Pool P = new Pool();
//
//    @Test
//    void TestGetName(){
//        s.addPlayer(f, "Jack Liu   ");
//        assertEquals("Jack Liu", s.players.get(0).getName());
//    }
//    @Test
//    void TestResetPlayer(){
//        s.addPlayer(f, "Jack Liu   ");
//        s.resetPlayer(0, f, "bob");
//        assertEquals("bob", s.players.get(0).getName());
//    }
//    @Test
//    void TestSetScore(){
//        s.players.get(0).updateScore(25);
//        assertEquals(25, s.players.get(0).getScore());
//    }
//
//}