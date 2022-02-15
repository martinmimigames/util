package com.martinmimiGames.util.graphics.opengl2.v3;

/**
 * This is the MGGames utility dependency.
 * Location for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.0 first release
 * @since about 11-12-2021 dd-mm-yyyy
 */

public class Location implements Cloneable {

  //public static final int X = 0;
  //public static final int Y = 1;

  /*/**
   * coordinates,
   * can use X,Y values for retrieval
   */
  //public float[] coor;
  public float x;
  public float y;
  /**
   * the angle in which the object is drawn
   */
  public float angle;

  /*public Location() {
    coor = new float[2];
  }*/

  @Override
  protected java.lang.Object clone() throws CloneNotSupportedException {
    Location location = new Location();
    //location.coor[Location.X] = this.coor[Location.X];
    //location.coor[Location.Y] = this.coor[Location.Y];
    location.x = this.x;
    location.y = this.y;
    location.angle = this.angle;
    return location;
  }
}
