package io.github.liamfromgithubreal.testgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;   // add this import and NOT the one in the standard library
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import io.github.liamfromgithubreal.testgame.display.Display;
import io.github.liamfromgithubreal.testgame.logic.GameLogic;
import io.github.liamfromgithubreal.testgame.objects.Audio;
import io.github.liamfromgithubreal.testgame.objects.Batch;
import io.github.liamfromgithubreal.testgame.objects.Player;
import io.github.liamfromgithubreal.testgame.objects.World;

import java.util.Iterator;
import java.util.Random;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    // Instance of the random class
    public static Random rand = new Random();
    GameLogic game;
    Player player;
    Audio audio;
    Batch batch;
    Display display;
    World world;

    @Override
    public void create() {
        player = new Player();
        audio = new Audio();
        display = new Display();
        game = new GameLogic();
        batch = new Batch();
        world = new World();
    }

    @Override
    public void render() {
        display.clear();
        player.move(world);
        batch.spawn(player, audio);
        display.getCamera().update();
        batch.cycle(player, display.getCamera());
    }


    @Override
    public void dispose() {
        player.getSprite().dispose();
        audio.dispose();
        batch.dispose();
    }
}
