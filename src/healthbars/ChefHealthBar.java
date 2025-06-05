package healthbars;

import engine.Main;
import objects.entities.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import world.Cell;

public class ChefHealthBar extends HealthBar{
    public ChefHealthBar(Entity e) {
        super(((float) Main.getScreenWidth() / 2) - (Cell.getWidth() * 13),0, Cell.getWidth() * 26, Cell.getHeight(), Color.gray, Color.darkGray, e);
    }

    public void render(Graphics g){
        super.render(g);
        g.setColor(Color.red);
        g.drawString("Jerry: Boss of the Cafeteria", x, y + 50);
        if (e != null) {
            if (e.isDead()) {
                g.drawString("You have defeated Jerry!", x, y + 80);
            }
        }
    }
}
