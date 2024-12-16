package edu.grinnell.csc207.util;


/**

 * Stores the object methods for the calculator and stores the last value calculated.

 *

 * @author Alex Cyphers

 */


public class BFCalculator {

    //Fields
    private BigFraction fract = new BigFraction(0,1);
    

    //gets the last computed value (returns 0 if there is no such value).
    public BigFraction get(){
        return new BigFraction(fract.num, fract.denom);
    } 

    //adds val to the last computed value.
    public void add(BigFraction val){
        this.add(val);
    }

    //subtracts val from the last computed value.
    public void subtract(BigFraction val){

        this.subtract(val);
    }

    //multiplies the last computed value by val.
    public void multiply(BigFraction val){
        this.multiply(val);
    }

    //divides the last computed value by val.
    public void divide(BigFraction val){
        this.divide(val);
    }



    //resets the last computed value to 0.
    public void clear(){
        BigFraction zero = new BigFraction(0, 1);
        this.multiply(zero);
    }
}
