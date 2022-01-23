package com.martinmimiGames.util.helper;

import com.martinmimiGames.util.logger.Log;

/**
 * This is the MGGames utility dependency.
 * a fps regulator
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.0 first release
 * @since about 22-01-2021 dd-mm-yyyy
 */
public class BasicFpsRegulator implements Runnable {

  // default 50 fps
  private long stopTime = 20;

  @Override
  public void run() {
    try {
      Thread.sleep(stopTime);
    } catch (InterruptedException e) {
      if (Log.ON) Log.w(BasicFpsRegulator.class.toString(), "stop interrupted.\n" + e.getCause());
    }
  }

  /**
   * set the time to stop in millis
   *
   * @param stopTime time to stop in millis
   */
  public void setStopTime(long stopTime) {
    this.stopTime = stopTime;
  }

  /**
   * set the time to stop by fps
   *
   * @param fps fps of loop
   */
  public void setFps(float fps) {
    this.stopTime = (long) (1000 / fps);
  }
}
