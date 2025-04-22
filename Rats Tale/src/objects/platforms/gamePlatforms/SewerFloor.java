package objects.platforms.gamePlatforms;

import objects.platforms.Platform;
import objects.world.Cell;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import ui.images.ImageRenderer;

public class SewerFloor extends Platform {
    public SewerFloor(float x, float y) {
        super(x, y);
        this.x = x;
        this.y = y;
        image = ImageRenderer.sewerFloor;
        h = Cell.getHeight();
    }

    public void render(Graphics g) {
        float cellW = Cell.getWidth();
        float cellH = Cell.getHeight();
        image.draw(cell.getX() * cellW, cell.getY() * cellH, cellW, h);
        if (isBottomPlatform()) {
            g.setColor(Color.gray);
//            g.drawString("Bottom",x,y);
//            g.draw(getBounds());
        }
    }

}