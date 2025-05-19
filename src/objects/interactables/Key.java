package objects.interactables;

import objects.GameObject;
import org.newdawn.slick.Graphics;
import ui.Images;
import values.PlayerValues;
import world.Cell;

public class Key extends GameObject {

    public Key(float x, float y) {
        super(x,y, Images.key);
    }

    public void render(Graphics g) {
        if (!PlayerValues.isPlayerTouchingKey) {
            super.render(g);
        }
        else{
            image.draw(Cell.getWidth() * 45, Cell.getHeight() * 3); // *46.5f and / 3
        }
    }

}