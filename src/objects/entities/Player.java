package objects.entities;

import engine.states.Game;
import objects.GameObject;
import objects.entities.enemies.*;
import objects.entities.enemies.PassiveCar;
import objects.interactables.Door;
import objects.interactables.Key;
import objects.interactables.Weapon;
import objects.platforms.Platform;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import values.*;
import world.Cell;
import world.World;
import ui.Images;

import java.util.ArrayList;

public class Player extends Entity {

    private boolean jumpingOffOfEnemy;
    private float weaponCooldown;
    private boolean canAttack;
    private boolean attack;

    private SpriteSheet mySheet;
    private Image currentFrame;
    private int step = 0;
    private int frames = 0;
    private int framesPerStep = 6;

    private float trapTime;
    private float temp;
    private boolean trapped;

    public static boolean gameOver;

    public Player(float x, float y) {
        super(x,y, PlayerValues.X_SPEED, PlayerValues.Y_SPEED, PlayerValues.HEALTH, PlayerValues.ATTACK, Images.ratIdle, PlayerValues.IFRAMES);
        facingRight = true;
        onGround = true;
        gameOver = false;
        jumpingOffOfEnemy = false;
        weaponCooldown = 30;
        canAttack = false;
        attack = false;
        trapped = false;

        trapTime = 0;
        temp = xSpeed;

        mySheet = Images.rat;
        currentFrame = mySheet.getSprite(0,0);
    }

