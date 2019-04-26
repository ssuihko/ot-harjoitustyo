
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.Alias;
import domain.Points;

public class PointsTest {

    Alias alias = new Alias("Alex");
    Points o = new Points(alias, 30);
    Alias nimi = new Alias("Nala");
    Points p = new Points(nimi, 25);
    
    @Test
    public void addingPointsWorks() {
        o.setTime(100);
        assertEquals(100, o.getTime());

    }
    @Test
    public void addingPointsIfFalse() {
        o.setTime(19);
        assertFalse(o.getTime() != 19);
    }
    @Test
    public void sameAliasFalse() {
        assertFalse(o.getAlias().equals(p.getAlias()));
    }
}
