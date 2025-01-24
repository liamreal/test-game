package io.github.liamfromgithubreal.testgame.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Player {

    Rectangle hitbox;
    Texture sprite;


    private int runSpeed = 600;
    private int jumpSpeed = 0;

    public Player() {
        sprite = new Texture(Gdx.files.internal("sprites/bucket/bucket.png"));
        hitbox = new Rectangle();
        hitbox.x = 800 / 2 - 64 / 2;
        hitbox.y = 20;
        hitbox.width = 64;
        hitbox.height = 64;
    }


    public void move(World world) {
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            // // below seems to do nothing??? maybe deletes previous frame sprite?
//            display.getCamera().unproject(touchPos);


//            // no else because if its somehow same as mouse, do nothing
//            if (touchPos.x - 64 / 2 - Math.abs(bucket.x) < 20) {
//                // do nothing if mouse isnt moving and bucket has reached mouse range
//            }

            // this line below calculates the middle of the bucket relative to the mouse (using the width of the bucket)
            // IDEALLY, the width of the bucket would be a variable instead of hard-coded 64 below, will change in
            // future but as of right now will remain hard-coded
            float middleTouchPosX = touchPos.x - 64/2;
            if (middleTouchPosX > getHitbox().x) {
                getHitbox().x += getRunSpeed() * Gdx.graphics.getDeltaTime();
                // this line (same in next statement) prevents the bucket from shaking if it has reached the mouse
                // because it would try to go where the mouse is, would overshoot, would come back, would overshoot and
                // this would loop, making it go right, left, right, left of the stationary mouse pointer
                if (getHitbox().x > middleTouchPosX) getHitbox().x = middleTouchPosX;
            } else if (middleTouchPosX < getHitbox().x) {
                getHitbox().x -= getRunSpeed() * Gdx.graphics.getDeltaTime();
                if (getHitbox().x < middleTouchPosX) getHitbox().x = middleTouchPosX;
            }

//            // moves directly to mouse instead of a bit each frame
//            bucket.x = touchPos.x - 64 / 2;
        }
        // this is here to ensure BOTH keyboard and mouse are not pressed, otherwise can be exploited and both used at
        // same time to make the bucket go much faster
        else {
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) getHitbox().x -= getRunSpeed() * Gdx.graphics.getDeltaTime();
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) getHitbox().x += getRunSpeed() * Gdx.graphics.getDeltaTime();
        }

        if(getHitbox().x < 0) getHitbox().x = 0;
        if(getHitbox().x > 800 - 64) getHitbox().x = 800 - 64;

        // if up input pressed AND bucket is on floor (y-level = 10 ...for now)
        if((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) && (getHitbox().y == 10)) {
            // jump by setting jumpSpeed to be a high positive int
            setJumpSpeed(1500);
        }

        getHitbox().y += (getJumpSpeed() + world.getGravitySpeed()) * Gdx.graphics.getDeltaTime();
        setJumpSpeed(getJumpSpeed() + world.getGravitySpeed());

        if (getHitbox().y < 10) {
            getHitbox().y = 10;
            setJumpSpeed(0);
        }
    }


    public int getRunSpeed() {
        return runSpeed;
    }

    public int getJumpSpeed() {
        return jumpSpeed;
    }
    public void setJumpSpeed(int jumpSpeed) {
        this.jumpSpeed = jumpSpeed;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public Texture getSprite() {
        return sprite;
    }
}
