package com.martinmimiGames.util.graphics.opengl2.v3;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;

import java.util.ArrayList;

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

    ArrayList<Drawable> objects = new ArrayList<>();

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
        while (objects.size() > 0) {
            objects.get(0).draw(draw);
            objects.remove(0);
        }
    }

    /**
     * Clear the rendering surface.
     */
    public void clear() {
        // Clear the rendering surface.
        glClear(GL_COLOR_BUFFER_BIT);
    }
}



