package healthbars;

import engine.Main;
import objects.entities.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import ui.Fonts;
import world.Cell;

public class JanitorHealthBar extends HealthBar {

    public JanitorHealthBar(Entity e) {
        super(((float) Main.getScreenWidth() / 2) - (Cell.getWidth() * 13), 0, Cell.getWidth() * 26, Cell.getHeight(), new Color(128, 180, 37), new Color(100, 125, 100), e);
    }

    public void render(Graphics g){
        super.render(g);
        g.setColor(Color.red);
        g.setFont(Fonts.titleScreenButtonFont);
        if (e != null) {
            if (e.isDead()) {
                g.setColor(Color.yellow);
                g.drawString("You Have Defeated Jerry!", x + (float) Fonts.titleScreenButtonFont.getWidth("You Have Defeated Jerry!") / 2, y + Cell.getHeight() / 2 - ((float) Fonts.titleScreenButtonFont.getHeight() / 2));
            }
            else{
                g.drawString("Jerry: Boss of the Closet", x + (float) Fonts.titleScreenButtonFont.getWidth("Jerry: Boss of the Closet") / 2, y + Cell.getHeight() / 2 - ((float) Fonts.titleScreenButtonFont.getHeight() / 2));
            }
        }
    }
}
