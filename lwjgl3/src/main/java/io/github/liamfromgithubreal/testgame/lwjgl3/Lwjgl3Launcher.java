package io.github.liamfromgithubreal.testgame.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.github.liamfromgithubreal.testgame.Main;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    private static Lwjgl3Application createApplication() {

//        // works, but ONLY shows libGDX logo
//        return new Lwjgl3Application(new Main(), getDefaultConfiguration());



//        return new Lwjgl3Application(new io.github.liamfromgithubreal.testgame.lwjgl3.Drop(), getDefaultConfiguration());
        return new Lwjgl3Application(new Main(), getDefaultConfiguration());

    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {

        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("TestGame");
        configuration.useVsync(true);
        //// Limits FPS to the refresh rate of the currently active monitor.
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate);
        //// If you remove the above line and set Vsync to false, you can get unlimited FPS, which can be
        //// useful for testing performance, but can also be very stressful to some hardware.
        //// You may also need to configure GPU drivers to fully disable Vsync; this can cause screen tearing.

//        // automatically sets resolution based on screen size
//        Graphics.DisplayMode dm = Lwjgl3ApplicationConfiguration.getDisplayMode();
//        configuration.setWindowedMode(dm.width, dm.height);


        configuration.setWindowedMode(800, 640);

        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");

        return configuration;
    }
}
