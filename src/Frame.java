import java.util.ArrayList;

//International Bot 17205786, 18439314, 18763829

public class Frame {

	public ArrayList<LetterTile> tileFrame = new ArrayList<LetterTile>();	//Arraylist for which contains drawn tiles
	public static Pool P;	//Pool from which tiles are drawn
	private final int MAX_TILES_IN_FRAME = 7;	//Scrabble frame size

	//Method returns the arraylist tileFrame containing all tiles
	public ArrayList<LetterTile> getFrame(){
		return tileFrame;
	}

	//Method returns true if there are no tiles in the frame
	public Boolean isEmpty(){
		return tileFrame.size() == 0;
	}

	//Method returns the amount of tiles currently in the frame
	public int getSize(){
		return tileFrame.size();
	}

	//Method removes all tiles from frame
	public void clear() {
		tileFrame.clear();
	}

	//Constructor method
	public Frame(Pool passedInPool){
		P = passedInPool;
		refillFrame();
	}

	//Method refills frame
	public void refillFrame(){
		//Checks whether pool has tiles
		if(P.isEmpty()) throw new IllegalArgumentException("Cannot fill frame. There are no more tiles in the pool.");

		//Checks whether frame is not full
		if(tileFrame.size() == MAX_TILES_IN_FRAME) throw new IllegalArgumentException("There cannot be more than 7 tiles in frame.");

		//Draws tiles and adds them to the frame until the frame is full or no tiles remain in the pool
		while(tileFrame.size() < MAX_TILES_IN_FRAME && P.tilesLeft() > 0){
			tileFrame.add(P.randomDraw());
		}
	}

	//Method for removing a selected tile
	public void removeTile(LetterTile letter){
		if(tileFrame.isEmpty()) throw new IllegalArgumentException ("Frame is emtpy. No more tiles can be removed.");
			else tileFrame.remove(letter);
	}

	//Method returns tile at queried index
	public LetterTile getTile(int index) {
		if(index > tileFrame.size() || index < 0) throw new IllegalArgumentException ("There is no tile at index: " + index);
			else return tileFrame.get(index);
	}

	//Method returns true if frame contains queried tile
	public Boolean containsTile(LetterTile letter) {
		return tileFrame.contains(letter);
	}

	//Method for removing a tile at the selected index
	public void removeTile(int index){
		if(tileFrame.isEmpty()) throw new IllegalArgumentException ("Frame is emtpy. No more tiles can be removed.");
			else tileFrame.remove(index);
	}

	//Returns String form of frame
	public String toString(){
		String frameString = "";
		for(int i = 0; i < tileFrame.size(); i++){
			frameString += tileFrame.get(i).toString();
		}
		return frameString;
	}

	public static void main(String[] args) {
		Pool P = new Pool();
		Frame F = new Frame(P);
		System.out.println(F);
		System.out.println(F.getTile(1));

	}

}