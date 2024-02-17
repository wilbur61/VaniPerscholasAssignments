
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IsGreaterTest {

    private IsGreater isGreater;
    @BeforeEach
    @DisplayName("Before run test case to init resource")
    void init() {
        isGreater= new IsGreater();
    }
    @Test
    public void isGreaterTest() {
        System.out.println("Test");
        IsGreater tester = new IsGreater();
        assertTrue(tester.isGreater(11, 90)," ITS TRUE.. ITS TRUE");
    }

    @AfterEach
    @DisplayName("Finish Test to destroy resource")
    void tearDown() {
        isGreater = null;
    }
}
