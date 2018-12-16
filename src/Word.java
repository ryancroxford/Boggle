//Ryan Croxford
//CS 110
//Word class for boggle game that creates a word with a given ArrayList, and then stores it's value
import java.util.ArrayList;

public class Word {
    //Declare variables
    private String word;
    private int points;

    /**
     * Default constructor, makes a string from an arraylist and calculates a score based off the length of the word
     * @param entry ArrayList
     */
    public Word(ArrayList<Tile> entry)
    {
        //Store ArrayList as a String
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < entry.size();i++)
        {
            sb.append(entry.get(i).getLetterShowing());
        }

        word = sb.toString();

        //Set the score
        int wordLength = word.length();
        if (wordLength < 3)
            points = 0;
        if (wordLength == 3 || wordLength == 4)
            points = 1;
        if (wordLength == 5)
            points = 2;
        if (wordLength == 6)
            points = 3;
        if (wordLength == 7)
            points = 5;
        if (wordLength >= 8)
            points = 11;


    }

    /**
     * Return the value of the points
     * @return int points
     */
    public int getPoints()
    {
        return points;
    }

    /**
     *Overrides the toString Method and returns the String variable word
     * @return word String
     * @Override toString method
     */
    @Override
    public String toString()
    {
        return (word.toUpperCase());
    }
}
