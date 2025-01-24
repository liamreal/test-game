package io.github.liamfromgithubreal.testgame.logic;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import io.github.liamfromgithubreal.testgame.display.Display;
import io.github.liamfromgithubreal.testgame.objects.Player;
import io.github.liamfromgithubreal.testgame.objects.World;

// will handle game loops such as input and calculating interactions between different objects
public class GameLogic {

    public GameLogic() {

    }

    public void input(Player player, Display display, World world) {
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            display.getCamera().unproject(touchPos);


//            // no else because if its somehow same as mouse, do nothing
//            if (touchPos.x - 64 / 2 - Math.abs(bucket.x) < 20) {
//                // do nothing if mouse isnt moving and bucket has reached mouse range
//            }

            // this line below calculates the middle of the bucket relative to the mouse (using the width of the bucket)
            // IDEALLY, the width of the bucket would be a variable instead of hard-coded 64 below, will change in
            // future but as of right now will remain hard-coded
            float middleTouchPosX = touchPos.x - 64/2;
            if (middleTouchPosX > player.getHitbox().x) {
                player.getHitbox().x += player.getRunSpeed() * Gdx.graphics.getDeltaTime();
                // this line (same in next statement) prevents the bucket from shaking if it has reached the mouse
                // because it would try to go where the mouse is, would overshoot, would come back, would overshoot and
                // this would loop, making it go right, left, right, left of the stationary mouse pointer
                if (player.getHitbox().x > middleTouchPosX) player.getHitbox().x = middleTouchPosX;
            } else if (middleTouchPosX < player.getHitbox().x) {
                player.getHitbox().x -= player.getRunSpeed() * Gdx.graphics.getDeltaTime();
                if (player.getHitbox().x < middleTouchPosX) player.getHitbox().x = middleTouchPosX;
            }

//            // moves directly to mouse instead of a bit each frame
//            bucket.x = touchPos.x - 64 / 2;
        }
        // this is here to ensure BOTH keyboard and mouse are not pressed, otherwise can be exploited and both used at
        // same time to make the bucket go much faster
        else {
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) player.getHitbox().x -= player.getRunSpeed() * Gdx.graphics.getDeltaTime();
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) player.getHitbox().x += player.getRunSpeed() * Gdx.graphics.getDeltaTime();
        }

        if(player.getHitbox().x < 0) player.getHitbox().x = 0;
        if(player.getHitbox().x > 800 - 64) player.getHitbox().x = 800 - 64;

        // if up input pressed AND bucket is on floor (y-level = 10 ...for now)
        if((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) && (player.getHitbox().y == 10)) {
            // jump by setting jumpSpeed to be a high positive int
            player.setJumpSpeed(1500);
        }

        player.getHitbox().y += (player.getJumpSpeed() + world.getGravitySpeed()) * Gdx.graphics.getDeltaTime();
        player.setJumpSpeed(player.getJumpSpeed() + world.getGravitySpeed());

        if (player.getHitbox().y < 10) {
            player.getHitbox().y = 10;
            player.setJumpSpeed(0);
        }
    }

}
