import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rene Sommerfeld on 09.06.2017.
 */
public final class Assertion {

    /**
     * Defines the default test name prefix for each test units name
     */
    public static final String TEST_DEFAULT_NAME_PREFIX = "Test ";

    /**
     * Defines the default failed message for a test unit
     */
    public static final String TEST_DEFAULT_FAILED_MESSAGE = "FAILED";

    /**
     * Defines the default passed message for a test unit
     */
    public static final String TEST_DEFAULT_PASSED_MESSAGE = "PASSED";

    /**
     * References the singleton instance of this class
     */
    private static Assertion instance;

    /**
     * Holds each registered test unit
     */
    private List<Test> tests;

    /**
     * Defines the index of the current created test
     */
    private int currentTest;

    /**
     * Private constructor in order to stick to the rules
     * of singleton pattern
     */
    private Assertion() {
        tests = new ArrayList<>();
        currentTest = -1;
    }

    /**
     * Returns the singleton instance.
     * @return the singleton instance
     */
    public static Assertion getInstance() {
        if(instance == null) {
            instance = new Assertion();
        }
        return instance;
    }

    /**
     * Creates a new test unit. Any call of the methods
     * like {@link #nameTest(String)}, {@link #setPassedMessage(String)},
     * or {@link #setFailedMessage(String)} will refer to the test unit created
     * by invoking this method.
     */
    public void newTest() {
        currentTest++;
        tests.add(new Test(TEST_DEFAULT_NAME_PREFIX + currentTest));
    }

    /**
     * Returns whether or not a test unit is available.
     * @return true if a test unit is available, otherwise throws
     * a {@code {@link NoSuchAssertionTestException}} if the instance isn't
     * created yet.
     */
    private boolean isTestAvailable() {
        if(currentTest >= 0) {
            return true;
        } else {
            throw new Assertion.NoSuchAssertionTestException("There is no Assertion test unit to name. " +
                    "Please invoke newTest method.");
        }
    }

    /**
     * Names the current test created by {@link #newTest()}.
     * @param name the name of the test unit
     */
    public void nameTest(String name) {
        if(isTestAvailable()) {
            tests.get(currentTest).setName(name);
        }
    }

    /**
     *
     * @param passedMessage
     */
    public void setPassedMessage(String passedMessage) {
        if(isTestAvailable()) {
            tests.get(currentTest).setPassedStatusMessage(passedMessage);
        }
    }

    /**
     *
     * @param failedMessage
     */
    public void setFailedMessage(String failedMessage) {
        if(isTestAvailable()) {
            tests.get(currentTest).setFailedStatusMessage(failedMessage);
        }
    }

    /**
     * Prints the results
     */
    public void printResults() {
        if(isTestAvailable()) {
            for(Test test : instance.tests) {
                System.out.println("########");
                System.out.println(test);
                System.out.println("########");
            }
        }
    }



    /**
     *
     * @param actual
     */
    public void assertTrue(boolean actual) {
        if(actual) {
            markCurrentAsPassed();
        } else {
            markCurrentAsFailed();
        }
    }

    public void assertFalse(boolean actual) {
        assertTrue(!actual);
    }

    /**
     *
     * @param actual
     */
    public void assertNull(Object actual) {
        assertTrue(actual == null);
    }

    /**
     *
     * @param expected
     * @param actual
     */
    public void assertEquals(String expected, String actual) {
        if(expected != null && actual != null) {
            assertTrue(expected.equals(actual));
        } else {
            setCurrentFailureMessage(Test.FAILURE_NULL_ARRAY_REFERENCE);
            markCurrentAsFailed();
        }
    }

    /**
     *
     * @param expected
     * @param actual
     * @param <T>
     */
    public <T> void assertEquals(T[] expected, T[] actual) {
        if(expected != null && actual != null) {
            if(expected.length == actual.length) {
                for(int i = 0; i < expected.length; i++) {
                    if(expected[i] != null && actual[i] != null) {
                        if(!expected[i].equals(actual[i])) {
                            setCurrentFailureMessage(Test.FAILURE_NOT_EQUAL);
                            markCurrentAsFailed();
                            break;
                        }
                    } else {
                        setCurrentFailureMessage(Test.FAILURE_NULL_ELEMENT_REFERENCE);
                        markCurrentAsFailed();
                        break;
                    }
                }
            } else {
                setCurrentFailureMessage(Test.FAILURE_DIFFERENT_LENGTH);
                markCurrentAsFailed();
            }
        } else {
            setCurrentFailureMessage(Test.FAILURE_NULL_ARRAY_REFERENCE);
            markCurrentAsFailed();
        }
    }

    /**
     *
     * @param expected
     * @param actual
     * @param <T>
     */
    public <T> void assertEquals(Collection<T> expected, Collection<T> actual) {

    }

    /**
     *
     * @param expected
     * @param actual
     */
    public void assertEquals(boolean expected, boolean actual) {

    }

    /**
     *
     * @param expected
     * @param actual
     */
    public void assertEquals(char expected, char actual) {

    }

    /**
     *
     * @param expected
     * @param actual
     */
    public void assertEquals(int expected, int actual) {

    }

    /**
     *
     * @param expected
     * @param actual
     */
    public void assertEquals(long expected, long actual) {

    }

    /**
     *
     * @param expected
     * @param actual
     */
    public void assertEquals(float expected, float actual) {

    }

    /**
     *
     * @param expected
     * @param actual
     */
    public void assertEquals(double expected, double actual) {

    }

    private void markCurrentAsPassed() {
        if(isTestAvailable()) {
            tests.get(currentTest).setPassed(true);
        }
    }

    private void markCurrentAsFailed() {
        if(isTestAvailable()) {
            tests.get(currentTest).setPassed(false);
        }
    }

    private void setCurrentFailureMessage(String failureMessage) {
        if(isTestAvailable()) {
            tests.get(currentTest).setFailureMessage(failureMessage);
        }
    }



    private static class Test {

        public static final String FAILURE_DIFFERENT_LENGTH = "LENGTHS ARE DIFFERENT";
        public static final String FAILURE_NULL_ARRAY_REFERENCE = "NULL ARRAY";
        public static final String FAILURE_NULL_ELEMENT_REFERENCE = "NULL ELEMENT";
        public static final String FAILURE_NOT_EQUAL = "NOT EQUAL";


        private String name;
        private String passedStatusMessage;
        private String failedStatusMessage;
        private String failureMessage;
        private boolean printOnError;
        private boolean passed;

        public Test(String defaultName) {
            this.name = defaultName;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPassedStatusMessage(String passedStatusMessage) {
            this.passedStatusMessage = passedStatusMessage;
        }

        public void setFailedStatusMessage(String failedStatusMessage) {
            this.failedStatusMessage = failedStatusMessage;
        }

        private void setFailureMessage(String failureMessage) {
            this.failureMessage = failureMessage;
        }

        public void setPrintOnError(boolean printOnError) {
            this.printOnError = printOnError;
        }

        public void setPassed(boolean passed) {
            this.passed = passed;
        }

        public boolean isPassed() {
            return passed;
        }

        public boolean isPrintOnError() {
            return printOnError;
        }

        @Override
        public String toString() {
            return null;
        }
    }

    private static class NoSuchAssertionTestException extends RuntimeException {
        public NoSuchAssertionTestException(String message) {
            super(message);
        }
    }



}
