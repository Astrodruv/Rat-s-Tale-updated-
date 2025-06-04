package objects.entities.enemies;

import engine.Main;
import objects.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.JanitorValues;
import world.Cell;


public class Janitor extends Entity {

    private Image image;
    private SpriteSheet mySheet;
    private Image currentFrame;
    private int step = 0;
    private int frames = 0;
    private int framesPerStep = 6;

    public Janitor(float x, float y) {
        super(x, y, JanitorValues.X_SPEED, JanitorValues.Y_SPEED, JanitorValues.HEALTH, JanitorValues.ATTACK, Images.janitorIdle, JanitorValues.IFRAMES);
        facingRight = true;
        image = Images.janitorIdle;
        mySheet = Images.janitor;
        currentFrame = mySheet.getSprite(0, 0);
    }

    public void render(Graphics g) {
//        super.render(g);
        float renderOffsetY = h - currentFrame.getHeight();
        if (facingRight) {
            currentFrame.draw(x, y + renderOffsetY);
        } else {
            currentFrame.getFlippedCopy(true, false).draw(x, y + renderOffsetY);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        if (x <= 0 || x >= Main.getScreenWidth() - Images.janitorIdle.getWidth()) {
            xSpeed *= -1;
            image = image.getFlippedCopy(true, false);
            facingRight = false;
        }

        x += xSpeed;

        if (xSpeed > 0) {
            facingRight = true;
        }

        frames++;
        if (frames % framesPerStep == 0) {
            step++;
        }
        if (step >= mySheet.getHorizontalCount()) {
            step = 0;
        }
        currentFrame = mySheet.getSprite(step, 0);
    }

    public Rectangle getBounds(boolean facingRight) {
        if (!facingRight) {
            return new Rectangle(x, y, w, h);
        } else {
            return new Rectangle(x + (Cell.getWidth() * 3), y, w, h);
        }
    }
}

