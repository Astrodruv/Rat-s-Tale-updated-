package objects.entities.player;

import engine.Game;
import engine.Main;
import objects.GameObject;
import objects.entities.Entity;
import objects.entities.enemy.boss.attacking.Cockroach;
import object.entities.enemy.boss.passive.Car;
import objects.interactables.Key;
import objects.interactables.Knife;
import objects.interactables.Lock;
import objects.platforms.Platform;
import objects.world.Cell;
import objects.world.World;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import ui.images.ImageRenderer;
import ui.text.Fonts;

import java.util.ArrayList;


public class Player extends Entity {

    private boolean facingRight;
    private boolean onGround;
    private float xAccel;
    private float xVelocity;
    private float yVelocity;
    private float newX;
    private float newY;
    private final float gravity;
    public static int attackDamage;
    public boolean jumpingOffOfEnemy;
    public static float percentHealth;
    private static final int BASE_WIDTH = 1920;
    private static final int BASE_HEIGHT = 1080;
    public float cooldown;
    public boolean canAttack;
    public boolean attack;
    public static int level = 0;
    public static Image image;

    public static boolean keyAttained;
    public static boolean holdingKnife;
    public static boolean knifeAttained;

    private SpriteSheet mySheet;
    public SpriteSheet mySheet2;
    private Image currentFrame;
    private int step = 0;
    private int frames = 0;
    private int framesPerStep = 6;
    private int attackStep = 0;
    private int attackFrames = 0;
    private int streetTimer = 1800;
    private final int attackFramesPerStep = 5;
    private boolean isAttacking = false;
    private boolean hitFront = false;
    private Image currentAttackFrame;

    public Player(float x, float y) {
        image = ImageRenderer.ratIdle;

        super(x,y,Cell.getWidth() * ImageRenderer.screenRatio * 0.55f, Cell.getHeight() * ImageRenderer.screenRatio * 0.6f,100,10, image);
        this.x = x;
        this.y = y;
        float scaleX = (float) Main.getScreenWidth() / BASE_WIDTH;
        float scaleY = (float) Main.getScreenHeight() / BASE_HEIGHT;
        float scale = (scaleX + scaleY) / 2f;
        mySheet = ImageRenderer.rat;
        mySheet2 = ImageRenderer.knifeAttack;
        currentFrame = mySheet.getSprite(0,0);
        currentAttackFrame = mySheet2.getSprite(attackStep, 0).getScaledCopy(scale);
        if(Main.getScreenWidth() < 2256){
         xSpeed = 9.0f * scale;
             ySpeed = 20.0f * scale;
        } else {
             xSpeed = 10.0f * scale;
             ySpeed = 18.0f * scale;
        }


        facingRight = true;
        onGround = true;
        xVelocity = 0;
        yVelocity = 0;
        attackDamage = attackDmg;
        newX = 0;
        newY = 0;
        xAccel = 0;
        gravity = 1;
        jumpingOffOfEnemy = false;
        keyAttained = false;// DEBUG

        percentHealth = (float) curHealth / maxHealth;

        cooldown = 90;

        canAttack = false;
        attack = false;

if(level > 0){
    knifeAttained = true;
} else {
    knifeAttained = false; //DEBUG
}
    }

