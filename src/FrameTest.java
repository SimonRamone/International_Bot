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
	void testRefillFrame() {
		try {
			F.refillFrame();
		} catch(IllegalArgumentException ex) {}

		for(int i = 0; i < 14; i++) {
			F.clear();
			F.refillFrame();
		}

		assertEquals(0, P.tilesLeft());

		try {
			F.clear();
			F.refillFrame();
		} catch(IllegalArgumentException ex) {}
	}

}