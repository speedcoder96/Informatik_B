package test.examples;

import test.core.AssertionEvaluator;
import test.values.BooleanAssertion;
import test.values.ClassObjectAssertion;
import test.values.ObjectAssertion;

/**
 * Created by Rene Sommerfeld on 21.05.2017.
 */
public class ListTest {

    public static void main(String[] args) {

        cloneTest();
        AssertionEvaluator.getInstance().printSummary();
    }

    private static void cloneTest() {

        AssertionEvaluator evaluator = AssertionEvaluator.getInstance();

        Object x = null;
        Object clone = null;

        /**
         * Der List Stuff vorweg!!!
         * .... dann die Tests
         */

        evaluator.evaluate(BooleanAssertion.create("x.clone() != x", clone != x, true));
        evaluator.evaluate(ClassObjectAssertion.create("not required : x.clone().getClass() == x.getClass()",
                x, clone));

        evaluator.evaluate(ObjectAssertion.create("not required : x.clone().equals(x)", x, clone));

    }

}
