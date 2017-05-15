package test.generic;

/**
 * Created by Rene Sommerfeld on 15.05.2017.
 */
public class GenericArrayAssertion<E, A> extends GenericAssertion<E[], A[]> {


    public GenericArrayAssertion(String name, E[] expected, A[] actual) {
        super(name, expected, actual);
    }

    @Override
    public void evaluate() {

    }

    @Override
    public String toString() {
        return null;
    }
}
