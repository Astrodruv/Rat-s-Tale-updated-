package objects.entities;

import engine.Main;
import objects.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.CarValues;
import world.Cell;

public class PassiveCar extends Entity {
private Image image;
    public PassiveCar(float x, float y) {
        super(x, y, CarValues.X_SPEED, CarValues.Y_SPEED, CarValues.HEALTH, CarValues.ATTACK, Images.car2, CarValues.IFRAMES);
        image = Images.car2;
//        w = image.getWidth();
//        h = image.getHeight();
    }

    public void render(Graphics g) {
        super.render(g);
        image.draw(x,y);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

    }
}
