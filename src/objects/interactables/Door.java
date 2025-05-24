package objects.interactables;

import objects.GameObject;
import org.newdawn.slick.Graphics;
import ui.Images;
import values.PlayerValues;


public class Door extends GameObject {

    public Door(float x, float y) {
        super(x,y, Images.door);
    }

    public void render(Graphics g) {
        super.render(g);
    }

}
