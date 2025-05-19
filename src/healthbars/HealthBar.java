package healthbars;

import objects.entities.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class HealthBar {
    protected float x;
    protected float y;
    protected float w;
    protected float h;
    protected Entity e;
    protected Color color1;
    protected Color color2;

    public HealthBar(float x, float y, float w, float h, Color color1, Color color2, Entity e){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.e = e;
        this.color1 = color1;
        this.color2 = color2;
    }

    public void render(Graphics g){
        g.setColor(color2);
        g.fillRect(x,y,w,h);
        g.setColor(color1);
        if (e != null) {
            g.fillRect(x, y, w * e.getPercentHealth(), h);
        }
    }

    public Entity getEntity(){
        return e;
    }

}