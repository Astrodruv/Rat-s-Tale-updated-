package objects.entities.player;

import engine.Game;
import engine.Main;
import objects.GameObject;
import objects.entities.Entity;
import objects.entities.enemy.boss.attacking.Cockroach;
import objects.interactables.Key;
import objects.interactables.Lock;
import objects.platforms.Platform;
import objects.world.Cell;
import objects.world.World;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import ui.images.ImageRenderer;

import java.util.ArrayList;


public class Player extends Entity {

    private boolean facingRight;
    private boolean onGround;
    private float xAccel;
    private float xVelocity;
    private float yVelocity;
    private float newX;
    private float newY;
    private final float gravity;
    public static int attackDamage;
    public boolean jumpingOffOfEnemy;
    public static float percentHealth;
    public float cooldown;
    public boolean canAttack;
    public boolean attack;
    public boolean sidePlatformCollision;

    public boolean contactingPlatformSide;

    public static boolean keyAttained;

    public Player(float x, float y) {
        super(x,y,Cell.getWidth() * ImageRenderer.screenRatio * 0.55f, Cell.getHeight() * ImageRenderer.screenRatio * 0.6f,100,10, ImageRenderer.ratIdle);
        this.x = x;
        this.y = y;
        if (Main.getScreenWidth() == 2256){
            xSpeed = 5 * ImageRenderer.screenRatio * 5;
            ySpeed = Cell.getHeight() * ImageRenderer.screenRatio * 0.75f;
        }
        facingRight = true;
        onGround = true;
        xVelocity = 0;
        yVelocity = 0;
        attackDamage = attackDmg;
        newX = 0;
        newY = 0;
        xAccel = 0;
        gravity = 1;
        jumpingOffOfEnemy = false;

        keyAttained = true; // DEBUG
        percentHealth = (float) curHealth / maxHealth;
        contactingPlatformSide = false;

        cooldown = 90;
        canAttack = false;
        attack = false;

        sidePlatformCollision = false;
    }

