package edu.grinnell.csc207.main;


import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.CalculatorUtils;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;


/**

 * Allows the user to write equations one line at a time.

 *

 * @author Alex Cyphers

 */

public class InteractiveCalculator {

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
          if (line.length() <= 6 || (line.charAt(6) < 'a' || line.charAt(6) > 'z')) {
            pen.println("Error [Invalid expression]");
            continue;
          } // if
          registerSet.store(line.charAt(6), calc.get());
          pen.println("STORED " + calc.get());
          continue;
        } else {
          String[] expression = line.split(" ");
          BigFraction result = CalculatorUtils.readExpression(expression, registerSet);
          calc.clear();
          calc.add(result);
          pen.println(result);
        } // if/else
      } catch (Exception e) {
        pen.println("ERROR [Invalid expression]");
      }
    } // while-loop
  } // main
}