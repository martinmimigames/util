package com.martinmimiGames.util.graphics.opengl2.v4;

import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.glDrawArrays;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

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

public class Images implements Drawable {

  public static final String TAG = "Images";

  /**
   * how many position component are there.
   * update stride
   * default = 2
   */
  int positionComponentCount = 2;
  /**
   * how many coordinate component are there.
   * default = 2
   */
  int textureCoordinatesComponentCount = 2;
  /**
   * how many set of points are there.
   * a set = (position + coordinate)
   */
  public int points;
  /**
   * stride
   */
  public int stride;

  /**
   * type of image.
   * use value in TYPE
   */
  public int imageType = TYPE.NONE;

  /**
   * id of texture stored in openGL
   */
  public int textureId;

  /**
   * vertex data
   */
  public float[] vertex_data;

  /**
   * update stride value
   */
  public void updateStride() {
    stride = (positionComponentCount + textureCoordinatesComponentCount) * points;
  }

  @Override
  public void draw() {
    Draw.availablePrograms.textureProgram.useProgram();

    Draw.availablePrograms.textureProgram.setUniforms(Draw.projectionMatrix, textureId);

    try {
      Draw.vertexArray.overwrite(vertex_data);
    }catch (BufferOverflowException e) {
      Draw.vertexArray = new VertexArray(vertex_data);
    }catch (NullPointerException e){
      Draw.vertexArray = new VertexArray(vertex_data);
    }
    Draw.vertexArray.setVertexAttribPointer(
        0,
        Draw.availablePrograms.textureProgram.getPositionAttributeLocation(),
        positionComponentCount,
        stride);

    Draw.vertexArray.setVertexAttribPointer(
        positionComponentCount,
        Draw.availablePrograms.textureProgram.getTextureCoordinatesAttributeLocation(),
        textureCoordinatesComponentCount,
        stride);

    glDrawArrays(GL_TRIANGLE_STRIP, 0, points);
  }

  public static final class TYPE {
    public static final int NONE = 0;
    public static final int IMAGES = 1;
  }


  /**
   * @param context  the context
   * @param imageRId the id in R.java
   */
  public Images(final Context context, final int imageRId) {
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
    textureId = Parser.parseTexture(bitmap);
    imageType = TYPE.IMAGES;
  }

  public void deleteTexture(){
    Parser.deleteTexture(textureId);
  }
}
