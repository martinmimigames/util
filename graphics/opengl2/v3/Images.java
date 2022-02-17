package com.martinmimiGames.util.graphics.opengl2.v3;

import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.glDrawArrays;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.martinmimiGames.util.graphics.opengl2.v3.glsl.TextureShaderProgram;
import com.martinmimiGames.util.graphics.opengl2.v3.glsl.VertexArray;
import com.martinmimiGames.util.graphics.opengl2.v3.images.Parser;
import com.martinmimiGames.util.logger.Log;

/**
 * This is the MGGames utility dependency.
 * Images for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.1 release
 * @since 17-02-2022 dd-mm-yyyy
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

  private VertexArray vertexArray;

  /**
   * update stride value
   */
  public void updateStride() {
    stride = (positionComponentCount + textureCoordinatesComponentCount) * points;
  }

  public void updateVertexDataNewLength() {
    vertexArray = new VertexArray(vertex_data);
  }

  public void updateVertexData() {
    vertexArray.overwrite(vertex_data);
  }

  @Override
  public void draw(Draw draw) {
    //if (draw.availablePrograms.textureProgram == null)
    //  draw.availablePrograms.textureProgram = new TextureShaderProgram();

    draw.availablePrograms.textureProgram.useProgram();

    draw.availablePrograms.textureProgram.setUniforms(Draw.projectionMatrix, textureId);

    bindData(draw.availablePrograms.textureProgram, vertexArray);

    glDrawArrays(GL_TRIANGLE_STRIP, 0, points);
  }

  private void bindData(final TextureShaderProgram textureProgram, final VertexArray vertexArray) {
    vertexArray.setVertexAttribPointer(
        0, textureProgram.getPositionAttributeLocation(), positionComponentCount, stride);

    vertexArray.setVertexAttribPointer(
        positionComponentCount,
        textureProgram.getTextureCoordinatesAttributeLocation(),
        textureCoordinatesComponentCount,
        stride);
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
}
