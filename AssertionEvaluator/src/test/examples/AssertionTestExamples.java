package test.examples;


import test.core.Evaluator;
import test.ranges.IntValueRangeAssertion;
import test.values.*;

/**
 * Created by Rene Sommerfeld on 21.05.2017.
 */
public class AssertionTestExamples {

    public static class Person {

    }

    public static class Student {

    }

    public static void main(String[] args) {

        //Boolean Assertions
        Evaluator.getInstance().eval(BooleanAssertion.create(true, true));
        Evaluator.getInstance().eval(BooleanAssertion.createInverted(true, true));

        //Char Assertions
        Evaluator.getInstance().eval(CharAssertion.create('c', 'd'));
        Evaluator.getInstance().eval(CharAssertion.createInverted('c', 'd'));

        //Double Assertions
        Evaluator.getInstance().eval(DoubleAssertion.create(56.6, 45.5));
        Evaluator.getInstance().eval(DoubleAssertion.createInverted(56.6, 45.5));

        //....

        //NullAssertion
        Evaluator.getInstance().eval(NullAssertion.create(null));
        Evaluator.getInstance().eval(NullAssertion.createInverted(null));

        //ObjectAssertion
        Person p = new Person();
        Person q = new Person();
        Student s = new Student();
        Evaluator.getInstance().eval(ObjectAssertion.create(p, q));
        Evaluator.getInstance().eval(ObjectAssertion.createInverted(p, s));

        //ClassObject Assertions
        Evaluator.getInstance().eval(ClassObjectAssertion.create(p, q));
        Evaluator.getInstance().eval(ClassObjectAssertion.createInverted(p, q));

        //......

        Evaluator.getInstance().eval(IntValueRangeAssertion.create(50, 100, 75));
        Evaluator.getInstance().eval(IntValueRangeAssertion.createInverted(50, 100, 75));


        //prints a pre summary containing the number of
        //failed and passed tests
        //Evaluator.getInstance().preSummary();

        //prints summary every evaluated test
        //Evaluator.getInstance().printSummary();

        //prints only passed tests
        //Evaluator.getInstance().printOnlyPassed();

        //prints only failed tests
        //Evaluator.getInstance().printOnlyFailed();

    }

}
