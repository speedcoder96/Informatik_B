package test.arrays;

import test.values.IntAssertion;

/**
 * Created by Rene Sommerfeld on 15.05.2017.
 */
public class IntArrayAssertion extends ArrayAssertion {

    private int[] expected;
    private int[] actual;

    public IntArrayAssertion(String name, int[] expected, int[] actual) {
        super(name);
        this.expected = expected;
        this.actual = actual;
    }

    @Override
    public void evaluate() {

    }

    @Override
    public String toString() {
        return null;
    }

    public static IntArrayAssertion create(String name, int[] expected, int[] actual) {
        return new IntArrayAssertion(name, expected, actual);
    }

    public static IntArrayAssertion create(int[] expected, int[] actual) {
        return create(null, expected, actual);
    }

}
