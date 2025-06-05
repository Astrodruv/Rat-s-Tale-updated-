package objects.entities.enemies;

import engine.Main;
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
import values.ChefValues;
import world.Cell;

import javax.sql.XAConnection;
import java.util.ArrayList;

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


    public Chef(float x, float y) {
        super(x, y, ChefValues.X_SPEED, ChefValues.Y_SPEED, ChefValues.HEALTH, ChefValues.ATTACK, Images.chefIdle, ChefValues.IFRAMES);
        facingRight = true;
        mySheet = Images.chef;
        atkSheet = Images.chefAtk;
        currentFrame = mySheet.getSprite(0, 0);
        curAtkFrame = atkSheet.getSprite(0, 0);

        attack = false;
        atkTimer = 180;
        jumpTimer = 240;
    }

    public void render(Graphics g) {
        if (isHit) {
            if (invincibilityFrames % 7 == 0) {

            } else {
                float renderOffsetY = h - currentFrame.getHeight();
                if (facingRight) {
                    if (!attack) {
                        currentFrame.draw(x, y + renderOffsetY);
                    } else {
                        curAtkFrame.draw(x, y + renderOffsetY);
                    }
                } else {
                    if (!attack) {
                        currentFrame.getFlippedCopy(true, false).draw(x, y + renderOffsetY);
                    } else {
                        curAtkFrame.getFlippedCopy(true, false).draw(x, y + renderOffsetY);
                    }
                }
            }
        } else {
            float renderOffsetY = h - currentFrame.getHeight();
            if (facingRight) {
                if (!attack) {
                    currentFrame.draw(x, y + renderOffsetY);
                } else {
                    curAtkFrame.draw(x, y + renderOffsetY);
                }

            } else {
                if (!attack) {
                    currentFrame.getFlippedCopy(true, false).draw(x, y + renderOffsetY);
                } else {
                    curAtkFrame.getFlippedCopy(true, false).draw(x, y + renderOffsetY);
                }
            }
        }
        g.drawString("" + atkTimer, 700, 700);
        g.drawString("" + attack, 900, 700);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        setPlayerX();

        atkTimer--;
        jumpTimer--;

        if (atkTimer > 0) {
            if (playerX < x) {
                xVelocity -= xAccel;
                xAccel = 0.025F;
                facingRight = false;
            } else {
                xVelocity += xAccel;
                xAccel = 0.025F;
                facingRight = true;
            }

            x += xVelocity;

        } else {
            attack = true;
        }

        if(jumpTimer < 0)
        {
            jump();

            jumpTimer = 240;

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
        Food food = new Food(x, y);
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
