package objects.interactables;

import objects.GameObject;
import objects.entities.Player;
import org.newdawn.slick.Graphics;
import ui.Images;
import values.PlayerValues;


public class Door extends GameObject {

    public Door(float x, float y) {
        super(x, y, Images.door);
    }

    public void render(Graphics g) {
        if(Player.section == 0)
        {
            image = Images.door;
        }
        else if(Player.section == 1)
        {
            image = Images.streetDoor;
        }
        super.render(g);
    }

}
