package test.ranges;

import test.values.IntAssertion;

/**
 * Created by Rene Sommerfeld on 22.05.2017.
 */
public class IntValueRangeAssertion extends ValueRangeAssertion {

    private int from;
    private int to;
    private int actual;

    /**
     * Constructor of Assertion.
     * Every assertion has to have at least a name
     *
     * @param name the name of the assertion
     */
    private IntValueRangeAssertion(String name, int from, int to, int actual) {
        super(name);
        this.from = from;
        this.to = to;
        this.actual = actual;
    }

    @Override
    public void eval() {
        if(from <= actual && actual <= to) {
            markedAsPassed();
        } else {
            markedAsFailed();
        }
    }

    @Override
    public String toString() {
        return buildSummaryString(String.valueOf(from), String.valueOf(to), String.valueOf(actual));
    }

    public static IntValueRangeAssertion create(String name, int from, int to, int actual) {
        return new IntValueRangeAssertion(name, from, to, actual);
    }

    public static IntValueRangeAssertion create(int from, int to, int actual) {
        return create(null, from, to, actual);
    }

    public static IntValueRangeAssertion createInverted(String name, int from, int to, int actual) {
        IntValueRangeAssertion assertion = create(name, from, to, actual);
        assertion.setInverted(true);
        return assertion;
    }

    public static IntValueRangeAssertion createInverted(int from, int to, int actual) {
        return createInverted(null, from, to, actual);
    }

}
