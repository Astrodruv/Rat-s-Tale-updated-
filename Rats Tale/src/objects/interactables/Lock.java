package objects.interactables;

import engine.Game;
import objects.GameObject;
import objects.entities.player.Player;
import objects.world.Cell;
import objects.world.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import ui.images.ImageRenderer;

import java.util.Objects;

public class Lock extends GameObject {

    public Lock(float x, float y){
        super(x, y);
        this.x = x;
        this.y = y;
        image = ImageRenderer.lock;
        w = image.getWidth();
        h = image.getHeight();
    }

    public void render(Graphics g){
        g.setColor(Color.darkGray);
        image.draw(x,y);
        g.setColor(Color.orange);
        g.draw(getBounds());
    }

    public void collisions(StateBasedGame sbg) {
    }
}
