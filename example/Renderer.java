package com.martinmimiGames.util.example;

import android.app.Activity;
import android.opengl.GLSurfaceView;

import com.martinmimiGames.util.graphics.opengl2.v3.Draw;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Renderer implements GLSurfaceView.Renderer {
  private final Activity activity;
  public Draw draw;

  // setting variables

  public Renderer(Activity activity) {
    this.activity = activity;
  }

  @Override
  public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
    draw = new Draw(activity);
    // base color
    // not necessary
    draw.background(0, 0, 0, 255);
  }

  @Override
  public void onSurfaceChanged(GL10 glUnused, int width, int height) {
    draw.setScreen(width, height);
    // setting variables that
    // requires screen size info
  }

  // everything inside will be done again and again
  @Override
  public void onDrawFrame(GL10 glUnused) {
    // start of loop
    // Clear the rendering surface.
    draw.clean();
    draw.draw();
    // end of loop
  }
}
