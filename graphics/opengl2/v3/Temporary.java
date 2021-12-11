package com.martinmimiGames.util.graphics.opengl2.v3;

import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.Matrix.rotateM;
import static android.opengl.Matrix.translateM;

import com.martinmimiGames.util.graphics.opengl2.v3.glsl.TextureShaderProgram;
import com.martinmimiGames.util.graphics.opengl2.v3.glsl.VertexArray;
import com.martinmimiGames.util.objects.Rect;
import com.martinmimiGames.util.objects.RotatableRect;

import java.util.ArrayList;

public class Temporary implements Drawable{

    private final Draw draw;
    private final int texture;
    private final float[] vertex_data;
    private final float x,y, angle;

    public Temporary(Draw draw, Location location, Rect objects, final int textureId){
        this.draw = draw;
        // get the data from the storage
        float[]result = objects.getDataArray();
        ArrayList<Float> crop = draw.crop_data.get(textureId);
        texture = draw.texture_data.get(textureId);
        //final float angle = result[RotatableRect.ANGLE];

        // combine the data

        final float half_w = result[RotatableRect.HALF_WIDTH] * draw.ratio;
        final float half_h = result[RotatableRect.HALF_HEIGHT] * draw.ratio;

        vertex_data = new float[]{
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
        x = location.coor[Location.X] * draw.ratio;
        y = location.coor[Location.Y] * draw.ratio;
        angle = location.angle;
    }

    public void draw(Draw draw) {

        // the drawing process
        translateM(draw.projectionMatrix, 0, x, -y, 0f);
        rotateM(draw.projectionMatrix, 0, -angle, 0f, 0f, 1f);

        drawTextureDefault(vertex_data, draw.projectionMatrix, texture, draw);

        rotateM(draw.projectionMatrix, 0, angle, 0f, 0f, 1f);

        translateM(draw.projectionMatrix, 0, -x, y, 0f);
        // the end of the drawing process
    }

    private void drawTextureDefault(final float[] vertex_data, final float[] projectionMatrix, final int texture, final Draw draw){
        final VertexArray vertexArray = new VertexArray(vertex_data);

        draw.textureProgram.useProgram();

        draw.textureProgram.setUniforms(projectionMatrix, texture);

        bindData(draw.textureProgram, vertexArray);

        glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
    }

    private void bindData(final TextureShaderProgram textureProgram, final VertexArray vertexArray) {
        vertexArray.setVertexAttribPointer(
                0, textureProgram.getPositionAttributeLocation(), draw.POSITION_COMPONENT_COUNT, draw.STRIDE);

        vertexArray.setVertexAttribPointer(
                draw.POSITION_COMPONENT_COUNT,
                textureProgram.getTextureCoordinatesAttributeLocation(),
                draw.TEXTURE_COORDINATES_COMPONENT_COUNT,
                draw.STRIDE);
    }

}
