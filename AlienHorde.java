
//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde {
	private List<Alien> aliens;
	private int score = 0;
	private int alienHordeSize = 0;

	public AlienHorde(int size) {
		aliens = new ArrayList<Alien>(size);
		this.alienHordeSize = size;
	}

	public void add(Alien al) {
		aliens.add(al);
	}

	public boolean isEmpty() {
		return aliens.isEmpty();
	}

	public Alien getAlien(int index) {
		return aliens.get(index);
	}

	public int size() {
		return aliens.size();
	}

	public void drawEmAll(Graphics window) {
		if (aliens.size() > 0) {
			for (Alien a : aliens) {
				a.draw(window);
			}
		}
	}

	public void moveEmAll() {
		if (aliens.get(0).getY() < 600) {
			for (Alien a : aliens) {
				a.move("DOWN");
			}
		}
	}

	public void removeDeadOnes(Bullets shots) {
		for (int i = aliens.size() - 1; i >= 0; i--) {
			for (int j = shots.size() - 1; j >= 0; j--) {
				if (shots.getShipAmmo(j).didCollideTop(aliens.get(i))) {
					score += 1;
					aliens.remove(i);
					shots.cleanUpShipAmmo(j);
					return;
				}
			}
		}
	}

	public void createMatrixHorde(int x, int y, int w, int h, int s) {
		int xPos = x;
		for (int i = 0; i < alienHordeSize; i++) {
			aliens.add(new Alien(x, y, w, h, s));
			if (x >= 600) {
				x = xPos;
				y = y + 10 + h;
			} else {
				x = x + w + 10;
			}

		}
	}

	public boolean didAlienCollideShip(Ship ship) {
		for (int i = aliens.size() - 1; i >= 0; i--) {
			if (aliens.get(i).didCollideTop(ship)) {
				aliens.remove(i);
				return true;
			}
		}
		return false;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int s) {
		score = s;
	}

	public String toString() {
		return "aliens:" + aliens.size();
	}
}
