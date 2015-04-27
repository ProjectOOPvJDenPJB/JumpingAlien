/**
 * 
 */
package jumpingalien.model;

import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of Position.
 * Link to repository: https://github.com/ProjectOOPvJDenPJB/JumpingAlien
 * 
 * @author Joren Dhont (Ingenieurswetenschappen: Computerwetenschappen - Elektrotechniek) 
 * 	& Pieterjan Beerden (Ingenieurswetenschappen: Elektrotechniek - Computerwetenschappen)
 * @version 0.1
 */
@Value
public class Position {
		
	/**
	 * Initializes this position with the given positions and a given world
	 * @param XPosition
	 * 		  The position on the X-axis
	 * @param YPosition
	 * 		  The position on the y-axis
	 * @param World
	 * 		  The world for which the position is given
	 * @post	The position is the same as the given position.
	 * 			| (new.getXPosition() == XPosition) && (new.getYPosition() == YPosition)
	 * @Post	The world for which the position is given is the same as the given world
	 * 			| (new.getWorld() == world) && (new.isworld == True)
	 */
	public Position(double XPosition, double YPosition, World World) {
		this.world = World;
		try {
			if (! isValidXPosition(XPosition, getWidth())) {
				throw new IllegalXPositionException(XPosition);
			}
			if (! isValidYPosition(YPosition, getHeight())) {
				throw new IllegalYPositionException(YPosition);
			}
		} catch (IllegalStateException exc) {
		}
		this.XPos = XPosition;
		this.YPos = YPosition;
//		System.out.println(getXPosition()+ "......" + getYPosition());		
	}
	
	/**
	 * Initializes a new position with the given positions 
	 * @param XPosition
	 * 		  The position on the X-axis
	 * @param YPosition
	 * 		  The position on the y-axis
	 * @effect	The new position is initialized with the given XPosition and YPosition
	 * 			and null as world.
	 * 				this(XPosition,YPosition,null);

	 */
	public Position(double XPosition, double YPosition) {
		this(XPosition,YPosition,null);
	}
	
	/**
	 * Initializes a new position 
	 * @effect	The new position is initialized with the zero for the XPosition and YPosition
	 * 				this(XPosition,YPosition);

	 */
	public Position() {
		this(0,0);
	}
	
	/**
	 * Return the X position of this position.
	 */
	public double getXPosition() {
		return this.XPos;
	}
	
	/**
	 * Check whether the given X position is a valid X position.
	 * 
	 * @param positionX
	 * 			The position on the X-axis to check against.
	 * @return	True if the given position is a valid position:
	 * 			| result ==
	 * 			|	(position <= width) && (position >= 0)
	 */
	public static boolean isValidXPosition(double positionX, int width) {
		return Util.fuzzyLessThanOrEqualTo(positionX,width) && Util.fuzzyGreaterThanOrEqualTo(positionX, 0);
	}
	
	private final double XPos;
	
	/**
	 * Return the Y position of this position.
	 */
	public double getYPosition() {
		return this.YPos;
	}
	
	/**
	 * Check whether the given Y position is a valid Y position for a Mazub.
	 * 
	 * @param 	positionY
	 * 			The position on the Y-axis to check against.
	 * @return	True if the given Y position is a valid Y position.
	 * 			| result ==
	 * 			|	(position <= height) && (position >= 0)
	 */
	public static boolean isValidYPosition(double positionY, int height) {
		return Util.fuzzyLessThanOrEqualTo(positionY,height) && Util.fuzzyGreaterThanOrEqualTo(positionY,0);
	}
	
	
	private final double YPos;

	/**
	 * Returns the X and Y position of this position.
	 * @return	The position is returned as an integer array with the X position
	 * 			first and the Y position second.
	 * 			| result[0] == getXPosition()
	 * 			|	&& result[1] == getYPosition()
	 */
	public int[] getPosition() {
		int[] Position = new int[2];
		Position[0] = (int) getXPosition();
		Position[1] = (int) getYPosition();
		return Position;
	}

	/**
	 * Return the world for this position.
	 */
	private World getWorld() {
		return this.world;
	}

	/**
	 * Variable registering the world for this position.
	 */
	private final World world;
	
	/**
	 * Return the width of the world from this position
	 * @throws	IllegalStateException
	 * 			The world of this position is not effective.
	 * 			| (this.getWorld() == null)
	 */
	public int getWidth() throws IllegalStateException {
		if (this.getWorld() == null) 
			throw new IllegalStateException("World is Null");
		else 
			return getWorld().getPixelWidth();
	}
	
	/**
	 * Return the height of the world from this position
	 * @throws	IllegalStateException
	 * 			The world of this position is not effective.
	 * 			| (this.getWorld() == null)
	 */
	public int getHeight() throws IllegalStateException {
		if (this.getWorld() == null) 
			throw new IllegalStateException("World is Null");
		else
			return getWorld().getPixelHeight();
	}
}
