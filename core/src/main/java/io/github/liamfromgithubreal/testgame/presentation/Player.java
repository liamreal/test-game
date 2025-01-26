package io.github.liamfromgithubreal.testgame.presentation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import io.github.liamfromgithubreal.testgame.application.Global;

public class Player {
    Rectangle hitbox;
    Texture sprite;
    private int runSpeed = 600;
    private int jumpSpeed = 0;
    public Player() {
        sprite = new Texture(Gdx.files.internal("sprites/bucket/bucket.png"));
        hitbox = new Rectangle();
        hitbox.x = Global.HORIZONTAL_RESOLUTION / 2 - 64 / 2;
        hitbox.y = 20;
        hitbox.width = 64;
        hitbox.height = 64;
    }
    public void update(World world) {
        // gravity acting on player in current world
        fall(world);
        // checks for if on floor of current world or at border
        floorCheck(world);
        borderCheck();
    }
    public void moveLeft(World world) {
        getHitbox().x -= (getRunSpeed() * Gdx.graphics.getDeltaTime());
    }
    public void moveRight(World world) {
        getHitbox().x += (getRunSpeed() * Gdx.graphics.getDeltaTime());
    }
    public void jump() {
        // jump by setting jumpSpeed to be a high positive int
        setJumpSpeed(1500);
    }
    public void fall(World world) {
        getHitbox().y += (getJumpSpeed() + world.getGravitySpeed()) * Gdx.graphics.getDeltaTime();
        setJumpSpeed(getJumpSpeed() + world.getGravitySpeed());
    }
    public void floorCheck(World world) {
        if (getHitbox().y < world.getGroundHeight()) {
            getHitbox().y = world.getGroundHeight();
            setJumpSpeed(0);
        }
    }
    public void borderCheck() {
        if(getHitbox().x < 0) getHitbox().x = 0;
        if(getHitbox().x > Global.HORIZONTAL_RESOLUTION - 64) getHitbox().x = Global.HORIZONTAL_RESOLUTION - 64;
    }


    public int getRunSpeed() {
        return runSpeed;
    }
    public void setRunSpeed(int runSpeed) {this.runSpeed = runSpeed;}

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
