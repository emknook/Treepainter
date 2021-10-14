package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;

public class World extends Observable {

	private CopyOnWriteArrayList<GameObject> objects;

	public World() {
		objects = new CopyOnWriteArrayList<GameObject>();
	}

	public void addTree(Tree tree) {
		objects.add(tree);
		setChanged();
		orderObjects();
		notifyObservers(objects);
	}

	public void moveObjects() {
		for (GameObject o : objects) {
			o.move();
			if (o.getRelX() > 110) {
				objects.remove(o);
			}
			if (o instanceof Duck) {
				((Duck) o).flap(true, false);
			}
			update();
		}

	}

	public CopyOnWriteArrayList<GameObject> getObjects() {
		orderObjects();
		return objects;
	}

	private void orderObjects() {
		Collections.sort(objects, new Comparator<GameObject>() {
			@Override
			public int compare(GameObject o1, GameObject o2) {
				return Double.compare(o1.getRelY(), o2.getRelY());
			}

		});
	}

	private void update() {
		orderObjects();
		setChanged();
		notifyObservers(objects);
	}

	public void clearTrees() {
		for (GameObject g : objects) {
			if (g instanceof Tree) {
				objects.remove(g);
			}
		}
		update();
	}

	public Duck getDuck() {
		for (GameObject g : objects) {
			if (g instanceof Duck) {
				return (Duck) g;
			}
		}
		return null;
	}

	public Guy getGuy() {
		for (GameObject g : objects) {
			if (g instanceof Guy) {
				return (Guy) g;
			}
		}
		return null;
	}

	public void setDuck(Duck duck) {
		objects.add(duck);
		setChanged();
		notifyObservers(objects);
	}

	public void moveDuck(Duck.Movement movement, boolean isMoviePlaying) {
		for (GameObject g : objects) {
			if (g instanceof Duck) {
				((Duck) g).setMovement(movement);
				((Duck) g).move();
				update();
			}
		}
	}

	public void animateCoins() {
		for (GameObject g : objects) {
			if (g instanceof Coin) {
				((Coin) g).animate();
			}
		}
		update();
	}

	public void addCoin(Coin coin) {
		objects.add(coin);
		update();
	}

	public void addGuy(Guy guy) {
		objects.add(guy);
		update();
	}

	public void removeDuck() {
		for (GameObject g : objects) {
			if (g instanceof Duck) {
				objects.remove(g);
				break;
			}
		}
		update();
	}

	public void removeCoins() {
		for (GameObject g : objects) {
			if (g instanceof Coin) {
				objects.remove(g);
			}
		}
	}

	public void removeGuy() {
		for (GameObject g : objects) {
			if (g instanceof Guy) {
				objects.remove(g);
				break;
			}
		}
		update();
	}

	public void moveGuy(Guy.Movement movement) {
		for (GameObject g : objects) {
			if (g instanceof Guy) {
				((Guy) g).setMovement(movement);
				((Guy) g).move();
				update();
			}
		}
	}

	public void addObject(GameObject o) {
		objects.add(o);
		update();
	}

	public void removeObjects() {
		objects.clear();
	}

	public int getCoins() {
		for (GameObject g : objects) {
			if (g instanceof Guy) {
				return ((Guy) g).getCoins();
			}
		}
		return 0;
	}

	public void setCoins(int amount) {
		for (GameObject g : objects) {
			if (g instanceof Guy) {
				((Guy) g).setCoins(amount);
			}
		}
	}
}
