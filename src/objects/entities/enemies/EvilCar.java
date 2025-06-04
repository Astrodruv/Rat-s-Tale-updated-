package objects.entities.enemies;

import engine.Main;
import objects.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.CarValues;
import world.Cell;

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
        super.render(g);
        float renderOffsetY = h - currentFrame.getHeight();
            currentFrame.draw(x, y + renderOffsetY);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        xVelocity -= (float) xSpeed / 5;

        if (x <= Cell.getWidth() * 2) {
            x = Main.getScreenWidth() - (Cell.getWidth() * 2) - w;
            xVelocity = 0;
        }
        frames+=2;
        if(frames % framesPerStep == 0)
        {
            step++;
        }
        if(step >= mySheet.getHorizontalCount())
        {
            step = 0;
        }
        currentFrame = mySheet.getSprite(step, 0);

        super.update(gc, sbg, delta);
    }
}
