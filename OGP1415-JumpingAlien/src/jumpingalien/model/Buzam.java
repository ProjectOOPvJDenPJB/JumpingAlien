package jumpingalien.model;

import jumpingalien.util.Sprite;

public class Buzam extends Mazub {
	
	/**
	 * Initialize this new Buzam with given position, sprites, maximum horizontal velocity and
	 * initial horizontal velocity.
	 * 
	 * @param 	positionLeftX
	 * 			The position on the X-axis of the leftmost pixel for this new Buzam.
	 * @param 	positionBottomY
	 * 			The position on the Y-axis of the bottom pixel for this new Buzam.
	 * @param 	sprites
	 * 			The array of sprites for this new Buzam.
	 * @param 	maximumHorizontalVelocity
	 * 			The maximum horizontal velocity for this new Buzam.
	 * @param 	initialHorizontalVelocity
	 * 			The initial horizontal velocity for this new Buzam.
	 * @param	world
	 * 			The world for this new Buzam.
	 * @param	hitpoints
	 * 			The hitpoints for this new Buzam.
	 * @effect	The new Buzam is initialized as a Mazub.
	 * 			| super(positionLeftX, positionBottomY, sprites, maximumHorizontalVelocity,
				|	initialHorizontalVelocity, world, hitpoints)
	 */
	public Buzam(int positionLeftX, int positionBottomY, Sprite[] sprites,
			double maximumHorizontalVelocity, double initialHorizontalVelocity,
			World world, int hitpoints) {
		super(positionLeftX, positionBottomY, sprites, maximumHorizontalVelocity,
				initialHorizontalVelocity, world, hitpoints);
	}
	
	/**
	 * Initialize this new Buzam with given position and sprites.
	 * 
	 * @param 	positionLeftX
	 * 			The position on the X-axis of the leftmost pixel for this new Buzam.
	 * @param 	positionBottomY
	 * 			The position on the Y-axis of the bottom pixel for this new Buzam.
	 * @param 	spriteArray
	 * 			The array of sprites for this new Buzam.
	 * @param 	program
	 * 			The given program for this new buzam
	 * @effect	The new Buzam is initialized with the given position as its position,
	 * 			the given spriteArray as its spriteArray, maximumHorizontalVelocity = 3
	 * 			and initialHorizontalVelocity = 1. The world is set to null and hitpoints
	 * 			are set to 500.
	 * 			| this(positionLeftX,positionBottomY,spriteArray, 3, 1, null, 500)
	 * @post	The program for this new buzam is equal to the given program
	 */
	public Buzam(int positionLeftX, int positionBottomY, Sprite[] sprites,Program program) {
		this(positionLeftX,positionBottomY,sprites,3,1,null,500);
		this.setProgram(program);
	}
	
	/**
	 * Initialize this new Buzam with given position and sprites.
	 * 
	 * @param 	positionLeftX
	 * 			The position on the X-axis of the leftmost pixel for this new Buzam.
	 * @param 	positionBottomY
	 * 			The position on the Y-axis of the bottom pixel for this new Buzam.
	 * @param 	spriteArray
	 * 			The array of sprites for this new Buzam.
	 * @effect	The new Buzam is initialized with the given position as its position,
	 * 			the given spriteArray as its spriteArray, maximumHorizontalVelocity = 3
	 * 			and initialHorizontalVelocity = 1. The world is set to null and hitpoints
	 * 			are set to 500.
	 * 			| this(positionLeftX,positionBottomY,spriteArray, 3, 1, null, 500)
	 */
	public Buzam(int positionLeftX, int positionBottomY, Sprite[] sprites) {
		this(positionLeftX,positionBottomY,sprites,3,1,null,500);
	}
	
	/**
	 * Advances the time with a given timeInterval and changes every time-related attribute of this Buzam
	 * accordingly.
	 * @effect	If this buzam is dying, then this buzam is terminated and
	 * 			the death timer is increased by timeInterval
	 * 			| if (isDying())
	 * 			|	then terminate() && setDeathTimer(getDeathTimer() + timeInterval)
 	 * @effect	If this buzam is alive then advanceTimeAlive is invoked.
	 * 			| if (isAlive())
	 * 			|	then advanceTimeAlive(timeInterval)
	 * @throws	IllegalStateException
	 * 			Buzam is already dead.
	 * 			| (isDead())
	 * @throws	IllegalTimeIntervalException
	 * 			The given time interval is not a valid time interval for this Buzam.
	 * 			| (! isValidTimeInterval(timeInterval))
	 */
	@Override
	public void advanceTime(double timeInterval) throws IllegalTimeIntervalException, IllegalStateException {
		if (! isValidTimeInterval(timeInterval))
			throw new IllegalTimeIntervalException(timeInterval);
		else if (isDying()) {
			terminate();
			this.setDeathTimer(getDeathTimer() + timeInterval);
		}
		else if (isDead())
			throw new IllegalStateException("Mazub is already dead.");
		else {
			double dt_min = getSmallestDT(timeInterval);
			for (double dt = dt_min; dt <= timeInterval-dt_min; dt += dt_min){
				advanceTimeAlive(dt_min);
			}
			advanceTimeAlive(timeInterval - (timeInterval - dt_min));
		}
	}
	
}
