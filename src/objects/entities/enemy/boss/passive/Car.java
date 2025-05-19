package objects.entities.enemy.boss.passive;

import engine.Game;
import engine.Main;
import objects.GameObject;
import objects.entities.Entity;
import objects.platforms.Platform;
import objects.world.Cell;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import ui.images.ImageRenderer;
import ui.text.Fonts;

import java.util.ArrayList;

public class Car extends Entity
{

    private float yVelocity;
    private float gravity;
    private float xAccel;
    private boolean onGround;
    private float xSpeed = (float) ((Math.random() * 5) + 10);
    public static int attackDamage;
    private float newX;
    private float newY;
    public static Image image = ImageRenderer.car;
    public Car(float x, float y) {
        super(Main.getScreenWidth(), y - Cell.getHeight(), 0, 0, 0, 20, image);
        this.x = Main.getScreenWidth();
        this.y = y - Cell.getHeight();
        attackDamage = 20;
        gravity = 1;
        yVelocity = 0;
        xAccel = 0;
        w = image.getWidth();
        h = image.getHeight();

    }

    public void render(Graphics g)
    {
        g.setColor(Color.green);
        image.draw(this.x, this.y);
     //   g.draw(getBounds());

       // g.setFont(Fonts.medium);
      //  g.drawString(""+y, 100,100);
      //  g.drawString(""+x,100,200);
    }
public float getXSpeed(){
        return xSpeed;
}
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        this.y += yVelocity;
        this.x = this.x - xSpeed;
        newX = this.x - xSpeed;
        newY = this.y + yVelocity;

        if (this.x < -50) {
            this.x = Main.getScreenWidth();
        }
    }

    public void collisions(StateBasedGame sbg)
    {
        onGround = false;
        Rectangle futureX = new Rectangle(newX, y - 1, w, h);
        Rectangle futureY = new Rectangle(newX, newY, w, h);
        for (GameObject o : new ArrayList<>(Game.levelObjects)){
            if (o instanceof Platform) {
                if (futureX.intersects(o.getBounds())) {
                    if (onGround) {
                        if (xSpeed > 0) {
                            newX = o.getX() - w;
                        } else if (xSpeed < 0) {
                            newX = o.getX() + o.getW();
                        }
                        xSpeed = 0;
                    }
                    else{
                        if (xSpeed > 0) {
                            newX = x;
                        } else if (xSpeed < 0) {
                            newX = x;
                        }
                        xSpeed = 0;
                    }
                }

                if (futureY.intersects(o.getBounds())){
                    if (yVelocity > 0 && !futureX.intersects(o.getBounds())) {
                        newY = o.getY() - h;
                        yVelocity = 0;
                        onGround = true;
                    } else if (yVelocity < 0 && ((Platform) o).isBottomPlatform() && futureX.intersects(o.getBounds())) {
                        newY = o.getY() + o.getH();
                        yVelocity = gravity;
                    }
                }
            }
//        for(GameObject o: Game.levelObjects)
//        {
//
//        }}
    }
}
}
