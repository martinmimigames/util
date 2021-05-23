package com.martinmimiGames.util.objects.collision;

import com.martinmimiGames.util.objects.Coor;
import com.martinmimiGames.util.objects.Rect;
import com.martinmimiGames.util.objects.RotatableRect;

public class Collision{

    private Rect rect_subject;
    private RotatableRect rotatable_rect_subject;

    private CollisionPoints[] collision_points;
    private float collision_radius;
    
    public Collision(){
	rect_subject = null;
	rotatable_rect_subject = null;
	collision_points = null;
    }

    public void setSubject(RotatableRect subject){
        rotatable_rect_subject = subject;
	rect_subject = null;
	collision_points = new CollisionPoints[4];
    }

    public void setSubject(Rect subject){
        rect_subject = subject;
	rotatable_rect_subject = null;
	collision_points = null;
    }

    public void setOffset(//variables){
	if (rotatable_rect_subject != null){
	    for (CollisionPoints point : collision_points){
		point.setOffset(//variables);
	    }
	}else if (rect_subject != null){
	    
	    }
    }

    public void updateAngle(){
	if(rotatable_rect_subject != null){
	    for (CollisionPoints point : collision_points){
		point.update(rotatable_rect_subject.getAngle());
	    }
	}
    }

    public void testTarget(RotatableRect target){
	
    }

    public void testTarget(Rect target){

    }

    public void testTarget(Coor target){

    }

}
