package objects.entities.enemy.boss.passive;

import objects.entities.Entity;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

public class Janitor extends Entity {

    public Janitor(float x, float y, float xSpeed, float ySpeed, int health, int attackDamage, Image image) {
        super(x, y, xSpeed, ySpeed, health, attackDamage, image);
    }

    public void collisions(StateBasedGame sbg) {

    }
}
