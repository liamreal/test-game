package io.github.liamfromgithubreal.testgame.presentation;

public class World {

    private int gravitySpeed = -100;
    private int groundHeight = 10;

    public World() {

    }
    // if want to initialise with custom gravity speed
    public World(int gravitySpeed) {
        this.gravitySpeed = gravitySpeed;
    }
    // getters/setters
    public int getGravitySpeed() {
        return gravitySpeed;
    }
    public int getGroundHeight() {
        return groundHeight;
    }
    public void setGroundHeight(int groundHeight) {
        this.groundHeight = groundHeight;
    }
}
