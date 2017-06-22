import java.io.PrintStream;
import java.util.*;

/**
 * Created by Rene Sommerfeld on 09.06.2017.
 */
public final class Assert {

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
     * Holds each registered test unit
     */
    private List<TestUnit> testUnits;

    /**
     * Defines current created test unit
     */
    private TestUnit currentTestUnit;

    /**
     * Defines the PrintStream where the output is written to
     */
    private PrintStream output;

    /**
     * Defines whether or not the evaluation is inverted
     */
    private boolean inverted;

    /**
     * References the singleton instance of this class
     */
    private static Assert instance = new Assert();

    /**
     * Private constructor in order to stick to the rules
     * of singleton pattern
     */
    private Assert() {
        testUnits = new ArrayList<>();
        currentTestUnit = null;
        output = System.out;
        inverted  = false;
    }

    /**
     * Creates a new test unit. Any call of the methods
     * like {@link #name(String)}, {@link #passedMessage(String)},
     * or {@link #failedMessage(String)} will refer to the test unit created
     * by invoking this method.
     */
    public static void create() {
        instance.currentTestUnit = new TestUnit(TEST_DEFAULT_NAME_PREFIX + (instance.testUnits.size() + 1));
        instance.testUnits.add(instance.currentTestUnit);
    }

    /**
     * Names the current test unit created by {@link #create()}.
     * @param name the name of the test unit
     */
    public static void name(String name) {
        if(instance.isTestAvailable()) {
            instance.currentTestUnit.setName(name);
        }
    }

    /**
     * Sets the PrintStream where the output is written to.
     * @param output the output
     */
    public static void out(PrintStream output) {
        if(output != null) {
            instance.output = output;
        }
    }

    /**
     * Sets the inverted mode of the test unit evaluation.
     */
    public static void invertMode() {
        instance.inverted = true;
    }

    /**
     * Sets the default mode of the test unit evaluation.
     */
    public static void normalMode() {
        instance.inverted = false;
    }


    /**
     * Sets the message of a passed test unit.
     * @param passedMessage the message
     */
    public static void passedMessage(String passedMessage) {
        if(instance.isTestAvailable()) {
            instance.currentTestUnit.setPassedStatusMessage(passedMessage);
        }
    }

    /**
     * Sets the message of a failed test unit.
     * @param failedMessage the message for a failed test unit
     */
    public static void failedMessage(String failedMessage) {
        instance.currentTestUnit.setFailedStatusMessage(failedMessage);
    }

    /**
     * Prints the results of all registered test. Tests
     * are registered by calling any of the following methods:
     * {@link Equals#assertEquals(boolean, boolean)},
     * {@link Equals#assertEquals(int, int)},
     * {@link Equals#assertEquals(long, long)},
     * {@link Equals#assertEquals(double, double)},
     * {@link Equals#assertEquals(Character, Character)},
     * {@link Equals#assertEquals(char, char)},
     * {@link Equals#assertEquals(String, String)},
     * {@link Equals#assertEquals(Class, Class)},
     * {@link Arrays#assertEquals(Object[], Object[])},
     * {@link Generic#assertEquals(Collection, Collection)},
     * {@link Generic#assertEquals(Iterable, Iterable)},
     * {@link Bool#assertTrue(boolean)},
     * {@link Bool#assertFalse(boolean)},
     * {@link Equals#assertNull(Object)},
     *
     * or calling any of the corresponding static short cut methods.
     */
    public static void results() {
        int failCount = 0;
        if(instance.isTestAvailable()) {
            for(TestUnit testUnit : instance.testUnits) {
                instance.output.println("########");
                instance.output.println(testUnit);
                instance.output.println("########");
                if(!testUnit.isPassed()) {
                    failCount++;
                }
            }
        }
        int testCount = instance.testUnits.size();
        instance.output.println("######Report######");
        instance.output.printf("%d/%d have passed! \n", testCount - failCount, testCount);
        instance.output.printf("%d/%d have failed! \n", failCount, testCount);
        instance.output.println("############");
    }

