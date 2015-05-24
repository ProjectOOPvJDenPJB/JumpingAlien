package jumpingalien.model;

import java.util.Random;

import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
/**
 * A class of Shark.
 * Link to repository: https://github.com/ProjectOOPvJDenPJB/JumpingAlien
 * 
 * @author Joren Dhont (Ingenieurswetenschappen: Computerwetenschappen - Elektrotechniek) 
 * 	& Pieterjan Beerden (Ingenieurswetenschappen: Elektrotechniek - Computerwetenschappen)
 * @version 0.1
 */
public class Shark extends LivingCreatures {
	

	/**
	 * Initialize this new Shark with given position, horizontal velocity, vertical velocity,
	 * 	world, sprites and hitpoints.
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Shark.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Shark.
	 * @param 	horizontalVelocity
	 * 			The horizontal velocity for this new Shark.
	 * @param 	verticalVelocity
	 * 			The vertical velocity for this new Shark.
	 * @param   world
	 * 			The world for this new Shark.
	 * @param 	sprites
	 * 			The sprites for this new Shark.
	 * @param	hitpoints
	 * 			The hitpoitns for this new Shark.
	 * @effect	The new Shark is initialized as a LivingCreature with given position,
	 * 			horizontal velocity, vertical velocity, sprites, world and hitpoints.
	 * 			The horizontal acceleration is set to 1.5, the vertical acceleration
	 * 			and initial horizontal velocity are set to 0. The maximum horizontal velocity is set to 4.
	 * 			| super(positionX,positionY, horizontalVelocity, verticalVelocity,1.5,0,0,4,world,sprites, hitpoints)
	 */
	public Shark(int positionX, int positionY, double horizontalVelocity, double verticalVelocity,
			World world,Sprite[] sprites, int hitpoints){
		super(positionX,positionY, horizontalVelocity, verticalVelocity,1.5,0,0,4,world,sprites, hitpoints);
		this.setInitialHorizontalVelocity(0);
		this.setInitialHorizontalAcceleration(1.5);
	}
	
	/**
	 * Initialize this new Shark with given position en sprites
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Shark.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Shark.
	 * @param 	sprites
	 * 			The sprites for this new Shark.
	 * @param   program	
	 * 			The given program for this new Shark
	 * @effect	The new Shark is initialized with given position and sprite array.
	 * 			The world is set to null, the hitpoints to 100 and
	 * 			horizontal velocity and vertical velocity are set to 0.
	 * 			| this(positionX,positionY,0,0,null,sprites,100)
	 * @post	The program for this new Shark is equal to the given program
	 */
	public Shark(int positionX, int positionY, Sprite[] sprites,Program program){
		this(positionX,positionY,0,0,null,sprites,100);	
		this.setProgram(program);
	}

	/**
	 * Initialize this new Shark with given position en sprites
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Shark.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Shark.
	 * @param 	sprites
	 * 			The sprites for this new Shark.
	 * @effect	The new Shark is initialized with given position and sprite array.
	 * 			The world is set to null, the hitpoints to 100 and
	 * 			horizontal velocity and vertical velocity are set to 0.
	 * 			| this(positionX,positionY,0,0,null,sprites,100)
	 */
	public Shark(int positionX, int positionY, Sprite[] sprites){
		this(positionX,positionY,0,0,null,sprites,100);	
	}
	
	/**
	 * Initialize this new Shark with sprites
	 * 
	 * @param 	sprites
	 * 			The sprites for this new Shark.
	 * @effect	The new Shark is initialized with given sprites.
	 * 			The position is set to (0,0).
	 * 			| this(0,0,sprites, null)
	 */
	public Shark(Sprite[] sprites){
		this(0,0,sprites);	

	}
	
