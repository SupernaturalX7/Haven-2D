package net.supernatural.minecraftplatform;

import java.awt.*;

public class Block extends Rectangle{		//Whenever extending, have serial version UID
	private static final long serialVersionUID = 1L;
	
	public int[] id = {-1, -1}; //air blocks // this.id will select this line //multi-dimensional array to access blocks
	
	public Block(Rectangle size, int[] id) {
		setBounds(size); //set coordinates
		this.id = id; //public int id will be assigned to this
	}

	public void render(Graphics g) {
		if(id != Tile.air) {
			g.drawImage(Tile.tileset_terrain, x - (int) Component.sX, y - (int) Component.sY, x + width - (int) Component.sX, y + height - (int) Component.sY, id[0] * Tile.tileSize, id[1] * Tile.tileSize, id[0] * Tile.tileSize + Tile.tileSize, id[1] * Tile.tileSize + Tile.tileSize, null);
		}
	}
}
