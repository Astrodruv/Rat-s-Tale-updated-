package healthbars;

import engine.Main;
import objects.entities.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import ui.Fonts;
import world.Cell;

public class BirdHealthBar extends HealthBar{

    public BirdHealthBar(Entity e) {
        super(((float) Main.getScreenWidth() / 2) - (Cell.getWidth() * 13),0, Cell.getWidth() * 26, Cell.getHeight(), Color.gray, Color.darkGray, e);
    }

    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.red);
        g.setFont(Fonts.titleScreenButtonFont);
        if (e != null) {
            if (e.isDead()) {
                g.setColor(Color.yellow);
                g.drawString("You Have Defeated Barry!", x + (float) Fonts.titleScreenButtonFont.getWidth("You Have Defeated Barry!") / 2, y + Cell.getHeight() / 2 - ((float) Fonts.titleScreenButtonFont.getHeight() / 2));
            }
            else{
                g.drawString("Barry: Boss of the Streets", x + (float) Fonts.titleScreenButtonFont.getWidth("Barry: Boss of the Streets") / 2, y + Cell.getHeight() / 2 - ((float) Fonts.titleScreenButtonFont.getHeight() / 2));
            }
        }
    }

}
