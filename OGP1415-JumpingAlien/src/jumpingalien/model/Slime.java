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
	 * Initialize this new Slime with given position en sprites
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Slime.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Slime.
	 * @param 	sprites
	 * 			The sprites for this new Slime.
	 * @param   world
	 * 			The world in which the Slime is located
	 * @post	The position of this new Slime is the same as the given position.
	 * 			| (new.getXPosition() == positionLeftX) && (new.getYPosition() == positionY)
	 * @Post	The world in which the Slime is located is the same as the given world
	 * 			| (new.getWorld() == world) && (new.isworld == True)
	 * @throws	IllegalXPositionException
	 * 			The given X position is not a valid X position for a Mazub.
	 * 			| ! isValidXPosition(positionLeftX)
	 * @throws	IllegalYPositionException
	 * 			The given Y position is not a valid Y position for a Mazub.
	 * 			| ! isValidYPosition(positionBottomY)
	 * 
	 */
	public Slime(int positionX, int positionY, double horizontalVelocity,double verticalVelocity,
			double horizontalAcceleration,Sprite[] sprites, World world, School school, int hitpoints){
		super(positionX,positionY,horizontalVelocity,verticalVelocity,horizontalAcceleration,
				world,sprites,hitpoints);
		this.setSchool(school);

	}
	
	/**
	 * Initialize this new Slime with given position en sprites
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Slime.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Slime.
	 * @param 	sprites
	 * 			The sprites for this new Slime.
	 * @param   world
	 * 			The world in which the Slime is located
	 * @post	The position of this new Slime is the same as the given position.
	 * 			| (new.getXPosition() == positionLeftX) && (new.getYPosition() == positionY)
	 * @Post	The world in which the Slime is located is the same as the given world
	 * 			| (new.getWorld() == world) && (new.isworld == True)
	 * @throws	IllegalXPositionException
	 * 			The given X position is not a valid X position for a Mazub.
	 * 			| ! isValidXPosition(positionLeftX)
	 * @throws	IllegalYPositionException
	 * 			The given Y position is not a valid Y position for a Mazub.
	 * 			| ! isValidYPosition(positionBottomY)
	 //TODO Commentaar klopt niet meer
	 */
	public Slime(int positionX, int positionY,double horizontalVelocity,double verticalVelocity, 
			World world,Sprite[] sprites, School school){
		this(positionX,positionY,horizontalVelocity,verticalVelocity,0.7,sprites, world,school,100);	
	}

	/**
	 * Initialize this new Slime with given position en sprites
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Slime.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Slime.
	 * @param 	sprites
	 * 			The sprites for this new Slime.
	 * @effect	The new Slime is initialized with the given position as its position,
	 * 			the given sprites as its sprites and world as null
	 * 			| this(positionX,positionY,sprites, null)
	 //TODO Commentaar klopt niet meer	 */
	public Slime(int positionX, int positionY, double horizontalVelocity,double verticalVelocity,
			Sprite[] sprites, School school){
		this(positionX,positionY,horizontalVelocity,verticalVelocity, null,sprites, school);	
	}
	
	/**
	 * Initialize this new Slime with given position en sprites
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Slime.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Slime.
	 * @param 	sprites
	 * 			The sprites for this new Slime.
	 * @effect	The new Slime is initialized with the given position as its position,
	 * 			the given sprites as its sprites and world as null
	 * 			| this(positionX,positionY,sprites, null)
	 //TODO Commentaar klopt niet meer	 */
	public Slime(int positionX, int positionY, Sprite[] sprites, School school){
		this(positionX,positionY,0,0,sprites, school);	
	}
	
	/**
	 * Initialize this new Slime with sprites
	 * 
	 * @param 	sprites
	 * 			The sprites for this new Slime.
	 * @effect	The new Slime is initialized with the given sprites as its sprites 
	 * and as for positionX and positionY the value zero as its position, and world as null.
	 * 			| this(0,0,sprites, null)
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
	 */
	@Override
	public void terminate() {
		if (!isDead()) {
			if ((isAlive() && (getHP() >= 0) && (! getOutOfBounds())))
				throw new IllegalStateException("Slime is alive within the boundaries of the world!");
			else if (((isDying()) && (Util.fuzzyGreaterThanOrEqualTo(getDeathTimer(), 0.6))) ||
					(getOutOfBounds())){
				this.getSchool().removeSlime(this);
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
	
	@Override
	public void advanceTime(double timeInterval) throws IllegalTimeIntervalException {
		if (! isValidTimeInterval(timeInterval))
			throw new IllegalTimeIntervalException(timeInterval);
		
		if (isDying()) {
			terminate();
			this.setDeathTimer(getDeathTimer() + timeInterval);
		}
		
		if (isAlive()) {
			
			if (Util.fuzzyGreaterThanOrEqualTo(getRunTime(), getRandomTime())) {
				startMoveOpposite();
				setRandomTime();
				setRunTime(0);
			}
			else
				this.setRunTime(getRunTime() + timeInterval);

			this.changeHorizontalPosition(timeInterval);
			this.setHorizontalVelocity(this.getHorizontalVelocity() + this.getHorizontalAcceleration()*timeInterval);
			this.setHitTimer(this.getHitTimer() + timeInterval);
			this.applyTerrainDmg(timeInterval);
		}
		
	}
	/**
	 * returns the random time for the length of the movement of this slime
	 */
	private double getRandomTime() {
		return this.randomTime;
	}
	
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
