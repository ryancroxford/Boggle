//Ryan Croxford
//CS 110
//This is the dictionary class for the Boggle game that is constructed with a file

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary{
    //Declare the dict as a private ArrayList
    private ArrayList<String> dict;

    /**
     * Sole Constructor for the dictionary class that reads the contents of a file that is input as a parameter
     * and puts it into an ArrayList
     * @param fileName File name that is input as a  param in the main method that calls the dictionary
     * @throws IOException
     */
    public Dictionary(String fileName)
    {
        try {
            //Open the file that the program want's to build the dictionary out of
            Scanner inFile = new Scanner(new File(fileName));
            //Read file into ArrayList
            dict = new ArrayList<>();
            while (inFile.hasNext()) {
                dict.add(inFile.next());
            }
            //Close file
            inFile.close();
        }catch (IOException e){
            System.out.println("Dictionary file not found.");
        }


    }

    /**
     * This takes an ArrayList of Tile objects and turns it into a Word object then determines if it is contained
     * in the dictionary
     * @param tiles is ArrayList of Tile objects
     * @return if the inputted word is contained in the Dictionary
     */
    public boolean isValidWord(ArrayList<Tile> tiles)
    {
        Word w = new Word(tiles);
        return dict.contains(w.toString());

//        StringBuilder sb = new StringBuilder();
//        String word;
//        for (int i = 0; i < tiles.size();i++)
//        {
//            sb.append(tiles.get(i).getLetterShowing());
//        }
//        word = sb.toString();
//        System.out.println(word);
//        return dict.contains(word);
    }

    /**
     * Alternate constructor using a word object
     * @param word object from game
     * @return boolean
     */
    public boolean isValidWord(Word word){
        return dict.contains(word.toString().toLowerCase());
    }


}
