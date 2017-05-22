package test.values;

/**
 * Created by Rene Sommerfeld on 21.05.2017.
 */
public class ClassObjectAssertion extends ObjectAssertion {

    private ClassObjectAssertion(String name, Object expected, Object actual) {
        super(name, expected, actual);
    }

    @Override
    public void evaluate() {
        if(expected != null && actual != null) {
            if(expected.getClass() == actual.getClass()) {
                markedAsPassed();
            } else {
                markedAsFailed();
            }
        } else {
            markedAsFailed();
        }
    }

    @Override
    public String toString() {
        return buildSummaryString(String.valueOf(expected), String.valueOf(actual));
    }

    public static ClassObjectAssertion create(String name, Object expected, Object actual) {
        return new ClassObjectAssertion(name, expected, actual);
    }

    public static ClassObjectAssertion create(Object expected, Object actual) {
        return create(null, expected, actual);
    }
}
