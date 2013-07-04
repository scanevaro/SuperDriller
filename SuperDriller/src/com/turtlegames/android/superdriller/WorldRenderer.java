package com.turtlegames.android.superdriller;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.impl.GLGraphics;

public class WorldRenderer {

	static final float FRUSTRUM_WIDTH = 9;
	static final float FRUSTRUM_HEIGHT = 14;

	GLGraphics glGraphics;
	SpriteBatcher batcher;
	World world;
	Camera2D cam;
	Random rand;

	public WorldRenderer(GLGraphics glGraphics, SpriteBatcher batcher,
			World world) {
		this.glGraphics = glGraphics;
		this.batcher = batcher;
		this.world = world;
		this.cam = new Camera2D(glGraphics, FRUSTRUM_WIDTH, FRUSTRUM_HEIGHT);
		this.cam.position.y = this.world.player.position.y;
		rand = new Random();
	}

	public void render() {
		// TODO Auto-generated method stub
		if (world.player.position.y < cam.position.y) {
			cam.position.y = world.player.position.y;
		}
		cam.setViewportAndMatrices();
		renderBackground();
		renderObjects();
	}

	public void renderBackground() {
		batcher.beginBatch(Assets.background);
		batcher.drawSprite(cam.position.x, cam.position.y, FRUSTRUM_WIDTH,
				FRUSTRUM_HEIGHT, Assets.backgroundRegion);
		batcher.endBatch();
	}

	public void renderObjects() {
		// TODO Auto-generated method stub
		GL10 gl = glGraphics.getGL();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		batcher.beginBatch(Assets.items);
		renderPlayer();
		renderBlocks();
		renderArrowButtons();
		batcher.endBatch();
		gl.glDisable(GL10.GL_BLEND);

	}

	public void renderArrowButtons() {
		// TODO Auto-generated method stub
		batcher.drawSprite(4.5f, world.player.position.y - 6, 2, 2,
				Assets.arrowButton);
		batcher.drawSprite(1, world.player.position.y - 6, 2, 2, 180 + 90,
				Assets.arrowButton);
	}

	private void renderBlocks() {
		// TODO Auto-generated method stub
		int len = world.blocks.size();
		for (int i = 0; i < len; i++) {
			Block block = world.blocks.get(i);

			if (block.type == Block.BLOCK_TYPE_DIRT) {
				batcher.drawSprite(block.position.x, block.position.y,
						Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT,
						Assets.dirtBlockRegion);

			} else {
				batcher.drawSprite(block.position.x, block.position.y,
						Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT,
						Assets.stoneBlockRegion);
			}

		}

	}

	private void renderPlayer() {
		// TODO Auto-generated method stub
		batcher.drawSprite(world.player.position.x, world.player.position.y, 1,
				1, Assets.playerRegion);
	}

}