package model;

import java.util.ArrayList;

public class Guy extends GameObject {

	private double relX;
	private double relY;
	private Movement movement;
	private ArrayList<Pose> walkingRight;
	private ArrayList<Pose> walkingLeft;
	private ArrayList<Pose> walkingUp;
	private ArrayList<Pose> walkingDown;
	private Pose pose;
	private double poseNr;
	private int coins;

	public enum Movement {
		Stationary, Left, Right, Up, Down;
	}

	public enum Pose {
		Up, UpLeft, UpRight, Down, DownLeft, DownRight, Right, RightStep, Left, LeftStep;
	}

	@SuppressWarnings("serial")
	public Guy(double relX, double relY, int amountOfCoins) {
		this.coins = amountOfCoins;
		this.setRelX(relX);
		this.setRelY(relY);
		movement = Movement.Stationary;
		walkingLeft = new ArrayList<Pose>() {
			{
				add(Pose.Left);
				add(Pose.LeftStep);
			}
		};
		walkingRight = new ArrayList<Pose>() {
			{
				add(Pose.Right);
				add(Pose.RightStep);
			}
		};
		walkingDown = new ArrayList<Pose>() {
			{
				add(Pose.Down);
				add(Pose.DownLeft);
				add(Pose.Down);
				add(Pose.DownRight);
			}
		};
		walkingUp = new ArrayList<Pose>() {
			{
				add(Pose.Up);
				add(Pose.UpRight);
				add(Pose.Up);
				add(Pose.UpLeft);
			}
		};
		this.setPose(Pose.Down);
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
		switch (movement) {
		case Stationary:
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
	}

	private void moveDown() {
		if (relY + 1 <= 100) {
			relY += 1;
		}
		if (poseNr >= walkingDown.size()) {
			poseNr = 0;
		}
		poseNr += 0.5;
		if (poseNr >= walkingDown.size()) {
			poseNr = 0;
		}
		pose = walkingDown.get((int) poseNr);
	}

	private void moveUp() {
		if (relY - 1 >= 60) {
			relY -= 1;
		}
		if (poseNr >= walkingUp.size()) {
			poseNr = 0;
		}
		poseNr += 0.5;
		if (poseNr >= walkingUp.size()) {
			poseNr = 0;
		}
		pose = walkingUp.get((int) poseNr);
	}

	private void moveRight() {
		if (relX + 1 <= 100) {
			relX += 1;
		}
		if (poseNr >= walkingRight.size()) {
			poseNr = 0;
		}
		poseNr += 0.5;
		if (poseNr >= walkingRight.size()) {
			poseNr = 0;
		}
		pose = walkingRight.get((int) poseNr);
	}

	private void moveLeft() {
		if (relX - 1 >= 0) {
			relX -= 1;
		}
		if (poseNr >= walkingLeft.size()) {
			poseNr = 0;
		}
		poseNr += 0.5;
		if (poseNr >= walkingLeft.size()) {
			poseNr = 0;
		}
		pose = walkingLeft.get((int) poseNr);
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
	}

	public Pose getPose() {
		return pose;
	}

	@Override
	public String toString() {
		return "guy:" + getCoins() + ":" + (int) relX + ":" + (int) relY;
	}

	public void setPose(Pose pose) {
		this.pose = pose;
	}

	public int getCoins() {
		return coins;
	}

	public void addCoin() {
		coins++;
	}

	@Override
	public boolean hasCollided(GameObject o) {
		if (o.getRelX() >= getRelX() - 2 && o.getRelX() <= getRelX() + 2 && o.getRelY() <= getRelY() + 2
				&& o.getRelY() >= getRelY() - 2) {
			return true;
		}
		return false;
	}

	public void setCoins(int amount) {
		coins = amount;
	}

}
