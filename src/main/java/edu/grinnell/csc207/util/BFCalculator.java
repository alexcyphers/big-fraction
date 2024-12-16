package edu.grinnell.csc207.util;


/**
 * Stores the object methods for the calculator and stores the last value calculated.
 *
 * @author Alex Cyphers
 */
public class BFCalculator {

  /**
  * Big fraction field which stores the last value calculated.
  */
  private BigFraction fract = new BigFraction(0, 1);

  /**
  * Gets the last computed value and returns 0 if there is no such value
  *.
  * @return the last computed value (0 if no such value).
  */
  public BigFraction get() {
    return fract;
  } // get()

  /**
   * Adds the given value to the last computed value.
   *
   * @param val
   */
  public void add(BigFraction val) {
    this.fract = this.fract.add(val);
  } // add(BigFraction)

  /**
   * Subtracts the given value to the last computed value.
   *
   * @param val
   */
  public void subtract(BigFraction val) {
    this.fract = this.fract.subtract(val);
  } // subtract(BigFraction)

  /**
   * Multiplies the given value by the last computed value.
   *
   * @param val
   */
  public void multiply(BigFraction val) {
    this.fract = this.fract.multiply(val);
  } // multiply(BigFraction)

  /**
   * Divides the last computed value by the given value.
   *
   * @param val
   */
  public void divide(BigFraction val) {
    if (val.numerator().equals(val.denominator())) {
      throw new ArithmeticException("Can't divide by zero");
    } // if
    this.fract = this.fract.divide(val);
  } // divide(BigFraction)

  /**
   * resets the last computed value to 0.
   */
  public void clear() {
    this.fract = new BigFraction(0, 1);
  } // clear()
} // class BFCalculator
