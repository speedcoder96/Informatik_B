package test.generic;

import test.core.Assertion;

/**
 * Created by Rene Sommerfeld on 15.05.2017.
 */
public abstract class GenericAssertion<E, A> extends Assertion {

    private E expected;
    private A actual;

    public GenericAssertion(String name, E expected, A actual) {
        super(name);
        this.expected = expected;
        this.actual = actual;
    }

    public E getExpected() {
        return expected;
    }

    public A getActual() {
        return actual;
    }




}
