package model;

public class Coin extends GameObject {

	private double shape;
	private final int nrOfShapes = 7;
	private final int size = 32;

	public enum Movement {
		Stationary, Left, Right, Up, Down;
	}

	public Coin(double relX, double relY) {
		this.setRelX(relX);
		this.setRelY(relY);
		this.setShape(0);
	}

	public double getRelY() {
		return relY;
	}

	public void setRelY(double relY) {
		this.relY = relY;
	}

	public double getRelX() {
		return relX;
	}

	public void setRelX(double relX) {
		this.relX = relX;
	}

	public void move() {
		double pixels = (relY - 50) / 50;
		relX = relX + pixels;
		animate();
	}

	public void animate() {
		shape += 0.5;
		if (shape >= nrOfShapes) {
			shape = 0;
		}
	}

	@Override
	public String toString() {
		return "coin:" + getShape() + ":" + (int) relX + ":" + (int) relY;
	}

	public double getShape() {
		return shape;
	}

	public void setShape(int shape) {
		this.shape = shape;
	}

	@Override
	public boolean hasCollided(GameObject o) {
		return false;
	}

	public int getNrOfShapes() {
		return nrOfShapes;
	}

	public int getSize() {
		return size;
	}

}
