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
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.roguelike.AustinsGame;

public class GameScreen implements Screen{

    //Testing

    static final int WORLD_WIDTH = 100;
    static final int WORLD_HEIGHT = 100;

    static final int camSpeed = 15;
    static final float camZoomSpeed = 1f;

    static final int camViewportWidth = 30;
    static final int camViewportHeight = 30;
    
    private OrthographicCamera cam;
    private Sprite map;
    private SpriteBatch batch;
    private float rotationSpeed;

    public GameScreen(AustinsGame game) {

        rotationSpeed = 0.5f;

        map = new Sprite(new Texture(Gdx.files.internal("test_map.png")));
        map.setPosition(0, 0);
        map.setSize(WORLD_WIDTH, WORLD_HEIGHT);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        cam = new OrthographicCamera(camViewportWidth, camViewportHeight * (h / w));
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();

        batch = new SpriteBatch();

    }

    @Override
    public void show() {
  
    }

    @Override
    public void render(float delta) {

        handleCameraInput(delta);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        map.draw(batch);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        cam.viewportWidth = camViewportWidth;                 
		cam.viewportHeight = camViewportHeight * height/width; 
		cam.update();
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

    }

    public void handleCameraInput(float delta){
        /*
         * handles Camera zoom and translation
         * zoom in = page up
         * zoom out = page down
         * 
         * arrow keys translate
        */

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            cam.translate(-1*camSpeed*delta, 0, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            cam.translate(camSpeed*delta, 0, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            cam.translate(0, camSpeed*delta, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            cam.translate(0, -1*camSpeed*delta, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.PAGE_UP)){
            cam.zoom -= camZoomSpeed*delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.PAGE_DOWN)){
            cam.zoom += camZoomSpeed*delta;
        }
        
        cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, WORLD_WIDTH/cam.viewportWidth);

		float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
		float effectiveViewportHeight = cam.viewportHeight * cam.zoom;

		cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, WORLD_WIDTH - effectiveViewportWidth / 2f);
		cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, WORLD_HEIGHT - effectiveViewportHeight / 2f);
    }
    
}
