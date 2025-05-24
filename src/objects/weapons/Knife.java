package objects.weapons;

import objects.GameObject;
import objects.entities.Entity;
import objects.entities.Player;
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
            if (entity.isFacingRight() && Player.holdingKnife) {
                image.draw(entity.getX() + entity.getW(), entity.getY());
            }
            else if(!entity.isFacingRight() && Player.holdingKnife ){
                image.getFlippedCopy(true,false).draw(entity.getX() - getW(), entity.getY());
            }
        }
    }
}
