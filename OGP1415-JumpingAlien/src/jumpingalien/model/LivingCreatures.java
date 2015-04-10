package jumpingalien.model;

import jumpingalien.model.Position;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public abstract class LivingCreatures {

	protected LivingCreatures(int XPosition, int YPosition,double horizontalVelocity, 
			double verticalVelocity, double horizontalAcceleration,double verticalAcceleration,
			World world, Sprite[] sprites,int hitpoints){
		LivingCreatures.isValidSpriteArray(sprites,this);
		this.setWorld(world);
		this.setPosition(XPosition, YPosition);
		this.setHorizontalVelocity(horizontalVelocity);
		this.setVerticalVelocity(verticalVelocity);
		this.setHP(hitpoints);
		this.setHorizontalAcceleration(horizontalAcceleration);
		this.setVerticalAcceleration(verticalAcceleration);
		this.spriteArray = sprites;		
	}
	
	protected LivingCreatures(int XPosition, int YPosition, double horizontalVelocity, 
			double verticalVelocity, double horizontalAcceleration, 
			World world, Sprite[] sprites, int hitpoints){
		this(XPosition,YPosition,horizontalVelocity,verticalVelocity, horizontalAcceleration, 
				0,world, sprites, hitpoints);
	}
	
	protected LivingCreatures(int XPosition, int YPosition, double horizontalVelocity, 
			double verticalVelocity,World world, Sprite[] sprites, int hitpoints){
		this(XPosition,YPosition,horizontalVelocity,verticalVelocity, 0,world, sprites, hitpoints);
	}
	
	protected LivingCreatures(int XPosition, int YPosition,World world, Sprite[] sprites, int hitpoints){
		this(XPosition,YPosition,0,0,world, sprites, hitpoints);
	}
	
	protected LivingCreatures(int XPosition, int Yposition,Sprite[] sprites, int hitpoints) {
		this(XPosition,Yposition,0,0,null,sprites, hitpoints);
	}
	
	protected LivingCreatures(Sprite[] sprites, int hitpoints) {
		this(0,0,sprites, hitpoints);
	}
	
	/**
	 * Return the X position of this living creature.
	 * 	The position is the actual position of the left X pixel of
	 * 	the living creature character in the gameworld.
	 */
	@Basic
	public double getXPosition() {
		return position.getXPosition();
	}
	
	/**
	 * Return the Y position of this living creature.
	 * 	The position is the actual position of the bottom Y pixel of
	 * 	the living creature character in the gameworld.
	 */
	@Basic
	public double getYPosition () {
		return position.getYPosition();
	}
	
	public int[] getPosition() {
		return position.getPosition();
	}

	private Position position = new Position();

	/**
	 * Set the X position of this living creature to the given X position.
	 * @param	xPosition
	 * 			The new position on the X-axis of the leftmost pixel for this living creature.
	 * @post	The new X position of the leftmost pixel of the living creature is equal to the
	 * 			given xPosition.
	 * @throws 	IllegalXPositionException
	 * 			The given X position is not a valid X position for a living creature.
	 * 			| ! isValidXPosition(xPosition)
	 */
	@Basic @Raw
	public void setPosition(double positionLeftX, double positionBottomY) throws IllegalXPositionException, IllegalYPositionException {
		try {
			position =  new Position(positionLeftX,positionBottomY,getWorld());
		} catch (IllegalXPositionException | IllegalYPositionException exc) {
			if (positionLeftX < 0) {
				positionLeftX = 0;
				//terminate();
			}
			else if (positionLeftX > position.getWidth())
				positionLeftX = position.getWidth();
				//terminate();
			if (positionBottomY < 0)
				positionBottomY = 0;
				//terminate();
			else if (positionBottomY > position.getHeight())
				positionBottomY = position.getHeight();
				//terminate();
		}
		position =  new Position(positionLeftX, positionBottomY,getWorld());
	}
	
	public void setXPosition(double positionLeftX) {
		setPosition(positionLeftX, getYPosition());
	}
	
	public void setYPosition(double positionBottomY) {
		setPosition(getXPosition(), positionBottomY);
	}
	
	@Basic
	public double getHorizontalVelocity() {
		return this.horizontalVelocity;
	}
	
	/**
	 * Variable registering the horizontal velocity of this living creature.
	 * 	The standard horizontal velocity is 0.
	 */
	private double horizontalVelocity = 0;
	
	/**
	 * Sets the horizontal velocity of this living creature to the given horizontal velocity.
	 * 
	 * @param	horizontalVelocity
	 * 			The new horizontal velocity for this living creature.
	 * @pre 	The given horizontalVelocity is a valid velocity for this living creature.
	 * @post	The horizontalVelocity of this living creature is equal to
	 * 			the given horizontalVelocity.
	 * 			| new.getHorizontalVelocity() == horizontalVelocity

	 */
	public void setHorizontalVelocity(double horizontalVelocity) {
			this.horizontalVelocity = horizontalVelocity;
	}

	/**
	 *  Return the vertical velocity of this living creature.
	 */
	public double getVerticalVelocity(){
		return this.verticalVelocity;
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
	 * @post	The new vertical velocity is equal to the
	 * 			given vertical velocity.
	 * 			| 	then new.verticalVelocity = verticalVelocity
	 */
	public void setVerticalVelocity(double verticalVelocity){
			this.verticalVelocity = verticalVelocity;
	}
	
	/**
	 *  Return the horizontal acceleration of this living creature.
	 */
	public double getHorizontalAcceleration(){
		return this.horizontalAcceleration;
	}
	
	/**
	 * Variable registering the horizontal acceleration of this living creature.
	 * 	The standard horizontal acceleration is 0.
	 */
	private double horizontalAcceleration = 0;
	
	/**
	 * Sets the horizontal acceleration of this living creature to the given horizontal acceleration.
	 * 
	 * @param	horizontalVelocity
	 * 			The new horizontal acceleration for this living creature.
	 * @pre		The given horizontalAcceleration is a valid acceleration for this living creature.
	 * @post	The new horizontal acceleration is equal to the
	 * 			given horizontal acceleration.
	 * 			| 	then new.horizontalAcceleration = horizontalAcceleration
	 */
	public void setHorizontalAcceleration(double horizontalAcceleration){
			this.horizontalAcceleration = horizontalAcceleration;
	}
	
	/**
	 *  Return the vertical acceleration of this living creature.
	 */
	public double getVerticalAcceleration(){
		return this.verticalAcceleration;
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
			this.verticalAcceleration = verticalAcceleration;
	}
	
	protected World world;
	
	
	/**
	 * Set the world for this living creature to the given world.
	 * @param	world
	 * 			The new world for this living creature.
	 * @post	The new world for the living creature is equal to the
	 * 			given world.
	 */
	public void setWorld(World world){
		assert canHaveAsWorld(world);
		this.world = world;
	}
	
	/**
	 * Return world in which the living creature is located.
	 */
	public World getWorld(){
		return this.world;
	}
	
	public void removeFromWorld(){
		this.setWorld(null);
	}
	
	public boolean isInWorld(){
		if (world != null)
			return true;
		else
			return false;
	}
	
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
	 * @return	True if the spriteArray is a valid spriteArray.
	 * 			| result ==
	 * 			|	(spriteArray.length >= 0) &&  (spriteArray.length % 2 == 0)
	 */
	public static boolean isValidSpriteArray(Sprite[] spriteArray,Object object) {
		if (object instanceof Mazub)
			return Mazub.isValidSpriteArray(spriteArray);
		else {			
			int length = spriteArray.length;
			return length == 2;
		}
	}
	
	/**
	 * Variable registering the spriteArray of this living creature.
	 */
	private final Sprite[] spriteArray;
	
	public String getKey() {
		return this.key;
	}
	
	private String key;
	
	public void setKey(String key) {
		this.key = key;
	}

	public Sprite getCurrentSprite() {
		if (getDirection() == Direction.RIGHT)
			return getSpriteArray()[1];
		else
			return getSpriteArray()[0];
	}
	
	/**
	 * TODO
	 * @return
	 */
	public Direction getDirection() {
		return this.direction;
	}
	
	/**
	 * Variable indicating the direction of this living creature. -1 indicates left, 1 indicates right.
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
		//TODO Manier van programmeren?
		this.direction = direction;
	}
	
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
	 * 		 	The new boolean that indicates wether or not living creature is moving horiztonally.
	 * @post	The new moving state of this living creature is equal to the 
	 * 			given flag.
	 * 			| new.getMoving == flag
	 */
	@Basic
	public void setMoving(boolean flag) {
		this.moving = flag;
	}
	
	public int getHP() {
		return this.hitpoints;
	}
	
	private int hitpoints;
	
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
	
	public abstract void terminate();

	public abstract int getMaxHP();
	public abstract int getMinHP();
	
	public static final int MIN_HP = 0;
	
	public void addHP(int HP) {
		setHP(getHP() + HP);
	}
	
	protected double getTerrainTimer() {
		return this.terrainTimer;
	}
	
	protected double terrainTimer;
	
	protected void setTerrainTimer(double time) {
		assert isValidTimerValue(time);
		terrainTimer = time;
	}

	protected boolean isValidTimerValue(double time) {
		return Util.fuzzyGreaterThanOrEqualTo(time, 0);
	}
	
	public void terrainDamage(Tile terrain) {
		if (terrain.getType() == TileType.WATER) {
			waterDamage(terrain);
		}
		else if (terrain.getType() == TileType.MAGMA) {
			magmaDamage(terrain);
		}
	}

	private void magmaDamage(Tile magma) {
		assert (magma.getType() == TileType.MAGMA);
		if (magma.getState() == Tile.State.UNAFFECTED) {
			addHP(-50);
		}
		else if (magma.getState() == Tile.State.OCCUPIED) {
			if (Util.fuzzyGreaterThanOrEqualTo(getTerrainTimer(), 0.2))
				addHP(-50);
		}
	}

	private void waterDamage(Tile water) {
		assert (water.getType() == TileType.WATER);
		if (Util.fuzzyGreaterThanOrEqualTo(getTerrainTimer(), 0.2))
			addHP(-2);
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
	
	protected static enum State {
		ALIVE,DEAD;
	}
	
	protected State getState() {
		return this.state;
	}
	
	protected State state;
	
	protected void setState(State state) {
		assert (state != null);
		this.state = state;
	}
}

