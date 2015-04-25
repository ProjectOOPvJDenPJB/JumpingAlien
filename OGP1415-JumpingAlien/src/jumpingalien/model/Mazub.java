package jumpingalien.model;

import jumpingalien.util.*;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of Mazub.
 * Link to repository: https://github.com/ProjectOOPvJDenPJB/JumpingAlien
 * 
 * @author Joren Dhont (Ingenieurswetenschappen: Computerwetenschappen - Elektrotechniek) 
 * 	& Pieterjan Beerden (Ingenieurswetenschappen: Elektrotechniek - Computerwetenschappen)
 * @version 0.1
 */
public class Mazub extends LivingCreatures {
	
	/**
	 * Initialize this new Mazub with given position, sprites, maximum horizontal velocity and
	 * initial horizontal velocity.
	 * 
	 * @param 	positionLeftX
	 * 			The position on the X-axis of the leftmost pixel for this new Mazub.
	 * @param 	positionBottomY
	 * 			The position on the Y-axis of the bottom pixel for this new Mazub.
	 * @param 	maximumHorizontalVelocity
	 * 			The maximum horizontal velocity for this new Mazub.
	 * @param 	initialHorizontalVelocity
	 * 			The initial horizontal velocity for this new Mazub.
	 * @param 	spriteArray
	 * 			The array of sprites for this new Mazub.
	 * @post	The position of this new Mazub is the same as the given position.
	 * 			| (new.getXPosition() == positionLeftX) && (new.getYPosition() == positionBottomY)
	 * @post	If the given spriteArray is valid, then the array of sprites for this new Mazub is the same as the given array of sprites.
	 * 			| if (isValidSpriteArray(spriteArray))
	 * 			| 	then new.getSpriteArray == spriteArray
	 * @post	If the given maximum horizontal velocity is valid, then the maximum horizontal velocity of this new Mazub is the same
	 * 			as the given maximum horizontal velocity.
	 * 			| if (isValidMaximumHorizontalVelocity(maximumHorizontalVelocity, initialHorizontalVelocity)
	 * 			|	then new.getMaximumHorizontalVelocity() == maximumHorizontalVelocity
	 * @post	If the given initial horizontal velocity is valid, then the initial horizontal velocity of this new Mazub is the same
	 * 			as the given initial horizontal velocity.
	 * 			| if (isValidInitialVelocity(initialHorizontalVelocity, maximumHorizontalVelocity)
	 * 			|	then new.getInitialVelocity() == initialHorizontalVelocity
	 * @throws	IllegalXPositionException
	 * 			The given X position is not a valid X position for a Mazub.
	 * 			| ! isValidXPosition(positionLeftX)
	 * @throws	IllegalYPositionException
	 * 			The given Y position is not a valid Y position for a Mazub.
	 * 			| ! isValidYPosition(positionBottomY)
	 * 
	 */
	public Mazub(int positionLeftX,int positionBottomY,	Sprite[] sprites,
			double maximumHorizontalVelocity,double initialHorizontalVelocity,
			World world, int hitpoints) {
		super(positionLeftX,positionBottomY,0,0,0,0,initialHorizontalVelocity,
				maximumHorizontalVelocity,world,sprites,hitpoints);
	}
	
	/**
	 * Initialize this new Mazub with given position and sprites.
	 * 
	 * @param 	positionLeftX
	 * 			The position on the X-axis of the leftmost pixel for this new Mazub.
	 * @param 	positionBottomY
	 * 			The position on the Y-axis of the bottom pixel for this new Mazub.
	 * @param 	spriteArray
	 * 			The array of sprites for this new Mazub.
	 * @effect	The new Mazub is initialized with the given position as its position,
	 * 			the given spriteArray as its spriteArray, maximumHorizontalVelocity = 3
	 * 			and initialHorizontalVelocity = 1.
	 * 			| this(positionLeftX,positionBottomY,spriteArray, 3, 1)
	 */
	public Mazub(int positionLeftX, int positionBottomY, Sprite[] spriteArray) throws IllegalXPositionException, IllegalYPositionException {
		this(positionLeftX,positionBottomY,spriteArray, 3, 1,null,100);	
	}
	
