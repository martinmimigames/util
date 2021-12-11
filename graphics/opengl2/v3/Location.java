package com.martinmimiGames.util.graphics.opengl2.v3;

/**
 * This is the MGGames utility dependency.
 * Location for Draw dependency
 * @author martinmimi (from martinmimigames)
 * @since about 11-12-2021 dd-mm-yyyy
 * @version 1.0.0 first release
 */

public class Location {

    public static final int X = 0;
    public static final int Y = 1;

    /**
     * coordinates,
     * can use X,Y values for retrieval
     */
    public float[] coor;
    /**
     * the angle in which the object is drawn
     */
    public float angle;

    public Location() {
        coor = new float[2];
    }

}
