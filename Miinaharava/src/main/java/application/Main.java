/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType; 
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import dao.AliasDao;
import domain.Alias;
import dao.FileAliasDao;
import java.util.Properties;
import java.io.FileInputStream;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Main extends Application {

    private static int seconds;
    static int numBombs;
    static int foundBombs;
    private static Stage stage;

    private static int bombProcent = 10;
    private static int gridSize = 10;
    private static Tile[][] grid; 
    public static Timer clock;
    private static VBox vbox = new VBox();

    @Override
    public void init() throws Exception {
        
        Properties prop = new Properties();
        prop.load(new FileInputStream("config.properties"));
        String aliasFile = prop.getProperty("aliasFile");

        FileAliasDao aliasDao = new FileAliasDao(aliasFile);
    }

    @Override
    public void start(Stage ikkuna) throws Exception {

        grid = new Tile[gridSize][gridSize];

        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                seconds++;
            }
        };

        clock = new Timer();

        clock.scheduleAtFixedRate(timer, 1000, 1000);

        stage = ikkuna;

        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        stage.setTitle("Minesweeper");
        //
        Label Alias = new Label("Your alias: ");
        TextField input = new TextField();
        //
        MenuBar menu = new MenuBar();

        Menu file = new Menu("File");

        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction(e -> {
            Platform.exit();
        });

        file.getItems().addAll(quit);

        Menu menuSize = new Menu("Size");
        MenuItem ten = new MenuItem("10x10");
        ten.setOnAction(e -> {
            gridSize = 10;
            reload();
        });
        MenuItem fifteen = new MenuItem("15x15");
        fifteen.setOnAction(e -> {
            gridSize = 15;
            reload();
        });
        MenuItem twenty = new MenuItem("20x20");
        twenty.setOnAction(e -> {
            gridSize = 20;
            reload();
        });

        menuSize.getItems().addAll(ten, fifteen, twenty);

        Menu menuDifficulty = new Menu("Difficulty");
        MenuItem easy = new MenuItem("Easy - 10% Bombs");
        easy.setOnAction(e -> {
            bombProcent = 10;
            reload();
        });
        MenuItem medium = new MenuItem("Medium - 15% Bombs");
        medium.setOnAction(e -> {
            bombProcent = 15;
            reload();
        });
        MenuItem hard = new MenuItem("Hard - 20% Bombs");
        hard.setOnAction(e -> {
            bombProcent = 20;
            reload();
        });
        menuDifficulty.getItems().addAll(easy, medium, hard);

        Menu menuAlias = new Menu("Leaderboard");
        MenuItem me = new MenuItem("Best scores");
        
        menuAlias.getItems().addAll(me);

        menu.getMenus().addAll(menuSize, menuDifficulty, menuAlias);

        vbox.getChildren().addAll(menu, createContent());

        Scene scene = new Scene(vbox);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();

        stage.show();
    }

    public static void reload() {

        grid = new Tile[gridSize][gridSize];

        seconds = 0;

        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                seconds++;
            }
        ;
        };
        
        clock.cancel();
        clock = new Timer();
        clock.schedule(timer, 1000, 1000);
        vbox.getChildren().remove(1);
        vbox.getChildren().add(createContent());
        stage.sizeToScene();

    }

    public static Parent createContent() {

        Pane root = new Pane();
        root.setPrefSize(gridSize * 40, gridSize * 40);

        numBombs = 0;
        foundBombs = 0;

        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {

                Tile tile = new Tile(x, y, Math.random() < (double) bombProcent / 100);

                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }

        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {

                int NNB = 0;
                ArrayList<Tile> neighbours = new ArrayList<Tile>();
                int[] points = new int[]{
                    -1, -1,
                    -1, 0,
                    -1, 1,
                    0, -1,
                    0, 1,
                    1, -1,
                    1, 0,
                    1, 1
                };

                for (int i = 0; i < points.length; i++) {
                    int dx = points[i];
                    int dy = points[++i];

                    int newX = x + dx;
                    int newY = y + dy;

                    if (newX >= 0 && newX < gridSize && newY >= 0 && newY < gridSize) {
                        neighbours.add(grid[newX][newY]);
                        if (grid[newX][newY].hasBomb) {
                            NNB++;
                        }
                    }
                }

                grid[x][y].numBombs = NNB;
                grid[x][y].neighbours = neighbours;

                Color[] colors = {null, Color.BLUE, Color.GREEN, Color.RED, Color.DARKBLUE, Color.DARKRED, Color.CYAN,
                    Color.BLACK, Color.DARKGRAY};

                grid[x][y].color = colors[grid[x][y].numBombs];

            }

        }
        return root;
    }

    public static void gameOver() {
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                if (grid[x][y].hasBomb) {
                    grid[x][y].button.setDisable(true);
                }
            }
        }

        Alert gameOver = new Alert(AlertType.INFORMATION);
        gameOver.setTitle("Game Over!");
        gameOver.setHeaderText("Bomb Explosion");
        gameOver.setContentText("Oh no! Wanna start over?");
        gameOver.showAndWait();
        reload();
    }

    public static void win() {
        Alert win = new Alert(AlertType.CONFIRMATION);
        win.setTitle("You won!");
        win.setHeaderText("Congratulations!");
        win.setContentText("You found the bobms in " + seconds + " seconds.");
        win.showAndWait();
        reload();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
