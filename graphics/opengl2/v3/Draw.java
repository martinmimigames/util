package com.martinmimiGames.util.graphics.opengl2.v3;

import static android.opengl.GLES20.GL_BLEND;
import static android.opengl.GLES20.GL_ONE_MINUS_SRC_ALPHA;
import static android.opengl.GLES20.GL_SRC_ALPHA;
import static android.opengl.GLES20.glBlendFunc;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glViewport;
import static android.opengl.Matrix.multiplyMM;
import static android.opengl.Matrix.orthoM;
import static android.opengl.Matrix.setIdentityM;
import static android.opengl.Matrix.translateM;

import android.app.Activity;

import com.martinmimiGames.util.graphics.opengl2.v3.glsl.AvailablePrograms;

/**
 * This is the MGGames utility dependency.
 * Draw dependency for opengl graphic works
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.0 first release
 * @since about 11-12-2021 dd-mm-yyyy
 */

public class Draw {

    private final RunOnGLThread runOnGLThread;
    public static float[] projectionMatrix;
    float ratio;
    public Integer height;
    AvailablePrograms availablePrograms;
    private volatile boolean drawFinished;


    /**
     * Do not use in Constructor
     * place in onSurfaceCreated instead
     *
     * @param activity Activity with the screen
     */
    public Draw(Activity activity) {
        // setting up variables
        height = activity.getWindow().getDecorView().getBottom();
        runOnGLThread = new RunOnGLThread(this);
        projectionMatrix = new float[16];
        availablePrograms = new AvailablePrograms();
        // enable transparent texture
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    /**
     * setup the screen
     * place in onSurfaceChange
     *
     * @param width  width of screen
     * @param height height of screen
     */
    public void setScreen(int width, int height) {
        // Set the OpenGL viewport to fill the entire surface.
        ratio = 2 / (float) height;
        this.height = height;
        glViewport(0, 0, width, height);
        final float aspectRatio = (float) width / (float) height;
        orthoM(projectionMatrix, 0, -aspectRatio, aspectRatio, -1, 1, -1, 1);
        translateM(projectionMatrix, 0, -(((float) width) / (float) height), 1, 0);
        float[] modelMatrix = new float[16];
        setIdentityM(modelMatrix, 0);
        final float[] temp = new float[16];
        multiplyMM(temp, 0, projectionMatrix, 0, modelMatrix, 0);
        System.arraycopy(temp, 0, projectionMatrix, 0, temp.length);
    }

    /**
     * Add object to draw query to be used in draw()
     */
    public void addLocationIndependent(Object object) {
        Object new_object = new Object();
        try {
            new_object.location = (Location) object.location.clone();
        } catch (CloneNotSupportedException ignored) {
        }
        new_object.drawable = object.drawable;
        runOnGLThread.objects.add(new_object);
    }

    /**
     * Add object to draw query to be used in draw()
     */
    public void add(Object object) {
        runOnGLThread.objects.add(object);
    }

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
        runOnGLThread.background(red, green, blue, alpha);
    }

    /**
     * Clear the rendering surface.
     * Can only run in GLThread
     */
    public void clean() {
        runOnGLThread.clear();
    }

    public void clear() {
        runOnGLThread.objects.clear();
    }

    /**
     * Draw queried objects.
     * Can only run in GLThread
     */
    public void draw() {
        runOnGLThread.draw();
    }

    /**
     * call after draw frame
     */
    public void nextBuffer() {
        drawFinished = true;
    }

    /**
     * call to wait until next buffer
     */
    public void waitForNextBuffer() {
        while (!drawFinished) ;
        drawFinished = false;
    }
}
