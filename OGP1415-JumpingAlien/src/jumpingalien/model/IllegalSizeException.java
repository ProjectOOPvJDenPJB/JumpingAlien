package jumpingalien.model;

/**
 * A class for signalling an illegal size for Mazub.
 * 
 * @author Joren Dhont & Pieterjan Beerden
 *
 */
public class IllegalSizeException extends RuntimeException {

	/**
	 * Initialize this new illegal size exception with the given size.
	 * 
	 * @param 	size
	 * 			The size for this illegal size exception.
	 * @post	The size of this new size exception
	 * 			is equal to the given size.
	 * 			| new.getSize == size
	 */
	public IllegalSizeException(int size) {
		this.size = size;
	}
	
	/**
	 * Return the size registered for the illegal size.
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Variable registering the size in this IllegalSizeException.
	 */
	private final int size;

	/**
	 * Serial Version ID, strongly advised by the Java API.
	 */
	private static final long serialVersionUID = 4596417414623577409L;
}
