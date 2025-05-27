package objects.interactables;

import objects.GameObject;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import ui.Images;
import values.PlayerValues;
import world.Cell;

public class StreetDoor extends GameObject {

    public StreetDoor(float x, float y) {
        super(x, y, Images.streetDoor);
    }

}