	/**
	 * Initiialize this new Mazub with given sprites.
	 * 
	 * @param 	spriteArray
	 * 			The array of sprites for this new Mazub.
	 * @effect	The new Mazub is initialized with the given spriteArray as its spriteArray
	 * 			and the position (0,0) as its position within the gameworld.
	 * 			| this(0,0,spriteArray)
	 */
	public Mazub(Sprite[] spriteArray) {
		this(0,0,spriteArray);
	}
			
	/**
	 * Return the boolean indicating whether this Mazub is moving
	 * 	horizontally or not.
	 */
	@Basic
	public boolean getMoving(){
		return this.moving;
	}
	
	/**
	 * Variable registering whether this Mazub is moving horizontally or not.
	 */
	private boolean moving = false;
	
	/**
	 * Sets the boolean indicating whether this Mazub is moving horizontally.
	 * @param 	flag
	 * 		 	The new boolean that indicates wether or not Mazub is moving horiztonally.
	 * @post	The new moving state of this Mazub is equal to the 
	 * 			given flag.
	 * 			| new.getMoving == flag
	 */
	@Basic
	public void setMoving(boolean flag) {
		this.moving = flag;
	}
	
			
	/**
	 * Return the initial vertical velocity of this Mazub.
	 */
	public int getInitialVerticalVelocity() {
		return this.initialVerticalVelocity;
	}
	
	/**
	 * Variable registering the initial vertical velocity of this Mazub.
	 */
	private final int initialVerticalVelocity = 8;
	
	/**
	 * Return the boolean indicating whether the Mazub is ducking or not.
	 */
	private boolean getDucking() {
		return this.ducking;
	}
	
	/**
	 * Variable registering whether this Mazub is ducking or not.
	 */
	private boolean ducking = false;
	
	/**
	 * 
	 * @param	flag
	 * 			The new boolean that indicates wether or not this Mazub is ducking.
	 * @post	The new ducking state of this Mazub is equal to the given flag
	 * 			| new.getDucking == flag
	 */
	private void setDucking(boolean flag) throws IllegalStateException {
		if (this.getDucking() == flag)
			throw new IllegalStateException();
		this.ducking = flag;
	}
	
	/**
	 * Return the previous Sprite of this Mazub.
	 */
	private int getPreviousSprite(){
		return this.previousSprite;
	}
	
	/**
	 * Variable registering the previous sprite of this Mazub.
	 */
	private int previousSprite = -1;
	
	/**
	 * Sets the previous sprite of this Mazub to the given sprite.
	 * 
	 * @param 	sprite
	 * 			The new previous sprite for this Mazub.
	 * @post	The new previous sprite for this Mazub is equal to
	 * 			the given sprite.
	 * 			| new.getPreviousSprite == sprite
	 */
	private void setPreviousSprite(int sprite){
		this.previousSprite = sprite;
	}	
	
	/**
	 * Initializes horizontal movement to the direction Mazub is facing.
	 * 
	 * @param	horizontalAcceleration
	 * 			The new horizontal acceleration for this Mazub.
	 * @pre		The given horizontal acceleration must be a valid horizontal acceleration.
	 * 			| isValidHorizontalAcceleration(horizontalAcceleration)
	 * @post	The new horizontal velocity of this Mazub is equal to the initial horizontal
	 * 			velocity of this Mazub, the horizontal acceleration is set to
	 * 			the standard horizontal acceleration of a Mazub, the boolean moving is set to true and the runTime
	 * 			is set to zero.
	 * 			| new.getHorizontalVelocity() == this.getInitialHorizontalVelocity()
	 * 			|	&& new.getHorizontalAcceleration() == horizontalAcceleration
	 * 			|	&& new.getMoving() == true && new.getRunTime() == 0
	 */
	public void startMove(double horizontalAcceleration){
		if (this.getMoving() == false) {
			// Vraag: The horizontal velocity shall not be set to zero wordt al gedaan.
			// Hier is de implementatie om gewoon niets aan de snelheid te veranderen.
			// Dat moeten we vragen aan assistenten.
			assert isValidHorizontalAcceleration(horizontalAcceleration);
			this.setHorizontalAcceleration(horizontalAcceleration);
			this.setHorizontalVelocity(this.getInitialHorizontalVelocity());
			this.setMoving(true);
		}
		this.setRunTime(0);
	}
		
