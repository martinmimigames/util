package com.martinmimiGames.util.graphics.opengl2.v3;

import android.content.Context;

public class Object {

    public Location location;
    public Drawable drawable;

  public Object(){
    location = new Location();
  }

  public static Object Images(final Context context, final int Rid){
    final Object object = new Object();
    object.drawable = new Images(context, Rid);
    return object;
  }

}
