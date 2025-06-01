package objects.weapons;

import objects.GameObject;
import objects.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.PlayerValues;
import world.Cell;

public class Knife extends GameObject {
    Entity entity;

    private SpriteSheet mySheet;
    private Image currentFrame;
    private int step = 0;
    private int frames = 0;
    private int framesPerStep = 1;

    public Knife(Entity e) {
        super(0,0, Images.knife);
        entity = e;

        mySheet = Images.knifeAttack;
        currentFrame = mySheet.getSprite(0,0);
    }

    public void render(Graphics g) {
        if (PlayerValues.doesPlayerHaveKnife) {
            if (!PlayerValues.isPlayerUsingKnife) {
                if (entity.isFacingRight() && PlayerValues.isPlayerHoldingKnife) {
                    image.draw(entity.getX() + entity.getW(), entity.getY());
                } else if (!entity.isFacingRight() && PlayerValues.isPlayerHoldingKnife) {
                    image.getFlippedCopy(true, false).draw(entity.getX() - getW(), entity.getY());
                }
            }
            else{
                float renderOffsetY = entity.getH() - currentFrame.getHeight();
                if (entity.isFacingRight() && PlayerValues.isPlayerHoldingKnife) {
                    currentFrame.draw(entity.getX() + entity.getW(), entity.getY() + renderOffsetY + (Cell.getHeight() / 2));
                } else if (!entity.isFacingRight() && PlayerValues.isPlayerHoldingKnife) {
                    currentFrame.getFlippedCopy(true, false).draw(entity.getX() - currentFrame.getWidth(), entity.getY() + renderOffsetY + (Cell.getHeight() / 2));
                }
            }
        }

//        g.drawString("Step: " + step, 500, 600);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        if (PlayerValues.isPlayerUsingKnife) {
            frames++;
            if (frames % framesPerStep == 0) {
                step++;
                if (step >= 7) {
                    step = 0;
                    PlayerValues.isPlayerUsingKnife = false;
                }
            }

            int spriteX = step % 3;
            int spriteY = step / 3;

            currentFrame = mySheet.getSprite(spriteX, spriteY).getScaledCopy((int) (Cell.getWidth() * 4), (int) (Cell.getHeight() * 2.5f));
        }
    }



}
