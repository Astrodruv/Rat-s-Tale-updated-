package objects;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import world.Cell;

public class GameObject {
    protected Cell cell;
    protected Image image;
    protected float x;
    protected float y;
    protected float w;
    protected float h;

    public GameObject(float x, float y, Image image){
        this.x = x;
        this.y = y;
        this.w = image.getWidth();
        this.h = image.getHeight();
        this.image = image;
    }

    public void setCell(Cell c){
        this.cell = c;
    }

    public void render(Graphics g){
        image.draw(x,y);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta){

    }

    public void keyPressed(int key, char c){

    }

    public void collisions(StateBasedGame sbg){

    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getW(){
        return w;
    }

    public float getH(){
        return h;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,w,h);
    }

    public Rectangle getWeaponBounds(boolean facingRight)
    {
        if(facingRight)
        {
            return new Rectangle(x + w, y - Cell.getHeight(), Cell.getWidth() * 6, Cell.getHeight() * 3);
        }
        else {
            return new Rectangle(x - w - (Cell.getWidth() * 3), y - Cell.getHeight(), Cell.getWidth() * 6, Cell.getHeight() * 3);
        }
    }

    public Rectangle getJanitorBounds(boolean facingRight)
    {
        if(!facingRight)
        {
            return new Rectangle(x,y,w,h);
        }
        else {
            return new Rectangle(x + (Cell.getWidth() * 3), y, w, h);
        }
    }

}
