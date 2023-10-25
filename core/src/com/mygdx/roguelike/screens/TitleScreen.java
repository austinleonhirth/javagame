package com.mygdx.roguelike.screens;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.roguelike.AustinsGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class TitleScreen implements Screen{

    /*
     * Local variables
     * game is a reference to the AustinsGame object (used to switch screens)
     * stage is the stage that will be drawn to the screen
     */
    private final AustinsGame game;
    private Stage stage;

    /*
     * Actors
     */
    Label title;
    Label fpsCounter;

    /*
     * Constructor for the TitleScreen class.
     * @param 'game' The AustinsGame game object (used to switch screens)
     */
    public TitleScreen(final AustinsGame game) {
        this.game = game;

        //Viewport keeps everything the same size
        Viewport viewport = new ScreenViewport();
        this.stage = new Stage(viewport, game.batch);

        //Title text (center of screen)
        BitmapFont title_font           = new BitmapFont(); //default font
        Label.LabelStyle title_style    = new Label.LabelStyle();
        title_style.font                = title_font;
        title_style.fontColor           = Color.WHITE;
        title = new Label("Austin's Game", title_style);
        title.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-100, Align.center);

        //fps counter
        BitmapFont fps_font             = new BitmapFont(); //default font
        Label.LabelStyle fps_style      = new Label.LabelStyle();
        fps_style.font                  = fps_font;
        fps_style.fontColor             = Color.WHITE;
        fpsCounter = new Label("FPS: " + Gdx.graphics.getFramesPerSecond(), fps_style);
        fpsCounter.setPosition(0, Gdx.graphics.getHeight(), Align.topLeft);

        //Add actors
        stage.addActor(title);
        stage.addActor(fpsCounter);
    }

    @Override
    public void show() {
        //start taking input from the stage
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        //Clear screen
        ScreenUtils.clear(Color.BLACK);

        //Draw stage
        fpsCounter.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
        
    }

    @Override
    public void resize(int width, int height) {
        //update based on new screen size
        stage.getViewport().update(width, height, true);
        title.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-100, Align.center);
        fpsCounter.setPosition(0, Gdx.graphics.getHeight(), Align.topLeft);

    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
    
}