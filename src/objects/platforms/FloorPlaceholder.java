package objects.platforms;

import objects.GameObject;
import objects.interactables.Door;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import ui.Images;
import world.Cell;
import world.World;

public class FloorPlaceholder extends GameObject {
    public FloorPlaceholder(float x, float y) {
        super(x,y, Images.streetBoundary);
    }

}
