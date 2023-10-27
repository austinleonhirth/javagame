package com.mygdx.roguelike.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameCharacter extends Actor {

    private int moveSpeed = 8;
    public float characterWidth = 2;
    public float characterHeight = 2;

    private int worldHeight;
    private int worldWidth;

    private TextureRegion textureRegion;

    public GameCharacter(Texture texture, int worldHeight, int worldWidth){
        textureRegion = new TextureRegion(texture);

        this.worldHeight = worldHeight;
        this.worldWidth = worldWidth;

        setBounds(getX(), getY(), characterWidth, characterHeight);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        float originX = characterWidth / 2;
        float originY = characterHeight / 2;

        batch.draw(
        textureRegion,      // The texture
        getX(), getY(),     // The x and y position on the screen
        originX, originY,   // The originX and originY point around which rotation will happen (center)
        characterWidth, characterHeight, // The width and the height of the texture
        1, 1,               // The scaleX and scaleY, no scaling is applied here
        -90                 // The rotation of the image, in degrees
    );
    }

    public void act(float delta) {
        super.act(delta);
        //Move based on WASD but bound to the world
        float moveDistance = moveSpeed*delta;

        //Sprint
        if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)){
            moveDistance *= 3;
        }

        //If moving diagonally, move at 70% speed
        if(Gdx.input.isKeyPressed(Keys.W) && Gdx.input.isKeyPressed(Keys.A)){
           moveDistance*=0.7;
        }
        if(Gdx.input.isKeyPressed(Keys.W) && Gdx.input.isKeyPressed(Keys.D)){
           moveDistance*=0.7;
        }
        if(Gdx.input.isKeyPressed(Keys.S) && Gdx.input.isKeyPressed(Keys.A)){
           moveDistance*=0.7;
        }
        if(Gdx.input.isKeyPressed(Keys.S) && Gdx.input.isKeyPressed(Keys.D)){
           moveDistance*=0.7;
        }

        if(Gdx.input.isKeyPressed(Keys.W)){
            moveBy(0, moveDistance);
        }
        if(Gdx.input.isKeyPressed(Keys.A)){
            moveBy(-1*moveDistance, 0);
        }
        if(Gdx.input.isKeyPressed(Keys.S)){
            moveBy(0, -1*moveDistance);
        }
        if(Gdx.input.isKeyPressed(Keys.D)){
            moveBy(moveDistance, 0);
        }
        //Clamp x and y to the world
        setX(MathUtils.clamp(getX(), 0, worldWidth-characterWidth));
        setY(MathUtils.clamp(getY(), 0, worldHeight-characterHeight));

        
    }
}
