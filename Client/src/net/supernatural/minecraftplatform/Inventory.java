package net.supernatural.minecraftplatform;

import java.awt.*;
import java.awt.event.*;

public class Inventory {
	public static Cell[] invBar = new Cell[Tile.invLength];
	public static Cell[] invBag = new Cell[Tile.invLength * Tile.invHeight];
	
	public static boolean isOpen = false;
	public static boolean isHolding = false;
	
	
	public static int selected = 0;
	public static int[] holdingID = Tile.air;
	
	public Inventory() {
		for(int i=0;i<invBar.length;i++) {
			invBar[i] = new Cell(new Rectangle((Component.pixel.width /2 ) - ((Tile.invLength * (Tile.invCellSize + Tile.invCellSpace)) / 2) + (i * (Tile.invCellSize + Tile.invCellSpace)), Component.pixel.height - (Tile.invCellSize + Tile.invBorderSpace), Tile.invCellSize, Tile.invCellSize), Tile.air);
		}
		
		
		int x = 0, y = 0;
		for(int i = 0; i < invBag.length; i++) {
			invBag[i] = new Cell(new Rectangle((Component.pixel.width /2 ) - ((Tile.invLength * (Tile.invCellSize + Tile.invCellSpace)) / 2) + (x * (Tile.invCellSize + Tile.invCellSpace)), Component.pixel.height - (Tile.invCellSize + Tile.invBorderSpace) - (Tile.invHeight * Tile.invCellSize + Tile.invCellSpace) + (y * Tile.invCellSize + Tile.invCellSpace), Tile.invCellSize, Tile.invCellSize), Tile.air);
		
			x ++;
			if(x == Tile.invLength) {
				x = 0;
				y++;
			}
		
		}
		
		invBar[0].id = Tile.earth;
		invBar[1].id = Tile.grass;
		invBar[2].id = Tile.sand;
		invBar[3].id = Tile.wood;
	}
	
	public static void click(MouseEvent e){
		if(e.getButton() == 1) {
			if(isOpen) {
				for(int i = 0; i < invBar.length; i++) {
					if(invBar[i].contains(new Point(Component.mse.x / Component.pixelSize, Component.mse.y / Component.pixelSize))) {
						if(invBar[i].id != Tile.air && !isHolding){
							holdingID = invBar[i].id;
							invBar[i].id = Tile.air;
						
							isHolding = true;
						} else if(isHolding && invBar[i].id == Tile.air) {
							invBar[i].id = holdingID;
							
							isHolding = false;
						} else if(isHolding && invBar[i].id != Tile.air) {
							int[] con = invBar[i].id;
							
							invBar[i].id = holdingID;
							holdingID = con;
						}
					}
				}
				
				for(int i = 0; i < invBag.length; i++) {
					if(invBag[i].contains(new Point(Component.mse.x / Component.pixelSize, Component.mse.y / Component.pixelSize))) {
						if(invBag[i].id != Tile.air && !isHolding){
							holdingID = invBag[i].id;
							invBag[i].id = Tile.air;
								
							isHolding = true;
						} else if(isHolding && invBag[i].id == Tile.air) {
							invBag[i].id = holdingID;
									
							isHolding = false;
						} else if(isHolding && invBag[i].id != Tile.air) {
							int[] con = invBag[i].id;
									
							invBag[i].id = holdingID;
							holdingID = con;
						}
					}
				}
			}
		}
	}
	public void render(Graphics g) {
		for(int i=0;i<invBar.length;i++) {
			boolean isSelected = false;
			if(i == selected){
				isSelected = true;
			} 		
		
			invBar[i].render(g, isSelected);
		}
		
		if(isOpen) {
			for(int i=0;i<invBag.length;i++) {
				invBag[i].render(g, false);
			}
		}
	
		if(isHolding) {
				g.drawImage(Tile.tileset_terrain, (Component.mse.x / Component.pixelSize) - (Tile.invCellSize / 2) + Tile.invItemBorder, (Component.mse.y / Component.pixelSize) - (Tile.invCellSize / 2) + Tile.invItemBorder, (Component.mse.x / Component.pixelSize) - (Tile.invCellSize / 2) + Tile.invCellSize - Tile.invItemBorder, (Component.mse.y / Component.pixelSize) - (Tile.invCellSize / 2) + Tile.invCellSize - Tile.invItemBorder, holdingID[0] * Tile.tileSize, holdingID[1] * Tile.tileSize, holdingID[0] * Tile.tileSize + Tile.tileSize, holdingID[1] * Tile.tileSize + Tile.tileSize, null);
			}
		}
	}