	/**
	 * Ends horizontal movement in the direction Mazub is facing.
	 * 
	 * @param	horizontalAcceleration
	 * 			The new horizontal acceleration for this Mazub.
	 * @pre		The given horizontal acceleration must be a valid horizontal acceleration.
	 * 			| isValidHorizontalAcceleration(horizontalAcceleration)
	 * @post	The new horizontal velocity of this Mazub is equal to zero,
	 * 			the new horizontal acceleration is set to the given horizontalAcceleration,
	 * 			the boolean moving is set to false and the runTime is set to zero.
	 * 			| new.getHorizontalVelocity() == 0 && new.getMoving == false
	 * 			|	&& new.getRunTime == 0
	 */
	public void endMove(double horizontalAcceleration) {
		assert isValidHorizontalAcceleration(horizontalAcceleration);
		this.setHorizontalVelocity(0);
		this.setHorizontalAcceleration(horizontalAcceleration);
		this.setMoving(false);
		this.setRunTime(0);
	}
		
	/**
	 * Initializes ducking.
	 * 
	 * @post	The boolean ducking is set to true.
	 * 			| new.getDucking() == true
	 */
	public void startDuck() {
			try {
				this.setDucking(true);
			} catch (IllegalStateException exc) {
				//Nothing happens if the Mazub is in an Illegal State.
			}
	}
	
	/**
	 * Ends ducking.
	 * 
	 * @post	The boolean ducking is set to false.
	 * 			| new.getDucking() == false
	 */
	public void endDuck() {
		try {
			this.setDucking(false);
		} catch (IllegalStateException exc) {
			//Nothing happens if the Mazub is in an Illegal State.
		}
	}

	
	/**
	 * Advances the time with a given timeInterval and changes every time-related attribute of this Mazub
	 * accordingly.
	 * 
	 * @param 	timeInterval
	 * 			The time that elapses in this invocation of the method advanceTime.
	 * @post	If this Mazub is moving horizontally then the new horizontal velocity is equal to the current horizontal velocity
	 * 			added to the horizontal velocity calculated with the horizontal acceleration and timeInterval.
	 *			| if (this.getMoving() == true)
	 *			|	then new.getHorizontalVelocity() == this.getHorizontalVelocity() +  horizontalVelocityCalculated
	 * @post	If this Mazub is moving vertically after the Y position is changed then the new vertical velocity is equal to
	 * 			the current vertical velocity added to the vertical velocity calculated with the vertical acceleration and timeinteval.
	 * 			| if (this.getMovingVertically() == true)
	 * 			|	then new.getVerticalVelocity() == this.getVerticalVelocity + verticalVelocityCalculated
	 * @post	The new runTime is equal to the current runTime added to the timeInterval.
	 * 			|
	 * @effect	The new X position of this Mazub is changed using timeInterval if this Mazub is moving horizontally.
	 * 			| this.changeHorizontalPosition(timeInterval)
	 * @effect	The new Y position of this Mazub is changed using timeInterval if this Mazub is moving vertically.
	 * 			| this.changeVerticalPosition(timeInterval)
	 * @throws	IllegalTimeIntervalException
	 * 			The given time interval is not a valid time interval for this Mazub.
	 * 			| (! isValidTimeInterval(timeInterval))
	 * @note	In the execution of the method advanceTime the maximum horizontal velocity may be limited to 1 if
	 * 			this Mazub is ducking. After advanceTime is done running it is set back to its original value.
	 */
	@Override
	public void advanceTime(double timeInterval) throws IllegalTimeIntervalException {
		if (! isValidTimeInterval(timeInterval))
			throw new IllegalTimeIntervalException(timeInterval);
		else if (isDying()) {
			terminate();
			this.setDeathTimer(getDeathTimer() + timeInterval);
		}
		else if (hasWonGame()) {
			getWorld().terminate();
		}
		else if (isDead())
			throw new IllegalStateException("Mazub is already dead.");
		else {
			//double dt_min = getSmallestDT(timeInterval);
			double vHmax = this.getMaximumHorizontalVelocity();
			//for (double dt = dt_min; Util.fuzzyLessThanOrEqualTo(dt, timeInterval); dt += dt_min) {
				if (this.getDucking() == true) {
					this.setMaximumHorizontalVelocity(1);
				}
				if (this.getMoving() == true){
					this.setHorizontalAcceleration(0.9);
					this.changeHorizontalPosition(timeInterval);
				}
				
				if (this.getMovingVertical() == true) {
					changeVerticalPosition(timeInterval);
					if (this.getMovingVertical() == true){
					}
				}
			//}
				this.setHorizontalVelocity(this.getHorizontalVelocity() + this.getHorizontalAcceleration()*timeInterval);
				this.setVerticalVelocity(this.getVerticalVelocity() + this.getVerticalAcceleration()*timeInterval);
				this.setMaximumHorizontalVelocity(vHmax);
				this.setRunTime(this.getRunTime() + timeInterval);
				this.setHitTimer(this.getHitTimer() + timeInterval);
				this.applyTerrainDmg(timeInterval);
				this.setMovementBlocked(false);
		}
	}
			
