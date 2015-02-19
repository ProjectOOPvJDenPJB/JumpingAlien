import be.kuleuven.cs.som.annotate.*;

/**
 * A class for signalling illegal sizes for Mazub.
 * 
 * @author Joren
 * @Version 1.0
 */
public class IllegalSizeException extends RuntimeException {
	
	/**
	 * Initialize this new illegal size exception with given size.
	 * 
	 * @param 	size
	 * 			The size for this new illegal size exception.
	 * @post	The size of this new illegal size exception
	 * 			is equal to the given size.
	 * 			| new.getSize() == size
	 */
	public IllegalSizeException(int size) {
		this.size = size;
	}
	
	/**
	 * Return the size registered for the illegal size exception.
	 */
	@Basic @Immutable
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Variable registering the size in this illegal size exception.
	 */
	private final int size;
	
	/**
	 * Serial Version ID, strongly advised by the Java API.
	 */
	private static final long serialVersionUID = -6270791427924534860L;

}
