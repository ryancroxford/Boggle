//Ryan Croxford
//CS 110
//This is the game class that creates a game of boggle and stores the underlying operations

import java.util.ArrayList;

public class Game {
    //Declare private variables
    private Board gameBoard;
    private ArrayList<Tile> selectedTiles;
    private ArrayList<String> words;
    private int score;
    //Create a dictionary for the game
    private Dictionary dict = new Dictionary("dictionary.txt");

    /**
     * Default constructor for Game
     * Creates a new board, selected tile list, word list, and score.
     */
    public Game() {
        gameBoard = new Board();
        selectedTiles = new ArrayList<>();
        words = new ArrayList<>();
        score = 0;

    }

    /**
     * Determines if the tile is valid to add to the selected tiles list
     * @param row int
     * @param col int
     * @return Boolean (True if the Tile is a valid selection)
     */
    public Boolean isValidSelection(int row, int col) {


        //Test if the row and column entered is even on the board
        if ((row < gameBoard.getRows().size() && row >= 0) && (col < gameBoard.getRows().size() && col >= 0)) {
            //If there was a tile selected before this tile, make sure the current tile is adjacent
            if (selectedTiles.size() > 0) {
                //make the bounds of the tile minus 1 to plus 1 the position of the rows and columns
                int lastRow = selectedTiles.get(selectedTiles.size() - 1).getRow();
                int rowBehind = selectedTiles.get(selectedTiles.size() - 1).getRow() - 1;
                int rowAhead = selectedTiles.get(selectedTiles.size() - 1).getRow() + 1;
                int lastCol = selectedTiles.get(selectedTiles.size() - 1).getColumn();
                int aboveCol = selectedTiles.get(selectedTiles.size() - 1).getColumn() - 1;
                int belowCol = selectedTiles.get(selectedTiles.size() - 1).getColumn() + 1;

                //Test if it is adjacent to the previously selected tile
                //return true because both if statements are met
//return false because it is not adjacent
                return (selectedTiles.size() > 0) && (row == lastRow || row == rowBehind || row == rowAhead)
                        && (col == lastCol || col == aboveCol || col == belowCol);

            }else{
                //return true because it is a valid tile and there has been nothing selected before it
                return true;
            }
        }else {
            //return false because it is not in the board
            return false;
        }
    }

    /**
     * Get the tile from the Board to add to the list
     * @param row
     * @param col
     * @return Tile object
     */
    public Tile getTile(int row, int col){
        return gameBoard.getRows().get(row).get(col);
    }

    /**
     * Get the ArrayList of words for the GUI to display
     * @return ArrayList of Words
     */
    public ArrayList<String> getWords() {
        return words;
    }

    /**
     * Returns a list of selected tiles
     * @return ArrayList of tiles
     */
    public ArrayList<Tile> getSelectedTiles(){
        return selectedTiles;
    }

    /**
     * Get the score for the user to see
     * @return Score
     */
    public int getScore() {
        return score;
    }

    /**
     * Add the tile based on coordinates, to the selected tile list
     * @param row
     * @param col
     */
    public void addToSelected(int row, int col){
        selectedTiles.add(getTile(row, col));
    }

    /**
     * Remove tile from selected list based on position
     * @param row
     * @param col
     */
    public void removeFromSelected(int row, int col){
        selectedTiles.remove(getTile(row, col));
    }

    /**
     * Completely clear the selectTiles list
     */
    public void clearSelected(){
        selectedTiles.clear();
    }

    /**
     * Test to see if the list of selected tiles is in the dictionary, and add the amount of points given to that word
     */
    public void testSelected(){
        Word word = new Word(selectedTiles);
        for(int i = 0;i<selectedTiles.size();i++){
            selectedTiles.get(i).setTileUsed(false);
        }
        selectedTiles.clear();
        if (dict.isValidWord(word)){
            if(words.contains(word.toString())) {
            }
            else{
                score += word.getPoints();
                words.add(word.toString());
            }
        }

    }

    /**
     * Get gameBoard for GUI to print
     * @return Board gameBoard
     */
    public Board getGameBoard() {
        return gameBoard;
    }

    /**
     * This outputs the game board, selected list, word list, and score
     * @return String of the game
     */
    @Override
    public String toString(){
        return gameBoard.toString() + "\nSelected" + selectedTiles + "\nWords:"+ words +"\nScore: " + score+"\n";
    }

}
