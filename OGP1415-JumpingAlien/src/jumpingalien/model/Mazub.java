package jumpingalien.model;
import jumpingalien.util.*;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of Mazub.
 * 
 * 
 * @author Joren Dhont & Pieterjan Beerden
 * @version 0.1
 */
public class Mazub {
	
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
	public Mazub(int positionLeftX,int positionBottomY,	Sprite[] spriteArray,
			double maximumHorizontalVelocity,double initialHorizontalVelocity) throws IllegalXPositionException, IllegalYPositionException {
		assert isValidInitialVelocity(initialHorizontalVelocity, maximumHorizontalVelocity);
		assert isValidMaximumHorizontalVelocity(maximumHorizontalVelocity, initialHorizontalVelocity);
		assert isValidSpriteArray(spriteArray);
		if (!isValidXPosition(positionLeftX))
			throw new IllegalXPositionException(positionLeftX);
		if (!isValidYPosition(positionBottomY))
			throw new IllegalYPositionException(positionBottomY);
		this.setXPosition(positionLeftX);
		this.setYPosition(positionBottomY);
		this.initialHorizontalVelocity = initialHorizontalVelocity;
		this.setMaximumHorizontalVelocity(maximumHorizontalVelocity);
		this.spriteArray = spriteArray;
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
		this(positionLeftX,positionBottomY,spriteArray, 3, 1);	
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
	 * Return the X position of this mazub.
	 * 	The position is the actual position of the left X pixel of
	 * 	the Mazub character in the gameworld.
	 */
	@Basic
	public double getXPosition() {
		return this.positionLeftX;
	}

	/**
	 * Check whether the given X position is a valid X position for a Mazub.
	 * 
	 * @param positionX
	 * 			The position on the X-axis to check against.
	 * @return	True if the given position is a valid position:
	 * 			| result ==
	 * 			|	(position <= 1024) && (position >= 0)
	 */
	public static boolean isValidXPosition(double positionX) {
		return Util.fuzzyLessThanOrEqualTo(positionX,1024) && Util.fuzzyGreaterThanOrEqualTo(positionX, 0);
	}
	
	/**
	 * Variable registering the X position of the leftmost pixel of Mazub.
	 */
	private double positionLeftX;

	/**
	 * Set the X position of this Mazub to the given X position.
	 * @param	positionLeftX
	 * 			The new position on the X-axis of the leftmost pixel for this Mazub.
	 * @post	The new X position of the leftmost pixel of the Mazub is equal to the
	 * 			given positionLeftX.
	 * @throws 	IllegalXPositionException
	 * 			The given X position is not a valid X position for a Mazub.
	 * 			| ! isValidXPosition(positionLeftX)
	 */
	@Basic @Raw
	public void setXPosition(double positionLeftX) throws IllegalXPositionException {
		if (! isValidXPosition(positionLeftX))
			throw new IllegalXPositionException(positionLeftX);
		this.positionLeftX = positionLeftX;
	}
	
	/**
	 * Return the Y position of this mazub.
	 * 	The position is the actual position of the bottom Y pixel of
	 * 	the Mazub character in the gameworld.
	 */
	@Basic
	public double getYPosition () {
		return this.positionBottomY;
	}
	
	
	/**
	 * Check whether the given Y position is a valid Y position for a Mazub.
	 * 
	 * @param 	positionY
	 * 			The position on the Y-axis to check against.
	 * @return	True if the given Y position is a valid Y position.
	 * 			| result ==
	 * 			|	(position <= 768) && (position >= 0)
	 */
	public static boolean isValidYPosition(double positionY) {
		return Util.fuzzyLessThanOrEqualTo(positionY,768) && Util.fuzzyGreaterThanOrEqualTo(positionY,0);
	}
	
	/**
	 * Variable registering the Y position of the bottom pixel of this Mazub.
	 */
	private double positionBottomY;
	
	/**
	 * Set the Y position of this Mazub to the given Y position.
	 * @param	positionBottomY
	 * 			The new position on the Y-axis of the bottom pixel for this Mazub.
	 * @post	The new Y position of the bottom pixel of the Mazub is equal to the
	 * 			given positionBottomY.
	 * @throws 	IllegalYPositionException
	 * 			The given Y position is not a valid Y position for a Mazub.
	 * 			| ! isValidYPosition(positionBottomY)
	 */
	@Basic @Raw
	public void setYPosition(double positionBottomY) throws IllegalYPositionException {
		if (! isValidYPosition(positionBottomY))
			throw new IllegalYPositionException(positionBottomY);
		this.positionBottomY = positionBottomY;
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
	private void setMoving(boolean flag){
		this.moving = flag;
	}
	
	/**
	 * Return the boolean indicating whether this Mazub is moving
	 * 	vertically or not.
	 */
	@Basic
	private boolean getMovingVertical(){
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
	private void setMovingVertical(boolean flag){
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
		if (horizontalVelocity > this.getMaximumHorizontalVelocity()) {
			this.horizontalVelocity = this.getMaximumHorizontalVelocity();
		}
		else {
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
	 * 
	 * @param verticalAcceleration
	 */
	public void setVerticalAcceleration(double verticalAcceleration){
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
	public void setMaximumHorizontalVelocity(double maximumHorizontalVelocity) {
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
	private void setDucking(boolean flag) {
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
	private int previousSprite;
	
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
	 * Return the spriteArray of this Mazub.
	 */
	public Sprite[] getSpriteArray() {
		return this.spriteArray;
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
	 * Variable registering the spriteArray of this Mazub.
	 */
	private final Sprite[] spriteArray;
		
	/**
	 * @param 	timeInterval 
	 * 			The time interval in which the position of this mazub has changed.
	 * @post	The new X position of this Mazub is equal to the current X position added to the horizontal distance
	 * 			travelled calculated with a formula using the given time interval. 
	 * 			new.getXPosition = this.getXPosition() + distanceCalculated
	 */
	public void changeHorizontalPosition(double timeInterval){
		double newPosition = this.getXPosition() + this.getDirection() * (100 * this.getHorizontalVelocity()*timeInterval 
				+ 50 * this.getHorizontalAcceleration()*timeInterval*timeInterval);
			try { 
				this.setXPosition(newPosition);
			} catch (IllegalXPositionException exc) {
				if (newPosition < 0){
					this.setXPosition(0);
				// if the new X position calculated is smaller than zero then the 
				// X position is set to 0, which is the left border of the gameworld.
				}
				else {
					this.setXPosition(1024);
				// if the new X position calculated is greater than 1024 then the
				// X position is set to 1024, which is the right border of the gameworld.
				}
			}
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
			double newPosition = this.getYPosition() 
					+ 100 * this.getVerticalVelocity() * timeInterval
					+ 50 * this.getVerticalAcceleration() * timeInterval * timeInterval;
			try {
				this.setYPosition(newPosition);
			} catch (IllegalYPositionException exc) {
				if (newPosition < 0) {
					this.endJump();
					this.setMovingVertical(false);
					this.setYPosition(0);
					this.setVerticalVelocity(0);
					this.setVerticalAcceleration(0);
				// If the new Y position calculated is smaller than zero then the 
				// Y position is set to 0, which is the bottom border of the gameworld.
				}
				else {
					this.setYPosition(768);
				// If the new Y position calculated is greater than 768 then the
				// Y position is set to 768, which is the top border of the gameworld.
				}
			}
		}
	}
	
	/**
	 * Initializes horizontal movement to the direction Mazub is facing.
	 * 
	 * @post	The new horizontal velocity of this Mazub is equal to the initial horizontal
	 * 			velocity of this Mazub, the boolean moving is set to true and the runTime
	 * 			is set to zero.
	 * 			| new.getHorizontalVelocity() == this.getInitialHorizontalVelocity()
	 * 			|	&& new.getMoving() == true && new.getRunTime() == 0
	 */
	public void startMove(){
		this.setHorizontalVelocity(this.getInitialHorizontalVelocity());
		this.setHorizontalAcceleration(0.9);
		this.setMoving(true);	
		this.setRunTime(0);
	}
	
	/**
	 * Ends horizontal movement in the direction Mazub is facing.
	 * 
	 * @post	The new horizontal velocity of this Mazub is equal to zero,
	 * 			the boolean moving is set to false and the runTime is set to zero.
	 * 			| new.getHorizontalVelocity() == 0 && new.getMoving == false
	 * 			|	&& new.getRunTime == 0
	 */
	public void endMove() {
		this.setHorizontalVelocity(0);
		this.setHorizontalAcceleration(0);
		this.setMoving(false);
		this.setRunTime(0);
	}
	
	/**
	 * Initializes vertical movment.
	 * 
	 * @post	If the current Y position of this Mazub is equal to zero then
	 * 			the new vertical velocity is set to the initial vertical velocity
	 * 			and the boolean movingVertical is set to true.
	 * 			| if (this.getYPosition() == 0) 
				|	then new.getVerticalVelocity() == this.getInitialVerticalVelocity()
				|	&& new.getMovingVertical() == true
		
	 */
	public void startJump(){
		if (Util.fuzzyEquals(this.getYPosition(), 0)) {
		this.setVerticalVelocity(this.getInitialVerticalVelocity());
		this.setVerticalAcceleration(-10);
		this.setMovingVertical(true);
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
		this.setDucking(true);
	}
	
	/**
	 * Ends ducking.
	 * 
	 * @post	The boolean ducking is set to false.
	 * 			| new.getDucking() == false
	 */
	public void endDuck() {
		this.setDucking(false);
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
	 * @note	In the execution of the method advanceTime the maximum horizontal velocity may be limited to 1 if
	 * 			this Mazub is ducking. After advanceTime is done running it is set back to its original value.
	 */
	public void advanceTime(double timeInterval){
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
	 */
	public Sprite getCurrentSprite() {
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
	 */
	private int getNotMovingSprite() {
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
	 */
	private int getMovingSprite() {
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
	 */
	private int runningSprite(int m) {
		int Sprite;
		if (this.getDirection() == 1) {
			if (this.getPreviousSprite() == 8 + m)
				Sprite = 8;
			else
				Sprite = this.getPreviousSprite() + 1;
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
	 *TODO
	 * @param sprite
	 * @return
	 */
	public int[] getSize(Sprite sprite) {
		int[] size = new int[2];
		size[0] = sprite.getHeight();
		size[1] = sprite.getWidth();
		return size;
	}	
}
