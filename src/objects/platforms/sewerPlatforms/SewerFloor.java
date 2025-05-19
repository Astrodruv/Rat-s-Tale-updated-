package objects.platforms.sewerPlatforms;

import objects.platforms.Platform;
import org.newdawn.slick.Graphics;
import ui.Images;

public class SewerFloor extends Platform
{
    public SewerFloor(float x, float y) {
        super(x,y, Images.sewerFloor);
    }

    public void render(Graphics g) {
        super.render(g);
    }

}