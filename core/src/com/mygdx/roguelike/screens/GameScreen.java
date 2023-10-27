package com.mygdx.roguelike.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.roguelike.AustinsGame;
import com.mygdx.roguelike.characters.GameCharacter;

public class GameScreen implements Screen{

    //Testing

    static final int WORLD_WIDTH = 100;
    static final int WORLD_HEIGHT = 100;

    static final int camSpeed = 15;
    static final float camZoomSpeed = 1f;

    static final int camViewportWidth = 45;
    static final int camViewportHeight = 45;
    
    private Sprite map;
    private SpriteBatch batch;

    private static Stage stage;
    GameCharacter character;

    public GameScreen(AustinsGame game) {

        batch = new SpriteBatch();

        //Test actor
        character = new GameCharacter(new Texture(Gdx.files.internal("moomoo.jpg")),WORLD_HEIGHT, WORLD_WIDTH);
        character.setPosition(50, 50);
    
        // Create an OrthographicCamera that controls the viewable area according to your defined dimensions
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, camViewportWidth, camViewportHeight);
    
        // Create the FitViewport with your camera, and the world width and height as the minimum viewable area
        FillViewport viewport = new FillViewport(camViewportWidth, camViewportHeight, camera);
        stage = new Stage(viewport, batch);  
        Gdx.input.setInputProcessor(stage);
    
        map = new Sprite(new Texture(Gdx.files.internal("test_map.png")));
        map.setPosition(0, 0);
        map.setSize(WORLD_WIDTH, WORLD_HEIGHT);

        // Ensure our camera updates once initially
        camera.update();
        stage.addActor(character);
    }
    

    @Override
    public void show() {
  
    }

    @Override
    public void render(float delta) {
        // Get the camera from the stage
        OrthographicCamera camera = (OrthographicCamera) stage.getCamera();
    
        // Update the camera position based on the character's position
        // We also center the camera on the character by adding half the character's dimensions
        float camX = character.getX() + character.characterWidth / 2;
        float camY = character.getY() + character.characterHeight / 2;
    
        // Clamp the camera's position to ensure it does not go outside the world bounds
        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;

        //Aspect ratio
        float ar = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
    
        // These are the clamped x and y coordinates for the camera
        camera.position.x = MathUtils.clamp(camX, effectiveViewportWidth / 2f, WORLD_WIDTH - effectiveViewportWidth / 2f);
        camera.position.y = MathUtils.clamp(camY, effectiveViewportHeight/2 * ar, WORLD_HEIGHT - effectiveViewportHeight/2 * ar);
    
        camera.update();
    
        // Set the SpriteBatch's projection matrix
        batch.setProjectionMatrix(camera.combined);
    
        // Clear the screen and draw everything
        ScreenUtils.clear(Color.BLACK);
        
        batch.begin();
        map.draw(batch);  // Draw the map first
        batch.end();
    
        stage.act(delta); // Perform the stage actions
        stage.draw();     // Draw the stage
    }
    
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        stage.getCamera().update();
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
