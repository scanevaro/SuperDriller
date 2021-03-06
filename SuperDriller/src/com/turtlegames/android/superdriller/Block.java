package com.turtlegames.android.superdriller;

import com.badlogic.androidgames.framework.DynamicGameObject;

public class Block extends DynamicGameObject {

	public static final float BLOCK_WIDTH = 32;
	public static final float BLOCK_HEIGHT = 32;
	public static final int BLOCK_TYPE_X = 0;
	public static final int BLOCK_TYPE_Y = 1;
	public static final int BLOCK_TYPE_Z = 2;
	public static final int BLOCK_TYPE_J = 3;
	public static final float BLOCK_PULVERIZE_TIME = 0.2f * 4;
	public static final int BLOCK_FALLING = 1;
	public static final int BLOCK_STILL = 0;
	public static final int BLOCK_STATE_PULVERIZING = 2;

	int type;
	int state;
	float stateTime;

	public Block(int type, float x, float y) {
		super(x, y, BLOCK_WIDTH, BLOCK_HEIGHT);
		this.type = type;
		this.state = BLOCK_STILL;
		this.stateTime = 0;
	}

	public void update(float deltaTime) {
		stateTime += deltaTime;
//		if (type == BLOCK_TYPE_Y) {
			if (state == BLOCK_FALLING) {
				velocity.add(World.gravity.x * deltaTime, World.gravity.y
						* deltaTime);
				if (position.y >= -43.5f) {
					position.add(velocity.x * deltaTime, velocity.y * deltaTime);
					bounds.lowerLeft.set(position).sub(bounds.width / 2,
							bounds.height / 2);
				}
			}
			if (state == BLOCK_STILL) {
				velocity.x = 0;
				velocity.y = 0;
			}

//		}
	}

	public void pulverize() {
		state = BLOCK_STATE_PULVERIZING;
		stateTime = 0;
	}

}
