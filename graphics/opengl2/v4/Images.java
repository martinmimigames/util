package com.martinmimiGames.util.graphics.opengl2.v4;

import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUniformMatrix4fv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import com.martinmimiGames.util.graphics.opengl2.v4.glsl.ShaderCode;
import com.martinmimiGames.util.graphics.opengl2.v4.glsl.VertexArray;
import com.martinmimiGames.util.graphics.opengl2.v4.images.Parser;
import com.martinmimiGames.util.logger.Log;

import java.nio.BufferOverflowException;

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
   * type of image.
   * use value in TYPE
   */
  public int imageType = TYPE.NONE;

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

    if (Draw.vertexArray == null)
      Draw.vertexArray = new VertexArray();

    program = Draw.defaultPrograms.textureProgram;

    positionLocation = program.getAttributeLocation(ShaderCode.A_POSITION);
    textureUnitLocation = program.getUniformLocation(ShaderCode.U_TEXTURE_UNIT);
    texturePositionLocation = program.getAttributeLocation(ShaderCode.A_TEXTURE_COORDINATES);
    matrixLocation = program.getUniformLocation(ShaderCode.U_MATRIX);

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
    textureId = Parser.parseTexture(bitmap);
    imageType = TYPE.IMAGES;
    bitmap.recycle();
  }

  /**
   * @param bitmap the bitmap for the image
   */
  public Images(final Bitmap bitmap) {

    if (Draw.vertexArray == null)
      Draw.vertexArray = new VertexArray();

    program = Draw.defaultPrograms.textureProgram;

    positionLocation = program.getAttributeLocation(ShaderCode.A_POSITION);
    textureUnitLocation = program.getUniformLocation(ShaderCode.U_TEXTURE_UNIT);
    texturePositionLocation = program.getAttributeLocation(ShaderCode.A_TEXTURE_COORDINATES);
    matrixLocation = program.getUniformLocation(ShaderCode.U_MATRIX);

    textureId = Parser.parseTexture(bitmap);
    imageType = TYPE.IMAGES;
  }
  /**
   * update stride value
   */
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

    try {
      Draw.vertexArray.overwrite(vertex);
    }catch (BufferOverflowException e) {
      Draw.vertexArray = new VertexArray(vertex);
    }catch (NullPointerException e){
      Draw.vertexArray = new VertexArray(vertex);
    }
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

  public static final class TYPE {
    public static final int NONE = 0;
    public static final int IMAGES = 1;
  }




  public void deleteTexture(){
    Parser.deleteTexture(textureId);
  }
}
