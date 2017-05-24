import test.core.Evaluator;
import test.values.ObjectAssertion;

/**
 * Created by Rene Sommerfeld on 23.05.2017.
 */
public class FractionTest {

    public static void main(String[] args) {
        testSameInstance();
        testSameInstance2();
        Evaluator.getInstance().printSummary();
    }

    private static void testSameInstance() {
        Fraction a = Fraction.parseFraction("1/2");
        Fraction b = Fraction.parseFraction("2/4");
        Evaluator.getInstance().eval(ObjectAssertion.create("a= " + a + " b= " + b, a, b));
    }

    private static void testSameInstance2() {
        Fraction a = Fraction.parseFraction("1/2");
        Fraction b = Fraction.parseFraction("2/4");
        Fraction c = Fraction.parseFraction("4/8");
        Evaluator.getInstance().eval(ObjectAssertion.create("a= " + a + " b= " + b, a, b));
        Evaluator.getInstance().eval(ObjectAssertion.create("b= " + b + " c= " + c, b, c));
    }

}
