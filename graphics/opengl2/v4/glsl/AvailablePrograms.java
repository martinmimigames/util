package com.martinmimiGames.util.graphics.opengl2.v4.glsl;

/**
 * This is the MGGames utility dependency.
 * Available opengl programs for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.1 release
 * @since 17-02-2022 dd-mm-yyyy
 */

public class AvailablePrograms {

  public TextureProgram textureProgram;
  public SolidColorProgram solidColorProgram;

  public AvailablePrograms(){
    textureProgram = new TextureProgram();
    solidColorProgram = new SolidColorProgram();
  }

}
