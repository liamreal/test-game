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
import io.github.liamfromgithubreal.testgame.application.Randomiser;
import io.github.liamfromgithubreal.testgame.logic.Game;

import java.util.Iterator;

public class Batch {
    private SpriteBatch batch;
    private Array<Rectangle> raindrops;
    private Texture dropImage;
    private long lastDropTime;


    public Batch() {
        batch = new SpriteBatch();
//        image = new Texture("libgdx.png");
        // load the images for the droplet and the bucket, 64x64 pixels each
        dropImage = new Texture(Gdx.files.internal("sprites/drop/drop.png"));
        raindrops = new Array<Rectangle>();
        spawnRaindrop();
    }
    public void update(Player player, Audio audio, Display display) {
        spawn();
        collide(player, audio.getDropSounds());
        cycle(player, display.getCamera());
    }
    private void cycle(Player player, OrthographicCamera camera) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(player.getSprite(), player.getHitbox().x, player.getHitbox().y);
        for(Rectangle raindrop: raindrops) {
            batch.draw(dropImage, raindrop.x, raindrop.y);
        }
        batch.end();
    }
    private void spawn() {
        if(TimeUtils.nanoTime() - lastDropTime > 500000000) spawnRaindrop();
    }
    private void collide(Player player, Sound[] sounds) {
        for (Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext(); ) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if(raindrop.y + 64 < 0) iter.remove();
            if(raindrop.overlaps(player.getHitbox())) {
                if (player.getJumpSpeed() < 0) player.setJumpSpeed(1500);
                // this will be used to index the dropSound array for one of 3 possible sounds, so upper bound is 3
                // (from 0 up to but NOT including 3)
                int soundIndex = Randomiser.rand.nextInt(3);
                // play drop sound at random index
                sounds[soundIndex].play();
                // remove rain drop as it collided with bucket
                iter.remove();
            }

        }
    }
    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800-64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }
    public void dispose() {
        dropImage.dispose();
        batch.dispose();
    }

}
