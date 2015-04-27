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
	 * @param 	sprites
	 * 			The array of sprites for this new Mazub.
	 * @param 	maximumHorizontalVelocity
	 * 			The maximum horizontal velocity for this new Mazub.
	 * @param 	initialHorizontalVelocity
	 * 			The initial horizontal velocity for this new Mazub.
	 * @param	world
	 * 			The world for this new Mazub.
	 * @param	hitpoints
	 * 			The hitpoints for this new Mazub.
	 * @effect	The new Mazub is initialized as a LivingCreature with horizontalVelocity, verticalVelocity
	 * 			horizontalAcceleration and verticalAcceleration set to 0.
	 * 			| super(positionLeftX,positionBottomY,0,0,0,0,initialHorizontalVelocity,
				|	maximumHorizontalVelocity,world,sprites,hitpoints)
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
	 * 			and initialHorizontalVelocity = 1. The world is set to null and hitpoints
	 * 			are set to 100.
	 * 			| this(positionLeftX,positionBottomY,spriteArray, 3, 1, null, 100)
	 */
	public Mazub(int positionLeftX, int positionBottomY, Sprite[] spriteArray) {
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
	 * @throws	IllegalStateException
	 * 			Mazub tries to stand up but can't.
	 * 			| ((flag == false) && (!canStandUp()))
	 */
	private void setDucking(boolean flag) throws IllegalStateException {
		if ((flag == false) && (!canStandUp()))
			throw new IllegalStateException();
		else
			this.ducking = flag;
	}
	
	/**
	 * Checks whether this Mazub can stand up in its current position.
	 * @return	False if the top border of this mazub collides with solid terrain, true otherwise.
	 */
	public boolean canStandUp(){
		if ((world.getTileType((int)this.getXPosition()+1, (int)this.getYPosition() + getSize()[1] + world.getTileSize()) == 1) 
				|| ((world.getTileType((int)(this.getXPosition()+1 + this.getSize()[0]),
						(int)(this.getYPosition()  + getSize()[1] + world.getTileSize())) == 1))){
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Return whether this mazub is trying to end his duck.
	 */
	private boolean getTryEndingDuck() {
		return this.tryEndingDuck;
	}
	
	/**
	 * Variable registering whether this mazub is trying to end his duck.
	 */
	private boolean tryEndingDuck = false;
	
	/**
	 * Sets whether this mazub tries to end his duck.
	 * @param 	flag
	 * 			The flag to be set.
	 * @post	The new trying to end duck state of this mazub is equal to the given flag.
	 * 			| new.getTryEndingDuck() == flag
	 */
	private void setTryEndingDuck(boolean flag) {
		this.tryEndingDuck = flag;
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
		assert isValidHorizontalAcceleration(horizontalAcceleration);
		this.setHorizontalAcceleration(horizontalAcceleration);
		this.setHorizontalVelocity(this.getInitialHorizontalVelocity());
		this.setMoving(true);
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
			this.setTryEndingDuck(false);
		} catch (IllegalStateException exc) {
			this.setTryEndingDuck(true);
		}
	}

	
	/**
	 *TODO
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
			double dt_min = getSmallestDT(timeInterval);
			for (double dt = dt_min; dt <= timeInterval-dt_min; dt += dt_min){
				advanceTimeAlive(dt_min);
			}
			advanceTimeAlive(timeInterval - (timeInterval - dt_min));
		}
	}
	
	/**
	 * Advances the time with a given timeInterval and changes every time-related attribute of this Mazub
	 * accordingly.
	 * 
	 * @param 	timeInterval
	 * 			The time that elapses in this invocation of the method advanceTimeAlive.
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
	public void advanceTimeAlive(double timeInterval) {
		double vHmax = this.getMaximumHorizontalVelocity();
		if (this.getDucking() == true) {
			this.setMaximumHorizontalVelocity(1);
		}
		if (this.getMoving() == true){
			this.changeHorizontalPosition(timeInterval,0.9);
		}
		
		if (this.getMovingVertical() == true) {
			changeVerticalPosition(timeInterval);
		}
		if ((this.getTryEndingDuck()) && (canStandUp())) {
			endDuck();
		}
		Interaction.interactWithOtherCreatures(this);
		this.setHorizontalVelocity(this.getHorizontalVelocity() + this.getHorizontalAcceleration()*timeInterval);
		this.setVerticalVelocity(this.getVerticalVelocity() + this.getVerticalAcceleration()*timeInterval);
		this.setMaximumHorizontalVelocity(vHmax);
		this.setRunTime(this.getRunTime() + timeInterval);
		this.setHitTimer(this.getHitTimer() + timeInterval);
		this.applyTerrainDmg(timeInterval);
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
	
	/**
	 * Changes hp according to the amount of hitpoints distracted when hit.
	 * @effect	Adds hitpoints with the hit amount.
	 * 			| addHp(getHitAmount())
	 */
	public void hitByEnemy() {
		this.addHP(getHitAmount());
	}
	
	/**
	 * Returns the amount of hitpoints to be healed.
	 */
	public int getHealAmount() {
		return healAmount;
	}
	
	/**
	 * Variable registering the amount of hitpoints to be healed.
	 */
	private final int healAmount = 50;
	
	/**
	 * Return the amount of hitpoints to be substracted when hit.
	 */
	private int getHitAmount() {
		return hitAmount;
	}
	
	/**
	 * Variable registering the amount of hitpoints to be substracted when hit.
	 */
	private final int hitAmount = -50;
	
	/**
	 * Checks whether this Mazub has won the game.
	 * @return	True if this mazub has won, false otherwise.
	 * 			| result ==
	 * 			|	this.getWorld().hasWonGame()
	 */
	public boolean hasWonGame() {
		return this.getWorld().hasWonGame();
	}
	
	/**
	 * Checks whether this Mazub can have the given world as his world.
	 * @return	True if the world is effective and the world can have this mazub
	 * 			as his mazub.
	 * 			| result ==
	 * 			|	(world != null) && (world.canHaveAsMazub(this))
	 */
	@Override
	public boolean canHaveAsWorld(World world) {
		return (world != null) && (world.canHaveAsMazub(this));
	}
	
	/**
	 * Terminates this mazub if it's still alive.
	 * @post 	If this Mazub isn't dying yet but meets the requirements to die, he is set to be dying.
	 * 		 	|if isAlive() && (getHP() > 0) && (! getOutOfBounds())
	 * 			| new.getState == state.DYING
	 * @post 	If this Mazub is dying for longer then 0.6 seconds or out of the game boundaries he is terminated
	 * 		 	|if isDying() && Util.fuzzyGreaterThanOrEqualTo(getDeathTimer(), 0.6) || getOutOfBounds()
	 * 		 	| new.getWorld() == null
	 * 		 	| new.getState() == state.DEAD
	 * 		 	| new !in (old.getWorld().getSlimes())
	 * @throws	IllegalStateException
	 * 			This Mazub is alive within the boundaries of the world and didnt win.
	 * 			| (isAlive() && (getHP() > 0) && (! getOutOfBounds()) && (! hasWonGame())
	 */
	public void terminate() {
		if (!isDead()) {
			if ((isAlive() && (getHP() > 0) && (! getOutOfBounds()) && (! hasWonGame()) && (!this.getWorld().isTerminating())))
				throw new IllegalStateException("Mazub is alive within the boundaries of the world and didn't win!");
			else if (((isDying()) && (Util.fuzzyGreaterThanOrEqualTo(getDeathTimer(), 0.6))) ||
					(getOutOfBounds())) {
				setState(State.DEAD);
				World oldWorld = getWorld();
				oldWorld.terminate();
				setWorld(null);
				setHP(0);
			}
			else if (isAlive()) {
				setState(State.DYING);
				setDeathTimer(0);
			}
		}
	}
	
}
