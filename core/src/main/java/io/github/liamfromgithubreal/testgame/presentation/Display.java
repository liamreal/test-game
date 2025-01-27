package io.github.liamfromgithubreal.testgame.presentation;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.liamfromgithubreal.testgame.application.Global;

public class Display {
    OrthographicCamera camera = new OrthographicCamera();
    public Display() {
        camera.setToOrtho(false, Global.HORIZONTAL_RESOLUTION, Global.VERTICAL_RESOLUTION);
    }
    // update screen camera
    public void update() {
        clear();
        getCamera().update();
    }
    private void clear() {
        ScreenUtils.clear(0f, 0f, 0.2f, 1f);
    }
    // getters/setters
    public OrthographicCamera getCamera() {
        return camera;
    }
}
