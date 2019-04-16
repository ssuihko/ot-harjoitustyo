
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.Points;
import domain.HighScoreComparator;
import java.io.File;
import java.io.FileWriter;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import domain.Alias;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import domain.HighScoreManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class HighScoreManagerTest {

    public HighScoreManager manager = new HighScoreManager();
    public HighScoreComparator com = new HighScoreComparator();
    ArrayList<Points> p = new ArrayList<>();

    ObjectOutputStream output = null;
    ObjectInputStream input = null;
    public Alias b = new Alias("Bert");
    public Alias c = new Alias("Celine");

    @Test
    public void sortFunctional() {

    }

    @Test
    public void getPointsFunctional() {

        Alias ada = new Alias("ada");
        manager.addPoints(ada, 30);

        // assertEquals(30, manager.getPoints().toArray());
    }

    @Test
    public void loadFileFunctional() {

    }

    @Test
    public void updateFileFunctional() {

    }

    @Test(expected = FileNotFoundException.class)
    public void notFound() throws Exception {
        input = new ObjectInputStream(new FileInputStream(""));

    }

    @Test
    public void outputFlushandClose() {

    }

    @Test
    public void updateFileWorks() {
        // Points o = new Points(b, 20);

        Points i = new Points(c, 70);
        manager.points.add(i);

        manager.points.clear();
        manager.updatePointsFile();

        manager.points.add(i);

        String a = manager.getHighScoreString();
        assertEquals("", a.toString());
    }
    @Test
    public void loadFileWorks() {
        manager.loadPointsFile();
        Points point = new Points(b, 30);
        manager.points.add(point);
     
        
       // assertEquals()
    }

}
//expected:<1.[ Celine 70 2. Bert 20 ]> but was:<1.[ Celine  70 2. Bert  20 ]>
