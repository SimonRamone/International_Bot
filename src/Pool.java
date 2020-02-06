import java.util.ArrayList;
import java.util.Random;

public class Pool {

    // initially come up with an idea to create two arraylists
    // one which is the bag to the scrabble game which contains a 100 tiles that can be drawn
    public ArrayList<LetterTile> LetterPool = new ArrayList<LetterTile>();

    // another which contains all the possible tiles in scrabble and this list is used to get values of a tile
    // no tiles are ever drawn from this pool of tiles
    public ArrayList<LetterTile> QueryPool = new ArrayList<LetterTile>();

    // amount of tiles in a scrabble game
    private final int count = 100;

    public Pool(){
        createAndSetLetters();
        setTiles();
    }

    //  this is to set the letters in the bag of scrabble tiles.
    public void createAndSetLetters() {
        // a for loop that loops up to a 100 times which places
        // the respective amount of tiles for each letter through if...elseif statements
        for(int i = 0; i < count; i++){
            if(i <= 11){
                LetterPool.add(LetterTile.tileE);
            }
            else if(i <= 20){
                LetterPool.add(LetterTile.tileA);
            }
            else if(i <= 29){
                LetterPool.add(LetterTile.tileI);
            }
            else if(i <= 37){
                LetterPool.add(LetterTile.tileO);
            }
            else if(i <= 43){
                LetterPool.add(LetterTile.tileN);
            }
            else if(i <= 49){
                LetterPool.add(LetterTile.tileR);
            }
            else if(i <= 55){
                LetterPool.add(LetterTile.tileT);
            }
            else if(i <= 59){
                LetterPool.add(LetterTile.tileL);
            }
            else if(i <= 63){
                LetterPool.add(LetterTile.tileS);
            }
            else if(i <= 67){
                LetterPool.add(LetterTile.tileU);
            }
            else if(i <= 71){
                LetterPool.add(LetterTile.tileD);
            }
            else if(i <= 74){
                LetterPool.add(LetterTile.tileG);
            }
            else if(i <= 76){
                LetterPool.add(LetterTile.tileB);
            }
            else if(i <= 78){
                LetterPool.add(LetterTile.tileC);
            }
            else if(i <= 80){
                LetterPool.add(LetterTile.tileM);
            }
            else if(i <= 82){
                LetterPool.add(LetterTile.tileP);
            }
            else if(i <= 84){
                LetterPool.add(LetterTile.tileF);
            }
            else if(i <= 86){
                LetterPool.add(LetterTile.tileH);
            }
            else if(i <= 88){
                LetterPool.add(LetterTile.tileV);
            }
            else if(i <= 90){
                LetterPool.add(LetterTile.tileW);
            }
            else if(i <= 92){
                LetterPool.add(LetterTile.tileY);
            }
            else if(i == 93){
                LetterPool.add(LetterTile.tileK);
            }
            else if(i == 94){
                LetterPool.add(LetterTile.tileJ);
            }
            else if(i == 95){
                LetterPool.add(LetterTile.tileX);
            }
            else if(i == 96){
                LetterPool.add(LetterTile.tileQ);
            }
            else if(i == 97){
                LetterPool.add(LetterTile.tileZ);
            }
            else{
                LetterPool.add(LetterTile.tileBlank);
            }
        }

    }

    // this method is to place all the tiles available into an arraylist which can be
    // used to query for values.
    public void setTiles(){
        QueryPool.add(LetterTile.tileA);
        QueryPool.add(LetterTile.tileB);
        QueryPool.add(LetterTile.tileC);
        QueryPool.add(LetterTile.tileD);
        QueryPool.add(LetterTile.tileE);
        QueryPool.add(LetterTile.tileF);
        QueryPool.add(LetterTile.tileG);
        QueryPool.add(LetterTile.tileH);
        QueryPool.add(LetterTile.tileI);
        QueryPool.add(LetterTile.tileJ);
        QueryPool.add(LetterTile.tileK);
        QueryPool.add(LetterTile.tileL);
        QueryPool.add(LetterTile.tileM);
        QueryPool.add(LetterTile.tileN);
        QueryPool.add(LetterTile.tileO);
        QueryPool.add(LetterTile.tileP);
        QueryPool.add(LetterTile.tileQ);
        QueryPool.add(LetterTile.tileR);
        QueryPool.add(LetterTile.tileS);
        QueryPool.add(LetterTile.tileT);
        QueryPool.add(LetterTile.tileU);
        QueryPool.add(LetterTile.tileV);
        QueryPool.add(LetterTile.tileW);
        QueryPool.add(LetterTile.tileX);
        QueryPool.add(LetterTile.tileY);
        QueryPool.add(LetterTile.tileZ);
        QueryPool.add(LetterTile.tileBlank);
    }

    // this will reset the bag of tiles by calling the createAndSetLetters() function.
    public void reset(){
        LetterPool.clear();
        createAndSetLetters();
    }

    //allows to check the amount of tiles left in pool.
    public int tilesLeft(){
        return LetterPool.size();
    }

    //allows to check if the amount of tiles in the pool is empty.
    public boolean isEmpty(){
        return LetterPool.size() == 0;
    }

    // by using QueryPool arraylist, this method finds the
    // value of queried tile.
    public int getLetterScore(char letter){
        int score = 0;
        for (LetterTile letterTile : QueryPool) {
            if (letter == letterTile.getLetter()) {
                score = letterTile.getScore();
                break;
            }
        }
        return score;
    }

    // this method allows random tile to be drawn from bag
    // will reduce size of arraylist by 1
    public LetterTile randomDraw(){
        Random rand = new Random();

        int rand_int = rand.nextInt(LetterPool.size());
        LetterTile letterChosen = LetterPool.get(rand_int);
        LetterPool.remove(rand_int);

        return letterChosen;
    }

    public static void main(String[] args) {
        /*
        // some simple code to test functionality
        Pool bag = new Pool();
        System.out.println(bag.LetterPool.get(78).getLetter()); // should output C
        System.out.println(bag.getLetterScore('C')); // should be 3

        bag.randomDraw(); // initialize a draw from the bag
        System.out.println(bag.tilesLeft()); // check if there are 99 tiles left after removing one tile

        bag.reset();
        System.out.println(bag.tilesLeft());

        // check if i can draw all the tiles

        for(int i = 0; i < 100; i++){
            bag.randomDraw();
            bag.isEmpty();
        }

        System.out.println(bag.LetterPool.size());
        */

    }


}
