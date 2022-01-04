package com.martinmimiGames.util.graphics.gl2d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glVertexAttribPointer;

@Deprecated
public class VertexArray {
    private final FloatBuffer floatBuffer;
    private final int byte_per_float;

    public VertexArray(float[] vertexData) {
        byte_per_float = 4;
        floatBuffer = ByteBuffer
                .allocateDirect(vertexData.length * byte_per_float)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(vertexData);
    }

    public void setVertexAttribPointer(int dataOffset, int attributeLocation,
                                       int componentCount, int stride) {
        floatBuffer.position(dataOffset);
        glVertexAttribPointer(attributeLocation, componentCount, GL_FLOAT,
                false, stride, floatBuffer);
        glEnableVertexAttribArray(attributeLocation);

        floatBuffer.position(0);
    }
}
