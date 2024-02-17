import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyMathTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testAddNumbers(int number) {
        // Perform tests with different values of number
        int result = MyMath.add(number, number);
        assertEquals(number*2, result);
    }
}