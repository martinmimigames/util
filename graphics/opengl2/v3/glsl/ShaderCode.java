package com.martinmimiGames.util.graphics.opengl2.v3.glsl;

/**
 * This is the MGGames utility dependency.
 * opengl code for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.1 release
 * @since 17-02-2022 dd-mm-yyyy
 */

public class ShaderCode {

  public static final String TEXTURE_VERTEX_SHADER =
      new StringBuilder()
      .append("uniform mat4 u_Matrix;")
      .append("attribute vec4 a_Position;")
      .append("attribute vec2 a_TextureCoordinates;")
      .append("varying vec2 v_TextureCoordinates;")
      .append("void main()")
      .append("{v_TextureCoordinates=a_TextureCoordinates;")
      .append("gl_Position=u_Matrix*a_Position;}")
      .toString();


  public static final String TEXTURE_FRAGMENT_SHADER =
      new StringBuilder()
          .append("precision mediump float;")
          .append("uniform sampler2D u_TextureUnit;")
          .append("varying vec2 v_TextureCoordinates;")
          .append("void main()")
          .append("{gl_FragColor=texture2D(u_TextureUnit,v_TextureCoordinates);}")
          .toString();
}
