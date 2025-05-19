package healthbars;

import engine.Main;
import objects.entities.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import world.Cell;

public class BirdHealthBar extends HealthBar{

    public BirdHealthBar(Entity e) {
        super(((float) Main.getScreenWidth() / 2) - (Cell.getWidth() * 13), Cell.getHeight(), Cell.getWidth() * 26, Cell.getHeight(), Color.gray, Color.darkGray, e);
    }

    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.red);
        g.drawString("Barry: Boss of the Streets", x, y + 35);
        if (e != null) {
            if (e.isDead()) {
                g.drawString("You have defeated Barry!", x, y + 80);
            }
        }
    }

}