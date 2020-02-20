package Logic;

//International Bot: 17205786, 18439314, 18763829
public class SimplePlayer {
    public String name;
    public int score;
    public Frame playerFrame;

    public SimplePlayer(Pool P, String name ){
        playerFrame = new Frame(P);
        this.name = name;
        resetScore();
    }

    public Frame getFrame(){
        return playerFrame;
    }

    public void setName(String name){
//        Scanner scan = new Scanner(System.in);
//        System.out.print("Enter player Name: \n");
//        name = scan.nextLine();
        this.name = name.trim();
    }

    public void setScore(int score) {  //at the moment we can set it to any number, just for testing.
        this.score = score;         //later on with updatescore function being complte, code will be better
    }

    public void resetScore() { // re-initiallise player's score to 0
        setScore(0);
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
    }                           // with the value of the word, wordscore method will be implemented later
    public String getName() {
        return name.trim();     //return trimmed name
    }

    public int getScore() {
        return score;
    }

}