package objects.entities.enemies;

import objects.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.StudentValues;
import world.Cell;

public class Student extends Entity {

    private SpriteSheet mySheet;
    private Image currentFrame;
    private int step = 0;
    private int frames = 0;
    private int framesPerStep = 6;

    public boolean crazed;
    private float turnTimer;
    private float jumpTimer;

    public Student(float x, float y) {
        super(x, y, StudentValues.X_SPEED, StudentValues.Y_SPEED, StudentValues.HEALTH, StudentValues.ATTACK, Images.studentOneIdle, StudentValues.IFRAMES);
        facingRight = true;
        image = Images.studentOneIdle;
        mySheet = Images.student1;
        currentFrame = mySheet.getSprite(0,0);
        crazed = false;
        turnTimer = 240;
        jumpTimer = 180;
    }

    public void render(Graphics g)
    {
//        super.render(g);
        float renderOffsetY = h - currentFrame.getHeight();
        if (facingRight) {
            currentFrame.draw(x, y + renderOffsetY);
        } else {
            currentFrame.getFlippedCopy(true, false).draw(x, y + renderOffsetY);
        }
        g.drawRect(x,y,w,h);
    }

    public void update(GameContainer GC, StateBasedGame sbg, int delta)
    {

        if(crazed)
        {


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
