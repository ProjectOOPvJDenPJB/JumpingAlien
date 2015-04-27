package jumpingalien.model;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

/**
 * Class of Plant
 * Link to repository: https://github.com/ProjectOOPvJDenPJB/JumpingAlien
 * 
 * @author Joren Dhont (Ingenieurswetenschappen: Computerwetenschappen - Elektrotechniek) 
 * 	& Pieterjan Beerden (Ingenieurswetenschappen: Elektrotechniek - Computerwetenschappen)
 * @version 0.1
 *
 */
public class Plant extends LivingCreatures{

	/**
	 * Initialize this new Plant with given position, sprites, world and hitpoints.
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Plant.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Plant.
	 * @param 	horizontalVelocity
	 * 			The horizontal velocity for this new Plant.
	 * @param 	sprites
	 * 			The sprites for this new Plant.
	 * @param   world
	 * 			The world for this new Plant.
	 * @param	hitpoints
	 * 			The hitpoints for this new Plant.
	 * @effect	The new plant is initialized as a LivingCreature with given position,
	 * 			horizontal velocity, sprites, world and hitpoints.
	 * 			The vertical velocity is set to 0 and the maximum horizontal velocity to 0.5.
	 * 			| super(positionX,positionY, horizontalVelocity,0,0.5,world,sprites, hitpoints)
	 */
	public Plant(int positionX, int positionY, double horizontalVelocity, Sprite[] sprites, World world, int hitpoints){
		super(positionX,positionY, horizontalVelocity,0,0.5,world,sprites, hitpoints);
	}

	/**
	 * Initialize this new Plant with given position en sprites.
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Plant.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Plant.
	 * @param 	sprites
	 * 			The sprites for this new Plant.
	 * @effect	The new Plant is initialized with the given position as its position,
	 * 			the given sprites as its sprites, the world as null and hitpoints are set to 1.
	 * 			| this(positionX,positionY,sprites, null, 1)
	 */
	
	public Plant(int positionX, int positionY, Sprite[] sprites){
		this(positionX,positionY,0.5,sprites, null, 1);	
	}
	
	/**
	 * Initialize this new Plant with sprites
	 * 
	 * @param 	sprites
	 * 			The sprites for this new Plant.
	 * @effect	The new Plant is initialized with the given sprites as its sprites 
	 * 			and (0,0) as its position.
	 * 			| this(0,0,sprites)
	 */
	public Plant(Sprite[] sprites){
		this(0,0,sprites);	

	}
	
	/**
	 * Terminates this plant if it's still alive.
	 * @post If the plant isn't dying yet but meets the requirements to die
	 * 		 |if isAlive() && (getHP() > 0) && (! getOutOfBounds())
	 * 		 | new.getState == state.DYING
	 * @post If the plant is dying for longer then 0.6 seconds or out of the game boundaries he is terminated
	 * 		 |if isDying() && Util.fuzzyGreaterThanOrEqualTo(getDeathTimer(), 0.6) || getOutOfBounds()
	 * 		 | new.getWorld() == null
	 * 		 | new.getState() == state.DEAD
	 * 		 | new !in (old.getWorld().getSlimes())
	 * @throws	IllegalStateException
	 * 			This Plant is alive within the boundaries of the world and didnt win.
	 * 			| (isAlive() && (getHP() > 0) && (! getOutOfBounds()) && (! hasWonGame())
	 */
	public void terminate() {
		if (!isDead()) {
			if ((isAlive() && (getHP() > 0) && (! getOutOfBounds()) && (!this.getWorld().isTerminating())))
				throw new IllegalStateException("Plant is alive within the boundaries of the world!");
			else if (((isDying()) && (Util.fuzzyGreaterThanOrEqualTo(getDeathTimer(), 0.6))) ||
					(getOutOfBounds())){
				setState(State.DEAD);
				World oldWorld = getWorld();
				setWorld(null);
				oldWorld.removePlant(this);
				setHP(0);
			}
			else if (isAlive()) {
				setState(State.DYING);
				setDeathTimer(0);
			}
		}
	}

	/**
	 * Checks whether this plant is eatable.
	 * @return	True if this plant is alive, false otherwise.
	 * 			| result == isAlive()
	 */
	public boolean isEatablePlant() {
		return (isAlive());
	}
	
	/**
	 * returns the maximum hit points for this plant
	 */
	@Override
	public int getMaxHP() {
		return 1;
	}

	/**
	 * returns the minimum hit points for this plant
	 */
	@Override
	public int getMinHP() {
		return 0;
	}
	
	/**
	 * Advances the time with a given timeInterval and changes every time-related attribute of this Plant
	 * accordingly.
	 * 
	 * @effect	If this plant is dying, then this plant is terminated and
	 * 			the death timer is increased by timeInterval
	 * 			| if (isDying())
	 * 			|	then terminate() && setDeathTimer(getDeathTimer() + timeInterval)
	 * @effect	If this plant is alive then advanceTimeAlive is invoked.
	 * 			| if (isAlive())
	 * 			|	then advanceTimeAlive(timeInterval)
	 * @throws	IllegalTimeIntervalException
	 * 			The given time interval is not a valid time interval for this Plant.
	 * 			| (! isValidTimeInterval(timeInterval))
	 */
	@Override
	public void advanceTime(double timeInterval) throws IllegalTimeIntervalException {
		if (! isValidTimeInterval(timeInterval))
			throw new IllegalTimeIntervalException(timeInterval);
		
		if (isDying()) {
			terminate();
			this.setDeathTimer(getDeathTimer() + timeInterval);
		}
		
		if (isAlive()) {
			double dt_min = getSmallestDT(timeInterval);
			for (double dt = dt_min; dt <= timeInterval-dt_min; dt += dt_min){
				advanceTimeAlive(dt_min);
			}
			advanceTimeAlive(timeInterval - (timeInterval - dt_min));
		}
	}
	
	/**
	 * Advances the time with a given timeInterval and changes every time-related attribute of this Plant
	 * accordingly.
	 * 
	 * @post	If the run time of this plant is greater 0.5, the plant starts to move
	 * 			in the opposite direction and the run time is reset.
	 * 			| if (getRunTime() >= getRandomTime())
	 * 			|	then new.getRunTime() == 0
	 * 			|		&& new.getDirection == old.getDirection.oppositeDirection()
	 * @post	The new runTime is equal to the current runTime added to the timeInterval.
	 * 			| new.getRunTime() == old.getRunTime() + timeInterval
	 * @effect	The new X position of this plant is changed using timeInterval.
	 * 			| this.changeHorizontalPosition(timeInterval, 0)
	 * @effect	Interaction effects with other creatures are applied when applicable.
	 * 			| Interaction.interactWithOtherCreatures(this)
	 */
	public void advanceTimeAlive(double timeInterval) {
		if (Util.fuzzyGreaterThanOrEqualTo(this.getRunTime(), 0.5)){
			this.setDirection(this.getDirection().oppositeDirection());
			this.setRunTime(0);			
		}
		
		this.changeHorizontalPosition(timeInterval,0);
		Interaction.interactWithOtherCreatures(this);
		this.setRunTime(getRunTime() + timeInterval);
	}
}
	

