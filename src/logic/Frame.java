package logic;

import java.util.ArrayList;
import java.lang.Character.*;

//International Bot 17205786, 18439314, 18763829

public class Frame {

	public ArrayList<LetterTile> tileFrame = new ArrayList<LetterTile>();	//Arraylist for which contains drawn tiles
	public static Pool P;	//Pool from which tiles are drawn
	private final int MAX_TILES_IN_FRAME = 7;	//Maximum number of tiles allowed in a Scrabble frame

	//Method returns the arraylist tileFrame containing all tiles
	public ArrayList<LetterTile> getFrame(){
		return tileFrame;
	}

	public Boolean isEmpty(){
		return tileFrame.size() == 0;
	}

	public int getSize(){
		return tileFrame.size();
	}

	//Method removes all tiles from frame
	public void emptyFrame() {
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

		//Checks that frame is not full
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
//		if(index > tileFrame.size()-1 || index < 0) throw new IllegalArgumentException ("There is no tile at index: " + index);
		return tileFrame.get(index);
	}
	
	public LetterTile getTile(char letter) {
		for(int i = 0; i < tileFrame.size(); i++) {
			if(tileFrame.get(i).getLetter() == letter || tileFrame.get(i).getLetter() == letter-32) {
				return tileFrame.get(i);
			}
		}
		throw new IllegalArgumentException ("Selected letter is not in frame.");
	}

	//Method returns true if frame contains queried tile
	public Boolean containsTile(LetterTile letter) {
		return tileFrame.contains(letter);
	}
	
	public Boolean containsTile(char letter) {
		for(int i = 0; i < tileFrame.size(); i++) {
			if(tileFrame.get(i).getLetter() == letter || tileFrame.get(i).getLetter() == letter-32) return true;
		}
		return false;
	}

	public int findTileByChar(char letter) {
		int index = 0;

		for(int i = 0; i < tileFrame.size(); i++) {
			if(tileFrame.get(i).getLetter() == letter || tileFrame.get(i).getLetter() == letter-32) {
				index = i;
			}
		}
		return index;
	}

	//Method for removing a tile at the selected index
	public void removeTile(int index){
		if(tileFrame.isEmpty()) throw new IllegalArgumentException ("Frame is emtpy. No more tiles can be removed.");
			else tileFrame.remove(index);
	}

	public String toString(){
		String frameString = "";
		for(int i = 0; i < tileFrame.size(); i++){
			frameString += tileFrame.get(i).toString();
		}
		return frameString;
	}

	public static void main(String[] args) {
		/*
		// Some code to test class functionality
		Pool P = new Pool();
		Frame F = new Frame(P);
		
		System.out.println(F);
		System.out.println("Number of tiles in frame: " + F.getSize());
		
		System.out.println();
		
		System.out.println("Tile at index 0:" + F.getTile(0));
		System.out.println("Tile at index 3:" + F.getTile(3));
		System.out.println("Tile at index 6:" + F.getTile(6));
		
		System.out.println();
		
		F.removeTile(3);
		System.out.println("Frame after removing tile at index 3: " + F);
		System.out.println("Number of tiles in frame: " + F.getSize());
		
		System.out.println();
		
		if(F.containsTile(LetterTile.tileE)) {
			System.out.println("Frame contains the letter E.");
			F.removeTile(LetterTile.tileE);
			System.out.println("Frame after removing one instance of the letter E: " + F);
		}
			else System.out.println("Frame does not contain the letter E.");
		System.out.println("Number of tiles in frame: " + F.getSize());
		
		System.out.println();
		
		F.refillFrame(); 
		System.out.println("Refilled frame: " + F);
		System.out.println("Number of tiles in frame: " + F.getSize());
		
		System.out.println();
		
		System.out.println("Is frame empty? " + F.isEmpty());
		F.emptyFrame();
		System.out.println("Number of tiles after emptying frame: " + F.getSize());
		System.out.println("Is frame empty? " + F.isEmpty());
		*/
	}

}