package edu.grinnell.csc207.util;

import java.math.BigInteger;


/**
 * Utility class for the calculators input and output. Specifically
 * used for breaking down similar code used by different calculators
 * into methods. It helps interpret different arguments, expressions,
 * and operations used in the calculators so that calculations can
 * be made.
 */
public class CalculatorIOUtils {

  /**
   * Returns the value from only one argument. If the
   * argument is a register it will return its corresponding
   * BigFraction. If the argument isn't valid it just returns
   * null.
   *
   * @param arg
   *    the argument which determines the value returned.
   * @param registerSet
   *    BFRegister used to extract the stored value from a register.
   * @return the value determined by the argument (BigFraction or null).
   */
  public static BigFraction getValue(String arg, BFRegisterSet registerSet) {
    // Check if the register is valid.
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

  /**
   * Computes two BigFractions given the specified operation and
   * returns the resulting BigFraction. The computation can be
   * considered something like (num1 * num2 or num1 / num2).
   *
   * @param operator
   *    the operation used to compute the BigFractions.
   * @param num1
   *    the first BigFraction.
   * @param num2
   *    the second BigFraction.
   *
   * @return the resulting BigFraction from operating
   * on the two given BigFractions.
   */
  public static BigFraction compute(String operator, BigFraction num1, BigFraction num2) {
    if (operator.equals("+")) {
      return num1.add(num2);
    } else if (operator.equals("-")) {
      return num1.subtract(num2);
    } else if (operator.equals("/")) {
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


  /**
   * Determines if the given argument is an operator used
   * in this calculator. Returns true if it is an operator
   * and false otherwise.
   *
   * @param arg
   *    the argument being checked to see if it is an operator.
   *
   * @return true if the given argument is an operator.
   */
  public static Boolean isOperator(String arg) {
    return arg.equals("+") || arg.equals("-") || arg.equals("/") || arg.equals("*");
  } // isOperator(String)

  /**
   * Checks to see if the given fraction is a valid
   * BigFraction. It is expected to be either a whole
   * number or a fraction with a numerator or denominator.
   * It simply returns false if BigFraction fails to be built
   * from the given fraction string.
   *
   * @param fraction t
   * the string being checked to see if it's a valid fraction.
   *
   * @return true if the fraction was built and false otherwise.
   */
  public static Boolean isValidFraction(String fraction) {
    try {
      if (fraction.contains("/")) {
        Integer.parseInt(fraction.substring(0, fraction.indexOf((int) '/')));
        Integer.parseInt(fraction.substring(fraction.indexOf((int) '/') + 1));
      } else {
        Integer.parseInt(fraction);
      } // if/else
      return true;
    } catch (Exception e) {
      return false;
    } // try/catch
  } // isValidFraction(String)

  /**
   * Reads the given expression and computes it to return the
   * expected BigFraction. If the given expression isn't valid
   * it will throw an error. It can also compute the expression
   * using variables that store BigFractions.
   *
   * @param expression the string array with each given operation used
   * to compute the expected BigFraction.
   * @param registerSet the register set which stores all the registers
   * that store BigFraction values, which is used for operating with
   * variables.
   *
   * @return the result from computing the expression.
   */
  public static BigFraction computeExpression(String[] expression, BFRegisterSet registerSet) {
    BigFraction result = null;

    for (int i = 0; i < expression.length; i++) {
      Boolean isCharAlpha = false;
      // Check if the first character is lowercase.
      if (!(expression[i].charAt(0) < 'a' || expression[i].charAt(0) > 'z')) {
        isCharAlpha = true;
      } // if

      // For when there is only one argument in the entire expression.
      if (expression.length == 1) {
        if (isCharAlpha) {
          // Check if the register is in the register set.
          if (registerSet.get(expression[i].charAt(0)) != null) {
            result = registerSet.get(expression[i].charAt(0));
          } else {
            throw new IllegalArgumentException();
          } // if/else
        } else if (isValidFraction(expression[i])) {
          result = new BigFraction(expression[i]);
        } else {
          throw new IllegalArgumentException();
        } // if/else
        break;
      } // if

      // If the argument is an operator try to compute
      // with the operation.
      if (isOperator(expression[i])) {
        // For when you try to operate without a number before
        // the operator.
        if (result == null) {
          throw new IllegalArgumentException();
        } // if

        // The second BigFraction which is being operated on.
        BigFraction next = null;

        // Check to see that not at last argument in the expression.
        if (i + 1 < expression.length) {
          next = getValue(expression[i + 1], registerSet);
          if (next == null) {
            throw new IllegalArgumentException();
          } // if
        } // if
        result = compute(expression[i], result, next);
        i++;
        // For when the BigFraction before the operator has not
        // been processed yet.
      } else if (result == null) {
        result = getValue(expression[i], registerSet);
        if (result == null) {
          throw new IllegalArgumentException();
        } // if
      } else {
        throw new IllegalArgumentException();
      } // if/else
    } // for-loop
    return result;
  } // computeExpression(String[], BFRegisterSet)
} // class CalculatorIOUtils.java
