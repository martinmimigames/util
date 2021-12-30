package com.martinmimiGames.util.math;

public class Offset {

  private double length;
  private double atan_in_radians;
  public final Left left;
  public final Right right;

  public Offset(float x, float y) {
    setOffset(x, y);
    left = new Left();
    right = new Right();
  }

  public Offset(){
    left = new Left();
    right = new Right();
  }

  private void setOffset(float x, float y) {
    length = Math.sqrt(sqr(x) + sqr(y));
    atan_in_radians = Math.atan(x / y);
  }

  public class Left {

    public float x(float angle) {
      return (float) (length * Math.sin(Math.toRadians(angle) - atan_in_radians));
    }

    public float y(float angle) {
      return (float) -(length * Math.cos(Math.toRadians(angle) - atan_in_radians));
    }
  }

  public class Right {
    public float x(float angle) {
      return (float) (length * Math.sin(Math.toRadians(angle) + atan_in_radians));
    }

    public float y(float angle) {
      return (float) -(length * Math.cos(Math.toRadians(angle) + atan_in_radians));
    }
  }

  private float sqr(float l) {
    return l * l;
  }
}
