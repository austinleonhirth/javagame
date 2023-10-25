package com.mygdx.roguelike;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.roguelike.screens.TitleScreen;

public class AustinsGame extends Game {
	public Color mainBackgroundColor = new Color(241f/255f, 233f/255f, 212f/255f, 1f);
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new TitleScreen(this));
	}

	@Override
	public void render () {
		//Delegates render method to the current screen
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
