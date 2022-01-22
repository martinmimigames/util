package com.martinmimiGames.util.helper.button;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

import com.martinmimiGames.util.graphics.opengl2.v3.Draw;
import com.martinmimiGames.util.graphics.opengl2.v3.Drawable;
import com.martinmimiGames.util.graphics.opengl2.v3.Object;

import com.martinmimiGames.util.control.v2.Button;

public class Rectangle extends Button implements Drawable {

  public Button button;
  public Object object;

  public Rectangle(){
    button = new Button();
    object = new Object();
  }
  public Rectangle setX(float x){
    button.location.x = x;
    object.location.coor[com.martinmimiGames.util.graphics.opengl2.v3.Location.X] = x;
    return this;
  }

  public Rectangle setY(float y){
    button.location.y = y;
    object.location.coor[com.martinmimiGames.util.graphics.opengl2.v3.Location.Y] = y;
    return this;
  }

  public Rectangle setWidth(float width){
    button.setWidth(width);
    try {
      ((com.martinmimiGames.util.graphics.opengl2.v3.images.shapes.Rectangle) object.drawable).setWidth(width);
    } catch (NullPointerException e){
      throw new RuntimeException("IMAGE NOT SET\n" + e.toString(), e.getCause());
    }
    return this;
  }

  public Rectangle setHeight(float height){
    button.setHeight(height);
    try {
      ((com.martinmimiGames.util.graphics.opengl2.v3.images.shapes.Rectangle) object.drawable).setHeight(height);
    } catch (NullPointerException e){
      throw new RuntimeException("IMAGE NOT SET\n" + e.toString(), e.getCause());
    }
    return this;
  }

  public Rectangle setImage(Draw draw, Context context, int Rid){
    object.drawable = new com.martinmimiGames.util.graphics.opengl2.v3.images.shapes.Rectangle(draw, context, Rid);
    return this;
  }

  public Rectangle setImage(Draw draw, Bitmap bitmap){
    object.drawable = new com.martinmimiGames.util.graphics.opengl2.v3.images.shapes.Rectangle(draw, bitmap);
    return this;
  }

  public Rectangle setImage(Rectangle rectangle){
    object.drawable = rectangle;
    return this;
  }

  public Rectangle setImageCrop(final float left,
                                final float right,
                                final float top,
                                final float bottom){
    ((Rectangle)object.drawable).setImageCrop(left, right, top, bottom);
    return this;
  }

  @Override
  public boolean check(MotionEvent event, int index) {
    return button.check(event, index);
  }

  @Override
  public boolean check(float x, float y) {
    return button.check(x, y);
  }

  @Override
  public void draw(Draw draw) {
    object.draw(draw);
  }

}
