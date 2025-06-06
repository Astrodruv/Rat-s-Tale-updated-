package objects.interactables;

import engine.Main;
import engine.states.Game;
import objects.GameObject;
import objects.entities.enemies.Chef;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import world.Cell;

import java.util.Random;

public class Food extends GameObject {

    private static final Random random = new Random();
    private static float foodChoice;
    private boolean generated = false;
    private boolean removed;

    private float xSpeed = 4;


    public Food(float x, float y) {
        super(x, y, Images.apple);
        randomizer();

        if(foodChoice == 0)
        {
            image = Images.apple;
        }
        else if(foodChoice == 1)
        {
            image = Images.broccoli;
        }
        else if(foodChoice == 2)
        {
            image = Images.chicken;
        }

    }

    public void render(Graphics g) {
        super.render(g);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        y = ((float) 1 /2000) * (x-Chef.getX) * (x-(Chef.getX + 50)) + 650;
        if(Chef.facingRight)
        {
            x += xSpeed;
        }
        else {
            x -= xSpeed;
        }
        if(y > Main.getScreenHeight() - Cell.getHeight())
        {
            removed = true;
        }
    }

    public void randomizer() {
        if (!generated) {
            foodChoice = random.nextInt(3);
            generated = true;
        }
    }
}
