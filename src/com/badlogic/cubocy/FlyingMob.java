
package com.badlogic.cubocy;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class FlyingMob  extends AbstractMob{
	
	private static final float FORWARD_VEL_FLYING_MOB = 5;
	private static final float BACKWARD_VEL_FLYING_MOB = 5;
	private static final float UPWARD_VEL_FLYING_MOB = 5;
	private static final float DOWNWARD_VEL_FLYING_MOB = 5;
	
	private int stateHorizontal = FORWARD;
	private int stateVertical = UPWARD;
	
	private final Vector2 startPos=new Vector2();
	
	public FlyingMob (Map map, float x, float y) {
		super(map, x, y);
		startPos.x=x;
		startPos.y=y;		
	}

	public void init () {
		
		vel.y = UPWARD_VEL_FLYING_MOB;		
		vel.x = FORWARD_VEL_FLYING_MOB;
		angle = 0;
	}

	public void update (float deltaTime) {
		move(deltaTime);
		
		checkHitBob();
	}
	
	protected void move (float deltaTime) {
		pos.add(vel.x * deltaTime, vel.y * deltaTime);

		if (pos.y-startPos.y>=1 || pos.y-startPos.y<=-1) {
			pos.y -= vel.y * deltaTime;
			stateVertical = -stateVertical;
			vel.scl(1,-1);
			if (stateVertical == UPWARD) vel.nor().scl(UPWARD_VEL_FLYING_MOB);
			if (stateVertical == DOWNWARD) vel.nor().scl(DOWNWARD_VEL_FLYING_MOB);
		}
		if (pos.x-startPos.x>=3 || pos.x-startPos.x<=-3) {
			pos.x -= vel.x * deltaTime;
			stateHorizontal = -stateHorizontal;
			vel.scl(-1,1);
			if (stateHorizontal == FORWARD) vel.nor().scl(FORWARD_VEL_FLYING_MOB);
			if (stateHorizontal == BACKWARD) vel.nor().scl(BACKWARD_VEL_FLYING_MOB);
		}
	}
	
}
