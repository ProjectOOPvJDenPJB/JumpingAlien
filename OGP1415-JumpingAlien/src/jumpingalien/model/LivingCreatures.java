package jumpingalien.model;

import jumpingalien.model.Position;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * Class representing each living creature.
 * Link to repository: https://github.com/ProjectOOPvJDenPJB/JumpingAlien
 * 
 * @author Joren Dhont (Ingenieurswetenschappen: Computerwetenschappen - Elektrotechniek) 
 * 	& Pieterjan Beerden (Ingenieurswetenschappen: Elektrotechniek - Computerwetenschappen)
 * @version 0.1
 */
public abstract class LivingCreatures extends GameObject {

/**
 * Initialize this new living creature with given position, horizontal velocity, vertical velocity,
 * 	horizontal acceleration, vertical acceleration, initial horizontal velocity,
 * 	maximum horizontal velocity, world, sprites and hitpoints.
 * 
 * @param 	XPosition
 * 			The position on the X-axis of the leftmost pixel for this new living creature.		
 * @param 	YPosition
 * 			The position on the Y-axis of the bottom pixel for this new living creature.
 * @param 	horizontalVelocity
 * 			The horizontal velocity for this new living creature.
 * @param 	verticalVelocity
 * 			The vertical velocity for this new living creature.
 * @param 	horizontalAcceleration
 * 			The horizontal acceleartion for this new living creature.
 * @param 	verticalAcceleration
 * 			The vertical acceleration for this new living creature.
 * @param 	initialHorizontalVelocity
 * 			The initial horizontal velocity for this new living creature.
 * @param 	maximumHorizontalVelocity
 * 			The maximum horizontal velocity for this new living creature.
 * @param 	world
 * 			The world for this new living creature.
 * @param 	sprites
 * 			The array of sprites for this new living creature.
 * @param 	hitpoints
 * 			The hitpoints for this new living creature.
 * @pre		The given spirteArray must be a valid spriteArray.
 * 			| isValidSpriteArray(sprites,this)
 * @pre		The maximum horizontal velocity must be a valid one for this living creature.
 * 			| isValidMaximumHorizontalVelocity(maximumHorizontalVelocity, initialHorizontalVelocity)
 * @pre		The initial horizontal velocity must be a valid one for this living creature.
 * 			| isValidInitialVelocity(initialHorizontalVelocity, maximumHorizontalVelocity)
 * @post	The initial horizontal velocity of this living creature is set to the given
 * 			initial horizontal velocity.
 * 			| new.getInitialHorizontalVelocity() == initialHorizontalVelocity
 * @post	The new sprite array for this living creature is set to the given sprite array.
 * 			| new.getSpriteArray() == sprites
 * @effect	The new world for this world is set to the given world.
 * 			| this.setWorld(world)
 * @effect	The new maximum horizontal velocity for this living creature
 * 			is set to the given maximum horizontal velocity.
 * 			| this.setMaximumHorizontalVelocity(maximumHorizontalVelocity)
 * @effect	The horizontal velocity for this living creature is set to
 * 			the given horizontal velocity
 * 			| this.setHorizontalVelocity(horizontalVelocity);
 * @effect	The new vertical velocity for this living creature is set to
 * 			the given vertical velocity
 * 			| this.setVerticalVelocity(verticalVelocity)
 * @effect	The new hitpoints for this living creature are set to the given hitpoints
 * 			| this.setHP(hitpoints)
 * @effect	The new horizontal acceleration for this living creature is set to
 * 			the given horizontal acceleration.
 *			| this.setHorizontalAcceleration(horizontalAcceleration)
 * @effect	The new vertical acceleration for this living creature is set to
 * 			the given vertical acceleration.
 * 			| this.setVerticalAcceleration(verticalAcceleration)
 * @effect	The new position for this living creature is set to the given position.
 * 			| this.setPosition(XPosition, YPosition)
 */
	protected LivingCreatures(int XPosition, int YPosition,double horizontalVelocity, 
			double verticalVelocity, double horizontalAcceleration,double verticalAcceleration,
			double initialHorizontalVelocity,
			double maximumHorizontalVelocity, World world, Sprite[] sprites,int hitpoints){
		this.setWorld(world);
		assert LivingCreatures.isValidSpriteArray(sprites,this);
		assert isValidMaximumHorizontalVelocity(maximumHorizontalVelocity, initialHorizontalVelocity);
		assert isValidInitialVelocity(initialHorizontalVelocity, maximumHorizontalVelocity);
		this.initialHorizontalVelocity = initialHorizontalVelocity;
		this.setMaximumHorizontalVelocity(maximumHorizontalVelocity);
		this.setHorizontalVelocity(horizontalVelocity);
		this.setVerticalVelocity(verticalVelocity);
		this.setHP(hitpoints);
		this.setHorizontalAcceleration(horizontalAcceleration);
		this.setVerticalAcceleration(verticalAcceleration);
		this.spriteArray = sprites;		
		this.setPosition(XPosition, YPosition);
	}
	
	/**
	 * Initialize this new living creature with given position, horizontal velocity, vertical velocity,
	 * 	horizontal acceleration, maximum horizontal velocity, world, sprites and hitpoints.
	 * 
	 * @param 	XPosition
	 * 			The position on the X-axis of the leftmost pixel for this new living creature.		
	 * @param 	YPosition
	 * 			The position on the Y-axis of the bottom pixel for this new living creature.
	 * @param 	horizontalVelocity
	 * 			The horizontal velocity for this new living creature.
	 * @param 	verticalVelocity
	 * 			The vertical velocity for this new living creature.
	 * @param 	horizontalAcceleration
	 * 			The horizontal acceleartion for this new living creature.
	 * @param 	maximumHorizontalVelocity
	 * 			The maximum horizontal velocity for this new living creature.
	 * @param 	world
	 * 			The world for this new living creature.
	 * @param 	sprites
	 * 			The array of sprites for this new living creature.
	 * @param 	hitpoints
	 * 			The hitpoints for this new living creature.
	 * @effect	The new LivingCreature is initialized with given position, horizontal velocity,
	 * 			vertical velocity, horizontal acceleration, maximum horizontal velocity,
	 * 			world, sprite array and hitpoints. The vertical acceleration and initial
	 * 			horizontal velocity is set to 0.
	 * 			| this(XPosition,YPosition,horizontalVelocity,verticalVelocity, horizontalAcceleration, 
	 *			|	0, 0, maximumHorizontalVelocity, world, sprites, hitpoints)
	 */
	protected LivingCreatures(int XPosition, int YPosition, double horizontalVelocity, 
			double verticalVelocity, double horizontalAcceleration,
			double maximumHorizontalVelocity,
			World world, Sprite[] sprites, int hitpoints){
		this(XPosition,YPosition,horizontalVelocity,verticalVelocity, horizontalAcceleration, 
				0, 0, maximumHorizontalVelocity, world, sprites, hitpoints);
	}
	
