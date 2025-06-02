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


public class Janitor extends Entity {

    private Image image;
    private SpriteSheet mySheet;
    private Image currentFrame;
    private int step = 0;
    private int frames = 0;
    private int framesPerStep = 6;

    public Janitor(float x, float y)
    {
        super(x, y, JanitorValues.X_SPEED, JanitorValues.Y_SPEED, JanitorValues.HEALTH, JanitorValues.ATTACK, Images.janitorIdle, JanitorValues.IFRAMES);
        facingRight = true;
        image = Images.janitorIdle;
        mySheet = Images.janitor;
        currentFrame = mySheet.getSprite(0,0);
    }

    public void render(Graphics g)
    {
        super.render(g);
        float renderOffsetY = h - currentFrame.getHeight();
        if (facingRight) {
            currentFrame.draw(x, y + renderOffsetY);
        } else {
            currentFrame.getFlippedCopy(true, false).draw(x, y + renderOffsetY);
        }
        g.drawRect(x,y,w,h);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        if(x <= -418 - 50|| x >= Main.getScreenWidth() + 50)
        {
            xSpeed *= -1;
            image = image.getFlippedCopy(true, false);
            facingRight = false;
        }

        x += xSpeed;

        if(xSpeed > 0){
            facingRight = true;
        }

        frames++;
        if(frames % framesPerStep == 0)
        {
            step++;
        }
        if(step >= mySheet.getHorizontalCount())
        {
            step = 0;
        }
        currentFrame = mySheet.getSprite(step, 0);
    }

}