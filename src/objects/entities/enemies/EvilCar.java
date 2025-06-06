package objects.entities.enemies;

import engine.Main;
import engine.states.Game;
import objects.GameObject;
import objects.entities.Entity;
import objects.platforms.Platform;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.CarValues;
import world.Cell;

import java.util.ArrayList;

public class EvilCar extends Entity {


    private Image image;
    private SpriteSheet mySheet;
    private Image currentFrame;
    private int step = 0;
    private int frames = 0;
    private int framesPerStep = 6;


    public EvilCar(float x, float y) {
        super(x, y, CarValues.X_SPEED, CarValues.Y_SPEED, CarValues.HEALTH, CarValues.ATTACK, Images.car1, CarValues.IFRAMES);
        image = Images.car1;
        w = image.getWidth();
        h = image.getHeight();
        mySheet = Images.movingCar;
        currentFrame = mySheet.getSprite(0,0);
    }

    public void render(Graphics g) {
//        super.render(g);
        float renderOffsetY = h - currentFrame.getHeight();
        currentFrame.draw(x, y + renderOffsetY);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        xVelocity = -xSpeed;

        if (x <= -w) {
            x = Main.getScreenWidth() + w;
        }

        newX = x + xVelocity;
        newY = y + yVelocity;

        frames+=2;
        if(frames % framesPerStep == 0)
        {
            step++;
        }
        if(step >= mySheet.getHorizontalCount())
        {
            step = 0;
        }
        currentFrame = mySheet.getSprite(step, 0).getScaledCopy((int) (Cell.getWidth() * 8), (int) (Cell.getHeight() * 2));

        x = newX;
        y = newY;
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y + (Cell.getHeight() / 8),w,h - (Cell.getHeight() / 8));
    }

}
