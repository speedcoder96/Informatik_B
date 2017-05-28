import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Every instance of <code>Fraction</code> represents a fraction with numerator
 * and decorator.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public class Fraction extends Number {

   private static Map<String, Fraction> cachedFractions;

   static {
      cachedFractions = new HashMap<String, Fraction>();
   }

   public static final String FRACTION_REGEX = "-?\\d+/[1-9]\\d*+";

   /**
    * Creates greatest common divisor for a and b.
    *
    * @param a
    * @param b
    * @return the greatest common divisor for a and b.
    */
   public static int gcd(int a, int b) {
      return b == 0 ? a : gcd(b, a % b);
   }

   private final int numerator;

   private final int denominator;

   /**
    * Creates a Fraction object with numerator and denominator 1, cancels the
    * fraction with creation.
    *
    * @param numerator
    */
   private Fraction(int numerator) {
      this(numerator, 1);
   }

   /**
    * Creates a Fraction object with numerator and denominator, cancels the
    * fraction by creation. Denominator == 0 is not allowed.
    *
    * @param numerator   the numerator
    * @param denominator the denominator, must not be 0.
    * @throws RuntimeException if <code>denominator</code> is 0
    */
   private Fraction(int numerator, int denominator) {
      if (denominator == 0) {
         throw new RuntimeException("denominator == 0 is not possible");
      }

      /*
       * creates greatest common divisior.
       */
      int gcd = Fraction.gcd(numerator, denominator);
      /*
       * check sign, make denominator positive
       */
      if (denominator / gcd < 0) {
         gcd *= -1;
      }

      this.numerator = numerator / gcd;

      this.denominator = denominator / gcd;
   }

   /**
    * Divides this Fraction with another Fraction
    *
    * @param another Fraction to divide this Fraction by
    * @return quotient as a new Fraction
    * @throws RuntimeException if <code>numerator</code> of <code>another</code> is 0
    */
   public Fraction divide(Fraction another) {
      return new Fraction(this.numerator * another.denominator,
              this.denominator * another.numerator);
   }

   public int getDenominator() {
      return this.denominator;
   }

   public int getNumerator() {
      return this.numerator;
   }

   /**
    * Multiplies this Fraction with another Fraction
    *
    * @param factor Fraction to multiply this Fraction with
    * @return product as a new Fraction
    */
   public Fraction multiply(Fraction factor) {
      return new Fraction(this.numerator * factor.numerator, this.denominator
              * factor.denominator);
   }

   /**
    * Multiplies this Fraction with all other Fraction instances given by
    * factors
    *
    * @param factors Fraction instances to multiply this Fraction with
    * @return product as a new Fraction
    */
   public Fraction multiply(Fraction... factors) {
      Fraction result = this;
      for (int i = 0; i < factors.length; i++) {
         result = result.multiply(factors[i]);
      }
      return result;
   }

   /**
    * Multiplies this Fraction with int n.
    *
    * @param n factor to multiply with
    * @return product as a new Fraction
    */
   public Fraction multiply(int n) {
      return new Fraction(this.numerator * n, this.denominator);
   }

   /**
    * Adds another Fraction to this Fraction. Returned Fraction will be simplified.
    * @param addend Fraction to add
    * @return sum as a new Fraction
    */
   public Fraction add(Fraction addend) {
      int newNumerator = this.getNumerator() * addend.getDenominator() + this.getDenominator() * addend.getNumerator();
      int newDenominator = this.getDenominator() * addend.getDenominator();
      return new Fraction(newNumerator, newDenominator);
   }

   /**
    * Subtracts a Fraction from this Fraction. Returned Fraction will be simplified.
    * @param subtrahend Fraction used to subtract
    * @return difference as a new Fraction
    */
   public Fraction subtract(Fraction subtrahend) {
      return add(subtrahend.multiply(-1));
   }

   /**
    * Turns a valid String representation of a Fraction into a Fraction.
    * Valid String format: (-)m/n , n != 0
    * @param fraction as String
    * @return Fraction
    * @throws RuntimeException if Fraction format is invalid
    */
   public static Fraction parseFraction(String fraction) {
       if (!fraction.matches(FRACTION_REGEX)) {
           throw new RuntimeException("Invalid fraction");
       }
       String[] numbers = fraction.split("/");
       int num = Integer.parseInt(numbers[0]);
       int den = Integer.parseInt(numbers[1]);
       Fraction temp = new Fraction(num, den);
       return cacheFraction(temp);
   }

    /**
     * Creates a fraction with a numerator <tt>numerator</tt>
     * and a denominator with value 1
     * @param numerator the numerator
     * @return an already cached fraction or the new instance
     */
   public static Fraction create(int numerator) {
       Fraction temp = new Fraction(numerator);
       return cacheFraction(temp);
   }

    /**
     * Creates a fraction with a numerator <tt>numerator</tt>
     * and a denominator <tt>denominator</tt>
     * @param numerator the numerator
     * @param denominator the denominator
     * @return an already cached fraction or the new instance
     */
   public static Fraction create(int numerator, int denominator) {
       Fraction temp = new Fraction(numerator, denominator);
       return cacheFraction(temp);
   }

    /**
     * Returns an already cached fraction, so that fractions
     * with the same value have the same instance
     * @param fraction
     * @return
     */
   private static Fraction cacheFraction(Fraction fraction) {
      String key = fraction.toString();
      if (cachedFractions.containsKey(key)) {
         return cachedFractions.get(key);
      } else {
         cachedFractions.put(key, fraction);
         return fraction;
      }
   }

   /**
    * Returns a string representation of this Fraction such as
    * numerator/denominator.
    *
    * @return This Fraction as a String
    */
   public String toString() {
      return numerator + "/" + denominator;
   }

   @Override
   public int intValue() {
      return (int)(doubleValue() + 0.5);
   }

   @Override
   public long longValue() {
      return intValue();
   }

   @Override
   public float floatValue() {
      return (float)doubleValue();
   }

   @Override
   public double doubleValue() {
      return (numerator / denominator);
   }
}