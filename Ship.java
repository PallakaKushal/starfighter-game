
//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;

public class Ship extends MovingThing implements Collidable<Ammo> {
	private int speed;
	private Image image;

	public Ship() {
		this(10, 10, 10, 10, 10);
	}

	public Ship(int x, int y) {
		super(x, y);
	}

	public Ship(int x, int y, int s) {
		super(x, y);
		this.speed = s;
	}

	public Ship(int x, int y, int w, int h, int s) {
		super(x, y, w, h);
		speed = s;
		try {
			URL url = getClass().getResource("/images/ship.jpg");
			image = ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("Ship image file not loading");
		}
	}

	public void setSpeed(int s) {
		this.speed = s;
	}

	public int getSpeed() {
		return speed;
	}

	public void move(String direction) {
		if (direction.equalsIgnoreCase("LEFT")) {
			setX(getX() - speed);
		}
		if (direction.equalsIgnoreCase("RIGHT")) {
			setX(getX() + speed);
		}
		if (direction.equalsIgnoreCase("DOWN")) {
			setY(getY() + speed);
		}
		if (direction.equalsIgnoreCase("UP")) {
			setY(getY() - speed);
		}
	}

	public void draw(Graphics window) {
		window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
	}

	public String toString() {
		return super.toString() + getSpeed();
	}

	public boolean didCollideLeft(Ammo ammo) {
		return true;
	}

	public boolean didCollideRight(Ammo ammo) {
		return true;
	}

	public boolean didCollideTop(Ammo ammo) {
		if(ammo==null)
			return false;
		if (getX() + getWidth()>= ammo.getX() && 
			getX() <= ammo.getX() + ammo.getWidth() && 
			getY() + getHeight() >= ammo.getY() && 
			getY() <= ammo.getY() + ammo.getHeight()) {
			System.out.println("The Ship has been hit by Ammo");
			return true;
		} else {
			return false;
		}
	}

	public boolean didCollideBottom(Ammo ammo) {
		return true;
	}
}
