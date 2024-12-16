package edu.grinnell.csc207.util;


/**
 * Stores registers that can be used to hold fractions
 * corresponding with the letters from a to z.
 *
 * @author Alex Cyphers
 */
public class BFRegisterSet {

  /**
   * The size of the alphabet used for the size of the array.
   */
  private static final int ALPHABET_SIZE = 26;

  /**
   * Array of big fractions that correspond with their given register.
   */
  private final BigFraction[] values;

  /**
   * Builds a register set with an empty array of
   * big fractions.
   */
  public BFRegisterSet() {
    this.values = new BigFraction[ALPHABET_SIZE];
  } // BFRegisterSet()


  /**
   * Stores the given value in the corresponding register.
   *
   * @param register
   *    the register to store the value in.
   * @param val
   *    the value being stored in the given register.
   */
  public void store(char register, BigFraction val) {
    this.values[register - 'a'] = val;
  } // store(char, BigFraction)


  /**
   * Gets the value from the specified register.
   *
   * @param register
   *    the register of the value being retrieved.
   * @return the big fraction value corresponding with the register.
   */
  public BigFraction get(char register) {
    return this.values[register - 'a'];
  } // get(char)
} // class BFRegisterSet




