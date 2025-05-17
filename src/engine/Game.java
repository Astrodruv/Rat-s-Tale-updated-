package engine;

import objects.GameObject;
import objects.entities.player.Player;
import objects.healthbars.CockroachHealthBar;
import objects.healthbars.PlayerHealthBar;
import objects.platforms.gamePlatforms.SewerPlatform;
import objects.world.World;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import ui.images.ImageRenderer;
import ui.text.Fonts;

import java.util.ArrayList;

public class Game extends BasicGameState
{
    public static ArrayList<GameObject> levelObjects;
    private int id;

    public static boolean changeLevels;
    private World world;
    private static Player player;
    private static PlayerHealthBar playerHealthBar;
    private static CockroachHealthBar cockroachHealthBar;
    public static GameContainer gc;
    private int timer = 300;
    public static Image knifeDisplay;

    StateBasedGame sbg;

    public Game(int id) {
        this.id = id;
        levelObjects = new ArrayList<>();
        changeLevels = true;
    }

    public int getID()
    {
        return id;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        this.gc = gc;
        this.sbg = sbg;
        gc.setShowFPS(true);
        setLevel("levels/sewer1.txt");//DEBUG

        world = new World();
        playerHealthBar = new PlayerHealthBar();
        cockroachHealthBar = new CockroachHealthBar();
        Fonts.loadFonts();
        knifeDisplay = ImageRenderer.knifeInv;
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
    {
        world.update(gc, sbg, delta);
        playerHealthBar.update(gc, sbg, delta);
        if (World.level.equals("levels/sewer3.txt")) {
            cockroachHealthBar.update(gc, sbg, delta);
        }
timer--;
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
    {
        if(Player.level == 1){
            g.drawImage(ImageRenderer.streetBackground,0,0);
        } if(Player.level == 2){

                g.drawImage(ImageRenderer.schoolBackground, 0, 0);

        }  if(Player.level == 0){
        g.drawImage(ImageRenderer.sewerBackground,0,0);
if(World.level.equals("levels/sewer4.txt") && Player.knifeAttained){
    g.setFont(Fonts.medium);
    g.setColor(Color.white);
    g.drawString(" '1' to equip knife", Main.getScreenWidth()/2.3f, 700);
}

    }
        if(Player.knifeAttained){
            float knifeX;
            if(Player.level > 0){
                knifeX = Main.getScreenWidth() / 26f;
            } else {
                knifeX = Main.getScreenWidth() / 23f;
            }

            float knifeY = 50 + knifeDisplay.getHeight() + 10; // 20px padding from bottom
            knifeDisplay.draw(knifeX, knifeY);

        }
        //each level has end of level screen to unlock new ability/weapon? (ex sewer 4)
        world.render(g);
        playerHealthBar.render(g);
        if (World.level.equals("levels/sewer3.txt")) {
            cockroachHealthBar.render(g);
        }
        if(timer > 0){
            g.setFont(Fonts.medium);
            g.setColor(Color.white);
            g.drawString("W to jump, A/D to move left and right", (float) Main.getScreenWidth()/3,100);
            g.drawString("Spacebar to attack", (float) Main.getScreenWidth()/3, 200);
        }
        if(World.level.equals("levels/sewer4.txt")){
            timer = -10;
        }
if(World.level.equals("levels/sewer4.txt")){
    g.setColor(Color.red);
    g.setFont(Fonts.big);
    g.drawString("You have defeated the Cockroach!", (float) Main.getScreenWidth() / 2 - 300, 150);
}
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException
    {

    }

    public void leave(GameContainer gc, StateBasedGame sbg)
    {
        // This code happens when you leave a gameState.
    }

    public void keyPressed(int key, char c)
    {
        world.keyPressed(key, c);
    }

    public void mousePressed(int button, int x, int y) {

    }

    public static Player myPlayer(){
        return player;
    }

    public static PlayerHealthBar myPlayerHealthBar(){
        return playerHealthBar;
    }

    public static void setLevel(String s){
        levelObjects.clear();
        World.level = s;
        changeLevels = true;
    }

}
