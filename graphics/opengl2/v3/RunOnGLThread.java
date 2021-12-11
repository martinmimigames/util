package com.martinmimiGames.util.graphics.opengl2.v3;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;

import java.util.ArrayList;

/**
 * functions that can only run in GLThreads
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



