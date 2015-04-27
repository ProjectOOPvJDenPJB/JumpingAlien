package jumpingalien.model;

import jumpingalien.model.Position;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

public abstract class LivingCreatures {

	protected LivingCreatures(int XPosition, int YPosition,double horizontalVelocity, 
			double verticalVelocity, double horizontalAcceleration,double verticalAcceleration,
			double initialHorizontalVelocity,
			double maximumHorizontalVelocity, World world, Sprite[] sprites,int hitpoints){
		this.setWorld(world);
		LivingCreatures.isValidSpriteArray(sprites,this);
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
		this.setHitTimer(0.6);
		this.setState(State.ALIVE);
		this.setPosition(XPosition, YPosition);
	}
	
	protected LivingCreatures(int XPosition, int YPosition, double horizontalVelocity, 
			double verticalVelocity, double horizontalAcceleration,
			double maximumHorizontalVelocity,
			World world, Sprite[] sprites, int hitpoints){
		this(XPosition,YPosition,horizontalVelocity,verticalVelocity, horizontalAcceleration, 
				0, 0, maximumHorizontalVelocity, world, sprites, hitpoints);
	}
	
	protected LivingCreatures(int XPosition, int YPosition, double horizontalVelocity, 
			double verticalVelocity,
			double maximumHorizontalVelocity, World world, Sprite[] sprites, int hitpoints){
		this(XPosition,YPosition,horizontalVelocity,verticalVelocity, 0, maximumHorizontalVelocity,
				world, sprites, hitpoints);
	}
	
	protected LivingCreatures(int XPosition, int YPosition,World world, Sprite[] sprites, int hitpoints){
		this(XPosition,YPosition,0,0, 3,world, sprites, hitpoints);
	}
	
	protected LivingCreatures(int XPosition, int Yposition,Sprite[] sprites, int hitpoints) {
		this(XPosition,Yposition, null,sprites, hitpoints);
	}
	
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
	private final double initialHorizontalVelocity;

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
	
	//TODO
	public abstract void terminate();

	public abstract int getMaxHP();
	
	public abstract int getMinHP();
	
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
	 * the time since the living creature was hit by an enemy
	 */
	private double hitTimer;
	
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
	
	public static enum State {
		ALIVE,DYING,DEAD;
	}
	
	/**
	 * @return the current state of this living creature
	 */
	protected State getState() {
		return this.state;
	}
	
	private State state;
	
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
	 * @return whether the living creature is dead or not
 	 */
	public boolean isDead() {
		return (this.getState() == State.DEAD);
	}
	
	/**
	 * @return whether the living creatures is dying or not
	 */
	public boolean isDying() {
		return (this.getState() == State.DYING);
	}
	
	/**
	 * @return whether the living creature is alive or not
	 */
	public boolean isAlive() {
		return (this.getState() == State.ALIVE);
	}
	
	/**
	 * @param 	timeInterval 
	 * 			The time interval in which the position of this mazub has changed.
	 * @post	The new X position of this Mazub is equal to the current X position added to the horizontal distance
	 * 			travelled calculated with a formula using the given time interval. 
	 * 			new.getXPosition = this.getXPosition() + distanceCalculated
	 */
	public void changeHorizontalPosition(double timeInterval){
		this.setHorizontalAcceleration(0.9);
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
	  * 		The time interval in which the position of this mazub has changed.
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
	public void setMovingVertical(boolean flag) throws IllegalStateException {
//		if (this.getMovingVertical() == flag)
//			throw new IllegalStateException();
		this.movingVertical = flag;
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
	public void startJump(double velocity,double acceleration){
		if (! getMovingVertical()){
			this.setVerticalVelocity(velocity);
			this.setVerticalAcceleration(acceleration);
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
	protected void setMaximumHorizontalVelocity(double maximumHorizontalVelocity) {
		assert isValidMaximumHorizontalVelocity(maximumHorizontalVelocity, this.getInitialHorizontalVelocity());
		this.maximumHorizontalVelocity = maximumHorizontalVelocity;
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
	public int[] getSize() throws IllegalSizeException {
		int[] size = new int[2];
		size[0] = getCurrentSprite().getWidth();
		size[1] = getCurrentSprite().getHeight();
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
	
	public boolean collidesWithTerrainThroughBottomBorder(){
		World world = this.getWorld();
		if (this instanceof Shark) {
			return this.getSubmerged();
		}
		if ((world.getTileType((int)this.getXPosition()+1, (int)this.getYPosition()) != 1) 
				&& ((world.getTileType((int)(this.getXPosition() + this.getSize()[0]), (int)(this.getYPosition())) != 1))){
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean noTerrainUnderSidesOfObject(){
		World world = this.getWorld();
		if ((world.getTileType((int)this.getXPosition(), (int)this.getYPosition()-1) != 1) 
				|| ((world.getTileType((int)(this.getXPosition() + this.getSize()[0]), (int)(this.getYPosition())-1) != 1))){
			return true;
		}
		else {
			return false;
		}
	}
	
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
	
}

