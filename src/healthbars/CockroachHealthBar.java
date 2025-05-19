package healthbars;

import engine.Main;
import objects.entities.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import world.Cell;

public class CockroachHealthBar extends HealthBar {

    public CockroachHealthBar(Entity e) {
        super(((float) Main.getScreenWidth() / 2) - (Cell.getWidth() * 13), Cell.getHeight(), Cell.getWidth() * 26, Cell.getHeight(), new Color(128, 180, 37), new Color(100, 125, 100), e);
    }

    public void render(Graphics g){
        super.render(g);
        g.setColor(Color.red);
        g.drawString("Larry: Boss of the Sewers", x, y + 50);
        if (e != null) {
            if (e.isDead()) {
                g.drawString("You have defeated Larry!", x, y + 80);
            }
        }
    }
}