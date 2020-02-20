package Logic;

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

    // Test to see all letters are in pool and to ensure getLetter method work as intended
    @Test
    void testGetLetter(){
        for(int i = 0; i < 100; i++){
            if(i <= 11){
                assertEquals('E', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 20){
                assertEquals('A', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 29){
                assertEquals('I', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 37){
                assertEquals('O', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 43){
                assertEquals('N', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 49){
                assertEquals('R', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 55){
                assertEquals('T', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 59){
                assertEquals('L', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 63){
                assertEquals('S', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 67){
                assertEquals('U', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 71){
                assertEquals('D', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 74){
                assertEquals('G', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 76){
                assertEquals('B', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 78){
                assertEquals('C', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 80){
                assertEquals('M', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 82){
                assertEquals('P', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 84){
                assertEquals('F', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 86){
                assertEquals('H', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 88){
                assertEquals('V', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 90){
                assertEquals('W', p.LetterPool.get(i).getLetter());
            }
            else if(i <= 92){
                assertEquals('Y', p.LetterPool.get(i).getLetter());
            }
            else if(i == 93){
                assertEquals('K', p.LetterPool.get(i).getLetter());
            }
            else if(i == 94){
                assertEquals('J', p.LetterPool.get(i).getLetter());
            }
            else if(i == 95){
                assertEquals('X', p.LetterPool.get(i).getLetter());
            }
            else if(i == 96){
                assertEquals('Q', p.LetterPool.get(i).getLetter());
            }
            else if(i == 97){
                assertEquals('Z', p.LetterPool.get(i).getLetter());
            }
            else{
                assertEquals('_', p.LetterPool.get(i).getLetter());
            }
        }
    }

    // test if all score are initialized correctly
    @Test
    void testGetLetterScore(){
        assertEquals(0, p.getLetterScore('_'));
        assertEquals(1, p.getLetterScore('A'));
        assertEquals(3, p.getLetterScore('B'));
        assertEquals(3, p.getLetterScore('C'));
        assertEquals(2, p.getLetterScore('D'));
        assertEquals(1, p.getLetterScore('E'));
        assertEquals(4, p.getLetterScore('F'));
        assertEquals(2, p.getLetterScore('G'));
        assertEquals(4, p.getLetterScore('H'));
        assertEquals(1, p.getLetterScore('I'));
        assertEquals(8, p.getLetterScore('J'));
        assertEquals(5, p.getLetterScore('K'));
        assertEquals(1, p.getLetterScore('L'));
        assertEquals(3, p.getLetterScore('M'));
        assertEquals(1, p.getLetterScore('N'));
        assertEquals(1, p.getLetterScore('O'));
        assertEquals(3, p.getLetterScore('P'));
        assertEquals(10, p.getLetterScore('Q'));
        assertEquals(1, p.getLetterScore('R'));
        assertEquals(1, p.getLetterScore('S'));
        assertEquals(1, p.getLetterScore('T'));
        assertEquals(1, p.getLetterScore('U'));
        assertEquals(4, p.getLetterScore('V'));
        assertEquals(4, p.getLetterScore('W'));
        assertEquals(8, p.getLetterScore('X'));
        assertEquals(4, p.getLetterScore('Y'));
        assertEquals(10, p.getLetterScore('Z'));


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