    /**
     *
     * @return
     */
    private boolean isTestAvailable() {
        if(currentTestUnit != null) {
            return true;
        } else {
            throw new NoSuchAssertionTestException("There is no Assert test unit to name. " +
                    "Please invoke createTestUnit method.");
        }
    }

    /**
     *
     */
    private void markCurrentAsPassed() {
        if(isTestAvailable()) {
            currentTestUnit.setPassed(!inverted);
        }
    }

    /**
     *
     */
    private void markCurrentAsFailed() {
        if(isTestAvailable()) {
            currentTestUnit.setPassed(inverted);
        }
    }

    /**
     *
     * @param failure
     */
    private void setCurrentFailureMessage(Failure failure) {
        if(isTestAvailable()) {
            currentTestUnit.setFailureMessage(failure.toString());
        }
    }

    /**
     *
     * @param expected
     */
    private void setCurrentExpected(String expected) {
        if(isTestAvailable()) {
            currentTestUnit.setExpected(expected);
        }
    }

    /**
     *
     * @param actual
     */
    private void setCurrentActual(String actual) {
        if(isTestAvailable()) {
            currentTestUnit.setActual(actual);
        }
    }

    /**
     *
     * @param b
     * @param elem
     * @param last
     */
    private void buildStringRepresentation(StringBuilder b, Object elem, boolean last) {
        if(!last) {
            b.append(elem.toString());
            b.append(", ");
        } else {
            b.append(elem.toString());
        }
    }

    public static final class Bool {

        private Bool() {}

        /**
         * Tests if the value is true.
         * @param actual the actual value
         */
        public static void assertTrue(boolean actual) {
            instance.setCurrentExpected("true");
            instance.setCurrentActual(String.valueOf(actual));
            if(actual) {
                instance.markCurrentAsPassed();
            } else {
                instance.setCurrentFailureMessage(new NotEqualFailure());
                instance.markCurrentAsFailed();
            }
        }

        /**
         * Tests if the value is false
         * @param actual the actual value
         */
        public static void assertFalse(boolean actual) {
            assertTrue(!actual);
        }

    }

    public static final class Generic {

        private Generic() {}

        /**
         * Helper method in order to iterate over an array and collections. Takes iterable
         * objects and their sizes to compare if they are equal
         * @param expectedIterable the expected iterable object
         * @param actualIterable the actual iterable object
         * @param expectedSize the size of the expected object
         * @param actualSize the size of the actual object
         * @param <T> the type of the elements of the iterable objects
         */
        public static <T> void assertEquals(Iterable<T> expectedIterable, Iterable<T> actualIterable,
                                      int expectedSize, int actualSize) {
            //StringBuilder for building up a string representation of the iterable objects
            StringBuilder expectedBuilder = new StringBuilder();
            StringBuilder actualBuilder = new StringBuilder();
            boolean failed = false;
            //null check
            if(expectedIterable != null && actualIterable != null) {
                //size check
                if(expectedSize == actualSize) {
                    int currentIndex = 0;
                    Iterator<T> expectedIterator = expectedIterable.iterator();
                    Iterator<T> actualIterator = actualIterable.iterator();
                    //walking through all elements of both iterable objects
                    while(expectedIterator.hasNext() && !failed) {
                        T expectedElem = expectedIterator.next();
                        T actualElem = actualIterator.next();
                        instance.buildStringRepresentation(expectedBuilder, expectedElem, !expectedIterator.hasNext());
                        instance.buildStringRepresentation(actualBuilder, actualElem, !actualIterator.hasNext());
                        //null elements check
                        if(expectedElem != null && actualElem != null) {
                            if(!expectedElem.equals(actualElem)) {
                                //marks the test unit as failed, notes that the error
                                //has something to do with two elements that are
                                //compared were not equal
                                instance.setCurrentFailureMessage(new IndexNotEqualFailure(currentIndex));
                                instance.markCurrentAsFailed();
                                failed = true;
                            }
                        } else {
                            //marks the test unit as failed, notes that the error
                            //has something to do with one or both elements are null
                            instance.setCurrentFailureMessage(new NullFailure());
                            instance.markCurrentAsFailed();
                            failed = true;
                        }
                        currentIndex++;
                    }
                    //marks the test unit as passed
                    if(!failed)
                        instance.markCurrentAsPassed();
                } else {
                    //marks the test unit as failed, notes that the error
                    //has something to do with the sizes of the iterable objects
                    //are being different
                    instance.setCurrentFailureMessage(new LengthFailure(expectedSize, actualSize));
                    instance.markCurrentAsFailed();
                }
            } else {
                //marks the test unit as failed, notes that the error has
                //something to do with the iterable objects are being null
                instance.setCurrentFailureMessage(new NullFailure());
                instance.markCurrentAsFailed();
            }
            //sets the expected and actual value of the current test unit
            instance.setCurrentExpected(expectedBuilder.toString());
            instance.setCurrentActual(actualBuilder.toString());
        }

