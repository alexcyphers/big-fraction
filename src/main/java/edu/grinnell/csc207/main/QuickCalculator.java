package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.BigFraction;



/**

 * Allows the user to write equations all on the command line.

 *

 * @author Alex Cyphers

 */



public class QuickCalculator{

    public static void main(String args[]){
     
    
    PrintWriter pen = new PrintWriter(System.out, true);

    BigFraction sum = new BigFraction(0, 1);   // Zero

    String constructor = "";

    for (int i = 0; i<args.length; i++) {


      if(i % 2 == 0 && isConstructor(args[i])){
        System.err.println("ERROR [Invalid expression]");
      }
      else if(i % 2 == 1 && !isConstructor(args[i])){
        System.err.println("ERROR [Invalid expression]");
      }

      if(i == 0){
        sum = sum.add(new BigFraction(args[i]));
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
        sum = sum.add(new BigFraction(args[i]));
      }
      else if(constructor.equals("-")){
        sum = sum.subtract(new BigFraction(args[i]));
      }
      else if(constructor.equals("/")){
        sum = sum.divide(new BigFraction(args[i]));
      }
      else if(constructor.equals("*")){
        sum = sum.multiply(new BigFraction(args[i]));
      }
      

      
    } // for

    pen.println(sum);

    pen.close();
    }

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





    

