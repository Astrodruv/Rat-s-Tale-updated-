package objects.entities;

import engine.Main;
import engine.states.Game;
import objects.GameObject;
import objects.entities.enemies.*;
import objects.interactables.*;
import objects.platforms.Platform;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import values.*;
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

    public static Image image;

    public static boolean hasWeapon = false;
    public static boolean gameOver;
    public static int section = 0;
    private boolean hitFront = false;

    private int streetTimer = 1800;

    public static boolean holdingKnife;
    public static boolean knifeAttained;

    private boolean inBox;
    private float trapTime;
    private boolean trapped; // use in reference to other slow effects
    private float temp;

    public Player(float x, float y) {

        super(x, y, PlayerValues.X_SPEED, PlayerValues.Y_SPEED, PlayerValues.HEALTH, PlayerValues.ATTACK, Images.ratIdle, PlayerValues.IFRAMES);
        facingRight = true;
        onGround = true;
        gameOver = false;
        jumpingOffOfEnemy = false;
        weaponCooldown = 90;
        canAttack = false;
        attack = false;
        mySheet = Images.rat;
        //mySheet2 = Images.knifeAttack;
        currentFrame = mySheet.getSprite(0, 0);
        image = Images.ratIdle;
        knifeAttained = false;
        holdingKnife = false;
        inBox = false;
        trapTime = 0;
        temp = xSpeed;

    }

    public void render(Graphics g) {
        super.render(g);
        if (streetTimer > 1200) {
//            g.setFont(Fonts.big);
//            g.setColor(Color.red);
//            g.drawString("Dont get hit!", (float) Main.getScreenWidth() / 2, (float) Main.getScreenHeight() / 2 - 400);
        }
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

        if (PlayerValues.doesPlayerHaveWeapon && holdingKnife) {
            g.fillRect(x, y - 50, (w + 50), 10);
            g.setColor(Color.yellow);
            if (weaponCooldown > 0) {
                g.fillRect(x, y - 50, (w + 50) * ((30 - weaponCooldown) / 30), 10);
            } else {
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
                g.fillRect(x, y - 65, w + 50, 10);
            }
        }
        g.drawString(""+ xVelocity,700,700);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        Input input = gc.getInput();

        weaponCooldown--;
        if (weaponCooldown < 0) {
            canAttack = true;
        }

        trapTime--;
        if(trapTime > 0)
        {
            trapped = true;
            xSpeed /= 2;
            trapTime--;
        }
        else {
            trapped = false;
            xSpeed = (int) temp;
        }

        if (input.isKeyDown(Input.KEY_D) && !contactingPlatformSide) {
            moveRight();
            facingRight = true;
        } else if (input.isKeyDown(Input.KEY_A) && !contactingPlatformSide) {
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
        frames++;
        if (frames % framesPerStep == 0) {
            step++;
        }
        if (step >= mySheet.getHorizontalCount()) {
            step = 0;
        }
        currentFrame = mySheet.getSprite(step, 0);

        if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_A)) {
            contactingPlatformSide = false;
        }

        if (input.isKeyDown(Input.KEY_W) && onGround && !jumpingOffOfEnemy) {
            jump();
        }

        if(xVelocity > 10)
        {
            xVelocity = 10;
        }
        if(xVelocity < -10)
        {
            xVelocity = -10;
        }

        super.update(gc, sbg, delta);
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
                if (!PlayerValues.isPlayerTouchingKey) {
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
                } else {
                    if (getBounds().intersects(o.getBounds()) && PlayerValues.isPlayerTouchingKey) {

                        if (World.level.equals("levels/sewer1.txt")) { //sewer1
                            Game.setLevel("levels/sewer2.txt");
                            if (!PlayerValues.keyOnPermanentlySetting) {
                                PlayerValues.isPlayerTouchingKey = false;
                            }
                            continue;
                        }
                        if (World.level.equals("levels/sewer2.txt")) {
                            Game.setLevel("levels/sewer3.txt");
                            if (!PlayerValues.keyOnPermanentlySetting) {
                                PlayerValues.isPlayerTouchingKey = false;
                            }
                            continue;
                        }
                        if (World.level.equals("levels/sewer3.txt")) {
                            Game.setLevel("levels/sewer4.txt");
                            if (!PlayerValues.keyOnPermanentlySetting) {
                                PlayerValues.isPlayerTouchingKey = false;
                            }
                            continue;
                        }
                        if (World.level.equals("levels/sewer4.txt")) {
                            section++;
                            Game.setLevel("levels/street1.txt"); //street1
                            if (!PlayerValues.keyOnPermanentlySetting) {
                                PlayerValues.isPlayerTouchingKey = false;
                            }
                            continue;
                        }
                        if (World.level.equals("levels/street1.txt")) {
                            Game.setLevel("levels/street2.txt");
                            if (!PlayerValues.keyOnPermanentlySetting) {
                                PlayerValues.isPlayerTouchingKey = false;
                            }
                            continue;
                        }
                        if (World.level.equals("levels/street2.txt")) {
                            Game.setLevel("levels/street3.txt");
                            if (!PlayerValues.keyOnPermanentlySetting) {
                                PlayerValues.isPlayerTouchingKey = false;
                            }
                            continue;
                        }
                        if (World.level.equals("levels/street3.txt")) {
                            Game.setLevel("levels/street4.txt");
                            if (!PlayerValues.keyOnPermanentlySetting) {
                                PlayerValues.isPlayerTouchingKey = false;
                            }
                            continue;
                        }
                        if (World.level.equals("levels/street4.txt")) {
                            Game.setLevel("levels/street5.txt");
                            if (!PlayerValues.keyOnPermanentlySetting) {
                                PlayerValues.isPlayerTouchingKey = false;
                            }
                            continue;
                        }
                        if (World.level.equals("levels/street5.txt")) {
                            Game.setLevel("levels/school.txt");
                            if (!PlayerValues.keyOnPermanentlySetting) {
                                PlayerValues.isPlayerTouchingKey = false;
                            }
                            continue;
                        }
                        if (World.level.equals("levels/school.txt")) {
                            section++;
                            Game.setLevel("levels/closet1.txt");
                            if (!PlayerValues.keyOnPermanentlySetting) {
                                PlayerValues.isPlayerTouchingKey = false;
                            }
                            continue;
                        }
                        if (World.level.equals("levels/closet1.txt")) {
                            Game.setLevel("levels/closet2.txt");
                            if (!PlayerValues.keyOnPermanentlySetting) {
                                PlayerValues.isPlayerTouchingKey = false;
                            }
                            continue;
                        }
                        if (World.level.equals("levels/closet2.txt")) {
                            Game.setLevel("levels/closet3.txt");
                            if (!PlayerValues.keyOnPermanentlySetting) {
                                PlayerValues.isPlayerTouchingKey = false;
                            }
                            continue;
                        }
                        if (World.level.equals("levels/closet3.txt")) {
                            Game.setLevel("levels/closet4.txt");
                            if (!PlayerValues.keyOnPermanentlySetting) {
                                PlayerValues.isPlayerTouchingKey = false;
                            }
                            continue;
                        }
                        if (World.level.equals("levels/closet4.txt")) {
                            Game.setLevel("levels/closet5.txt");
                            if (!PlayerValues.keyOnPermanentlySetting) {
                                PlayerValues.isPlayerTouchingKey = false;
                            }
                            continue;
                        }
                    }
                }
            }

            if (o instanceof Key) {
                if (getBounds().intersects(o.getBounds())) {
                    PlayerValues.isPlayerTouchingKey = true;
                }
            }

            if (o instanceof Weapon) {
                if (World.level.equals("levels/sewer4.txt")) {
                    if (getBounds().intersects(o.getBounds())) {
                        PlayerValues.doesPlayerHaveWeapon = true;
                        PlayerValues.isPlayerTouchingKey = true;
                        knifeAttained = true;
                    }
                }
            }
            if (section > 0) {
                knifeAttained = true;
            }

            if (o instanceof Cockroach) {
                if (futureY.intersects(o.getBounds()) && !(((Cockroach) o).isDead)) {
                    if (futureY.getMaxY() <= o.getBounds().getMinY() + 30 && futureY.getMinY() < o.getBounds().getMinY()) {
                        PlayerValues.isPlayerHurtingEnemy = true;
                        onGround = true;
                        jumpingOffOfEnemy = true;
                        jump();
                        jumpingOffOfEnemy = false;
                    } else {
                        takeDamage(CockroachValues.ATTACK);
                        System.out.println("Damage");
                    }
                }
                if (((Cockroach) o).isDead()) {
                    if (World.level.equals(CockroachValues.LEVEL_SPAWN_LOCATION)) {
                        PlayerValues.isPlayerTouchingKey = true;
                    }
                }
            }

            if (o instanceof Bird) {
                Rectangle weaponBounds = getWeaponBounds(facingRight);
                if (futureY.intersects(o.getBounds()) && !((Bird) o).isDead) {
                    if (futureY.getMaxY() <= o.getBounds().getMinY() + 30 && futureY.getMinY() < o.getBounds().getMinY()) {
                        PlayerValues.isPlayerHurtingEnemy = true;
                        onGround = true;
                        jumpingOffOfEnemy = true;
                        jump();
                        jumpingOffOfEnemy = false;
                    } else {
                        takeDamage(BirdValues.ATTACK);
                        System.out.println("Damage");
                    }
                }
                if (weaponBounds.intersects(o.getBounds()) && !((Bird) o).isDead) {
                    inBox = true;
                    if (attack) {
                        PlayerValues.isPlayerHurtingEnemy = true;
                        attack = false;
                    }
                } else {
                    inBox = false;
                }
                if (((Bird) o).isDead()) {
                    if (World.level.equals(BirdValues.LEVEL_SPAWN_LOCATION)) {
                        PlayerValues.isPlayerTouchingKey = true;
                    }
                }
            }

            if (o instanceof EvilCar){
                if (futureY.intersects(o.getBounds())) {
                    if (futureY.getMaxY() <= o.getBounds().getMinY() + 30 && futureY.getMinY() < o.getBounds().getMinY()) {
                        jumpingOffOfEnemy = true;
                        jump();
                        jumpingOffOfEnemy = false;
                    } else {
                        takeDamage(CarValues.ATTACK);
                    }
                }
            }

            if (o instanceof PassiveCar){
                if (futureY.intersects(o.getBounds())) {
                    if (futureY.getMaxY() <= o.getBounds().getMinY() + 30 && futureY.getMinY() < o.getBounds().getMinY()) {
                        jumpingOffOfEnemy = true;
                        jump();
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
                    if (newY < o.getBounds().getMinY()) {
                        jump();
                    }
                    takeDamage(RatTrapValues.ATTACK);
                    trapTime = 240;
                }
            }

            if(o instanceof Janitor)
            {
                if(futureY.intersects(o.getJanitorBounds(((Janitor) o).facingRight)))
                {
                    takeDamage(JanitorValues.ATTACK);
                }
            }
        }
    }

    public void keyPressed(int key, char c) {
        if (key == Input.KEY_D && !facingRight) {
//            image = rightFacingImage;
            facingRight = true;
            xAccel = 0;
        }
        if (key == Input.KEY_A && facingRight) {
            facingRight = false;
            xAccel = 0;
//            image = image.getFlippedCopy(true, false);
        }
        if (key == Input.KEY_SPACE && canAttack && inBox && holdingKnife) {
            attack = true;
            weaponCooldown = 30;
            canAttack = false;
        }
        if (key == Input.KEY_1 && knifeAttained) {
            holdingKnife = !holdingKnife;
            weaponCooldown = 30;
        }

    }

}