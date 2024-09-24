package edu.grinnell.csc207.util;


/**

 * Stores registers that can be used to hold fractions corresponding with the letters from a to z.

 *

 * @author Alex Cyphers

 */

public class BFRegisterSet {
    

    
    BigFraction [] arr = new BigFraction[26];

    public void store(char register, BigFraction val){ // - stores the given value in the specified register.

        for(int i = 0; i<26; i++){
            if((int)register-97==i){
                this.arr[i] = val;
            }
        }
    } 



    public BigFraction get(char register){ //- retrieves the value from the given register.
        return arr[(int)register-97];
    } 

}
