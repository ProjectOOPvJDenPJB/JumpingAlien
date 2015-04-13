package jumpingalien.model;

import java.util.Random;

import jumpingalien.model.LivingCreatures.State;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

public class Slime extends LivingCreatures {
	/**
	 * Initialize this new Slime with given position en sprites
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Slime.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Slime.
	 * @param 	sprites
	 * 			The sprites for this new Slime.
	 * @param   world
	 * 			The world in which the Slime is located
	 * @post	The position of this new Slime is the same as the given position.
	 * 			| (new.getXPosition() == positionLeftX) && (new.getYPosition() == positionY)
	 * @Post	The world in which the Slime is located is the same as the given world
	 * 			| (new.getWorld() == world) && (new.isworld == True)
	 * @throws	IllegalXPositionException
	 * 			The given X position is not a valid X position for a Mazub.
	 * 			| ! isValidXPosition(positionLeftX)
	 * @throws	IllegalYPositionException
	 * 			The given Y position is not a valid Y position for a Mazub.
	 * 			| ! isValidYPosition(positionBottomY)
	 * 
	 */
	public Slime(int positionX, int positionY, double horizontalVelocity,double verticalVelocity,
			double horizontalAcceleration,Sprite[] sprites, World world, int hitpoints){
		super(positionX,positionY,horizontalVelocity,verticalVelocity,horizontalAcceleration,
				world,sprites,hitpoints);
	}
	
	/**
	 * Initialize this new Slime with given position en sprites
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Slime.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Slime.
	 * @param 	sprites
	 * 			The sprites for this new Slime.
	 * @param   world
	 * 			The world in which the Slime is located
	 * @post	The position of this new Slime is the same as the given position.
	 * 			| (new.getXPosition() == positionLeftX) && (new.getYPosition() == positionY)
	 * @Post	The world in which the Slime is located is the same as the given world
	 * 			| (new.getWorld() == world) && (new.isworld == True)
	 * @throws	IllegalXPositionException
	 * 			The given X position is not a valid X position for a Mazub.
	 * 			| ! isValidXPosition(positionLeftX)
	 * @throws	IllegalYPositionException
	 * 			The given Y position is not a valid Y position for a Mazub.
	 * 			| ! isValidYPosition(positionBottomY)
	 //TODO Commentaar klopt niet meer
	 */
	public Slime(int positionX, int positionY,double horizontalVelocity,double verticalVelocity, 
			World world,Sprite[] sprites){
		this(positionX,positionY,horizontalVelocity,verticalVelocity,0.7,sprites, world,100);	
	}

	/**
	 * Initialize this new Slime with given position en sprites
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Slime.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Slime.
	 * @param 	sprites
	 * 			The sprites for this new Slime.
	 * @effect	The new Slime is initialized with the given position as its position,
	 * 			the given sprites as its sprites and world as null
	 * 			| this(positionX,positionY,sprites, null)
	 //TODO Commentaar klopt niet meer	 */
	public Slime(int positionX, int positionY, double horizontalVelocity,double verticalVelocity,
			Sprite[] sprites){
		this(positionX,positionY,horizontalVelocity,verticalVelocity, null,sprites);	
	}
	
	/**
	 * Initialize this new Slime with given position en sprites
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Slime.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Slime.
	 * @param 	sprites
	 * 			The sprites for this new Slime.
	 * @effect	The new Slime is initialized with the given position as its position,
	 * 			the given sprites as its sprites and world as null
	 * 			| this(positionX,positionY,sprites, null)
	 //TODO Commentaar klopt niet meer	 */
	public Slime(int positionX, int positionY, Sprite[] sprites){
		this(positionX,positionY,0,0,sprites);	
	}
	
	/**
	 * Initialize this new Slime with sprites
	 * 
	 * @param 	sprites
	 * 			The sprites for this new Slime.
	 * @effect	The new Slime is initialized with the given sprites as its sprites 
	 * and as for positionX and positionY the value zero as its position, and world as null.
	 * 			| this(0,0,sprites, null)
	 */
	public Slime(Sprite[] sprites){
		this(0,0,sprites);	

	}
	
	public void startMoveOpposite(){
		this.setDirection(getDirection().oppositeDirection());
		this.setMoving(true);
		this.setHorizontalVelocity(0);
		this.setHorizontalAcceleration(0.7);
		}
	
	@Override
	public void terminate() {
		if (!isDead()) {
			if ((isAlive() && (getHP() > 0) && (! getOutOfBounds())))
				throw new IllegalStateException("Slime is alive within the boundaries of the world!");
			else if (((isDying()) && (Util.fuzzyGreaterThanOrEqualTo(getDeathTimer(), 0.6))) ||
					(getOutOfBounds())){
				setState(State.DEAD);
				World oldWorld = getWorld();
				setWorld(null);
				oldWorld.removeSlime(this);
				setHP(0);
			}
			else if (isAlive()) {
				setState(State.DYING);
				setDeathTimer(0);
			}
		}
	}

	@Override
	public int getMaxHP() {
		return 100;
	}

	@Override
	public int getMinHP() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	/**
	 * @param 	timeInterval 
	 * 			The time interval in which the position of this mazub has changed.
	 * @post	The new X position of this Mazub is equal to the current X position added to the horizontal distance
	 * 			travelled calculated with a formula using the given time interval. 
	 * 			new.getXPosition = this.getXPosition() + distanceCalculated
	 */
	public void changeHorizontalPosition(double timeInterval){
		double newPositionX = this.getXPosition() + this.getDirection().getInt() * 
				(100 * this.getHorizontalVelocity()*timeInterval 
				+ 50 * this.getHorizontalAcceleration()*timeInterval*timeInterval); 
		this.setXPosition(newPositionX);
	}
	
	@Override
	public void advanceTime(double timeInterval) throws IllegalTimeIntervalException {
		if (! isValidTimeInterval(timeInterval))
			throw new IllegalTimeIntervalException(timeInterval);
		
		if (isDying()) {
			terminate();
			this.setDeathTimer(getDeathTimer() + timeInterval);
		}
		
		if (isAlive()) {
			
			if (Util.fuzzyGreaterThanOrEqualTo(getRunTime(), getRandomTime())) {
				startMoveOpposite();
				setRandomTime();
				setRunTime(0);
			}
			else
				this.setRunTime(getRunTime() + timeInterval);
			
			this.changeHorizontalPosition(timeInterval);
			this.setHorizontalVelocity(this.getHorizontalVelocity() + this.getHorizontalAcceleration()*timeInterval);
		}
		
	}
	private double getRandomTime() {
		return this.randomTime;
	}
	
	private double randomTime = (2 + (6-2) * new Random().nextDouble());
	
	private void setRandomTime() {
		this.randomTime = (2 + (6-2) * new Random().nextDouble());
	}
}
