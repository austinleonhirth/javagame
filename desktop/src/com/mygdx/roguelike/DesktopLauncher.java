package com.mygdx.roguelike;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
//import com.mygdx.roguelike.AustinsGame;


public class DesktopLauncher {
/*
 * This is the main entry point for the desktop version of the game.
 * Application Information:
 */
final static String TITLE 			= "Austin's Game";
final static int 	WINDOW_WIDTH 	= 1280;
final static int 	WINDOW_HEIGHT 	= 720;
final static int	FPS 			= 120;


	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(FPS);
		config.setWindowedMode(WINDOW_WIDTH, WINDOW_HEIGHT);
		config.setResizable(true);
		config.setTitle(TITLE);
		new Lwjgl3Application(new AustinsGame(), config);
	}
}
