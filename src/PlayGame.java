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
		for(int i = 0; i < numOfPlayers; i++){
			int j = i + 1;
			System.out.println("Player " + j + " Enter your name: ");
			playerName = q.nextLine();
			scrabblePlayers.addPlayer(playerName.trim());
		}
		
		q.close();

		for(int i = 0; i < numOfPlayers; i++){
			System.out.println(scrabblePlayers.players.get(i));
			System.out.println(scrabblePlayers.players.get(i).getFrame());
		}

		System.out.println();
		System.out.println("To show that removing a tile from frame, refilling frame, and number of tiles left in bag works appropriately:");
		System.out.println("For Player 1");
		System.out.println("Tiles left in pool: " + P.tilesLeft());
		System.out.println("Removing a tile...");
		scrabblePlayers.players.get(0).getFrame().removeTile(1);
		System.out.println(scrabblePlayers.players.get(0).getFrame());
		System.out.println("Refilling Frame...");
		scrabblePlayers.players.get(0).getFrame().refillFrame();
		System.out.println(scrabblePlayers.players.get(0).getFrame());
		System.out.println("Tiles left in pool: " + P.tilesLeft());

		System.out.println();
		System.out.println("For Player 2");
		System.out.println("Tiles left in pool: " + P.tilesLeft());
		System.out.println("Removing a tile...");
		scrabblePlayers.players.get(1).getFrame().removeTile(1);
		System.out.println(scrabblePlayers.players.get(1).getFrame());
		System.out.println("Refilling Frame...");
		scrabblePlayers.players.get(1).getFrame().refillFrame();
		System.out.println(scrabblePlayers.players.get(1).getFrame());
		System.out.println("Tiles left in pool: " + P.tilesLeft());

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
