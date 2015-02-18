
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
	
	public Mazub(int position,int size,float velocity,
			int vmax, int acceleration, int vinitial) {
		this.vmax = vmax;
		this.acceleration = acceleration;
		this.vinitial = vinitial;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	private int position;
	
	public void setPosition() throws IllegalPosition {
		//TODO
	}
	
	public int getSize() {
		return this.size;
	}
	
	private int size;
		
	public void setSize() throws IllegalSize {
		//TODO
	}
	
	public int getInitialVelocity() {
		return this.vinitial;
	}
	
	public int getVelocity() {
		return this.velocity;
	}
	
	/**
	 * 
	 * @param 	velocity
	 * 			The velocity to check.
	 * @param 	vmax
	 * 			The maximum velocity to check the velocity against.
	 * @param	vinitial
	 * 			The initial velocity to check the velocity against.
	 * @return	...
	 * 			| result ==
	 * 			|	(velocity >= vinitial) && (velocity <= vmax)
	 */
	public static boolean isValidVelocity(int velocity,int vmax, int vinitial) {
		return (velocity >= vinitial) && (velocity <= vmax);
	}
	
	/**
	 * Initializes movement to the given direction.
	 * @param direction
	 */
	public void startMove(String direction) {			
		if (direction.equals("Left")) {
			this.direction = -1;
		} else {
			this.direction = 1;
		}
		//TODO
	}
	
	public void endMove() {
		this.velocity = 0;
	}
	
	private int velocity;
		
	private final int vmax;
	
	private final int acceleration;
	
	private final int vinitial;
	
	private int direction;
	
	
}
