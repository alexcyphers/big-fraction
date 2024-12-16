package edu.grinnell.csc207.util;


/**

 * Stores registers that can be used to hold fractions corresponding with the letters from a to z.

 *

 * @author Alex Cyphers

 */

public class BFRegisterSet {

  private final BigFraction[] registers;

  public BFRegisterSet() {
    this.registers = new BigFraction[26];
  }

  // - stores the given value in the specified register.
  public void store(char register, BigFraction val) {
    for (int i = 0; i < 26; i++) {
      if ((int)register - 97 == i) {
        this.registers[i] = val;
      } // if
    } // for-loop
  }


  // retrieves the value from the given register.
  public BigFraction get(char register) {
    return registers[(int)register - 97];
  }
}

    


