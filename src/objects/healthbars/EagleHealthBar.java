package objects.healthbars;

import engine.Main;
import objects.entities.enemy.boss.attacking.Bird;
import objects.entities.enemy.boss.attacking.Cockroach;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class EagleHealthBar extends HealthBar
{

    public EagleHealthBar()
    {
        super(((float) Main.getScreenWidth() / 2) - 300, 150, 600, 50, Color.gray, Color.darkGray);
    }

    public void render(Graphics g)
    {
        g.setColor(color2);
        g.fillRect(x,y,w,h);
        g.setColor(color1);
        g.fillRect(x,y,w * Bird.getPercentHealth(),h);

        g.setColor(Color.red);
        g.drawString("Barry: Boss of the Streets", x, y + 35);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta){

    }

    public void collisions(StateBasedGame sbg){

    }



}
