package objects.platforms;

import engine.Main;
import objects.GameObject;
import objects.world.Cell;
import objects.world.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;
import ui.images.ImageRenderer;

public class Platform extends GameObject
{
    public Platform(float x, float y) {
        super(x, y);
        this.x = x;
        this.y = y;
    }

    public void collisions(StateBasedGame sbg){

    }

    public boolean isBottomPlatform() {
        if (cell == null) return true;

        int belowY = cell.getY() + 1;
        int x = cell.getX();

        if (belowY >= World.HEIGHT) return true;

        Cell belowCell = World.getCell(x, belowY);
        if (belowCell == null || belowCell.getObject() == null) return true;

        return !(belowCell.getObject() instanceof Platform);
    }

}