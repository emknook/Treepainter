package model;

import java.util.ArrayList;

public class Duck extends GameObject {

	private double relX;
	private double relY;
	private Movement movement;
	private int flap;
	private ArrayList<Shape> flaps;
	private Shape shape;

	public enum Movement {
		Stationary, Left, Right, Up, Down;
	}

	public enum Shape {
		Up, HalfUp, StartUp, Mid, StartDown, Down;
	}

	@SuppressWarnings("serial")
	public Duck(double relX, double relY, Shape shape) {
		this.setRelX(relX);
		this.setRelY(relY);
		movement = Movement.Stationary;
		flaps = new ArrayList<Shape>() {
			{
				add(Shape.Up);
				add(Shape.Up);
				add(Shape.HalfUp);
				add(Shape.HalfUp);
				add(Shape.StartUp);
				add(Shape.Mid);
				add(Shape.StartDown);
				add(Shape.Down);
				add(Shape.Down);
				add(Shape.StartDown);
				add(Shape.StartDown);
				add(Shape.Mid);
				add(Shape.StartUp);
				add(Shape.HalfUp);
			}
		};
		flap = flaps.indexOf(shape);
		this.setShape(shape);
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

	@Override
	public void move() {
		switch (movement) {
		case Stationary:
			;
			break;
		case Left:
			moveLeft();
			break;
		case Right:
			moveRight();
			break;
		case Up:
			moveUp();
			break;
		case Down:
			moveDown();
			break;
		default:
			;
		}
		flap(true, false);
	}

	private void moveDown() {
		if (relY + 1 <= 32) {
			relY += 1;
		}
	}

	private void moveUp() {
		if (relY - 1 >= -5) {
			relY -= 1;
		}
	}

	private void moveRight() {
		if (relX + 1 <= 100) {
			relX += 1;
		}
	}

	private void moveLeft() {
		if (relX - 1 >= 0) {
			relX -= 1;
		}
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
	}

	public void flap(boolean isMoviePlaying, boolean isPlayerMoving) {
		if (isMoviePlaying && !isPlayerMoving) {
			int i = flap;
			if (i < flaps.size()) {
				flap++;
				if (flap == flaps.size()) {
					flap = 0;
				}
			}
		} else if (!isMoviePlaying && isPlayerMoving) {
			int i = flap;
			if (i < flaps.size()) {
				flap++;
				if (flap == flaps.size()) {
					flap = 0;
				}
			}
		}
		shape = flaps.get(flap);
	}

	public Shape getShape() {
		return shape;
	}

	@Override
	public String toString() {
		return "duck:" + getShape() + ":" + (int) relX + ":" + (int) relY;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	@Override
	public boolean hasCollided(GameObject o) {
		// TODO Auto-generated method stub
		return false;
	}

}
