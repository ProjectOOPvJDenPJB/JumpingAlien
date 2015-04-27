package jumpingalien.model;


import java.util.Random;






//import jumpingalien.model.LivingCreatures.State;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
/**
 * A class of Slime.
 * Link to repository: https://github.com/ProjectOOPvJDenPJB/JumpingAlien
 * 
 * @author Joren Dhont (Ingenieurswetenschappen: Computerwetenschappen - Elektrotechniek) 
 * 	& Pieterjan Beerden (Ingenieurswetenschappen: Elektrotechniek - Computerwetenschappen)
 * @version 0.1
 */
public class Slime extends LivingCreatures {
	/**
	 * Initialize this new Slime with given position, horizontal velocity,
	 * 	vertical velocity, horizontal acceleration, sprites, world and hitpoints.
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Slime.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Slime.
	 * @param 	horizontalVelocity
	 * 			The horizontal velocity for this new Slime.
	 * @param	verticalVelocity
	 * 			The vertical velocity for this new Slime.
	 * @param	horizontalAcceleration
	 * 			The horizontal acceleration for this new Slime.
	 * @param 	sprites
	 * 			The sprites for this new Slime.
	 * @param   world
	 * 			The world for this new Slime.
	 * @param	school
	 * 			The given school for this new Slime.
	 * @param	hitpoints
	 * 			The hitpoints for this new Slime.
	 * @effect	The new slime is initialized as a LivingCreature with given position,
	 * 			horizontal velocity, vertical velocity, horizontal acceleration, sprites, world and hitpoints.
	 * 			The maximum horizontal velocity is set to 2.5.
	 * 			| super(positionX,positionY,horizontalVelocity,verticalVelocity,horizontalAcceleration,2.5,
	 *			|	world,sprites,hitpoints)
	 * @effect	The new slime is added to the given school.
	 * 			| school.addSlime(this)
	 */
	public Slime(int positionX, int positionY, double horizontalVelocity,double verticalVelocity,
			double horizontalAcceleration,Sprite[] sprites, World world, School school, int hitpoints){
		super(positionX,positionY,horizontalVelocity,verticalVelocity,horizontalAcceleration,2.5,
				world,sprites,hitpoints);
		school.addSlime(this);
	}
	
	/**
	 * Initialize this new Slime with given position, horizontal velocity,
	 * 	vertical velocity, world, sprites and school.
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Slime.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Slime.
	 * @param 	horizontalVelocity
	 * 			The horizontal velocity for this new Slime.
	 * @param	verticalVelocity
	 * 			The vertical velocity for this new Slime.
	 * @param   world
	 * 			The world for this new Slime.
	 * @param 	sprites
	 * 			The sprites for this new Slime.
	 * @param	school
	 * 			The school for this new Slime.
	 * @effect	The new slime is initialized with given position, horizontal velocity,
	 * 			vertical velocity, world, sprites and school. The horizontal acceleration
	 * 			is set to 0 and the hitpoints are set to 100.
	 * 			| this(positionX,positionY,horizontalVelocity,verticalVelocity,0,sprites, world,school,100)
	 */
	public Slime(int positionX, int positionY,double horizontalVelocity,double verticalVelocity, 
			World world,Sprite[] sprites, School school){
			this(positionX,positionY,horizontalVelocity,verticalVelocity,0,sprites, world,school,100);	
	}

	/**
	 * Initialize this new Slime with given position, horizontal velocity,
	 * 	vertical velocity, sprites and school.
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Slime.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Slime.
	 * @param 	horizontalVelocity
	 * 			The horizontal velocity for this new Slime.
	 * @param	verticalVelocity
	 * 			The vertical velocity for this new Slime.
	 * @param 	sprites
	 * 			The sprites for this new Slime.
	 * @param	school
	 * 			The school for this new Slime.
	 * @effect	The new slime is initialized with given position, horizontal velocity,
	 * 			vertical velocity, sprites and school. The world is set to null.
	 * 			| this(positionX,positionY,horizontalVelocity,verticalVelocity, null,sprites, school)
	 */
	public Slime(int positionX, int positionY, double horizontalVelocity,double verticalVelocity,
			Sprite[] sprites, School school){
		this(positionX,positionY,horizontalVelocity,verticalVelocity, null,sprites, school);	
	}
	
	/**
	 * Initialize this new Slime with given position, sprites and school.
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Slime.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Slime.
	 * @param 	sprites
	 * 			The sprites for this new Slime.
	 * @param	school
	 * 			The school for this new Slime.
	 * @effect	The new Slime is initialized with given position, sprites and school.
	 * 			The horizontal and vertical velocity are set to 0.
	 * 			| this(positionX,positionY,0,0,sprites, school)
	 */
	public Slime(int positionX, int positionY, Sprite[] sprites, School school){
		this(positionX,positionY,0,0,sprites, school);	
	}
	
	/**
	 * Initialize this new Slime with given sprites and school.
	 * 
	 * @param 	sprites
	 * 			The sprites for this new Slime.
	 * @param	school
	 * 			The school for this new Slime.
	 * @effect	The new Slime is initialized with given sprites and school.
	 * 			The position is set to (0,0)
	 * 			| this(0,0,sprites, school)
	 */
	public Slime(Sprite[] sprites, School school){
		this(0,0,sprites,school);	

	}
	
