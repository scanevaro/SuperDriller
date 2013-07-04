package com.turtlegames.android.superdriller;

import com.badlogic.androidgames.framework.DynamicGameObject;

public class Player extends DynamicGameObject {

	public static final float PLAYER_WIDTH = 1;
	public static final float PLAYER_HEIGHT = 1;
	public static final int PLAYER_STANDING = 0;
	public static final int PLAYER_FALLING = 1;
	public static final int PLAYER_MOVING_LEFT = 2;
	public static final int PLAYER_MOVING_RIGHT = 3;
	public static final int LOOKING_DOWN = 0;
	public static final int LOOKING_LEFT = 1;
	public static final int LOOKING_RIGHT = 2;

	int state;
	int looking;
	World world;

	public Player(World world, float x, float y) {
		super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
		this.world = world;
	}

	public void update(float deltaTime) {
		if (state == PLAYER_FALLING) {
			velocity.add(World.gravity.x * deltaTime, World.gravity.y
					* deltaTime);
			position.add(velocity.x * deltaTime, velocity.y * deltaTime);
			bounds.lowerLeft.set(position).sub(bounds.width / 2,
					bounds.height / 2);
		}
		if (state == PLAYER_MOVING_LEFT) {
			velocity.add(World.gravity.y * deltaTime, 0);
			position.add(velocity.x * deltaTime, velocity.y * deltaTime);
			bounds.lowerLeft.set(position).sub(bounds.width / 2,
					bounds.height / 2);
		}
		if (state == PLAYER_MOVING_RIGHT) {

		}

	}

	public void digDown(Block block) {
		if (block.position.y + 1 == position.y
				&& position.x == block.position.x) {
			world.blocks.remove(block);
		}
	}

	public void digLeft(Block block) {
		if (block.position.x + 1 == position.x
				&& block.position.y == position.y) {
			world.blocks.remove(block);
		}
	}

	public void digRight(Block block) {
		if (block.position.x - 1 == position.x
				&& block.position.y == position.y) {
			world.blocks.remove(block);
		}
	}

	public boolean isBlockLeft(Block block) {
		// TODO Auto-generated method stub
		if (position.x - 1 == block.position.x
				&& position.y == block.position.y) {
			return true;
		} else {
			return false;
		}
	}
}