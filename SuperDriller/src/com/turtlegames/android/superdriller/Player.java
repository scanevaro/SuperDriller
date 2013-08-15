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
		looking = LOOKING_DOWN;
	}

	public void update(float deltaTime) {
		
		// Depending on the State it checks the corresponding block.
		// It can be a Switch too
		if (state == PLAYER_FALLING) {

			// THE IF BLOCK CHECKS IF THE PLAYER IS AT THE SAME/OR BELOW Y THAT
			// THE LAST BLOCKS AND STOPS THE UPDATE METHOD FOR KEEP DROPING.
			// ONLY FOR THE PROTOTIPE
			if (position.y >= -43.5f) {
				velocity.add(World.gravity.x * deltaTime, World.gravity.y
						* deltaTime);
				position.add(velocity.x * deltaTime, velocity.y * deltaTime);
				bounds.lowerLeft.set(position).sub(bounds.width / 2,
						bounds.height / 2);
			}
		}
		if (state == PLAYER_MOVING_LEFT) {
			// velocity is reset every state change to avoid player moving at the speed of light eventually
			velocity.y = 0;
			
			velocity.add(-5 * deltaTime, 0);
			// velocity is added to position, but it can be only a number to position, it doesn't really matter
			position.add(velocity.x * deltaTime, velocity.y * deltaTime);
			// player bounds update
			bounds.lowerLeft.set(position).sub(bounds.width / 2,
					bounds.height / 2);
		}
		if (state == PLAYER_MOVING_RIGHT) {
			velocity.y = 0;
			velocity.add(5 * deltaTime, 0);
			position.add(velocity.x * deltaTime, velocity.y * deltaTime);
			bounds.lowerLeft.set(position).sub(bounds.width / 2,
					bounds.height / 2);
		}
	}

	public void digDown(Block block) {
		// check if block argument is below player
		if (block.position.y + 1 == position.y
				&& position.x == block.position.x) {
//			world.blocks.remove(block);
			chain(block);
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

	public boolean isBlockRight(Block block) {
		if (position.x + 1 == block.position.x
				&& position.y == block.position.y) {
			return true;
		} else {
			return false;
		}
	}
	
	public void chain(Block block){
		if(!world.chainBlocks.contains(block)){
			world.chainBlocks.add(block);
		}
		for(int i = 0; i < world.blocks.size(); i++){
			Block blockAtSide = world.blocks.get(i);
			for(int x = -1; x <= 1; x++){
				for(int y = -1; y <= 1; y++){
					if(x != 0 || y != 0){
						if(x == -1){
							// block down
							if(block.position.x == blockAtSide.position.x && block.position.y - 1 == blockAtSide.position.y){
								if(blockAtSide.type == block.type){
									if(!world.chainBlocks.contains(blockAtSide)){
										world.chainBlocks.add(blockAtSide);
										chain(blockAtSide);
									}else{
										for(int j = 0; j < world.chainBlocks.size(); j++){
											for(int k = 0; k < world.blocks.size(); k++){
												if(world.blocks.get(k).equals(world.chainBlocks.get(0))){
													world.chainBlocks.remove(0);
													world.blocks.remove(k);
												}
											}
										}    // LOS BLOQUES NO SE BORRAN PORQUE TAMBIEN ESTAN EN EL ARRAY WORLD.BLOCKS
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
}
