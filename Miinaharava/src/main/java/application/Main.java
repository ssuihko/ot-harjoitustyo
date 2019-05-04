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
import domain.HighScoreManager;
import domain.Points;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class Main extends Application {

    private static int seconds;
    private static int winSeconds;
    static int numBombs;
    static int foundBombs;
    private static Stage stage;

    private static int bombProcent = 10;
    private static int gridSize = 10;
    private static Tile[][] grid;
    public static Timer clock;
    private static VBox vbox = new VBox();
    private static HighScoreManager hm = new HighScoreManager();

    @Override
    public void init() throws Exception {

        Properties prop = new Properties();
        prop.load(new FileInputStream("config.properties"));
        String aliasFile = prop.getProperty("aliasFile");

        FileAliasDao aliasDao = new FileAliasDao(aliasFile);
    }
    
    /** 
     * Method opens the initial window
     * 
     * @param ikkuna , 
     * @throws Exception 
     */

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

        MenuItem custom = new MenuItem("Set own Percent");
        custom.setOnAction(e -> {
            TextField field = new TextField();
            Button b = new Button("OK");

            b.setOnAction(((event -> {
                bombProcent = Integer.parseInt(field.getText());
                if (bombProcent < 5) {
                    Alert notUnder = new Alert(AlertType.ERROR);
                    notUnder.setTitle("Limitation Alert");
                    notUnder.setHeaderText("You can not set the difficulty under 5%!");
                    notUnder.show();
                    
                } else {
                reload();
                }})));

            HBox k = new HBox();
            
            k.setSpacing(20);
            k.getChildren().addAll(field, b);

            Scene n = new Scene(k);
            Stage s = new Stage();
            s.setTitle("Set Percentage");
            s.setScene(n);
            s.show();

        });

        menuDifficulty.getItems().addAll(easy, medium, hard, custom);

        Menu menuAlias = new Menu("Leaderboard");
        MenuItem me = new MenuItem("Top 10");

        me.setOnAction(e -> {
            try {
                top10Screen();
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        menuAlias.getItems().addAll(me);

        menu.getMenus().addAll(menuSize, menuDifficulty, menuAlias);

        vbox.getChildren().addAll(menu, createContent());

        Scene scene = new Scene(vbox);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();

        stage.show();
    }
    /**
     * Method loads the game after a game over and resets the clock
     */

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
    /**
     * Method creates the minesweeper
     * @return 
     */

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
    /**
     * Method shows the game over screen 
     */
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
        gameOver.setHeaderText("Bomb Exploded!");
        gameOver.setContentText("Oh no! Would you like to start over?");
        gameOver.showAndWait();
        reload();
    }
    /**
     * Method shows the You won -alert
     */

    public static void win() throws Exception {
        Alert win = new Alert(AlertType.CONFIRMATION);
        win.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        win.setTitle("You won!");
        win.setHeaderText("Congratulations!");
        win.setContentText("You found the bombs in " + seconds + " seconds. You can press OK to set your score if the bomb percentage you used was 20% or over");
        winSeconds = seconds;
        Optional<ButtonType> result = win.showAndWait();
        ButtonType button = result.orElse(ButtonType.YES);

        if (button == ButtonType.CANCEL) {
            reload();
        } else if (bombProcent > 19) {
            setAlias();
            reload();
        } else {
            reload();
        }
    }
    /**
     * Method sets the alias to the received score
     */

    public static void setAlias() {
        // alias functionality 
        VBox aliasPane = new VBox(10);
        HBox inputPane = new HBox(10);
        Label aliasLabel = new Label("Set Alias");
        aliasPane.setPadding(new Insets(10));
        TextField aliasInput = new TextField();
       
        inputPane.getChildren().addAll(aliasLabel, aliasInput);
        Button createAlias = new Button("Set your Alias");

        createAlias.setOnAction((ActionEvent e) -> {
            String a = aliasInput.getText();
            Alias b = new Alias(a);
            Points p = new Points(b, winSeconds);
            hm.addPoints(p.getAlias(), p.getTime());
            try {
                top10Screen();
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        aliasPane.getChildren().addAll(inputPane, createAlias);
        Scene aliasScene = new Scene(aliasPane, 300, 250);

        Stage st = new Stage();
        st.setScene(aliasScene);
        st.show();

        // alias functionality
    }
    /**
     * Method shows the top 10 screen
     * @throws Exception 
     */
   
    public static void top10Screen() throws Exception {
        
        Stage sc2 = new Stage();
        Pane screen = new Pane();
        //
        StackPane pane = new StackPane();
        pane.setPrefSize(200, 150);
        pane.setAlignment(Pos.CENTER);
        
        String scores = hm.getHighScoreString();
        Text text = new Text(scores);
        pane.getChildren().add(text);
        
        Scene sc = new Scene(pane);
        sc2.setTitle("Top 10");
        sc2.setScene(sc);
        sc2.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}