        /**
         * Calls {@link #assertEquals(Iterable, Iterable)}
         * @param expectedIterable the expected iterable object
         * @param actualIterable the actual iterable object
         * @param <T> the type of the elements of the iterable object
         */
        public static <T> void assertEquals(Iterable<T> expectedIterable, Iterable<T> actualIterable) {
            assertEquals(expectedIterable, actualIterable, -1, -1);
        }

        /**
         * Tests if two collections are equal.
         * @param expected the expected value
         * @param actual the actual value
         * @param <T> the type of the elements of the collection
         */
        public static <T> void assertEquals(Collection<T> expected, Collection<T> actual) {
            int expectedSize = (expected != null) ? expected.size() : -1;
            int actualSize = (actual != null) ? actual.size() : -1;
            assertEquals(expected, actual, expectedSize, actualSize);
        }
    }

    public static final class Arrays {

        private Arrays() {}

        /**
         * Tests if two arrays are equal.
         * @param expected the expected value
         * @param actual the actual value
         * @param <T> the type of the elements of the array
         */
        public static <T> void assertEquals(T[] expected, T[] actual) {
            int expectedSize = (expected != null) ? expected.length : -1;
            int actualSize = (actual != null) ? actual.length : -1;
            Generic.assertEquals(new GenericArrayWrapper<>(expected), new GenericArrayWrapper<>(actual), expectedSize, actualSize);
        }

        /**
         *
         * @param expected
         * @param actual
         */
        public static void assertEquals(int[] expected, int[] actual) {
            int expectedSize = (expected != null) ? expected.length : -1;
            int actualSize = (actual != null) ? actual.length : -1;
            Generic.assertEquals(new GenericArrayWrapper<>(PrimitiveArrayConverter.convert(expected)),
                    new GenericArrayWrapper<>(PrimitiveArrayConverter.convert(actual)), expectedSize, actualSize);
        }

        /**
         *
         * @param expected
         * @param actual
         */
        public static void assertEquals(long[] expected, long[] actual) {
            int expectedSize = (expected != null) ? expected.length : -1;
            int actualSize = (actual != null) ? actual.length : -1;
            Generic.assertEquals(new GenericArrayWrapper<>(PrimitiveArrayConverter.convert(expected)),
                    new GenericArrayWrapper<>(PrimitiveArrayConverter.convert(actual)), expectedSize, actualSize);
        }

        /**
         *
         * @param expected
         * @param actual
         */
        public static void assertEquals(double[] expected, double[] actual) {
            int expectedSize = (expected != null) ? expected.length : -1;
            int actualSize = (actual != null) ? actual.length : -1;
            Generic.assertEquals(new GenericArrayWrapper<>(PrimitiveArrayConverter.convert(expected)),
                    new GenericArrayWrapper<>(PrimitiveArrayConverter.convert(actual)), expectedSize, actualSize);
        }

