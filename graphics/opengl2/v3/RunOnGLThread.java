package com.martinmimiGames.util.graphics.opengl2.v3;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glFlush;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * This is the MGGames utility dependency.
 * Store functions that can only run in GLThreads
 * @author martinmimi (from martinmimigames)
 * @since about 11-12-2021 dd-mm-yyyy
 * @version 1.0.0 first release
 */
class RunOnGLThread {

    private final Draw draw;

    RunOnGLThread(Draw draw) {
        this.draw = draw;
    }

    final ArrayList<Drawable> objects = new ArrayList<>(1000);

    /**
     * set background colour.
     * Can only run in GLThread
     *
     * @param red   colour red, value 0 - 255
     * @param green colour green, value 0 - 255
     * @param blue  colour blue, value 0 - 255
     * @param alpha value alpha, value 1 - 255
     */
    public void background(final float red, final float green, final float blue, final float alpha) {
        glClearColor(red / 255f, green / 255f, blue / 255f, alpha / 255f);
    }

    /**
     * draw the Drawables in query
     */
    public void draw() {
        //Drawable[] list = objects.toArray(objects.toArray(new Drawable[objects.size()]));
        /*ListIterator<Drawable> iterator;
            List<Drawable> list = new ArrayList(objects);
            iterator = list.listIterator(objects.size());
            objects.removeAll(list);

        Log.e("","size : " + objects.size());
        while (iterator.hasPrevious()){
                iterator.previous().draw(draw);
                iterator.remove();
        }*/
        Log.e("","size : " + objects.size());
        for (int i = 0;!objects.isEmpty(); i++) {
            Drawable object = objects.get(0);
            if (object != null)
                object.draw(draw);
            if (i % 75 == 0){
                glFlush();
            }
            objects.remove(0);
        }
        /*final Drawable[] drawables = objects.toArray(new Drawable[objects.size()]);
        objects = new ArrayList<>();
        for (Drawable drawable : drawables) {
            drawable.draw(draw);
        }*/

    }

    /**
     * Clear the rendering surface.
     */
    public void clear() {
        // Clear the rendering surface.
        glClear(GL_COLOR_BUFFER_BIT);
    }
}



