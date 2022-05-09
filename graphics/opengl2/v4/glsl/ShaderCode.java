package com.martinmimiGames.util.graphics.opengl2.v4.glsl;

/**
 * This is the MGGames utility dependency.
 * opengl code for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 4.0.1 release
 * @since 09-05-2022 dd-mm-yyyy
 */

public class ShaderCode {
  // Uniform constants
  public static final String U_MATRIX = "u_Matrix";
  public static final String U_TEXTURE_UNIT = "u_TextureUnit";

  // Attribute constants
  public static final String A_POSITION = "a_Position";
  public static final String A_COLOR = "a_Color";
  public static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";

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

  public static final String SOLID_VERTEX_SHADER =
      new StringBuilder()
          .append("uniform mat4 u_Matrix;")
          .append("attribute vec4 a_Position;")
          .append("void main()")
          .append("{gl_Position=u_Matrix*a_Position;}")
          .toString();

  public static final String TEXTURE_FRAGMENT_SHADER =
      new StringBuilder()
          .append("precision mediump float;")
          .append("uniform sampler2D u_TextureUnit;")
          .append("varying vec2 v_TextureCoordinates;")
          .append("void main()")
          .append("{gl_FragColor=texture2D(u_TextureUnit,v_TextureCoordinates);}")
          .toString();

  public static final String SOLID_COLOR_FRAGMENT_SHADER =
      new StringBuilder()
          .append("precision mediump float;")
          .append("uniform vec4 a_Color;")
          .append("void main()")
          .append("{gl_FragColor = a_Color;}")
          .toString();


}
