//Ryan Croxford
//CS 110
//GUI for the boggle project
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import javax.swing.text.Style;
import java.awt.*;

import static javafx.application.Application.launch;

public class BoggleGUI extends Application
{

    private Game game;
    private BorderPane mainPane;
    private GridPane grid;
    private VBox buttonPane,scorePane,endGamePane,titlePane;
    private HBox messagePane,finalButtonPane;
    private Text title, score,endGameText,message;
    private String styleSheet;
    private ObservableList<String> oListWords;
    private ListView<String> wordListView;
    private Button endGame, newGame, submitWord, exit;

    @Override
    public void start(Stage mainStage){
        //Set up the Board
        mainStage.setTitle("Boggle");//Set Window Title
        mainPane = new BorderPane();//Make main structure
        mainPane.getStyleClass().add("game");
        grid = new GridPane(); //make grid for the letters
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(5);
        grid.setHgap(5);
        game = new Game(); //Start a new game
        drawBoard(); //Draw the board of tiles
        grid.setAlignment(Pos.CENTER); //Align the grid in the center
        mainPane.setCenter(grid); //Put the grid in the center of the BorderPane

        //Set up the title
        title = new Text("Boggle");//Set the title text to boggle
        title.getStyleClass().add("title");
        titlePane = new VBox(); //Make a pane for the title to go in
        titlePane.setMinHeight(75);
        titlePane.setAlignment(Pos.CENTER);
        titlePane.getChildren().add(title);//Add the title to the Pane
        mainPane.setTop(titlePane); //Add the title pane to the borderPane

        //Set up the score
        scorePane = new VBox(10);//Make a pane for the score and words used
        scorePane.setMinWidth(150);
        scorePane.setAlignment(Pos.CENTER);
        scorePane.setPadding(new Insets(0,10,0,10));
        score = new Text("Score: "+game.getScore());
        score.getStyleClass().add("messageText");
        scorePane.getChildren().add(score);

        //Make an observable list for to be displayed with a listView
        oListWords = FXCollections.observableArrayList();
        wordListView = new ListView<>(oListWords);
        wordListView.getStyleClass().add("wordList");
        wordListView.setPrefWidth(150);
        wordListView.setPrefHeight(135);
        scorePane.getChildren().add(wordListView); //Add the listview to the scorePane
        mainPane.setLeft(scorePane); //Add the score pane to the borderPane

        //Make the button pane and all the buttons, including their functions
        buttonPane = new VBox(15);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setMinWidth(150);
        buttonPane.setPadding(new Insets(0,10,0,10));
        finalButtonPane =new HBox(10);
        finalButtonPane.setAlignment(Pos.CENTER);
        message = new Text();
        message.getStyleClass().add("messageText");
        //SubmitWord button set up and implementation
        submitWord = new Button("Submit Word");
        submitWord.getStyleClass().add("button");
        submitWord.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                game.testSelected();
                drawBoard();
                if(game.getWords().size() > 0 && !oListWords.contains(game.getWords().get(game.getWords().size()-1))){
                    oListWords.add(game.getWords().get(game.getWords().size()-1));
                }else{
                    message.setText("Invalid Word");
                }
                score.setText("Score: "+game.getScore());
            }
        });
        //Exit button set up and implementation
        exit = new Button("Exit");
        exit.getStyleClass().add("button");
        exit.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Platform.exit();
            }
        });
        //Exit button and implementation
        endGame = new Button("End Game");
        endGame.getStyleClass().add("button");
        endGameText = new Text();
        endGameText.getStyleClass().add("messageText");
        endGamePane = new VBox(10);
        endGamePane.setAlignment(Pos.CENTER);
        endGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               mainPane.setCenter(endGamePane);
               endGamePane.setMinHeight(300);
               endGamePane.setMinWidth(300);
               endGameText.setText("Thanks for Playing! \n" +"Total Score: "+game.getScore()+"\nWords found:");
               endGameText.setTextAlignment(TextAlignment.CENTER);
               endGameText.setFont(Font.font("Arial",18));
               finalButtonPane.getChildren().addAll(newGame,exit);
               endGamePane.getChildren().addAll(endGameText,wordListView,finalButtonPane);
               buttonPane.getChildren().removeAll(endGame,submitWord,newGame);
               scorePane.getChildren().removeAll(score,wordListView);
            }
        });
        //New Game button and implementation
        newGame = new Button("Start new game");
        newGame.getStyleClass().add("button");
        newGame.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                game = new Game();
                drawBoard();
                oListWords.clear();
                score.setText("Score: "+game.getScore());
                if(!endGamePane.getChildren().isEmpty()) {
                    finalButtonPane.getChildren().removeAll(newGame,exit);
                    endGamePane.getChildren().removeAll(endGameText, wordListView, finalButtonPane);
                    buttonPane.getChildren().add(submitWord);
                    buttonPane.getChildren().addAll(endGame, newGame);
                    scorePane.getChildren().addAll(score, wordListView);
                    mainPane.setCenter(grid);
                }
            }
        });
        buttonPane.getChildren().add(submitWord);
        buttonPane.getChildren().addAll(endGame,newGame); //Add buttons to pane
        mainPane.setRight(buttonPane); //Add buttonPane to the borderPane

        //Create message and Message pane to give feedback to the user
        message = new Text("");
        message.setFont(Font.font("Arial",18));
        messagePane = new HBox();
        messagePane.setAlignment(Pos.CENTER);
        messagePane.setMinHeight(50);
        messagePane.getChildren().add(message);
        mainPane.setBottom(messagePane);

        //Add to scene
        Scene scene = new Scene(mainPane);
        styleSheet = "BoggleStyle.css";
        scene.getStylesheets().add(styleSheet);

        //Add shadow to Panes
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setColor(Color.BLACK);
        //grid.setEffect(ds);
        title.setEffect(ds);
        wordListView.setEffect(ds);
        //buttonPane.setEffect(ds);
        endGameText.getStyleClass().add("messageText");


        mainStage.setScene(scene);
        mainStage.show();

    }

    /**
     * Draws the board of tilePane objects
     */
    private void drawBoard() {
        grid.getChildren().clear();
        grid.getStyleClass().add("grid");
        for(int r = 0; r < game.getGameBoard().getRows().size();r++)
        {
            for(int c = 0; c < game.getGameBoard().getRows().size();c++){
                TilePane tp = new TilePane(game.getTile(r,c));
                tp.getStyleClass().add("tile");
                if(!tp.getTile().getTileUsed()){
                   tp.setOnMouseClicked(this::handleSelect);
                   tp.getStyleClass().add("unused");
                }
                else if(tp.getTile().getTileUsed()){
                    tp.setOnMouseClicked(this::handleDeselect);
                    tp.getStyleClass().add("used");
                }
                grid.add(tp,c,r);
            }
        }
    }

    /**
     * Handles the selection of tiles on the board
     * @param mouseEvent
     */
    private void handleSelect(MouseEvent mouseEvent) {
        TilePane tp = (TilePane)(mouseEvent.getSource());
        Tile tile = game.getTile(tp.getTile().getRow(),tp.getTile().getColumn());
        if(!tile.getTileUsed()) {
            if (game.isValidSelection(tile.getRow(), tile.getColumn())) {
                game.addToSelected(tile.getRow(), tile.getColumn());
                tile.setTileUsed(true);
                drawBoard();
                message.setText("");
            }else {
                message.setText("Invalid selection");
            }
        }

    }

    /**
     * Handles deselection of the last tile to be selected
     * @param mouseEvent
     */
    private void handleDeselect(MouseEvent mouseEvent) {
        TilePane tp = (TilePane)(mouseEvent.getSource());
        Tile tile = game.getTile(tp.getTile().getRow(),tp.getTile().getColumn());
        if(tile.equals(game.getSelectedTiles().get(game.getSelectedTiles().size()-1))){
            game.removeFromSelected(tile.getRow(), tile.getColumn());
            tile.setTileUsed(false);
            drawBoard();
        }
    }


    public static void main(String [] args) {
        launch(args);
    }
}