    public void render(Graphics g){
        g.setColor(Color.orange);
        if (isHit){
            if (invincibilityFrames % 7 == 0){

            }
            else{
                image.draw(x,y);
                g.setColor(Color.orange);
                g.draw(getBounds());
                g.draw(getWeaponBounds(facingRight));
            }
        }
        else{
            image.draw(x,y);

            g.draw(getBounds());
            g.draw(getWeaponBounds(facingRight));
        }

        g.drawString("YVel "+ yVelocity, 600, 500);
        g.drawString("On Ground "+ onGround, 600, 550);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta){
        super.update(gc, sbg, delta);

        percentHealth = (float) curHealth / maxHealth;

        Input input = gc.getInput();

        cooldown--;
        if(cooldown < 0){
            canAttack = true;
        }

        if (input.isKeyDown(Input.KEY_D) && !contactingPlatformSide){
            moveRight();
            facingRight = true;
        }
        else if (input.isKeyDown(Input.KEY_A) && !contactingPlatformSide){
            moveLeft();
            facingRight = false;
        }
        else{
            if (xAccel > 0){
                xVelocity = xAccel;
                xAccel--;
                if (xAccel < 0) xAccel = 0;
            }
            else if (xAccel < 0){
                xVelocity = xAccel;
                xAccel++;
                if (xAccel > 0) xAccel = 0;
            }
            else{
                xVelocity = 0;
                xAccel = 0;
            }
        }

        if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_A)){
            contactingPlatformSide = false; // Fix the phasing of platforms (Maybe make a cap for acceleration)
        }

        sidePlatformCollision = false;

        if (input.isKeyDown(Input.KEY_W) && onGround && !jumpingOffOfEnemy){
            jump();
        }

        yVelocity += gravity;

        newX = x + xVelocity;
        newY = y + yVelocity;

        collisions(sbg);

        if(isDead){
            cell.removeObject();
            percentHealth = 0;
        }

        x = newX;
        y = newY;
    }

    public void moveLeft(){
        xVelocity = -xSpeed + xAccel;
        xAccel -= 0.1f;
    }

    public void moveRight(){
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

        for (GameObject o : new ArrayList<>(Game.levelObjects)){
            if (o instanceof Platform) {
                float playerRight = futureX.getX() + futureX.getWidth();
                float playerLeft = futureX.getX();

                float prevPlayerRight = x + w;
                float prevPlayerLeft = x;

                float platformRight = o.getX() + o.getW();
                float platformLeft = o.getX();

                boolean verticalOverlap = futureX.getY() + futureX.getHeight() > o.getY() && futureX.getY() < o.getY() + o.getH();

                if (futureX.intersects(o.getBounds()) && ((Platform) o).isSidePlatform() && (!((Platform) o).isBottomPlatform() || (((Platform) o).isBottomPlatform() && ((Platform) o).isSidePlatform()))) {
                    if (xVelocity > 0 && playerRight > platformLeft && prevPlayerRight <= platformLeft && verticalOverlap){
                        newX = o.getX() - getW();
                    }
                    if (xVelocity < 0 && playerLeft < platformRight && prevPlayerLeft >= platformRight && verticalOverlap){
                        newX = o.getX() + o.getW();
                    }
                    xVelocity = 0;
                }

                if (futureY.intersects(o.getBounds()) && ((Platform) o).isBottomPlatform()){
                    float platformTop = o.getY();
                    float platformBottom = o.getY() + o.getH();
                    float playerTop = futureY.getY();
                    float playerBottom = futureY.getY() + futureY.getHeight();

                    if (yVelocity > 0 && y + h <= platformTop) {
                        newY = platformTop - h;
                        yVelocity = 0;
                        onGround = true;
                    }

                    else if (yVelocity < 0 && y >= platformBottom) {
                        newY = platformBottom;
                        yVelocity = 0;
                    }
//                    if (yVelocity > 0) {
//                        newY = o.getY() - getH();
//                        yVelocity = 0;
//                        onGround = true;
//                    }
//                    if (yVelocity < 0) {
//                        newY = o.getY() + o.getH();
//                    }
                }
            }

            if (o instanceof Lock){
                if (!keyAttained){
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

                    if (futureY.intersects(o.getBounds())) {
                        if (yVelocity > 0 && !futureX.intersects(o.getBounds())) {
                            newY = o.getY() - h;
                            yVelocity = 0;
                            onGround = true;
//                            if (jumpingOffOfEnemy) jumpingOffOfEnemy = false;
                        }
                    }
                }
                else{
                    if (getBounds().intersects(o.getBounds()) && Player.keyAttained) {
                        if (World.level.equals("levels/sewer1.txt")){
                            Game.setLevel("levels/sewer2.txt");
                            keyAttained = false;
                        }
                    }
                    if (getBounds().intersects(o.getBounds()) && Player.keyAttained) {
                        if (World.level.equals("levels/sewer2.txt")){
                            Game.setLevel("levels/sewer3.txt");
                        }
                    }
                }
            }
            if(Cockroach.isDead){
                keyAttained = true;
                if (World.level.equals("levels/sewer3.txt")){
                    Game.setLevel("levels/sewer4.txt");
                }
            }

            if (o instanceof Key){
                if (getBounds().intersects(o.getBounds())) {
                    keyAttained = true;
                }
            }

            if (o instanceof Cockroach){
                Rectangle weaponBounds = getWeaponBounds(facingRight);
                if (futureY.intersects(o.getBounds())) {
                    if (futureY.getMaxY() <= o.getBounds().getMinY() + 30 && futureY.getMinY() < o.getBounds().getMinY()) {
                        isHit = false;
                        Cockroach.isDamaged = true;
                        onGround = true;
                        jumpingOffOfEnemy = true;
                        jump();
                        jumpingOffOfEnemy = false;
                        if(!Cockroach.groundCheck()) {
                            takeDamage(Cockroach.attackDamage);
                        }
                    }
                    else{
                        takeDamage(Cockroach.attackDamage);
                        System.out.println("Damage");
                    }
                }
                if(weaponBounds.intersects(o.getBounds())) {
                    if(attack) {
                        Cockroach.isDamaged = true;
                        attack = false;
                    }
                }
            }

        }
    }

    public void keyPressed(int key, char c){
        if (key == Input.KEY_D && !facingRight){
            image = rightFacingImage;
            xAccel = 0;
        }
        if (key == Input.KEY_A && facingRight){
            xAccel = 0;
            image = image.getFlippedCopy(true, false);
        }
        if(key == Input.KEY_SPACE && canAttack)
        {
            attack = true;
            cooldown = 90;
            canAttack = false;
        }
    }

    public float newX(){
        return newX;
    }

    public float newY(){
        return newY;
    }

    public static int getAttackDamage(){
        return attackDamage;
    }

    public static float getPercentHealth(){
        return percentHealth;
    }

}
