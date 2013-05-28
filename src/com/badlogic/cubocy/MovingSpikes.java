
package com.badlogic.cubocy;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MovingSpikes extends AbstractMob{
	
	static final float FORWARD_VEL_MOVINGSPIKES = 10;
	static final float BACKWARD_VEL_MOVINGSPIKES = 4;

	int state = FORWARD;
	
	int fx = 0;
	int fy = 0;
	int bx = 0;
	int by = 0;

	public MovingSpikes (Map map, float x, float y) {
		super(map, x, y);
	}

	public void init () {
		int ix = (int)pos.x;
		int iy = (int)pos.y;

		int left = map.tiles[ix - 1][map.tiles[0].length - 1 - iy];
		int right = map.tiles[ix + 1][map.tiles[0].length - 1 - iy];
		int top = map.tiles[ix][map.tiles[0].length - 1 - iy - 1];
		int bottom = map.tiles[ix][map.tiles[0].length - 1 - iy + 1];

		if (left == Map.TILE) {
			vel.x = FORWARD_VEL_MOVINGSPIKES;
			angle = -90;
			fx = 1;
		}
		if (right == Map.TILE) {
			vel.x = -FORWARD_VEL_MOVINGSPIKES;
			angle = 90;
			bx = 1;
		}
		if (top == Map.TILE) {
			vel.y = -FORWARD_VEL_MOVINGSPIKES;
			angle = 180;
			by = -1;
		}
		if (bottom == Map.TILE) {
			vel.y = FORWARD_VEL_MOVINGSPIKES;
			angle = 0;
			fy = -1;
		}
	}

	public void update (float deltaTime) {
		move(deltaTime);
		
		checkHitBob();
	}
	
	protected void move(float deltaTime){
		pos.add(vel.x * deltaTime, vel.y * deltaTime);
		boolean change = false;
		if (state == FORWARD) {
			change = map.tiles[(int)pos.x + fx][map.tiles[0].length - 1 - (int)pos.y + fy] == Map.TILE;
		} else {
			change = map.tiles[(int)pos.x + bx][map.tiles[0].length - 1 - (int)pos.y + by] == Map.TILE;
		}
		if (change) {
			pos.x -= vel.x * deltaTime;
			pos.y -= vel.y * deltaTime;
			state = -state;
			vel.scl(-1);
			if (state == FORWARD) vel.nor().scl(FORWARD_VEL_MOVINGSPIKES);
			if (state == BACKWARD) vel.nor().scl(BACKWARD_VEL_MOVINGSPIKES);
		}
	}
}
