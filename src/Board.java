//Ryan Croxford
//CS 021
//Board Class of boggle game
//Creates the board from the selection of dice (randomly) and creates the output in a toString method

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board {
    //Declare Variables
    private static final int DICE_SIDES = 6;
    //Declare ArrayList of the dice with Strings
    private ArrayList<String> dice = new ArrayList<>(Arrays.asList("RIFOBX","IFEHEY","DENOWS","UTOKND","HMSRAO","LUPETS",
            "ACITOA","YLGKUE","QBMJOA","EHISPN","VETIGN","BALIYT","EZAVND","RALESC","UWILRG","PACEMD"));
    //Declare/Construct rows and columns
    private ArrayList<Tile> row1 = new ArrayList<>();
    private ArrayList<Tile> row2 = new ArrayList<>();
    private ArrayList<Tile> row3 = new ArrayList<>();
    private ArrayList<Tile> row4 = new ArrayList<>();
    private ArrayList<ArrayList<Tile>> rows = new ArrayList<>(Arrays.asList(row1,row2,row3,row4));


    /**
     * Default and only constructor that creates a board filled with random dice objects using the provided dice
     * Never uses the same die
     */
    public Board()
    {
        Random rand = new Random();
        //Runs until the dice ArrayList is empty
        while (dice.size() > 0)
        {
            //loop for each column
            for (int i = 0; i < rows.size();i++)
            {
                //loop for each row
                for (int j = 0; j < rows.size();j++)
                {
                    //Get a random die from the Array list and put a random character from it's String into a Tile
                    int diceNum = rand.nextInt(dice.size());
                    String diceLetter = String.valueOf(dice.get(diceNum).charAt(rand.nextInt(DICE_SIDES)));
                    //Specify the location of the tile based on the loops
                    Tile t = new Tile(diceLetter,i,j);
                    //add the tile to the current row
                    rows.get(i).add(t);
                    //remove the die from the ArrayList to prevent it from being used again and the loop being infinite
                    dice.remove(diceNum);
                }
            }
        }
    }

    /**
     *Return the first row ArrayList of tiles
     * @return ArrayList<Tile> row1
     */
    public ArrayList<Tile> getRow1() {
        return row1;
    }
    /**
     *Return the second row ArrayList of tiles
     * @return ArrayList<Tile> row2
     */
    public ArrayList<Tile> getRow2() {
        return row2;
    }
    /**
     *Return the third row ArrayList of tiles
     * @return ArrayList<Tile> row3
     */
    public ArrayList<Tile> getRow3() {
        return row3;
    }
    /**
     *Return the fourth row ArrayList of tiles
     * @return ArrayList<Tile> row4
     */
    public ArrayList<Tile> getRow4() {
        return row4;
    }
    /**
     *Return the ArrayList of ArrayLists of Tiles
     * @return ArrayList<Tile> rows
     */
    public ArrayList<ArrayList<Tile>> getRows() {
        return rows;
    }

    /**
     * Override the toString Method to create a good output of the Board
     * @return String
     */
    @Override
    public String toString()
    {

        StringBuilder stringBuilder = new StringBuilder();
        //Make a index header
        stringBuilder.append("  _0__1__2__3_\n");
        //Run the loop for every row
        for (int i = 0;i < rows.size();i++)
        {
            //Add the index and a | to the left side
            stringBuilder.append(i+"| ");
            for (int j = 0; j < rows.size(); j++)
            {
                String s = rows.get(i).get(j).getLetterShowing();
                //If the letter in the tile is Q, print Qu
                if (s.equals("Q")||s.equals("QU"))
                {
                    stringBuilder.append("Qu ");
                    rows.get(i).get(j).setLetterShowing("Qu");
                }
                else {
                    stringBuilder.append(s+ "  ");
                }
            }
            //Add a new line before running through the next row
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
