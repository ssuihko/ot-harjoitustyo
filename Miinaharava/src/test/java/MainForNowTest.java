
import com.mycompany.miinaharava.MainForNow;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.application.Application;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainForNowTest {

    MainForNow harava;

    @Before
    public void setUp() {

        harava = new MainForNow();
       
    }

    @Test
    public void oliPommiTulostus() {

        assertEquals("Game Over", harava.GameOver());

    }
    @Test 
    public void PaneJaCreateContent() {
        Parent h = harava.createContent();
        
        assertEquals(Pane.class, h.getClass());
        
    }
}
