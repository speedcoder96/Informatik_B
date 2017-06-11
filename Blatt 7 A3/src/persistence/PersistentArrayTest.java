package persistence;


import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Rene Sommerfeld on 09.06.2017.
 */
public class PersistentArrayTest {

    private static final Integer[] TEST_ARRAY = new Integer[]{1,2,4,8,16,32};

    public static void main(String[] args) {

        testCreatePersistentIntegerArray();
        testLoadAndCheckIfHasSameElements();
        testLoadAndChangeElements();
        testSetIndex();
        testGetIndex();
        Assertion.results();
    }

    private static void testCreatePersistentIntegerArray() {
        try (PersistentIntegerArray a = new PersistentIntegerArray("array.dat", TEST_ARRAY)) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //checks methods get and length
    private static void testLoadAndCheckIfHasSameElements() {
        try (PersistentIntegerArray b = new PersistentIntegerArray("array.dat")) {
            Integer[] copyArray = new Integer[b.length()];

            for(int i = 0; i < copyArray.length; i++) {
                copyArray[i] = b.get(i);
            }

            Assertion.create();
            Assertion.name("Same Elements Check");
            Assertion.equals(TEST_ARRAY, copyArray);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //checks methods set and get
    private static void testLoadAndChangeElements() {
        try (PersistentIntegerArray b = new PersistentIntegerArray("array.dat")) {
            Integer[] originalArray = TEST_ARRAY;
            Integer[] copyArray = new Integer[b.length()];

            for(int i = 0; i < originalArray.length; i++) {
                originalArray[i] = i * 3;
                b.set(i, originalArray[i]);
                copyArray[i] = b.get(i);
            }

            Assertion.create();
            Assertion.name("Same Elements Check After Setting");
            Assertion.equals(originalArray, copyArray);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //checks exception
    private static void testSetIndex() {
        Assertion.create();
        Assertion.name("Try Setting At Index -3 -> ArrayIndexOutOfBounds");
        try (PersistentIntegerArray b = new PersistentIntegerArray("array.dat")) {
            b.set(-3, 20);
        } catch (Exception e) {
            Assertion.equals(ArrayIndexOutOfBoundsException.class, e.getClass());
        }
    }

    //checks exception
    private static void testGetIndex() {
        Assertion.create();
        Assertion.name("Try Getting At Index -3 -> ArrayIndexOutOfBounds");
        try (PersistentIntegerArray b = new PersistentIntegerArray("array.dat")) {
            Integer result = b.get(-3);
        } catch (Exception e) {
            Assertion.equals(ArrayIndexOutOfBoundsException.class, e.getClass());
        }
    }



}
