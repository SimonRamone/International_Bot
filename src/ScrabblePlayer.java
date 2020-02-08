import java.util.ArrayList;
//International Bot: 17205786, 18439314, 18763829

public class ScrabblePlayer {

    public Pool bag = new Pool();

    public ArrayList<SimplePlayer> players = new ArrayList<SimplePlayer>();

    public ScrabblePlayer(){

    }

    public void addPlayer(String name){
        players.add(new SimplePlayer(new Frame(bag), name));
    }

    public void removePlayer(int i){
        players.remove(i);
    }
    //reset player will remove current player at index i,and create a new player at tht index.
    public void resetPlayer(int i, Frame f, String name){
        removePlayer(i);
        players.add(i, new SimplePlayer(f, name));
    }



    public static void main(String[] args) {

        ScrabblePlayer s = new ScrabblePlayer();
        s.addPlayer("Jack Liu    ");      //player 1
        System.out.println(s.players.get(0));
        s.players.get(0).updateScore(456);

        s.addPlayer("jerald");       //player2
        //s.resetPlayer(0, f,"bob");      // new player 1
       // System.out.println(s.players.get(0));
        System.out.println(s.players.get(0).getFrame());
        s.players.get(0).getFrame().removeTile(0);
        System.out.println(s.players.get(0).getFrame());
        System.out.println(s.players.get(0).getName());
        s.players.get(0).updateScore(100);
        System.out.println(s.players.get(0).getScore());

        System.out.println(s.players.get(1));
        System.out.println(s.players.get(1).getFrame());

    }


}

