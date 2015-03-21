package jumpingalien.model;

/**
 * A class for signalling an illegal tile position for World.
 * 
 * @author Joren Dhont & Pieterjan Beerden
 */
public class IllegalTileException extends RuntimeException {
	
	/**
	 * Initialize this new illegal tile exception with the given position.
	 * 
	 * @param 	tileX
	 * 			The X position for this illegal tile exception.
	 * @param 	tileY
	 * 			The Y position for this illegal tile exception.
	 * @post	The X position of this new illegal tile exception is equal to the
	 * 			given X position and the Y position of this new illegal tile exception
	 * 			is equal to the given Y position.
	 * 			| (new.getLeftX() == tileX) && (new.getBottomY() == tileY)
	 */
	public IllegalTileException(int tileX,int tileY) {
		this.leftX = tileX;
		this.bottomY = tileY;
	}
	
	/**
	 * Return the X position registered for this illegal tile.
	 */
	public int getLeftX() {
		return this.leftX;
	}
	
	/**
	 * Variable registering the X position in this illegal tile exception.
	 */
	public final int leftX;
	
	/**
	 * Return the Y position registered for this illegal tile.
	 */
	public int getBottomY() {
		return this.bottomY;
	}
	
	/**
	 * Variable registering the Y position in this illegal tile exception.
	 */
	public final int bottomY;

	/**
	 * Serial Version ID, strongly advised by the Java API.
	 */
	private static final long serialVersionUID = 2788785101383166513L;
	
}
