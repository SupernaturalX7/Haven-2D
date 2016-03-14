package net.supernatural.minecraftplatform;

import java.awt.*;

public class Cell extends Rectangle{
	private static final long serialVersionUID = 1L;

	public int[] id = {0, 0};
	
	public Cell(Rectangle size, int[] id) {
		setBounds(size);
		this.id = id;
	}
	public void render(Graphics g, boolean isSelected) {
		if(Inventory.isOpen && contains(new Point(Component.mse.x / Component.pixelSize, Component.mse.y / Component.pixelSize))) { //If open, set color
			g.setColor(new Color(255, 255, 255, 130));
			g.fillRect(x,y, width, height);
		}
		
		g.drawImage(Tile.tile_cell, x, y, width, height, null);
		
		if(id != Tile.air){
			g.drawImage(Tile.tileset_terrain, x + Tile.invItemBorder, y + Tile.invItemBorder, x - Tile.invItemBorder + width, y - Tile.invItemBorder + height, id[0] * Tile.tileSize, id[1] * Tile.tileSize, id[0] * Tile.tileSize + Tile.tileSize, id[1] * Tile.tileSize + Tile.tileSize, null);
		}
		
		if(isSelected && !Inventory.isOpen){
			g.drawImage(Tile.tile_select, x - 1, y - 1, width + 2, height + 2, null);
		}
	}
}
