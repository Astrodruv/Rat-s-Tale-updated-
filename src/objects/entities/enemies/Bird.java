package objects.entities.enemies;

import engine.Main;
import objects.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.BirdValues;
import values.PlayerValues;
import world.World;

public class Bird extends Entity{

    private float flightTime;
    private float flightHeight;

    public Bird(float x, float y) {
        super(x, y, BirdValues.X_SPEED, BirdValues.Y_SPEED, BirdValues.HEALTH, BirdValues.ATTACK, Images.birdIdle, BirdValues.IFRAMES);
        facingRight = true;
        flightTime = 3 * 60;
        flightHeight = (float) (Math.random() * 500) + 500;
    }

    public void render(Graphics g) {
        super.render(g);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        if (World.level.equals(BirdValues.LEVEL_SPAWN_LOCATION) && PlayerValues.isPlayerHurtingEnemy){
            isHit = true;
            PlayerValues.isPlayerHurtingEnemy = false;
        }

        if (isHit && !hasTakenDamage){
            takeDamage(PlayerValues.ATTACK);
            hasTakenDamage = true;
        }

        y = (((float) -1 / (1000)) * (x) * (x - (Main.getScreenWidth() - image.getWidth())));

        if(y < -200)
        {
            xSpeed *= -1;
            image = image.getFlippedCopy(true, false);
        }

        x += xSpeed;

        super.update(gc, sbg, delta);
    }

}