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

public class TextureProgram extends Program {
  // Uniform locations
  private final int uMatrixLocation;
  private final int uTextureUnitLocation;

  // Attribute locations
  private final int aPositionLocation;
  private final int aTextureCoordinatesLocation;

  public TextureProgram() {
    super();
    addShaderProgram(GL_VERTEX_SHADER, ShaderCode.VERTEX_SHADER);
    addShaderProgram(GL_FRAGMENT_SHADER, ShaderCode.TEXTURE_FRAGMENT_SHADER);
    complete();

    // Retrieve uniform locations for the shader program.
    uMatrixLocation = getUniformLocation(ShaderCode.U_MATRIX);
    uTextureUnitLocation = getUniformLocation(ShaderCode.U_TEXTURE_UNIT);

    // Retrieve attribute locations for the shader program.
    aPositionLocation = getAttributeLocation(ShaderCode.A_POSITION);
    aTextureCoordinatesLocation = getAttributeLocation(ShaderCode.A_TEXTURE_COORDINATES);
  }

  public void setUniforms(final float[] matrix,final int textureId) {
    // Pass the matrix into the shader program.
    glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);

    // Set the active texture unit to texture unit 0.
    glActiveTexture(GL_TEXTURE0);

    // Bind the texture to this unit.
    glBindTexture(GL_TEXTURE_2D, textureId);

    // Tell the texture uniform sampler to use this texture in the shader by
    // telling it to read from texture unit 0.
    glUniform1i(uTextureUnitLocation, 0);
  }

  public int getPositionAttributeLocation() {
    return aPositionLocation;
  }

  public int getTextureCoordinatesAttributeLocation() {
    return aTextureCoordinatesLocation;
  }
}