	/**
	 * Initialize this new living creature with given position, horizontal velocity, vertical velocity,
	 * 	maximum horizontal velocity, world, sprites and hitpoints.
	 * 
	 * @param 	XPosition
	 * 			The position on the X-axis of the leftmost pixel for this new living creature.		
	 * @param 	YPosition
	 * 			The position on the Y-axis of the bottom pixel for this new living creature.
	 * @param 	horizontalVelocity
	 * 			The horizontal velocity for this new living creature.
	 * @param 	verticalVelocity
	 * 			The vertical velocity for this new living creature.
	 * @param 	maximumHorizontalVelocity
	 * 			The maximum horizontal velocity for this new living creature.
	 * @param 	world
	 * 			The world for this new living creature.
	 * @param 	sprites
	 * 			The array of sprites for this new living creature.
	 * @param 	hitpoints
	 * 			The hitpoints for this new living creature.
	 * @effect	The new LivingCreature is initialized with given position, horizontal velocity,
	 * 			vertical velocity, maximum horizontal velocity,
	 * 			world, sprite array and hitpoints. The horizontal acceleration is set to 0.
	 * 			| this(XPosition,YPosition,horizontalVelocity,verticalVelocity, 0, maximumHorizontalVelocity,
	 *			|	world, sprites, hitpoints)
	 */
	protected LivingCreatures(int XPosition, int YPosition, double horizontalVelocity, 
			double verticalVelocity,
			double maximumHorizontalVelocity, World world, Sprite[] sprites, int hitpoints){
		this(XPosition,YPosition,horizontalVelocity,verticalVelocity, 0, maximumHorizontalVelocity,
				world, sprites, hitpoints);
	}
	
	/**
	 * Initialize this new living creature with given position, world, sprites and hitpoints.
	 * 
	 * @param 	XPosition
	 * 			The position on the X-axis of the leftmost pixel for this new living creature.		
	 * @param 	YPosition
	 * 			The position on the Y-axis of the bottom pixel for this new living creature.
	 * @param 	world
	 * 			The world for this new living creature.
	 * @param 	sprites
	 * 			The array of sprites for this new living creature.
	 * @param 	hitpoints
	 * 			The hitpoints for this new living creature.
	 * @effect	The new LivingCreature is initialized with given position, world,
	 * 			sprite array and hitpoints. The horizontal velocity and vertical velocity
	 * 			are set to 0. The maximum horizontal velocity is set to 3.
	 * 			| this(XPosition,YPosition,0,0, 3,world, sprites, hitpoints)
	 */
	protected LivingCreatures(int XPosition, int YPosition,World world, Sprite[] sprites, int hitpoints){
		this(XPosition,YPosition,0,0, 3,world, sprites, hitpoints);
	}
	
	/**
	 * Initialize this new living creature with given position, sprites and hitpoints.
	 * 
	 * @param 	XPosition
	 * 			The position on the X-axis of the leftmost pixel for this new living creature.		
	 * @param 	YPosition
	 * 			The position on the Y-axis of the bottom pixel for this new living creature.
	 * @param 	sprites
	 * 			The array of sprites for this new living creature.
	 * @param 	hitpoints
	 * 			The hitpoints for this new living creature.
	 * @effect	The new LivingCreature is initialized with given position,
	 * 			sprite array and hitpoints. The world is set to null.
	 * 			| this(XPosition,YPosition, null, sprites, hitpoints)
	 */
	protected LivingCreatures(int XPosition, int Yposition,Sprite[] sprites, int hitpoints) {
		this(XPosition,Yposition, null,sprites, hitpoints);
	}
	
	/**
	 * Initialize this new living creature with given sprites and hitpoints.
	 * 
	 * @param 	sprites
	 * 			The array of sprites for this new living creature.
	 * @param 	hitpoints
	 * 			The hitpoints for this new living creature.
	 * @effect	The new LivingCreature is initialized with given sprite array
	 * 			and hitpionts. The position is set to (0,0)
	 * 			| this(0,0,sprites, hitpoints)
	 */
	protected LivingCreatures(Sprite[] sprites, int hitpoints) {
		this(0,0,sprites, hitpoints);
	}
	
	/**
	 * Return the X position of this living creature.
	 * 	The position is the actual position of the left X pixel of
	 * 	the living creature character in the gameworld.
	 */
	public double getXPosition() {
		return getPosition().getXPosition();
	}
	
	/**
	 * Return the Y position of this living creature.
	 * 	The position is the actual position of the bottom Y pixel of
	 * 	the living creature character in the gameworld.
	 */
	public double getYPosition () {
		return getPosition().getYPosition();
	}
	
	/**
	 * Return the position of this living creature.
	 * 	The position is represented as an object of the Position value class.
	 */
	@Basic
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Variable registering the position of this living creature as an object
	 * 	of the Position value class.
	 */
	protected Position position = new Position();

	/**
	 * Set the X position of this living creature to the given X position.
	 * @param	xPosition
	 * 			The new position on the X-axis of the leftmost pixel for this living creature.
	 * @post	If the given position was a valid position in regards to the world this creature is in,
	 * 			the new position is set to the given position.
 	 *			| new.getPosition().getPosition() == [positionLeftX, positionBottomY]
	 * @post	If the given position is not a valid position, the creature is set to be out of bounds and
	 * 			is terminated.
	 * 			| new.getOutOfBounds == true && (new.isDead() || new.isDying())
	 */
	@Basic @Raw
	public void setPosition(double positionLeftX, double positionBottomY) {
		try {
			this.position =  new Position(positionLeftX,positionBottomY,getWorld());
		} catch (IllegalXPositionException | IllegalYPositionException exc) {
			this.setOutOfBounds(true);
			this.terminate();
		}
	}
	
