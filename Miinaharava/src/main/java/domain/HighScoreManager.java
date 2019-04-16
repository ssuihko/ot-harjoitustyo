package domain;

import java.util.*;
import java.io.*;

public class HighScoreManager {

    private static final String POINTS_FILE = "points.txt";

    public ArrayList<Points> points;

    ObjectOutputStream output = null;
    ObjectInputStream input = null;

    public HighScoreManager() {

        points = new ArrayList<Points>();

    }

    public ArrayList<Points> getPoints() {
        loadPointsFile();
        sort();
        return points;
    }

    private void sort() {
        HighScoreComparator comparator = new HighScoreComparator();
        Collections.sort(points, comparator);
    }

    public void addPoints(Alias alias, int point) {
        loadPointsFile();
        points.add(new Points(alias, point));
        updatePointsFile();

    }

    public void loadPointsFile() {
        try {
            input = new ObjectInputStream(new FileInputStream(POINTS_FILE));
            points = (ArrayList<Points>) input.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("[LOAD] Load Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[LOAD] IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[LOAD] CNF Error: " + e.getMessage());
        } finally {
            try {
                if (output != null) {
                    output.flush();
                    output.close();
                }
            } catch (IOException e) {
                System.out.println("[LOAD] IO Error: " + e.getMessage());
            }
        }
    }

    public void updatePointsFile() {
        
        try {
            output = new ObjectOutputStream(new FileOutputStream(POINTS_FILE));
            output.writeObject(points);
        } catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
            try {
                if (output != null) {
                    output.flush();
                    output.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }

    }
    
public String getHighScoreString() {
    String highScore = "";
    int max = 10;
    
    ArrayList<Points> points;
    points = getPoints();
    
    int i = 0;
    int x = points.size();
    if(x > max) {
        x = max;
    }
    while(i < x) {
        highScore += (i + 1) + ".\t" + points.get(i).getAlias() + "\t\t" + points.get(i).getTime() + "\n";
        i++;  
    }
    return highScore;
}
}
