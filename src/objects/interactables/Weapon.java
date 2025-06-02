package objects.interactables;

import objects.GameObject;
import org.newdawn.slick.Graphics;
import ui.Images;
import values.PlayerValues;

public class Weapon extends GameObject {

    public Weapon(float x, float y) {
        super(x,y, Images.knife);
    }

    public void render(Graphics g) {
        if (!PlayerValues.doesPlayerHaveKnife) {
            super.render(g);
        }
        else{

        }
    }

}
