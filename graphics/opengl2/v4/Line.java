package com.martinmimiGames.util.graphics.opengl2.v4;

import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glLineWidth;
import static android.opengl.GLES20.glUniform4fv;
import static android.opengl.GLES20.glUniformMatrix4fv;

import android.opengl.GLES20;

import com.martinmimiGames.util.graphics.opengl2.v4.glsl.ShaderCode;
import com.martinmimiGames.util.graphics.opengl2.v4.glsl.VertexArray;

public class Line extends Renderable{

  public float[] color;
  private final int positionLocation;
  private final int colorLocation;
  private float lineWidth;
  private final int matrixLocation;

  public Line() {
    super();
    color = new float[4];

    program = Draw.defaultPrograms.solidColorProgram;

    positionLocation = program.getAttributeLocation(ShaderCode.A_POSITION);
    colorLocation = program.getUniformLocation(ShaderCode.A_COLOR);
    lineWidth = 1;
    matrixLocation = program.getUniformLocation(ShaderCode.U_MATRIX);
  }

  public void setWidth(float width){
    lineWidth = width;
  }

  public void setColor(float red, float green, float blue, float alpha) {
    color[0] = red / 255f;
    color[1] = green / 255f;
    color[2] = blue / 255f;
    color[3] = alpha / 255f;
  }

  public void draw() {
    // Add program to OpenGL ES environment
    program.use();

    Draw.vertexArray.overwrite(vertex);
    Draw.vertexArray.setVertexAttribPointer(0, positionLocation, vertexPartCount, vertexStride);

    glUniformMatrix4fv(matrixLocation, 1, false, Draw.projectionMatrix, 0);
    // Set color for drawing the triangle
    // Set color with red, green, blue and alpha (opacity) values
    glUniform4fv(colorLocation, 1, color, 0);

    glLineWidth(lineWidth);
    // Draw the triangle
    glDrawArrays(GLES20.GL_LINES, 0, vertexCount);

    // Disable vertex array
    Draw.vertexArray.disableVertexAttribPointer(positionLocation);
  }
}
