package edu.grinnell.csc207.util;
import java.math.BigInteger;


/**
 * Implements the fractions used in the program, and allows the objects to be called.
 * It also contains different operations that allow you to add multiply divide or
 * subtract the fractions. It also simplifies them.
 *
 * @author Alex Cyphers
 */
public class BigFraction {

  // +------------------+---------------------------------------------
  // | Design Decisions |
  // +------------------+


  /*
   * (1) Denominators are always positive. Therefore, negative fractions
   * are represented with a negative numerator. Similarly, if a fraction
   * has a negative numerator, it is negative.
   *
   * (2) Fractions are not necessarily stored in simplified form. To
   * obtain a fraction in simplified form, one must call the `simplify`
   * method.
   */


  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+


  /** The numerator of the fraction. Can be positive, zero or negative. */
  private BigInteger num;


  /** The denominator of the fraction. Must be non-negative. */
  private BigInteger denom;


  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+


  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(BigInteger numerator, BigInteger denominator) {
    this.num = numerator;
    this.denom = denominator;

    if (this.denom.compareTo(BigInteger.ZERO) < 0) {
      this.num = this.num.negate();
      this.denom = this.denom.negate();
    } // if
  } // BigFraction(BigInteger, BigInteger)


  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(int numerator, int denominator) {
    this.num = BigInteger.valueOf(numerator);
    this.denom = BigInteger.valueOf(denominator);

    if (this.denom.compareTo(BigInteger.ZERO) < 0) {
      this.num = this.num.negate();
      this.denom = this.denom.negate();
    } // if
  } // BigFraction(int, int)


  /**
   * Build a new fraction by parsing a string.
   *
   *
   * @param str
   *   The fraction in string form
   */
  public BigFraction(String str) {
    if (str.contains("/")) {
      this.num = BigInteger.valueOf(Integer.parseInt(str.substring(0, str.indexOf((int) '/'))));
      this.denom = BigInteger.valueOf(Integer.parseInt(str.substring(str.indexOf((int) '/') + 1)));
    } else {
      this.num = BigInteger.valueOf(Integer.parseInt(str));
      this.denom = BigInteger.ONE;
    } // if/else

    // Make negative
    if (this.denom.compareTo(BigInteger.ZERO) < 0) {
      this.num = this.num.negate();
      this.denom = this.denom.negate();
    } // if
  } // BigFraction


  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+


  /**
   * Express this fraction as a double.
   *
   * @return the fraction approxiamted as a double.
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()


  /**
   * Add another faction to this fraction.
   *
   * @param addend
   *   The fraction to add.
   *
   * @return the result of the addition.
   */
  public BigFraction add(BigFraction addend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(addend.denom);

    // The numerator is more complicated
    resultNumerator = (this.num.multiply(addend.denom)).add(addend.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator).simplify();
  } // add(BigFraction)


  /**
   * Get the denominator of this fraction.
   *
   * @return the denominator
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()


  /**
   * Get the numerator of this fraction.
   *
   * @return the numerator
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()


  /**
   * Convert this fraction to a string for ease of printing.
   *
   * @return a string that represents the fraction.
   */
  public String toString() {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } // if it's zero

    // If it can be a whole number.
    if (this.num.mod(this.denom).equals(BigInteger.ZERO)) {
      return this.num.divide(this.denom).toString();
    } // if

    return this.num + "/" + this.denom;
  } // toString()

  /**
   * Multiplies two fractions and returns the resulting BigFraction.
   *
   * @param a
   *    the big fraction to multiply by.
   * @return
   *    The result of muliplying two big fractions.
   */
  public BigFraction multiply(BigFraction a) {
    return new BigFraction(this.num.multiply(a.num), this.denom.multiply(a.denom)).simplify();
  } //multiply(BigFraction, BigFraction)



  /**
   * Divides two BigFractions and returns the resulting BigFraction.
   *
   * @param a the BigFraction to divide by, it can't have a denominator
   * of zero.
   *
   * @return a bigfraction representing the result of dividing the
   * two bigfractions.
   */
  public BigFraction divide(BigFraction a) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(a.num);
    resultNumerator = this.num.multiply(a.denom);

    return new BigFraction(resultNumerator, resultDenominator).simplify();
  } // divide(BigFraction)



  /**
   * Subtracts two BigFractions and returns the resulting BigFraction.
   *
   * @param a the BigFraction which you are subtracting by.
   *
   * @return a bigfraction representing the result of subtracting the
   * two bigfractions.
   */
  public BigFraction subtract(BigFraction a) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(a.denom);

    // The numerator is more complicated
    resultNumerator = (this.num.multiply(a.denom)).subtract(a.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator).simplify();
  } // subtract(BigFraction)


  /**
   * Computes the fractional remainder as a result of simplifying
   * the fraction to a whole number.
   *
   * @return a bigfraction representing the fractional remainder
   * from simplifying
   */
  public BigFraction fractional() {
    return new BigFraction(this.num.mod(this.denom), this.denom);
  } //fractional()


  /**
   * Simplifies a BigFraction and returns the resulting BigFraction.
   *
   * @return a bigfraction representing the result of simplifying the
   * given bigfraction.
   */
  public BigFraction simplify() {
    BigInteger resultNumerator = this.num.divide(this.num.gcd(this.denom));
    BigInteger resultDenominator = this.denom.divide(this.num.gcd(this.denom));

    return new BigFraction(resultNumerator, resultDenominator);
  } // simplify()
} // class BigFraction