        /**
         *
         * @param expected
         * @param actual
         */
        public static void assertEquals(boolean[] expected, boolean[] actual) {
            int expectedSize = (expected != null) ? expected.length : -1;
            int actualSize = (actual != null) ? actual.length : -1;
            Generic.assertEquals(new GenericArrayWrapper<>(PrimitiveArrayConverter.convert(expected)),
                    new GenericArrayWrapper<>(PrimitiveArrayConverter.convert(actual)), expectedSize, actualSize);
        }

        /**
         *
         * @param expected
         * @param actual
         */
        public static void assertEquals(char[] expected, char[] actual) {
            int expectedSize = (expected != null) ? expected.length : -1;
            int actualSize = (actual != null) ? actual.length : -1;
            Generic.assertEquals(new GenericArrayWrapper<>(PrimitiveArrayConverter.convert(expected)),
                    new GenericArrayWrapper<>(PrimitiveArrayConverter.convert(actual)), expectedSize, actualSize);
        }

    }

    public static class Equals {

        private Equals() {}

        /**
         * Tests if a value is null
         * @param actual the actual value
         */
        public static void assertNull(Object actual) {
            Bool.assertTrue(actual == null);
        }

        /**
         * Tests if two strings are equal.
         * @param expected the expected value
         * @param actual the actual value
         */
        public static void assertEquals(String expected, String actual) {
            if(expected != null && actual != null) {
                Bool.assertTrue(expected.equals(actual));
            } else {
                instance.setCurrentFailureMessage(new NullFailure());
                instance.markCurrentAsFailed();
            }
            instance.setCurrentExpected(expected);
            instance.setCurrentActual(actual);
        }

        /**
         * Tests if two boolean values are equal.
         * @param expected the expected value
         * @param actual the actual value
         */
        public static void assertEquals(boolean expected, boolean actual) {
            Bool.assertTrue(expected == actual);
            instance.setCurrentExpected(String.valueOf(expected));
            instance.setCurrentActual(String.valueOf(actual));
        }

        /**
         * Tests if two character values are equal.
         * @param expected the expected value
         * @param actual the actual value
         */
        public static void assertEquals(char expected, char actual) {
            Bool.assertTrue(expected == actual);
            instance.setCurrentExpected(String.valueOf(expected));
            instance.setCurrentActual(String.valueOf(actual));
        }

        /**
         * Tests if two integer values are equal.
         * @param expected the expected value
         * @param actual the actual value
         */
        public static void assertEquals(int expected, int actual) {
            Bool.assertTrue(expected == actual);
            instance.setCurrentExpected(String.valueOf(expected));
            instance.setCurrentActual(String.valueOf(actual));
        }


        /**
         * Tests if two long values are equal.
         * @param expected the expected value
         * @param actual the actual value
         */
        public static void assertEquals(long expected, long actual) {
            Bool.assertTrue(expected == actual);
            instance.setCurrentExpected(String.valueOf(expected));
            instance.setCurrentActual(String.valueOf(actual));
        }

        /**
         * Tests if two Character values are equal.
         * @param expected the expected value
         * @param actual the actual value
         */
        public static void assertEquals(Character expected, Character actual) {
            Bool.assertTrue(expected == actual);
            instance.setCurrentExpected(String.valueOf(expected));
            instance.setCurrentActual(String.valueOf(actual));
        }

        /**
         * Tests if two double values are equal.
         * @param expected the expected value
         * @param actual the actual value
         */
        public static void assertEquals(double expected, double actual) {
            Bool.assertTrue(expected == actual);
            instance.setCurrentExpected(String.valueOf(expected));
            instance.setCurrentActual(String.valueOf(actual));
        }

        /**
         * Tests if two class objects are equal.
         * @param expected the expected value
         * @param actual the actual value
         */
        public static void assertEquals(Class<?> expected, Class<?> actual) {
            if(expected != null && actual != null) {
                Bool.assertTrue(expected.equals(actual));
            } else {
                instance.setCurrentFailureMessage(new NullFailure());
                instance.markCurrentAsFailed();
            }
            instance.setCurrentExpected(String.valueOf(expected));
            instance.setCurrentActual(String.valueOf(actual));
        }

    }

