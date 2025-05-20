package objects.entities;

import engine.Main;
import engine.states.Game;
import objects.GameObject;
import objects.entities.enemies.Bird;
import objects.entities.enemies.Car;
import objects.entities.enemies.Cockroach;
import objects.interactables.Door;
import objects.interactables.Key;
import objects.interactables.Weapon;
import objects.platforms.Platform;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import values.BirdValues;
import values.CarValues;
import values.CockroachValues;
import values.PlayerValues;
import world.World;
import ui.Images;

import java.util.ArrayList;

public class Player extends Entity {

    private boolean jumpingOffOfEnemy;
    private float weaponCooldown;
    private boolean canAttack;
    private boolean attack;

    public static boolean hasWeapon = false;
    public static boolean gameOver;
    public static int section = 0;
    private boolean hitFront = false;
    private int streetTimer = 1800;
    private float cooldown = 60;
    private boolean inBox;

    public Player(float x, float y) {
        super(x, y, PlayerValues.X_SPEED, PlayerValues.Y_SPEED, PlayerValues.HEALTH, PlayerValues.ATTACK, Images.ratIdle, PlayerValues.IFRAMES);
        facingRight = true;
        onGround = true;
        gameOver = false;
        jumpingOffOfEnemy = false;
        weaponCooldown = 90;
        canAttack = false;
        attack = false;
        inBox = false;
    }

    public void render(Graphics g) {
      if(!isDead) {
          super.render(g);
          if (streetTimer > 1200) {
//            g.setFont(Fonts.big);
//            g.setColor(Color.red);
//            g.drawString("Dont get hit!", (float) Main.getScreenWidth() / 2, (float) Main.getScreenHeight() / 2 - 400);
          }
          g.fillRect(x, y - 50, (w + 50), 10);
          g.setColor(Color.yellow);
          if (weaponCooldown > 0) {
              g.fillRect(x, y - 50, (w + 50) * ((90 - weaponCooldown) / 90), 10);
          } else {
              g.fillRect(x, y - 50, w + 50, 10);

          }
      }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        Input input = gc.getInput();

        weaponCooldown--;
        if (weaponCooldown < 0) {
            canAttack = true;
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

        if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_A)) {
            contactingPlatformSide = false;
        }

        if (input.isKeyDown(Input.KEY_W) && onGround && !jumpingOffOfEnemy) {
            jump();
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
                if (!PlayerValues.isPlayerTouchingKey || !PlayerValues.enemyDeadInArea) {
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
                        if (World.level.equals("levels/sewer1.txt")) {
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
                                PlayerValues.enemyDeadInArea = true;
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
                    }
                }
            }

            if (o instanceof Key) {
                if (getBounds().intersects(o.getBounds())) {
                    PlayerValues.isPlayerTouchingKey = true;
                }
            }

//            if (o instanceof Weapon){
            if (World.level.equals("levels/sewer4.txt")) {
                if (getBounds().intersects(o.getBounds())) {
                    PlayerValues.doesPlayerHaveWeapon = true;
                    PlayerValues.isPlayerTouchingKey = true;
                }
            }
//            }

            if (o instanceof Cockroach) {
                PlayerValues.enemyDeadInArea = false;
                Rectangle weaponBounds = getWeaponBounds(facingRight);
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
                        PlayerValues.enemyDeadInArea = true;
                    }
                }
                if (weaponBounds.intersects(o.getBounds()) && !(((Cockroach) o).isDead)) {
                   inBox = true;
                    if (attack) {
                        PlayerValues.isPlayerHurtingEnemy = true;
                        attack = false;
                    }
                } else if(!(((Cockroach) o).isDead))
                {
                    inBox = false;
                }
            }

            if (o instanceof Bird) {
                Rectangle weaponBounds = getWeaponBounds(facingRight);
                PlayerValues.enemyDeadInArea = false;
                if (futureY.intersects(o.getBounds()) && !(((Bird) o).isDead)) {
                    if (futureY.getMaxY() - 50 <= o.getBounds().getMinY() + 30 && futureY.getMinY() < o.getBounds().getMinY() + 40) {
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
                if (weaponBounds.intersects(o.getBounds()) && !(((Bird) o).isDead)) {
                    inBox = true;
                    if (attack) {
                        PlayerValues.isPlayerHurtingEnemy = true;
                        attack = false;
                    }
                }
                else if(!(((Bird) o).isDead))
                {
                    inBox = false;
                }
            }

            if (o instanceof Car) {
                Car car = (Car) o;
                Rectangle carBounds = car.getBounds();
                if (futureX.intersects(o.getBounds())) {
                    boolean hitFront = false;
                    if (newX <= car.getX() + 5) {
                        hitFront = true;
                    }

                    if (hitFront) {
                        takeDamage(CarValues.ATTACK);
                        jump();
                    }
                    if (xAccel == 0 && hitFront) {
                        jump();
                    }

                    if (xVelocity > 0) {
                        newX = car.getX() - w;
                    } else if (xVelocity < 0) {
                        newX = car.getX() + car.getW();
                    }

                }
                if (futureY.intersects(o.getBounds())) {
                    if ((y <= car.getY() + 10)) {
                        newY = car.getY() - h;
                        onGround = true;

                    }
                }
            }


        }
    }

    public void keyPressed(int key, char c) {
        if (key == Input.KEY_D && !facingRight) {
//            image = rightFacingImage;
            xAccel = 0;
        }
        if (key == Input.KEY_A && facingRight) {
            xAccel = 0;
//            image = image.getFlippedCopy(true, false);
        }
        if (key == Input.KEY_SPACE && canAttack && inBox) {
            attack = true;
            weaponCooldown = 90;
            canAttack = false;
        }
    }
}