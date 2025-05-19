package healthbars;

import objects.entities.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class PlayerHealthBar extends HealthBar {

    public PlayerHealthBar(Entity e) {
        super(50, 50, 500, 70, Color.red, Color.darkGray, e);
    }

    public void render(Graphics g){
        super.render(g);
    }

}