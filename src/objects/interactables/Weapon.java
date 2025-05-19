package objects.interactables;

import objects.GameObject;
import org.newdawn.slick.Graphics;
import ui.Images;
import values.PlayerValues;
import world.Cell;

public class Weapon extends GameObject {

    public Weapon(float x, float y) {
        super(x,y, Images.knife);
    }

    public void render(Graphics g) {
        if (!PlayerValues.doesPlayerHaveWeapon) {
            super.render(g);
        }
        else{
            image.draw(Cell.getWidth() * 49f, Cell.getHeight() / 6);
        }
    }

}