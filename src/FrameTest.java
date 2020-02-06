import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FrameTest {
		
		Pool P = new Pool();
		Frame F = new Frame(P);	
	
		@Test
			void testGetSize() {
				assertEquals(7, F.getSize());
				F.removeTile(6);
				F.removeTile(0);
				assertEquals(5, F.getSize());
			}
		
}