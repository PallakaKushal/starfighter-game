
//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;

public class Ammo extends MovingThing implements Collidable<Alien> {
	private int speed;
	public boolean alive;

	public Ammo() {
		this(5, 5, 5);
	}

	public Ammo(int x, int y) {
		this(x, y, 5);
	}

	public Ammo(int x, int y, int s) {
		super(x, y);
		this.speed = s;
	}

	public void setSpeed(int s) {
		this.speed = s;
	}

	public int getSpeed() {
		return speed;
	}

	public void draw(Graphics window) {
		// add code to draw the ammo
		window.setColor(Color.YELLOW);
		//System.out
		window.fillRect(getX(), getY(), 10, 10);
	}
	
	

	public void move(String direction) {
		if (direction.equalsIgnoreCase("RIGHT")) {
			setX(getX() + speed);
		}
		if (direction.equalsIgnoreCase("LEFT")) {
			setX(getX() - speed);
		}
		if (direction.equalsIgnoreCase("UP")) {
			setY(getY() - speed);
		}
		if (direction.equalsIgnoreCase("DOWN")) {
			setY(getY() + speed);
		}
	}

	public String toString() {
		return super.toString() + getSpeed();
	}

	public boolean didCollideLeft(Alien obj) {
		return true;
	}

	public boolean didCollideRight(Alien obj) {
		return true;
	}

	public boolean didCollideTop(Alien alien) {
		if (getX() + 10 >= alien.getX() && 
			getX() <= alien.getX() + alien.getWidth() && 
			getY() - 10 >= alien.getY() && 
			getY() <= alien.getY() + alien.getHeight()) {
			System.out.println("The Alien has been hit in Ammo");
			kill();
			return true;
		} else {
			return false;
		}

	}
	
	public boolean isAlive() {
		if (getY() < 0) {
			kill();
		}
		return alive;
	}


	public void kill() {
		alive = false;
	}

	public boolean didCollideBottom(Alien obj) {
		return true;
	}
}
