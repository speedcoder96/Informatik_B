package fraction;

/**
 * Created by Rene Sommerfeld on 10.05.2017.
 */
public class Calculator {

    private static final int NUM_ARGUMENTS = 3;
    private static final String REGEX_OPERATOR_PATTERN = "(\\'\\*\\')|\\/|\\+|\\-";
    private static final String MULTIPLICATION = "'*'";
    private static final String ADDITION = "+";
    private static final String SUBTRACTION = "-";
    private static final String DIVISION = "/";

    public static void main(String[] args) {
        if(args.length == NUM_ARGUMENTS) {
            Calculator calc = new Calculator();
            String firstNumber = args[0];
            String operator = args[1];
            String secondNumber = args[2];
            //checks if it is a valid operator
            if (operator.matches(REGEX_OPERATOR_PATTERN)) {
                switch(operator) {
                    //multiplication case
                    case MULTIPLICATION:
                        System.out.println(calc.multiply(firstNumber, secondNumber));
                        break;
                    //addition case
                    case ADDITION:
                        System.out.println(calc.add(firstNumber, secondNumber));
                        break;
                    //subtraction case
                    case SUBTRACTION:
                        System.out.println(calc.subtract(firstNumber, secondNumber));
                        break;
                    //division case
                    case DIVISION:
                        System.out.println(calc.divide(firstNumber, secondNumber));
                        break;
                }
            }
        }
    }

    /**
     * Parses two string represented numbers to Number objects
     * and performs the addition operation on them
     * @param a the first string represented number
     * @param b the second string represented number
     * @return the number representing the result
     */
    public Number add(String a, String b) {
        Number p = parseArgument(a);
        Number q = parseArgument(b);
        Number result = null;
        if(p instanceof Fraction && q instanceof Fraction) {
            result = ((Fraction) p).add((Fraction)q);
        } else {
            result = p.floatValue() + q.floatValue();
        }
        return result;
    }

    /**
     * Parses two string represented numbers to Number objects
     * and performs the subtraction operation on them
     * @param a the first string represented number
     * @param b the second string represented number
     * @return the number representing the result
     */
    public Number subtract(String a, String b) {
        Number p = parseArgument(a);
        Number q = parseArgument(b);
        Number result = null;
        if(p instanceof Fraction && q instanceof Fraction) {
            result = ((Fraction) p).subtract((Fraction)q);
        } else {
            result = p.floatValue() - q.floatValue();
        }
        return result;
    }

    /**
     * Parses two string represented numbers to Number objects
     * and performs the multiplication operation on them
     * @param a the first string represented number
     * @param b the second string represented number
     * @return the number representing the result
     */
    public Number multiply(String a, String b) {
        Number p = parseArgument(a);
        Number q = parseArgument(b);
        Number result = null;
        if(p instanceof Fraction && q instanceof Fraction) {
            result = ((Fraction) p).multiply((Fraction)q);
        } else {
            result = p.floatValue() * q.floatValue();
        }
        return result;
    }

    /**
     * Parses two string represented numbers to Number objects
     * and performs the division operation on them
     * @param a the first string represented number
     * @param b the second string represented number
     * @return the number representing the result
     */
    public Number divide(String a, String b) {
        Number p = parseArgument(a);
        Number q = parseArgument(b);
        Number result = null;
        if(p instanceof Fraction && q instanceof Fraction) {
            result = ((Fraction) p).divide((Fraction)q);
        } else {
            result = p.floatValue() / q.floatValue();
        }
        return result;
    }

    /**
     * Parses one string represented number to a Number
     * object.
     * @param n the string to parse
     * @return the parsed Number object
     */
    private Number parseArgument(String n) {
        if(n.matches(Fraction.FRACTION_REGEX)) {
            return Fraction.parseFraction(n);
        } else {
            return Float.parseFloat(n);
        }
    }

    /**
     * Prints the possible inputs to calculate numbers
     * on the console
     */
    private static void printOptions() {
        System.out.println("Geben Sie einen Bruch gefolgt von einen der\n"
                + "gueltigen Operatoren '+ '*' - /' gefolgt von einem zweiten\n"
                + "Bruch hintereinander ein! (Zwischen Bruch und Operator\n"
                + "und dem anderen Bruch sind Leerzeichen zu setzen!)");
    }
}
