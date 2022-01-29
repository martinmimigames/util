package com.martinmimiGames.util.control.v2;

import static com.martinmimiGames.javacar4.main_screen.Renderer.height;
import static com.martinmimiGames.javacar4.main_screen.Renderer.width;

import android.view.MotionEvent;

public class Button {

  private float half_w = width / 2f;
  private float half_h = height / 2f;

  public Location location;

  public Button() {
    location = new Location();
  }

  public Button(float x, float y, float width, float height) {
    location = new Location();
    location.x = x;
    location.y = y;
    setWidth(width);
    setHeight(height);
  }

  public class Location {
    public float x;
    public float y;
  }

  public Button setWidth(float width) {
    half_w = width / 2f;
    return this;
  }

  public Button setHeight(float height) {
    half_h = height / 2f;
    return this;
  }

  public boolean check(MotionEvent event, int index) {
    return check(event.getX(index), event.getY(index));
  }

  public boolean check(float x, float y) {
    return x >= location.x - half_w
        && y <= location.y + half_h
        && x <= location.x + half_w
        && y >= location.y - half_h;
  }
}
