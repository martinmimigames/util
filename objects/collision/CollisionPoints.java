package com.martinmimiGames.util.objects.collision;

import android.util.Log;
import com.martinmimiGames.util.logger.LoggerConfig;
import com.martinmimiGames.util.objects.Coor;

public class CollisionPoints extends Coor{

    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    private Algorithms algorithms;

    private float theta;
    private float hypo;//longest side of triangle

    public CollisionPoints(float base, float height, int direction){
        algorithms = new Algorithms();
        this.hypo = algorithms.getHypo(base, height);
        switch (direction){
            case LEFT:
                theta = -algorithms.atan(base / height);
            case RIGHT:
                theta = algorithms.atan(base / height);
            default:
                if (LoggerConfig.ON){
                    Log.e("util_Collison", "such option is not valid : " + direction);
                }
                theta = 0f;
        }


    }

    public void setOffset(float base, float height, int direction){
        this.hypo = algorithms.getHypo(base, height);
        switch (direction){
            case LEFT:
                theta = -algorithms.atan(base / height);
            case RIGHT:
                theta = algorithms.atan(base / height);
            default:
                if (LoggerConfig.ON){
                    Log.e("util_Collison", "such option is not valid : " + direction);
                }
                theta = 0f;
        }
    }

    public void update(float angle){
        setCoor(hypo * algorithms.sin(angle - theta),
		hypo * algorithms.cos(angle - theta));
    }
}

