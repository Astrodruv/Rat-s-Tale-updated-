package objects.interactables;

import objects.GameObject;
import objects.entities.Player;
import org.newdawn.slick.Graphics;
import ui.Images;
import values.PlayerValues;
import world.Cell;

public class Key extends GameObject {

    public Key(float x, float y) {
        super(x, y, Images.key);
    }

    public void render(Graphics g) {


        if (Player.section == 1) {
            image = Images.coin;
        } else if (Player.section == 2) {
            image = Images.screwDriver;
        }

        if (!PlayerValues.isPlayerTouchingKey) {
            super.render(g);
        } else {
            image.draw(Cell.getWidth() * 46.5f, Cell.getHeight() / 3);
        }
    }
}
