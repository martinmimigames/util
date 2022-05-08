package com.martinmimiGames.util.graphics.opengl2.v4;

import com.martinmimiGames.util.graphics.opengl2.v4.glsl.Program;
import com.martinmimiGames.util.graphics.opengl2.v4.glsl.VertexArray;

public class Renderable implements Drawable{

  protected Program program;

  // number of coordinates per vertex in this array
  public int vertexPartCount = 2;
  public float[] vertex;
  public int vertexCount;
  public static final int FLOAT_BYTE_SIZE = 4;
  public int vertexStride = vertexPartCount * FLOAT_BYTE_SIZE; // 4 bytes per vertex

  public Renderable(){
    if (Draw.vertexArray == null)
      Draw.vertexArray = new VertexArray();
  }

  public void updateStride(){
    vertexCount= vertex.length / vertexPartCount;
    vertexStride = vertexPartCount * FLOAT_BYTE_SIZE;
  }

  @Override
  public void draw() {}
}
