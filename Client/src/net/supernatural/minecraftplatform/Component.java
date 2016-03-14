package net.supernatural.minecraftplatform;

import java.applet.*; // Extend 
import javax.swing.*; //
import java.awt.*;
import java.util.*;


public class Component extends Applet implements Runnable {
	private static final long serialVersionUID = 1L; // Compilation reference (not too important)
	
	public static int pixelSize = 2; //Pixel size
	
	public static int moveFromBorder = 0;
	public static double sX = moveFromBorder, sY = moveFromBorder; //Side scroller functions
	public static double dir = 0;
	
	public static Dimension realSize;
	public static Dimension size = new Dimension(700, 560);
	public static Dimension pixel = new Dimension(size.width / pixelSize, size.height / pixelSize);
	
	public static Point mse = new Point(0, 0);
	
	public static String name = "Haven";
	
	public static boolean isRunning = false;
	public static boolean isMoving = false;
	public static boolean isJumping = false;
	public static boolean isMouseLeft = false;
	public static boolean isMouseRight = false;
	
	private Image screen;
	
	public static Level level;
	public static Character character;
	public static Inventory inventory;
	public static Sky sky;
	public static ArrayList<Mob> mob = new ArrayList<Mob>();
	
	public Component() {
		setPreferredSize(size);
		
		addKeyListener(new Listening());
		addMouseListener(new Listening());
		addMouseMotionListener(new Listening());
		addMouseWheelListener(new Listening());
	}
	
	public void start(){
		requestFocus();
		
		//Defining objects, etc
		new Tile(); //Loading images
		level = new Level();
		character = new Character(Tile.tileSize, Tile.tileSize * 2);
		inventory = new Inventory();
		sky = new Sky();
		mob.add(new Grunt(50, 10, Tile.tileSize, Tile.tileSize * 2, Tile.mobGrunt));
		
		//Starting game loop.
		isRunning = true;
		new Thread(this).start(); // run more than one thread at once
	}
	
	public void stop() {
		isRunning = false;
		
	}
	//Game loop - something that runs all the time, used for tracking movement, click tracking, manages timing, etc. ALL games need a game loop
	
	private static JFrame frame;
	public static void main(String args[]) {
		Component component = new Component(); // Create new component for other stuffs
		
		frame = new JFrame();
		frame.add(component); // Add applet onto JFrame
		frame.pack();
		
		realSize = new Dimension(frame.getWidth(), frame.getHeight()); // What the frame holds
		
		frame.setTitle(name);
		frame.setResizable(true); // Adjust sizing
		frame.setLocationRelativeTo(null); // Center alignment
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Enables window to be closed; prevents having to use activity handler
		frame.setVisible(true);
		
		component.start(); 										// goes to constructor, then packs frame to screen
	}
	
	
	
	public void tick() {										//Processes in game movement
		if(frame.getWidth() != realSize.width || frame.getWidth() != realSize.height) {					// If conditions are true..
			frame.pack(); //...Will change to whatever the applet size is
		}
		
		character.tick();
		level.tick((int) sX, (int) sY, (pixel.width / Tile.tileSize) + 2, (pixel.height/ Tile.tileSize) + 2);
		sky.tick();
		
		for(int i = 0; i < mob.toArray().length; i++) {
			mob.get(i).tick();
		}
	}
	
	public void render() { 										// Draws stuff on your screen (High priority; test constantly)
		Graphics g = screen.getGraphics(); 						// Draws images on screen, drawn on top of screen Image
		
		//Drawing things
		sky.render(g);
		
		level.render(g, (int) sX, (int) sY, (pixel.width / Tile.tileSize) + 2, (pixel.height/ Tile.tileSize) + 2); 										// This will allow you to draw on top of screen Image
		character.render(g);
		inventory.render(g);
		
		for(int i = 0; i < mob.toArray().length; i++) {
			mob.get(i).render(g);
		}
		
		g = getGraphics(); // Pulls actual surface
		
		g.drawImage(screen,  0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);
		g.dispose(); // Throw away to prevent too many graphics objects
		
	}
	
	
	public void run() {
		screen = createVolatileImage(pixel.width, pixel.height); //fully accessible from graphics card - prevents heavy lag
		
		while(isRunning) {								// This command can cause crashes, infinite loop check
			tick(); 									//ALWAYS tick method before render method
			render();
	
			try {
				Thread.sleep(5); 						//Call thread, sleep with object (stop game for 5 milliseconds, try again)
			} catch(Exception e) {}
		}
	}
}

