package com.martinmimiGames.util.graphics.opengl2.v3;

import static android.opengl.Matrix.rotateM;
import static android.opengl.Matrix.translateM;

import android.content.Context;

import com.martinmimiGames.util.graphics.opengl2.v3.images.shapes.Rectangle;

public class Object implements Drawable {

    public Location location;
    public Drawable drawable;

    public Object() {
        location = new Location();
    }

    public static Object Images(final Draw draw, final Context context, final int Rid) {
        final Object object = new Object();
        object.drawable = new Rectangle(draw, context, Rid);
        return object;
    }

    @Override
    public void draw(Draw draw) {

        final float x = location.coor[Location.X] * draw.ratio;
        final float y = location.coor[Location.Y] * draw.ratio;

        // the drawing process
        translateM(Draw.projectionMatrix, 0, x, -y, 0f);
        rotateM(Draw.projectionMatrix, 0, -location.angle, 0f, 0f, 1f);

        drawable.draw(draw);

        rotateM(Draw.projectionMatrix, 0, location.angle, 0f, 0f, 1f);

        translateM(Draw.projectionMatrix, 0, -x, y, 0f);
        // the end of the drawing process
    }

}
