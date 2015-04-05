package jumpingalien.model;
import jumpingalien.util.Sprite;

public class Plant {

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
	public Plant(int positionX, int positionY, Sprite[] sprites, World world){
		this.positionX = positionX;
		this.positionY = positionY;
		this.inWorld = world;
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
		this(positionX,positionY,sprites, null);	
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
		this(0,0,sprites, null);	

	}
	
	//ik was aan het denken om een aparte class voor positie te maken aangezien we dat
	//toch overal nodig hebben.
	private int positionX;
	private int positionY;
	private World inWorld;
	
	public boolean isInWorld(){
		return (this.getWorld() != null);
	}
	
	/**
	 * Return the World in which the Plant is Located
	 */
	public World getWorld(){
		return this.inWorld;
	}
	

	/**
	 * Set the World in which the plant is located to the given world.
	 * @param	world
	 * 			The new world in which the plant will be located.
	 * @post	The new world of the plant is equal to the
	 * 			given world if the plant isn't already in an other world.
	 */
	public void setWorld(World world){
		if (!isInWorld()){
			this.inWorld = world;
		}
	}
	
	/**
	 * Register a removal from this plant from a world. If the plant is in a world.
	 *
	 * @post   This plant is no longer in a world.
	 *	     | ! new.isInWorld()
	 * @post   The former world of this plant, no longer contains this plant
	 */
	public void removeFromWorld(){
		if (this.isInWorld()){
			this.getWorld().removeObject(this);
			/* 
			 * Ik ben er voorlopig vanuit gegaan dat we in world bijhouden wat er allemaal in zit
			 * maar we maken best een superclass aan waarin wordt bijgehouden wat er allemaal op welke
			 * positie in een gegeven wereld is.
			 */
			this.setWorld(null);
		}
	}
	}
	

