package objects.entities;

import engine.states.Game;
import objects.GameObject;
import objects.interactables.Door;
import objects.platforms.Platform;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public abstract class Entity extends GameObject {
    protected int curHealth;
    protected int maxHealth;
    protected float percentHealth;
    protected int attack;
    protected int xSpeed;
    protected int ySpeed;
    protected Image image;
    protected Image rightFacingImage;
    protected boolean facingRight;
    protected boolean isDead;
    protected boolean isHit;
    protected boolean hasTakenDamage;
    protected int invincibilityFrames;
    protected int invincibilityFrameValue;
    protected float xVelocity;
    protected float yVelocity;
    protected float newX;
    protected float newY;
    protected float gravity;
    protected boolean contactingPlatformSide;
    protected boolean onGround;
    protected float xAccel;

    public Entity(float x, float y, int xSpeed, int ySpeed, int health, int attack, Image image, int iFrames) {
        super(x,y, image);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        maxHealth = health;
        curHealth = maxHealth;
        this.attack = attack;
        this.image = image;
        rightFacingImage = image;
        isDead = false;
        isHit = false;
        hasTakenDamage = false;
        invincibilityFrameValue = iFrames;
        invincibilityFrames = invincibilityFrameValue;
        percentHealth = (float) curHealth / maxHealth;
        xVelocity = 0;
        yVelocity = 0;
        newX = 0;
        newY = 0;
        xAccel = 0;
        gravity = 1;
        contactingPlatformSide = false;
        onGround = true;
    }

    public void render(Graphics g){
        if (isHit){
            if (invincibilityFrames % 7 == 0){

            }
            else{
                image.draw(x,y);
            }
        }
        else{
            image.draw(x,y);
        }

//        g.setColor(Color.white);
//        g.draw(getBounds());
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        if (isHit){
            invincibilityFrames--;
        }

        if (invincibilityFrames <= 0){
            invincibilityFrames = invincibilityFrameValue;
            isHit = false;
        }

        percentHealth = (float) curHealth / maxHealth;

        if (!isHit) hasTakenDamage = false;

        yVelocity += gravity;

        newX = x + xVelocity;
        newY = y + yVelocity;

        collisions(sbg);

        if(percentHealth == 0) {
            isDead = true;
            cell.removeObject();
        }

        x = newX;
        y = newY;
    }

    public void moveLeft(){
        image = rightFacingImage.getFlippedCopy(true, false);
        xVelocity = -xSpeed + xAccel;
        xAccel -= 0.1f;
    }

    public void moveRight(){
        image = rightFacingImage;
        xVelocity = xSpeed + xAccel;
        xAccel += 0.1f;
    }

    public void jump(){
        yVelocity = -ySpeed;
        onGround = false;
    }

    public void collisions(StateBasedGame sbg) {
        onGround = false;
        Rectangle futureX = new Rectangle(newX, y, w, h);
        Rectangle futureY = new Rectangle(newX, newY, w, h);

        for (GameObject o : new ArrayList<>(Game.levelObjects)) {
            if (o instanceof Platform) {
                float playerRight = futureX.getX() + futureX.getWidth();
                float playerLeft = futureX.getX();

                float prevPlayerRight = x + w;
                float prevPlayerLeft = x;

                float platformRight = o.getX() + o.getW();
                float platformLeft = o.getX();

                boolean verticalOverlap = futureX.getY() + futureX.getHeight() > o.getY() && futureX.getY() < o.getY() + o.getH();

                if (futureX.intersects(o.getBounds()) && ((Platform) o).isSidePlatform() && (!((Platform) o).isBottomPlatform() || (((Platform) o).isBottomPlatform() && ((Platform) o).isSidePlatform()))) {
                    if (xVelocity > 0 && playerRight > platformLeft && prevPlayerRight <= platformLeft && verticalOverlap) {
                        newX = o.getX() - getW();
                    }
                    if (xVelocity < 0 && playerLeft < platformRight && prevPlayerLeft >= platformRight && verticalOverlap) {
                        newX = o.getX() + o.getW();
                    }
                    xVelocity = 0;
                }

                if (futureY.intersects(o.getBounds()) && ((Platform) o).isBottomPlatform()) {
                    float platformTop = o.getY();
                    float platformBottom = o.getY() + o.getH();
                    float playerTop = futureY.getY();
                    float playerBottom = futureY.getY() + futureY.getHeight();

                    if (yVelocity > 0 && y + h <= platformTop) {
                        newY = platformTop - h;
                        yVelocity = 0;
                        onGround = true;
                    } else if (yVelocity < 0 && y >= platformBottom) {
                        newY = platformBottom;
                        yVelocity = 0;
                    }
                }
            }

            if (o instanceof Door) {
                float playerRight = futureX.getX() + futureX.getWidth();
                float playerLeft = futureX.getX();

                float prevPlayerRight = x + w;
                float prevPlayerLeft = x;

                float platformRight = o.getX() + o.getW();
                float platformLeft = o.getX();

                boolean verticalOverlap = futureX.getY() + futureX.getHeight() > o.getY() && futureX.getY() < o.getY() + o.getH();

                if (futureX.intersects(o.getBounds())) {
                    if (xVelocity > 0 && playerRight > platformLeft && prevPlayerRight <= platformLeft && verticalOverlap) {
                        newX = o.getX() - getW();
                    }
                    if (xVelocity < 0 && playerLeft < platformRight && prevPlayerLeft >= platformRight && verticalOverlap) {
                        newX = o.getX() + o.getW();
                    }
                    xVelocity = 0;
                }

                if (futureY.intersects(o.getBounds())) {
                    float platformTop = o.getY();
                    float platformBottom = o.getY() + o.getH();
                    float playerTop = futureY.getY();
                    float playerBottom = futureY.getY() + futureY.getHeight();

                    if (yVelocity > 0 && y + h <= platformTop) {
                        newY = platformTop - h;
                        yVelocity = 0;
                        onGround = true;
                    } else if (yVelocity < 0 && y >= platformBottom) {
                        newY = platformBottom;
                        yVelocity = 0;
                    }
                }
            }
        }
    }

    public void takeDamage(int damage) {
        if (invincibilityFrames == invincibilityFrameValue) {
            System.out.println("Taking Damage");
            isHit = true;
            curHealth -= damage;
            if (curHealth <= 0) {
                curHealth = 0;
                isDead = true;
            }
        }
    }

    public boolean isHit(){
        return isHit;
    }

    public boolean isDead(){
        return isDead;
    }

    public float getPercentHealth(){
        return percentHealth;
    }
}