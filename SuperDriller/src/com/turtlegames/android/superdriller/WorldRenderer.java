package com.turtlegames.android.superdriller;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.impl.GLGraphics;

public class WorldRenderer {

	static final float FRUSTUM_WIDTH = 288;
	static final float FRUSTUM_HEIGHT = 448;

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
		this.cam = new Camera2D(glGraphics, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
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
		batcher.drawSprite(cam.position.x, cam.position.y, FRUSTUM_WIDTH,
				FRUSTUM_HEIGHT, Assets.backgroundRegion);
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
		renderButtons();
		batcher.endBatch();
		gl.glDisable(GL10.GL_BLEND);

	}

	public void renderButtons() {
		// TODO Auto-generated method stub
		batcher.drawSprite(96, world.player.position.y - 192, 64, 64,
				Assets.arrowButton);
		batcher.drawSprite(32, world.player.position.y - 192, 64, 64, 180 + 90,
				Assets.arrowButton);
		batcher.drawSprite(160, world.player.position.y - 192, 64, 64, 90,
				Assets.arrowButton);
		batcher.drawSprite(256, world.player.position.y - 192, 64, 64,
				Assets.digButton);
	}

	private void renderBlocks() {
		// TODO Auto-generated method stub
		int len = world.blocks.size();
		for (int i = 0; i < len; i++) {
			Block block = world.blocks.get(i);

			if (block.type == Block.BLOCK_TYPE_X) {
				batcher.drawSprite(block.position.x, block.position.y,
						Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT,
						Assets.xBlockRegion);
			} else if (block.type == Block.BLOCK_TYPE_Y) {
				batcher.drawSprite(block.position.x, block.position.y,
						Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT,
						Assets.yBlockRegion);
			} else if (block.type == Block.BLOCK_TYPE_J) {
				batcher.drawSprite(block.position.x, block.position.y,
						Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT,
						Assets.jBlockRegion);
			} else if (block.type == Block.BLOCK_TYPE_Z) {
				batcher.drawSprite(block.position.x, block.position.y,
						Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT,
						Assets.zBlockRegion);
			}
		}

	}

	private void renderPlayer() {
		// TODO Auto-generated method stub
		batcher.drawSprite(world.player.position.x, world.player.position.y, world.player.PLAYER_WIDTH,
				world.player.PLAYER_HEIGHT, Assets.playerRegion);
	}

}
