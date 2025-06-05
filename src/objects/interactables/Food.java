package objects.interactables;

import engine.states.Game;
import objects.GameObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.ChefValues;
import values.PlayerValues;

import java.util.Random;

public class Food extends GameObject {

    private static final Random random = new Random();
    private static float foodChoice;
    private boolean generated = false;

    private float xSpeed;
    private float ySpeed;

    public Food(float x, float y) {
        super(x, y, Images.apple);
        randomizer();

//        if(foodChoice == 0)
//        {
//            image = Images.apple;
//        }
//        else if(foodChoice == 1)
//        {
//            image = Images.broccoli;
//        }
//        else if(foodChoice == 2)
//        {
//            image = Images.chicken;
//        }

    }

    public void render(Graphics g) {
        super.render(g);
        g.drawRect(x, y, w, h);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        y++;
    }

    public void randomizer() {
        if (!generated) {
            foodChoice = random.nextInt(3);
            generated = true;
        }
    }
}
