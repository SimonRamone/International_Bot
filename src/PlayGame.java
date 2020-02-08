public class PlayGame {

	public static void main(String[] args) {
		Pool P = new Pool();
		ScrabblePlayer scrabblePlayers = new ScrabblePlayer(P);
		
		scrabblePlayers.addPlayer("John");
		System.out.println(scrabblePlayers.players.get(0));
		System.out.println("Tiles left in pool: " + P.tilesLeft());		
		System.out.println(scrabblePlayers.players.get(0).getFrame());
		scrabblePlayers.players.get(0).getFrame().removeTile(1);
		System.out.println(scrabblePlayers.players.get(0).getFrame());
		scrabblePlayers.players.get(0).getFrame().refillFrame();
		System.out.println(scrabblePlayers.players.get(0).getFrame());
		System.out.println("Tiles left in pool: " + P.tilesLeft());		
		
		scrabblePlayers.addPlayer("Bob");
		System.out.println(scrabblePlayers.players.get(1));
		System.out.println("Tiles left in pool: " + P.tilesLeft());		
		System.out.println(scrabblePlayers.players.get(1).getFrame());
		scrabblePlayers.players.get(1).getFrame().removeTile(1);
		System.out.println(scrabblePlayers.players.get(1).getFrame());
		scrabblePlayers.players.get(1).getFrame().refillFrame();
		System.out.println(scrabblePlayers.players.get(1).getFrame());
		System.out.println("Tiles left in pool: " + P.tilesLeft());
		scrabblePlayers.players.get(1).updateScore(100);
		System.out.println(scrabblePlayers.players.get(1));
	}

}