    public static final class Interval {

        private static final String BETWEEN_INCL_BOUNDS_STRING = "Between Including Bounds : ";
        private static final String BETWEEN_EXCL_BOUNDS_STRING = "Between Excluding Bounds : ";

        private Interval() {}

        public static void betweenIncludingBounds(double lowerBound, double upperBound, double value) {
            Bool.assertTrue(lowerBound <= value && value <= upperBound);
            instance.setCurrentExpected(BETWEEN_INCL_BOUNDS_STRING + lowerBound + " -- " + upperBound);
            instance.setCurrentActual(String.valueOf(value));
        }

        public static void betweenIncludingBounds(float lowerBound, float upperBound, float value) {
            Bool.assertTrue(lowerBound <= value && value <= upperBound);
            instance.setCurrentExpected(BETWEEN_INCL_BOUNDS_STRING + lowerBound + " -- " + upperBound);
            instance.setCurrentActual(String.valueOf(value));
        }

        public static void betweenIncludingBounds(int lowerBound, int upperBound, int value) {
            Bool.assertTrue(lowerBound <= value && value <= upperBound);
            instance.setCurrentExpected(BETWEEN_INCL_BOUNDS_STRING + lowerBound + " -- " + upperBound);
            instance.setCurrentActual(String.valueOf(value));
        }

        public static void betweenIncludingBounds(long lowerBound, long upperBound, long value) {
            Bool.assertTrue(lowerBound <= value && value <= upperBound);
            instance.setCurrentExpected(BETWEEN_INCL_BOUNDS_STRING + lowerBound + " -- " + upperBound);
            instance.setCurrentActual(String.valueOf(value));
        }

        public static void betweenExcludingBounds(double lowerBound, double upperBound, double value) {
            Bool.assertTrue(lowerBound < value && value < upperBound);
            instance.setCurrentExpected(BETWEEN_EXCL_BOUNDS_STRING + lowerBound + " -- " + upperBound);
            instance.setCurrentActual(String.valueOf(value));
        }

        public static void betweenExcludingBounds(float lowerBound, float upperBound, float value) {
            Bool.assertTrue(lowerBound < value && value < upperBound);
            instance.setCurrentExpected(BETWEEN_EXCL_BOUNDS_STRING + lowerBound + " -- " + upperBound);
            instance.setCurrentActual(String.valueOf(value));
        }

        public static void betweenExcludingBounds(int lowerBound, int upperBound, int value) {
            Bool.assertTrue(lowerBound < value && value < upperBound);
            instance.setCurrentExpected(BETWEEN_EXCL_BOUNDS_STRING + lowerBound + " -- " + upperBound);
            instance.setCurrentActual(String.valueOf(value));
        }

        public static void betweenExcludingBounds(long lowerBound, long upperBound, long value) {
            Bool.assertTrue(lowerBound < value && value < upperBound);
            instance.setCurrentExpected(BETWEEN_EXCL_BOUNDS_STRING + lowerBound + " -- " + upperBound);
            instance.setCurrentActual(String.valueOf(value));
        }

    }

    private static class TestUnit {

        private static final String NAME_TAG = "NAME";
        private static final String EXPECTED_TAG = "EXP ";
        private static final String ACTUAL_TAG = "ACT ";
        private static final String STATUS_TAG = "STAT";
        private static final String FAILURE_TAG = "FAIL";

        private String name;
        private String expected;
        private String actual;
        private String passedStatusMessage;
        private String failedStatusMessage;
        private String failureMessage;
        private boolean passed;

