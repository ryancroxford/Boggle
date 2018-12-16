//Ryan Croxford
//CS 110
//This is the Tile object for the boggle project
//It stores the letter, row, and column of the tile

public class Tile {
    //Declare private variables
    private String letterShowing;
    private int row;
    private int column;
    private boolean tileUsed;

    /**
     * This is constructor takes a string and two ints, and stores the values of the letter, row, and column, and flag
     * @param letter String value
     * @param row int value for vertical position
     * @param column int value for horizontal position
     */
    public Tile(String letter, int row, int column)
    {
        letterShowing = letter;
        this.row = row;
        this.column = column;
        tileUsed = false;
    }
    /**
     * This is constructor takes a string and two ints, and stores the values of the letter, row, and column, and flag
     * @param letter char value that is converted to a string in order to be stored in this class
     * @param row int value for vertical position
     * @param column int value for horizontal position
     */
    public Tile(char letter, int row, int column)
    {
        //String s = String.valueOf(letter).toLowerCase();


        letterShowing = String.valueOf(letter);
        this.row = row;
        this.column = column;
        tileUsed = false;
    }

    /**
     * Copy constructor
     * @param tile takes a Tile object to copy it
     */
    public Tile(Tile tile)
    {
        letterShowing = tile.getLetterShowing();
        row = tile.getRow();
        column = tile.getColumn();
        tileUsed = tile.getTileUsed();
    }

    /**
     * To build words
     * @return String value of the letter showing
     */
    public String getLetterShowing()
    {
        return letterShowing;
    }
    public void setLetterShowing(String qCase){
        letterShowing = qCase;
    }

    /**
     * To get the value of the which row it is in
     * @return row String
     */
    public int getRow(){
        return row;
    }

    /**
     * To get the value of which column it is in
     * @return
     */
    public int getColumn(){
        return column;
    }

    /**
     * To see if the tile has been selected already
     * @return boolean Flag
     */
    public boolean getTileUsed()
    {
        return tileUsed;
    }

    /**
     * For GUI to set if the tile has been clicked
     * @param tileUsed true or false
     */
    public void setTileUsed(boolean tileUsed) {
        this.tileUsed = tileUsed;
    }

    /**
     * Override toString method in order to display tiles in the ArrayList
     * @return String letterShowing
     */
    @Override
    public String toString(){
        //If the letter character is q return it as a qu string in the for the Tile object
        if (letterShowing.equals("Q") || letterShowing.equals("q")){
            letterShowing = "QU";
        }
        return letterShowing;

    }
}
