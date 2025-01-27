package io.github.liamfromgithubreal.testgame.presentation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.GdxRuntimeException;
import io.github.liamfromgithubreal.testgame.application.Global;

import java.util.ArrayList;

public class Audio {
    private ArrayList<Sound> dropSounds = new ArrayList<>();
    private Music rainMusic;

    public Audio() {
        String dropSoundPath = new String("sounds/drop/drop_#.mp3");
        // max sounds an interaction can have
        for (int i = 0; i < Global.MAX_SOUNDS; i++) {
            String dropSoundPathTemp = dropSoundPath.replace("#", Integer.toString(i + 1));
            // try adding until no more sound files found
            try {
                Sound dropSound = Gdx.audio.newSound(Gdx.files.internal(dropSoundPathTemp));
                dropSounds.add(dropSound);
            }
            catch(GdxRuntimeException e) {
                // log caught error to terminal
                System.out.print("Caught GdxRuntimeException exception: " + e.getMessage() + " - No more sounds in path to add, so break out of loop");
                break;
            }
        }
        // load ambience music into memory
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/ambience/rain.mp3"));
        // set playback of music to loop and play immediately
        rainMusic.setLooping(true);
        rainMusic.play();
    }
    // getters/setters
    public ArrayList<Sound> getDropSounds() {
        return dropSounds;
    }
    // dispose from memory
    public void dispose() {
        for (Sound s : dropSounds) s.dispose();
        rainMusic.dispose();
    }
}
