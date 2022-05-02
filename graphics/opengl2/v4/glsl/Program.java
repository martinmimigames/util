package com.martinmimiGames.util.graphics.opengl2.v4.glsl;

import static android.opengl.GLES20.GL_COMPILE_STATUS;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_LINK_STATUS;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCompileShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glDeleteProgram;
import static android.opengl.GLES20.glDeleteShader;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetProgramiv;
import static android.opengl.GLES20.glGetShaderiv;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glLinkProgram;
import static android.opengl.GLES20.glShaderSource;
import static android.opengl.GLES20.glUseProgram;

import com.martinmimiGames.util.logger.Log;


/**
 * This is the MGGames utility dependency.
 * opengl code implementation for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.1 release
 * @since 17-02-2022 dd-mm-yyyy
 */

public class Program {


  private static final String TAG = "ShaderHelper";

  /**
   * Shader program
   * value = 0 if incorrect
   */
  protected int id;

  public Program(){
    // Create a new program object.
    // return 0 if error
    id = glCreateProgram();

    //run if error
    if (id == 0) {
      if (Log.ON) Log.w(TAG, "Could not create new program");
    }
  }

  public Program addShaderProgram(int type, String shaderCode){
    if (id != 0)
    glAttachShader(id, compileShader(type, shaderCode));
    return this;
  }

  public int getUniformLocation(String name){
    return glGetUniformLocation(id, name);
  }

  public int getAttributeLocation(String name) {
    return glGetAttribLocation(id, name);
  }

  public Program complete(){
    if (id == 0) return this;
    // Link the two shaders together into a program.
    glLinkProgram(id);

    // Get the link status.
    final int[] linkStatus = new int[1];
    glGetProgramiv(id, GL_LINK_STATUS,
        linkStatus, 0);

    // Verify the link status.
    if (linkStatus[0] == 0) {
      // If it failed, delete the program object.
      glDeleteProgram(id);
      id = 0;
      if (Log.ON) Log.w(TAG, "Linking of program failed.");
    }
    return this;
  }

  public void use(){
    // Set the current OpenGL shader program to this program.
    glUseProgram(id);
  }

  public int getProgramId(){
    return id;
  }

  /**
   * Compiles a shader, returning the OpenGL object ID.
   * if error return 0
   */
  private int compileShader(int type, String shaderCode) {
    // Create a new shader object.
    final int shaderObjectId = glCreateShader(type);

    //if shader object id = 0
    //then shader cannot be created
    if (shaderObjectId == 0) {
      if (Log.ON) Log.w(TAG, "Could not create new shader.");
      return 0;
    }

    // Pass in the shader source.
    glShaderSource(shaderObjectId, shaderCode);

    // Compile the shader.
    glCompileShader(shaderObjectId);

    // Get the compilation status.
    final int[] compileStatus = new int[1];
    glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS,
        compileStatus, 0);

    // Verify the compile status.
    //if compile status = 0
    //an error had occurred
    if (compileStatus[0] == 0) {
      // If it failed, delete the shader object.
      glDeleteShader(shaderObjectId);

      if (Log.ON) Log.w(TAG, "Compilation of shader failed.");
      return 0;
    }

    // Return the shader object ID.
    return shaderObjectId;
  }
}
