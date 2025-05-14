package objects.entities.enemy.boss.attacking;

import engine.Game;
import engine.Main;
import objects.GameObject;
import objects.entities.Entity;
import objects.entities.player.Player;
import objects.platforms.Platform;
import objects.world.Cell;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import ui.images.ImageRenderer;

public class Bird extends Entity{

    private float x;
    private float y;

    private float xSpeed;
    private float ySpeed;
    private float flightTime;
    private float flightHeight;

    private boolean Right;

    public static float percentHealth;
    public static int attackDamage;
    public static boolean isDead;
    public static boolean isDamaged;

    public static boolean ouch;

    public Bird(float x, float y)
    {
        super(x, y, Cell.getWidth() * ImageRenderer.screenRatio * 0.5f, Cell.getHeight() * ImageRenderer.screenRatio, 100, 30, ImageRenderer.birdIdle);

        this.x = x;
        this.y = y;

        xSpeed = 4;
        ySpeed = 0;
        flightTime = 3 * 60;
        flightHeight = (float) (Math.random() * 500) + 500;

        attackDamage = attackDmg;
        percentHealth = (float) curHealth / maxHealth;
        isDead = false;
        isDamaged = isHit;

        ouch = false;
    }

    public void render(Graphics g)
    {
        image.draw(x,y);
        g.drawString(""+x,900, 500);
        g.drawString(""+isDamaged, 900, 700);
        g.setColor(Color.red);
        g.drawString(""+curHealth, 900,900);
        g.drawString(""+ouch, 1100, 500);
        g.drawRect(x,y,w,h);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        percentHealth = (float) curHealth / maxHealth;

        if (isDamaged){
            takeDamage(Player.getAttackDamage());
            if(curHealth <= 0)
            {
                isDead = true;
            }
            isDamaged = false;
        }
        if(isDead)
        {
            cell.removeObject();
            percentHealth = 0;
        }


        y = (((float) -1 / (1000)) * (x) * (x - (Main.getScreenWidth() - image.getWidth())));

        if(y < -200)
        {
            xSpeed *= -1;
            image = image.getFlippedCopy(true, false);
        }

        x += xSpeed;

        collisions(sbg);
    }

    public void collisions(StateBasedGame sbg) {
        Rectangle bird = new Rectangle(x,y,w,h);

        for (GameObject o : Game.levelObjects)
        {

        }
    }

    public static int getAttackDamage(){
        return attackDamage;
    }

    public static float getPercentHealth()
    {
        return percentHealth;
    }
}
