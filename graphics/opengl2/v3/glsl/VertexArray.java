package com.martinmimiGames.util.graphics.opengl2.v3.glsl;

import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glVertexAttribPointer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * This is the MGGames utility dependency.
 * Vertex data processing for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.0 first release
 * @since about 11-12-2021 dd-mm-yyyy
 */

public class VertexArray {
  private final FloatBuffer floatBuffer;
  private static final int BYTE_PER_FLOAT = 4;

  public VertexArray(float[] vertexData) {
    floatBuffer = ByteBuffer
        .allocateDirect(vertexData.length * BYTE_PER_FLOAT)
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
