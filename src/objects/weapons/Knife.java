package objects.weapons;

import objects.GameObject;
import objects.entities.Entity;
import org.newdawn.slick.Graphics;
import ui.Images;
import values.PlayerValues;

public class Knife extends GameObject {
    Entity entity;

    public Knife(float x, float y, Entity e) {
        super(x,y, Images.knife);
        entity = e;
    }

    public void render(Graphics g) {
        if (PlayerValues.doesPlayerHaveWeapon) {
            if (entity.isFacingRight()) {
                image.draw(entity.getX() + entity.getW(), entity.getY());
            }
            else{
                image.draw(entity.getX() - getW(), entity.getY());
            }
        }
    }
}
