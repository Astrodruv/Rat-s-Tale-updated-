package objects.interactables;

import objects.GameObject;
import objects.entities.player.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import ui.images.ImageRenderer;

public class Knife extends GameObject {
    public Knife(float x, float y){
        super(x, y);
        this.x = x;
        this.y = y;
        image = ImageRenderer.knife;
        w = image.getWidth();
        h = image.getHeight();

    }
    public void render(Graphics g){
        if(!Player.knifeAttained){
            g.setColor(Color.darkGray);
            image.draw(x, y);
            g.setColor(Color.orange);
            g.draw(getBounds());
        }

    }
    public void collisions(StateBasedGame sbg) {

    }
}
