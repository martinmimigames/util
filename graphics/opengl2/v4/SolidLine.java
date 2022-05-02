package com.martinmimiGames.util.graphics.opengl2.v4;

import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glLineWidth;
import static android.opengl.GLES20.glUniform4fv;

import android.opengl.GLES20;

import com.martinmimiGames.util.graphics.opengl2.v4.glsl.Program;
import com.martinmimiGames.util.graphics.opengl2.v4.glsl.ShaderCode;
import com.martinmimiGames.util.graphics.opengl2.v4.glsl.VertexArray;

public class SolidLine extends Renderable{

  public float[] color;
  private final int positionLocation;
  private final int colorLocation;
  private float lineWidth;

  public SolidLine() {
    color = new float[4];
    if (Draw.vertexArray == null)
      Draw.vertexArray = new VertexArray();

    program = new Program()
        .addShaderProgram(GLES20.GL_VERTEX_SHADER, ShaderCode.Solid_VERTEX_SHADER)
        .addShaderProgram(GLES20.GL_FRAGMENT_SHADER, ShaderCode.SOLID_COLOR_FRAGMENT_SHADER)
        .complete();

    positionLocation = program.getAttributeLocation(ShaderCode.A_POSITION);
    colorLocation = program.getUniformLocation(ShaderCode.A_COLOR);
    lineWidth = 10 / Draw.height;
  }

  public void setWidth(float width){
    lineWidth = width / Draw.height;
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
