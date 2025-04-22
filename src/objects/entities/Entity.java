package objects.entities;

import objects.GameObject;
import objects.platforms.Platform;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Entity extends GameObject {
    protected int curHealth;
    protected int maxHealth;
    protected int attackDmg;
    protected float xSpeed;
    protected float ySpeed;
    protected Image image;
    protected Image rightFacingImage;
    protected boolean isDead;
    protected boolean isHit;
    protected int invincibilityFrames;
    protected final int invincibilityFrameValue = 120;

    public Entity(float x, float y, float xSpeed, float ySpeed, int health, int attackDamage, Image image) {
        super(x,y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        maxHealth = health;
        curHealth = maxHealth;
        this.attackDmg = attackDamage;
        this.image = image;
        rightFacingImage = image;
        isDead = false;
        isHit = false;
        invincibilityFrames = invincibilityFrameValue;

        w = image.getWidth();
        h = image.getHeight();

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        if (isHit){
            invincibilityFrames--;
        }

        if (invincibilityFrames <= 0){
            invincibilityFrames = invincibilityFrameValue;
            isHit = false;
        }
    }

    public void takeDamage(int damage){
        if (invincibilityFrames == invincibilityFrameValue) {
            isHit = true;
            curHealth -= damage;
            if (curHealth <= 0) {
                curHealth = 0;
                isDead = true;
            }
        }
    }

}
