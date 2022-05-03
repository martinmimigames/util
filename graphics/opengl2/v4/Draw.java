package com.martinmimiGames.util.graphics.opengl2.v4;

import static android.opengl.GLES20.GL_BLEND;
import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_CULL_FACE;
import static android.opengl.GLES20.GL_CW;
import static android.opengl.GLES20.GL_FRONT;
import static android.opengl.GLES20.GL_ONE_MINUS_SRC_ALPHA;
import static android.opengl.GLES20.GL_SRC_ALPHA;
import static android.opengl.GLES20.glBlendFunc;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glCullFace;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glFrontFace;
import static android.opengl.GLES20.glViewport;
import static android.opengl.Matrix.orthoM;
import static android.opengl.Matrix.translateM;

import android.app.Activity;
import android.util.Log;

import com.martinmimiGames.util.graphics.opengl2.v4.glsl.AvailablePrograms;
import com.martinmimiGames.util.graphics.opengl2.v4.glsl.VertexArray;

/**
 * This is the MGGames utility dependency.
 * Draw dependency for opengl graphic works
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.2 release
 * @since 09-03-2022 dd-mm-yyyy
 */

public class Draw {

  /** aspect ratio type: width and height always -1f - 1f*/
  public static final int ASPECT_RATIO_NO_FOLLOW = 1;
  /** aspect ratio type: width always -1f - 1f*/
  public static final int ASPECT_RATIO_FOLLOW_WIDTH = 3;
   /** aspect ratio type: height always -1f - 1f*/
  public static final int ASPECT_RATIO_FOLLOW_HEIGHT = 2;

  public static float[] projectionMatrix = new float[16];
  public static float ratio;
  public static Integer height = 1080; //default size
  static AvailablePrograms availablePrograms = new AvailablePrograms();
  public static VertexArray vertexArray;

  public static void init(){
    projectionMatrix = new float[16];
    availablePrograms = new AvailablePrograms();
    // enable transparent texture
    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    // enable cull face
    /*glEnable(GL_CULL_FACE);
    glCullFace(GL_FRONT);
    glFrontFace(GL_CW);*/
  }

  public static void init(Activity activity) {
    height = activity.getWindow().getDecorView().getBottom();
    init();
  }

  /**
   * Setup the screen.
   * Place in onSurfaceChange.
   * @param width  width of screen
   * @param height height of screen
   */
  public static void setScreen(int width, int height, int aspectRatioType) {
    // Set the OpenGL viewport to fill the entire surface.
    glViewport(0, 0, width, height);
    // set aspect ratio
    float aspectRatio;
    switch (aspectRatioType){
      case 1:
        orthoM(projectionMatrix, 0, -1, 1, -1, 1, -1, 1);
        break;
      case 2:
        aspectRatio = (float) width / (float) height;
        orthoM(projectionMatrix, 0, -aspectRatio, aspectRatio, -1, 1, -1, 1);
        break;
      case 3:
        aspectRatio = height / (float) width;
        orthoM(projectionMatrix, 0, -1,1,-aspectRatio, aspectRatio,  -1, 1);
        break;
      default:
        throw new RuntimeException("not such aspect ratio type: " + aspectRatioType);
    }
    // set variables
    Draw.height = height;
    ratio = 2 / (float) height;
  }

  /**
   * Set background colour.
   * Can only run in GLThread.
   * @param red   colour red, value 0 - 255
   * @param green colour green, value 0 - 255
   * @param blue  colour blue, value 0 - 255
   * @param alpha value alpha, value 1 - 255
   */
  public static void background(final float red, final float green, final float blue, final float alpha) {
    glClearColor(red / 255f, green / 255f, blue / 255f, alpha / 255f);
  }

  /**
   * Clear the rendering surface.
   * Can only run in GLThread
   */
  public static void clean() {
    glClear(GL_COLOR_BUFFER_BIT);
  }
}
