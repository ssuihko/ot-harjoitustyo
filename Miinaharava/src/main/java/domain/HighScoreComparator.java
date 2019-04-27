
package domain;

import java.util.Comparator;

public class HighScoreComparator implements Comparator<Points> {
    
    /**
     * Method compares two sets of points 
     * @param point1
     * @param point2
     * @return 
     */
    
    public int compare(Points point1, Points point2) {
        
        int p1 = point1.getTime();
        int p2 = point2.getTime();
        
        if (p1 > p2) {
            return +1;
        } else if (p1 < p2) {
            return -1;
        } else {
            return 0;
        }
        
    }
    
    
}
