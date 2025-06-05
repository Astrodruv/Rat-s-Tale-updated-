package engine.states;

import engine.Main;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import ui.Fonts;
import ui.Images;

import ui.Button;
import ui.Message;
import world.Cell;

import java.util.ArrayList;

public class TitleScreen extends BasicGameState {
    private int id;

    public TitleScreen(int id) {
        this.id = id;
    }

    public int getID()
    {
        return id;
    }

    public static GameContainer gc;
    StateBasedGame sbg;

    private Button startButton;
    private Button controlsButton;
    private Button loreButton;
    private Button goBackButton;

    private ArrayList<Message> instructions;
    private ArrayList<Message> lore;

    private int screenType;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        this.gc = gc;
        this.sbg = sbg;
        screenType = 0;
        Images.loadImages();
        Fonts.loadFonts();
        startButton = new Button(Main.getScreenWidth() - (Cell.getWidth() * 13), Cell.getHeight() * 2, (int) Cell.getWidth() * 10, (int) Cell.getHeight() * 2, "Start", new Color(217, 140, 0), Color.black, Color.yellow, Fonts.titleScreenButtonFont);
        controlsButton = new Button(Main.getScreenWidth() - (Cell.getWidth() * 13), Cell.getHeight() * 6, (int) Cell.getWidth() * 10, (int) Cell.getHeight() * 2, "Controls", new Color(217, 140, 0), Color.black, Color.yellow, Fonts.titleScreenButtonFont);
        loreButton = new Button(Main.getScreenWidth() - (Cell.getWidth() * 13), Cell.getHeight() * 10, (int) Cell.getWidth() * 10, (int) Cell.getHeight() * 2, "Lore", new Color(217, 140, 0), Color.black, Color.yellow, Fonts.titleScreenButtonFont);
        goBackButton = new Button(Main.getScreenWidth() - (Cell.getWidth() * 13), Cell.getHeight(), (int) Cell.getWidth() * 10, (int) Cell.getHeight() * 2, "Back", new Color(217, 140, 0), Color.black, Color.yellow, Fonts.titleScreenButtonFont);

        instructions = new ArrayList<>();
        lore = new ArrayList<>();

        instructions.add(new Message(Cell.getWidth(), Cell.getHeight(), "Movement Controls:", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
        instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 2, "W - Jump:", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
        instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 3, "A - Move Left", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
        instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 4, "D - Move Right", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
        instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 5, "S - Dash", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));

        instructions.add(new Message(Cell.getWidth(), Cell.getHeight() * 6, "Attacking Controls: ", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
        instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 7, "Jump on Enemies to Damage Them", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
        instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 8, "Space - Use Knife - LOCKED", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
        instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 9, "Space - Use Glock - LOCKED", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));

        instructions.add(new Message(Cell.getWidth(), Cell.getHeight() * 10, "Hotkeys:", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
        instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 11, "1 - Equip Knife - LOCKED", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
        instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 12, "2 - Equip Glock - LOCKED", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
        instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 13, "P - Pause & Settings", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));

        instructions.add(new Message(Cell.getWidth(), Cell.getHeight() * 14, "Collect the key or beat the boss to unlock the door and progress to the next level!", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        if (screenType == 0) { // Title Screen
            g.drawImage(Images.titleScreen, 0, 0);
            startButton.render(g);
            controlsButton.render(g);
            loreButton.render(g);
        }
        if (screenType == 1) { // Instructions Screen (Maybe put pictures of rat? IDK)
            g.drawImage(Images.loreInstructionsBackground, 0, 0);
            goBackButton.render(g);
            for (Message m : instructions){
                m.render(g);
            }
        }
        if (screenType == 2) { // Lore Screen (Maybe put pictures of rat? IDK)
            g.drawImage(Images.loreInstructionsBackground, 0, 0);
            goBackButton.render(g);
            for (Message m : lore){
                m.render(g);
            }
        }
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        // This code happens when you enter a gameState.
    }

    public void leave(GameContainer gc, StateBasedGame sbg)
    {
        // This code happens when you leave a gameState.
    }

    public void keyPressed(int key, char c)
    {
        // This code happens every time the user presses a key
    }

    public void mousePressed(int button, int x, int y) {
        if (screenType == 0) {
            if (startButton.isMouseOver(x, y)) this.sbg.enterState(Main.GAME_ID);
            if (controlsButton.isMouseOver(x, y)) screenType = 1;
            if (loreButton.isMouseOver(x, y)) screenType = 2;
        }
        if (screenType == 1 || screenType == 2){
            if (goBackButton.isMouseOver(x,y)) screenType = 0;
        }
    }
}
