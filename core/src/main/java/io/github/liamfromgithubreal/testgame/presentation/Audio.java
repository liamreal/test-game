package io.github.liamfromgithubreal.testgame.presentation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.GdxRuntimeException;
import io.github.liamfromgithubreal.testgame.application.Global;

import java.util.ArrayList;

public class Audio {
    private ArrayList<Sound> sounds = new ArrayList<>();
    private Music rainMusic;

    public Audio(String soundPath) {
        // find sounds in file based on inputted sound path
        findSounds(sounds, soundPath);

        // load ambience music into memory
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/ambience/rain.mp3"));
        // set playback of music to loop and play immediately
        rainMusic.setLooping(true);
        rainMusic.play();
    }
    private void findSounds(ArrayList<Sound> sounds, String soundPath) {
        // max sounds an interaction can have
        for (int i = 0; i < Global.MAX_SOUNDS; i++) {
            String dropSoundPathTemp = soundPath.replace("#", Integer.toString(i + 1));
            // try adding until no more sound files found
            try {
                Sound dropSound = Gdx.audio.newSound(Gdx.files.internal(dropSoundPathTemp));
                sounds.add(dropSound);
            }
            catch(GdxRuntimeException e) {
                // log caught error to terminal
                System.out.print("Caught GdxRuntimeException exception: " + e.getMessage() + " - No more sounds in path to add, so break out of loop");
                break;
            }
        }
    }
    // getters/setters
    public ArrayList<Sound> getSounds() {
        return sounds;
    }
    // dispose from memory
    public void dispose() {
        for (Sound s : sounds) s.dispose();
        rainMusic.dispose();
    }
}