    public void render(Graphics g){
        g.setColor(Color.darkGray);

            float renderOffsetY = h - currentFrame.getHeight();

            if (xVelocity == 0 && xAccel == 0) {
                if (facingRight) {
                    image.draw(x, y + h - image.getHeight());
                } else {
                    image.getFlippedCopy(true, false).draw(x, y + h - image.getHeight());
                }
            } else if (facingRight) {
                currentFrame.draw(x, y + renderOffsetY);
            } else {
                currentFrame.getFlippedCopy(true, false).draw(x, y + renderOffsetY);
            }


        g.setColor(Color.orange);
        g.fillRect(x+30, y-50, (w+50), 10);
        g.setColor(Color.yellow);
        if(cooldown > 0)
        {
            g.fillRect(x+30, y-50, (w+50) * ((90-cooldown)/90), 10);
        }
        else
        {
            g.fillRect(x+30, y-50,w+50, 10);

        }
//        g.draw(getBounds());
//        g.draw(getWeaponBounds(facingRight));
//        g.drawString(""+maxHealth, 500, 500);
//        g.drawString(""+curHealth, 500, 600);
//        g.drawString(""+getPercentHealth(), 500, 700);
//        g.drawString(""+Cockroach.getAttackDamage(), 500, 800);
//        g.drawString(""+cooldown, 700, 500);
//        g.drawString(""+canAttack, 700, 700);
//        g.drawString(""+attack, 700, 900);
//        g.drawString(""+xSpeed, 300, 900);
//        g.drawString(""+invincibilityFrames, 1000, 900);
//        g.drawString(""+isHit,1000,1100);
//        g.drawString(""+holdingKnife, 1100, 1200);
        if(World.level.equals("levels/street1.txt") || World.level.equals("levels/street2.txt")){
            g.setFont(Fonts.big);
            g.setColor(Color.white);
            g.drawString("Survive for "+streetTimer/60 + "s", Main.getScreenWidth()/2, Main.getScreenHeight()/2 -500);
             if(streetTimer > 1200) {
                g.setFont(Fonts.big);
                g.setColor(Color.red);
                g.drawString("Dont get hit!", Main.getScreenWidth()/2, Main.getScreenHeight()/2 - 400);
            }
        }

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta){
        super.update(gc, sbg, delta);
        if(World.level.equals("levels/street1.txt") || World.level.equals("levels/street2.txt")){
            streetTimer--;
        }
        if(streetTimer<0 ){
            if(World.level.equals("levels/street1.txt")){
                Game.setLevel("levels/street2.txt");
streetTimer=1800;
            }
            else if(World.level.equals("levels/street2.txt")){
                level++;
                Game.setLevel("levels/school.txt");
            }
        }
        if(holdingKnife){
            image = ImageRenderer.ratKnife;
        } else{
            image = ImageRenderer.ratIdle;
        }
        frames++;

        if(frames % framesPerStep == 0)
        {
            step++;
        }
        if(step >= mySheet.getHorizontalCount())
        {
            step = 0;
        }
        currentFrame = mySheet.getSprite(step, 0);
        percentHealth = (float) curHealth / maxHealth;

        Input input = gc.getInput();

        cooldown--;
        if(cooldown < 0)
        {
            canAttack = true;
        }

          if (input.isKeyDown(Input.KEY_D)) {
           moveRight();
              facingRight = true;
         } else if (input.isKeyDown(Input.KEY_A)) {
        moveLeft();
        facingRight = false;
    } else {
        if (xAccel > 0) {
            xVelocity = xAccel;
            xAccel--;
            if (xAccel < 0) xAccel = 0;
        } else if (xAccel < 0) {
            xVelocity = xAccel;
            xAccel++;
            if (xAccel > 0) xAccel = 0;
        } else {
            xVelocity = 0;
            xAccel = 0;
        }
    }
    yVelocity += gravity;

    newX = x + xVelocity;
    newY = y + yVelocity;

        if (input.isKeyDown(Input.KEY_W) && onGround && !jumpingOffOfEnemy){
            jump();
        }

        collisions(sbg);

        if(isDead)
        {
            cell.removeObject();
            percentHealth = 0;
        }

        x = newX;
        y = newY;
        if (attack && holdingKnife) {
            isAttacking = true;
            attackFrames++;

            if (attackFrames % attackFramesPerStep == 0) {
                attackStep++;
                if (attackStep >= mySheet2.getHorizontalCount()) {
                    // End of animation
                    attack = false;
                    isAttacking = false;
                    attackStep = 0;
                    attackFrames = 0;
                }
            }

            // Get current frame
            currentAttackFrame = mySheet2.getSprite(attackStep, 0).getScaledCopy(ImageRenderer.screenRatio);

        }
    }

    public void moveLeft(){
        xVelocity = -xSpeed + xAccel;
        xAccel -= 0.1f;
    }

    public void moveRight(){
        xVelocity = xSpeed + xAccel;
        xAccel += 0.1f;
    }

    public void jump(){
        yVelocity = -ySpeed;
        onGround = false;
    }

