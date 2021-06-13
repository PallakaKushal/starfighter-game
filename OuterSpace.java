
//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class OuterSpace extends Canvas implements KeyListener, Runnable {
	private Ship ship;
	private AlienHorde horde;
	private Bullets bullets;
	private boolean[] keys;
	private int live = 3;

	private BufferedImage back;
	private static final int expectedElapsedMisc = 5000;
	private long recentFiretimeMisc;

	public OuterSpace() {
		setBackground(Color.black);
		keys = new boolean[6];
		// instantiate other instance variables
		// Ship, Alien
		ship = new Ship(350, 400, 60, 40, 1);
		horde = new AlienHorde(36);
		bullets = new Bullets();
		this.addKeyListener(this);
		recentFiretimeMisc = System.currentTimeMillis();
		new Thread(this).start();
		setVisible(true);
	}

	public void update(Graphics window) {
		paint(window);
	}

	private boolean isTimeForAlienFire() {
		// some time passes
		long currentTimeMisc = System.currentTimeMillis();
		long elapsedTime = currentTimeMisc - recentFiretimeMisc;
		if (elapsedTime > expectedElapsedMisc) {
			recentFiretimeMisc = currentTimeMisc;
		    return true;
		}
		return false;
	}

	public void paint(Graphics window) {
		// set up the double buffering to make the game animation nice and
		// smooth
		Graphics2D twoDGraph = (Graphics2D) window;
		if (horde.isEmpty()) {
			horde.createMatrixHorde(10, 30, 40, 40, 20);
		}

		// take a snap shop of the current screen and same it as an image
		// that is the exact same width and height as the current screen
		if (back == null)
			back = (BufferedImage) (createImage(getWidth(), getHeight()));

		// create a graphics reference to the back ground image
		// we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();

		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("StarFighter ", 25, 50);
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0, 0, 800, 600);

		if (keys[0] == true) {
			if (ship.getX() > 0 - (ship.getWidth() / 2) + 30) {
				ship.move("LEFT");
				if(horde.didAlienCollideShip(ship)){
					live--;
					keys[0] = false;
				}
			}
		}

		if (keys[1] == true) {
			if (ship.getX() < 800 - (ship.getWidth() / 2) - 30) {
				ship.move("RIGHT");
				if(horde.didAlienCollideShip(ship)){
					live--;
					keys[1] = false;
				}
			}
		}

		if (keys[2] == true) {
			if (ship.getY() > (0 - (ship.getHeight() / 2) + 20)) {
				ship.move("UP");
				if(horde.didAlienCollideShip(ship)){
					live--;
					keys[2] = false;
				}
				
			}
		}

		if (keys[3] == true) {
			if (ship.getY() < 600 - ship.getHeight() / 2 - 50) {
				ship.move("DOWN");
				if(horde.didAlienCollideShip(ship)){
					live--;
					keys[3] = false;
				}
			}
		}

		if (keys[4] == true) {
			Ammo ammo = new Ammo((ship.getX() + ship.getWidth() / 2) - 5, ship.getY() - 5, 5);
			bullets.addShipAmmo(ammo);
			keys[4] = false;

		}
		
		graphToBack.setColor(Color.GREEN);
		graphToBack.drawString("SCORE: " + horde.getScore(), 15, 30);
		graphToBack.drawString("LIVE: " + live, 100, 30);
		if (live == 0) {
			graphToBack.setColor(Color.RED);
			graphToBack.setFont(new Font(Font.SANS_SERIF, 50, 50));
			graphToBack.drawString("Game Over  ", 200, 300);
			graphToBack.drawString("Press R to RESTART  ", 200, 350);
		} else {
			// add code to move Ship, Alien, etc.
			bullets.drawShipEmAll(graphToBack);
			bullets.moveShipEmAll();
			horde.drawEmAll(graphToBack);
			ship.draw(graphToBack);
			
			horde.removeDeadOnes(bullets);
			if (isTimeForAlienFire()) {
				randomAlienFireInitiate();
				horde.moveEmAll();
			}
			bullets.drawAlienEmAll(graphToBack);
			bullets.moveAlienEmAll();
			if (bullets.isAmmoOutofCanvas()) {
				bullets.cleanUpAlienAmmo();
			} else if (ship.didCollideTop(bullets.getAlienAmmo())) {
				live--;
				bullets.cleanUpAlienAmmo();
			}
		}
		twoDGraph.drawImage(back, null, 0, 0);
	}

	private void randomAlienFireInitiate() {
		int rand = (int) ((Math.random() * (horde.size() - 1)));
		int xPos = (horde.getAlien(rand).getX() + horde.getAlien(rand).getWidth() / 2) - 5;
		int yPos = horde.getAlien(rand).getY() + 200;
		Ammo ammo = new Ammo(xPos, yPos, 1);
		bullets.addAlienAmmo(ammo);
	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keys[3] = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			keys[4] = true;
		}
		
		repaint();
	}

	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			keys[4] = false;
		}
		
		repaint();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void run() {
		try {
			while (true) {
				Thread.currentThread().sleep(5);
				repaint();
			}
		} catch (Exception e) {
		}
	}
}
