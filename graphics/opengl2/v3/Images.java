package com.martinmimiGames.util.graphics.opengl2.v3;

import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.Matrix.rotateM;
import static android.opengl.Matrix.translateM;
import static com.martinmimiGames.util.logger.Log.Log;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.martinmimiGames.util.graphics.opengl2.v3.glsl.TextureShaderProgram;
import com.martinmimiGames.util.graphics.opengl2.v3.glsl.VertexArray;
import com.martinmimiGames.util.graphics.opengl2.v3.images.Parser;
import com.martinmimiGames.util.graphics.opengl2.v3.images.shapes.Rectangle;

public class Images implements Drawable{

    public static final String TAG = "Parser";

    int positionComponentCount = 2;
    int textureCoordinatesComponentCount = 2;
    public int points;
    public int stride;

    public float[] vertex_data;

    public void updateStride(){
        stride = (positionComponentCount + textureCoordinatesComponentCount) * points;
    }

    @Override
    public void draw(Draw draw) {
        drawTextureDefault(vertex_data, draw.projectionMatrix, parser.imageId, draw);
    }

    private void drawTextureDefault(final float[] vertex_data, final float[] projectionMatrix, final int texture, final Draw draw){
        final VertexArray vertexArray = new VertexArray(vertex_data);

        draw.textureProgram.useProgram();

        draw.textureProgram.setUniforms(projectionMatrix, texture);

        bindData(draw.textureProgram, vertexArray, draw);

        glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
    }

    private void bindData(final TextureShaderProgram textureProgram, final VertexArray vertexArray, final Draw draw) {
        vertexArray.setVertexAttribPointer(
                0, textureProgram.getPositionAttributeLocation(), draw.POSITION_COMPONENT_COUNT, draw.STRIDE);

        vertexArray.setVertexAttribPointer(
                draw.POSITION_COMPONENT_COUNT,
                textureProgram.getTextureCoordinatesAttributeLocation(),
                draw.TEXTURE_COORDINATES_COMPONENT_COUNT,
                draw.STRIDE);
    }


    public static final class TYPE {
        public static final int NONE = 0;
        public static final int IMAGES = 1;
    }

    public int imageType;
    public Parser parser;

    public Images() {
        nullifier();
        Rectangle.getDefaultVertexData(this);
    }

    public Images(final Context context, final int imageRId) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        // Read in the resource
        final Bitmap bitmap =
                BitmapFactory.decodeResource(context.getResources(), imageRId, options);

        if (bitmap == null) {
            Log(TAG, "Resource ID " + imageRId + " could not be decoded.");
            nullifier();
            return;
        }
        parser = new Parser(bitmap);
        imageType = TYPE.IMAGES;
        bitmap.recycle();
    }

    public Images(final Bitmap bitmap) {
        parser = new Parser(bitmap);
        imageType = TYPE.IMAGES;
    }

    public void nullifier() {
        imageType = TYPE.NONE;
        parser = null;
    }

}
