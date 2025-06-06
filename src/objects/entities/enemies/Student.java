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
import values.StudentValues;
import world.Cell;

import java.awt.*;
import java.util.Random;

public class Student extends Entity {

    private SpriteSheet mySheet;
    private Image currentFrame;
    private int step = 0;
    private int frames = 0;
    private int framesPerStep = 6;

    public boolean crazed;
    public boolean effected;
    private float xMax;
    private float xMin;
    private float jumpTimer;
    private boolean jumpStart;
    private float xVelstore;

    private static final Random random = new Random();
    private static int color;
    private boolean generated;


    public Student(float x, float y) {
        super(x, y, StudentValues.X_SPEED, StudentValues.Y_SPEED, StudentValues.HEALTH, StudentValues.ATTACK, Images.studentOneIdle, StudentValues.IFRAMES);
        facingRight = true;
        image = Images.studentOneIdle;
        crazed = false;
        effected = false;
        jumpTimer = 60;
        jumpStart = false;
        xVelstore = xVelocity;
        generated = false;
        xMax = (float) (x + (200 + Math.random() * 200));
        xMin = (float) (x - (200 + Math.random() * 200));

        randomizer();

        if(color == 0)
        {
            mySheet = Images.student2;
        }
        else if(color == 1)
        {
             mySheet = Images.student1;
        }
        currentFrame = mySheet.getSprite(0, 0);

    }

    public void render(Graphics g) {
//        super.render(g);
        float renderOffsetY = h - currentFrame.getHeight();
        if (!facingRight) {
            currentFrame.draw(x, y + renderOffsetY);
        } else {
            currentFrame.getFlippedCopy(true, false).draw(x, y + renderOffsetY);
        }
        randomizer();
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        if (crazed) {
            jumpStart = true;
            effected = true;
        }

        if(jumpStart)
        {
            jumpTimer--;
        }

        if (x < xMin || x > xMax) {
            if (crazed) {
                xSpeed *= -2;
                crazed = false;
            }
            else {
                xSpeed *= -1;
            }
            facingRight = false;
        }

        if (xSpeed > 0) {
            facingRight = true;
        }

        if(jumpTimer < 0)
        {
            jump();
        }

        x += xSpeed;

        frames++;
        if (frames % framesPerStep == 0) {
            step++;
        }
        if (step >= mySheet.getHorizontalCount()) {
            step = 0;
        }
        currentFrame = mySheet.getSprite(step, 0);

//        super.update(gc, sbg, delta);
    }

    public void randomizer() {
        if (!generated) {
            color = random.nextInt(2);
            generated = true;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }

}