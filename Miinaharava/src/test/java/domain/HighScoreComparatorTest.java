package domain;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.Points;
import domain.Alias;
import domain.HighScoreComparator;

public class HighScoreComparatorTest {

    
    public Alias a1 = new Alias("Matti");
    public Alias a2 = new Alias("Maija");
    public Points p1 = new Points(a1, 30);
    public Points p2 = new Points(a2, 50);
    
    /**
     * Test tests if the comparator can order a bigger point result before a smaller one
     */

    @Test
    public void compareFunctionalBigger() {
        HighScoreComparator comparator2 = new HighScoreComparator();
        int a;
        a = comparator2.compare(p1, p2);
        
        assertEquals(-1, a);

    }
    
    /**
     * Test tests if the comparator can order a smaller result after a bigger one
     */
    @Test
    public void compareFunctionalSmaller() {
        HighScoreComparator comparator2 = new HighScoreComparator();
        int a;
        a = comparator2.compare(p2, p1);
        
        assertEquals(1, a);
    }
    /**
     * Test tests if two same point amounts are seen as equal by the comparator
     */
    @Test 
    public void compareFunctionalEquals() {
        HighScoreComparator comparator2 = new HighScoreComparator();
        int a;
        a = comparator2.compare(p1, p1);
        
        assertEquals(0, a);
    }

}
