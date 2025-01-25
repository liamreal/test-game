package io.github.liamfromgithubreal.testgame.presentation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Audio {

    private Sound[] dropSounds = new Sound[3];
    private Music rainMusic;

    public Audio() {
        String dropSoundPath = new String("sounds/drop/drop_#.mp3");
        for (int i = 0; i < 3; i++) {
            String dropSoundPathTemp = dropSoundPath.replace("#", Integer.toString(i + 1));
            Sound dropSound = Gdx.audio.newSound(Gdx.files.internal(dropSoundPathTemp));
            dropSounds[i] = dropSound;
        }


        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/ambience/rain.mp3"));

        // start the playback of the background music immediately
        rainMusic.setLooping(true);
        rainMusic.play();

    }

    public Sound[] getDropSounds() {
        return dropSounds;
    }


    public void dispose() {
        for (Sound s : dropSounds) s.dispose();
        rainMusic.dispose();
    }
}
