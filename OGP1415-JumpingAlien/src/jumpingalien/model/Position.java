/**
 * 
 */
package jumpingalien.model;

import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * @author Joren
 *
 */
@Value
public class Position {
		
	public Position(double XPosition, double YPosition, World World) {
		this.world = World;
		if (! isValidXPosition(XPosition, getWidth())) {
			throw new IllegalXPositionException(XPosition);
		}
		this.XPos = XPosition;
		if (! isValidYPosition(YPosition, getHeight())) {
			throw new IllegalYPositionException(YPosition);
		}
		this.YPos = YPosition;
		
	}
	
	public Position(double XPosition, double YPosition) {
		this(XPosition,YPosition,null);
	}
	
	public Position() {
		this(0,0,null);
	}
	
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
	static boolean isValidXPosition(double positionX, int width) {
		return Util.fuzzyLessThanOrEqualTo(positionX,width) && Util.fuzzyGreaterThanOrEqualTo(positionX, 0);
	}
	
	private final double XPos;
	
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
	private static boolean isValidYPosition(double positionY, int height) {
		return Util.fuzzyLessThanOrEqualTo(positionY,height) && Util.fuzzyGreaterThanOrEqualTo(positionY,0);
	}
	
	static boolean isPassable(LivingCreatures creature,double newXposition, double d){
		World world = creature.getWorld();
		return world.isPassable(newXposition,d);
	}
	
	private final double YPos;
	
	public int[] getPosition() {
		int[] Position = new int[2];
		Position[0] = (int) getXPosition();
		Position[1] = (int) getYPosition();
		return Position;
	}
	
	private World getWorld() {
		return this.world;
	}
	private final World world;
	
	public int getWidth() {
		if (this.getWorld() == null) 
			return 1024;
		else 
			return world.getPixelWidth();
	}
	
	public int getHeight() {
		if (this.getWorld() == null) 
			return 768;
		else
			return world.getPixelHeight();
	}

}
