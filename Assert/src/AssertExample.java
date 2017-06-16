
/**
 * Created by Rene Sommerfeld on 09.06.2017.
 */
public class AssertExample {

    public static void main(String[] args) {

        //Changes the PrintStream where to write the output on
        //Assert.out(System.out);

        //Creating a new test unit
        //Assert.create();

        //Naming the last created test unit. If the name of a test unit isn't set manually, a
        //name with a ordinal suffix will set.
        //Assert.name("AssertExample Name");

        //Sets the passed message of the last created test unit.
        //Assert.passedMessage("Test Has Passed");

        //Sets the failed message of the last created test unit.
        //Assert.failedMessage("Test Has Failed");

        //Sets the evaluation mode to normal. Meaning if a test should result in PASSED
        //a test will result in PASSED. (is default). If invertMode gets called, the mode
        //is switched to inverted mode and only switches back to normal if normalMode gets
        //called.
        //Assert.normalMode();

        //Sets the evaluation mode to inverted.
        //Assert.invertMode();

        //Prints the results. Should only be called after all equals methods were called.
        //Assert.results();

        //Assert has 5 inner classes. Each class handles a different kind of tests.
        //The first class handles the simple true/false statements. (Assert.Bool)

        //EXAMPLE : Something should equal to true.
        //Assert.create(); //create the test unit
        //Assert.name("Should be true"); //naming the test unit (optional)
        //Assert.Bool.assertTrue(8 == 8); //statement

        //after all tests are done, call results()

        //The second class handles the generic stuff. For example Collections, Iterable, etc. (Assert.Generic)

        //The third class handles the array stuff. For example equalization of two arrays of any primitive type. (Assert.Arrays)

        //The fourth class handles interval stuff. For example testing if a certain type of a number is in a given interval. (Assert.Interval)

        //The fifth class handles standard equals stuff. For example two primitive types are equal, two objects are equal. (Assert.Equals)

        //STRUCTURE OF ANY ASSERT TEST
        //1. call create() to create a new test unit (Assert.create())
        //2. OPTIONAL name("Any String") name the last created test unit (Assert.name("Any String"))
        //3. call any method of Assert.Bool, Assert.Generic, Assert.Arrays, Assert.Interval, Assert.Equals
        //4. FOR CREATING ANOTHER TEST start at step 1. again
        //5. IF ALL TESTS ARE REGISTERED call the results() method two print the results (Assert.results())

    }

}
