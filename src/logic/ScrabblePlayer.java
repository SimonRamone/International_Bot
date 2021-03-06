package logic;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
//International Bot: 17205786, 18439314, 18763829

public class ScrabblePlayer {

    public ArrayList<SimplePlayer> players = new ArrayList<SimplePlayer>();
    public int score = 0;
    public Pool scrabblePool;

    public ScrabblePlayer(Pool P){
        scrabblePool = P;
    }

    public void addPlayer(String name){ //catch if too many players entered
        if(players.size() == 4) throw new IllegalArgumentException("Maximum number of players reached, can't enter anymore players.");
        else players.add(new SimplePlayer(scrabblePool, name));
    }

    public SimplePlayer getPlayer(int i) {
    	if(players.isEmpty()) throw new IllegalArgumentException ("There are no players!");
    	return players.get(i);
    }
    
    public void removePlayer(int i){        //Catch trying to remove empty list
        if(players.isEmpty()) throw new IllegalArgumentException ("No player left. Can't remove anymore players.");
        else players.remove(i);

    }
    
	public int getWinner() {
		switch (players.size()) {
			case 2: 
				if(getPlayer(0).getScore()>getPlayer(1).getScore()) return 0;
				if(getPlayer(1).getScore()>getPlayer(0).getScore()) return 1;
				else return 99;
			case 3:
				if(getPlayer(0).getScore()>getPlayer(1).getScore() && getPlayer(0).getScore()>getPlayer(2).getScore()) return 0;
				if(getPlayer(1).getScore()>getPlayer(0).getScore() && getPlayer(1).getScore()>getPlayer(2).getScore()) return 1;
				if(getPlayer(2).getScore()>getPlayer(0).getScore() && getPlayer(2).getScore()>getPlayer(1).getScore()) return 2;
				else return 99;
			case 4:
				if(getPlayer(0).getScore()>getPlayer(1).getScore() && getPlayer(0).getScore()>getPlayer(2).getScore()&& getPlayer(0).getScore()>getPlayer(3).getScore() ) return 0;
				if(getPlayer(1).getScore()>getPlayer(0).getScore() && getPlayer(1).getScore()>getPlayer(2).getScore()&& getPlayer(1).getScore()>getPlayer(3).getScore() ) return 1;
				if(getPlayer(2).getScore()>getPlayer(0).getScore() && getPlayer(2).getScore()>getPlayer(1).getScore()&& getPlayer(2).getScore()>getPlayer(3).getScore() ) return 2;
				if(getPlayer(3).getScore()>getPlayer(0).getScore() && getPlayer(3).getScore()>getPlayer(1).getScore()&& getPlayer(3).getScore()>getPlayer(2).getScore() ) return 3;
				else return 99;
			default: 
				return 99;
		}
	}
    
    //reset player will remove current player at index i,and create a new player at that index.
    public void resetPlayer(int i, String name){
//        if (i<0|| i>players.size()-1){  java automatically throws out of bounds exception
//            throw new IllegalArgumentException("Cannot reset player as no player exist at this index");
//        }else {
            removePlayer(i);
            players.add(i, new SimplePlayer(scrabblePool, name));
            players.get(i).resetScore();

        //}
    }

    private void setScore(int i) {
        this.score = i;
    }
    public void resetScore() { // re-initiallise player's score to 0
        setScore(0);
    }




    public static void main(String[] args) {
        Frame f = new Frame(new Pool());
        Pool P = new Pool();
        ScrabblePlayer s = new ScrabblePlayer(P);

//        s.addPlayer(f, "Jack Liu    ");      //player 1
//        System.out.println(s.players.get(0));
//        s.players.get(0).updateScore(456);
//
//        s.addPlayer(f, "jerald");       //player2
//        //s.resetPlayer(0, f,"bob");      // new player 1
//       // System.out.println(s.players.get(0));
//        System.out.println(s.players.get(0).getFrame());
//        s.players.get(0).getFrame().removeTile(0);
//        System.out.println(s.players.get(0).getFrame());
//        System.out.println(s.players.get(0).getName());
//        s.players.get(0).updateScore(100);
//        System.out.println(s.players.get(0).getScore());
//        s.addPlayer("bob builder ");
//        s.players.get(0).setScore(123);
//        s.players.get(0).updateScore(26);
//        s.players.get(0).updateScore(1);
//        System.out.println(s.players.get(0).getScore());
    }

}