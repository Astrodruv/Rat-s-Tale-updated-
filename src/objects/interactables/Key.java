package objects.interactables;

import objects.GameObject;
import org.newdawn.slick.Graphics;
import ui.Images;
import values.PlayerValues;
import world.Cell;

public class Key extends GameObject {

    public Key(float x, float y) {
        super(x,y, Images.key);
        if (PlayerValues.section == 1) image = Images.coin;
    }

    public void render(Graphics g) {
        if (!PlayerValues.isPlayerTouchingKey) {
            super.render(g);
        }
        else{
            image.draw(Cell.getWidth() * 46.5f, Cell.getHeight() / 3);
        }
    }

}
