package com.martinmimiGames.util.control.v2;

import android.view.MotionEvent;

public class Button {

  private final float[] buttonEdges;
  private static final int LEFT = 0;
  private static final int RIGHT = 1;
  private static final int TOP = 2;
  private static final int BOTTOM = 3;

  public Location location;

  public Button(){
    buttonEdges = new float[4];
    location = new Location();
  }

  public Button(float x, float y, float width, float height){
    buttonEdges = new float[4];
    location = new Location();
    location.x = x;
    location.y = y;
    setWidth(width);
    setHeight(height);
  }

  public class Location{
    public float x;
    public float y;
  }

  public Button setWidth(float width){
    buttonEdges[LEFT] = location.x - width / 2f;
    buttonEdges[RIGHT] = location.x + width / 2f;
    return this;
  }

  public Button setHeight(float height){
    buttonEdges[TOP] = location.y + height / 2f;
    buttonEdges[BOTTOM] = location.y - height / 2f;
    return this;
  }

  public boolean check(MotionEvent event, int index){
    return check(event.getX(index), event.getY(index));
  }

  public boolean check(float x, float y){
    return x >= buttonEdges[LEFT]
        && y <= buttonEdges[TOP]
        && x <= buttonEdges[RIGHT]
        && y >= buttonEdges[BOTTOM];
  }
}
