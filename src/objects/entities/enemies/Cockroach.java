package objects.entities.enemies;

import engine.Main;
import objects.entities.Entity;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.CockroachValues;
import values.PlayerValues;
import world.Cell;
import world.World;

public class Cockroach extends Entity{
    private float xVelstore;
    private int timer;
    private int xTimer;
    private float jumpTimer;
    private boolean moveLeft;

    public Cockroach(float x, float y){
        super(x, y, CockroachValues.X_SPEED, CockroachValues.Y_SPEED, CockroachValues.HEALTH, CockroachValues.ATTACK, Images.cockroachIdle, CockroachValues.IFRAMES);
        facingRight = false;
        timer = 7 * 60;
        xTimer = 7 * 60;
        jumpTimer = (float) (Math.random() * (float) (3.5 * 60)) + 4;
        xVelstore = xVelocity;
        moveLeft = true;
    }

    public void render(Graphics g){
        super.render(g);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta){
        if (World.level.equals(CockroachValues.LEVEL_SPAWN_LOCATION) && PlayerValues.isPlayerHurtingEnemy){
            isHit = true;
            PlayerValues.isPlayerHurtingEnemy = false;
        }

        if (isHit && !hasTakenDamage){
            takeDamage(PlayerValues.ATTACK);
            hasTakenDamage = true;
            jump();
        }

        if (x > Cell.getWidth() * 3 && moveLeft) {
            moveLeft();
        }
        else{
            moveLeft = false;
            moveRight();
        }
        if (!moveLeft && x > Main.getScreenWidth() - (Cell.getWidth() * 4) - w){
            moveLeft = true;
        }

        if (xAccel >= 3){
            xAccel = 0;
        }

        jumpTimer--;
        if (jumpTimer < 0) {
            jump();
            jumpTimer = (float) (Math.random() * (3.5f * 60f)) + 4f;
        }

        super.update(gc, sbg, delta);
    }

    public void moveLeft(){
        image = rightFacingImage.getFlippedCopy(true, false);
        xVelocity = -(xSpeed + xAccel);
        xAccel += 0.075f;
    }

    public void moveRight(){
        image = rightFacingImage;
        xVelocity = (xSpeed - xAccel);
        xAccel -= 0.075f;
    }

    public void jump() {
        if(jumpTimer > -300) {
            yVelocity = -ySpeed;
            xVelstore = xVelocity;
        }
        else
        {
            yVelocity = 0;
            xVelocity = xVelstore;
        }
        onGround = false;
    }

}