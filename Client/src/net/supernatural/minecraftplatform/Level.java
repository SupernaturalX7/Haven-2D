package net.supernatural.minecraftplatform;

import java.awt.*;
import java.util.*;

public class Level {
	public int worldW = 50, worldH = 50;
	public Block[][] block = new Block[worldW][worldH]; 														//multi-dimensional array, will hold all blocks
	
	public Level(){		// Constructor
		for(int x=0;x<block.length;x++) {														//Starts in left top corner; creates coordinate grid for block placement. Increment x to continue direction
			for(int y=0;y<block[0].length;y++){ 														//Starts in left top corner; creates coordinate grid for block placement. Increment y to continue direction
				block[x][y] = new Block(new Rectangle(x * Tile.tileSize, y * Tile.tileSize,  Tile.tileSize, Tile.tileSize), Tile.air); // RECHECK THIS LINE
			}
		}
		
		generateLevel();
	}
	
	public void generateLevel() { 															//If x is right top corner of screen, if y = 0 (top) if x = 0 (left)
		//Generate mountains, earth, etc
		for(int y=0;y<block.length;y++) {													//Starts in left top corner; creates coordinate grid for block placement. Increment x to continue direction
			for(int x=0;x<block[0].length;x++){ 											//Starts in left top corner; creates coordinate grid for block placement. Increment y to continue direction
				if(y > worldH / 4) {
					if (new Random().nextInt(100) > 20) {
						try {
							if(block[x-1][y-1].id == Tile.earth) {
								block[x][y].id = Tile.earth;
							}
						} catch(Exception e) { }		
					}
					
					if (new Random().nextInt(100) > 30) {	
						try {
							if(block[x+1][y-1].id == Tile.earth) {
								block[x][y].id = Tile.earth;
							}
						} catch(Exception e) { }
					}
					
					try {
						if(block[x][y-1].id == Tile.earth) {
							block[x][y].id = Tile.earth;
						}
					} catch(Exception e) { }
						
					if(new Random().nextInt(100) < 2){
						block[x][y].id = Tile.earth;
					}
				}
			}
		}
		
		//Tree placement
		for(int y=0;y<block.length;y++) {													//Starts in left top corner; creates coordinate grid for block placement. Increment x to continue direction
			for(int x=0;x<block[0].length;x++){
				try {
					if(block[x][y + 1].id == Tile.earth && block[x][y].id == Tile.air) {	
						if(new Random().nextInt(100) <= 5) {		// check to see if there's grass 
							for(int i = 0; i < new Random().nextInt(5) + 4; i++) {
								block[x][y - 1].id = Tile.wood;
							}
						}
					}
				} catch(Exception e) { }
			}
		}

		//Placing out grass blocks
		for(int y=0;y<block.length;y++) {													//Starts in left top corner; creates coordinate grid for block placement. Increment x to continue direction
			for(int x=0;x<block[0].length;x++){
				if(block[x][y].id == Tile.earth && block[x][y-1].id == Tile.air){
					block[x][y].id = Tile.grass;
				}
			}
		}
		
		// Solid air border around level; prevent escape/ mob blocker
		for(int y=0;y<block.length;y++) {													//Starts in left top corner; creates coordinate grid for block placement. Increment x to continue direction
			for(int x=0;x<block[0].length;x++){
				if (x == 0 || y == 0 || x == block[0].length - 1 || y == block[0].length - 1) {
					block[x][y].id = Tile.solidair;
				}
			}
		}
	}
	
	public void building(int camX, int camY, int renW, int renH){
		if(Component.isMouseLeft || Component.isMouseRight) {
			for(int x=(camX / Tile.tileSize); x < (camX / Tile.tileSize) + renW; x++) {															//Starts in left top corner; creates coordinate grid for block placement. Increment x to continue direction
				for(int y=(camY / Tile.tileSize);y<(camY / Tile.tileSize)+ renH;y++){ 																//Starts in left top corner; creates coordinate grid for block placement. Increment y to continue direction
					if(x >= 0 && y >= 0 && x < worldW && y < worldH) {	
						if(block[x][y].contains(new Point((Component.mse.x / Component.pixelSize) + (int) Component.sX, (Component.mse.y / Component.pixelSize) + (int)Component.sY))) {
							int sid[] = Inventory.invBar[Inventory.selected].id;
							
							if(Component.isMouseLeft){
								if(block[x][y].id != Tile.solidair)
									block[x][y].id = Tile.air;
							} else if (Component.isMouseRight) {
								if(block[x][y].id == Tile.air){
									if(sid == Tile.earth || sid == Tile.grass || sid == Tile.sand) {
										block[x][y].id = sid;
										
										if(block[x][y+1].id == Tile.grass){
											block[x][y+1].id = Tile.earth;
										}
									}
								}
							}
							
							break;
						}
					}
				}
			}
		}
	}
	
	public void tick(int camX, int camY, int renW, int renH) {
		if(!Inventory.isOpen){
			building(camX, camY, renW, renH);
		}
	}
	
	public void render(Graphics g, int camX, int camY, int renW, int renH){ 														//method now has graphics object; call g inside this method
		for(int x=(camX / Tile.tileSize); x < (camX / Tile.tileSize) + renW; x++) {															//Starts in left top corner; creates coordinate grid for block placement. Increment x to continue direction
			for(int y=(camY / Tile.tileSize); y < (camY / Tile.tileSize)+ renH;y++){ 																//Starts in left top corner; creates coordinate grid for block placement. Increment y to continue direction
				if(x >= 0 && y >= 0 && x < worldW && y < worldH) {
					block[x][y].render(g);
					
					if(block[x][y].id != Tile.air && block[x][y].id != Tile.solidair && !Inventory.isOpen){
					if(block[x][y].contains(new Point((Component.mse.x / Component.pixelSize) + (int) Component.sX, (Component.mse.y / Component.pixelSize) + (int)Component.sY))) { //Check if current block rendered contains mouse cursor. If so, draw square (below)
						g.setColor(new Color(255,255,255, 60));
						g.fillRect(block[x][y].x - camX, block[x][y].y - camY, block[x][y].width, block[x][y].height); // Draws cursor
						}
					}
				} 																								
			}
		}
	}
}
