package objects.entities.enemy.boss.attacking;

import engine.Main;
import objects.entities.Entity;
import objects.world.Cell;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;
import ui.images.ImageRenderer;

public class Bird extends Entity{

    private float x;
    private float y;

    private float xSpeed;
    private float ySpeed;

    private boolean Right;

    public static float percentHealth;
    public static int attackDamage;

    public Bird(float x, float y)
    {
        super(x, y, Cell.getWidth() * ImageRenderer.screenRatio * 0.5f, Cell.getHeight() * ImageRenderer.screenRatio, 100, 20, ImageRenderer.birdIdle);

        this.x = Main.getScreenHeight();
        this.y = y;

        xSpeed = 5;
        ySpeed = 0;

        attackDamage = attackDmg;
        percentHealth = (float) curHealth / maxHealth;
    }

    public void render(Graphics g)
    {
        image.draw(x,y);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        percentHealth = (float) curHealth / maxHealth;

        y = (x) * (x-(Main.getScreenWidth() - image.getWidth()));
        x += 1;
    }

    public void collisions(StateBasedGame sbg)
    {

    }

    public static int getAttackDamage(){
        return attackDamage;
    }

    public static float getPercentHealth()
    {
        return percentHealth;
    }
}
