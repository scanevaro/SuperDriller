package com.turtlegames.android.superdriller;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.impl.GLScreen;
import com.badlogic.androidgames.framework.math.OverlapTester;
import com.badlogic.androidgames.framework.math.Rectangle;
import com.badlogic.androidgames.framework.math.Vector2;
import com.turtlegames.android.superdriller.World.WorldListener;

public class GameScreen extends GLScreen {

	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_OVER = 3;

	int state;
	Camera2D guiCam;
	Vector2 touchPoint;
	SpriteBatcher batcher;
	World world;
	WorldListener worldListener;
	WorldRenderer renderer;
	Rectangle pauseBounds;
	Rectangle resumeBounds;
	Rectangle quitBounds;
	Rectangle downArrowRectangle;
	Rectangle leftArrowRectangle;
	Rectangle rightArrowRectangle;
	int lastScore;
	String scoreString;
	Rectangle digArrowRectangle;

	public GameScreen(Game game) {
		super(game);
		state = GAME_READY;
		guiCam = new Camera2D(glGraphics, 288, 448);
		touchPoint = new Vector2();
		batcher = new SpriteBatcher(glGraphics, 1000);
		worldListener = new WorldListener() {
			public void jump() {

			}

			public void highJump() {

			}

			public void hit() {

			}

			public void coin() {

			}
		};
		world = new World(worldListener);
		renderer = new WorldRenderer(glGraphics, batcher, world);
		pauseBounds = new Rectangle(0, 448 - 64, 64, 64);
		resumeBounds = new Rectangle(144 - 64, 224, 64, 64);
		quitBounds = new Rectangle(144 - 96, 224 - 64, 64, 64);
		downArrowRectangle = new Rectangle(64, 0, 64, 64);
		leftArrowRectangle = new Rectangle(0, 0, 64, 64);
		rightArrowRectangle = new Rectangle(64 + 64, 0, 64, 64);
		digArrowRectangle = new Rectangle(288 - 64, 0, 64, 64);
		lastScore = 0;
		scoreString = "score: 0";

	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		if (deltaTime > 0.1f) {
			deltaTime = 0.1f;
		}
		switch (state) {
		case GAME_READY:
			updateReady();
			break;
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		case GAME_PAUSED:
			updatePaused();
			break;
		case GAME_OVER:
			updateGameOver();
			break;
		}

	}

	private void updateGameOver() {
		// TODO Auto-generated method stub

	}

	private void updatePaused() {
		// TODO Auto-generated method stub

	}

	private void updateRunning(float deltaTime) {
		// TODO Auto-generated method stub
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

			touchPoint.set(event.x, event.y);
			guiCam.touchToWorld(touchPoint);

			if (event.type == TouchEvent.TOUCH_UP) {

				// Directional events
				if (OverlapTester.pointInRectangle(downArrowRectangle,
						touchPoint)) {
					world.player.looking = Player.LOOKING_DOWN;
				}
				if (OverlapTester.pointInRectangle(leftArrowRectangle,
						touchPoint)) {
					world.player.looking = Player.LOOKING_LEFT;
					for (int j = 0; j < world.blocks.size(); j++) {
						Block block = world.blocks.get(j);
						if (world.player.isBlockLeft(block)) {
							break;
						} else {
							if (j == world.blocks.size() - 1) {
								world.player.state = Player.PLAYER_MOVING_LEFT;
							}
						}
					}
				}
				if (OverlapTester.pointInRectangle(rightArrowRectangle,
						touchPoint)) {
					world.player.looking = Player.LOOKING_RIGHT;
					for (int j = 0; j < world.blocks.size(); j++) {
						Block block = world.blocks.get(j);
						if (world.player.isBlockRight(block)) {
							break;
						} else {
							if (j == world.blocks.size() - 1) {
								world.player.state = Player.PLAYER_MOVING_RIGHT;
							}
						}
					}
				}

				// Dig events
				if (OverlapTester.pointInRectangle(digArrowRectangle,
						touchPoint)) {
					switch (world.player.looking) {
					case 0:
						for (int j = 0; j < world.blocks.size(); j++) {
							Block block = world.blocks.get(j);
							world.player.digDown(block);
						}
						break;
					case 1:
						for (int j = 0; j < world.blocks.size(); j++) {
							Block block = world.blocks.get(j);
							world.player.digLeft(block);
						}
						break;
					case 2:
						for (int j = 0; j < world.blocks.size(); j++) {
							Block block = world.blocks.get(j);
							world.player.digRight(block);
						}
						break;
					}
				}
			}

		}
		world.update(deltaTime);
	}

	private void updateReady() {
		// TODO Auto-generated method stub
		if (game.getInput().getTouchEvents().size() > 0) {
			state = GAME_RUNNING;
		}
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_TEXTURE_2D);

		renderer.render();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
