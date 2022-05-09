package com.martinmimiGames.util.graphics.opengl2.v4;

import com.martinmimiGames.util.graphics.opengl2.v4.glsl.Program;
import com.martinmimiGames.util.graphics.opengl2.v4.glsl.VertexArray;

/**
 * This is the MGGames utility dependency.
 * Framework for opengl graphic works
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.2 release
 * @since 09-03-2022 dd-mm-yyyy
 */
public abstract class Renderable implements Drawable{

  /**
   * A simple variable to help store the used program.
   */
  public Program program;

  /**
   *  number of coordinates per vertex in the vertex array.
   *  Default: 2 (x, y)
   *  */
  public int vertexPartCount = 2;
  /** The vertex array to store the vertex.*/
  public float[] vertex;
  /** The amount of vertex pair in the vertex array.*/
  public int vertexCount;
  /** The size of one float byte size.*/
  public static final int FLOAT_BYTE_SIZE = 4;
  /** The size of one vertex pair.*/
  public int vertexStride = vertexPartCount * FLOAT_BYTE_SIZE; // 4 bytes per vertex

  /**
   * Initiate class,
   * recommended to be called with super().
   */
  public Renderable(){
    if (Draw.vertexArray == null)
      Draw.vertexArray = new VertexArray();
  }

  /**
   * update vertex variable,
   * for vertex array change in structure.
   */
  public void updateStride(){
    vertexCount = vertex.length / vertexPartCount;
    vertexStride = vertexPartCount * FLOAT_BYTE_SIZE;
  }

  @Override
  public abstract void draw();
}
