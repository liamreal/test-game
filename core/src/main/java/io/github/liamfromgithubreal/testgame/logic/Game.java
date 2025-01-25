package io.github.liamfromgithubreal.testgame.logic;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.liamfromgithubreal.testgame.presentation.Display;
import io.github.liamfromgithubreal.testgame.presentation.Audio;
import io.github.liamfromgithubreal.testgame.presentation.Batch;
import io.github.liamfromgithubreal.testgame.presentation.Player;
import io.github.liamfromgithubreal.testgame.presentation.World;

import java.util.Random;

// will handle game loops such as input and calculating interactions between different objects
public class Game {
    Player player;
    Audio audio;
    Batch batch;
    Display display;
    World world;
    public Game() {
        player = new Player();
        audio = new Audio();
        display = new Display();
        batch = new Batch();
        world = new World();
    }
    public void render() {
        handleInput();
        display.update();
        player.update(world);
        batch.update(player, audio, display);
    }
    public void dispose() {
        player.getSprite().dispose();
        audio.dispose();
        batch.dispose();
    }
    private void handleInput() {
        // input for left or right (or both at same time which will cause player to stay still)
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) player.moveLeft(world);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) player.moveRight(world);
        // if up input pressed AND bucket is on floor (only groundHeight in world object for now)
        if((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) && (player.getHitbox().y == world.getGroundHeight())) {
            player.jump();
        }
    }
}