	/**
	 * Sets the new X position of this creature to the given X position.
	 * @param 	positionLeftX
	 * 			The new X position to be set.
	 * @effect	The new position of this creature is set to the given X position and
	 * 			current Y position
	 * 			| setPosition(positionLeftX, getYPosition())
	 */
	public void setXPosition(double positionLeftX) {
		setPosition(positionLeftX, getYPosition());
	}
	
	/**
	 * Sets the new Y position of this creature to the given Y position.
	 * @param 	positionBottomY
	 * 			The new Y position to be set.
	 * @effect	The new position of this creature is set to the current X position
	 * 			and the given Y position.
	 * 			| setPosition(getXPosition(), positionBottomY
	 */
	public void setYPosition(double positionBottomY) {
		setPosition(getXPosition(), positionBottomY);
	}
	
	/**
	 * Return whether this creature is out of bounds or not.
	 */
	public boolean getOutOfBounds() {
		return this.outOfBounds;
	}
	
	/**
	 * Variable registering whether this creature is out of bounds or not.
	 */
	public boolean outOfBounds;
	
	/**
	 * Sets the outOfBounds boolean to the given flag.
	 * @param 	flag
	 * 			The flag to be set, either true or false.
	 */
	public void setOutOfBounds(boolean flag) {
		this.outOfBounds = true;
	}
	
	/**
	 *  Return the horizontal velocity of this creature.
	 */
	@Basic
	public double getHorizontalVelocity() {
		return this.horizontalVelocity;
	}
	
	/**
	 * Variable registering the horizontal velocity of this creature.
	 * 	The standard horizontal velocity is 0.
	 */
	private double horizontalVelocity = 0;
	
