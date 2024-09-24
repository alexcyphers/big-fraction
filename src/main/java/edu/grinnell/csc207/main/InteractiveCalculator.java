package edu.grinnell.csc207.main;


import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BFCalculator;
import java.io.PrintWriter;
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
    pen.println("Have not figured out scanner yet");
    pen.print(">");
    pen.flush();
    BigFraction sum = new BigFraction(0, 1);
    String lines = eyes.nextLine();
    String[] values = lines.split(" ");
    eyes.close();


    
    String constructor = "";

    for (int i = 0; i<values.length; i++) {


      if(i % 2 == 0 && isConstructor(values[i])){
        System.err.println("ERROR [Invalid expression]");
      }
      else if(i % 2 == 1 && !isConstructor(values[i])){
        System.err.println("ERROR [Invalid expression]");
      }

      if(i == 0){
        sum = sum.add(new BigFraction(values[i]));
      }


      if(args[i].equals("+")){
        constructor = new String("+");
        continue;
      }
      else if(args[i].equals("-")){
        constructor = new String("-");
        continue;
      }
      else if(args[i].equals("*")){
        constructor = new String("*");
        continue;
      }
      else if(args[i].equals("/")){
        constructor = new String("/");
        continue;
      }


      if(constructor.equals("+")){
        sum = sum.add(new BigFraction(values[i]));
      }
      else if(constructor.equals("-")){
        sum = sum.subtract(new BigFraction(values[i]));
      }
      else if(constructor.equals("/")){
        sum = sum.divide(new BigFraction(values[i]));
      }
      else if(constructor.equals("*")){
        sum = sum.multiply(new BigFraction(values[i]));
      }
      

      
    } // for


    

} // class Interactive Calculator



 private static Boolean isConstructor(String arg){
   if(arg.equals("+")){
     return true;
   }
   else if(arg.equals("-")){
     return true;
   }
   else if(arg.equals("/")){
     return true;
   }
   else if(arg.equals("*")){
     return true;
   }

   return false;

 }
}
/*
 mvn compile -q
 mvn package -q
 java -jar target/big-fraction-1.0.jar
 */