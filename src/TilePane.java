//Ryan Croxford
//CS 110
//TilePane class for the boggle project

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TilePane extends HBox {
    private Tile tile;
    private Text displayLetter;

    /**
     * Default constructor for TilePane
     * @param tile object that is created by game
     */
    public TilePane(Tile tile) {
        //this.setStyle("-fx-border-width: 5;" + "-fx-border-color: lightblue;" + "-fx-border-radius: 10px;");
        this.setPrefSize(75, 75);
        this.setAlignment(Pos.CENTER);
        this.tile = new Tile(tile);
        setTilePane(this.tile.getLetterShowing());
    }

    /**
     * Sets the letter on the GUI based on the letter of the Tile
     * @param letterShowing
     */
    public void setTilePane(String letterShowing){
        displayLetter = new Text("");
        displayLetter.setStyle("-fx-fill: #539bb4;");
        //displayLetter.setFont(Font.font("Arial",36));
            if(letterShowing.equals("Q")){
                displayLetter.setText("Qu");
            }
            else{
                displayLetter.setText(letterShowing);
            }
            this.getChildren().add(displayLetter);
    }

    /**
     * Get the tile object from the Pane
     * @return tile Tile
     */
    public Tile getTile() {
        return tile;
    }

}

