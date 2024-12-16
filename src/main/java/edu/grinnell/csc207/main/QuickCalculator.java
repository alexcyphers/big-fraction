package edu.grinnell.csc207.main;


import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.CalculatorIOUtils;
import java.io.PrintWriter;


/**
 * Calculator which computes the equations all from the command line.
 *
 * @author Alex Cyphers
 */
public class QuickCalculator {

  /**
   * The expected index of the register fin the input line.
   */
  private static final int REGISTER_INDEX = 6;

  /**
   * Generate a few fractions and print them out.
   *
   * @param args
   *   Command-line arguments; ignored.
   *
   * @throws Exception
   *   When we have some unexpected I/O issue.
   */
  public static void main(String[] args) throws Exception {

    PrintWriter pen = new PrintWriter(System.out, true);
    BFCalculator calc = new BFCalculator();
    BFRegisterSet registerSet = new BFRegisterSet();

    for (int argIndex = 0; argIndex < args.length; argIndex++) {
      String line = args[argIndex];
      if (line.length() == 0) {
        pen.println("Failed [Invalid expression]");
        break;
      } // if

      try {
        if (line.startsWith("STORE")) {
          if (line.length() <= REGISTER_INDEX
              || (line.charAt(REGISTER_INDEX) < 'a' || line.charAt(REGISTER_INDEX) > 'z')) {
            pen.println(line + ": Failed [STORE command received invalid register]");
            continue;
          } // if
          registerSet.store(line.charAt(REGISTER_INDEX), calc.get());
          pen.println(line + " -> STORED");
          continue;
        } else {
          String[] expression = line.split(" ");
          BigFraction result = CalculatorIOUtils.computeExpression(expression, registerSet);
          calc.clear();
          calc.add(result);
          pen.println(line + " -> " + result);
        } // if/else
      } catch (Exception e) {
        pen.println(line + ": Failed [Invalid expression]");
      } // try/catch
    } // while-loop
  } // main(String[])
} // class QuickCalculator
