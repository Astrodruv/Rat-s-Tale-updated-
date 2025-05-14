package objects.entities.enemy.boss.passive;

import engine.Game;
import engine.Main;
import objects.GameObject;
import objects.entities.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;
import ui.images.ImageRenderer;

public class Car extends Entity
{
    private int x;
    private float xSpeed = (float) ((Math.random() * 5) + 2);
    public static int attackDamage;

    public Car(float x, float y) {
        super(x, y, 0, 0, 0, 20, ImageRenderer.key);
        this.x = Main.getScreenWidth() + image.getWidth();
        attackDamage = 20;
    }

    public void render(Graphics g)
    {
        g.setColor(Color.green);
        g.drawRect(x,y, 300,100);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        x = (int) (x - xSpeed);
    }

    public void collisions(StateBasedGame sbg)
    {

        for(GameObject o: Game.levelObjects)
        {

        }
    }
}
