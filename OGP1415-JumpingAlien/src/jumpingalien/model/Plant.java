package jumpingalien.model;
import jumpingalien.util.Sprite;

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
	public Plant(int positionX, int positionY, double horizontalVelocity, Sprite[] sprites, World world){
		super(positionX,positionY, horizontalVelocity,0,world,sprites);
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
		this(positionX,positionY,0,sprites, null);	
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
		this(0,0,0,sprites, null);	

	}
	
	public void startMoveRight(){
		this.setHorizontalVelocity(0.5);
		this.setRunTime(0);
	}
	
	public void startMoveLeft(){
		this.setHorizontalVelocity(0.5);
		this.setRunTime(0);
	}
	/**
	 * Return the runtimer of this Mazub.
	 */
	private double getRunTime() {
		return this.runTime;
	}
	
	/**
	 * Variable registering the time since Mazub stopped running when Mazub is not moving horizontally.
	 * When Mazub moves horizontally, the variable registers the time since the last spritechange.
	 */
	private double runTime = 1;

	/**
	 * Sets the runTimer of this Mazub to the given time.
	 * 
	 * @param 	runTimer
	 * 			The new time for the runTimer for this Mazub.
	 * @post	The new runTimer for this Mazub is equal to the given
	 * 			runTimer.
	 * 			| new.getRunTime == runTime
	 */
	private void setRunTime(double runTime) {
		this.runTime = runTime;
	}
	}
	

