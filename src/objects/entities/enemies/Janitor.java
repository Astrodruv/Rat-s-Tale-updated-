package objects.entities.enemies;

import engine.Main;
import objects.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
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

    private int movementTimer;

    public Janitor(float x, float y)
    {
        super(x, y, JanitorValues.X_SPEED, JanitorValues.Y_SPEED, JanitorValues.HEALTH, JanitorValues.ATTACK, Images.janitorIdle, JanitorValues.IFRAMES);
        facingRight = false;
        image = Images.janitorIdle;
        mySheet = Images.janitor;
        currentFrame = mySheet.getSprite(0,0);
        movementTimer = JanitorValues.MOVEMENT_TIMER_VALUE;
    }

    public void render(Graphics g)
    {
//        super.render(g);
        if (xVelocity != 0) {
            float renderOffsetY = h - currentFrame.getHeight();
            if (facingRight) {
                currentFrame.draw(x, y + renderOffsetY);
            } else {
                currentFrame.getFlippedCopy(true, false).draw(x, y + renderOffsetY);
            }
        }
        else{
            image.draw(x,y,w,h);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        if (movementTimer >= 0) moveRight();
        if (-JanitorValues.MOVEMENT_TIMER_VALUE < -movementTimer) moveLeft();
        else movementTimer = JanitorValues.MOVEMENT_TIMER_VALUE;

        movementTimer--;

        frames++;
        if(frames % framesPerStep == 0)
        {
            step++;
        }
        if(step >= mySheet.getHorizontalCount())
        {
            step = 0;
        }

        currentFrame = mySheet.getSprite(step, 0).getScaledCopy((int) (Cell.getWidth() * 6), (int) (Cell.getHeight() * 5));

        super.update(gc, sbg, delta);
    }
}