    public void collisions(StateBasedGame sbg) {
        onGround = false;
        Rectangle futureX = new Rectangle(newX, y - 1, w, h);
        Rectangle futureY = new Rectangle(newX, newY, w, h);
if(level > 0){
    keyAttained = true;
}
        for (GameObject o : new ArrayList<>(Game.levelObjects)){
            if (o instanceof Platform) {
                if (futureX.intersects(o.getBounds())) {
                    if (onGround) {
                        if (xVelocity > 0) {
                            newX = o.getX() - w;
                        } else if (xVelocity < 0) {
                            newX = o.getX() + o.getW();
                        }
                        xVelocity = 0;
                    }
                    else{
                        if (xVelocity > 0) {
                            newX = x;
                        } else if (xVelocity < 0) {
                            newX = x;
                        }
                        xVelocity = 0;
                    }
                }

                if (futureY.intersects(o.getBounds())){
                    if (yVelocity > 0 && !futureX.intersects(o.getBounds())) {
                        newY = o.getY() - h;
                        yVelocity = 0;
                        onGround = true;
                    } else if (yVelocity < 0 && ((Platform) o).isBottomPlatform() && futureX.intersects(o.getBounds())) {
                        newY = o.getY() + o.getH();
                        yVelocity = gravity;
                    }
                }
            }
             if (o instanceof Car) {
                Car car = (Car) o;
                Rectangle carBounds = car.getBounds();
                if (futureX.intersects(o.getBounds())) {
                    boolean hitFront = false;
                        if (newX <= car.getX() + 5) {
                            hitFront = true;
                        }

                    if (hitFront) {

                        takeDamage(Car.attackDamage);

                        jump();
                    }
                    if(xAccel == 0 && hitFront){
                        jump();
                    }

                    if (xVelocity > 0) {
                        newX = car.getX() - w;
                    } else if (xVelocity < 0) {
                        newX = car.getX() + car.getW();
                    }
                }
                if (futureY.intersects(o.getBounds())) {
                    if ((y <= car.getY() + 10)) {
                        newY = car.getY() - h;
                        onGround = true;

                    }
                }
            }

            if (o instanceof Lock){
                if (!keyAttained){
                    if (futureX.intersects(o.getBounds())) {
                        if (onGround) {
                            if (xVelocity > 0) {
                                newX = o.getX() - w;
                            } else if (xVelocity < 0) {
                                newX = o.getX() + o.getW();
                            }
                            xVelocity = 0;
                        }
                        else{
                            if (xVelocity > 0) {
                                newX = x;
                            } else if (xVelocity < 0) {
                                newX = x;
                            }
                            xVelocity = 0;
                        }
                    }

                    if (futureY.intersects(o.getBounds())) {
                        if (yVelocity > 0 && !futureX.intersects(o.getBounds())) {
                            newY = o.getY() - h;
                            yVelocity = 0;
                            onGround = true;
//                            if (jumpingOffOfEnemy) jumpingOffOfEnemy = false;
                        }
                    }
                }
                else{
                    if (getBounds().intersects(o.getBounds()) && Player.keyAttained) {
                        if (World.level.equals("levels/sewer1.txt")){
                            Game.setLevel("levels/sewer2.txt");
                            keyAttained = false;
                        }
                    }
                    if (getBounds().intersects(o.getBounds()) && Player.keyAttained) {
                        if (World.level.equals("levels/sewer2.txt")){
                            Game.setLevel("levels/sewer3.txt");
                        }
                    }
                    if (getBounds().intersects(o.getBounds()) && Player.keyAttained) {

                        if (World.level.equals("levels/sewer4.txt")){
                            level++;
                            Game.setLevel("levels/street1.txt");
                        }
                    }


                }
            }
            

            if (o instanceof Key){
                if (getBounds().intersects(o.getBounds())) {
                    keyAttained = true;
                }
            }
            if(o instanceof Knife){
                if(getBounds().intersects(o.getBounds())){
                    knifeAttained = true;

                }
            }

            if (o instanceof Cockroach && !Cockroach.isDead){
                Rectangle ratBounds = getBounds();
                Rectangle oBounds = o.getBounds();
                Rectangle weaponBounds = getWeaponBounds(facingRight);

                if (ratBounds.intersects(oBounds)) {
                    if (ratBounds.getMaxY() <= oBounds.getMinY() + 5 && ratBounds.getMinY() < oBounds.getMinY()) {
                        Cockroach.isDamaged = true;
                        onGround = true;
                        jumpingOffOfEnemy = true;
                        jump();
                        jumpingOffOfEnemy = false;
                        if(!Cockroach.onGround)
                        {
                           takeDamage(Cockroach.attackDamage);
                        }
                    }
                    else{
                            takeDamage(Cockroach.attackDamage);
                            System.out.println("Damage");
                    }
                }
                if(weaponBounds.intersects(oBounds))
                {
                    if(attack)
                    {
                        Cockroach.isDamaged = true;
                        attack = false;
                    }
                }
            }

        }
    }

    public void keyPressed(int key, char c){
        if (key == Input.KEY_D && !facingRight){

            xAccel = 0;
            facingRight = true;
        }
        if (key == Input.KEY_A && facingRight){
            xAccel = 0;
            facingRight = false;

        }
        if(key == Input.KEY_SPACE && canAttack)
        {
            attack = true;
            isAttacking = true;
            attackFrames = 0;
            attackStep = 0;
            cooldown = holdingKnife ? 30 : 90;
            canAttack = false;

        }
        if(key == Input.KEY_1 && knifeAttained){
            holdingKnife = !holdingKnife;
                cooldown = 30;
        }

    }
    public float newX(){
        return newX;
    }

    public float newY(){
        return newY;
    }

    public static int getAttackDamage(){
        return attackDamage;
    }

    public static float getPercentHealth(){
        return percentHealth;
    }

}
