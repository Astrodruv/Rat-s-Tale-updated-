package objects.entities.enemies;

import objects.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.ChefValues;
import world.Cell;

public class Chef extends Entity {

    private SpriteSheet mySheet;
    private SpriteSheet atkSheet;
    private Image currentFrame;
    private Image curAtkFrame;
    private int step = 0;
    private int frames = 0;
    private int atkFrames;
    private int framesPerStep = 6;

    private boolean attack;

    public Chef(float x, float y) {
        super(x, y, ChefValues.X_SPEED, ChefValues.Y_SPEED, ChefValues.HEALTH, ChefValues.ATTACK, Images.chefIdle, ChefValues.IFRAMES);
        facingRight = true;
        mySheet = Images.chef;
        atkSheet = Images.chefAtk;
        currentFrame = mySheet.getSprite(0, 0);
        curAtkFrame = atkSheet.getSprite(0, 0);

        attack = false;
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
                currentFrame.draw(x, y + renderOffsetY);
                curAtkFrame.draw(x, y + renderOffsetY);

            } else {
                currentFrame.getFlippedCopy(true, false).draw(x, y + renderOffsetY);
                curAtkFrame.getFlippedCopy(true, false).draw(x, y + renderOffsetY);
            }
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
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
            frames++;
            if (atkFrames % framesPerStep == 0) {
                step++;
            }
            if (step >= atkSheet.getHorizontalCount()) {
                step = 0;
            }
            curAtkFrame = atkSheet.getSprite(step, 0);
        }

        super.update(gc, sbg, delta);
    }
}