	/**
	 * Sets the horizontal velocity of this creature to the given horizontal velocity.
	 * 
	 * @param	horizontalVelocity
	 * 			The new horizontal velocity for this creature.
	 * @post	If the given horizontalVelocity is greater than or equal to zero and smaller or equal to
	 * 			the maximumHorizontalVelocity then the horizontalVelocity of this creature is equal to
	 * 			the given horizontalVelocity.
	 * 			| if (horizontalVelocity >= 0) 
	 * 			|	&& (horizontalVelocity <= this.getMaximumHorizontalVelocity())
	 * 			|	then new.getHorizontalVelocity() == horizontalVelocity
	 * @post	If the given horizontalVelocity is smaller than zero then the horizontalVelocity of this creature
	 * 			is equal to zero.
	 * 			| if (horizontalVelocity  < 0)
	 * 			|	then new.getHorizontalVelocity() == 0
	 * @post	If the given horizontalVelocity is greater than the maximumHorizontalVelocity then the
	 * 			horizontalVelocity of this creature is equal to the maximumHorizontalVelocity.
	 * 			| if (horizontalVelocity > this.getMaximumHorizontalVelocity())
	 * 			|	then new.getHorizontalVelocity() == this.getMaximumHorizontalVelocity()
	 */
	@Raw
	public void setHorizontalVelocity(double horizontalVelocity) {
		if (horizontalVelocity  < 0) {
			this.horizontalVelocity = 0;
		}
		else if (Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,this.getMaximumHorizontalVelocity())) {
			this.horizontalVelocity = this.getMaximumHorizontalVelocity();
		}
		else {
			this.horizontalVelocity = horizontalVelocity;
		}
	}
	
	/**
	 * Return the initial horizontal velocity of this creature.
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
	 * Variable registering the initial horizontal velocity of this creature.
	 */
	private double initialHorizontalVelocity;

	/**
	 *Set the initial horizontal acceleration of this creature.
	 */
	public void setInitialHorizontalVelocity(double velocity){
		this.initialHorizontalVelocity = velocity;
	}
	
	/**
	 * Return the initial horizontal acceleration of this creature.
	 */
	public double getInitialHorizontalAcceleration(){
		return this.initialHorizontalAcceleration;
	}
	
	/**
	 * Variable registering the initial Horizontal acceleration of this creature.
	 */
	private double initialHorizontalAcceleration;
	
	public void setInitialHorizontalAcceleration(double acceleration){
		this.initialHorizontalAcceleration = acceleration;
	}	
	/**
	 *  Return the vertical velocity of this creature.
	 */
	public double getVerticalVelocity(){
		return this.verticalVelocity;
	}
	
	/**
	 * Checks whether the current vertical velocity is a valid vertical velocity.
	 * @param 	verticalVelocity
	 * @return	The verticalVelocity must be a finite number.
	 * 			| result == Double.isFinite(verticalVelocity)
	 */
	public boolean isValidVerticalVelocity(double verticalVelocity) {
		return (Double.isFinite(verticalVelocity));
	}
	
	/**
	 * Variable registering the vertical velocity of this living creature.
	 * 	The standard vertical velocity is 0.
	 */
	private double verticalVelocity = 0;
	
	/**
	 * Sets the vertical velocity of this living creature to the given vertical velocity.
	 * 
	 * @param	verticalVelocity
	 * 			The new vertical velocity for this living creature.
	 * @pre		The given verticalVelocity is a valid velocity for this living creature.
	 * 			| isValidVerticalVelocity(verticalVelocity)
	 * @post	The new vertical velocity is equal to the
	 * 			given vertical velocity.
	 * 			| 	then new.verticalVelocity = verticalVelocity
	 */
	@Raw
	public void setVerticalVelocity(double verticalVelocity){
		assert isValidVerticalVelocity(verticalVelocity);
		this.verticalVelocity = verticalVelocity;
	}
	
	/**
	 *  Return the horizontal acceleration of this living creature.
	 */
	public double getHorizontalAcceleration(){
		return this.horizontalAcceleration;
	}
	
	/**
	 * Checks whether the given horizontalAcceleration is a valid one for this creature.
	 * @param 	horizontalAcceleration
	 * 			The horizontal acceleration to check.
	 * @return	True if and only if the given acceleration is positive or zero.
	 * 			| result == (horizontalAcceleration >= 0)
	 */
	public static boolean isValidHorizontalAcceleration(double horizontalAcceleration) {
		return horizontalAcceleration >= 0;
	}
	
	/**
	 * Variable registering the horizontal acceleration of this living creature.
	 */
	private double horizontalAcceleration;
	
	/**
	 * Sets the horizontal acceleration of this living creature to the given horizontal acceleration.
	 * 
	 * @param	horizontalVelocity
	 * 			The new horizontal acceleration for this living creature.
	 * @pre		The given horizontalAcceleration is a valid acceleration for this living creature.
	 * 			| isValidHorizontalAcceleration(horizontalAcceleration)
	 * @post	The new horizontal acceleration is equal to the
	 * 			given horizontal acceleration.
	 * 			| 	then new.horizontalAcceleration = horizontalAcceleration
	 */
	@Raw
	public void setHorizontalAcceleration(double horizontalAcceleration){
		assert isValidHorizontalAcceleration(horizontalAcceleration);
		this.horizontalAcceleration = horizontalAcceleration;
	}
	
	/**
	 *  Return the vertical acceleration of this living creature.
	 */
	public double getVerticalAcceleration(){
		return this.verticalAcceleration;
	}
	
	/**
	 * Checks whether the given verticalAcceleartion is a valid one for a creature.
	 * @param 	verticalAcceleration
	 * 			The vertical acceleration to check.
	 * @return	If the verticalAcceleration is 0 or -10 it is valid.
	 * 			| result == 
	 * 			|	(verticalAcceleration == 0) || (verticalAcceleration == -10)
	 */
	public static boolean isValidVerticalAcceleration(double verticalAcceleration) {
		return (verticalAcceleration) == 0 || (verticalAcceleration == -10);
	}
	
	/**
	 * Variable registering the vertical acceleration of this living creature.
	 * 	The standard vertical acceleration is 0.
	 */
	private double verticalAcceleration = 0;
	
	/**
	 * Sets the vertical acceleration of this living creature to the given vertical acceleration.
	 * 
	 * @param	horizontalVelocity
	 * 			The new vertical acceleration for this living creature.
	 * @pre		The given verticalAcceleration is a valid acceleration for this living creature.
	 * @post	The new vertical acceleration is equal to the
	 * 			given vertical acceleration.
	 * 			| 	then new.verticalAcceleration = verticalAcceleration
	 */
	@Raw
	public void setVerticalAcceleration(double verticalAcceleration){
		assert isValidVerticalAcceleration(verticalAcceleration);
		this.verticalAcceleration = verticalAcceleration;
	}
	
	/**
	 * Return world in which the living creature is located.
	 */
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Variable registering the world of this creature.
	 */
	protected World world;
	
	
	/**
	 * Set the world for this living creature to the given world.
	 * @param	world
	 * 			The new world for this living creature.
	 * @post	The new world for the living creature is equal to the
	 * 			given world.
	 * 			| new.getWorld() == world
	 */
	@Raw
	public void setWorld(World world){
		assert canHaveAsWorld(world);
		this.world = world;
	}

	/**
	 * Checks whether this creature is in a world.
	 * @return 	The world must not be of null type.
	 * 			| result == (world != null)
	 */
	public boolean isInWorld(){
		return (world != null);
	}
	
	/**
	 * Checks whether the given world is the world in which the creature
	 * 	is located.
	 * @param 	world
	 * 			The world to check.
	 * @return	True if the given world is the world of this creature, false otherwise.
	 * 			| result == (getWorld() == world)
	 */
	public boolean hasAsWorld(World world) {
		try {
			return this.getWorld() == world;
		}
		catch (NullPointerException exc) {
        	// This creature doesn't inhabit a world.
            assert (world == null);
            return false;
        }
	}
	
	/**
	 * Checks whether this creature can have the given world as world.
	 * @param 	world
	 * 			The world to check
	 * @return	The world must not be null and the world must be able to have
	 * 			this creature as an object.
	 * 			| result ==
	 * 			|	 (world != null) && (world.canHaveAsObject(this))
	 */
	public boolean canHaveAsWorld(World world) {
		return (world != null) && (world.canHaveAsObject(this));
	}

	/**
	 * Return a clone of the spriteArray of this living creature.
	 */
	public Sprite[] getSpriteArray() {
		return this.spriteArray.clone();
	}
	
	/**
	 * Checks whether the given spriteArray is a valid spriteArray.
	 * 
	 * @param 	spriteArray
	 * 			The spriteArray to check against.
	 * @effect	If the object is of type Mazub, then the checker of the Mazub class is invoked.
	 * 			| if (object instanceof Mazub)
	 * 			|	then Mazub.isValidSpriteArray(spriteArray)
	 * @return	True if the spriteArray is a valid spriteArray for a creature apart from Mazub.
	 * 			These spriteArrays must be of length 2.
	 * 			| result == (spriteArray.length == 2)
	 */
	@Raw
	public static boolean isValidSpriteArray(Sprite[] spriteArray,Object object) {
		if (object instanceof Mazub)
			return Mazub.isValidSpriteArray(spriteArray);
		else {			
			int length = spriteArray.length;
			return (length == 2);
		}
	}
	
	/**
	 * Variable registering the spriteArray of this living creature.
	 */
	private final Sprite[] spriteArray;

	public Sprite getCurrentSprite() {
		if (getDirection() == Direction.RIGHT)
			return getSpriteArray()[1];
		else
			return getSpriteArray()[0];
	}
	
	/**
	 * Return the direction of this creature.
	 */
	public Direction getDirection() {
		return this.direction;
	}
	
	/**
	 * Variable registering the direction of this living creature.
	 */
	private Direction direction = Direction.RIGHT;

	/**
	 * Sets the direction of this living creature to the given direction.
	 * 
	 * @param 	direction
	 * 			The new direction of this living creature.
	 * @post	If the given direction is LEFT then the new direction of this living creature is left.
	 * 			| if (direction == LEFT)
	 * 			| 	then new.getDirection() == LEFT
	 * @post	If the given direction is RIGHT then the new direction of this living creature is right.
	 * 			| if (direction == RIGHT)
	 * 			|	then new.getDirection() == RIGHT
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/**
	 * Sets the direction of this living creature to the given integer direction.
	 * 	A 1 resembles right and a -1 resembles left.
	 * @param 	direction
	 * 			The new direction of this living creature in form of an integer.
	 * @post	If the given direction integer was 1, then the new direction of this creature is right.
	 * 			| if (direction == 1)
	 * 			|	then new.getDirection() == RIGHT
	 * @post	If the given direction integer was -1, then the new direction of this creature is left.
	 * 			| if (direction == -1)
	 * 			|	then new.getDirection() == LEFT
	 * @post	If the given direction integer was not 1 or -1, nothing happens.
	 * 			| if (direction != 1) && (direction != -1)
	 * 			|	then new.getDirection() == old.getDirection()
	 */
	public void setDirection(int direction){
		if (direction == 1){
			this.direction = Direction.RIGHT;
		}
		if (direction == -1){
			this.direction = Direction.LEFT;
		}
	}
	
	/**
	 * Return the boolean indicating whether this living creature is moving
	 * 	horizontally or not.
	 */
	@Basic
	public boolean getMoving(){
		return this.moving;
	}
	
	/**
	 * Variable registering whether this living creature is moving horizontally or not.
	 */
	private boolean moving = false;
	
	/**
	 * Sets the boolean indicating whether this living creature is moving horizontally.
	 * @param 	flag
	 * 		 	The new boolean that indicates whether or not living creature is moving horizontally.
	 * @post	The new moving state of this living creature is equal to the 
	 * 			given flag.
	 * 			| new.getMoving == flag
	 */
	@Basic
	public void setMoving(boolean flag) {
		this.moving = flag;
	}
	
	/**
	 * Return the hitpoints of this living creature.
	 */
	public int getHP() {
		return this.hitpoints;
	}
	
	/**
	 * Variable registering the hitpoints of this living creature.
	 */
	private int hitpoints;
	
	/**
	 * Sets the hitpoints of this living creature to the given hitpoints.
	 * @param 	HP
	 * 			The new hitpoints to be set for this living creature.
	 * @post	If the hitpoints are lower than the minimum hitpoints for this creature
	 * 			then the creature is terminated and the hitpoints are set to the minimum hitpoints.
	 * 			| if (HP <= getMinHp())
	 * 			|	then ((isDead()) || (isDying())) && new.getHP() == getMinHP()
	 * @post	If the hitpoints are higher than the maximum hitpoints of this creature, then the hitpoints
	 * 			are set to the maximum hitpoints.
	 * 			| if (HP > getMaxHP())
	 * 			|	then new.getHP() == getMaxHP()
	 * @post	If the hitpoints are neither lower than the minimum hitpoints or higher than the maximum hitpoints
	 * 			then the hitpoints are set to the given hitpoints.
	 * 			| if (HP <= getMaxHP()) && (HP > getMinHP())
	 * 			|	then new.getHP() == HP
	 */
	@Raw
	public void setHP(int HP) {
		if (HP <= getMinHP()) {
			hitpoints = getMinHP();
			this.terminate();
		}
		else if (HP > getMaxHP())
			hitpoints = getMaxHP();
		else
			hitpoints = HP;
	}
	
	/**
	 * Terminates this living creature.
	 */
	public abstract void terminate();

	/**
	 * Return the maximum hitpoints this creature can have as an integer.
	 */
	public abstract int getMaxHP();
	
	/**
	 * Return the minimum hitpoints this creature can have as an integer.
	 */
	public abstract int getMinHP();
	
	/**
	 * Variable registering the minimum hitpoints of this living creature.
	 */
	public static final int MIN_HP = 0;
	
	/**
	 * Adds the given hit points to the current hit points of this living creature
	 * @param HP
	 * 		  The given hit points to add
	 * @post  The hit points for this living creature are added with the given hit points
	 * 		  |new.setHP(getHP() + HP);
	 * @post  If the given hit point are smaller then zero and the living creature
	 * 		  is a slime. Then one hit point is subtracted from every other slime in the
	 * 		  same school as the living creature.
	 */
	public void addHP(int HP) {
		this.setHP(getHP() + HP);
	}
	
	/**
	 * @return the time since this living creature was hit by an enemy
	 */
	public double getHitTimer(){
		return this.hitTimer;
	}
	
	/**
	 * The time since the living creature was hit by an enemy
	 * 	The initial value is set so the creature can immediatly interact
	 * 	with other creatures.
	 */
	private double hitTimer = 0.6;
	
	/**
	 * sets the time since last hit by an enemy to the given time
	 * @param time
	 * 		  The given time
	 * @post  Sets the hit timer to the given time
	 * 		  |new.getHitTimer() = time;
	 */
	public void setHitTimer(double time){
		this.hitTimer = time;
	}
	
	/**
	 * @return the time since the last terrain damage
	 */
	protected double getTerrainTimer() {
		return this.terrainTimer;
	}
	
	/**
	 * The time since the last terrain damage
	 */
	protected double terrainTimer;
	
	/**
	 * Sets the time since the last terrain damage to the given time
	 * @param time
	 * 		  The given time
	 * @post  Sets the terrain timer to the given time
	 * 		  |new.getTerrainTimer() = time;
	 */
	protected void setTerrainTimer(double time) {
		assert isValidTimerValue(time);
		terrainTimer = time;
	}
	
	/**
	 * Checks whether the given time is a valid time
	 * @param time
	 * 		  The time to check
	 * @return	True if the time is valid otherwise returns false
	 */
	public static boolean isValidTimerValue(double time) {
		return Util.fuzzyGreaterThanOrEqualTo(time, 0);
	}
	
	/**
	 * applies magma damage to the living creature
	 * @param timeInterval
	 * 		  The given time interval 
	 * @Post  Fifty hit point are subtracted from the
	 * 		  current hit point of the living creature if it hasn't received any terrain damage
	 * 		  in the last 0.2 seconds.
	 * 		  |if (Util.fuzzyGreaterThanOrEqualTo(getTerrainTimer(), 0.2)))
	 * 		  |new.getHP() = old.getHP() - 50
	 * 		  |new.getTerrainTimer = 0;
	 * @Post  The living creature has received any terrain damage the last 0.2 seconds
	 * 		  so no damage is applied and the terrainTimer goes up
	 * 	      |if (getTerrainTimer() < 0.2)
	 * 		  |this.setTerrainTimer(this.getTerrainTimer() + timeInterval);
	 */
	private void applyMagmaDamage(double timeInterval) {
		if (Util.fuzzyGreaterThanOrEqualTo(getTerrainTimer(), 0.2)){
				addHP(-50);
				this.setTerrainTimer(0);
		}else{
			this.setTerrainTimer(this.getTerrainTimer() + timeInterval);
		}
	}

	/**
	 * applies water damage to the living creature
	 * @param timeInterval
	 * 		  The given time interval 
	 * @Post  Two hit point are subtracted from the current hit point 
	 * 		  of the living creature if it hasn't received any terrain damage
	 * 		  in the last 0.2 seconds.
	 * 		  |if (Util.fuzzyGreaterThanOrEqualTo(getTerrainTimer(), 0.2)))
	 * 		  |new.getHP() = old.getHP() - 2
	 * 		  |new.getTerrainTimer() = 0
	 * @Post  The living creature has received any terrain damage the last 0.2 seconds
	 * 		  so no damage is applied and the terrainTimer goes up
	 * 	      |if (getTerrainTimer() < 0.2)
	 * 		  |this.setTerrainTimer(this.getTerrainTimer() + timeInterval);
	 */
	private void applyWaterDamage(double timeInterval) {
		if ((Util.fuzzyGreaterThanOrEqualTo(getTerrainTimer(), 0.2))){
			addHP(-2);
			this.setTerrainTimer(0);
		}else{
			this.setTerrainTimer(this.getTerrainTimer() + timeInterval);
		}
	}
	
	/**
	 * applies air damage to the living creature
	 * @param timeInterval
	 * 		  The given time interval 
	 * @Post  Six hit point are subtracted from the
	 * 		  current hit point of the living creature if it hasn't received any terrain damage
	 * 		  in the last 0.2 seconds.
	 * 		  |if (Util.fuzzyGreaterThanOrEqualTo(getTerrainTimer(), 0.2)))
	 * 		  |new.getHP() = old.getHP() - 6
	 * 		  |new.getTerrainTimer = 0;
	 * @Post  The living creature has received any terrain damage the last 0.2 seconds
	 * 		  so no damage is applied and the terrainTimer goes up
	 * 	      |if (getTerrainTimer() < 0.2)
	 * 		  |this.setTerrainTimer(this.getTerrainTimer() + timeInterval);
	 */
	private void applyAirDamage(double timeInterval){
		if ((Util.fuzzyGreaterThanOrEqualTo(getTerrainTimer(), 0.2))){
			addHP(-6);
			this.setTerrainTimer(0);
		}else{
			this.setTerrainTimer(this.getTerrainTimer() + timeInterval);
		}
	}

	/**
	 * Advances the time and alters all time related aspects of this living creature
	 * 	 according to the given time interval.
	 */
	public abstract void advanceTime(double dt);
	
	/**
	 * Return the runtimer of this living creature.
	 */
	protected double getRunTime() {
		return this.runTime;
	}
	
	/**
	 * Variable registering the time since living creature stopped running when living creature is not moving horizontally.
	 * When living creature moves horizontally, the variable registers the time since the last spritechange.
	 */
	protected double runTime = 1;

	/**
	 * Sets the runTimer of this living creature to the given time.
	 * 
	 * @param 	runTimer
	 * 			The new time for the runTimer for this living creature.
	 * @post	The new runTimer for this living creature is equal to the given
	 * 			runTimer.
	 * 			| new.getRunTime == runTime
	 */
	protected void setRunTime(double runTime) {
		assert isValidTimerValue(runTime);
		this.runTime = runTime;
	}
	
	/**
	 * Checks whether the given timeInterval is a valid timeInterval for this living creature.
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
	 * In-class enumeration of the different States of a living creature.
	 */
	protected static enum State {
		ALIVE,DYING,DEAD;
	}
	
	/**
	 * Return the current state of this living creature
	 */
	protected State getState() {
		return this.state;
	}

	/**
	 * Variable registering the current state of this living creature.
	 * 	The default value is ALIVE
	 */
	protected State state = State.ALIVE;
	
	/**
	 * Sets the current state for the living creature the given state
	 * @param state
	 * 		  The given state
	 * @post  Sets the current state for the living creature the given state
	 * 		  | new.state = state;
	 */
	protected void setState(State state) {
		assert (state != null);
		this.state = state;
	}
	
	/**
	 * Check whether the living creature is dead or not.
	 * @return	True of the state of this creature is DEAD.
	 * 			| result ==
	 * 			|	(this.getState() == DEAD)
 	 */
	public boolean isDead() {
		return (this.getState() == State.DEAD);
	}
	
	/**
	 * Check whether the living creatures is dying or not.
	 * @return	True of the state of this creature is DYING.
	 * 			| result ==é
	 * 			|	(this.getState() == DYING)
	 */
	public boolean isDying() {
		return (this.getState() == State.DYING);
	}
	
	/**
	 * Check whether the living creature is alive or not.
	 * @return	True of the state of this creature is ALIVE.
	 * 			| result ==
	 * 			|	(this.getState() == ALIVE)
	 */
	public boolean isAlive() {
		return (this.getState() == State.ALIVE);
	}
	
	/**
	 * @param 	timeInterval 
	 * 			The time interval in which the position of this creature has changed.
	 * @post	The new X position of this Mazub is equal to the current X position added to the horizontal distance
	 * 			travelled calculated with a formula using the given time interval. 
	 * 			new.getXPosition = this.getXPosition() + distanceCalculated
	 */
	public void changeHorizontalPosition(double timeInterval,double horizontalAcceleration){
		this.setHorizontalAcceleration(horizontalAcceleration);
		if (Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,this.getMaximumHorizontalVelocity())){
			this.setHorizontalAcceleration(0);
		}
		double newPositionX = this.getXPosition() + this.getDirection().getInt() * (100 * this.getHorizontalVelocity()*timeInterval 
				+ 50 * this.getHorizontalAcceleration()*timeInterval*timeInterval);
		double oldPositionX = this.getXPosition();
		setXPosition(newPositionX);
		if (Interaction.collidesWithTerrainHorizontal(this, 1)){
			setXPosition(oldPositionX);
		}
		if (Interaction.interactWithMovementBlockingCreature(this,this.getWorld())) {
			Interaction.interactWithOtherCreatures(this);
			setXPosition(oldPositionX);
		}
		if (this instanceof Slime) {
			if (noTerrainUnderSidesOfObject()) {
				setXPosition(oldPositionX);
			}
		}
		if (!this.collidesWithTerrainThroughBottomBorder()){
			this.startFall(-10);
		}
	}
	
	
	 /**
	  * @param 	timeInterval
	  * 		The time interval in which the position of this creature has changed.
	  * @post	The new Y position of this Mazub is equal to the current Y position added to the vertical distance
	  * 		travelled calculated with a formula using the given time interval. 
	  * 		new.getYPosition = this.getYPosition() + distanceCalculated
	  */
	public void changeVerticalPosition(double timeInterval) {
		if ((this instanceof Shark) && (this.getMovingVertical())
				&& (this.getVerticalVelocity() < 0)) {
			if (Interaction.collidesWithTerrainTopSide(this, 2)
					&& Interaction.collidesWithTerrainBottomSide(this, 2)) {
				this.setVerticalAcceleration(0);
				this.setVerticalVelocity(0);
			}
		}
		double oldPositionY = this.getYPosition();
		double newPositionY = this.getYPosition() 
				+ 100 * this.getVerticalVelocity() * timeInterval
				+ 50 * this.getVerticalAcceleration() * timeInterval * timeInterval;
		setYPosition(newPositionY);
		boolean creatureBlock = Interaction.interactWithMovementBlockingCreature(this,this.getWorld());
		if (Interaction.collidesWithTerrainVertical(this, 1)
				|| creatureBlock){
			if (creatureBlock) {
				Interaction.interactWithOtherCreatures(this);
			}
			setYPosition(oldPositionY);
			this.endJump();
			if (this.collidesWithTerrainThroughBottomBorder()
					|| creatureBlock){
				this.setMovingVertical(false);
				this.setVerticalAcceleration(0);
				this.setVerticalVelocity(0);
			}
		}
	}
	
	/**
	 * Calculates the timeInterval needed for this living creature to travel just 1 pixel.
	 * @param 	dt
	 * 			The original timeInterval.
	 * @return	The minimum of the time interval to travel 1 pixel horizontal and the time interval to
	 * 				travel 1 pixel vertically.
	 * 			| result == 
	 * 			| 	min(
	 * 			|		(0.01 / (this.getHorizontalVelocity() + this.getHorizontalAcceleration() * dt)),
	 * 			|		abs(0.01 / (this.getVerticalVelocity() + this.getVerticalAcceleration() * dt))
	 * 			|		)
	 */
	public double getSmallestDT(double dt) {
		double horizontalDT = 
				0.01 / (this.getHorizontalVelocity() + this.getHorizontalAcceleration() * dt);
		double verticalDT = 
				Math.abs(0.01 / (this.getVerticalVelocity() + this.getVerticalAcceleration() * dt));
		return Math.min(Math.min(horizontalDT, verticalDT), dt);
		
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
	public void setMovingVertical(boolean flag) {
		this.movingVertical = flag;
	}
	
	/**
	 * start a move to the given direction as the current movement of this LivingCreature
	 * 
	 * @post  The new direction is opposite to the current direction, 
	 * 		  the horizontal velocity is reset to zero and the horizontal acceleration is set to 1.5.
	 * 		 | new.getDirection() == direction
	 * 		 | new.getHorizontalVelocity == this.getInitialHorizontalVelocity()
	 * 		 | new.getHorizontalAcceleration == this.getInitialHorizontalAcceleration()
	 */
	public void startMove(double velocity,double horizontalAcceleration,Direction direction){
		this.setHorizontalAcceleration(horizontalAcceleration);
		this.setHorizontalVelocity(velocity);
		this.setMoving(true);
		this.setDirection(direction);
		this.setRunTime(0);
	}
	
	/**
	 * start a move to the given direction as the current movement of this LivingCreature
	 * 
	 * @post  The new direction is opposite to the current direction, 
	 * 		  the horizontal velocity is reset to zero and the horizontal acceleration is set to 1.5.
	 * 		 | new.getDirection() == direction
	 * 		 | new.getHorizontalVelocity == this.getInitialHorizontalVelocity()
	 * 		 | new.getHorizontalAcceleration == this.getInitialHorizontalAcceleration()
	 */
	public void startMove(Direction direction){
		this.setHorizontalAcceleration(this.getInitialHorizontalAcceleration());
		this.setHorizontalVelocity(this.getInitialHorizontalVelocity());
		this.setMoving(true);
		this.setDirection(direction);
		this.setRunTime(0);
	}
	
	/**
	 * Initializes vertical movment.
	 * 
	 * @param	velocity
	 * 			The initial vertical velocity to start the jump with.
	 * @param	acceleration
	 * 			The vertical acceleration of this jump.
	 * @post	If this creature is not moving vertical then
	 * 			the new vertical velocity is set to the given velocity, 
	 * 			the new vertical acceleration is set to the given acceleration
	 * 			and the boolean movingVertical is set to true.
	 * 			| if (! getMovingVertical()) 
	 *			|	then new.getVerticalVelocity() == velocity
	 *			|	&& new.getVerticalAcceleration() == acceleration
	 *			|	&& new.getMovingVertical() == true
	 */
	public void startJump(double velocity,double acceleration){
		if (! getMovingVertical()){
			this.setVerticalVelocity(velocity);
			this.setVerticalAcceleration(acceleration);
			this.setMovingVertical(true);
		}
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
	 * Ends horizontal movement in the direction Mazub is facing.
	 * @post	The new horizontal velocity of this Mazub is equal to zero,
	 * 			the new horizontal acceleration is set to 0,
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
	 * Initializes the falling motion of this living creature.
	 * @param 	acceleration
	 * 			The vertical acceleration with which  this living creature falls.
	 * @post	The new vertical acceleration is set to the given acceleration and
	 * 			the boolean movingVertical is set to true.
	 * 			| new.getVerticalAcceleration() == acceleration
	 *			|	&& new.getMovingVertical() == true
	 */
	public void startFall(double acceleration){
		this.setVerticalAcceleration(acceleration);
		this.setMovingVertical(true);
	}
	
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
	protected double maximumHorizontalVelocity;
	
	
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
	@Raw
	protected void setMaximumHorizontalVelocity(double maximumHorizontalVelocity) {
		assert isValidMaximumHorizontalVelocity(maximumHorizontalVelocity, this.getInitialHorizontalVelocity());
		this.maximumHorizontalVelocity = maximumHorizontalVelocity;
	}
	
	/**
	 * Returns the width of this creature.
	 */
	public int getWidth() throws IllegalSizeException {
		int size =  getCurrentSprite().getWidth();
		if (! isValidSize(size))
			throw new IllegalSizeException(size);
		return size;
	}
	
	/**
	 * Returns the height of this creature.
	 */
	public int getHeight() throws IllegalSizeException {
		int size =  getCurrentSprite().getHeight();
		if (! isValidSize(size))
			throw new IllegalSizeException(size);
		return size;
	}
	
	
	/**
	 * Returns the size of a sprite of this creature.
	 * @param 	sprite
	 * 			The sprite of which the size must be determined.
	 * @return	Returns the size of the given sprite in an array of type int.
	 * 			this array consists of two elements, the height and width respectively.
	 * @throws	IllegalSizeException
	 * 			The size of this sprite is not a valid size for a sprite of this Mazub.
	 * 			| (! isValidSize([sprite.getHeight(),sprite.getWidth()]))
	 */
	public int[] getSize() throws IllegalSizeException {
		int[] size = new int[2];
		try {
			size[0] = getWidth();
			size[1] = getHeight();
		} catch (IllegalSizeException exc) {
			throw exc;
		}
		return size;
	}
		
	/**
	 * Checks whether the given size is a valid size for this creature.
	 * 
	 * @param 	size
	 * 			The size to be checked.
	 * @return	True if and only if the size is greater or equal to 0.
	 * 			| result ==
	 * 			|	(size > 0)
	 */
	public static boolean isValidSize(int size) {
		return Util.fuzzyGreaterThanOrEqualTo(size, 0);
	}
	
	/**
	 * Checks whether there is a part of the bottom border of this creature that is on top of a solid tile.
	 * @return	If the bottom left pixel or the bottom right pixel of this creature is on top of a solid tile, true is returned.
	 * 			If the creature is of type Shark, whether the shark is submerged or not is returned.
	 * 			| if (this instanceof Shark)
	 * 			|	then result == this.getSubmerged()
	 * 			| if (this.getWorld().getTileType(getXPosition(),getYPosition()) == 1)
	 * 			|	|| (this.getWorld().getTileType(getXPosition()+getSize()[0],getYPosition()) == 1)
	 * 			|	then result == true
	 */
	public boolean collidesWithTerrainThroughBottomBorder(){
		World world = this.getWorld();
		if (this instanceof Shark) {
			return this.getSubmerged();
		}
		try{
			if ((world.getTileType((int)this.getXPosition()+1, (int)this.getYPosition()) != 1) 
					&& ((world.getTileType((int)(this.getXPosition() + this.getSize()[0]), (int)(this.getYPosition())) != 1))){
				return false;
			}
		}catch (NullPointerException exc){
		}
		return true;
	}
	
	
	/**
	 * Checks whether there is a part of the bottom border of this creature that is not on top of solid terrain.
	 * @return	If the bottom left pixel or the bottom right pixel of this creature is not on top of a solid tile, true is returned.
	 * 			| if (this.getWorld().getTileType(getXPosition(),getYPosition()-1) != 1)
	 * 			|	|| (this.getWorld().getTileType(getXPosition()+getSize()[0],getYPosition()-1) != 1)
	 * 			|	then result == true
	 */
	public boolean noTerrainUnderSidesOfObject(){
		World world = this.getWorld();
		try{
			if ((world.getTileType((int)this.getXPosition(), (int)this.getYPosition()-1) != 1) 
				|| ((world.getTileType((int)(this.getXPosition() + this.getSize()[0]), (int)(this.getYPosition())-1) != 1))){
			return true;
			}
		}catch(NullPointerException exc){
		}
			return false;
	}
	
	/**
	 * Checks whether this living creature is submerged in water.
	 * @return	If the bottom and top border of this creature collide with water, then true is returned.
	 * 			| result ==
	 * 			|	(Interaction.collidesWithTerrainTopSide(this, 2)
				|	&& Interaction.collidesWithTerrainBottomSide(this, 2)
	 */
	private boolean getSubmerged() {
		return (Interaction.collidesWithTerrainTopSide(this, 2)
				&& Interaction.collidesWithTerrainBottomSide(this, 2));
	}
	
	/**
	 * @return the current death timer of this living creature
	 */
	protected double getDeathTimer() {
		return this.deathTimer;
	}
	protected double deathTimer;
	
	/**
	 * Sets the death timer to the given time.
	 * @param time
	 * 		  The given time for the death timer
	 * @post  The death timer is set to the given time
	 * 		  | new.getDeathTimer() = time;
	 */
	protected void setDeathTimer(double time) {
		assert isValidTimerValue(time);
		this.deathTimer = time;
	}
	
	/**
	 * Applies terrain damage to the living creature if needed
	 * @param timeInterval
	 * 		  The current timeInterval since the last change of position
	 */
	public void applyTerrainDmg(double timeInterval){
		if (Interaction.collidesWithTerrain(this, 3)){
			this.applyMagmaDamage(timeInterval);
		}else if (Interaction.collidesWithTerrain(this, 2)){
			if (!(this instanceof Shark)){
			this.applyWaterDamage(timeInterval);
			}else{
				this.setTerrainTimer(0);
			}
		}else{
			if (!(this instanceof Shark)){
				this.setTerrainTimer(this.getTerrainTimer() + timeInterval);
			}else{
				this.applyAirDamage(timeInterval);
			}
		}
	}
	
	public Program getProgram(){
		return this.program;
	}
	
	private Program program;
	
	public void setProgram(Program program){
		this.program = program;
	}
	
}

