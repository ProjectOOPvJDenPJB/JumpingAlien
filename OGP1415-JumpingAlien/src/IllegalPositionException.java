import be.kuleuven.cs.som.annotate.*;

/**
 * A class for signalling illegal positions for Mazub.
 * 
 * @author Joren
 * @version 1.0
 */
public class IllegalPositionException extends RuntimeException {
	
	/**
	 * Initialize this new illegal position exception with given position.
	 * 
	 * @param 	position
	 * 			The position for this new illegal position exception.
	 * @post	The position of this new illegal position exception
	 * 			is equal to the given position.
	 * 			| new.getPosition == position
	 */
	public IllegalPositionException(int[] position) {
		this.position = position;
	}
	
	/**
	 * Return the position registered for the illegal position.
	 */
	@Basic @Immutable
	public int[] getPosition() {
		return this.position;
	}
	
	/**
	 * Variable registering the position in this illegal position
	 * exception.
	 */
	private final int[] position;
	
	/**
	 * Serial Version ID, strongly advised by the Java API.
	 */
	private static final long serialVersionUID = -8520917040316579677L;

}
