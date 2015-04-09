package jumpingalien.model;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public class LivingCreatures {

	protected LivingCreatures(int XPosition, int YPosition,double horizontalVelocity, 
			double verticalVelocity, World world, Sprite[] sprites){
		LivingCreatures.isValidSpriteArray(sprites,this);
		this.setWorld(world);
		this.setPosition(XPosition, YPosition);
		this.setHorizontalVelocity(horizontalVelocity);
		this.setVerticalVelocity(verticalVelocity);
		this.spriteArray = sprites;		
	}
	
	protected LivingCreatures(int XPosition, int YPosition, World world, Sprite[] sprites){
		this(XPosition,YPosition,0,0,world, sprites);
	}
	
	protected LivingCreatures(int XPosition, int Yposition,Sprite[] sprites) {
		this(XPosition,Yposition,null,sprites);
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
			}
			else if (positionLeftX > position.getWidth())
				positionLeftX = position.getWidth();
			if (positionBottomY < 0)
				positionBottomY = 0;
			else if (positionBottomY > position.getHeight())
				positionBottomY = position.getHeight();
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
	 * Variable registering the horizontal velocity of this Mazub.
	 * 	The standard horizontal velocity is 0.
	 */
	private double horizontalVelocity = 0;
	
	/**
	 * Sets the horizontal velocity of this Mazub to the given horizontal velocity.
	 * 
	 * @param	horizontalVelocity
	 * 			The new horizontal velocity for this Mazub.
	 * @pre 	The given horizontalVelocity is a valid velocity for this living creature.
	 * @post	The horizontalVelocity of this Mazub is equal to
	 * 			the given horizontalVelocity.
	 * 			| new.getHorizontalVelocity() == horizontalVelocity

	 */
	public void setHorizontalVelocity(double horizontalVelocity) {
			this.horizontalVelocity = horizontalVelocity;
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
	 * @pre		The given verticalVelocity is a valid velocity for this living creature.
	 * @post	The new vertical velocity is equal to the
	 * 			given vertical velocity.
	 * 			| 	then new.verticalVelocity = verticalVelocity
	 */
	public void setVerticalVelocity(double verticalVelocity){
			this.verticalVelocity = verticalVelocity;
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
	 * Return a clone of the spriteArray of this Mazub.
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
	 * Variable registering the spriteArray of this Mazub.
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
	 * Variable indicating the direction of this Mazub. -1 indicates left, 1 indicates right.
	 */
	private Direction direction;

	/**
	 * Sets the direction of this Mazub to the given direction.
	 * 
	 * @param 	direction
	 * 			The new direction of this Mazub.
	 * @post	If the given direction is LEFT then the new direction of this Mazub is left.
	 * 			| if (direction == LEFT)
	 * 			| 	then new.getDirection() == LEFT
	 * @post	If the given direction is RIGHT then the new direction of this Mazub is right.
	 * 			| if (direction == RIGHT)
	 * 			|	then new.getDirection() == RIGHT
	 */
	public void setDirection(Direction direction) {
		//TODO Manier van programmeren?
		this.direction = direction;
	}
}

