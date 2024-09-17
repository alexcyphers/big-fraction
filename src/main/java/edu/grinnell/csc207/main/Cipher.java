package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

public class Cipher {
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    for (int i = 0; i < args.length; i++) {
      pen.printf("args[%d] = \"%s\"\n", i, args[i]);
    }

    if(args.length < 4 || args.length > 4){
      pen.printf("Error: Expected 4 parameters, received %d", args.length);
      System.err.println("");
      return;
    }

    
    for(int i = 0; i < 4; i++){
     if(args[i].equals("")){
      System.err.println("Error: Empty keys are not permitted");
     }
    }
    
    String str1 = "";
    String str2 = "";
    Boolean is_String = false;
    
    outerloop:
    for(int i = 0; i<4; i++){ //Initial Search
      if(args[i].equals("-caesar")){ 
        for(int n = 0; n<4; n++){ //Second Search
          if(args[n].equals("-encode")){ 
            for(int j = 0; j<4; j++){ //Final Search
              if(isAlpha(args[j]) && args[j].length()>1){
                str1 = args[j];
                is_String = true;
              }
              else if(isAlpha(args[j]) && is_String == true && args[j].length()==1){
                str2 = args[j];
              }
            }

            if(str2.equals("")){
              System.err.println("Error: Invalid Parameters");
              break outerloop;
            }

            pen.printf("%s\n", CipherUtils.caesarEncrypt(str1, str2.charAt(0)));
          }
          else if(args[n].equals("-decode")){
            for(int j = 0; j<4; j++){ //Final Search
              if(isAlpha(args[j]) && args[j].length()>1){
                str1 = args[j];
                is_String = true;
              }
              else if(isAlpha(args[j]) && is_String == true && args[j].length()==1){
                str2 = args[j];
              }
            }

            if(str2.equals("")){
              System.err.println("Error: Invalid parameters");
              break outerloop;
            }
            
            pen.printf("%s\n", CipherUtils.caesarDecrypt(str1, str2.charAt(0)));
          }
        }
      }
      else if(args[i].equals("-vigenere")){ 
        for(int n = 0; n<4; n++){ //Second Search
          if(args[n].equals("-encode")){ 
            for(int j = 0; j<4; j++){ //Final Search
              if(isAlpha(args[j]) && is_String == false){
                str1 = args[j];
                is_String = true;
              }
              else if(isAlpha(args[j]) && is_String == true){
                str2 = args[j];
              }
            }

            if(str2.equals("")){
              System.err.println("Error: Invalid parameters");
              break outerloop;
            }

            pen.printf("%s\n", CipherUtils.vigenereEncrypt(str1, str2));
          }
          else if(args[n].equals("-decode")){
            for(int j = 0; j<4; j++){ //Final Search
              if(isAlpha(args[j]) && is_String == false){
                str1 = args[j];
                is_String = true;
              }
              else if(isAlpha(args[j]) && is_String == true){
                str2 = args[j];
              }
            }

            if(str2.equals("")){
              System.err.println("Error: Invalid parameters");
              break outerloop;
            }
            pen.printf("%s\n", CipherUtils.vigenereDecrypt(str1, str2));
          }
          else if(n == 3){
            System.err.println("Error: No valid action specified.  Legal values are '-encode' and '-decode'");
            break outerloop;
          }
        }
      }
      else if(i == 3){
        System.err.println("Error: No valid action specified.  Legal values are '-encode' and '-decode'");
        break outerloop;
      }
    }

    pen.close();
  }

  private static boolean isAlpha(String str){
    char arr[] = str.toCharArray();

    for(int i = 0; i<str.length(); i++){
      if(!(((int)arr[i])>= 97 && ((int)arr[i])<= 122)){
       return false;
      }
    }
    return true;
  }
}


