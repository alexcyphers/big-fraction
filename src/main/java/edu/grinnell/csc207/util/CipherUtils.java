package edu.grinnell.csc207.util;


public class CipherUtils {
  
  private static int letter2int(char letter){
       int num = (int) (letter - 97);
       return num;
  }

  private static char int2letter(int i){
        char c = (char) (i+97);
        return c;
  }


    //uses the Caeser Cipher to encrypt a string consisting of only lowercase letters, using the given letter as the “key”.
  public static String caesarEncrypt(String str, char letter){
        char [] arr = str.toCharArray();

        for(int i = 0; i<str.length(); i++){
            int num = letter2int(arr[i]);
            int den = letter2int(letter);
            arr[i] = int2letter((num + den) % 26);
        }

        return new String(arr);
  }


  public static String caesarDecrypt(String str, char letter) {
    char [] arr = str.toCharArray();

        for(int i = 0; i<str.length(); i++){
            int ch= letter2int(arr[i]);
            int key = letter2int(letter);
            int ch_key = ch - key;

            if(ch_key < 0){
              ch_key += 26;
            }
            
            arr[i] = int2letter(ch_key % 26);
      }

    return new String(arr);
  }




  public static String vigenereEncrypt(String str, String key) {
    char [] arr = str.toCharArray();
    char [] arr_key = key.toCharArray();
    int count = -1;
    int len = key.length();

    for(int i = 0; i<str.length(); i++){

      if(count == len-1){
        count = 0;
      }
      else
      {
        count++;
      }
     
      int ch = letter2int(arr[i]);
      int encrypt_key = letter2int(arr_key[count]);
      arr[i] = int2letter((ch + encrypt_key) % 26);
    }

    return new String(arr);
  }



  public static String vigenereDecrypt(String str, String key) {
    char [] arr = str.toCharArray();
    char [] arr_key = key.toCharArray();
    int count = -1;
    int len = key.length();
    
    for(int i = 0; i<str.length(); i++){

      if(count == len-1){
        count = 0;
      }
      else
      {
        count++;
      }
     
      int ch = letter2int(arr[i]);
      int encrypt_key = letter2int(arr_key[count]);
      int str_key = ch - encrypt_key;

      if(str_key < 0){
        str_key += 26;
      }

      arr[i] = int2letter(str_key % 26);
    }

    return new String(arr);
  }
}
