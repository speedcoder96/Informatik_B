import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Rene Sommerfeld on 09.06.2017.
 */
public final class Assertion {

    /**
     * Defines the default test name prefix for each test units name
     */
    public static final String TEST_DEFAULT_NAME_PREFIX = "TestUnit ";

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
    private List<TestUnit> testUnits;

    /**
     * Defines current created test unit
     */
    private TestUnit currentTestUnit;

    /**
     * Private constructor in order to stick to the rules
     * of singleton pattern
     */
    private Assertion() {
        testUnits = new ArrayList<>();
        currentTestUnit = null;
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
     * like {@link #nameTestUnit(String)}, {@link #setPassedMessage(String)},
     * or {@link #setFailedMessage(String)} will refer to the test unit created
     * by invoking this method.
     */
    public void createTestUnit() {
        currentTestUnit = new TestUnit(TEST_DEFAULT_NAME_PREFIX + (testUnits.size() + 1));
        testUnits.add(currentTestUnit);
    }

    /**
     * Short cut method. Calls {@link #createTestUnit()}
     */
    public static void create() {
        getInstance().createTestUnit();
    }

    /**
     * Returns whether or not a test unit is available.
     * @return true if a test unit is available, otherwise throws
     * a {@code {@link NoSuchAssertionTestException}} if the instance isn't
     * created yet.
     */
    private boolean isTestAvailable() {
        if(currentTestUnit != null) {
            return true;
        } else {
            throw new Assertion.NoSuchAssertionTestException("There is no Assertion test unit to name. " +
                    "Please invoke createTestUnit method.");
        }
    }


    /**
     * Names the current test created by {@link #createTestUnit()}.
     * @param name the name of the test unit
     */
    public void nameTestUnit(String name) {
        if(isTestAvailable()) {
            currentTestUnit.setName(name);
        }
    }

    /**
     * Short cut method. Calls {@link #nameTestUnit(String)}
     * @param name the name of the test unit
     */
    public static void name(String name) {
        getInstance().nameTestUnit(name);
    }

    /**
     *
     * @param passedMessage
     */
    public void setPassedMessage(String passedMessage) {
        if(isTestAvailable()) {
            currentTestUnit.setPassedStatusMessage(passedMessage);
        }
    }

    /**
     * Short cut method. Calls {@link #setPassedMessage(String)}
     * @param positiveMessage
     */
    public static void posMessage(String positiveMessage) {
        getInstance().setPassedMessage(positiveMessage);
    }

    /**
     *
     * @param failedMessage
     */
    public void setFailedMessage(String failedMessage) {
        if(isTestAvailable()) {
            currentTestUnit.setFailedStatusMessage(failedMessage);
        }
    }

    /**
     * Short cut method. Calls {@link #setFailedMessage(String)}
     * @param negativeMessage
     */
    public static void negMessage(String negativeMessage) {
        getInstance().setFailedMessage(negativeMessage);
    }

    /**
     * Prints the results
     */
    public void printResults() {
        int failCount = 0;
        if(isTestAvailable()) {
            for(TestUnit testUnit : testUnits) {
                System.out.println("########");
                System.out.println(testUnit);
                System.out.println("########");
                if(!testUnit.isPassed()) {
                    failCount++;
                }
            }
        }
        int testCount = testUnits.size();
        System.out.println("######Report######");
        System.out.printf("%d/%d have passed! \n", testCount - failCount, testCount);
        System.out.printf("%d/%d have failed! \n", failCount, testCount);
        System.out.println("############");
    }

    /**
     * Short cut method. Calls {@link #printResults}
     *
     */
    public static void results() {
        getInstance().printResults();
    }

    /**
     *
     * @param actual
     */
    public void assertTrue(boolean actual) {
        setCurrentExpected("true");
        setCurrentActual(String.valueOf(actual));
        if(actual) {
            markCurrentAsPassed();
        } else {
            setCurrentFailureMessage(TestUnit.FAILURE_NOT_EQUAL);
            markCurrentAsFailed();
        }
    }

    /**
     * Short cut method. Calls {@link #assertTrue(boolean)}
     */
    public static void assTrue(boolean actual) {
        getInstance().assertTrue(actual);
    }

    /**
     *
     * @param actual
     */
    public void assertFalse(boolean actual) {
        assertTrue(!actual);
    }

    /**
     * Short cut method. Calls {@link #assertFalse(boolean)}
     * @param actual
     */
    public static void assFalse(boolean actual) {
        getInstance().assertFalse(actual);
    }

    /**
     *
     * @param actual
     */
    public void assertNull(Object actual) {
        assertTrue(actual == null);
    }

    /**
     * Short cut method. Calls {@link #assertNull(Object)}
     * @param actual
     */
    public static void assNull(boolean actual) {
        getInstance().assertNull(actual);
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
            setCurrentFailureMessage(TestUnit.FAILURE_NULL_ARRAY_REFERENCE);
            markCurrentAsFailed();
        }
        setCurrentExpected(expected);
        setCurrentActual(actual);
    }

    /**
     * Short cut method. Calls {@link #assertEquals(String, String)}
     * @param actual
     * @param expected
     * @param actual
     */
    public static void equals(String expected, String actual) {
        if(expected != null && actual != null) {
            assTrue(expected.equals(actual));
        } else {
            getInstance().setCurrentFailureMessage(TestUnit.FAILURE_NULL_ARRAY_REFERENCE);
            getInstance().markCurrentAsFailed();
        }
    }

    /**
     *
     * @param expected
     * @param actual
     * @param <T>
     */
    public <T> void assertEquals(T[] expected, T[] actual) {
        StringBuilder expectedBuilder = new StringBuilder();
        StringBuilder actualBuilder = new StringBuilder();
        if(expected != null && actual != null) {
            if(expected.length == actual.length) {
                for(int i = 0; i < expected.length; i++) {
                    buildStringRepresentation(expectedBuilder, expected[i], i == expected.length - 1);
                    buildStringRepresentation(actualBuilder, actual[i], i == expected.length - 1);
                    if(expected[i] != null && actual[i] != null) {
                        if(!expected[i].equals(actual[i])) {
                            setCurrentFailureMessage(TestUnit.FAILURE_NOT_EQUAL);
                            markCurrentAsFailed();
                            return;
                        }
                    } else {
                        setCurrentFailureMessage(TestUnit.FAILURE_NULL_ELEMENT_REFERENCE);
                        markCurrentAsFailed();
                        return;
                    }
                }
                markCurrentAsPassed();
            } else {
                setCurrentFailureMessage(TestUnit.FAILURE_DIFFERENT_LENGTH);
                markCurrentAsFailed();
            }
        } else {
            setCurrentFailureMessage(TestUnit.FAILURE_NULL_ARRAY_REFERENCE);
            markCurrentAsFailed();
        }
        setCurrentExpected(expectedBuilder.toString());
        setCurrentActual(actualBuilder.toString());
        System.out.println(actualBuilder.toString());
    }

    public static <T> void equals(T[] expected, T[] actual) {
        getInstance().assertEquals(expected, actual);
    }

    /**
     *
     * @param expected
     * @param actual
     * @param <T>
     */
    public <T> void assertEquals(Collection<T> expected, Collection<T> actual) {
        StringBuilder expectedBuilder = new StringBuilder();
        StringBuilder actualBuilder = new StringBuilder();
        if(expected != null && actual != null) {
            if(expected.size() == actual.size()) {
                Iterator<T> expectedIterator = expected.iterator();
                Iterator<T> actualIterator = actual.iterator();
                while(expectedIterator.hasNext()) {
                    T expectedElem = expectedIterator.next();
                    T actualElem = actualIterator.next();
                    buildStringRepresentation(expectedBuilder, expectedElem, expectedIterator.hasNext());
                    buildStringRepresentation(actualBuilder, actualElem, actualIterator.hasNext());
                    if(expectedElem != null && actualElem != null) {
                        if(!expectedElem.equals(actualElem)) {
                            setCurrentFailureMessage(TestUnit.FAILURE_NOT_EQUAL);
                            markCurrentAsFailed();
                            return;
                        }
                    } else {
                        setCurrentFailureMessage(TestUnit.FAILURE_NULL_ELEMENT_REFERENCE);
                        markCurrentAsFailed();
                        return;
                    }
                }
                markCurrentAsPassed();
            } else {
                setCurrentFailureMessage(TestUnit.FAILURE_DIFFERENT_LENGTH);
                markCurrentAsFailed();
            }
        } else {
            setCurrentFailureMessage(TestUnit.FAILURE_NULL_ARRAY_REFERENCE);
            markCurrentAsFailed();
        }
        setCurrentExpected(expectedBuilder.toString());
        setCurrentActual(actualBuilder.toString());
    }

    public static <T> void equals(Collection<T> expected, Collection<T> actual) {
        getInstance().assertEquals(expected, actual);
    }

    /**
     *
     * @param expected
     * @param actual
     */
    public void assertEquals(Class<?> expected, Class<?> actual) {
        if(expected != null && actual != null) {
            assertTrue(expected.equals(actual));
        } else {
            setCurrentFailureMessage(TestUnit.FAILURE_NULL_ELEMENT_REFERENCE);
            markCurrentAsFailed();
        }
        setCurrentExpected(String.valueOf(expected));
        setCurrentActual(String.valueOf(actual));
    }

    public static void equals(Class<?> expected, Class<?> actual) {
        getInstance().assertEquals(expected, actual);
    }

    /**
     *
     * @param expected
     * @param actual
     */
    public void assertEquals(boolean expected, boolean actual) {
        assertTrue(expected == actual);
        setCurrentExpected(String.valueOf(expected));
        setCurrentActual(String.valueOf(actual));
    }

    public static void equals(boolean expected, boolean actual) {
        getInstance().assertEquals(expected, actual);
    }

    /**
     *
     * @param expected
     * @param actual
     */
    public void assertEquals(char expected, char actual) {
        assertTrue(expected == actual);
        setCurrentExpected(String.valueOf(expected));
        setCurrentActual(String.valueOf(actual));
    }

    public static void equals(char expected, char actual) {
        getInstance().assertEquals(expected, actual);
    }

    /**
     *
     * @param expected
     * @param actual
     */
    public void assertEquals(int expected, int actual) {
        assertTrue(expected == actual);
        setCurrentExpected(String.valueOf(expected));
        setCurrentActual(String.valueOf(actual));
    }

    public static void equals(int expected, int actual) {
        getInstance().assertEquals(expected, actual);
    }

    /**
     *
     * @param expected
     * @param actual
     */
    public void assertEquals(long expected, long actual) {
        assertTrue(expected == actual);
        setCurrentExpected(String.valueOf(expected));
        setCurrentActual(String.valueOf(actual));
    }

    public static void equals(long expected, long actual) {
        getInstance().assertEquals(expected, actual);
    }

    /**
     *
     * @param expected
     * @param actual
     */
    public void assertEquals(float expected, float actual) {
        assertTrue(expected == actual);
        setCurrentExpected(String.valueOf(expected));
        setCurrentActual(String.valueOf(actual));
    }

    public static void equals(float expected, float actual) {
        getInstance().assertEquals(expected, actual);
    }

    /**
     *
     * @param expected
     * @param actual
     */
    public void assertEquals(double expected, double actual) {
        assertTrue(expected == actual);
        setCurrentExpected(String.valueOf(expected));
        setCurrentActual(String.valueOf(actual));
    }

    public static void equals(double expected, double actual) {
        getInstance().assertEquals(expected, actual);
    }


    private void markCurrentAsPassed() {
        if(isTestAvailable()) {
            currentTestUnit.setPassed(true);
        }
    }

    private void markCurrentAsFailed() {
        if(isTestAvailable()) {
            currentTestUnit.setPassed(false);
        }
    }

    private void setCurrentFailureMessage(String failureMessage) {
        if(isTestAvailable()) {
            currentTestUnit.setFailureMessage(failureMessage);
        }
    }

    private void setCurrentExpected(String expected) {
        if(isTestAvailable()) {
            currentTestUnit.setExpected(expected);
        }
    }

    private void setCurrentActual(String actual) {
        if(isTestAvailable()) {
            currentTestUnit.setActual(actual);
        }
    }

    private void buildStringRepresentation(StringBuilder b, Object elem, boolean last) {
        if(!last) {
            b.append(elem.toString());
            b.append(",");
        } else {
            b.append(elem.toString());
        }
    }



    private static class TestUnit {

        private static final String NAME_TAG = "NAME";
        private static final String EXPECTED_TAG = "EXP ";
        private static final String ACTUAL_TAG = "ACT ";
        private static final String STATUS_TAG = "STAT";
        private static final String FAILURE_TAG = "FAIL";

        public static final String FAILURE_DIFFERENT_LENGTH = "LENGTHS ARE DIFFERENT";
        public static final String FAILURE_NULL_ARRAY_REFERENCE = "NULL ARRAY";
        public static final String FAILURE_NULL_ELEMENT_REFERENCE = "NULL ELEMENT";
        public static final String FAILURE_NOT_EQUAL = "NOT EQUAL";


        private String name;
        private String expected;
        private String actual;
        private String passedStatusMessage;
        private String failedStatusMessage;
        private String failureMessage;
        private boolean passed;

        public TestUnit(String defaultName) {
            this.name = defaultName;
            this.passedStatusMessage = Assertion.TEST_DEFAULT_PASSED_MESSAGE;
            this.failedStatusMessage = Assertion.TEST_DEFAULT_FAILED_MESSAGE;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setExpected(String expected) {
            this.expected = expected;
        }

        public void setActual(String actual) {
            this.actual = actual;
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


        public void setPassed(boolean passed) {
            this.passed = passed;
        }

        public boolean isPassed() {
            return passed;
        }


        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(NAME_TAG).append("\t").append(name).append("\n");
            builder.append(EXPECTED_TAG).append("\t").append(expected).append("\n");
            builder.append(ACTUAL_TAG).append("\t").append(actual).append("\n");
            String message = (passed) ? passedStatusMessage : failedStatusMessage;
            builder.append(STATUS_TAG).append("\t").append(message).append("\n");
            if(!passed) {
                builder.append(FAILURE_TAG).append("\t").append(failureMessage).append("\n");
            }
            return builder.toString();
        }
    }

    private static class NoSuchAssertionTestException extends RuntimeException {
        public NoSuchAssertionTestException(String message) {
            super(message);
        }
    }



}
