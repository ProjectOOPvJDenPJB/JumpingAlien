package jumpingalien.model;

import jumpingalien.model.LivingCreatures.State;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

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
			double horizontalAcceleration,Sprite[] sprites, World world, int hitpoints){
		super(positionX,positionY,horizontalVelocity,verticalVelocity,horizontalAcceleration,
				world,sprites,hitpoints);
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
			World world,Sprite[] sprites){
		this(positionX,positionY,horizontalVelocity,verticalVelocity,0,sprites, world,100);	
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
			Sprite[] sprites){
		this(positionX,positionY,horizontalVelocity,verticalVelocity, null,sprites);	
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
	public Slime(int positionX, int positionY, Sprite[] sprites){
		this(positionX,positionY,0,0,sprites);	
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
	public Slime(Sprite[] sprites){
		this(0,0,sprites);	

	}
	
	public void startMoveRight(){
		this.setDirection(1);
		this.setMoving(true);
		this.setHorizontalVelocity(0);
		this.setHorizontalAcceleration(0.7);

		}
	
	public void startMoveLeft(){
		this.setDirection(-1);
		this.setMoving(true);
		this.setHorizontalVelocity(0);
		this.setHorizontalAcceleration(0.7);
	}
	
	public void endMoveRight(){
		this.setMoving(false);
		this.setHorizontalVelocity(0);
		this.setHorizontalAcceleration(0);
	}
	
	public void endMoveLeft(){
		this.setMoving(false);
		this.setHorizontalVelocity(0);
		this.setHorizontalAcceleration(0);
	}


	@Override
	public void terminate() {
		setState(State.DEAD);
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaxHP() {
		return 100;
	}

	@Override
	public int getMinHP() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	/**
	 * @param 	timeInterval 
	 * 			The time interval in which the position of this mazub has changed.
	 * @post	The new X position of this Mazub is equal to the current X position added to the horizontal distance
	 * 			travelled calculated with a formula using the given time interval. 
	 * 			new.getXPosition = this.getXPosition() + distanceCalculated
	 */
	public void changeHorizontalPosition(double timeInterval){
		double newPositionX = this.getXPosition() + this.getDirection().getInt() * 
				(100 * this.getHorizontalVelocity()*timeInterval 
				+ 50 * this.getHorizontalAcceleration()*timeInterval*timeInterval); 
		this.setXPosition(newPositionX);
	}
	
	@Override
	public void advanceTime(double timeInterval) throws IllegalTimeIntervalException {
		if (! isValidTimeInterval(timeInterval))
			throw new IllegalTimeIntervalException(timeInterval);
		if (this.getMoving() == true){
			this.changeHorizontalPosition(timeInterval);
			if (Util.fuzzyLessThanOrEqualTo(this.getHorizontalVelocity(),2.5)){
				this.setHorizontalVelocity(this.getHorizontalVelocity() + this.getHorizontalAcceleration()*timeInterval);
			}
		}
		
	}
	
}
