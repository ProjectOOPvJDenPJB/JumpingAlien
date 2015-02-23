import be.kuleuven.cs.som.annotate.*;

/**
 * A class for signalling illegal positions for Mazub.
 * 
 * @author Joren
 * @version 1.0
 */
public class IllegalXPositionException extends RuntimeException {
	
	/**
	 * Initialize this new illegal position exception with given position.
	 * 
	 * @param 	position
	 * 			The position for this new illegal position exception.
	 * @post	The position of this new illegal position exception
	 * 			is equal to the given position.
	 * 			| new.getPosition == position
	 */
	public IllegalXPositionException(double position) {
		this.positionLeftX = position;
	}
	
	/**
	 * Return the position registered for the illegal position.
	 */
	@Basic @Immutable
	public double getXPosition() {
		return Math.floor(this.positionLeftX);
	}
	
	/**
	 * Variable registering the position in this illegal position
	 * exception.
	 */
	private final double positionLeftX;
	
	/**
	 * Serial Version ID, strongly advised by the Java API.
	 */
	private static final long serialVersionUID = -8520917040316579677L;

}
