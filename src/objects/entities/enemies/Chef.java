package objects.entities.enemies;

import engine.states.Game;
import objects.GameObject;
import objects.entities.Entity;
import objects.entities.Player;
import objects.interactables.Food;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.BirdValues;
import values.ChefValues;
import values.PlayerValues;
import world.Cell;
import world.World;

public class Chef extends Entity {

    private SpriteSheet mySheet;
    private SpriteSheet atkSheet;
    private Image currentFrame;
    private Image curAtkFrame;
    private int step = 0;
    private int atkStep = 0;
    private int frames = 0;
    private int atkFrames = 0;
    private int framesPerStep = 6;
    private int atkFramesPerStep = 4;

    private boolean attack;
    private float atkTimer;
    private float jumpTimer;
    private float playerX;
    private float thrownCooldown;
    private float throwTimer;


    public static boolean facingRight;
    public static float getX;

    public boolean foodSent;


    public Chef(float x, float y) {
        super(x, y, ChefValues.X_SPEED, ChefValues.Y_SPEED, ChefValues.HEALTH, ChefValues.ATTACK, Images.chefIdle, ChefValues.IFRAMES);
        facingRight = true;
        mySheet = Images.chef;
        atkSheet = Images.chefAtk;
        currentFrame = mySheet.getSprite(0, 0);
        curAtkFrame = atkSheet.getSprite(0, 0);

        this.getX = x;

        attack = false;
        atkTimer = 180;
        jumpTimer = 240;
        foodSent = false;

        throwTimer = 100;
        thrownCooldown = 0;
    }

    public void render(Graphics g) {
        if (isHit) {
            if (invincibilityFrames % 7 == 0) {

            } else {
                float renderOffsetY = h - currentFrame.getHeight();
                if (facingRight) {
                    if (!attack) {
                        currentFrame.draw(getX, y + renderOffsetY);
                    } else {
                        curAtkFrame.draw(getX, y + renderOffsetY);
                    }
                } else {
                    if (!attack) {
                        currentFrame.getFlippedCopy(true, false).draw(getX, y + renderOffsetY);
                    } else {
                        curAtkFrame.getFlippedCopy(true, false).draw(getX, y + renderOffsetY);
                    }
                }
            }
        } else {
            float renderOffsetY = h - currentFrame.getHeight();
            if (facingRight) {
                if (!attack) {
                    currentFrame.draw(getX, y + renderOffsetY);
                } else {
                    curAtkFrame.draw(getX, y + renderOffsetY);
                }

            } else {
                if (!attack) {
                    currentFrame.getFlippedCopy(true, false).draw(getX, y + renderOffsetY);
                } else {
                    curAtkFrame.getFlippedCopy(true, false).draw(getX, y + renderOffsetY);
                }
            }
        }
        g.drawRect(x,y,w,h);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        setPlayerX();

        atkTimer--;
        jumpTimer--;
        throwTimer--;

        if (World.level.equals(ChefValues.LEVEL_SPAWN_LOCATION) && PlayerValues.isPlayerHurtingEnemy) {
            isHit = true;
            PlayerValues.isPlayerHurtingEnemy = false;
        }

        if (isHit && !hasTakenDamage){
            if (PlayerValues.isPlayerUsingKnife){
                takeDamage(PlayerValues.ATTACK * 3);
            }
            else takeDamage(PlayerValues.ATTACK);
            hasTakenDamage = true;
        }

        if (atkTimer > 0) {
            if (playerX < getX) {
                xVelocity -= xAccel;
                xAccel = 0.025F;
                facingRight = false;
            } else {
                xVelocity += xAccel;
                xAccel = 0.025F;
                facingRight = true;
            }

            getX += xVelocity;

        } else if(throwTimer <= 0){
            attack = true;
            throwFood();
            throwTimer = 100;
            xVelocity = 0;
        }

        if (jumpTimer < 0) {
            jump();
            if(jumpTimer < -5) {
                jumpTimer = 240;
            }
        }

        if (!attack) {
            frames++;
            if (frames % framesPerStep == 0) {
                step++;
            }
            if (step >= mySheet.getHorizontalCount()) {
                step = 0;
            }
            currentFrame = mySheet.getSprite(step, 0);
        } else {
            atkFrames++;
            foodSent = true;
            if (atkFrames % atkFramesPerStep == 0) {
                atkStep++;
            }
            if (atkStep >= atkSheet.getHorizontalCount()) {
                atkStep = 0;
                attack = false;
                atkTimer = 180;
            }
            curAtkFrame = atkSheet.getSprite(atkStep, 0);
        }

        super.update(gc, sbg, delta);
    }

    public void throwFood() {
        Food food = new Food(getX, y - Cell.getHeight() / 2);
        Game.levelObjects.add(food);
    }

    public void setPlayerX() {
        for (GameObject o : Game.levelObjects) {
            if (o instanceof Player) {
                playerX = o.getX();
            }
        }
    }
}