	/**
	 * Selects a sprite based on the previous and current actions of mazub.
	 * @pre		The sprite array of this Mazub has to be a valid sprite array.
	 */
	@Override
	public Sprite getCurrentSprite() {
		assert (Mazub.isValidSpriteArray(this.getSpriteArray()));
		int Sprite;
		if (this.getMoving() == false) {
			Sprite = this.getNotMovingSprite();
 		}
		else {
			Sprite = this.getMovingSprite();
		}
		return this.getSpriteArray()[Sprite];
	}
	
	/**
	 * Returns the sprite of this Mazub while not moving based on its current and previous actions.
	 * @pre		This mazub cannot be moving when invoking this method.
	 * @return	If this mazub has not moved within the last second and is ducking, sprite 1 is returned.
	 * @return	If this mazub has not moved within the last second and is not ducking, sprite 0 is returned.
	 * @return	If this mazub has moved within the last second, is faced to the right and
	 * 			is ducking, sprite 6 is returned.
	 * @return	If this mazub has moved within the last second, is faced to the right and
	 * 			is not ducking, sprite 2 is returned.
	 * @return	If this mazub has moved within the last second, is faced to the left and
	 * 			is ducking, sprite 7 is returned.
	 * @return	If this mazub has moved within the last second, is faced to the left and
	 * 			is not ducking, sprite 3 is returned.
	 */
	private int getNotMovingSprite() {
		assert (this.getMoving() == false);
		if (Util.fuzzyGreaterThanOrEqualTo(this.getRunTime(),1)) {
			if (this.getDucking() == true)
				return 1;
			else
				return 0;
		}
		if (this.getDirection() == Direction.RIGHT)
			if (this.getDucking() == true)
				return 6;
			else
				return 2;
		else
			if (this.getDucking() == true)
				return 7;
			else
				return 3;
	}
	
	/**
	 * Returns the sprite of this Mazub while moving based on its current and previous actions.
	 * @pre		This mazub has to be moving when invoking this method.
	 * @return	If this mazub is jumping and is faced to the right, sprite 4 is returned.
	 * @return	If this mazub is jumping and is faced to the left, sprite 5 is returned.
	 * @return	If this mazub is ducking and is faced to the right, sprite 6 is returned.
	 * @return	If this mazub is ducking and is faced to the left, sprite 7 is returned.
	 * @return	If this mazub is starting to move to to the right and is not jumping or ducking, sprite 8 is returned.
	 * @return	If this mazub is starting to move to the left and is not jumping or ducking, sprite 9 + m is returned.
	 * @return	If this mazub has been moving to the left or right and is not jumping or ducking
	 * 			the result of the method runningSprite is returned.
	 * @post	If this mazub is starting to move to to the right and is not jumping or ducking,
	 * 			the new previous sprite is set to 8.
	 * @post	If this mazub is starting to move to the left and and the runtimer is greater than 0.075
	 * 			and this mazub is not jumping or ducking,
	 * 			the new previous sprite is set to 9+m.
	 * @post	If this mazub has been moving to the left or right and the runtimer is greater than 0.075
	 * 			and this mazub is not jumping or ducking,
	 * 			the new previous sprite is set to the result of the method runningSprite.
	 */
	private int getMovingSprite() {
		assert (this.getMoving() == true);
		if (this.getMovingVertical() == true) {
			if (this.getDirection() == Direction.RIGHT)
				return 4;
			else
				return 5;
		}
		else if (this.getDucking() == true) {
			if (this.getDirection() == Direction.RIGHT)
				return 6;
			else
				return 7;
		}
		else {
			int m = ((this.getSpriteArray().length - 8) / 2) - 1;
			if (this.getDirection() == Direction.RIGHT) {
				if ((this.getPreviousSprite() < 8) || (this.getPreviousSprite() > 8 + m))
				{
					this.setPreviousSprite(8);
					return 8;
				}
			}
			else if (this.getDirection() == Direction.LEFT){
				if ((this.getPreviousSprite() < 9+m)|| (this.getPreviousSprite() > 9+ 2*m))
				{
					this.setPreviousSprite(9+m);
					return 9 + m;
				}
			}
			if (Util.fuzzyGreaterThanOrEqualTo(runTime,0.075)) {
				int Sprite;
				Sprite = this.runningSprite(m);
				this.setPreviousSprite(Sprite);
				return Sprite;
			}
		}
		return this.getPreviousSprite();
	}

