import java.util.Scanner;

public class ScrabblePlayer {
    private static int num;
    public String name;
    public int score;
    public Frame player_frame;

    public ScrabblePlayer(Frame f, String n){
        player_frame = f;
        name = n;
    }

    public Frame getFrame(){
        return player_frame;
    }

    public void setName(){      //setName function, takes in username from scanner.
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter player Name: \n");
        name = scan.nextLine();
        name = name.trim();			//trims name so that there are no white spaces before or after the name string.
    }

    public void setScore() {    //initialises player score to 0
        score = 0;
    }     //initialises player score.

    public void resetName() {
        setName();
    }   //both resets are basically calling initialisation function again.

    public void resetScore() {
        setScore();
    }

    @Override
    public String toString() {
        String text = "No user inputted";
        int count = 0;
        if (name != "") {
            text = "playerName: " + getName() + "\nPlayer Score: " + getScore();
        }
        return text;
    }

    public void updateScore(int wordScore){
        score += wordScore; //based on the pool and frame methods, player's score will add
    }                           // with the value of the word.
    private String getName() {
        return name.trim();
    }

    private int getScore() {
        return score;
    }

    public static void main(String[] args) {
       // ScrabblePlayer[] players = new ScrabblePlayer[4];
//
//        numOfPlayers();
//        for (int i=0; i<num;i++ ) {
//            players[i] = new ScrabblePlayer();
//            players[i].setName();
//            players[i].setScore();
//        }
//
//        System.out.println("there will be " + num + " players");
//
//        for (int i=0; i<num;i++ ) {
//            System.out.println(players[i].toString());
//
//        }
    }

    private static void numOfPlayers() {    //function to set the amount of players
        Scanner q = new Scanner(System.in);
        System.out.println("Enter amount of players: ");
        num = q.nextInt();
        if (num > 4 || num < 0){    //guard for incase
            throw new IllegalArgumentException("# of players entered is invalid ");
        }
    }

}

