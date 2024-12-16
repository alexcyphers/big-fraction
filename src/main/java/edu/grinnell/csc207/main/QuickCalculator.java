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

public class QuickCalculator {

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
          if (line.length() <= 6 || (line.charAt(6) < 'a' || line.charAt(6) > 'z')) {
            pen.println(line + ": Failed [Invalid expression]");
            continue;
          } // if
          registerSet.store(line.charAt(6), calc.get());
          pen.println(line + " -> STORED");
          continue;
        } else {
          String[] expression = line.split(" ");
          BigFraction result = null;
  
          for (int i = 0; i < expression.length; i++) {
            Boolean isCharAlpha = false;
            // Check if the first character is lowercase.
            if (!(expression[i].charAt(0) < 'a' || expression[i].charAt(0) > 'z')) {
              isCharAlpha = true;
            } // if
  
            if (expression.length == 1) {
              if (isCharAlpha) {
                if (registerSet.get(expression[i].charAt(0)) != null) {
                  result = registerSet.get(expression[i].charAt(0));
                } else {
                  pen.println(line + ": FAILED [Register not initialized]");
                } // if/else
              } else if (CalculatorUtils.isValidFraction(expression[i])) {
                result = new BigFraction(expression[i]);
              } else {
                break;
              } // if/else
              break;
            } // if
  
  
            if (CalculatorUtils.isOperator(expression[i])) {
              if (result == null) {
                break;
              } // if
  
              BigFraction next = null;
  
              if (i + 1 < expression.length) {
                next = CalculatorUtils.getValue(expression[i + 1], registerSet);
                if (next == null) {
                  break;
                } // if
              }
              result = CalculatorUtils.compute(expression[i], result, next);
              i++;
            } else if (result == null) {
              result = CalculatorUtils.getValue(expression[i], registerSet);
              if (result == null) {
                break;
              } // if
            } else {
              break;
            } // if/else
          } // for-loop
          calc.clear();
          calc.add(result);
          pen.println(line + " -> " + result);
        } // if/else
      } catch (Exception e) {
        pen.println(line + ": Failed [Invalid expression]");
      }
    } // while-loop
  } // main
}