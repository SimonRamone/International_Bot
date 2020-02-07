import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
// International Bot 17205786, 18439314, 18763829
class PoolTest {
    Pool p = new Pool();

    @Test
    void testTilesLeft(){
        assertEquals(100, p.tilesLeft());
    }

    @Test
    void testReset(){
        p.randomDraw();
        p.reset();
        assertEquals(100, p.tilesLeft());
    }

    @Test
    void testGetLetter(){
        assertEquals('C', p.LetterPool.get(78).getLetter());
    }
    @Test
    void testGetLetterScore(){
        assertEquals(3, p.getLetterScore('C'));
    }

    @Test
    void testRandomDraw(){
        p.randomDraw();
        assertEquals(99, p.tilesLeft());

        p.reset();
        while(!p.isEmpty()){
            p.randomDraw();
        }

        try{
            p.randomDraw();
        }catch (IllegalArgumentException ex){}

    }
    @Test
    void testIsEmpty(){
        p.reset();
        while(!p.isEmpty()){
            p.randomDraw();
        }
        assertTrue(p.isEmpty());
    }

}