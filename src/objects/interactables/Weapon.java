package objects.interactables;

import objects.GameObject;
import objects.entities.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.PlayerValues;
import world.Cell;

public class Weapon extends GameObject {

    public Weapon(float x, float y) {
        super(x,y, Images.knife);
    }

    public void render(Graphics g) {
        if (!PlayerValues.doesPlayerHaveWeapon) {
            super.render(g);
        }
        else{

        }
    }

}
