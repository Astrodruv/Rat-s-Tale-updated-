package healthbars;

import engine.Main;
import objects.entities.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import world.Cell;

public class CockroachHealthBar extends HealthBar {

    public CockroachHealthBar(Entity e) {
        super(((float) Main.getScreenWidth() * ((float) 2/3)) - (Cell.getWidth() * 11), Cell.getHeight() * ((float)2/3), Cell.getWidth() * 26, Cell.getHeight(), new Color(128, 180, 37), new Color(100, 125, 100), e);
    }

    public void render(Graphics g){
        super.render(g);
        g.setColor(Color.red);
        g.drawString("Barry: Boss of the Sewers", x + 10, y + 50);
        if (e != null) {
            if (e.isDead()) {
                g.drawString("You have defeated Barry!", x, y + 80);
            }
        }
    }
}
