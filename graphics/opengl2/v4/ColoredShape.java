package com.martinmimiGames.util.graphics.opengl2.v4;

import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glUniform4fv;

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

public class ColoredShape implements Drawable {

  public static final String TAG = "Images";

  /**
   * how many position component are there.
   * update stride
   * default = 2
   */
  int positionComponentCount = 2;
  /**
   * how many set of points are there.
   * a set = (position + coordinate)
   */
  public int points = 3;
  /**
   * stride
   */
  public int stride;

  /**
   * vertex data
   */
  public float[] vertex_data;

  /**
   * update stride value
   */
  public void updateStride() {
    stride = (positionComponentCount) * points;
  }

  @Override
  public void draw() {
    Draw.availablePrograms.solidColorProgram.use();

    try {
      Draw.vertexArray.overwrite(vertex_data);
    }catch (BufferOverflowException e) {
      Draw.vertexArray = new VertexArray(vertex_data);
    }catch (NullPointerException e){
      Draw.vertexArray = new VertexArray(vertex_data);
    }
    Draw.vertexArray.setVertexAttribPointer(
        0,
        Draw.availablePrograms.solidColorProgram.getPositionAttributeLocation(),
        positionComponentCount,
        stride);

    glUniform4fv(
        Draw.availablePrograms.solidColorProgram.getColorLocation(),
        1,
        new float[]{0.7123879f,0,0, 1},
        0);

    glDrawArrays(GL_TRIANGLE_STRIP, 0, points);
  }
}
