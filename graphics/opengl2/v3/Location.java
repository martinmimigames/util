package com.martinmimiGames.util.graphics.opengl2.v3;

public class Location {

    public static final int X = 0;
    public static final int Y = 1;

    public float[] coor;
    public float angle;

    public Location(){
        coor = new float[2];
    }

    public void setCoor(float x, float y){
        coor[X] = x;
        coor[Y] = y;
    }

    public void setAngle(float angle){
        this.angle = angle;
    }

    public float[] getCoor(){
        return coor;
    }

    public float getAngle(){
        return angle;
    }

}
