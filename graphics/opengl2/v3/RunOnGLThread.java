package com.martinmimiGames.util.graphics.opengl2.v3;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;

import java.util.ArrayList;

/**
 * functions that can only run in GLThreads
 */
class RunOnGLThread {

    private final Draw draw;

    RunOnGLThread(Draw draw){
        this.draw = draw;
    }

    ArrayList<Drawable> objects = new ArrayList<>();

    public void draw(){
        while (objects.size() > 0) {
            objects.get(0).draw(draw);
            objects.remove(0);
        }
    }

    /**
     * Clear the rendering surface.
     */
    public void clear(){
        // Clear the rendering surface.
        glClear(GL_COLOR_BUFFER_BIT);
    }
}



