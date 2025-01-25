package io.github.liamfromgithubreal.testgame;
import com.badlogic.gdx.ApplicationAdapter;
import io.github.liamfromgithubreal.testgame.logic.Game;



/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    Game game;
    @Override
    public void create() {
        game = new Game();
    }
    @Override
    public void render() {
        game.render();
    }
    @Override
    public void dispose() {
        game.dispose();
    }
}
