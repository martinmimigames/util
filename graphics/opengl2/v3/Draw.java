package com.martinmimiGames.util.graphics.opengl2.v3;

import static android.opengl.GLES20.GL_BLEND;
import static android.opengl.GLES20.GL_ONE_MINUS_SRC_ALPHA;
import static android.opengl.GLES20.GL_SRC_ALPHA;
import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.glBlendFunc;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glViewport;
import static android.opengl.Matrix.multiplyMM;
import static android.opengl.Matrix.orthoM;
import static android.opengl.Matrix.rotateM;
import static android.opengl.Matrix.setIdentityM;
import static android.opengl.Matrix.translateM;

import android.app.Activity;

import com.martinmimiGames.util.graphics.opengl2.v3.glsl.TextureShaderProgram;
import com.martinmimiGames.util.graphics.opengl2.v3.glsl.VertexArray;
import com.martinmimiGames.util.objects.Rect;
import com.martinmimiGames.util.objects.RotatableRect;

import java.util.ArrayList;

public class Draw{

    private final Activity activity;
    private RunOnGLThread runOnGLThread;
    TextureShaderProgram textureProgram;
    public static float[] projectionMatrix;
    float ratio;
    // data storage
    static ArrayList<Integer> texture_data;
    static ArrayList<ArrayList<Float>> crop_data;
    static final int POSITION_COMPONENT_COUNT = 2;
    static final int TEXTURE_COORDINATES_COMPONENT_COUNT = 2;
    static final int STRIDE =
            (POSITION_COMPONENT_COUNT + TEXTURE_COORDINATES_COMPONENT_COUNT) * 4;

    /**
     * Do not use in Constructor
     * place in onSurfaceCreated instead
     * @param activity Activity with the screen
     */
    public Draw(Activity activity){
        // setting up variables
        this.activity = activity;
        runOnGLThread = new RunOnGLThread(this);
        projectionMatrix = new float[16];
        textureProgram = new TextureShaderProgram();

        // setting up storage
        texture_data = new ArrayList<>();
        crop_data = new ArrayList<>();

        // enable transparent texture
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    /**
     * setup the screen
     * place in onSurfaceChange
     * @param width width of screen
     * @param height height of screen
     */
    public void setScreen(int width, int height) {
        // Set the OpenGL viewport to fill the entire surface.
        ratio = 2 / (float) height;
        glViewport(0, 0, width, height);
        final float aspectRatio = (float) width / (float) (float) height;
        orthoM(projectionMatrix, 0, -aspectRatio, aspectRatio, -1, 1, -1, 1);
        translateM(projectionMatrix, 0, (float) -(((float) width) / (float) (float) height), 1, 0);
        float[] modelMatrix = new float[16];
        setIdentityM(modelMatrix, 0);
        final float[] temp = new float[16];
        multiplyMM(temp, 0, projectionMatrix, 0, modelMatrix, 0);
        System.arraycopy(temp, 0, projectionMatrix, 0, temp.length);
    }

    /**
     * load a texture and give an id for use
     * @param resourceId id from the res folder, given in R.java
     * @return id of texture
     */
    // load a texture (jpg, png, etc)
    public int loadTexture(int resourceId) {
        final int texture = new Images(activity, resourceId).parser.imageId;
        texture_data.add(texture);
        ArrayList<Float> data = new ArrayList<>();
        data.add(0f);
        data.add(1f);
        data.add(0f);
        data.add(1f);
        crop_data.add(data);

        // give a texture id
        return texture_data.size() - 1;
    }

    /**
     * change the texture to the newly given one
     * @param id id of texture given by "loadTexture"
     * @param resourceId id from the res folder, given in R.java
     */
    // load a texture (jpg, png, etc)
    public void changeTexture(int id, int resourceId) {
        final int texture = new Images(activity, resourceId).parser.imageId;
        texture_data.set(id, texture);
    }

    /**
     * crops a texture
     * @param textureId id of texture given by "loadTexture"
     * @param lrtb float list of left, right, top, bottom. 0f for 0% and 1f for 100%
     */
    // crops a texture
    public void crop(int textureId, float[] lrtb) {
        ArrayList<Float> data = new ArrayList<>();
        data.add(lrtb[0]);
        data.add(lrtb[1]);
        data.add(lrtb[2]);
        data.add(lrtb[3]);
        crop_data.set(textureId, data);
    }

    /**
     * Add object to draw query to be used in draw()
     */
    public void add(Location location, Rect objects, final int textureId) {
        runOnGLThread.objects.add(new Temporary(this,location, objects, textureId));
    }

    /**
     * set background colour.
     * Can only run in GLThread
     * @param red colour red, value 0 - 255
     * @param green colour green, value 0 - 255
     * @param blue colour blue, value 0 - 255
     * @param alpha value alpha, value 1 - 255
     */
    public void background(final float red, final float green, final float blue, final float alpha){
        glClearColor(red / 255f, green / 255f, blue / 255f, alpha / 255f);
    }

    /**
     * Clear the rendering surface.
     * Can only run in GLThread
     */
    public void clear(){
        runOnGLThread.clear();
    }

    /**
     * Draw queried objects.
     * Can only run in GLThread
     */
    public void draw(){
        runOnGLThread.draw();
    }

    /*public void draw(Rect objects, final int textureId) {

        // get the data from the storage
        float[]result = objects.getDataArray();
        ArrayList<Float> crop = crop_data.get(textureId);
        final int texture = texture_data.get(textureId);
        //final float angle = result[RotatableRect.ANGLE];
        final float angle = 0;

        // combine the data

        final float half_w = result[RotatableRect.HALF_WIDTH] * ratio;
        final float half_h = result[RotatableRect.HALF_HEIGHT] * ratio;

        final float[] vertex_data = {
                -half_w,
                -half_h,
                crop.get(0),
                crop.get(3),
                -half_w,
                half_h,
                crop.get(0),
                crop.get(2),
                half_w,
                -half_h,
                crop.get(1),
                crop.get(3),
                half_w,
                half_h,
                crop.get(1),
                crop.get(2)
        };

        // get the drawing location
        final float x = result[RotatableRect.X] * ratio;
        final float y = result[RotatableRect.Y] * ratio;

        // the drawing process
        translateM(projectionMatrix, 0, x, -y, 0f);
        rotateM(projectionMatrix, 0, -angle, 0f, 0f, 1f);

        drawTextureDefault(vertex_data, projectionMatrix, texture);

        rotateM(projectionMatrix, 0, angle, 0f, 0f, 1f);

        translateM(projectionMatrix, 0, -x, y, 0f);
        // the end of the drawing process
    }

    private void drawTextureDefault(final float[] vertex_data, final float[] projectionMatrix, final int texture){
        final VertexArray vertexArray = new VertexArray(vertex_data);

        textureProgram.useProgram();

        textureProgram.setUniforms(projectionMatrix, texture);

        bindData(textureProgram, vertexArray);

        glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
    }

    private void bindData(final TextureShaderProgram textureProgram, final VertexArray vertexArray) {
        vertexArray.setVertexAttribPointer(
                0, textureProgram.getPositionAttributeLocation(), POSITION_COMPONENT_COUNT, STRIDE);

        vertexArray.setVertexAttribPointer(
                POSITION_COMPONENT_COUNT,
                textureProgram.getTextureCoordinatesAttributeLocation(),
                TEXTURE_COORDINATES_COMPONENT_COUNT,
                STRIDE);
    }*/

}
