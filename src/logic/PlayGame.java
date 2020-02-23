package logic;

import java.util.Scanner;

// International Bot 17205786, 18439314, 18763829
public class PlayGame {

	public static void main(String[] args) {
		Pool P = new Pool();
		
		ScrabblePlayer scrabblePlayers = new ScrabblePlayer(P);

		int numOfPlayers = 0;
		String playerName;

		Scanner q = new Scanner(System.in);
		System.out.println("Enter amount of players: ");
		numOfPlayers = Integer.parseInt(q.nextLine());
		while (numOfPlayers > 4 || numOfPlayers < 2){    //guard for min and max players, will keep asking until appropriate value is given
			if(numOfPlayers > 4 || numOfPlayers < 2) {
				System.out.println("# of players entered is invalid. Only 2 to 4 players allowed");
				numOfPlayers = Integer.parseInt(q.nextLine());
			}
			else{
				break;
			}
		}

		// set name for players
		for(int i = 0; i < numOfPlayers; i++) {
			int j = i + 1;
			System.out.println("Player " + j + " Enter your name: ");
			playerName = q.nextLine();
			scrabblePlayers.addPlayer(playerName.trim());
		}

		for(int i = 0; i < numOfPlayers; i++){
			System.out.println(scrabblePlayers.players.get(i));
			System.out.println(scrabblePlayers.players.get(i).getFrame());
		}

		Board B = new Board();
		int row;
		char col;
		String word;
		String skip;
		char orientation;

		int isFirst = 1;

		while(!P.isEmpty()) {
		for(int i = 0; i < numOfPlayers; i++){
			System.out.println(scrabblePlayers.players.get(i).getName() + "'s TURN!");
			System.out.println(scrabblePlayers.players.get(i).getFrame());

			System.out.println("Do you want to skip your turn? Y for yes, N for no.");
			skip = q.nextLine().toUpperCase();
			if(skip.equals("Y")){
				System.out.println("Next player's turn...");
			}
			else{
				System.out.println("Enter a word to place on the board:");
				word = q.nextLine();
				while(!B.wordCheck(word, scrabblePlayers.players.get(i).getFrame())){
					System.out.println("Please only use tiles from your frame");
					if(B.wordCheck(word, scrabblePlayers.players.get(i).getFrame())){
						break;
					}
					else {
						word = q.nextLine();
					}
				}

				if(!B.isFirstTurn(isFirst)){
					System.out.println("Enter the row for the first letter:");
					row = Integer.parseInt(q.nextLine());
					System.out.println("Enter the column for the first letter:");
					col = q.nextLine().charAt(0);
					int colInteger = col - 65;
					if(!B.isBound(row, colInteger)){
						while(!B.isBound(row,colInteger)){
							System.out.println("Please select coordinates that are within the scrabble board");
							System.out.println("Enter the row for the first letter:");
							row = Integer.parseInt(q.nextLine());
							System.out.println("Enter the column for the first letter:");
							col = q.nextLine().charAt(0);
							colInteger = col - 65;
						}
					}
				}
				else{
					row = 7;
					col = 'H';
					isFirst++;
				}

				System.out.println("Enter orientation, either 'v' or '>':");
				orientation = q.nextLine().charAt(0);
				B.PrintBoard();
				B.placeWord(scrabblePlayers.players.get(i), word, row, col, orientation);
				B.PrintBoard();
			}
		}

		}
//		System.out.println();
//		System.out.println("To show that removing a tile from frame, refilling frame, and number of tiles left in bag works appropriately:");
//		System.out.println("For Player 1");
//		System.out.println("Tiles left in pool: " + P.tilesLeft());
//		System.out.println("Removing a tile...");
//		scrabblePlayers.players.get(0).getFrame().removeTile(1);
//		System.out.println(scrabblePlayers.players.get(0).getFrame());
//		System.out.println("Refilling Frame...");
//		scrabblePlayers.players.get(0).getFrame().refillFrame();
//		System.out.println(scrabblePlayers.players.get(0).getFrame());
//		System.out.println("Tiles left in pool: " + P.tilesLeft());
//
//		System.out.println();
//		System.out.println("For Player 2");
//		System.out.println("Tiles left in pool: " + P.tilesLeft());
//		System.out.println("Removing a tile...");
//		scrabblePlayers.players.get(1).getFrame().removeTile(1);
//		System.out.println(scrabblePlayers.players.get(1).getFrame());
//		System.out.println("Refilling Frame...");
//		scrabblePlayers.players.get(1).getFrame().refillFrame();
//		System.out.println(scrabblePlayers.players.get(1).getFrame());
//		System.out.println("Tiles left in pool: " + P.tilesLeft());

//		scrabblePlayers.addPlayer("John");
//		System.out.println(scrabblePlayers.players.get(0));
//		System.out.println("Tiles left in pool: " + P.tilesLeft());
//		System.out.println(scrabblePlayers.players.get(0).getFrame());
//		scrabblePlayers.players.get(0).getFrame().removeTile(1);
//		System.out.println(scrabblePlayers.players.get(0).getFrame());
//		scrabblePlayers.players.get(0).getFrame().refillFrame();
//		System.out.println(scrabblePlayers.players.get(0).getFrame());
//		System.out.println("Tiles left in pool: " + P.tilesLeft());
//
//		scrabblePlayers.addPlayer("Bob");
//		System.out.println(scrabblePlayers.players.get(1));
//		System.out.println("Tiles left in pool: " + P.tilesLeft());
//		System.out.println(scrabblePlayers.players.get(1).getFrame());
//		scrabblePlayers.players.get(1).getFrame().removeTile(1);
//		System.out.println(scrabblePlayers.players.get(1).getFrame());
//		scrabblePlayers.players.get(1).getFrame().refillFrame();
//		System.out.println(scrabblePlayers.players.get(1).getFrame());
//		System.out.println("Tiles left in pool: " + P.tilesLeft());
//		scrabblePlayers.players.get(1).updateScore(100);
//		System.out.println(scrabblePlayers.players.get(1));
	}

}
