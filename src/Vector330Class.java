import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class Vector330Class {
    private double x;
    private double y;
    private static double EPS = 0.0000000001;

    /**
     * Sets a new Vector330 object with null input.
     */
    public Vector330Class(){
        this.x = 0;
        this.y = 0;
    }

    /**
     * Sets a new Vector330 object with double input values.
     * @param x is the value to set the Vector 330 object's x value to.
     * @param y is the value to set the Vector 330 object's y value to.
     * */
    public Vector330Class(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Sets a new Vector330 object with integer input values.
     * @param x is the value to set the Vector 330 object's x value to.
     * @param y is the value to set the Vector 330 object's y value to.
     */
    public Vector330Class(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Sets a new Vector330 object with long input values.
     * @param x is the value to set the Vector 330 object's x value to.
     * @param y is the value to set the Vector 330 object's y value to.
     */
    public Vector330Class(long x, long y){
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x value of a Vector330 object as a double.
     * @return the double x value of the Vector330 object.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the x value of a Vector330 object as an integer.
     * @return the integer x value of the Vector330 object.
     */
    public int getXint(){
        return (int) x;
    }

    /**
     * Gets the x value of a Vector330 object as a long.
     * @return the long x value of the Vector330 object.
     */
    public long getXlong(){
        return (long) x;
    }

    /**
     * Gets the y value of a Vector330 object as a double.
     * @return the double y value of the Vector330 object.
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the y value of a Vector330 object as an integer.
     * @return the integer y value of the Vector330 object.
     */
    public int getYint(){
        return (int) y;
    }

    /**
     * Gets the y value of a Vector330 object as a long.
     * @return the long y value of the Vector330 object.
     */
    public long getYlong(){
        return (long) y;
    }

    /**
     * Sets a Vector330 objects x value to the double value of the parameter passed.
     * @param x is the double value to set the Vector33 object's x value to.
     */
    public void setX(double x){
        this.x = x;
    }

    /**
     * Sets a Vector330 objects x value to the integer value of the parameter passed.
     * @param x is the integer value to set the Vector33 object's x value to.
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Sets a Vector330 objects x value to the long value of the parameter passed.
     * @param x is the long value to set the Vector33 object's x value to.
     */
    public void setX(long x){
        this.x = x;
    }

    /**
     * Sets a Vector330 objects y value to the double value of the parameter passed.
     * @param y is the double value to set the Vector33 object's y value to.
     */
    public void setY(double y){
        this.y = y;
    }

    /**
     * Sets a Vector330 objects y value to the integer value of the parameter passed.
     * @param y is the integer value to set the Vector33 object's y value to.
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Sets a Vector330 objects y value to the long value of the parameter passed.
     * @param y is the long value to set the Vector33 object's y value to.
     */
    public void setY(long y){
        this.y = y;
    }

    /**
     * Checks for equality between to Vector330 objects.
     * @param v is the second Vector330 object passed in to check for equality.
     * @return true if they are the same, false if they are different.
     */
    public boolean equals(Vector330Class v){
        return (Math.abs(this.x-v.x) <= EPS || Math.abs(this.y-v.y) <= EPS);
    }

    /**
     * Adds two Vector330 objects together.
     * @param v is the second Vector330 object to be added to the first.
     * @return a new Vector330 object with the new x y values of the added vectors.
     */
    public Vector330Class add(Vector330Class v){
        return new Vector330Class((this.x + v.x), (this.y + v.y));
    }

    /**
     * Subtracts two Vector330 objects.
     * @param v is the second Vector330 object to be subtracted from the first.
     * @return a new Vector330 object with the new x y values of the subtracted vectors.
     */
    public Vector330Class subtract(Vector330Class v){
        return new Vector330Class((this.x - v.x), (this.y - v.y));
    }

    /**
     * Calculates the dot product of two Vector330 objects.
     * @param v is the second Vector330 object to be used in the dot product calculation.
     * @return a new Vector330 object with the x y values resulting from the dot product of the two passed vectors.
     */
    public double dotProduct(Vector330Class v){
        return ((this.x * v.x) + (this.y * v.y));
    }

    /**
     * Scales the value of a Vector330 object by a certain factor.
     * @param s is the factor to scale the Vector330 objects x y values by.
     * @return a new Vector330 object with the new scaled x y values.
     */
    public Vector330Class scale(double s){
        return new Vector330Class(this.x * s, this.y * s);
    }

    /**
     * Finds the magnitude of a Vector330 object
     * @return the double value of a Vector330 object's magnitude.
     */
    public double magnitude(){
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }

    /**
     * Finds the direction of a Vector330 object.
     * @return the double value of a Vector330 objects direction.
     */
    public double direction(){
        return Math.atan2(this.y, this.x);
    }

    /**
     * Normalizes a Vector330 object.
     * @return a new Vector330 object with a normalized x y value.
     */
    public Vector330Class normalize(){
        if(this.magnitude() <= EPS){
            return new Vector330Class(0, 0);
        }
        else{
            return new Vector330Class(this.x * (1/this.magnitude()), this.y * (1/this.magnitude()));
        }
    }

    /**
     * Prints out a Vector330 object as a string in the correct format.
     * @return a string containing the contents of a Vector330 object in the correct format.
     */
    public java.lang.String toString(){
        DecimalFormat df = new DecimalFormat("0.0");
        return "< " + df.format(this.x) + ", " + df.format(this.y) + " >";
    }

    /**
     * Parses a scanner object to find the values of a Vector330 object.
     * @param s is the scanner object containing the values to be parsed.
     * @return a new Vector330 object containing the x y values parsed from the scanner.
     * @throws Exception if the scanner is not formatted correctly and what is formatted incorrectly.
     */
    public static Vector330Class parseVector(java.util.Scanner s) throws Exception {
        double newX;
        double newY;
        Pattern originalPattern = s.delimiter();
        s.useDelimiter("[" + originalPattern + ",]");
        if (s.hasNext("<")) { // Reading in the first <
            s.next("<"); // Consume it
            if (s.hasNextDouble()) { // Reading the X value
                newX = s.nextDouble(); // Set it
                s.useDelimiter(originalPattern);
                if (s.hasNext(",")) { // Read and consume the ','
                    s.next(",");
                    if (s.hasNextDouble()) { // Reading the Y value
                        newY = s.nextDouble(); // Set it
                        try{
                            s.next(">"); // Look for the closing >
                        }
                        catch(InputMismatchException e){ // If ts not there, throw an exception
                            throw new Exception("Error occurred, no closing bracket."); // No closing bracket found
                        }
                    }
                    else { // The following are the exception throws and the associated error message for the missing item.
                        throw new Exception("Error occurred while parsing input, please check your second value.");
                    }
                }
                else {
                    throw new Exception("Error occurred while parsing input, please check your comas.");
                }
            }
            else {
                throw new Exception("Error occurred while parsing input, please check your first valur");
            }
        }
        else {
            throw new Exception("Error occurred, no starting bracket");
        }
        return new Vector330Class(newX, newY); // Return that vector!
    }
}
