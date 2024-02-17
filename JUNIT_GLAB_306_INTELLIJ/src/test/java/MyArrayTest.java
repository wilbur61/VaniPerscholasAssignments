
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


//  Tests for getNameAtIndex()
//  Test 1 Data:
//  Input = 2
//    Expected = “Tony”
//  Test 2 Data:
//  Input = 0
//    Expected = “Mike”

//  Test for addName()
//  Note: use getNames() to check results
//  Test Data:
//  Input = “Jared”
//  Expected = Last entry of getNames() arraylist should be “Jared”

public class MyArrayTest {

    @Test
    public void getNameAtIndexTest() {
        System.out.println("getNameAtIndex Test");
        MyArray tester = new MyArray();

        assertEquals("Tony",tester.getNameAtIndex(2));
        assertEquals("Mike",tester.getNameAtIndex(0));

        tester.addEntryToArray("Jared");
        assertEquals("Jared",tester.getNameAtIndex(3));

        System.out.println("getNames= "+tester.getNames());
    }


}
