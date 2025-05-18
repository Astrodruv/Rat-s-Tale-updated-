package objects.platforms.gamePlatforms;

import objects.platforms.Platform;
import objects.world.Cell;
import ui.images.ImageRenderer;

import java.awt.*;

public class StreetFloor extends Platform {

    public StreetFloor(float x, float y) {
        super(x,y);
        this.x = x;
        this.y = y;
        image = ImageRenderer.streetFloor;
        h = image.getHeight();
    }
    public void render(Graphics g){
        float cellW = Cell.getWidth();
        float cellH = Cell.getHeight();
        image.draw(cell.getX() * cellW, cell.getY() * cellH, cellW, h);

    }
}
