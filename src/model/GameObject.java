package model;

public abstract class GameObject {

	protected int width;
	protected int height;
	protected double relY;
	protected double relX;

	public abstract boolean hasCollided(GameObject o);

	public abstract void move();

	public double getRelX() {
		return relX;
	}

	public double getRelY() {
		return relY;
	}

}
