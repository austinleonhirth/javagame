package com.mygdx.roguelike.screens;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.roguelike.AustinsGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
    private Label fpsCounter;
    private Image titleLogo;
    private ImageButton playButton;
    private ImageButton settingsButton;
    private ImageButton exitButton;

    /*
     * Textures need to be visible to dispose() so we can dispose of them
     */
    private Texture logoTexture;
    private Texture b_play;
    private Texture b_play_h;
    private Texture b_play_p;
    private Texture b_settings;
    private Texture b_settings_h;
    private Texture b_settings_p;
    private Texture b_exit;
    private Texture b_exit_h;
    private Texture b_exit_p;


    /*
     * Constructor for the TitleScreen class.
     * @param 'game' The AustinsGame game object (used to switch screens)
     */
    public TitleScreen(final AustinsGame game) {
        this.game = game;

        //Viewport keeps everything the same size
        Viewport viewport = new ScreenViewport();
        this.stage = new Stage(viewport, game.batch);

        //FPS counter
        BitmapFont fps_font             = new BitmapFont(); //default font
        Label.LabelStyle fps_style      = new Label.LabelStyle();
        fps_style.font                  = fps_font;
        fps_style.fontColor             = Color.BLACK;
        fpsCounter = new Label("FPS: " + Gdx.graphics.getFramesPerSecond(), fps_style);
        fpsCounter.setPosition(2, Gdx.graphics.getHeight(), Align.topLeft);

        //Title logo
        logoTexture = new Texture(Gdx.files.internal("assets/titlescreen/crackcrazy_2.jpg"));
        titleLogo = new Image(logoTexture);
        titleLogo.setSize(550, 520);
        titleLogo.setPosition(Gdx.graphics.getWidth()/2+10, Gdx.graphics.getHeight()-220, Align.center);

        //Play button
        b_play      = new Texture(Gdx.files.internal("assets/titlescreen/b_play.png"));
        b_play_h    = new Texture(Gdx.files.internal("assets/titlescreen/b_play_h.png"));
        b_play_p    = new Texture(Gdx.files.internal("assets/titlescreen/b_play_p.png"));
        Drawable play_drawable          = new TextureRegionDrawable(b_play);
        Drawable play_drawable_h        = new TextureRegionDrawable(b_play_h);
        Drawable play_drawable_p        = new TextureRegionDrawable(b_play_p);
        ImageButtonStyle b_playStyle    = new ImageButtonStyle();
        b_playStyle.up      = play_drawable;
        b_playStyle.over    = play_drawable_h;
        b_playStyle.down    = play_drawable_p;
        playButton          = new ImageButton(b_playStyle);
        playButton.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-500, Align.center);

        playButton.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            // This will be called when the button is clicked.
                game.setScreen(new GameScreen(game));
        }});


        //Settings button
        b_settings      = new Texture(Gdx.files.internal("assets/titlescreen/b_settings.png"));
        b_settings_h    = new Texture(Gdx.files.internal("assets/titlescreen/b_settings_h.png"));
        b_settings_p    = new Texture(Gdx.files.internal("assets/titlescreen/b_settings_p.png"));
        Drawable settings_drawable          = new TextureRegionDrawable(b_settings);
        Drawable settings_drawable_h        = new TextureRegionDrawable(b_settings_h);
        Drawable settings_drawable_p        = new TextureRegionDrawable(b_settings_p);
        ImageButtonStyle b_settingsStyle    = new ImageButtonStyle();
        b_settingsStyle.up      = settings_drawable;
        b_settingsStyle.over    = settings_drawable_h;
        b_settingsStyle.down    = settings_drawable_p;
        settingsButton          = new ImageButton(b_settingsStyle);
        settingsButton.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-580, Align.center);

        //Exit button
        b_exit      = new Texture(Gdx.files.internal("assets/titlescreen/b_exit.png"));
        b_exit_h    = new Texture(Gdx.files.internal("assets/titlescreen/b_exit_h.png"));
        b_exit_p    = new Texture(Gdx.files.internal("assets/titlescreen/b_exit_p.png"));
        Drawable exit_drawable          = new TextureRegionDrawable(b_exit);
        Drawable exit_drawable_h        = new TextureRegionDrawable(b_exit_h);
        Drawable exit_drawable_p        = new TextureRegionDrawable(b_exit_p);
        ImageButtonStyle b_exitStyle    = new ImageButtonStyle();
        b_exitStyle.up      = exit_drawable;
        b_exitStyle.over    = exit_drawable_h;
        b_exitStyle.down    = exit_drawable_p;
        exitButton          = new ImageButton(b_exitStyle);
        exitButton.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-660, Align.center);

        exitButton.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            // This will be called when the button is clicked.
                Gdx.app.exit();
        }});
        

        //Add actors
        stage.addActor(titleLogo);
        stage.addActor(fpsCounter);
        stage.addActor(playButton);
        stage.addActor(settingsButton);
        stage.addActor(exitButton);

    }

    @Override
    public void show() {
        //start taking input from the stage
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        //Clear screen
        ScreenUtils.clear(game.mainBackgroundColor);

        //Draw stage
        fpsCounter.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
        
    }

    @Override
    public void resize(int width, int height) {
        //update based on new screen size
        stage.getViewport().update(width, height, true);
        titleLogo.setPosition(Gdx.graphics.getWidth()/2+10, Gdx.graphics.getHeight()-220, Align.center);
        fpsCounter.setPosition(2, Gdx.graphics.getHeight(), Align.topLeft);

        //button updates
        playButton.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-500, Align.center);
        settingsButton.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-580, Align.center);
        exitButton.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-660, Align.center);

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
        //textures
        logoTexture.dispose();
        b_play.dispose();
        b_play_h.dispose();
        b_play_p.dispose();
        b_settings.dispose();
        b_settings_h.dispose();
        b_settings_p.dispose();
        b_exit.dispose();
        b_exit_h.dispose();
        b_exit_p.dispose();

        //stage dispose
        stage.dispose();
    }
    
}