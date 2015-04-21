package jumpingalien.model;

//import jumpingalien.model.LivingCreatures.State;
import java.util.Random;


//import jumpingalien.model.LivingCreatures.State;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

public class Shark extends LivingCreatures {
	

	/**
	 * Initialize this new Shark with given position en sprites
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Shark.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Shark.
	 * @param 	sprites
	 * 			The sprites for this new Shark.
	 * @param   world
	 * 			The world in which the Shark is located
	 * @post	The position of this new Shark is the same as the given position.
	 * 			| (new.getXPosition() == positionLeftX) && (new.getYPosition() == positionY)
	 * @Post	The world in which the Shark is located is the same as the given world
	 * 			| (new.getWorld() == world) && (new.isInWorld == True)
	 * @throws	IllegalXPositionException
	 * 			The given X position is not a valid X position for a Mazub.
	 * 			| ! isValidXPosition(positionLeftX)
	 * @throws	IllegalYPositionException
	 * 			The given Y position is not a valid Y position for a Mazub.
	 * 			| ! isValidYPosition(positionBottomY)
	 * 
	 */
	public Shark(int positionX, int positionY, double horizontalVelocity, double verticalVelocity,
			World world,Sprite[] sprites, int hitpoints){
		super(positionX,positionY, horizontalVelocity, verticalVelocity,world,sprites, hitpoints);

	}

	/**
	 * Initialize this new Shark with given position en sprites
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Shark.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Shark.
	 * @param 	sprites
	 * 			The sprites for this new Shark.
	 * @effect	The new Shark is initialized with the given position as its position,
	 * 			the given sprites as its sprites and world as null
	 * 			| this(positionX,positionY,sprites, null)
	 */
	public Shark(int positionX, int positionY, Sprite[] sprites){
		this(positionX,positionY,0,0,null,sprites,100);	
	}
	
	/**
	 * Initialize this new Shark with sprites
	 * 
	 * @param 	sprites
	 * 			The sprites for this new Shark.
	 * @effect	The new Shark is initialized with the given sprites as its sprites 
	 * and as for positionX and positionY the value zero as its position, and world as null.
	 * 			| this(0,0,sprites, null)
	 */
	public Shark(Sprite[] sprites){
		this(0,0,sprites);	

	}
	
	/**
	 * Register a removal from this Shark from a world. If the Shark is in a world.
	 *
	 * @post   This Shark is no longer in a world.
	 *	     | ! new.isInWorld()
	 * @post   The former world of this Shark, no longer contains this Shark
	 */
	@Override
	public void terminate() {
		if (!isDead()) {
			if ((isAlive() && (getHP() > 0) && (! getOutOfBounds())))
				throw new IllegalStateException("Shark is alive within the boundaries of the world!");
			else if (((isDying()) && (Util.fuzzyGreaterThanOrEqualTo(getDeathTimer(), 0.6))) ||
					(getOutOfBounds())){
				setState(State.DEAD);
				World oldWorld = getWorld();
				setWorld(null);
				oldWorld.removeShark(this);
				setHP(0);
			}
			else if (isAlive()) {
				setState(State.DYING);
				setDeathTimer(0);
			}
		}
	}

	/**
	 * returns the maximum hit points for this slime
	 */
	@Override
	public int getMaxHP() {
		return 100;
	}

	/**
	 * returns the minimum hit points for this Slime
	 */
	@Override
	public int getMinHP() {
		return 0;
	}
	
	/**
	 * start a move to the given direction as the current movement of this shark
	 * 
	 * @post  The new direction is opposite to the current direction, 
	 * 		  the horizontal velocity is reset to zero and the horizontal acceleration is set to 1.5.
	 * 		 | new.getDirection() == direction
	 * 		 | new.getHorizontalVelocity == 0
	 * 		 | new.getHorizontalAcceleration == 1.5
	 */
	public void startMove(Direction direction){
			this.setDirection(direction);
			this.setHorizontalVelocity(0);
			this.setHorizontalAcceleration(1.5);
	}
	
	/**
	 * End the movement of this shark
	 * 
	 * @post  The horizontal velocity is reset to zero and the horizontal acceleration is set to 0.
	 * 		 | new.getHorizontalVelocity == 0
	 * 		 | new.getHorizontalAcceleration == 0
	 */
	public void endMove(){
		this.setHorizontalVelocity(0);
		this.setHorizontalAcceleration(0);
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
			
			if (Util.fuzzyGreaterThanOrEqualTo(getRunTime(),getRandomTime())) {
				generateRandomTime();
				this.setRunTime(0);
				this.endJump();
				this.endMove();
				this.startJump(2.0,-10.0);
				this.startMove(this.getDirection().oppositeDirection());
				this.setDirection(this.getDirection().oppositeDirection());
				this.generateRandomTime();
			}
			else
				this.setRunTime(getRunTime() + timeInterval);
			
			this.changeHorizontalPosition(timeInterval);
			this.changeVerticalPosition(timeInterval);
			this.setHorizontalVelocity(this.getHorizontalVelocity() + this.getHorizontalAcceleration()*timeInterval);
			this.setVerticalVelocity(this.getVerticalVelocity() + this.getVerticalAcceleration()*timeInterval);	
			this.setHitTimer(this.getHitTimer() + timeInterval);
			this.applyTerrainDmg(timeInterval);
		}
	}
	
	/**
	 * returns the random time for the length of the movement of this shark
	 */
	private double getRandomTime() {
		return this.randomTime;
	}	
	private double randomTime = (1 + (4-1) * new Random().nextDouble());
	
	/**
	 * Sets the random time for this shark to the generated time.
	 * @post	The new random time for this shark is equal to
	 * 			the generated time
	 * 			| new.getRandomTime() == (1 + (4-1) * new Random().nextDouble());
	 */
	private void generateRandomTime() {
		this.randomTime = (1 + (4-1) * new Random().nextDouble());
	}

	}
