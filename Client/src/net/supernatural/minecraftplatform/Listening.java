package net.supernatural.minecraftplatform;

import java.awt.event.*;

public class Listening implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{

	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key) {
		case KeyEvent.VK_D:
			Component.isMoving = true;
			Component.dir = Component.character.movementSpeed;
			
			break;
		case KeyEvent.VK_A:
			Component.isMoving = true;
			Component.dir = -Component.character.movementSpeed;
			break;
		case KeyEvent.VK_SPACE:
			Component.isJumping = true;
			break;
		case KeyEvent.VK_F:
			if(Inventory.isOpen) {
				Inventory.isOpen = false;
			} else {
				Inventory.isOpen = true;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key) {
		case KeyEvent.VK_D:
			if(Component.dir == Component.character.movementSpeed) {
				Component.isMoving = false;
			}
			break;
		case KeyEvent.VK_A:
			if(Component.dir == -Component.character.movementSpeed) {
				Component.isMoving = false;
			}
			break;
		case KeyEvent.VK_SPACE:
			Component.isJumping = false;
			break;
		}
	}

	public void keyTyped(KeyEvent e) {
	
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation() > 0) { // Downward
			if(Inventory.selected < Tile.invLength - 1) {
				Inventory.selected += 1;
			} else {
				Inventory.selected = 0;
			}
		} else if (e.getWheelRotation() < 0) { // Upward
			if(Inventory.selected > 0) { // When the edge is reached, will return back to zero/ origin
				Inventory.selected -= 1;
			} else {
				Inventory.selected = Tile.invLength - 1;
			}
		}
	}

	public void mouseDragged(MouseEvent e) {
		Component.mse.setLocation(e.getX(), e.getY());
	}

	public void mouseMoved(MouseEvent e) {
		Component.mse.setLocation(e.getX(), e.getY());
	}

	public void mouseClicked(MouseEvent e) {
	
	}

	public void mouseEntered(MouseEvent e) {
	
	}

	public void mouseExited(MouseEvent e) {
	
	}

	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			Component.isMouseLeft = true;
		} else if(e.getButton() == MouseEvent.BUTTON3){
			Component.isMouseRight = true;
		}
		
		Inventory.click(e);
	}

	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			Component.isMouseLeft = false;
		} else if(e.getButton() == MouseEvent.BUTTON3){
			Component.isMouseRight = false;
		}
	}
}
