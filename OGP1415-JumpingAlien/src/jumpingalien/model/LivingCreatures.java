package jumpingalien.model;

import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public class LivingCreatures {

	protected LivingCreatures(int XPosition, int YPosition,double horizontalVelocity, 
			double verticalVelocity, World world){
		this.setWorld(world);
		this.setIsInWorld(true);
		this.setXPosition(XPosition);
		this.setYPosition(YPosition);
		this.setHorizontalVelocity(horizontalVelocity);
		this.setVerticalVelocity(verticalVelocity);
	}
	
	protected LivingCreatures(int XPosition, int YPosition, World world){
		this.setWorld(world);
		this.setIsInWorld(true);
		this.setXPosition(XPosition);
		this.setYPosition(YPosition);
		this.setHorizontalVelocity(0);
		this.setVerticalVelocity(0);
	}
	
	/**
	 * Return the X position of this living creature.
	 * 	The position is the actual position of the left X pixel of
	 * 	the living creature character in the gameworld.
	 */
	@Basic
	public double getXPosition() {
		return this.positionX;
	}

	/**
	 * Check whether the given X position is a valid X position for a living creature.
	 * 
	 * @param xPosition
	 * 			The position on the X-axis to check against.
	 * @return	True if the given position is a valid position:
	 * 			| result ==
	 * 			|	(position <= 1024) && (position >= 0)
	 */
	public static boolean isValidXPosition(double xPosition) {
		return Util.fuzzyLessThanOrEqualTo(xPosition,1024) && Util.fuzzyGreaterThanOrEqualTo(xPosition, 0);
		//TODO isPassable() 
		}
	
	/**
	 * Variable registering the X position of the leftmost pixel of living creature.
	 */
	private double positionX;

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
	public void setXPosition(double positionX) throws IllegalXPositionException {
		if (! isValidXPosition(positionX))
			throw new IllegalXPositionException(positionX);
		this.positionX = positionX;
	}
	
	/**
	 * Return the Y position of this living creature.
	 * 	The position is the actual position of the bottom Y pixel of
	 * 	the living creature character in the gameworld.
	 */
	@Basic
	public double getYPosition () {
		return this.positionY;
	}
	
	
	/**
	 * Check whether the given Y position is a valid Y position for a living creature.
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
	 * Variable registering the Y position of the bottom pixel of this living creature.
	 */
	private double positionY;
	
	/**
	 * Set the Y position of this living creature to the given Y position.
	 * @param	yPosition
	 * 			The new position on the Y-axis of the bottom pixel for this living creature.
	 * @post	The new Y position of the bottom pixel of the living creature is equal to the
	 * 			given yPosition.
	 * @throws 	IllegalYPositionException
	 * 			The given Y position is not a valid Y position for a living creature.
	 * 			| ! isValidYPosition(yPosition)
	 */
	@Basic @Raw
	public void setYPosition(double positionY) throws IllegalYPositionException {
		if (! isValidYPosition(positionY))
			throw new IllegalYPositionException(positionY);
		this.positionY = positionY;
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
	
	
	private World world;
	private boolean isInWorld;
	
	
	/**
	 * Set the world for this living creature to the given world.
	 * @param	world
	 * 			The new world for this living creature.
	 * @post	The new world for the living creature is equal to the
	 * 			given world.
	 */
	public void setWorld(World world){
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
		this.setIsInWorld(false);
	}
	
	/**
	 * Sets the boolean indicating whether a living creature is in a world
	 * or not.
	 * @param	flag
	 * 			The new state for this living creature.
	 * @post	The new state for the living creature is equal to the
	 * 			given flag.
	 */
	public void setIsInWorld(boolean flag){
		this.isInWorld = flag;
	}
	
	/**
	 * Return the boolean indicating whether this living creature is
	 * 	in a world or not.
	 */
	public boolean isInWorld(){
		return this.isInWorld;
	}
}

