package com.martinmimiGames.util.graphics.opengl2.v4.helper;

/**
 * This is the MGGames utility dependency.
 * Vertex in Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.2 release
 * @since 09-03-2022 dd-mm-yyyy
 */

public class Vertex {
  public static class Rectangle {
    public static float[] setWidth(float[] vertex, float width) {
      float halfWidth = width / 2f;
      vertex[0] = vertex[2] = -halfWidth;
      vertex[4] = vertex[6] = halfWidth;
      return vertex;
    }

    public static float[] setHeight(float[] vertex, float height) {
      float halfHeight = height / 2f;
      vertex[1] = vertex[5] = halfHeight;
      vertex[3] = vertex[7] = -halfHeight;
      return vertex;
    }
  }

  public static class Image {
    public static float[] setWidth(float[] vertex, float width) {
      float halfWidth = width / 2f;
      vertex[0] = vertex[4] = -halfWidth;
      vertex[8] = vertex[12] = halfWidth;
      return vertex;
    }

    public static float[] setHeight(float[] vertex, float height) {
      float halfHeight = height / 2f;
      vertex[1] = vertex[9] = halfHeight;
      vertex[5] = vertex[13] = -halfHeight;
      return vertex;
    }

    public static float[] setCrop(float[] vertex, float left, float right, float top, float bottom) {
      vertex[2] = vertex[6] = left;
      vertex[3] = vertex[11] = top;
      vertex[10] = vertex[14] = right;
      vertex[7] = vertex[15] = bottom;
      return vertex;
    }
  }
}
