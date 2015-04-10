package jumpingalien.model;

//import jumpingalien.model.LivingCreatures.State;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

public class Shark extends LivingCreatures {
	
	/**
	 * Initialize this new Shark with given position en sprites
	 * 
	 * @param 	positionX
	 * 			The position on the X-axis this new Shark.
	 * @param 	positionY
	 * 			The position on the Y-axis for this new Shark.
	 * @param 	sprites
	 * 			The sprites for this new Shark.
	 * @param   world
	 * 			The world in which the Shark is located
	 * @post	The position of this new Shark is the same as the given position.
	 * 			| (new.getXPosition() == positionLeftX) && (new.getYPosition() == positionY)
	 * @Post	The world in which the Shark is located is the same as the given world
	 * 			| (new.getWorld() == world) && (new.isInWorld == True)
	 * @throws	IllegalXPositionException
	 * 			The given X position is not a valid X position for a Mazub.
	 * 			| ! isValidXPosition(positionLeftX)
	 * @throws	IllegalYPositionException
	 * 			The given Y position is not a valid Y position for a Mazub.
	 * 			| ! isValidYPosition(positionBottomY)
	 * 
	 */
	public Shark(int positionX, int positionY, double horizontalVelocity, double verticalVelocity,
			World world,Sprite[] sprites, int hitpoints){
		super(positionX,positionY, horizontalVelocity, verticalVelocity,world,sprites, hitpoints);

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
	 * @effect	The new Shark is initialized with the given position as its position,
	 * 			the given sprites as its sprites and world as null
	 * 			| this(positionX,positionY,sprites, null)
	 */
	public Shark(int positionX, int positionY, Sprite[] sprites){
		this(positionX,positionY,0,0,null,sprites,100);	
	}
	
	/**
	 * Initialize this new Shark with sprites
	 * 
	 * @param 	sprites
	 * 			The sprites for this new Shark.
	 * @effect	The new Shark is initialized with the given sprites as its sprites 
	 * and as for positionX and positionY the value zero as its position, and world as null.
	 * 			| this(0,0,sprites, null)
	 */
	public Shark(Sprite[] sprites){
		this(0,0,sprites);	

	}
	
	/**
	 * Register a removal from this Shark from a world. If the Shark is in a world.
	 *
	 * @post   This Shark is no longer in a world.
	 *	     | ! new.isInWorld()
	 * @post   The former world of this Shark, no longer contains this Shark
	 */

	@Override
	public void terminate() {
		setState(State.DEAD);
		//TODO
	}

	@Override
	public int getMaxHP() {
		return 100;
	}

	@Override
	public int getMinHP() {
		return 0;
	}

	@Override
	public void advanceTime(double timeInterval) throws IllegalTimeIntervalException {
		if (! isValidTimeInterval(timeInterval))
			throw new IllegalTimeIntervalException(timeInterval);		
		//TODO
	}
	}
