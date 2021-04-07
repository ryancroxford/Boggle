import java.io.IOException;
import java.util.ArrayList;
public class PhaseOneTester
{
    public static void main(String [] args) {
        Dictionary dict = new Dictionary("dictionary.txt");
        ArrayList<Tile> tiles = new ArrayList<Tile>();
// experiment with different words here to be sure the isValidWord // method (below)is working.
// NOTE: nowhere are you testing the location/adjancency of
// tiles. this will be done in the Game class, Phase 2
        tiles.add(new Tile('p',0,0));
        tiles.add(new Tile('e',0,1));
        tiles.add(new Tile('r',0,2));
        tiles.add(new Tile('f',0,3));
        tiles.add(new Tile('e',1,3));
        tiles.add(new Tile('c',1,2));
        tiles.add(new Tile('t',1,1));
        Word w;
        w = new Word(tiles);
        if (dict.isValidWord(tiles))
        {
            System.out.printf("%s is a valid word\n",w);
            System.out.printf("it is worth %d points\n",w.getPoints()); }
        else
            System.out.printf("%s is not a valid word\n",w);

        tiles.clear();  // start over
        tiles.add(new Tile('g',0,0));
        tiles.add(new Tile('r',0,1));
        tiles.add(new Tile('a',0,2));
        tiles.add(new Tile('d',0,3));
        tiles.add(new Tile('e',1,3));
        w = new Word(tiles);
        if (dict.isValidWord(tiles))
        {
            System.out.printf("%s is a valid word\n",w);
            System.out.printf("it is worth %d points\n",w.getPoints()); }
        else
            System.out.printf("%s is not a valid word\n",w);

        tiles.clear();  // start over
        tiles.add(new Tile('p',0,0));
        tiles.add(new Tile('l',0,1));
        tiles.add(new Tile('e',0,2));
        tiles.add(new Tile('a',0,3));
        tiles.add(new Tile('s',1,3));
        tiles.add(new Tile('e',1,2));
        w = new Word(tiles);
        if (dict.isValidWord(tiles))
        {
            System.out.printf("%s is a valid word\n",w);
            System.out.printf("it is worth %d points\n",w.getPoints()); }
        else
            System.out.printf("%s is not a valid word\n",w);
        Board board = new Board();
        System.out.println(board);
    }
}