	/**
	 * Terminates this Shark if it's still alive.
	 * @post If the Shark isn't dying yet but meets the requirements to die
	 * 		 |if isAlive() && (getHP() > 0) && (! getOutOfBounds())
	 * 		 | new.getState == state.DYING
	 * @post If the Shark is dying for longer then 0.6 seconds or out of the game boundaries he is terminated
	 * 		 |if isDying() && Util.fuzzyGreaterThanOrEqualTo(getDeathTimer(), 0.6) || getOutOfBounds()
	 * 		 | new.getWorld() == null
	 * 		 | new.getState() == state.DEAD
	 * 		 | new !in (old.getWorld().getSlimes())
	 * @throws	IllegalStateException
	 * 			This Shark is alive within the boundaries of the world and didnt win.
	 * 			| (isAlive() && (getHP() > 0) && (! getOutOfBounds()) && (! hasWonGame())
	 */
	@Override
	public void terminate() {
		if (!isDead()) {
			if ((isAlive() && (getHP() > 0) && (! getOutOfBounds()) && (!this.getWorld().isTerminating())))
				throw new IllegalStateException("Shark is alive within the boundaries of the world!");
			else if (((isDying()) && (Util.fuzzyGreaterThanOrEqualTo(getDeathTimer(), 0.6))) ||
					(getOutOfBounds())){
				setState(State.DEAD);
				World oldWorld = getWorld();
				setWorld(null);
				oldWorld.removeShark(this);
				setHP(0);
			}
			else if (isAlive()) {
				setState(State.DYING);
				setDeathTimer(0);
			}

		}

	}

	/**
	 * returns the maximum hit points for this shark
	 */
	@Override
	public int getMaxHP() {
		return 100;
	}

	/**
	 * returns the minimum hit points for this shark
	 */
	@Override
	public int getMinHP() {
		return 0;
	}
	
	/**
	 * End the movement of this shark
	 * 
	 * @post  The horizontal velocity is reset to zero and the horizontal acceleration is set to 0.
	 * 		 | new.getHorizontalVelocity == 0
	 * 		 | new.getHorizontalAcceleration == 0
	 */
	public void endMove(){
		this.setHorizontalVelocity(0);
		this.setHorizontalAcceleration(0);
	}
	
	/**
	 * Advances the time with a given timeInterval and changes every time-related attribute of this Shark
	 * accordingly.
	 * 
	 * @effect	If this shark is dying, then this shark is terminated and
	 * 			the death timer is increased by timeInterval
	 * 			| if (isDying())
	 * 			|	then terminate() && setDeathTimer(getDeathTimer() + timeInterval)
	 * @effect	If this shark is alive then advanceTimeAlive is invoked.
	 * 			| if (isAlive())
	 * 			|	then advanceTimeAlive(timeInterval)
	 * @throws	IllegalTimeIntervalException
	 * 			The given time interval is not a valid time interval for this shark.
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
	 * Advances the time with a given timeInterval and changes every time-related attribute of this Shark
	 * accordingly.
	 * 
	 * @param 	timeInterval
	 * 			The time that elapses in this invocation of the method advanceTimeAlive.
	 * @post	The new horizontal velocity is equal to the current horizontal velocity
	 * 			added to the horizontal velocity calculated with the horizontal acceleration and timeInterval.
	 *			| new.getHorizontalVelocity() == this.getHorizontalVelocity() +  horizontalVelocityCalculated
	 * @post	The new vertical velocity is equal to the current vertical velocity
	 * 			added to the vertical velocity calculated with the vertical acceleration and timeinteval.
	 * 			| new.getVerticalVelocity() == this.getVerticalVelocity + verticalVelocityCalculated
	 * @post	The new hitTimer is equal to the current hitTimer added to the timeInterval.
	 * 			| new.getHitTimer() == old.getHitTimer() + timeInterval
	 * @post	If the run time of this Shark is greater than the random time, the shark starts to move
	 * 			in the opposite direction and the random time is reset.
	 * 			| if (getRunTime() >= getRandomTime())
	 * 			|	then new.getRunTime() == 0
	 * 			|		&& new.getDirection == old.getDirection.oppositeDirection()
	 * 			|		&& new.getRandomTime == generateRandomTime()
	 * 			Otherwise the run time is incremented by timeInterval
	 * 			| else
	 * 			|	then new.getRunTime() == old.getRunTime() + timeInterval
	 * @effect	The new X position of this Shark is changed using timeInterval.
	 * 			| this.changeHorizontalPosition(timeInterval, getHorizontalAcceleration())
	 * @effect	The new Y position of this Shark is changed using timeInterval.
	 * 			| this.changeVerticalPosition(timeInterval)
	 * @effect	Terrain damage is applied if needed.
	 * 			| applyTerrainDmg(timeInterval)
	 * @effect	Interaction effects with other creatures are applied when applicable.
	 * 			| Interaction.interactWithOtherCreatures(this)
	 */
	public void advanceTimeAlive(double timeInterval) {
		if (Util.fuzzyGreaterThanOrEqualTo(getRunTime(),getRandomTime())) {
			this.setRunTime(0);
			this.endJump();
			this.setMovingVertical(false);
			this.endMove();
			if (this.canJumpAgain()) {
				this.startJump(2, -10);
				generateRandomJumpCount();
			}
			else {
				this.startJump(0, getRandomAcceleration());
				setMovingVertical(false);
			}
			this.startMove(this.getDirection().oppositeDirection());
			this.generateRandomTime();
		}
		else
			this.setRunTime(getRunTime() + timeInterval);
		
		this.changeHorizontalPosition(timeInterval,getHorizontalAcceleration());
		this.changeVerticalPosition(timeInterval);
		Interaction.interactWithOtherCreatures(this);
		this.setHorizontalVelocity(this.getHorizontalVelocity() + this.getHorizontalAcceleration()*timeInterval);
		this.setVerticalVelocity(this.getVerticalVelocity() + this.getVerticalAcceleration()*timeInterval);	
		this.setHitTimer(this.getHitTimer() + timeInterval);
		this.applyTerrainDmg(timeInterval);
	}
	
