package jumpingalien.part1.facade;

import jumpingalien.model.Mazub;
import jumpingalien.util.Sprite;

public class Facade implements IFacade {

	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		return new Mazub(pixelLeftX, pixelBottomY, sprites);
	}

	public int[] getLocation(Mazub alien) {
		int[] location = new int[2];
		location[0] = (int) Math.round(alien.getXPosition());
		location[1] = (int) Math.round(alien.getYPosition());
		return location;
		
	}

	public double[] getVelocity(Mazub alien) {
		double[] velocity = new double[2];
		velocity[0] = alien.getHorizontalVelocity();
		velocity[1] = alien.getVerticalVelocity();
		return velocity;
	}

	public double[] getAcceleration(Mazub alien) {
		double[] acceleration = new double[2];
		acceleration[0] = alien.getHorizontalAcceleration();
		acceleration[1] = alien.getVerticalAcceleration();
		return acceleration;
	}

	public int[] getSize(Mazub alien) {
		return alien.getSize(alien.getCurrentSprite());
	}

	public Sprite getCurrentSprite(Mazub alien) {
		return alien.getCurrentSprite();
	}

	public void startJump(Mazub alien) {
		alien.startJump();
	}

	public void endJump(Mazub alien) {
		alien.endJump();
	}

	public void startMoveLeft(Mazub alien) {
		alien.setDirection("left");
		alien.startMove();
	}

	public void endMoveLeft(Mazub alien) {
		alien.endMove();
	}

	public void startMoveRight(Mazub alien) {
		alien.setDirection("right");
		alien.startMove();
	}

	public void endMoveRight(Mazub alien) {
		alien.endMove();
	}

	public void startDuck(Mazub alien) {
		alien.startDuck();
	}

	public void endDuck(Mazub alien) {
		alien.endDuck();
	}

	public void advanceTime(Mazub alien, double dt) {
		alien.advanceTime(dt);
	}

}
