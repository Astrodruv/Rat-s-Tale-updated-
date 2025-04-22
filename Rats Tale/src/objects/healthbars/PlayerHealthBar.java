package objects.healthbars;

import objects.entities.Entity;
import objects.entities.player.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class PlayerHealthBar extends HealthBar {

    public PlayerHealthBar() {
        super(50, 50, 500, 70, Color.red, Color.darkGray);
    }

    public void render(Graphics g){
        g.setColor(color2);
        g.fillRect(x,y,w,h);
        g.setColor(color1);
        g.fillRect(x,y,w * Player.getPercentHealth(),h);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta){

    }

    public void collisions(StateBasedGame sbg){

    }

}