        public TestUnit(String defaultName) {
            this.name = defaultName;
            this.passedStatusMessage = Assert.TEST_DEFAULT_PASSED_MESSAGE;
            this.failedStatusMessage = Assert.TEST_DEFAULT_FAILED_MESSAGE;
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
            boolean exp = expected != null && expected.length() != 0;
            boolean act = actual != null && actual.length() != 0;
            builder.append(NAME_TAG).append("\t").append(name).append("\n");
            if(exp) {
                builder.append(EXPECTED_TAG).append("\t").append(expected).append("\n");
            }
            if(act) {
                builder.append(ACTUAL_TAG).append("\t").append(actual).append("\n");
            }
            String message = (passed) ? passedStatusMessage : failedStatusMessage;
            builder.append(STATUS_TAG).append("\t").append(message).append("\n");
            if(!passed) {
                builder.append(FAILURE_TAG).append("\t").append(failureMessage).append("\n");
            }
            return builder.toString();
        }
    }

    private static class GenericArrayWrapper<T> implements Iterable<T> {

        private T[] array;

        public GenericArrayWrapper(T[] array) {
            this.array = array;
        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private int currentIndex = -1;

                @Override
                public boolean hasNext() {
                    return currentIndex + 1 < array.length;
                }

                @Override
                public T next() {
                    return array[++currentIndex];
                }
            };
        }
    }

    private static final class PrimitiveArrayConverter {

        public static Integer[] convert(int[] array) {
            Integer[] convertArray = new Integer[array.length];
            for(int i = 0; i < array.length; i++) {
                convertArray[i] = array[i];
            }
            return convertArray;
        }

        public static Long[] convert(long[] array) {
            Long[] convertArray = new Long[array.length];
            for(int i = 0; i < array.length; i++) {
                convertArray[i] = array[i];
            }
            return convertArray;
        }

        public static Character[] convert(Character[] array) {
            Character[] convertArray = new Character[array.length];
            for(int i = 0; i < array.length; i++) {
                convertArray[i] = array[i];
            }
            return convertArray;
        }

        public static Double[] convert(double[] array) {
            Double[] convertArray = new Double[array.length];
            for(int i = 0; i < array.length; i++) {
                convertArray[i] = array[i];
            }
            return convertArray;
        }

        public static Boolean[] convert(boolean[] array) {
            Boolean[] convertArray = new Boolean[array.length];
            for(int i = 0; i < array.length; i++) {
                convertArray[i] = array[i];
            }
            return convertArray;
        }

        public static Character[] convert(char[] array) {
            Character[] convertArray = new Character[array.length];
            for(int i = 0; i < array.length; i++) {
                convertArray[i] = array[i];
            }
            return convertArray;
        }


        public static Float[] convert(float[] array) {
            Float[] convertArray = new Float[array.length];
            for(int i = 0; i < array.length; i++) {
                convertArray[i] = array[i];
            }
            return convertArray;
        }


    }

    private static abstract class Failure {
        private String name;
        public Failure(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static class LengthFailure extends Failure {
        private int expectedLength;
        private int actualLength;

        public LengthFailure(int expectedLength, int actualLength) {
            super("LENGTH FAILURE");
            this.expectedLength = expectedLength;
            this.actualLength = actualLength;
        }

        @Override
        public String toString() {
            return super.toString() + "  expected length " +
                    "(" + expectedLength + ") but actual length was (" + actualLength + ")";
        }

    }

    private static class NotEqualFailure extends Failure {
        public NotEqualFailure() {
            super("NOT EQUAL FAILURE");
        }
    }

    private static class IndexNotEqualFailure extends NotEqualFailure {
        private int currentIndex;
        public IndexNotEqualFailure(int currentIndex) {
            super();
            this.currentIndex = currentIndex;
        }

        @Override
        public String toString() {
            return super.toString() + " on index " + currentIndex;
        }
    }

    private static class NullFailure extends Failure {
        public NullFailure() {
            super("NULL FAILURE");
        }
    }


    private static class NoSuchAssertionTestException extends RuntimeException {
        public NoSuchAssertionTestException(String message) {
            super(message);
        }
    }



}
