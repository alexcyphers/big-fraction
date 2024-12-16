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
    this.registers[register - 'a'] = val;
  }


  // retrieves the value from the given register.
  public BigFraction get(char register) {
    return this.registers[register - 'a'];
  }
}

    


