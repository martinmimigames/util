package com.martinmimiGames.util.objects.collision;

/**
 * this is the algorithms for com.martinmimiGames.util.objects.collision classes
 * @author martinmimi
 * @since 2021/3/30
 * @version 0.0.0.1
 */
public class Algorithms {

    /**
     * this is equal to tan-1 (maths)
     * or theta in tan(theta) = gradient
     * @param g gradient
     * @return angle in degree in float
     */
    public float atan(float g) {
        return (float) Math.toDegrees(Math.atan(g));
    }

    /**
     * this is equal to sin (maths)
     * @param a angle in degree in float
     * @return result in float in float
     */
    public float sin(float a) {
        return (float) Math.sin(Math.toRadians(a));
    }

    /**
     * this is equal to cos (maths)
     * @param a angle in degree
     * @return result in float
     */
    public float cos(float a) {
        return (float) Math.cos(Math.toRadians(a));
    }

    /**
     * this is equal to entered value times entered value
     * (value * value)
     * (square of value)
     * @param num
     * @return result in float
     */
    public float sqr(float num) {
        return num * num;
    }

    /**
     * this gives the square root of a number
     * @param num the number that needs to be square rooted
     * @return the result in float
     */
    public float sqrt(float num){
        return (float) Math.sqrt((double) num);
    }

    /**
     * this gives the length of the longest side of a right angled triangle
     * @param base the length of the base of a right angled triangle
     * @param height the length of the height of a right angled triangle
     * @return the length of the longest side of a right angled triangle
     */
    public float getHypo(float base, float height){
        return sqrt(sqr(base) + sqr(height));
    }

}
