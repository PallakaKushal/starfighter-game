
//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;

public class Alien extends MovingThing {
	private int speed;
	private Image image;

	public Alien() {
		this(0, 0, 30, 30, 0);
	}

	public Alien(int x, int y) {
		super(x, y);
	}

	public Alien(int x, int y, int s) {
		super(x, y);
		this.speed = s;
	}

	public Alien(int x, int y, int w, int h, int s) {
		super(x, y, w, h);
		speed = s;
		try {
			URL url = getClass().getResource("/images/alien.jpg");
			image = ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("Ailen image file not loading");
		}
	}

	public void setSpeed(int s) {
		this.speed = s;
	}

	public int getSpeed() {
		return speed;
	}

	public void move(String direction) {
		if (direction.equalsIgnoreCase("down")) {
			setY(getY() + speed);
		}
	}

	public void draw(Graphics window) {
		window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
	}
	
	public boolean didCollideTop(Ship ship) {
		if(ship==null)
			return false;
		if (getX() + getWidth()>= ship.getX() && 
			getX() <= ship.getX() + ship.getWidth() && 
			getY() + getHeight() >= ship.getY() && 
			getY() <= ship.getY() + ship.getHeight()) {
			System.out.println("The Alien has been hit by Ship");
			return true;
		} else {
			return false;
		}
	}


	public String toString() {
		return super.toString() + getSpeed();
	}

}
