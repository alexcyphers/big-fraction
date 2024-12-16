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



      if (line.startsWith("QUIT")) {
        pen.close();
        eyes.close();
        return;
      } // if
      
      if (line.startsWith("STORE")) {
        if (line.length() <= 6 || (line.charAt(6) < 'a' || line.charAt(6) > 'z')) {
          pen.println("Error: [Invalid expression]");
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
              if (isInRegister(expression[i].charAt(0), registerSet)) {
                result = registerSet.get(expression[i].charAt(0));
              } else {
                pen.println("ERROR [Register not initialized]");
              } // if/else
            } else if (isValidFraction(expression[i])) {
              result = new BigFraction(expression[i]);
            } else {
              pen.println("ERROR [Invalid expression]");
            } // if/else
            continue;
          } // if

          if (expression.length < 3 || !isOperator(expression[1])) {
            pen.println("ERROR [Invalid expression]");
          } // if
          // Check if current expression is a register
          if (isCharAlpha) {
            if (isInRegister(expression[i].charAt(0), registerSet)) {
              expression[i] = registerSet.get(expression[i].charAt(0)).toString();
            } else {
              pen.println("ERROR [Register not initialized]");
              continue;
            } // if
          } // if

          if (isOperator(expression[i])) {
            if (!isValidFraction(expression[i + 1])) {
              pen.println("ERROR [Invalid expression]");
              continue;
            } // if

            if (result == null) {
              pen.println("ERROR [Invalid expression]");
              continue;
            } // if

            BigFraction next;

            if (isCharAlpha) {
              if (isInRegister(expression[i + 1].charAt(0), registerSet)) {
                next = registerSet.get(expression[i + 1].charAt(0));
              } else {
                pen.println("ERROR [Register not initialized]");
                continue;
              } // if/else
            } else {
              next = new BigFraction(expression[i + 1]);
            }
            result = compute(expression[i], result, next);
            i++;
          } else if (result == null) {
            if (isValidFraction(expression[i])) {
              result = new BigFraction(expression[i]);
            } else {
              pen.println("ERROR [Invalid expression]");
            }
          } else {
            pen.println("ERROR [Invalid expression]");
          } // if/else
        } // for-loop
        calc.add(result);
        pen.println(result);
        calc.clear();
      } // if/else
    } // while-loop
  } // main


  public static BigFraction compute(String operator, BigFraction num1, BigFraction num2) {
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

    if (arg.equals("+")) {
      return true;
    } else if (arg.equals("-")) {
      return true;
    } else if(arg.equals("/")) {
      return true;
    } else if (arg.equals("*")) {
      return true;
    } else {
      return false;
    } // if/else
  } // isOperator(String)


  public static Boolean isInRegister(char register, BFRegisterSet registerSet) {
    if (registerSet.get(register) != null) {
      return true; 
    } else {
      return false;
    } // if/else
  } // inRegister(char, BFRegisterSet)

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