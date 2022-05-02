package com.martinmimiGames.util.graphics.opengl2.v4;

import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glUniform4fv;

import android.opengl.GLES20;

import com.martinmimiGames.util.graphics.opengl2.v4.glsl.Program;
import com.martinmimiGames.util.graphics.opengl2.v4.glsl.ShaderCode;
import com.martinmimiGames.util.graphics.opengl2.v4.glsl.VertexArray;

public class SolidShape extends Renderable{

  public float[] color;
  private final int positionLocation;
  private final int colorLocation;

  public SolidShape() {
    color = new float[4];
    if (Draw.vertexArray == null)
      Draw.vertexArray = new VertexArray();

    program = new Program()
        .addShaderProgram(GLES20.GL_VERTEX_SHADER, ShaderCode.Solid_VERTEX_SHADER)
        .addShaderProgram(GLES20.GL_FRAGMENT_SHADER, ShaderCode.SOLID_COLOR_FRAGMENT_SHADER)
        .complete();

    positionLocation = program.getAttributeLocation(ShaderCode.A_POSITION);
    colorLocation = program.getUniformLocation(ShaderCode.A_COLOR);
  }

  public void setColor(float red, float green, float blue, float alpha) {
    color[0] = red;
    color[1] = green;
    color[2] = blue;
    color[3] = alpha;
  }

  public void draw() {
    // Add program to OpenGL ES environment
    program.use();

    Draw.vertexArray.overwrite(vertex);
    Draw.vertexArray.setVertexAttribPointer(0, positionLocation, vertexPartCount, vertexStride);

    // Set color for drawing the triangle
    // Set color with red, green, blue and alpha (opacity) values
    glUniform4fv(colorLocation, 1, color, 0);

    // Draw the triangle
    glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, vertexCount);

    // Disable vertex array
    Draw.vertexArray.disableVertexAttribPointer(positionLocation);
  }
}