	/**
	 * Checks whether this shark can jump again.
	 * @return	If it has at least been 4 horizontal movements since the last jump,
	 * 			this shark can jump again.
	 * 			| result ==
	 * 			| 	(getJumpCounter() >= getRandomJumpCount())
	 */
	private boolean canJumpAgain() {
		return (getJumpCounter() >= getRandomJumpCount());
	}

	public void startJump(){
		if (this.canJumpAgain()){
			startJump(2,getRandomAcceleration());
		}
	}
	
	/**
	 * Return the jump counter for this shark.
	 */
	private int getJumpCounter(){
		return this.jumpCounter;
	}
	
	/**
	 * Variable registering the jump counter for this shark.
	 */
	private int jumpCounter = 4;
	
	/**
	 * Sets the jump counter of this shark to the given count.
	 * @param 	count
	 * 			The count to be set as jump counter.
	 * @post	If the count is smaller than 0, jump counter is set to 0
	 * 			| if (count < 0)
	 * 			|	then new.getJumpCounter == 0
	 * 			Otherwise the jump counter is set to the given count
	 * 			| if (count >= 0)
	 * 			|	then new.getJumpcounter == count
	 */
	private void setJumpCounter(int count) {
		if (count < 0)
			count = 0;
		this.jumpCounter = count;
	}
	
	/**
	 * Increases the jump counter by 1.
	 * @effect	Sets the jump counter to the current jump counter added by 1.
	 * 			| setJumpCounter(getJumpCounter() + 1)
	 */
	private void increaseJumpCounter() {
		setJumpCounter(getJumpCounter()+1);
	}
	
	/**
	 * returns the random time for the length of the movement of this shark
	 */
	private double getRandomTime() {
		return this.randomTime;
	}
	
	/**
	 * Variable registering the random time for the length of the movement of this shark.
	 */
	private double randomTime = (1 + (4-1) * new Random().nextDouble());
	
	/**
	 * Sets the random time for this shark to the generated time.
	 * @post	The new random time for this shark is equal to
	 * 			the generated time
	 * 			| new.getRandomTime() == (1 + (4-1) * new Random().nextDouble());
	 * @effect	The jump counter is increased.
	 * 			| increaseJumpCounter()
	 */
	private void generateRandomTime() {
		this.randomTime = (1 + (4-1) * new Random().nextDouble());
		this.increaseJumpCounter();
	}
	
	/**
	 * Return the random jump count for this shark.
	 */
	private int getRandomJumpCount() {
		return this.randomJumpCount;
	}
	
	/**
	 * Variable registering the random jump count for this shark.
	 */
	private int randomJumpCount = (4 + new Random().nextInt(3));
	
	/**
	 * Sets the random jump count to a newly generated random jump count between 4 and 7.
	 * @post	The new random jump count is set to a value between 4 and 7
	 * 			| 4 <= new.getRandomJumpCount <= 7
	 * @effect	The jump counter is reset to 0. 
	 * 			|setJumpCounter(0)
	 */
	private void generateRandomJumpCount() {
		this.randomJumpCount = (4 + new Random().nextInt(3));
		setJumpCounter(0);
	}
	
	/**
	 * Return a newly generated random acceleration between -0.2 and 0.2.
	 */
	private double getRandomAcceleration() {
		return (new Random().nextInt(4)-2) * (new Random().nextDouble());
	}

}
