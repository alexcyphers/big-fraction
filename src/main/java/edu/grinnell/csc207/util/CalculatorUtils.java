package edu.grinnell.csc207.util;

import java.math.BigInteger;

public class CalculatorUtils {
    
  public static BigFraction getValue(String arg, BFRegisterSet registerSet) {
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



  public static Boolean isOperator(String arg) {
    return arg.equals("+") || arg.equals("-") || arg.equals("/") || arg.equals("*");
  } // isOperator(String)


  public static Boolean isValidFraction(String fraction) {
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
