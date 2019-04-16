
package application;

import javafx.scene.paint.Color;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class Tile extends StackPane {
    
    Button button = new Button();
    
    int x = 0;
    int y = 0;
    
    boolean hasBomb;
    int numBombs = 0;
    Color color = null;
    boolean flagged = false;
    
    ArrayList<Tile> neighbours = new ArrayList<Tile>();
    
    boolean active = true;
    
    public Tile(int x, int y, boolean hasBomb) {
        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;
        
        if (hasBomb) {
            Main.numBombs++;
        }
        
        button.setMinHeight(40);
        button.setMinWidth(40);
        
        button.setOnMouseClicked(e -> {
            IfClicked(e);
        });
        
        getChildren().addAll(button);
        
        setTranslateX(x * 40);
        setTranslateY(y * 40);
    }
    private void IfClicked(MouseEvent event) { 
        if (event.getButton() == MouseButton.PRIMARY) {
            if (!flagged) {
                button.setBackground(null);
                button.setDisable(true);
                active = false;
                
                if (hasBomb) {
                    Main.gameOver();
                } else {
                    if (this.numBombs == 0) {
                        IfBlank(this);
                    } else {
                        button.setText(Integer.toString(numBombs));
                        button.setTextFill(color);
                    }
                }
            }
        } else {
            if (!flagged) {
                
                flagged = true;
                if (this.hasBomb) {
                    Main.foundBombs++;
                    button.setText("F");
                    if (Main.foundBombs == Main.numBombs) {
                        Main.win();
                    }
                }
            } else {
                if (hasBomb) {
                    Main.foundBombs--;
                }
                button.setGraphic(null);
                flagged = false;
            }
        }
    }
    private void IfBlank(Tile tile) {
        for (int i = 0; i < tile.neighbours.size(); i++) {
            if (tile.neighbours.get(i).active) {
                tile.neighbours.get(i).button.setDisable(true);
                tile.neighbours.get(i).button.setGraphic(null);
                tile.neighbours.get(i).button.setText(Integer.toString(tile.neighbours.get(i).numBombs));
                tile.neighbours.get(i).button.setTextFill(tile.neighbours.get(i).color);
                tile.neighbours.get(i).active = false;
                if (tile.neighbours.get(i).numBombs == 0) {
                    IfBlank(tile.neighbours.get(i));
                }
            }
        }
        return;
        
    }
    
}
