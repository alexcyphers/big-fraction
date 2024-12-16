package edu.grinnell.csc207.main;


import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.CalculatorIOUtils;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * Allows the user to write equations one line at a time.
 *
 * @author Alex Cyphers
 */
public class InteractiveCalculator {

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
    Scanner eyes = new Scanner(System.in);
    BFCalculator calc = new BFCalculator();
    BFRegisterSet registerSet = new BFRegisterSet();

    while (true) {
      pen.print("> ");
      pen.flush();
      String line = eyes.nextLine();


      try {
        if (line.startsWith("QUIT")) {
          pen.close();
          eyes.close();
          return;
        } // if

        if (line.startsWith("STORE")) {
          if (line.length() <= REGISTER_INDEX
              || (line.charAt(REGISTER_INDEX) < 'a' || line.charAt(REGISTER_INDEX) > 'z')) {
            pen.println("*** ERROR [STORE command received invalid register] ***");
            continue;
          } // if
          registerSet.store(line.charAt(REGISTER_INDEX), calc.get());
          pen.println("STORED");
          continue;
        } else {
          String[] expression = line.split(" ");
          BigFraction result = CalculatorIOUtils.computeExpression(expression, registerSet);
          calc.clear();
          calc.add(result);
          pen.println(result);
        } // if/else
      } catch (Exception e) {
        pen.println("*** ERROR [Invalid expression] ***");
      } // try/catch
    } // while-loop
  } // main(String[])
} // class InteractiveCalculator
