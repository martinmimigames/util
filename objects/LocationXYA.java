package com.martinmimiGames.util.objects;

/**
 * This is the MGGames utility dependency.
 * LocationXYA for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.0 first release
 * @since about 11-12-2021 dd-mm-yyyy
 */

public class LocationXYA implements Cloneable {

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

  /*public LocationXYA() {
    coor = new float[2];
  }*/

  @Override
  public java.lang.Object clone() throws CloneNotSupportedException {
    LocationXYA location = new LocationXYA();
    //location.coor[LocationXYA.X] = this.coor[LocationXYA.X];
    //location.coor[LocationXYA.Y] = this.coor[LocationXYA.Y];
    location.x = this.x;
    location.y = this.y;
    location.angle = this.angle;
    return location;
  }
}
