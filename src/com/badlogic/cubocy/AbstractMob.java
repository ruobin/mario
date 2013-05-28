
package com.badlogic.cubocy;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractMob {	
	
	static final int FORWARD = 1;
	static final int BACKWARD = -1;
	static final int UPWARD = 1;
	static final int DOWNWARD = -1;	
	
	protected Map map;
	protected Rectangle bounds = new Rectangle();
	protected Vector2 vel = new Vector2();
	protected Vector2 pos = new Vector2();
	protected float angle = 0;
	
	public AbstractMob (Map map, float x, float y) {
		this.map = map;
		this.pos.x = x;
		this.pos.y = y;
		this.bounds.x = x;
		this.bounds.y = y;
		this.bounds.width = this.bounds.height = 1;
	}

	abstract void init();

	public void update (float deltaTime) {
		move(deltaTime);		
		checkHitBob();
	}
	
	protected abstract void move (float deltaTime);
	
	protected void checkHitBob(){
		bounds.x = pos.x;
		bounds.y = pos.y;
		
		if (map.bob.bounds.overlaps(bounds)) {
			if (map.bob.state != Bob.DYING) {
				map.bob.state = Bob.DYING;
				map.bob.stateTime = 0;
			}
		}
	}

}
