package objects.entities.enemies;

import engine.Main;
import engine.states.Game;
import objects.GameObject;
import objects.entities.Entity;
import objects.platforms.Platform;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.CarValues;
import world.Cell;

import java.util.ArrayList;

public class EvilCar extends Entity {

    public EvilCar(float x, float y) {
        super(x, y, CarValues.X_SPEED, CarValues.Y_SPEED, CarValues.HEALTH, CarValues.ATTACK, Images.car1, CarValues.IFRAMES);
    }

    public void render(Graphics g) {
        super.render(g);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        xVelocity = -xSpeed;

        if (x <= -w) {
            x = Main.getScreenWidth() + w;
        }

        newX = x + xVelocity;
        newY = y + yVelocity;

        x = newX;
        y = newY;
    }

}
