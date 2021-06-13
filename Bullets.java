
//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Bullets {
	private List<Ammo> shipAmmo;
	private List<Ammo> alienAmmo;

	public Bullets() {
		shipAmmo = new ArrayList<Ammo>(1);
		alienAmmo = new ArrayList<Ammo>(1);
	}

	public void addShipAmmo(Ammo al) {
		shipAmmo.add(al);
	}

	public void addAlienAmmo(Ammo al) {
		alienAmmo.add(al);
	}

	public void drawShipEmAll(Graphics window) {
		if (size() > 0) {
			for (Ammo a : shipAmmo) {
				a.draw(window);
			}
		}
	}

	public void drawAlienEmAll(Graphics window) {
		if (alienAmmoSize() > 0) {
			for (Ammo a : alienAmmo) {
				a.draw(window);
			}
		}
	}

	public void moveShipEmAll() {
		if (size() > 0) {
			for (Ammo a : shipAmmo) {
				a.move("UP");
			}
		}
	}

	public void moveAlienEmAll() {
		for (Ammo a : alienAmmo) {
			a.move("Down");
		}
	}

	public void cleanUpShipAmmo(int index) {
		shipAmmo.remove(index);
	}

	public void cleanUpAlienAmmo() {
		alienAmmo.clear();
	}

	public int size() {
		return shipAmmo.size();
	}

	public int alienAmmoSize() {
		return alienAmmo.size();
	}

	public Ammo getShipAmmo(int index) {
		return shipAmmo.get(index);
	}
	
	public Ammo getAlienAmmo() {
		if(alienAmmo.isEmpty())
			return null;
		return alienAmmo.get(0);
	}
	
	public boolean isAmmoOutofCanvas() {
		if(alienAmmo.isEmpty())
			return false;
		Ammo ammo = alienAmmo.get(0);
		if(ammo.getY()>600){
			return true;
		}
		return false;
	}
	
	

	public String toString() {
		return "shipAmmo:" + shipAmmo.toString() + "alienAmmo:" + alienAmmo.toString();
	}
}
