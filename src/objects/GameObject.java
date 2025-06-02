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
            return new Rectangle(x + (w / 2), y - Cell.getHeight() / 2, Cell.getWidth() * 5, Cell.getHeight() + Cell.getHeight());
        }
        else {
            float width = Cell.getWidth() * 5;
            return new Rectangle(x - (width) + (width / 4), y - Cell.getHeight() / 2, width, Cell.getHeight() + Cell.getHeight());
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
