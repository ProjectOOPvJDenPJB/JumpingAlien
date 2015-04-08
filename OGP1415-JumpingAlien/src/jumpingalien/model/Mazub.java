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
			double maximumHorizontalVelocity,double initialHorizontalVelocity, World world) {
		super(positionLeftX,positionBottomY,world,sprites);
		assert isValidInitialVelocity(initialHorizontalVelocity, maximumHorizontalVelocity);
		assert isValidMaximumHorizontalVelocity(maximumHorizontalVelocity, initialHorizontalVelocity);
		this.initialHorizontalVelocity = initialHorizontalVelocity;
		this.setMaximumHorizontalVelocity(maximumHorizontalVelocity);
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
		this(positionLeftX,positionBottomY,spriteArray, 3, 1,null);	
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
	private boolean getMoving(){
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
	private void setMoving(boolean flag) {
		this.moving = flag;
	}
	
	/**
	 * Return the boolean indicating whether this Mazub is moving
	 * 	vertically or not.
	 */
	@Basic
	public boolean getMovingVertical() {
		return this.movingVertical;
	}
	
	/**
	 * Variable registering whether this Mazub is moving vertically or not.
	 */
	private boolean movingVertical = false;

	/**
	 * Sets the boolean indicating whether this Mazub is moving vertically.
	 * 
	 * @param 	flag
	 * 		 	The new boolean that indicates wether or not Mazub is moving vertically.
	 * @post	The new movingVertical state of this Mazub is equal to the 
	 * 			given flag.
	 * 			| new.getMovingVertical == flag
	 */
	@Basic
	public void setMovingVertical(boolean flag) throws IllegalStateException {
		if (this.getMovingVertical() == flag)
			throw new IllegalStateException();
		this.movingVertical = flag;
	}
		
	/**
	 *  Return the horizontal velocity of this Mazub.
	 */
	@Basic
	public double getHorizontalVelocity() {
		return this.horizontalVelocity;
	}
	
	/**
	 * Variable registering the horizontal velocity of this Mazub.
	 * 	The standard horizontal velocity is 0.
	 */
	private double horizontalVelocity = 0;
	
	/**
	 * Sets the horizontal velocity of this Mazub to the given horizontal velocity.
	 * 
	 * @param	horizontalVelocity
	 * 			The new horizontal velocity for this Mazub.
	 * @post	If the given horizontalVelocity is greater than or equal to zero and smaller or equal to
	 * 			the maximumHorizontalVelocity then the horizontalVelocity of this Mazub is equal to
	 * 			the given horizontalVelocity.
	 * 			| if (horizontalVelocity >= 0) 
	 * 			|	&& (horizontalVelocity <= this.getMaximumHorizontalVelocity())
	 * 			|	then new.getHorizontalVelocity() == horizontalVelocity
	 * @post	If the given horizontalVelocity is smaller than zero then the horizontalVelocity of this Mazub
	 * 			is equal to zero.
	 * 			| if (horizontalVelocity  < 0)
	 * 			|	then new.getHorizontalVelocity() == 0
	 * @post	If the given horizontalVelocity is greater than the maximumHorizontalVelocity then the
	 * 			horizontalVelocity of this Mazub is equal to the maximumHorizontalVelocity.
	 * 			| if (horizontalVelocity > this.getMaximumHorizontalVelocity())
	 * 			|	then new.getHorizontalVelocity() == this.getMaximumHorizontalVelocity()
	 */
	public void setHorizontalVelocity(double horizontalVelocity) {
		if (horizontalVelocity  < 0) {
			this.horizontalVelocity = 0;
		}
		else if (Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,this.getMaximumHorizontalVelocity())) {
			this.horizontalVelocity = this.getMaximumHorizontalVelocity();
		}
		else {
			this.setHorizontalAcceleration(0.9);
			this.horizontalVelocity = horizontalVelocity;
		}
	}

	/**
	 *  Return the vertical velocity of this Mazub.
	 */
	public double getVerticalVelocity(){
		return this.verticalVelocity;
	}
	
	/**
	 * Variable registering the vertical velocity of this Mazub.
	 * 	The standard vertical velocity is 0.
	 */
	private double verticalVelocity = 0;
	
	/**
	 * Sets the vertical velocity of this Mazub to the given vertical velocity.
	 * 
	 * @param	verticalVelocity
	 * 			The new vertical velocity for this Mazub.
	 * @post	If the given vertical velocity smaller than or equal to the Initial vertical
	 * 			velocity of this Mazub, then the new vertical velocity is equal to the
	 * 			given vertical velocity.
	 * 			| if (verticalVelocity <= this.getInitialVerticalVelocity())
	 * 			| 	then new.verticalVelocity = verticalVelocity
	 * @post	If the given vertical velocity is bigger than the initial vertical velocity
	 * 			then the new vertical velocity is equal to the initial vertical velocity.
	 * 			|if (verticalVelocity > this.getInitialVerticalVelocity())
				|	then new.verticalVelocity = this.getInitialVerticalVelocity()
	 */
	public void setVerticalVelocity(double verticalVelocity){
		if (verticalVelocity > this.getInitialVerticalVelocity()){
			this.verticalVelocity = this.getInitialVerticalVelocity();
		}
		else {
			this.verticalVelocity = verticalVelocity;
		}
	}
	
	/**
	 * Return the vertical accelartion of this Mazub.
	 */
	@Immutable
	public double getVerticalAcceleration(){
		return this.verticalAcceleration;
	}
	
	/**
	 * A variable registering the vertical Acceleration of this Mazub.
	 * 	This is a final variable which does not change during the game.
	 */
	private double verticalAcceleration;
	
	/**
	 * Sets the vertical acceleration of this Mazub to the given vertical acceleration.
	 * 
	 * @param 	verticalAcceleration
	 * 			The new vertical acceleration for this mazub.
	 * @post	If the vertical acceleration is not 0 or -10 the new
	 * 			vertical acceleration is set to 0.
	 * 			| if ((verticalAcceleration != 0) || (verticalAcceleration != -10))
	 * 			|	new.getVerticalAcceleration() == 0
	 * @post	If the vertical acceleration is 0 or -10 the new
	 * 			vertical acceleration is set to the given vertical 
	 * 			acceleration.
	 * 			| if ((verticalAcceleration == 0) || (verticalAcceleration == -10))
	 * 			|	new.getVerticalAcceleration() == verticalAcceleration
	 */	
	public void setVerticalAcceleration(double verticalAcceleration){
		if ((verticalAcceleration != 0) && (verticalAcceleration != -10))
			this.verticalAcceleration = 0;
		else
			this.verticalAcceleration = verticalAcceleration;
	}
	
	/**
	 * Return the initial horizontal velocity of this Mazub.
	 */
	@Immutable
	public double getInitialHorizontalVelocity() {
		return this.initialHorizontalVelocity;
	}
	
	/**
	 * Check whether the given initial horizontal velocity is a valid initial horizontal velocity.
	 * 
	 * @param 	initialHorizontalVelocity
	 * 			The initial horizontal velocity to check.
	 * @param	maximumHorizontalVelocity
	 * 			The maximum horizontal velocity to check the initial velocity against.
	 * @return 	True if the given initial horizontal velocity is a valid initial horizontal velocity.
	 * 			| result ==
	 * 			|	(initialHorizontalVelocity >= 1) && (initialHorizontalVelocity < maximumHorizontalVelocity)
	 * 
	 */
	public static boolean isValidInitialVelocity(double initialHorizontalVelocity, double maximumHorizontalVelocity) {
		return (Util.fuzzyGreaterThanOrEqualTo(initialHorizontalVelocity, 1)) && (initialHorizontalVelocity < maximumHorizontalVelocity);
	}
	
	/**
	 * Variable registering the initial horizontal velocity of this Mazub.
	 */
	private final double initialHorizontalVelocity;
	
	/**
	 * Return the maximum horizontal velocity of this Mazub.
	 */
	@Basic
	public double getMaximumHorizontalVelocity() {
		return this.maximumHorizontalVelocity;
	}
	
	/**
	 * Check whether the given maximum horizontal velocity is a valid maximum horizontal velocity.
	 * 
	 * @param 	maximumHorizontalVelocity
	 * 			The maximum horizontal velocity to check.
	 * @param	initialHorizontalVelocity
	 * 			The initial horizontal velocity to check the maximum horizontal velocity against.
	 * @return 	True if the given maximum horizontal velocity is a valid maximum horizontal velocity.
	 * 			| result == (maximumHorizontalVelocity > initialHorizontalVelocity)
	 */
	public static boolean isValidMaximumHorizontalVelocity(double maximumHorizontalVelocity, double initialHorizontalVelocity) {
		return maximumHorizontalVelocity > initialHorizontalVelocity;
	}
	
	/**
	 * Variable registering the maximum horizontal velocity of this Mazub.
	 */
	private double maximumHorizontalVelocity;
	
	/**
	 * Sets the maximum horizontal velocity of this Mazub to the given maximum horizontal velocity.
	 * @param	maximumHorizontalVelocity
	 * 			The new maximum horizontal velocity for this Mazub.
	 * @pre		The given maximum horizontal velocity must be a valid maximum horizontal velocity
	 * 			for this Mazub.
	 * 			| isValidMaximumHorizontalVelocity(maximumHorizontalVelocity, this.getInitialHorizontalVelocity())
	 * @post	The new maximum horizontal velocity of this Mazub is equal to the given maximum horizontal velocity.
	 * 			| new.getMaximumHorizontalVelocity() == maximumHorizontalVelocity
	 */
	private void setMaximumHorizontalVelocity(double maximumHorizontalVelocity) {
		assert isValidMaximumHorizontalVelocity(maximumHorizontalVelocity, this.getInitialHorizontalVelocity());
		this.maximumHorizontalVelocity = maximumHorizontalVelocity;
	}
	
	/**
	 * Return the horizontal acceleration of this Mazub.
	 */
	@Basic @Immutable
	public double getHorizontalAcceleration() {
		return this.horizontalAcceleration;	
	}
	
	/**
	 * 
	 * @param 	horizontalAcceleration
	 * 			The horizontal acceleration to check.
	 * @return	True if and only if the given acceleration is positive or zero.
	 * 			| result == (horizontalAcceleration >= 0)
	 */
	public static boolean isValidHorizontalAcceleration(double horizontalAcceleration) {
		return horizontalAcceleration >= 0;
	}
	
	/**
	 * Variable registering the horizontal acceleration of this Mazub.
	 */
	private double horizontalAcceleration;
	
	/**
	 * Sets the horizontal acceleration to the given horizontal acceleration.
	 * 
	 * @param	horizontalAcceleration
	 * 			The new horizontal acceleration for this Mazub
	 * @post	The new horizontal acceleration for this Mazub is equal to
	 * 			the given horizontal acceleration.	
	 */
	public void setHorizontalAcceleration(double horizontalAcceleration){
		this.horizontalAcceleration = horizontalAcceleration;
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
	 * Return the direction of this Mazub, either -1 for left or 1 for right.
	 */
	public int getDirection() {
		return this.direction;
	}
	
	/**
	 * Variable indicating the direction of this Mazub. -1 indicates left, 1 indicates right.
	 */
	private int direction;

	/**
	 * Sets the direction of this Mazub to the given direction.
	 * 
	 * @param 	direction
	 * 			The new direction of this Mazub.
	 * @post	If the given direction is "left" then the new direction of this Mazub is equal to -1.
	 * 			| if (direction == "left")
	 * 			| 	then this.direction = -1
	 * @post	If the given direction is "right" then the new direction of this Mazub is equal to 1.
	 * 			| if (direction == "right")
	 * 			|	then this.direction = 1
	 * @note	If the given direction is not equal to "left" or "right" then nothing happens to the 
	 * 			variable direction of this Mazub.
	 */
	public void setDirection(String direction) {
		if (direction == "left")
			this.direction = -1;
		else if (direction == "right")
			this.direction = 1;
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
	 * Return the runtimer of this Mazub.
	 */
	private double getRunTime() {
		return this.runTime;
	}
	
	/**
	 * Variable registering the time since Mazub stopped running when Mazub is not moving horizontally.
	 * When Mazub moves horizontally, the variable registers the time since the last spritechange.
	 */
	private double runTime = 1;

	/**
	 * Sets the runTimer of this Mazub to the given time.
	 * 
	 * @param 	runTimer
	 * 			The new time for the runTimer for this Mazub.
	 * @post	The new runTimer for this Mazub is equal to the given
	 * 			runTimer.
	 * 			| new.getRunTime == runTime
	 */
	private void setRunTime(double runTime) {
		this.runTime = runTime;
	}
	
		
	/**
	 * @param 	timeInterval 
	 * 			The time interval in which the position of this mazub has changed.
	 * @post	The new X position of this Mazub is equal to the current X position added to the horizontal distance
	 * 			travelled calculated with a formula using the given time interval. 
	 * 			new.getXPosition = this.getXPosition() + distanceCalculated
	 */
	public void changeHorizontalPosition(double timeInterval){
		if (Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,this.getMaximumHorizontalVelocity())) 
			this.setHorizontalAcceleration(0);
		double newPositionX = this.getXPosition() + this.getDirection() * (100 * this.getHorizontalVelocity()*timeInterval 
				+ 50 * this.getHorizontalAcceleration()*timeInterval*timeInterval); 
		setPosition(newPositionX, getYPosition());
	}
	 /**
	  * @param 	timeInterval
	  * 		The time interval in which the position of this mazub has changed.
	  * @post	The new Y position of this Mazub is equal to the current Y position added to the vertical distance
	  * 		travelled calculated with a formula using the given time interval. 
	  * 		new.getYPosition = this.getYPosition() + distanceCalculated
	  */
	public void changeVerticalPosition(double timeInterval) {
		if (this.getMovingVertical() == true){
			double newPositionY = this.getYPosition() 
					+ 100 * this.getVerticalVelocity() * timeInterval
					+ 50 * this.getVerticalAcceleration() * timeInterval * timeInterval;
			setPosition(getXPosition(), newPositionY);

			if (Util.fuzzyEquals(0, getYPosition())) {
				this.endJump();
				this.setMovingVertical(false);
				this.setVerticalVelocity(0);
				this.setVerticalAcceleration(0);
			}
		}
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
	 * Initializes vertical movment.
	 * 
	 * @post	If the current Y position of this Mazub is equal to zero then
	 * 			the new vertical velocity is set to the initial vertical velocity, 
	 * 			the new vertical acceleration is set to its default value
	 * 			and the boolean movingVertical is set to true.
	 * 			| if (this.getYPosition() == 0) 
	 *			|	then new.getVerticalVelocity() == this.getInitialVerticalVelocity()
	 *			|	&& new.getMovingVertical() == true
	 */
	public void startJump() {
		try{
			this.setMovingVertical(true);
			this.setVerticalVelocity(this.getInitialVerticalVelocity());
			this.setVerticalAcceleration(-10);
		} catch (IllegalStateException exc) {
			//Nothing happens if the Mazub is in an Illegal State.
		}
			
	}
	
	/**
	 * Ends vertical movement.
	 * 
	 * @post	If the vertical velocity is greater than zero then
	 * 			the new vertical velocity is set to zero.
	 * 			| if (this.getVerticalVelocity() > 0)
	 * 			|	then new.getVerticalVelocity() == 0
	 */
	public void endJump(){
		if (this.getVerticalVelocity() > 0){
			this.setVerticalVelocity(0);
		}
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
	 * Checks whether the given timeInterval is a valid timeInterval for this Mazub.
	 * 
	 * @param 	timeInterval
	 * 			The timeInterval to be checked.
	 * @return	True if and only if the time interval is between 0 ands 0.2.
	 * 			| result ==
	 * 			|	(timeInterval >= 0) && (timeInterval <= 0.2)
	 */
	public static boolean isValidTimeInterval(double timeInterval) {
		return Util.fuzzyGreaterThanOrEqualTo(timeInterval, 0) && Util.fuzzyLessThanOrEqualTo(timeInterval, 0.2);
	}
	
	/**
	 * Returns the size of a sprite of this Mazub.
	 * @param 	sprite
	 * 			The sprite of which the size must be determined.
	 * @return	Returns the size of the given sprite in an array of type int.
	 * 			this array consists of two elements, the height and width respectively.
	 * @throws	IllegalSizeException
	 * 			The size of this sprite is not a valid size for a sprite of this Mazub.
	 * 			| (! isValidSize([sprite.getHeight(),sprite.getWidth()]))
	 */
	public static int[] getSize(Sprite sprite) throws IllegalSizeException {
		int[] size = new int[2];
		size[0] = sprite.getHeight();
		size[1] = sprite.getWidth();
		if (! isValidSize(size))
			throw new IllegalSizeException(size);
		return size;
	}
	
	/**
	 * Checks whether the given size is a valid size for this Mazub.
	 * 
	 * @param 	size
	 * 			The size to be checked.
	 * @return	True if and only if the size is greater or equal to 0.
	 * 			| result ==
	 * 			|	(size[0] > 0) && (size[1] > 0)
	 */
	public static boolean isValidSize(int[] size) {
		return Util.fuzzyGreaterThanOrEqualTo(size[0], 0) && Util.fuzzyGreaterThanOrEqualTo(size[1], 0);
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
	public void advanceTime(double timeInterval) throws IllegalTimeIntervalException {
		if (! isValidTimeInterval(timeInterval))
			throw new IllegalTimeIntervalException(timeInterval);
		double vHmax = this.getMaximumHorizontalVelocity();
		if (this.getDucking() == true) {
			this.setMaximumHorizontalVelocity(1);
		}
		if (this.getMoving() == true){
			this.changeHorizontalPosition(timeInterval);
			this.setHorizontalVelocity(this.getHorizontalVelocity() + this.getHorizontalAcceleration()*timeInterval);
		}
		
		if (this.getMovingVertical() == true) {
			changeVerticalPosition(timeInterval);
			if (this.getMovingVertical() == true){
				this.setVerticalVelocity(this.getVerticalVelocity() + this.getVerticalAcceleration()*timeInterval);
			}
		}
		this.setMaximumHorizontalVelocity(vHmax);
		this.setRunTime(this.getRunTime() + timeInterval);
	}
			
	/**
	 * Selects a sprite based on the previous and current actions of mazub.
	 * @pre		The sprite array of this Mazub has to be a valid sprite array.
	 */
	public Sprite getCurrentSprite() {
		assert (isValidSpriteArray(this.getSpriteArray()));
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
		if (this.getDirection() == 1)
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
			if (this.getDirection() == 1)
				return 4;
			else
				return 5;
		}
		else if (this.getDucking() == true) {
			if (this.getDirection() == 1)
				return 6;
			else
				return 7;
		}
		else {
			int m = ((this.getSpriteArray().length - 8) / 2) - 1;
			if (this.getDirection() == 1) {
				if ((this.getPreviousSprite() < 8) || (this.getPreviousSprite() > 8 + m))
				{
					this.setPreviousSprite(8);
					return 8;
				}
			}
			else if (this.getDirection() == -1){
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
		if (this.getDirection() == 1) {
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
}