	/**
	 * Returns the current sprite while running based on its previous sprite.
	 * @pre		This mazub has to be moving when invoking this method.
	 * @return	The current sprite is returned based on this mazub's previous sprite.
	 * 			if the previous sprite was 8+m or 9+ 2*m the returned sprite is
	 * 			8 & 9 respsectively. Otherwise the sprite returned is  previous sprite + 1.
	 * @post	The run time is set to zero.
	 * 
	 */
	private int runningSprite(int m) {
		assert (this.getMoving() == true);
		int Sprite;
		if (this.getDirection() == Direction.RIGHT) {
			if (this.getPreviousSprite() == 8 + m)
				Sprite = 8;
			else{
				Sprite = this.getPreviousSprite() + 1;
			}
		}
		else {
			if (this.getPreviousSprite() == 9 + 2*m)
				Sprite = 9 + m;
			else
				Sprite = this.getPreviousSprite() + 1;
		}
		this.setRunTime(0); 
		return Sprite;			
	}
	
	/**
	 * Checks whether the given spriteArray is a valid spriteArray.
	 * 
	 * @param 	spriteArray
	 * 			The spriteArray to check against.
	 * @return	True if the spriteArray is a valid spriteArray.
	 * 			| result ==
	 * 			|	(spriteArray.length >= 0) &&  (spriteArray.length % 2 == 0)
	 */
	public static boolean isValidSpriteArray(Sprite[] spriteArray) {
		int length = spriteArray.length;
		return (length >= 10) && (length % 2 == 0);
	}
	
	/**
	 * returns the minimum hit points for this Slime
	 */
	public int getMinHP() {
		return 0;
	}
	
	/**
	 * returns the maximum hit points for this slime
	 */
	public int getMaxHP() {
		return 500;
	}
	
	public void hitByEnemy() {
		this.addHP(getHitAmount());
	}
	
	public int getHealAmount() {
		return healAmount;
	}
	
	private int healAmount = 50;
	
	private int getHitAmount() {
		return hitAmount;
	}
	
	private int hitAmount = -50;
	
	public boolean hasWonGame() {
		return this.getWorld().hasWonGame();
	}
	
	@Override
	public boolean canHaveAsWorld(World world) {
		return (world != null) && (world.canHaveAsMazub(this));
	}
	
	public void terminate() {
		if (!isDead()) {
			if ((isAlive() && (getHP() > 0) && (! getOutOfBounds()) && (! hasWonGame())))
				throw new IllegalStateException("Mazub is alive within the boundaries of the world and didn't win!");
			else if (((isDying()) && (Util.fuzzyGreaterThanOrEqualTo(getDeathTimer(), 0.6))) ||
					(getOutOfBounds())) {
				setState(State.DEAD);
				World oldWorld = getWorld();
				setWorld(null);
				oldWorld.terminate();
				setHP(0);
			}
			else if (isAlive()) {
				setState(State.DYING);
				setDeathTimer(0);
			}
		}
	}
	
}
