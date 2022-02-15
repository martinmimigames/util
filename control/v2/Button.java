package com.martinmimiGames.util.control.v2;

import android.view.MotionEvent;

import com.martinmimiGames.util.objects.LocationXY;

public class Button {

  private float half_w;
  private float half_h;

  public LocationXY location;

  public Button() {
    location = new LocationXY();
  }

  public Button(float x, float y, float width, float height) {
    location = new LocationXY();
    location.x = x;
    location.y = y;
    setWidth(width);
    setHeight(height);
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
