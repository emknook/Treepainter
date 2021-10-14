package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

import controller.MainController;
import model.Coin;
import model.Duck;
import model.GameObject;
import model.Guy;
import model.Tree;
import model.World;

@SuppressWarnings("serial")
public class PaintingPanel extends JPanel implements Observer {

	private LeafTreePainter ltp;
	private PineTreePainter ptp;
	private CoinPainter cp;
	private GuyPainter gp;
	private DuckPainter dp;
	private FontType font;
	private MainController m;
	private CopyOnWriteArrayList<GameObject> objects;

	public enum FontType {
		HELVETICA, ARIAL, COURIER, TIMES;
	}

	public PaintingPanel(MainController m, World world) {
		this.m = m;
		font = FontType.ARIAL;
		ltp = new LeafTreePainter();
		ptp = new PineTreePainter();
		cp = new CoinPainter();
		gp = new GuyPainter();
		dp = new DuckPainter();
		world.addObserver(this);
		this.setBackground(new Color(240, 254, 255));
		this.setPreferredSize(new Dimension(800, 600));
	}

	public void setFont(FontType font) {
		this.font = font;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(255, 155, 40));
		g.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2);
		if (objects != null) {
			for (GameObject o : objects) {
				if (o instanceof Tree) {
					if (((Tree) o).getType() == Tree.TreeType.leaf) {
						ltp.paintTree((Tree) o, g, this);
					} else {
						ptp.paintTree((Tree) o, g, this);
					}
				} else if (o instanceof Duck){
					dp.paintDuck((Duck) o, g, this);
				} else if (o instanceof Coin){
					cp.paintCoin((Coin) o, g, this);
				} else if (o instanceof Guy){
					gp.paintGuy((Guy) o, g, this);
				}
			}
		}
		g.setColor(Color.BLACK);
		switch (font) {
		case HELVETICA:
			g.setFont(new Font("Helvetica", Font.PLAIN, 30));
			break;
		case ARIAL:
			g.setFont(new Font("Arial", Font.PLAIN, 30));
			break;
		case COURIER:
			g.setFont(new Font("Courier", Font.PLAIN, 20));
			break;
		case TIMES:
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			break;
		}
		g.drawString("Marcy Knook", getWidth() - 200, getHeight() - 20);
		g.setFont(new Font("Arial",Font.PLAIN, 10));
		g.drawString("G for guy", 0, 10);
		g.drawString("D for duck", 0, 20);
		g.drawString("coins: " + m.getCoins(), getWidth()-60, 10);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object ob) {
		if (ob instanceof CopyOnWriteArrayList) {
			this.objects = (CopyOnWriteArrayList<GameObject>) ob;
		}
		repaint();
	}

}
