package com.martinmimiGames.util.graphics.opengl2.v4;

import static android.opengl.GLES20.GL_LINEAR_MIPMAP_LINEAR;
import static android.opengl.GLES20.GL_NEAREST;
import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.GL_TEXTURE_MAG_FILTER;
import static android.opengl.GLES20.GL_TEXTURE_MIN_FILTER;
import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glDeleteTextures;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glGenTextures;
import static android.opengl.GLES20.glGenerateMipmap;
import static android.opengl.GLES20.glTexParameteri;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static android.opengl.GLUtils.texImage2D;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import com.martinmimiGames.util.graphics.opengl2.v4.glsl.ShaderCode;
import com.martinmimiGames.util.graphics.opengl2.v4.helper.Vertex;
import com.martinmimiGames.util.logger.Log;

/**
 * This is the MGGames utility dependency.
 * Images for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.2 release
 * @since 09-03-2022 dd-mm-yyyy
 */

public class Images extends Renderable {

  public static final String TAG = "Images";

  /**
   * how many coordinate component are there.
   * default = 2
   */
  int texturePartCount = 2;

  /**
   * id of texture stored in openGL
   */
  public int textureId;

  private final int positionLocation;
  private final int textureUnitLocation;
  private final int texturePositionLocation;
  private final int matrixLocation;

  /**
   * @param context  the context
   * @param imageRId the id in R.java
   */
  public Images(final Context context, final int imageRId) {
    this();

    final BitmapFactory.Options options = new BitmapFactory.Options();
    options.inScaled = false;
    if (Build.VERSION.SDK_INT >= 10)
      options.inPreferQualityOverSpeed = true;

    // Read in the resource
    final Bitmap bitmap =
        BitmapFactory.decodeResource(context.getResources(), imageRId, options);

    if (bitmap == null) {
      if (Log.ON)
        Log.w(TAG, "Resource ID " + imageRId + " could not be decoded.");
      return;
    }

    textureId = parseTexture(bitmap);

    bitmap.recycle();
  }

  /**
   * @param bitmap the bitmap for the image
   */
  public Images(final Bitmap bitmap) {
    this();
    textureId = parseTexture(bitmap);
  }

  private Images(){
    super();
    vertexCount = 4;

    program = Draw.defaultPrograms.textureProgram;

    positionLocation = program.getAttributeLocation(ShaderCode.A_POSITION);
    textureUnitLocation = program.getUniformLocation(ShaderCode.U_TEXTURE_UNIT);
    texturePositionLocation = program.getAttributeLocation(ShaderCode.A_TEXTURE_COORDINATES);
    matrixLocation = program.getUniformLocation(ShaderCode.U_MATRIX);
    vertex = new float[16];
    Vertex.Image.setWidth(vertex, 1);
    Vertex.Image.setHeight(vertex, 1);
    Vertex.Image.setCrop(vertex, 0, 1, 0, 1);
    updateStride();
  }

  /**
   * update stride value
   */
  @Override
  public void updateStride() {
    vertexStride = (vertexPartCount + texturePartCount) * vertexCount;
  }

  @Override
  public void draw() {
    //Draw.defaultPrograms.textureProgram.use();
    program.use();

    // Pass the matrix into the shader program.
    glUniformMatrix4fv(matrixLocation, 1, false, Draw.projectionMatrix, 0);

    // Set the active texture unit to texture unit 0.
    glActiveTexture(GL_TEXTURE0);

    // Bind the texture to this unit.
    glBindTexture(GL_TEXTURE_2D, textureId);

    // Tell the texture uniform sampler to use this texture in the shader by
    // telling it to read from texture unit 0.
    glUniform1i(textureUnitLocation, 0);

    Draw.vertexArray.overwrite(vertex);

    Draw.vertexArray.setVertexAttribPointer(
        0,
        positionLocation,
        vertexPartCount,
        vertexStride);

    Draw.vertexArray.setVertexAttribPointer(
        vertexPartCount,
        texturePositionLocation,
        texturePartCount,
        vertexStride);

    glDrawArrays(GL_TRIANGLE_STRIP, 0, vertexCount);

    Draw.vertexArray.disableVertexAttribPointer(positionLocation);
    Draw.vertexArray.disableVertexAttribPointer(texturePositionLocation);
  }

  /**
   * put texture data into opengl,
   * and return texture id
   *
   * @param bitmap image
   * @return texture id
   */
  public static int parseTexture(Bitmap bitmap) {
    final int[] textureObjectIds = new int[1];
    glGenTextures(1, textureObjectIds, 0);

    if (textureObjectIds[0] == 0) {
      if (Log.ON) Log.w(TAG, "Could not generate a new OpenGL texture object.");
      return 0;
    }
    // Bind to the texture in OpenGL
    glBindTexture(GL_TEXTURE_2D, textureObjectIds[0]);

    // Set filtering: a default must be set, or the texture will be
    // black.
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

    // Load the bitmap into the bound texture.
    texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);

    // Note: Following code may cause an error to be reported in the
    // ADB log as follows: E/IMGSRV(20095): :0: HardwareMipGen:
    // Failed to generate texture mipmap levels (error=3)
    // No OpenGL error will be encountered (glGetError() will return
    // 0). If this happens, just squash the source image to be
    // square. It will look the same because of texture coordinates,
    // and mipmap generation will work.
    glGenerateMipmap(GL_TEXTURE_2D);

    // Recycle the bitmap, since its data has been loaded into
    // OpenGL.
    bitmap.recycle();

    // Unbind from the texture.
    glBindTexture(GL_TEXTURE_2D, 0);

    return textureObjectIds[0];
  }

  /**
   * delete the texture of the given id,
   * in order to free up memory
   * @param textureId the id of the texture to be deleted
   */
  public static void deleteTexture(int textureId){
    glDeleteTextures(1, new int[]{textureId}, 0);
  }
}
