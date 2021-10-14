package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Coin;
import model.Duck;
import model.Duck.Movement;
import model.GameObject;
import model.Guy;
import model.Tree;
import model.Tree.TreeSize;
import model.Tree.TreeType;
import model.World;
import view.PaintingFrame;

public class MainController implements KeyListener {

	private World w;
	private boolean isPlaying;
	private Runnable movie;
	private Thread t;
	private PaintingFrame p;
	private SoundPlayer s;
	private Runnable moveLeft;
	private Runnable moveRight;
	private Runnable moveUp;
	private Runnable moveDown;
	private Thread movingGuy;
	private boolean coins;

	public enum Control {
		Guy, Duck;
	}

	private Control control;

	public MainController() {
		initializeRunnables();
		isPlaying = false;
		w = new World();
		p = new PaintingFrame(this);
		p.addKeyListener(this);
		s = new SoundPlayer();
		movie = new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (isPlaying) {
						movie();
					}
					while (!isPlaying) {
						try {
							Thread.sleep(1000 / 30);
							animateCoins();
						} catch (InterruptedException e) {
							JOptionPane.showMessageDialog(p, "Something happened:   " + e.getMessage());
						}
					}
				}

			}
		};
		t = new Thread(movie);
		t.start();
	}

	private void initializeRunnables() {
		moveLeft = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(36);
					w.moveGuy(Guy.Movement.Left);
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(p, "Something happened:   " + e.getMessage());
				}
			}

		};
		moveRight = new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(36);
					w.moveGuy(Guy.Movement.Right);
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(p, "Something happened:   " + e.getMessage());
				}
			}
		};
		moveUp = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(36);
					w.moveGuy(Guy.Movement.Up);
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(p, "Something happened:   " + e.getMessage());
				}
			}
		};
		moveDown = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(36);
					w.moveGuy(Guy.Movement.Down);
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(p, "Something happened:   " + e.getMessage());
				}
			}
		};
	}

	protected void animateCoins() {
		w.animateCoins();
	}

	public World getW() {
		return w;
	}

	public void playWorld(boolean b) {
		isPlaying = b;
	}

	public void clearTrees() {
		w.clearTrees();
	}

	public void add100Trees() {
		for (int i = 0; i < 100; i++) {
			int random = (int) (Math.random() * 2) + 1;
			if (random > 1) {
				addPineTree();
			} else {
				addLeafTree();
			}
		}
	}

	public void addPineTree() {
		int randomSize = (int) (Math.random() * 5 + 1);
		TreeSize size = TreeSize.M;
		switch (randomSize) {
		case 5:
			size = TreeSize.XXL;
			break;
		case 4:
			size = TreeSize.XL;
			break;
		case 3:
			size = TreeSize.L;
			break;
		case 2:
			size = TreeSize.M;
			break;
		case 1:
			size = TreeSize.S;
			break;
		}
		double randomRelX = Math.random() * 100;
		double randomRelY = (Math.random() * 50) + 50;
		w.addTree(new Tree(TreeType.pine, size, randomRelX, randomRelY));
	}

	public void addCoins() {
		for (int i = 0; i < 30; i++) {
			double randomRelX = Math.random() * 100;
			double randomRelY = (Math.random() * 40) + 60;
			w.addCoin(new Coin(randomRelX, randomRelY));
		}
	}

	public void addLeafTree() {
		int randomSize = (int) (Math.random() * 5 + 1);
		TreeSize size = TreeSize.M;
		switch (randomSize) {
		case 5:
			size = TreeSize.XXL;
			break;
		case 4:
			size = TreeSize.XL;
			break;
		case 3:
			size = TreeSize.L;
			break;
		case 2:
			size = TreeSize.M;
			break;
		case 1:
			size = TreeSize.S;
			break;
		}
		double randomRelX = Math.random() * 100;
		double randomRelY = (Math.random() * 50) + 50;
		w.addTree(new Tree(TreeType.leaf, size, randomRelX, randomRelY));

	}

	private void movie() {
		try {
			Thread.sleep(1000 / 30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		addBeforeObject();
		w.moveObjects();
	}

	private void addBeforeObject() {
		int randomSize = (int) (Math.random() * 5 + 1);
		TreeSize size = TreeSize.M;
		switch (randomSize) {
		case 5:
			size = TreeSize.XXL;
			break;
		case 4:
			size = TreeSize.XL;
			break;
		case 3:
			size = TreeSize.L;
			break;
		case 2:
			size = TreeSize.M;
			break;
		case 1:
			size = TreeSize.S;
			break;
		}
		double randomRelY = (Math.random() * 50) + 50;
		int random = (int) (Math.random() * 3);
		if (random < 1) {
			w.addTree(new Tree(TreeType.leaf, size, -10, randomRelY));
		} else if (random < 2) {
			w.addTree(new Tree(TreeType.pine, size, -10, randomRelY));
		} else {
			if (coins) {
				if (randomRelY < 60) {
					randomRelY = 60 + (int) (Math.random() * 10);
				}
				w.addCoin(new Coin(-10, randomRelY));
			}
		}
	}

	public void loadFile() {
		JFileChooser chooser = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir") + "\\Resources\\paintings");
		chooser.setCurrentDirectory(workingDirectory);
		chooser.setFileFilter(new FileNameExtensionFilter("painting", "painting"));
		int returnVal = chooser.showOpenDialog(p);
		CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<GameObject>();
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			w.removeObjects();
			File file = chooser.getSelectedFile();
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = br.readLine()) != null) {
					String[] values = toValues(line);
					if (isDuck(values)) {
						w.setDuck(new Duck(Integer.parseInt(values[2]), Integer.parseInt(values[3]),
								Duck.Shape.valueOf(values[1])));
						control = Control.Duck;
					} else if (isTree(values)) {
						objects.add(new Tree(Tree.TreeType.valueOf(values[0]), Tree.TreeSize.valueOf(values[1]),
								Integer.parseInt(values[2]), Integer.parseInt(values[3])));
					} else if (isGuy(values)) {
						objects.add(new Guy(Integer.parseInt(values[2]), Integer.parseInt(values[3]),
								Integer.parseInt(values[1])));
					} else if (isCoin(values)) {
						objects.add(new Coin(Integer.parseInt(values[2]), Integer.parseInt(values[3])));
					} else {
						JOptionPane.showMessageDialog(p, "Not a readable file");
						objects.clear();
						break;
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(p, "Something went wrong :" + e.getMessage());
			}
		}
		for (GameObject o : objects) {
			w.addObject(o);
		}
	}

	private boolean isCoin(String[] values) {
		if ((values[0].equals("coin") && isCorrectInteger(values[2]) && isCorrectInteger(values[3]))) {
			return true;
		}
		return false;
	}

	private boolean isTree(String[] values) {
		if ((values[0].equals("leaf") || values[0].equals("pine")) && (values[1].equals("XXL") || values[1].equals("XL")
				|| values[1].equals("L") || values[1].equals("M") || values[1].equals("S"))
				&& isCorrectInteger(values[2]) && isCorrectInteger(values[3])) {
			return true;
		}
		return false;
	}

	private boolean isGuy(String[] values) {
		if (values[0].equals("guy") && isCorrectInteger(values[1]) && isCorrectInteger(values[2])
				&& isCorrectInteger(values[3])) {
			return true;
		}
		return false;
	}

	private boolean isDuck(String[] values) {
		if (values[0].equals("duck") && values[1].equals("Up") || values[1].equals("Down")
				|| values[1].equals("StartDown") || values[1].equals("Mid") || values[1].equals("HalfUp")
				|| values[1].equals("StartUp") && isCorrectInteger(values[2]) && isCorrectInteger(values[3])) {
			return true;
		}
		return false;
	}

	private boolean isCorrectInteger(String string) {
		for (int i = 0; i < string.length(); i++) {
			if (!Character.isDigit(string.charAt(i))) {
				if ((string.charAt(i) == '-')) {
					break;
				}
				return false;
			}
		}
		if ((Integer.parseInt(string) >= -10)) {
			return true;
		}
		return true;
	}

	private String[] toValues(String string) {
		String[] returnStringArray = new String[4];
		returnStringArray[0] = string.substring(0, string.indexOf(':'));
		returnStringArray[1] = string.substring(string.indexOf(':') + 1, string.indexOf(':', string.indexOf(':') + 1));
		returnStringArray[2] = string.substring(string.indexOf(':', string.indexOf(':') + 1) + 1,
				string.indexOf(':', string.indexOf(':', string.indexOf(':') + 1) + 1));
		returnStringArray[3] = string
				.substring(string.indexOf(':', string.indexOf(':', string.indexOf(':') + 1) + 1) + 1);
		return returnStringArray;
	}

	public void saveFile() {
		CopyOnWriteArrayList<GameObject> objects = w.getObjects();
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("painting", "painting"));
		File workingDirectory = new File(System.getProperty("user.dir") + "\\Resources\\paintings");
		chooser.setCurrentDirectory(workingDirectory);
		int returnVal = chooser.showSaveDialog(p);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			FileWriter fw = null;
			try {
				fw = new FileWriter(chooser.getSelectedFile() + ".painting");
				for (GameObject o : objects) {
					fw.write(o.toString() + "\r\n");
				}
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	
	// this is, now that I look back at it, a bad way of doing it, in this case it's better to make it a switch on press and release, always do the movement, activate it by changing the state of the character
	// e.g. set the movement in a direction to true while it's pressed, only set it to false after released.
	@Override
	public void keyPressed(KeyEvent k) {
		switch (k.getKeyCode()) {
		case KeyEvent.VK_D:
			if (w.getDuck() == null) {
				w.setDuck(new Duck(50, 25, Duck.Shape.Mid));
				s.playQuack();
				control = Control.Duck;
			} else if (w.getDuck() != null && control == Control.Guy) {
				control = Control.Duck;
			} else {
				if (w.getGuy() != null) {
					control = Control.Guy;
				}
				w.removeDuck();
				s.playQuack();
			}
			break;
		case KeyEvent.VK_G:
			if (w.getGuy() == null) {
				w.addGuy(new Guy(50, 78, 0));
				s.playHi();
				control = Control.Guy;
			} else if (w.getGuy() != null && control == Control.Duck) {
				control = Control.Guy;
			} else {
				if (w.getDuck() != null) {
					control = Control.Duck;
				}
				w.removeGuy();
			}
		}
		if (w.getDuck() != null || w.getGuy() != null) {
			switch (k.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				if (control == Control.Duck) {
					w.moveDuck(Movement.Down, isPlaying);
				} else if (control == Control.Guy) {
					moveGuy(Guy.Movement.Down);
				}
				break;
			case KeyEvent.VK_UP:
				if (control == Control.Duck) {
					w.moveDuck(Movement.Up, isPlaying);
				} else if (control == Control.Guy) {
					moveGuy(Guy.Movement.Up);
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (control == Control.Duck) {
					w.moveDuck(Movement.Right, isPlaying);
				} else if (control == Control.Guy) {
					moveGuy(Guy.Movement.Right);
				}
				break;
			case KeyEvent.VK_LEFT:
				if (control == Control.Duck) {
					w.moveDuck(Movement.Left, isPlaying);
				} else if (control == Control.Guy) {
					moveGuy(Guy.Movement.Left);
				}
				break;
			case KeyEvent.VK_SPACE:
				if (control == Control.Duck) {
					w.moveDuck(Movement.Stationary, isPlaying);
				} else if (control == Control.Guy) {
					moveGuy(Guy.Movement.Stationary);
				}
			}
		}
	}

	private void moveGuy(model.Guy.Movement movement) {
		Runnable moveGuy = null;
		switch (movement) {
		case Up:
			moveGuy = moveUp;
			break;
		case Down:
			moveGuy = moveDown;
			break;
		case Left:
			moveGuy = moveLeft;
			break;
		case Right:
			moveGuy = moveRight;
			break;
		case Stationary:
			w.moveGuy(movement);
		default:
			break;
		}
		movingGuy = new Thread(moveGuy);
		movingGuy.start();
		GameObject g = w.getGuy();
		for (GameObject g2 : w.getObjects()) {
			if (g2 instanceof Coin) {
				if (g.hasCollided(g2)) {
					((Guy) g).addCoin();
					w.getObjects().remove(g2);
					s.playPing();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {

	}

	@Override
	public void keyTyped(KeyEvent k) {

	}

	public void removeCoins() {
		w.removeCoins();
	}

	public int getCoins() {
		return w.getCoins();
	}

	public void setCoins(boolean selected) {
		this.coins = selected;
	}

}
