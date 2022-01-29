package com.martinmimiGames.util.example;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Activity extends android.app.Activity {

  private GLSurfaceView v;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    v = new GLSurfaceView(this);
    v.setEGLContextClientVersion(2);
    v.setRenderer(new Renderer(this));
    setContentView(v);
  }

  @Override
  protected void onStart(){
    super.onStart();
    v.onResume();
  }

  @Override
  protected void onPause(){
    super.onPause();
    v.onPause();
  }
}
