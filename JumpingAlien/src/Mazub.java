import be.kuleuven.cs.som.annotate.*;

/**
 * A class of Mazub.
 * 
 * TODO 	Invariants of movement variables.
 * 			Documentation of methods.
 * 
 * @author Joren Dhont & Pieterjan Beerden
 * @version 0.1
 */
public class Mazub {
	
	public Mazub(int[] position,int size,double velocity,
			double vmax,double acceleration,double vinitial) {
		this.vmax = vmax;
		this.acceleration = acceleration;
		this.vinitial = vinitial;
	}
	
	/**
	 * Return the position of this mazub.
	 * 	The position is the actual position of the bottom left pixel of
	 * 	the Mazub character in the //TODO gameworld.
	 */
	@Basic
	public int getPosition() {
		return this.position;
	}
	
	private int position;
	
	/**
	 *  //TODO
	 * @throws IllegalPositionException
	 */
	@Basic
	public void setPosition() throws IllegalPositionException {
		//TODO
	}
	
	/**
	 * Return the size of this mazub.
	 * 	The size is //TODO
	 */
	@Basic
	public int getSize() {
		return this.size;
	}
	
	private int size;
	
	/**
	 * //TODO
	 * @throws IllegalSizeException
	 */
	@Basic
	public void setSize() throws IllegalSizeException {
		//TODO
	}
	
	/**
	 * 
	 * @return
	 */
	@Basic
	public double getInitialVelocity() {
		return this.vinitial;
	}
	
	/**
	 * 
	 * @return
	 */
	@Basic
	public double getVelocity() {
		return this.velocity;
	}
	
	/**
	 * //TODO
	 * @return
	 */
	@Basic @Immutable
	public double getMaximumVelocity() {
		return this.vmax;
	}
	
	/**
	 * //TODO
	 * @return
	 */
	@Basic @Immutable
	public double getAcceleration() {
		return this.acceleration;	
	}
	
	/**
	 * 
	 * @param 	velocity
	 * 			The velocity to check.
	 * @param 	vmax
	 * 			The maximum velocity to check the velocity against.
	 * @param	vinitial
	 * 			The initial velocity to check the velocity against.
	 * @return	... //TODO
	 * 			| result ==
	 * 			|	(velocity >= vinitial) && (velocity <= vmax)
	 */
	public static boolean isValidVelocity(double velocity,double vmax,double vinitial) {
		return (velocity >= vinitial) && (velocity <= vmax);	
	}
	
	/**
	 * Initializes movement to the given direction.
	 * @param direction
	 */
	public void startMove(String direction) {			
		if (direction.equals("Left")) {
			this.setDirection(-1);
		} else {
			this.setDirection(1);
		}
		//TODO
	}
	
	public void endMove() {
		this.velocity = 0;
	}
	
	/**
	 *  //TODO
	 * @return
	 */
	@Basic
	public int getDirection() {
		return direction;
	}

	/**
	 *  //TODO
	 * @param direction
	 */
	@Basic
	public void setDirection(int direction) {
		this.direction = direction;
	}

	private double velocity;
		
	private final double vmax;
	
	private final double acceleration;
	
	private final double vinitial;
	
	private int direction;
	
	
}
