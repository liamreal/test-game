package io.github.liamfromgithubreal.testgame.presentation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import io.github.liamfromgithubreal.testgame.application.Global;

public class Player {
    Rectangle hitbox;
    Texture sprite;
    private int runSpeed = Global.DEFAULT_PLAYER_SPEED; // default speed
    private int jumpSpeed = 0; // 0 means not currently jumping, just still
    public Player() {
        sprite = new Texture(Gdx.files.internal("sprites/bucket/bucket.png"));
        hitbox = new Rectangle();
        hitbox.x = Math.round((float) (Global.HORIZONTAL_RESOLUTION - Global.PLAYER_WIDTH) / 2);
        hitbox.y = Global.PLAYER_HEIGHT;
        hitbox.width = Global.PLAYER_WIDTH;
        hitbox.height = Global.PLAYER_WIDTH;
    }
    // update player on screen
    public void update(World world) {
        // gravity acting on player in current world
        fall(world);
        // checks for if on floor of current world or at border
        floorCheck(world);
        borderCheck();
    }
    // move methods
    public void moveLeft(World world) {
        getHitbox().x -= (getRunSpeed() * Gdx.graphics.getDeltaTime());
    }
    public void moveRight(World world) {
        getHitbox().x += (getRunSpeed() * Gdx.graphics.getDeltaTime());
    }
    // player jumps against world.gravitySpeed
    public void jump() {
        // jump by setting jumpSpeed to be a high positive int
        setJumpSpeed(1500);
    }
    // player falling y-trajectory
    public void fall(World world) {
        getHitbox().y += (getJumpSpeed() + world.getGravitySpeed()) * Gdx.graphics.getDeltaTime();
        setJumpSpeed(getJumpSpeed() + world.getGravitySpeed());
    }
    // check if player on floor, so not falling anymore
    public void floorCheck(World world) {
        if (getHitbox().y < world.getGroundHeight()) {
            getHitbox().y = world.getGroundHeight();
            setJumpSpeed(0);
        }
    }
    // check if player reached world border either side
    public void borderCheck() {
        if(getHitbox().x < 0) getHitbox().x = 0;
        if(getHitbox().x > Global.HORIZONTAL_RESOLUTION - Global.PLAYER_WIDTH) getHitbox().x = Global.HORIZONTAL_RESOLUTION - Global.PLAYER_WIDTH;
    }
    // getters/setters
    public int getRunSpeed() {
        return runSpeed;
    }
    public void setRunSpeed(int runSpeed) { this.runSpeed = runSpeed; }
    public int getJumpSpeed() {
        return jumpSpeed;
    }
    public void setJumpSpeed(int jumpSpeed) {
        this.jumpSpeed = jumpSpeed;
    }
    public Rectangle getHitbox() { return hitbox; }
    public Texture getSprite() {
        return sprite;
    }
}
