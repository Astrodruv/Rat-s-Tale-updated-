package healthbars;

import objects.entities.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import world.Cell;

public class PlayerHealthBar extends HealthBar {

    public PlayerHealthBar(Entity e) {
        super(Cell.getWidth() / 2, Cell.getHeight() / 8, Cell.getWidth() * 10, Cell.getHeight() * 3 / 4, Color.red, Color.darkGray, e);
    }

    public void render(Graphics g){
        super.render(g);
    }

}