    public void render(Graphics g){
        float renderOffsetY = 0; // h - currentFrame.getHeight()

        if (isHit){
            if (invincibilityFrames % 7 == 0){

            }
            else{
                if (xVelocity == 0 && xAccel == 0) {
                    if (facingRight) {
                        w = (float) (Cell.getWidth() * 2.5);
                        h = Cell.getHeight() + 10;
                        rightFacingImage.draw(x, y + h - image.getHeight());
                    } else {
                        w = (float) (Cell.getWidth() * 2.5);
                        h = Cell.getHeight() + 10;
                        rightFacingImage.getFlippedCopy(true, false).draw(x, y + h - image.getHeight());
                    }
                } else if (facingRight) {
                    w = Cell.getWidth() * 4;
                    h = Cell.getHeight() / 3 * 2;
                    currentFrame.draw(x - (Cell.getWidth() * 3 / 2),y + Cell.getHeight() + 10 - (Cell.getHeight() * 4 / 3));
                } else {
                    w = Cell.getWidth() * 4;
                    h = Cell.getHeight() / 3 * 2;
                    currentFrame.getFlippedCopy(true, false).draw(x,y + Cell.getHeight() + 10 - (Cell.getHeight() * 4 / 3));
                }
            }
        }
        else{
            if (xVelocity == 0 && xAccel == 0) {
                if (facingRight) {
                    w = (float) (Cell.getWidth() * 2.5);
                    h = Cell.getHeight() + 10;
                    rightFacingImage.draw(x, y + h - image.getHeight());
                } else {
                    w = (float) (Cell.getWidth() * 2.5);
                    h = Cell.getHeight() + 10;
                    rightFacingImage.getFlippedCopy(true, false).draw(x, y + h - image.getHeight());
                }
            } else if (facingRight) {
                w = Cell.getWidth() * 4;
                h = Cell.getHeight() / 3 * 2;
                currentFrame.draw(x,y + Cell.getHeight() + 10 - (Cell.getHeight() * 4 / 3));
            } else {
                w = Cell.getWidth() * 4;
                h = Cell.getHeight() / 3 * 2;
                currentFrame.getFlippedCopy(true, false).draw(x,y + Cell.getHeight() + 10 - (Cell.getHeight() * 4 / 3));
            }
        }

        if (PlayerValues.doesPlayerHaveKnife && PlayerValues.isPlayerHoldingKnife) {
            g.fillRect(x, y - 50, (w + 50), 10);
            g.setColor(Color.yellow);
            if (weaponCooldown > 0) {
                g.fillRect(x, y - 50, (w + 50) * ((30 - weaponCooldown) / 30), 10);
            } else {
                g.setColor(Color.white);
                g.fillRect(x, y - 50, w + 50, 10);
            }
        }

        if(trapped)
        {
            g.fillRect(x, y - 65, (w + 50), 10);
            g.setColor(Color.red);
            if (trapTime > 0) {
                g.fillRect(x, y - 65, (w + 50) * ((240 - trapTime) / 240), 10);
            } else {
                g.setColor(Color.white);
                g.fillRect(x, y - 65, w + 50, 10);
            }
        }

        g.setColor(Color.white);
        g.draw(getBounds());
        g.draw(getWeaponBounds(facingRight));
//
//        g.drawString("DashTime: " + dashTimer, 500, 500);
//        g.drawString("DashCool: " + dashCooldown, 500, 525);
//        g.drawString("xAccel: " + xAccel, 500, 550);
//        g.drawString("CellWidth: " + Cell.getWidth(), 500, 575);

//        g.drawString("canAttack: " + canAttack, 500, 500);
//        g.drawString("attack: " + attack, 500, 550);
//        g.drawString("Is player using weapon: " + PlayerValues.isPlayerUsingKnife, 500, 600);
//        g.drawString("Weapon cooldown: " + weaponCooldown, 500, 650);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta){
        Input input = gc.getInput();

        weaponCooldown--;

        if(weaponCooldown < 0){
            canAttack = true;
        }

        if(weaponCooldown < 25){
//            PlayerValues.isPlayerUsingKnife = false;
        }

        trapTime--;
        if(trapTime > 0)
        {
            trapped = true;
            xSpeed /= 2;
            trapTime--;
        }
        else {
            xSpeed = (int) temp;
        }

        if (isDead){
            gameOver = true;
        }
        if (input.isKeyDown(Input.KEY_S) && dashTimer > 0){
            dash(facingRight);
        }
        else {
            if (input.isKeyDown(Input.KEY_D)) { // && !contactingPlatformSide
                moveRight();
                facingRight = true;
            } else if (input.isKeyDown(Input.KEY_A)) { // && !contactingPlatformSide
                moveLeft();
                facingRight = false;
            } else {
                if (xAccel > 0) {
                    xVelocity = xAccel;
                    xAccel--;
                    if (xAccel < 0) xAccel = 0;
                } else if (xAccel < 0) {
                    xVelocity = xAccel;
                    xAccel++;
                    if (xAccel > 0) xAccel = 0;
                } else {
                    xVelocity = 0;
                    xAccel = 0;
                }
            }
        }

        dashTimer--;
        dashCooldown--;

        frames++;
        if(frames % framesPerStep == 0)
        {
            step++;
        }
        if(step >= mySheet.getHorizontalCount())
        {
            step = 0;
        }
        currentFrame = mySheet.getSprite(step, 0).getScaledCopy(((int) (Cell.getWidth() * 4)), (int) Cell.getHeight() / 3 * 2);

        if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_A)){
            contactingPlatformSide = false;
        }

        if (input.isKeyDown(Input.KEY_W) && onGround && !jumpingOffOfEnemy){
            jump();
        }

        super.update(gc, sbg, delta);
    }

    public void collisions(StateBasedGame sbg) {
        onGround = false;
        Rectangle futureX = new Rectangle(newX, y, w, h);
        Rectangle futureY = new Rectangle(newX, newY, w, h);

        for (GameObject o : new ArrayList<>(Game.levelObjects)){
            if (o instanceof Platform) {
                float playerRight = futureX.getX() + futureX.getWidth();
                float playerLeft = futureX.getX();

                float prevPlayerRight = getX() + getW();
                float prevPlayerLeft = getX();

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
                    } else if (yVelocity < 0 && y >= platformBottom) {
                        newY = platformBottom;
                        yVelocity = 0;
                    }
                }
            }

            if (o instanceof Door){
                if (!PlayerValues.isPlayerTouchingKey){
                    float playerRight = futureX.getX() + futureX.getWidth();
                    float playerLeft = futureX.getX();

                    float prevPlayerRight = x + w;
                    float prevPlayerLeft = x;

                    float platformRight = o.getX() + o.getW();
                    float platformLeft = o.getX();

                    boolean verticalOverlap = futureX.getY() + futureX.getHeight() > o.getY() && futureX.getY() < o.getY() + o.getH();

                    if (futureX.intersects(o.getBounds())) {
                        if (xVelocity > 0 && playerRight > platformLeft && prevPlayerRight <= platformLeft && verticalOverlap){
                            newX = o.getX() - getW();
                        }
                        if (xVelocity < 0 && playerLeft < platformRight && prevPlayerLeft >= platformRight && verticalOverlap){
                            newX = o.getX() + o.getW();
                        }
                        xVelocity = 0;
                    }
                }
                else{
                    if (getBounds().intersects(o.getBounds()) && PlayerValues.isPlayerTouchingKey) {
                        switch (World.level) {
                            case "levels/sewer1.txt" -> {
                                Game.setLevel("levels/sewer2.txt");
                                if (!PlayerValues.keyOnPermanentlySetting) {
                                    PlayerValues.isPlayerTouchingKey = false;
                                }
                                continue;
                            }
                            case "levels/sewer2.txt" -> {
                                Game.setLevel("levels/sewer3.txt");
                                if (!PlayerValues.keyOnPermanentlySetting) {
                                    PlayerValues.isPlayerTouchingKey = false;
                                }
                                continue;
                            }
                            case "levels/sewer3.txt" -> {
                                Game.setLevel("levels/sewer4.txt");
                                if (!PlayerValues.keyOnPermanentlySetting) {
                                    PlayerValues.isPlayerTouchingKey = false;
                                }
                                continue;
                            }
                            case "levels/sewer4.txt" -> {
                                Game.setLevel("levels/street1.txt");
                                PlayerValues.section++;
                                if (!PlayerValues.keyOnPermanentlySetting) {
                                    PlayerValues.isPlayerTouchingKey = false;
                                }
                                continue;
                            }
                            case "levels/street1.txt" -> {
                                Game.setLevel("levels/street2.txt");
                                if (!PlayerValues.keyOnPermanentlySetting) {
                                    PlayerValues.isPlayerTouchingKey = false;
                                }
                                continue;
                            }
                            case "levels/street2.txt" -> {
                                Game.setLevel("levels/street3.txt");
                                if (!PlayerValues.keyOnPermanentlySetting) {
                                    PlayerValues.isPlayerTouchingKey = false;
                                }
                                continue;
                            }
                            case "levels/street3.txt" -> {
                                Game.setLevel("levels/street4.txt");
                                if (!PlayerValues.keyOnPermanentlySetting) {
                                    PlayerValues.isPlayerTouchingKey = false;
                                }
                                continue;
                            }
                            case "levels/street4.txt" -> {
                                Game.setLevel("levels/street5.txt");
                                if (!PlayerValues.keyOnPermanentlySetting) {
                                    PlayerValues.isPlayerTouchingKey = false;
                                }
                                continue;
                            }
                            case "levels/street5.txt" -> {
                                Game.setLevel("levels/closet1.txt");
                                PlayerValues.section++;
                                if (!PlayerValues.keyOnPermanentlySetting) {
                                    PlayerValues.isPlayerTouchingKey = false;
                                }
                                continue;
                            }
                        }
                    }
                }
            }

            if (o instanceof Key){
                if (getBounds().intersects(o.getBounds())) {
                    PlayerValues.isPlayerTouchingKey = true;
                }
            }

            if (o instanceof Weapon){
                if (World.level.equals("levels/sewer4.txt")) {
                    if (getBounds().intersects(o.getBounds())) {
                        PlayerValues.doesPlayerHaveKnife = true;
                        PlayerValues.isPlayerTouchingKey = true;
                    }
                }
            }

            if (o instanceof Cockroach){
                if (futureY.intersects(o.getBounds()) && !(((Cockroach) o).isDead)) {
                    if (futureY.getMaxY() <= o.getBounds().getMinY() + 30 && futureY.getMinY() < o.getBounds().getMinY()) {
                        PlayerValues.isPlayerHurtingEnemy = true;
                        onGround = true;
                        jumpingOffOfEnemy = true;
                        jump();
                        jumpingOffOfEnemy = false;
                    } else {
                        takeDamage(CockroachValues.ATTACK);
                    }
                }
                if(((Cockroach) o).isDead()){
                    if (World.level.equals(CockroachValues.LEVEL_SPAWN_LOCATION)){
                        PlayerValues.isPlayerTouchingKey = true;
                    }
                }
            }

            if (o instanceof Bird){
                Rectangle weaponBounds = getWeaponBounds(facingRight);
                if (futureY.intersects(o.getBounds()) && !(((Bird) o).isDead)) {
                    if (futureY.getMaxY() <= o.getBounds().getMinY() + 30 && futureY.getMinY() < o.getBounds().getMinY()) {
                        PlayerValues.isPlayerHurtingEnemy = true;
                        onGround = true;
                        jumpingOffOfEnemy = true;
                        jump();
                        jumpingOffOfEnemy = false;
                    }
                    else{
                        takeDamage(BirdValues.ATTACK);
                    }
                }
                if(weaponBounds.intersects(o.getBounds()) && PlayerValues.isPlayerHoldingKnife) {
                    if(PlayerValues.isPlayerUsingKnife) {
                        PlayerValues.isPlayerHurtingEnemy = true;
                        attack = false;
                    }
                }
                if(((Bird) o).isDead()){
                    if (World.level.equals(BirdValues.LEVEL_SPAWN_LOCATION)){
                        PlayerValues.isPlayerTouchingKey = true;
                    }
                }
            }


            if (o instanceof EvilCar){
                if (futureY.intersects(o.getBounds())) {
                    if (futureY.getMaxY() <= o.getBounds().getMinY() + 30 && futureY.getMinY() < o.getBounds().getMinY()) {
                        yVelocity = -((float) (ySpeed * 11) / 10);
                        onGround = false;
                        jumpingOffOfEnemy = false;
                    } else {
                        takeDamage(CarValues.ATTACK);
                    }
                }
            }

            if (o instanceof PassiveCar){
                if (futureY.intersects(o.getBounds())) {
                    if (futureY.getMaxY() <= o.getBounds().getMinY() + 30 && futureY.getMinY() < o.getBounds().getMinY()) {
                        yVelocity = -((float) (ySpeed * 11) / 10);
                        onGround = false;
                        jumpingOffOfEnemy = false;
                    }
                }

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
            }

            if (o instanceof RatTrap) {
                if (futureY.intersects(o.getBounds())) {
                    takeDamage(RatTrapValues.ATTACK);
                    trapTime = 120;
                }
            }

        }
    }

    public void keyPressed(int key, char c){
        if (key == Input.KEY_D && !facingRight){
            xAccel = 0;
        }
        if (key == Input.KEY_A && facingRight){
            xAccel = 0;
        }

        if(key == Input.KEY_SPACE && canAttack)
        {
            attack = true;
            PlayerValues.isPlayerUsingKnife = true;
            if (PlayerValues.isPlayerHoldingKnife) {
                weaponCooldown = 30;
            } else {
                weaponCooldown = 90;
            }
            canAttack = false;
        }
        if(key == Input.KEY_1 && PlayerValues.doesPlayerHaveKnife){
            PlayerValues.isPlayerHoldingKnife = !PlayerValues.isPlayerHoldingKnife;
            weaponCooldown = 30;
        }

        if (key == (Input.KEY_S) && dashTimer <= 0 && dashCooldown <= 0) {
            dashTimer = PlayerValues.DASH_TIME;
            dashCooldown = PlayerValues.DASH_COOLDOWN;
        }
    }

    public Rectangle getBounds() {
        if (xVelocity == 0 && xAccel == 0){
            return new Rectangle(x,y,w,h);
        }
        return new Rectangle(x,y + Cell.getHeight() + 10 - (Cell.getHeight() * 4 / 3),w,h);
    }
}
