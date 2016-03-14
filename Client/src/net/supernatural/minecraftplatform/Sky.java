package net.supernatural.minecraftplatform;

import java.awt.*;


public class Sky {
	public int day = 0;
	public int night = 1;
	public int time = day;
	public int r1 = 70, g1 = 120, b1 = 250; // Day
	public int r2 = 10, g2 = 10, b2 = 30; // Night
	public int r = r1, g = g1, b = b1;
	public int dayFrame = 0, dayTime = 2000;
	public int changeFrame = 0, changeTime = 4;
	
	public Sky() {
		if(time == day) {
			r = r1;
			g = g1;
			b = b1;
		} else if (time == night) {
			r = r2;
			g = g2;
			b = b2;
		}
	}
	
	public void tick() {
		if(dayFrame >= dayTime){
			if(time == day){
				time = night;
			} else if(time == night) {
				time = day;
			}
			
			dayFrame = 0;
		} else {
			dayFrame += 1;
		}
		
		if(changeFrame >= changeTime) {
			if(time == day) {
				if(r <  r2) {
					r += 1;
				}
				if(g < g2) {
					g += 1;
				}
				if(b < b2) {
					b += 1;
				}
				
			} else if(time == night){
				if(r >  r2) {
					r -= 1;
				}
				if(g > g2) {
					g -= 1;
				}
				if(b > b2) {
					b -= 1;
				}
			}
			
			changeFrame = 0;
		} else {
			changeFrame += 1;
		}
	}
	
	
	public void render(Graphics gr) {
		gr.setColor(new Color( r, g, b));
		gr.fillRect(0, 0, Component.pixel.width, Component.pixel.height);
	}

}