	/**
	 * start a move the the opposite direction as the current movement of this slime
	 * 
	 * @post  The new direction is opposite to the current direction, the boolean moving is set to true,
	 * 		  the horizontal velocity is reset to zero and the horizontal acceleration is set to 0.7.
	 * 		 | new.getDirection() == old.getDirection().oppositeDirection()
	 * 		 | new.getMoving() == true
	 * 		 | new.getHorizontalVelocity == 0
	 * 		 | new.getHorizontalAcceleration == 0.7
	 */
	public void startMoveOpposite(){
		this.setDirection(getDirection().oppositeDirection());
		this.setMoving(true);
		this.setHorizontalVelocity(0);
		this.setHorizontalAcceleration(0.7);
		}
	
	/**
	 * terminates this slime if it's still alive.
	 * @post If the slime isn't dying yet but meets the requirements to die
	 * 		 |if isAlive() && (getHP() > 0) && (! getOutOfBounds())
	 * 		 | new.getState == state.DYING
	 * @post If the slime is dying for longer then 0.6 seconds or out of the game boundaries he is terminated
	 * 		 |if isDying() && Util.fuzzyGreaterThanOrEqualTo(getDeathTimer(), 0.6) || getOutOfBounds()
	 * 		 | new.getWorld() == null
	 * 		 | new.getState() == state.DEAD
	 * 		 | new !in (old.getWorld().getSlimes())
	 * @throws	IllegalStateException
	 * 			This Slime is alive within the boundaries of the world and didnt win.
	 * 			| (isAlive() && (getHP() > 0) && (! getOutOfBounds()) && (! hasWonGame())
	 */
	@Override
	public void terminate() {
		if (!isDead()) {
			if ((isAlive() && (getHP() > 0) && (! getOutOfBounds()) && (!this.getWorld().isTerminating())))
				throw new IllegalStateException("Slime is alive within the boundaries of the world!");
			else if (((isDying()) && (Util.fuzzyGreaterThanOrEqualTo(getDeathTimer(), 0.6))) ||
					(getOutOfBounds())){
				if (!(this.getSchool() == null)){
					this.getSchool().removeSlime(this);	
				}
				setState(State.DEAD);
				World oldWorld = this.getWorld();
				setWorld(null);
				oldWorld.removeSlime(this);
				setHP(0);
			}
			else if (isAlive()) {
				setState(State.DYING);
				setDeathTimer(0);
			}
		}
	}
	
	/**
	 * returns the maximum hit points for this slime
	 */
	@Override
	public int getMaxHP() {
		return 100;
	}

	/**
	 * returns the minimum hit points for this Slime
	 */
	@Override
	public int getMinHP() {
		return 0;
	}
	
	/**
	 * TODO
	 * @param 	timeInterval
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
			if (!collidesWithTerrainThroughBottomBorder() && (!getMovingVertical())) {
				this.startFall(-10);
			}
			double dt_min = getSmallestDT(timeInterval);
			for (double dt = dt_min; dt <= timeInterval-dt_min; dt += dt_min){
				advanceTimeAlive(dt_min);
			}
			advanceTimeAlive(timeInterval - (timeInterval - dt_min));
		}
		
	}
	
	/**
	 * TODO
	 * @param 	timeInterval
	 */
	public void advanceTimeAlive(double timeInterval) {
		
		if (Util.fuzzyGreaterThanOrEqualTo(getRunTime(), getRandomTime())) {
			startMoveOpposite();
			setRandomTime();
			setRunTime(0);
		}
		else
			this.setRunTime(getRunTime() + timeInterval);
		
		this.changeVerticalPosition(timeInterval);
		this.changeHorizontalPosition(timeInterval,getHorizontalAcceleration());
		Interaction.interactWithOtherCreatures(this);
		this.setHorizontalVelocity(this.getHorizontalVelocity() + this.getHorizontalAcceleration()*timeInterval);
		this.setVerticalVelocity(this.getVerticalVelocity() + this.getVerticalAcceleration() * timeInterval);
		this.setHitTimer(this.getHitTimer() + timeInterval);
		this.applyTerrainDmg(timeInterval);
	}
	
	/**
	 * Return the random time for the length of the movement of this slime.
	 */
	private double getRandomTime() {
		return this.randomTime;
	}
	
	/**
	 * Variable registering the random time for the length of the movement of this slime.
	 */
	private double randomTime = (2 + (6-2) * new Random().nextDouble());
	
	/**
	 * Sets the random time for this slime to the generated time.
	 * @post	The new random time for this slime is equal to
	 * 			the generated time
	 * 			| new.getRandomTime() == (2 + (6-2) * new Random().nextDouble());

	 */
	private void setRandomTime() {
		this.randomTime = (2 + (6-2) * new Random().nextDouble());
	}
	
	/**
	 * return the school of this slime
	 */
	public School getSchool(){
		return this.school;
	}
	
	/**
	 * Variable registering the school of this slime.
	 */
	private School school;
	
	/**
	 * Sets the school for this slime to the given school.
	 * 
	 * @param	school
	 * 			The new school for this slime
	 * @post	The new school for this slime is equal to
	 * 			the given school.	
	 * 			| new.getSchool() == school
	 */
	public void setSchool(School school){
		this.school = school;
	}
	
}
