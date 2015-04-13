package jumpingalien.part1.facade;

import jumpingalien.model.Direction;
import jumpingalien.model.IllegalSizeException;
import jumpingalien.model.IllegalTimeIntervalException;
import jumpingalien.model.IllegalXPositionException;
import jumpingalien.model.IllegalYPositionException;
import jumpingalien.model.Mazub;
import jumpingalien.util.Sprite;

public class Facade implements IFacade {
	
	public Facade() {
	}

	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) throws jumpingalien.util.ModelException {
		try {
			return new Mazub(pixelLeftX, pixelBottomY, sprites);
		} catch (IllegalXPositionException | IllegalYPositionException exc) {
			throw new jumpingalien.util.ModelException("Illegal positions", exc);
		}
	}

	public int[] getLocation(Mazub alien) {
		int[] location = new int[2];
		location[0] = (int) alien.getXPosition();
		location[1] = (int) alien.getYPosition();
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

	public int[] getSize(Mazub alien) throws jumpingalien.util.ModelException {
		try {
			return alien.getSize();
		} catch (IllegalSizeException exc) {
			throw new jumpingalien.util.ModelException("Illegal size", exc);
		}
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
		alien.setDirection(Direction.LEFT);
		alien.startMove(0.9);
	}

	public void endMoveLeft(Mazub alien) {
		if (alien.getDirection() == Direction.LEFT) {
			alien.endMove(0);
		}
	}

	public void startMoveRight(Mazub alien) {
		alien.setDirection(Direction.RIGHT);
		alien.startMove(0.9);
	}

	public void endMoveRight(Mazub alien) {
		if (alien.getDirection() == Direction.RIGHT) {
			alien.endMove(0);
		}
	}

	public void startDuck(Mazub alien) {
		alien.startDuck();
	}

	public void endDuck(Mazub alien) {
		alien.endDuck();
	}

	public void advanceTime(Mazub alien, double dt) throws jumpingalien.util.ModelException {
		try {
			alien.advanceTime(dt);
		} catch (IllegalTimeIntervalException exc) {
			throw new jumpingalien.util.ModelException("Illegal time interval", exc);
		}
	}

}
