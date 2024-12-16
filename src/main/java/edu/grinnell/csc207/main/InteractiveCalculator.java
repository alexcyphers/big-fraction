package edu.grinnell.csc207.main;


import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BFCalculator;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;


/**

 * Allows the user to write equations one line at a time.

 *

 * @author Alex Cyphers

 */

public class InteractiveCalculator{

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
                pen.println("ERROR [Register not initialized]");
              } // if/else
            } else if (isValidFraction(expression[i])) {
              result = new BigFraction(expression[i]);
            } else {
              pen.println("ERROR [Invalid expression]");
              break;
            } // if/else
            break;
          } // if

          if (expression.length < 3 || !isOperator(expression[1])) {
            pen.println("ERROR [Invalid expression]");
            break;
          } // if

          if (isOperator(expression[i])) {
            if (result == null) {
              pen.println("ERROR [Invalid expression]");
              break;
            } // if

            BigFraction next = null;

            if (i + 1 < expression.length) {
              next = getValue(expression[i + 1], registerSet);
              if (next == null) {
                pen.println("ERROR [Invalid expression]");
                break;
              } // if
            }
            result = compute(expression[i], result, next);
            i++;
          } else if (result == null) {
            result = getValue(expression[i], registerSet);
            if (result == null) {
              pen.println("ERROR [Invalid expression]");
              break;
            } // if
          } else {
            pen.println("ERROR [Invalid expression]");
            break;
          } // if/else
        } // for-loop
        calc.clear();
        calc.add(result);
        pen.println(result);
      } // if/else
    } catch (Exception e) {
      // Catch any errors so that program keeps running. Error will
      // be outputted.
    }
    } // while-loop
  } // main

  private static BigFraction getValue(String arg, BFRegisterSet registerSet) {
    if (arg.length() == 1 && arg.charAt(0) >= 'a' && arg.charAt(0) <= 'z') {
      if (registerSet.get(arg.charAt(0)) != null) {
        return registerSet.get(arg.charAt(0));
      } else {
        return null;
      } // if/else
    } // if
    if (isValidFraction(arg)) {
      return new BigFraction(arg);
    } // if
    return null;
  } // getValue(String, BFRegisterSet)


  private static BigFraction compute(String operator, BigFraction num1, BigFraction num2) {
    if (operator.equals("+")) {
      return num1.add(num2);
    } else if (operator.equals("-")) {
      return num1.subtract(num2);
    } else if(operator.equals("/")) {
      if (num2.numerator().equals(BigInteger.ZERO)) {
        throw new ArithmeticException("Can't divide by zero");
      } // if
      return num1.divide(num2);
    } else if (operator.equals("*")) {
      return num1.multiply(num2);
    } else {
      throw new IllegalArgumentException("Not an operator: " + operator);
    } // if/else
  } // compute(String, BigFraction, BigFraction)



  private static Boolean isOperator(String arg) {
    return arg.equals("+") || arg.equals("-") || arg.equals("/") || arg.equals("*");
  } // isOperator(String)


  private static Boolean isValidFraction(String fraction) {
    try {
      if (fraction.contains("/")) {
        Integer.parseInt(fraction.substring(0, fraction.indexOf((int)'/')));
        Integer.parseInt(fraction.substring(fraction.indexOf((int)'/') + 1));
      } else {
        Integer.parseInt(fraction);
      } // if/else
      return true;
    } catch (Exception e) {
      return false;
    } // try/catch
  } // isValidFraction(String)
}