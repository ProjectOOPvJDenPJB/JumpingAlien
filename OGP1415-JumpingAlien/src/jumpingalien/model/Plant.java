package jumpingalien.model;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

public class Plant extends LivingCreatures{

	/**
	 * Initialize this new Plant with given position en sprites
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Plant.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Plant.
	 * @param 	sprites
	 * 			The sprites for this new Plant.
	 * @param   world
	 * 			The world in which the Plant is located
	 * @post	The position of this new Plant is the same as the given position.
	 * 			| (new.getXPosition() == positionLeftX) && (new.getYPosition() == positionY)
	 * @Post	The world in which the Plant is located is the same as the given world
	 * 			| (new.getWorld() == world) && (new.isInWorld == True)
	 * @throws	IllegalXPositionException
	 * 			The given X position is not a valid X position for a Mazub.
	 * 			| ! isValidXPosition(positionLeftX)
	 * @throws	IllegalYPositionException
	 * 			The given Y position is not a valid Y position for a Mazub.
	 * 			| ! isValidYPosition(positionBottomY)
	 * 
	 */
	public Plant(int positionX, int positionY, double horizontalVelocity, Sprite[] sprites, World world, int hitpoints){
		super(positionX,positionY, horizontalVelocity,0,world,sprites, hitpoints);
	}

	/**
	 * Initialize this new Plant with given position en sprites
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Plant.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Plant.
	 * @param 	sprites
	 * 			The sprites for this new Plant.
	 * @effect	The new Plant is initialized with the given position as its position,
	 * 			the given sprites as its sprites and world as null
	 * 			| this(positionX,positionY,sprites, null)
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
	 * and as for positionX and positionY the value zero as its position, and world as null.
	 * 			| this(0,0,sprites, null)
	 */
	public Plant(Sprite[] sprites){
		this(0,0,0.5,sprites, null,1);	

	}
	
	/**
	 * terminates this plant if it's still alive.
	 * @post If the plant isn't dying yet but meets the requirements to die
	 * 		 |if isAlive() && (getHP() > 0) && (! getOutOfBounds())
	 * 		 | new.getState == state.DYING
	 * @post If the plant is dying for longer then 0.6 seconds or out of the game boundaries he is terminated
	 * 		 |if isDying() && Util.fuzzyGreaterThanOrEqualTo(getDeathTimer(), 0.6) || getOutOfBounds()
	 * 		 | new.getWorld() == null
	 * 		 | new.getState() == state.DEAD
	 * 		 | new !in (old.getWorld().getSlimes())
	 */
	public void terminate() {
		if (!isDead()) {
			if ((isAlive() && (getHP() > 0) && (! getOutOfBounds())))
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
	 * @return true if the plant is eatable, else returns false
	 */
	public boolean isEatablePlant() {
		if (isAlive())
			return true;
		else
			return false;
	}
	
	/**
	 * returns the maximum hit points for this slime
	 */
	@Override
	public int getMaxHP() {
		return 1;
	}

	/**
	 * returns the minimum hit points for this slime
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
			if (Util.fuzzyGreaterThanOrEqualTo(this.getRunTime(), 0.5)){
				this.setDirection(this.getDirection().oppositeDirection());
				this.setRunTime(0);			
			}
			
			this.changeHorizontalPosition(timeInterval);
			
	//		double newXposition = this.getXPosition() + this.getDirection().getInt() * this.getHorizontalVelocity();
	//		
	//		if (Position.isPassable(this,newXposition,this.getYPosition()-1) == false);
	//				this.setXPosition(newXposition);
			this.setRunTime(getRunTime() + timeInterval);
		}
	}
}
	

