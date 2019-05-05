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
    /**
     * Method loads and sorts the file, where scores get saved 
     * @return 
     */
    public ArrayList<Points> getPoints() {
        loadPointsFile();
        sort();
        return points;
    }
    /**
     * Method sorts the points in order
     */
    private void sort() {
        HighScoreComparator comparator = new HighScoreComparator();
        Collections.sort(points, comparator);
    }
    /**
     * Method sets points to the used alias
     * @param alias
     * @param point 
     */
    public void addPoints(Alias alias, int point) {
        loadPointsFile();
        points.add(new Points(alias, point));
        updatePointsFile();

    }
    /**
     * Method loads the points.file
     */

    public void loadPointsFile() {
        try {
            input = new ObjectInputStream(new FileInputStream(POINTS_FILE));
            points = (ArrayList<Points>) input.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Load Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("CNF Error: " + e.getMessage());
        } finally {
            try {
                if (output != null) {
                    output.flush();
                    output.close();
                }
            } catch (IOException e) {
                System.out.println("IO Error: " + e.getMessage());
            }
        }
    }
    /**
     * Method sets the points to the points -file
     */

    public void updatePointsFile() {

        try {
            output = new ObjectOutputStream(new FileOutputStream(POINTS_FILE));
            output.writeObject(points);
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        } finally {
            try {
                if (output != null) {
                    output.flush();
                    output.close();
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }
    /**
     * Method searches the points -file 
     * @return returns ten highest scores
     */

    public String getHighScoreString() {
        String highScore = "";
        int max = 10;

        ArrayList<Points> points;
        points = getPoints();

        int i = 0;
        int x = points.size();
        
        if (x > max) {
            x = max;
        }
        while (i < x) {
            highScore += (i + 1) + ".\t" + points.get(i).getAlias() + "\t\t" + points.get(i).getTime() + "\n";
            i++;
        }
        return highScore;
    }
}
