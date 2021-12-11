package com.martinmimiGames.util.graphics.opengl2.v3.glsl;

public class ShaderCode {

    public static final String TEXTURE_VERTEX_SHADER =
            "uniform mat4 u_Matrix;\n" +
                    "attribute vec4 a_Position;\n" +
                    "attribute vec2 a_TextureCoordinates;\n" +
                    "varying vec2 v_TextureCoordinates;\n" +
                    "void main()\n" +
                    "{ v_TextureCoordinates = a_TextureCoordinates;\n" +
                    "gl_Position = u_Matrix * a_Position; }";


    public static final String TEXTURE_FRAGMENT_SHADER =
            "precision mediump float; \n" +
                    "uniform sampler2D u_TextureUnit;\n" +
                    "varying vec2 v_TextureCoordinates;\n" +
                    "void main()\n" +
                    "{ gl_FragColor = texture2D(u_TextureUnit, v_TextureCoordinates); }";
}
