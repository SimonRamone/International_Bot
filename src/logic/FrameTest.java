package logic;

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
	void testGetTile() {
		assertEquals(F.tileFrame.get(0), F.getTile(0));
		assertEquals(F.tileFrame.get(1), F.getTile(1));
		assertEquals(F.tileFrame.get(2), F.getTile(2));
		assertEquals(F.tileFrame.get(3), F.getTile(3));
		assertEquals(F.tileFrame.get(4), F.getTile(4));
		assertEquals(F.tileFrame.get(5), F.getTile(5));
		assertEquals(F.tileFrame.get(6), F.getTile(6));
		
		//Test when input index is negative
		try {
			F.getTile(-1);
		} catch(IllegalArgumentException ex) {}
		
		//Tests when input index is greater than amount of tiles in frame
		try {
			F.getTile(7);
		} catch(IllegalArgumentException ex) {}
		
		//Tests when input index is greater than amount of tiles in frame
		try {
			F.removeTile(0);
			F.getTile(6);
		} catch(IllegalArgumentException ex) {}
	}
	
	@Test
	void testContainsTile() {
		assertTrue(F.containsTile(F.getTile(0)));
	}
	
	@Test
	void testToString() {
		//Test when frame is full
		String str = F.getTile(0).toString() + F.getTile(1).toString() + F.getTile(2).toString() + F.getTile(3).toString()
				+ F.getTile(4).toString() + F.getTile(5).toString() + F.getTile(6).toString();
		assertEquals(str, F.toString());
		
		//When frame has less than 7 tiles
		F.removeTile(0);
		str = F.getTile(0).toString() + F.getTile(1).toString() + F.getTile(2).toString() + F.getTile(3).toString()
				+ F.getTile(4).toString() + F.getTile(5).toString();
		assertEquals(str, F.toString());
		
		//When frame is empty
		F.emptyFrame();
		assertEquals("", F.toString());
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
	void testEmptyFrame() {
		F.emptyFrame();
		assertEquals(0, F.getSize());
	}
	
	@Test
	void testGetFrame() {
		assertEquals(F.tileFrame, F.getFrame());
	}
	
	@Test
	void testIsEmpty() {
		F.emptyFrame();
		assertEquals(true, F.isEmpty());
		
		F.refillFrame();
		F.removeTile(6);
		F.removeTile(5);
		F.removeTile(4);
		F.removeTile(3);
		F.removeTile(2);
		F.removeTile(1);
		assertEquals(false, F.isEmpty());
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
			F.emptyFrame();
			F.refillFrame();
		}
		
		//Checks pool is empty
		assertEquals(0, P.tilesLeft());
		
		//Checks that frame cannot be refilled when pool is empty
		try {
			F.emptyFrame();
			F.refillFrame();
		} catch(IllegalArgumentException ex) {}
	}

}