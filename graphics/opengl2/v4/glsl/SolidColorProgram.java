package com.martinmimiGames.util.graphics.opengl2.v4.glsl;

import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUniformMatrix4fv;

/**
 * This is the MGGames utility dependency.
 * opengl program for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.0 first release
 * @since about 11-12-2021 dd-mm-yyyy
 */

public class SolidColorProgram extends Program {
  // Uniform locations
  private final int uMatrixLocation;

  // Attribute locations
  private final int aPositionLocation;
  private final int aColor;

  public SolidColorProgram() {
    super();
    addShaderProgram(GL_VERTEX_SHADER, ShaderCode.Solid_VERTEX_SHADER);
    addShaderProgram(GL_FRAGMENT_SHADER, ShaderCode.SOLID_COLOR_FRAGMENT_SHADER);
    complete();

    // Retrieve uniform locations for the shader program.
    uMatrixLocation = getUniformLocation(ShaderCode.U_MATRIX);

    // Retrieve attribute locations for the shader program.
    aPositionLocation = getAttributeLocation(ShaderCode.A_POSITION);
    aColor = getAttributeLocation(ShaderCode.A_COLOR);
  }

  public int getPositionAttributeLocation() {
    return aPositionLocation;
  }

  public int getColorLocation() {
    return aColor;
  }
}
