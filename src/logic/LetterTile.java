package logic;

// International Bot 17205786, 18439314, 18763829
public class LetterTile {

    public char letter;
    public int score;


    public LetterTile(char letter, int score){
        setLetter(letter);
        setScore(score);
    }

    // this is to create the letters needed for the letter pool.
    // blank tiles - 0pt
    // E, A, I, O, N, R, T, L, S, U - 1pt
    // D, G - 2pt
    // B, C, M, P - 3pt
    // F, H, V, W, Y - 4pt
    // K - 5pt
    // J, X - 8pt
    // Q, Z - 10pt
    final static LetterTile tileBlank = new LetterTile('_', 0);
    final static LetterTile tileA = new LetterTile('A', 1);
    final static LetterTile tileB = new LetterTile('B', 3);
    final static LetterTile tileC = new LetterTile('C', 3);
    final static LetterTile tileD = new LetterTile('D', 2);
    final static LetterTile tileE = new LetterTile('E', 1);
    final static LetterTile tileF = new LetterTile('F', 4);
    final static LetterTile tileG = new LetterTile('G', 2);
    final static LetterTile tileH = new LetterTile('H', 4);
    final static LetterTile tileI = new LetterTile('I', 1);
    final static LetterTile tileJ = new LetterTile('J', 8);
    final static LetterTile tileK = new LetterTile('K', 5);
    final static LetterTile tileL = new LetterTile('L', 1);
    final static LetterTile tileM = new LetterTile('M', 3);
    final static LetterTile tileN = new LetterTile('N', 1);
    final static LetterTile tileO = new LetterTile('O', 1);
    final static LetterTile tileP = new LetterTile('P', 3);
    final static LetterTile tileQ = new LetterTile('Q', 10);
    final static LetterTile tileR = new LetterTile('R', 1);
    final static LetterTile tileS = new LetterTile('S', 1);
    final static LetterTile tileT = new LetterTile('T', 1);
    final static LetterTile tileU = new LetterTile('U', 1);
    final static LetterTile tileV = new LetterTile('V', 4);
    final static LetterTile tileW = new LetterTile('W', 4);
    final static LetterTile tileX = new LetterTile('X', 8);
    final static LetterTile tileY = new LetterTile('Y', 4);
    final static LetterTile tileZ = new LetterTile('Z', 10);

    // accessor and mutators

    public char getLetter() {
        return letter;
    }

    public int getScore() { return score; }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String toString() {
        return "[ " + getLetter() + " " + getScore() + " ]";
    }
    
    public static LetterTile getLetterTile(char letter) {
    	switch(letter) {
    		case 'A': return tileA;
    		case 'B': return tileB;
    		case 'C': return tileC;
    		case 'D': return tileD;
    		case 'E': return tileE;
    		case 'F': return tileF;
    		case 'G': return tileG;
    		case 'H': return tileH;
    		case 'I': return tileI;
    		case 'J': return tileJ;
    		case 'K': return tileK;
    		case 'L': return tileL;
    		case 'M': return tileM;
    		case 'N': return tileN;
    		case 'O': return tileO;
    		case 'P': return tileP;
    		case 'Q': return tileQ;
    		case 'R': return tileR;
    		case 'S': return tileS;
    		case 'T': return tileT;
    		case 'U': return tileU;
    		case 'V': return tileV;
    		case 'W': return tileW;
    		case 'X': return tileX;
    		case 'Y': return tileW;
    		case 'Z': return tileY;
    		default: return tileBlank;
    	}
    }
}