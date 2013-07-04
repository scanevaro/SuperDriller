package com.turtlegames.android.superdriller;

import com.badlogic.androidgames.framework.DynamicGameObject;

public class Block extends DynamicGameObject {

	public static final float BLOCK_WIDTH = 1;
	public static final float BLOCK_HEIGHT = 1;
	public static final int BLOCK_TYPE_DIRT = 0;
	public static final int BLOCK_TYPE_STONE = 1;
	public static final int BLOCK_STATE_NORMAL = 0;
	public static final int BLOCK_STATE_PULVERIZING = 1;
	public static final float BLOCK_PULVERIZE_TIME = 0.2f * 4;

	int type;
	int state;
	float stateTime;

	public Block(int type, float x, float y) {
		super(x, y, BLOCK_WIDTH, BLOCK_HEIGHT);
		this.type = type;
		this.state = BLOCK_STATE_NORMAL;
		this.stateTime = 0;
	}

	public void update(float deltaTime) {
		stateTime += deltaTime;
	}

	public void pulverize() {
		state = BLOCK_STATE_PULVERIZING;
		stateTime = 0;
	}

}
