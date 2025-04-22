package objects.entities.enemy.boss.attacking;

import engine.Game;
import engine.Main;
import objects.GameObject;
import objects.entities.Entity;
import objects.entities.player.Player;
import objects.platforms.Platform;
import objects.world.Cell;
import org.lwjgl.opengl.EXTTimerQuery;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import ui.images.ImageRenderer;


public class Cockroach extends Entity{
    private boolean facingRight;
    private boolean onGround;
    private float xAccel;
    private float xVelocity;
    private float yVelocity;
    private float newX;
    private float newY;
    private final float gravity;
    public static boolean isDamaged;
    public static float percentHealth;

    private int timer;
    private int time;
    private int xTimer;
    private int xTime;
    public static int attackDamage;

    private Image leftFacingImage;

    private boolean leftDirection;
    private static double directionater = Math.random();

    public Cockroach(float x, float y){
        super(x, y, Cell.getWidth() * ImageRenderer.screenRatio * 0.5f, Cell.getHeight() * ImageRenderer.screenRatio, 100, 20, ImageRenderer.cockroachIdle);
        this.x = x;
        this.y = y;
        facingRight = false;
        onGround = true;
        xVelocity = 0;
        yVelocity = 0;
        newX = 0;
        newY = 0;
        xAccel = 0;
        gravity = 1;
        attackDamage = attackDmg;
        timer = 5 * 60;
        time = timer;
        xTimer = 2 * 60;
        xTime = xTimer;
        isDamaged = isHit;

        leftFacingImage = rightFacingImage.getFlippedCopy(true, false);
        leftDirection = true;

        xSpeed = 0; //DEBUG
        ySpeed = 0; //DEBUG

        percentHealth = (float) curHealth / maxHealth;
    }

    public void render(Graphics g){
        image.draw(x,y);
        g.setColor(Color.orange);
        g.draw(getBounds());
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta){
        super.update(gc, sbg, delta);

        percentHealth = (float) curHealth / maxHealth;

        if (isDamaged){
            takeDamage(Player.getAttackDamage());
        }

        timer--;
        xTimer--;

        if (xTimer > 0){
            moveLeft();
        }
        if (xTimer < 0){
            moveRight();
        }
        if (xTimer == 0){
            jump();
        }

        if (xTimer < -xTime){
            xTimer = xTime;
        }

        if (timer <= 0){
            jump();
            timer = time;
        }

        yVelocity += gravity;

        newX = x + xVelocity;
        newY = y + yVelocity;

        collisions(sbg);

        x = newX;
        y = newY;
    }

    public void moveLeft(){
        image = leftFacingImage;
        xVelocity = -xSpeed + xAccel;
        xAccel -= 0.025f;
    }

    public void moveRight(){
        image = rightFacingImage;
        xVelocity = xSpeed + xAccel;
        xAccel += 0.025f;
    }

    public void jump(){
        yVelocity = -ySpeed;
        onGround = false;
        timer = time;
    }

    public void collisions(StateBasedGame sbg) {
        onGround = false;
        Rectangle futureX = new Rectangle(newX, y - 1, w, h);
        Rectangle futureY = new Rectangle(newX, newY, w, h);

        for (GameObject o : Game.levelObjects){
            if (o instanceof Platform) {
                if (futureX.intersects(o.getBounds())) {
                    if (onGround) {
                        if (xVelocity > 0) {
                            newX = o.getX() - w;
                        } else if (xVelocity < 0) {
                            newX = o.getX() + o.getW();
                        }
                        xVelocity = 0;
                    }
                    else{
                        if (xVelocity > 0) {
                            newX = x;
                        } else if (xVelocity < 0) {
                            newX = x;
                        }
                        xVelocity = 0;
                    }
                }

                if (futureY.intersects(o.getBounds())){
                    if (yVelocity > 0 && !futureX.intersects(o.getBounds())){
                        newY = o.getY() - h;
                        yVelocity = 0;
                        onGround = true;
                    }
                    else if (yVelocity < 0 && ((Platform) o).isBottomPlatform() && futureX.intersects(o.getBounds())){
                        newY = o.getY() + o.getH();
                        yVelocity = gravity;
                    }
                }
            }
        }
    }

    public static int getAttackDamage(){
        return attackDamage;
    }

    public static float getPercentHealth(){
        return percentHealth;
    }

}