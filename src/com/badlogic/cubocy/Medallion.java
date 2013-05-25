
package com.badlogic.cubocy;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Medallion {
	Rectangle bounds = new Rectangle();
	private boolean visible = true;
	private Map map;
	float stateTime = 0;
	Vector2 pos = new Vector2();

	public Medallion (Map map, float x, float y) {
		this.map = map;
		this.pos.set(x, y);
		bounds.x = x;
		bounds.y = y;
		bounds.width = bounds.height = 1;
	}

	public boolean isVisible () {
		return visible;
	}

	public void setVisible (boolean visible) {
		this.visible = visible;
	}

	private boolean hitBob () {
		fetchCollidableRects ();
		if (bounds.overlaps(map.bob.bounds)) {
			return true;
		}
		return false;
	}

	public void update (float deltaTime) {
		bounds.x = pos.x + 0.2f;
		bounds.y = pos.y + 0.2f;
		if (hitBob()) {
			this.setVisible(false);
			stateTime = 0;
		}

		stateTime += deltaTime;
	}

	Rectangle[] r = {new Rectangle(), new Rectangle(), new Rectangle(), new Rectangle()};
	
	private void fetchCollidableRects () {
		int p1x = (int)bounds.x;
		int p1y = (int)Math.floor(bounds.y);
		int p2x = (int)(bounds.x + bounds.width);
		int p2y = (int)Math.floor(bounds.y);
		int p3x = (int)(bounds.x + bounds.width);
		int p3y = (int)(bounds.y + bounds.height);
		int p4x = (int)bounds.x;
		int p4y = (int)(bounds.y + bounds.height);

		int[][] tiles = map.tiles;
		int tile1 = tiles[p1x][map.tiles[0].length - 1 - p1y];
		int tile2 = tiles[p2x][map.tiles[0].length - 1 - p2y];
		int tile3 = tiles[p3x][map.tiles[0].length - 1 - p3y];
		int tile4 = tiles[p4x][map.tiles[0].length - 1 - p4y];

		if (tile1 != Map.EMPTY)
			r[0].set(p1x, p1y, 1, 1);
		else
			r[0].set(-1, -1, 0, 0);
		if (tile2 != Map.EMPTY)
			r[1].set(p2x, p2y, 1, 1);
		else
			r[1].set(-1, -1, 0, 0);
		if (tile3 != Map.EMPTY)
			r[2].set(p3x, p3y, 1, 1);
		else
			r[2].set(-1, -1, 0, 0);
		if (tile4 != Map.EMPTY)
			r[3].set(p4x, p4y, 1, 1);
		else
			r[3].set(-1, -1, 0, 0);
	}
}
