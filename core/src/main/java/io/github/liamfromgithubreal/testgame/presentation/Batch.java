package io.github.liamfromgithubreal.testgame.presentation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import io.github.liamfromgithubreal.testgame.application.Global;

import java.util.ArrayList;
import java.util.Iterator;

public class Batch {
    private SpriteBatch batch;
    private Array<Rectangle> raindrops;
    private Texture dropImage;
    private long lastDropTime;


    public Batch() {
        batch = new SpriteBatch();
//        image = new Texture("libgdx.png");
        // load the images for the droplet and the bucket (resolution specified in Global class file)
        dropImage = new Texture(Gdx.files.internal("sprites/drop/drop.png"));
        raindrops = new Array<Rectangle>();
        spawnRaindrop();
    }
    // update batch on screen
    public void update(Player player, Audio sounds, Display display) {
        spawn();
        collide(player, sounds.getSounds());
        cycle(player, display.getCamera());
    }
    // cycling through list of raindrops for batch
    private void cycle(Player player, OrthographicCamera camera) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(player.getSprite(), player.getHitbox().x, player.getHitbox().y);
        for(Rectangle raindrop: raindrops) {
            batch.draw(dropImage, raindrop.x, raindrop.y);
        }
        batch.end();
    }
    // spawn one raindrop after enough time
    private void spawn() {
        if(TimeUtils.nanoTime() - lastDropTime > 500000000) spawnRaindrop();
    }
    // collision of player with any raindrop
    private void collide(Player player, ArrayList<Sound> sounds) {
        for (Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext(); ) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if(raindrop.y + Global.RAINDROP_HEIGHT < 0) iter.remove();
            if(raindrop.overlaps(player.getHitbox())) {
                if (player.getJumpSpeed() < 0) player.setJumpSpeed(1500);
                // this will be used to index the dropSound array for one of 3 possible sounds, so upper bound is 3
                // (from 0 up to but NOT including length of sounds array)
                int soundIndex = Global.RAND.nextInt(sounds.size());
                // loop to only run IF there are sounds in array
                if (soundIndex < sounds.size()) {
                    // play drop sound at random index
                    sounds.get(soundIndex).play();
                }
                // remove rain drop as it collided with bucket
                iter.remove();
            }

        }
    }
    // create new raindrop, add to raindrops iterator, reset lastDropTime to current time (since now spawned a drop)
    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, Global.HORIZONTAL_RESOLUTION - Global.RAINDROP_WIDTH);
        raindrop.y = Global.VERTICAL_RESOLUTION;
        raindrop.width = Global.RAINDROP_WIDTH;
        raindrop.height = Global.RAINDROP_HEIGHT;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }
    // dispose of assets from memory
    public void dispose() {
        dropImage.dispose();
        batch.dispose();
    }
}
