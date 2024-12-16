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

      
      if (line.startsWith("STORE")) {
        registerSet.store(line.charAt(6), calc.get());
        pen.println("STORED");
      } else {
        String[] expression = line.split(" ");
        BigFraction result = null;
        for (int i = 0; i < expression.length; i++) {
           if (isOperator(expression[i])) {
             BigFraction next = new BigFraction(expression[i+1]);
             result = compute(expression[i], result, next);
             i++;
           } else if (result == null) {
             result = new BigFraction(expression[i]);
           } else {
            pen.println("Invalid Expression");
          } // if/else
        } // for-loop
        pen.println(result);
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
  }


  private static Boolean isOperator(String arg){
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
}