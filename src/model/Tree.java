package model;

public class Tree extends GameObject {

	public enum TreeType {
		pine, leaf;
	}

	public enum TreeSize {
		S, M, L, XL, XXL;
	}

	private TreeSize size;
	private TreeType type;
	private double relX;
	private double relY;

	public Tree(TreeType type, TreeSize size, double relX, double relY) {
		this.type = type;
		this.size = size;
		this.relX = relX;
		this.relY = relY;
	}

	public void move() {
		double pixels = (((100 - getRelY()) / 6) * -0.09 + 1);
		relX = relX + pixels;
	}

	public TreeSize getSize() {
		return size;
	}

	public void setSize(TreeSize size) {
		this.size = size;
	}

	public TreeType getType() {
		return type;
	}

	public void setType(TreeType type) {
		this.type = type;
	}

	public double getRelX() {
		return relX;
	}

	public void setRelX(double relX) {
		this.relX = relX;
	}

	public double getRelY() {
		return relY;
	}

	public void setRelY(double relY) {
		this.relY = relY;
	}

	@Override
	public String toString() {
		return type.toString() + ":" + size.toString() + ":" + (int) Math.round(relX) + ":" + (int) Math.round(relY);
	}

	@Override
	public boolean hasCollided(GameObject o) {
		return false;
	}

}
