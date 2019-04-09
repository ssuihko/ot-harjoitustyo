
import application.Main;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {

    Main harava;
    Stage stage = new Stage();

    @Before
    public void setUp() {

        harava = new Main();
        Main.win();
    }

    @Test
    public void CreateContentToimii() {
      
        assertEquals(null, Main.createContent());
    }
}
