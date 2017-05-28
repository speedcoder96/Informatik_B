import test.core.Evaluator;
import test.values.ObjectAssertion;

/**
 * Created by Rene Sommerfeld on 23.05.2017.
 */
public class FractionTest {

    public static void main(String[] args) {
        testSameInstance();
        testSameInstance2();
        testSameInstance3();
        testSameInstance4();
        Evaluator.getInstance().printSummary();
    }

    /**
     * Tests if 1/2 and 2/4 have the same reference
     * Creation via parsing fraction string
     */
    private static void testSameInstance() {
        Fraction a = Fraction.parseFraction("1/2");
        Fraction b = Fraction.parseFraction("2/4");
        Evaluator.getInstance().eval(ObjectAssertion.create("a= " + a + " b= " + b, a, b));
    }

    /**
     * Tests if 4/8 has the same reference as 1/2 and 2/4
     * Creation via parsing fraction string
     */
    private static void testSameInstance2() {
        Fraction a = Fraction.parseFraction("1/2");
        Fraction b = Fraction.parseFraction("2/4");
        Fraction c = Fraction.parseFraction("4/8");
        Evaluator.getInstance().eval(ObjectAssertion.create("a= " + a + " b= " + b, a, b));
        Evaluator.getInstance().eval(ObjectAssertion.create("b= " + b + " c= " + c, b, c));
    }

    /**
     * Tests if 1/2 and 2/4 have the same reference
     * Creation via create method
     */
    private static void testSameInstance3() {
        Fraction a = Fraction.create(1,2);
        Fraction b = Fraction.create(2,4);
        Evaluator.getInstance().eval(ObjectAssertion.create("a= " + a + " b= " + b, a, b));
    }

    /**
     * Tests if 2/1 and 2/1 have the same reference
     * Creation via create method
     */
    private static void testSameInstance4() {
        Fraction a = Fraction.create(2);
        Fraction b = Fraction.create(2);
        Evaluator.getInstance().eval(ObjectAssertion.create("a= " + a + " b= " + b, a, b));
    }



}
