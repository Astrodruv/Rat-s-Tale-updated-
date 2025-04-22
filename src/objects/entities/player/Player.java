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

        cooldown = 120;
        canAttack = false;
        attack = false;
    }

    public void render(Graphics g){
        g.setColor(Color.darkGray);
        image.draw(x,y);
        g.setColor(Color.orange);
        g.draw(getBounds());
        g.draw(getWeaponBounds(facingRight));
        g.drawString(""+maxHealth, 500, 500);
        g.drawString(""+curHealth, 500, 600);
        g.drawString(""+getPercentHealth(), 500, 700);
        g.drawString(""+Cockroach.getAttackDamage(), 500, 800);
        g.drawString(""+cooldown, 700, 500);
        g.drawString(""+canAttack, 700, 700);
        g.drawString(""+attack, 700, 900);

        g.drawString(""+invincibilityFrameValue, 500, 900);
        g.drawString(""+invincibilityFrames, 500, 1000);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta){
        super.update(gc, sbg, delta);

        percentHealth = (float) curHealth / maxHealth;

        Input input = gc.getInput();

        cooldown--;
        if(cooldown < 0)
        {
            canAttack = true;
        }

        if (input.isKeyDown(Input.KEY_D)){
            moveRight();
            facingRight = true;
        }
        else if (input.isKeyDown(Input.KEY_A)){
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

        if (input.isKeyDown(Input.KEY_W) && onGround && !jumpingOffOfEnemy){
            jump();
        }

        if(input.isKeyDown(Input.KEY_SPACE) && canAttack)
        {
            attack = true;
            collisions(sbg);
            cooldown = 180;
        }

        yVelocity += gravity;

        newX = x + xVelocity;
        newY = y + yVelocity;

        collisions(sbg);

        if(isDead)
        {
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
        Rectangle futureX = new Rectangle(newX, y - 1, w, h);
        Rectangle futureY = new Rectangle(newX, newY, w, h);

        for (GameObject o : new ArrayList<>(Game.levelObjects)){
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
                    if (yVelocity > 0 && !futureX.intersects(o.getBounds())) {
                        newY = o.getY() - h;
                        yVelocity = 0;
                        onGround = true;
                    } else if (yVelocity < 0 && ((Platform) o).isBottomPlatform() && futureX.intersects(o.getBounds())) {
                        newY = o.getY() + o.getH();
                        yVelocity = gravity;
                    }
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
                        }
                    }
                }
            }

            if (o instanceof Key){
                if (getBounds().intersects(o.getBounds())) {
                    keyAttained = true;
                }
            }

            if (o instanceof Cockroach){
                Rectangle ratBounds = getBounds();
                Rectangle oBounds = o.getBounds();
                Rectangle weaponBounds = getWeaponBounds(facingRight);

                if (ratBounds.intersects(oBounds)) {
                    if (ratBounds.getMaxY() <= oBounds.getMinY() + 5 && ratBounds.getMinY() < oBounds.getMinY()) {
                        Cockroach.isDamaged = true;
                        onGround = true;
                        jumpingOffOfEnemy = true;
                        jump();
                        jumpingOffOfEnemy = false;
                    }
                    else{
                        takeDamage(Cockroach.attackDamage);
                        System.out.println("Damage");
                    }
                }
                if(weaponBounds.intersects(oBounds))
                {
                    if(attack)
                    {
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
