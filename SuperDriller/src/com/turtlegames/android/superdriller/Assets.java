package com.turtlegames.android.superdriller;

import com.badlogic.androidgames.framework.Sound;
import com.badlogic.androidgames.framework.gl.Texture;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.impl.GLGame;

public class Assets {
	public static Texture background;
	public static TextureRegion backgroundRegion;
	public static Texture startButton;
	public static TextureRegion startButtonRegion;
	public static Texture items;
	public static TextureRegion playerRegion;
	public static TextureRegion dirtBlockRegion;
	public static TextureRegion stoneBlockRegion;
	public static TextureRegion arrowButton;
	//public static TextureRegion digButton;

	public static void load(GLGame game) {
		background = new Texture(game, "background.png");
		backgroundRegion = new TextureRegion(background, 0, 0, 288, 448);

		startButton = new Texture(game, "startbutton.png");
		startButtonRegion = new TextureRegion(startButton, 0, 0, 150, 100);

		items = new Texture(game, "items.png");
		playerRegion = new TextureRegion(items, 0, 0, 32, 32);
		dirtBlockRegion = new TextureRegion(items, 32, 0, 32, 32);
		stoneBlockRegion = new TextureRegion(items, 64, 0, 32, 32);
		arrowButton = new TextureRegion(items, 0, 32, 64, 64);

	}

	public static void reload() {
		background.reload();

	}

	public static void playSound(Sound sound) {

	}
}
