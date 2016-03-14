package net.supernatural.minecraftplatform;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Tile { //will hold character animation, stuff
	public static int tileSize = 20;
	public static int invLength = 8;
	public static int invHeight = 4;
	public static int invCellSize = 25;
	public static int invCellSpace = 4;
	public static int invBorderSpace = 4;
	public static int invItemBorder = 3;
	
	public static int[] air = {-1, -1};
	public static int[] earth = {0, 0};
	public static final int[] grass = {1, 0};
	public static final int[] sand = {2, 0};
	public static final int[] solidair = {3, 0};
	public static final int[] wood = {4, 0};
	
	public static int[] mobGrunt = {0, 16};
	public static int[] character = {0, 18};
	
	public static BufferedImage tileset_terrain;
	public static BufferedImage tile_cell;
	public static BufferedImage tile_select;
	
	public Tile() {
		try {
			Tile.tileset_terrain = ImageIO.read(new File("resource/tileset_terrain.png"));
			Tile.tile_cell = ImageIO.read(new File("resource/tile_cell.png"));
			Tile.tile_select = ImageIO.read(new File("resource/tile_select.png"));			//Takes file from PC folder
		} catch(Exception e) { }
	}
}
