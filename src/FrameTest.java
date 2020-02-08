import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//International Bot 17205786, 18439314, 18763829

class FrameTest {

	Frame F;
	Pool P;

	@BeforeEach
	void setUp() throws Exception {
		P = new Pool();
		F = new Frame(P);
	}

	//Removes all tiles from the frame then checks that the frame is empty 
	//and that no more tiles can be removed
	@Test
	void testRemoveTile() {
		F.removeTile(6);
		F.removeTile(5);
		F.removeTile(4);
		F.removeTile(3);
		F.removeTile(2);
		F.removeTile(1);
		F.removeTile(0);
		assertEquals(0, F.getSize());
		assertTrue(F.isEmpty());
		try {
			F.removeTile(0);
		} catch(IllegalArgumentException ex) {}

	}

	@Test
	void testGetSize() {
		assertEquals(7, F.getSize());
		
		F.removeTile(6);
		assertEquals(6, F.getSize());
		
		F.removeTile(5);
		assertEquals(5, F.getSize());
		
		F.removeTile(4);
		assertEquals(4, F.getSize());
		
		F.removeTile(3);
		assertEquals(3, F.getSize());
		
		F.removeTile(2);
		assertEquals(2, F.getSize());
		
		F.removeTile(1);
		assertEquals(1, F.getSize());
		
		F.removeTile(0);
		assertEquals(0, F.getSize());
		
		F.refillFrame();
		assertEquals(7, F.getSize());
		F.removeTile(4);
		assertEquals(6, F.getSize());
		F.removeTile(3);
		assertEquals(5, F.getSize());
		F.refillFrame();
		assertEquals(7, F.getSize());
	}
	
	@Test
	void testGetFrame() {
		Frame temp = F;
		assertTrue(temp.equals(F.getFrame()));
	}
	
	@Test
	void testIsEmpty() {
		F.clear();
		assertEquals(true, F.isEmpty());
		
		F.refillFrame();
		F.removeTile(6);
		F.removeTile(5);
		F.removeTile(4);
		F.removeTile(3);
		F.removeTile(2);
		F.removeTile(1);
		F.removeTile(0);
		assertEquals(true, F.isEmpty());
	}
	
	@Test
	void testRefillFrame() {
		//Checks that frame cannot be refilled when it's already full
		try {
			F.refillFrame();
		} catch(IllegalArgumentException ex) {}

		//Empties pool
		for(int i = 0; i < 14; i++) {
			F.clear();
			F.refillFrame();
		}
		
		//Checks pool is empty
		assertEquals(0, P.tilesLeft());
		
		//Checks that frame cannot be refilled when pool is empty
		try {
			F.clear();
			F.refillFrame();
		} catch(IllegalArgumentException ex) {}
	}

}