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
    private Image image;
    private SpriteSheet mySheet;
    private Image currentFrame;
    private int step = 0;
    private int frames = 0;
    private int framesPerStep = 6;
    public Cockroach(float x, float y){
        super(x, y, CockroachValues.X_SPEED, CockroachValues.Y_SPEED, CockroachValues.HEALTH, CockroachValues.ATTACK, Images.cockroachIdle, CockroachValues.IFRAMES);
        facingRight = false;
        timer = 7 * 60;
        xTimer = 7 * 60;
        jumpTimer = (float) (Math.random() * (float) (3.5 * 60)) + 4;
        xVelstore = xVelocity;
        moveLeft = true;
        image = Images.cockroachIdle;
        mySheet = Images.cockRoach;
        //mySheet2 = Images.knifeAttack;
        currentFrame = mySheet.getSprite(0,0);
    }

    public void render(Graphics g){
        if(!isDead) {
            super.render(g);
            float renderOffsetY = h - currentFrame.getHeight();
            if (xVelocity == 0 && xAccel == 0) {
                if (facingRight) {
                    image.draw(x, y + h - image.getHeight());
                } else {
                    image.getFlippedCopy(true, false).draw(x, y + h - image.getHeight());
                }
            } else if (facingRight) {
                currentFrame.draw(x, y + renderOffsetY);
            } else {
                currentFrame.getFlippedCopy(true, false).draw(x, y + renderOffsetY);
            }

        }
     //   g.draw(getBounds());
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta){
        if (PlayerValues.isPlayerHurtingEnemy)
        {
            isHit = true;
            PlayerValues.isPlayerHurtingEnemy = false;
            takeDamage(PlayerValues.ATTACK);
        }

//        if (isHit && !hasTakenDamage){
//            takeDamage(PlayerValues.ATTACK);
//            hasTakenDamage = true;
//            jump();
//        }
        frames++;
        if(frames % framesPerStep == 0)
        {
            step++;
        }
        if(step >= mySheet.getHorizontalCount())
        {
            step = 0;
        }
        currentFrame = mySheet.getSprite(step, 0);
        if (x > Cell.getWidth() * 3 && moveLeft) {
            moveLeft();
            facingRight = false;
        }
        else{
            moveLeft = false;
            moveRight();
            facingRight = true;
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