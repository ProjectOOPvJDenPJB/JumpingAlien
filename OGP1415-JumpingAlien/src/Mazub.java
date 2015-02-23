import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of Mazub.
 * 
 * TODO 	Invariants of movement variables.
 * 			Documentation of methods.
 * 
 * @author Joren Dhont & Pieterjan Beerden
 * @version 0.1
 */
/**
 * 
 * NAKIJKEN: Op welke manier elke methode moet ge�mplementeerd worden.
 * Dit is belangrijk
 * 
 * comment naar joren: 
 * moeten we acceleration, vmax, en vinitial wel aan ons object zelf toevoegen? En nie gewoon een waarde binnen de code?
 * Want die waardes veranderen niet, zijn statisch. 
 * Comment naar PJ:
 * 	Ja da dacht ik al ma kzei al da die constructor op nixen trekt! :)
 */
public class Mazub {
	
	public Mazub(int positionLeftX,int positionBottomY,int width,int height,double velocity,
			double vmax,double acceleration,double vinitial, int direction,double movingTime) {
		assert isValidInitialVelocity(vinitial, vmax);
		assert isValidMaximumVelocity(vmax, vinitial);
		assert isValidAcceleration(acceleration);
		this.vinitial = vinitial;
		this.vmax = vmax;
		this.acceleration = acceleration;
		this.movingTime = movingTime;
	}
	
	/**
	 * Return the X position of this mazub.
	 * 	The position is the actual position of the left X pixel of
	 * 	the Mazub character in the 1024*768 gameworld.
	 */
	@Basic
	public double getXPosition() {
		return Math.floor(this.positionLeftX);
	}
	
	/**
	 * Return the Y position of this mazub.
	 * 	The position is the actual position of the bottom Y pixl of
	 * 	the Mazub character in the 1024*768 gameworld.
	 */
	@Basic
	public double getYPosition () {
		return Math.floor(this.positionBottomY);
	}
	
	
	
	/**
	 * 
	 * @param position
	 * @return	True if the given position is a valid position:
	 * 			| result ==
	 * 			|	(position <= 1024) && (position >= 0)
	 */
	public static boolean isValidXPosition(double position) {
		return Util.fuzzyLessThanOrEqualTo(position,1024) && Util.fuzzyGreaterThanOrEqualTo(position, 0);
	}
	
	/**
	 *	//TODO
	 * @param position
	 * 			//TODO
	 * @return	True if the given Y position is a valid Y position:
	 * 			| result ==
	 * 			|	(position <= 768) && (position >= 0)
	 */
	public static boolean isValidYPosition(double position) {
		return Util.fuzzyLessThanOrEqualTo(position,768) && Util.fuzzyGreaterThanOrEqualTo(position,0);
	}
		
	private double positionLeftX;
	private double positionBottomY;
	
	/**
	 *  //TODO
	 * @throws IllegalXPositionException
	 */
	@Basic
	public void setXPosition(double positionLeftX) throws IllegalXPositionException {
		if (! isValidXPosition(positionLeftX))
			throw new IllegalXPositionException(positionLeftX);
		this.positionLeftX = positionLeftX;
	}
	
	/**
	 *  //TODO
	 * @throws IllegaYlPositionException
	 */
	@Basic
	public void setYPosition(double positionBottomY) throws IllegalYPositionException {
		if (! isValidYPosition(positionBottomY))
			throw new IllegalYPositionException(positionBottomY);
		this.positionBottomY = positionBottomY;
	}
	
	public static double timeStep = 0.75;
	
	/**
	 * 
	 */
	public void changeHorizontalPosition(){
		if ((this.getDirection() == "left") && isValidXPosition(this.getXPosition() - this.getVelocity()*timeStep - this.getAcceleration()*timeStep*timeStep)) {
			this.setXPosition(this.getXPosition() - this.getVelocity()*timeStep - this.getAcceleration()*timeStep*timeStep );
		}
		if ((this.getDirection() == "right") && isValidXPosition(this.getXPosition() + this.getVelocity()*timeStep + this.getAcceleration()*timeStep*timeStep)) {
			this.setXPosition(this.getXPosition() + this.getVelocity()*timeStep + this.getAcceleration()*timeStep*timeStep );
		}
	}
	
	
	/**
	 * Return the size of this mazub. And his current image
	 * 	The size is given by height and width
	 *
	 * Comment to Joren, getHeight en getWidth zijn voor geprogrammeerd in de gegeven class Sprite
	 * Comment to PJ: die gaan wel over een sprite-klasse en zijn niet bruikbaar egen de mazub klasse. Dus da moet anders
	 * 	ge�mplementeerd worden ;). Ik kijk da ergens deze week wel na alsk tijd heb
	 * 
	 * U stukje code om het maar niet volledig weg te laten:
	 * height = this.getHeight();
		width = this.getWidth();
		image = ??
		return height,width,image;
	 */
	@Basic
	public int getCurrentSprite() {	
		return -1;
	}
	
	
	
	@Basic
	public double getInitialVelocity() {
		return this.vinitial;
	}
	
	/**
	 * 
	 * @param 	vinitial
	 * 			The initial velocity to check.
	 * @param	vmax
	 * 			The maximum velocity to check the initial velocity against.
	 * @return 	...//TODO
	 * 			| result ==
	 * 			|	(vinitial >= 1) && (vinitial < vmax)
	 * 
	 */
	public static boolean isValidInitialVelocity(double vinitial, double vmax) {
		return (vinitial >= 1) && (vinitial < vmax);
	}
	
	private final double vinitial;

	
	@Basic
	public double getVelocity() {
		return this.velocity;
	}
	
	/**
	 * 
	 * @param 	velocity
	 * 			The velocity to check.
	 * @param 	vmax
	 * 			The maximum velocity to check the velocity against.
	 * @param	vinitial
	 * 			The initial velocity to check the velocity against.
	 * @return	... //TODO
	 * 			| result ==
	 * 			|	(velocity >= vinitial) && (velocity <= vmax)
	 */
	public static boolean isValidVelocity(double velocity,double vmax,double vinitial) {
		return (velocity >= vinitial) && (velocity <= vmax);	
	}
	
	private double velocity;
	
	/**
	 * Sets the velocity of Mazub to a specified velocity.
	 * 
	 * @pre		...
	 * 			| isValidVelocity(velocity, this.getMaximumVelocity(), this.getInitialVelocity())
	 * @post	...
	 * 			| new.getVelocity() == velocity
	 */
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
		
	/**
	 * //TODO
	 * @return
	 */
	@Basic @Immutable
	public double getMaximumVelocity() {
		return this.vmax;
	}
	
	/**
	 * 
	 * @param 	vmax
	 * 			The maximum velocity to check.
	 * @param	vinitial
	 * 			The initial velocity to check the maximum velocity against.
	 * @return 	... //TODO
	 * 			| result == (vmax > vinitial)
	 */
	public static boolean isValidMaximumVelocity(double vmax, double vinitial) {
		return vmax > vinitial;
	}
	
	private final double vmax;
	
	/**
	 * //TODO
	 * @return
	 */
	@Basic @Immutable
	public double getAcceleration() {
		return this.acceleration;	
	}
			
	/**
	 * 
	 * @param 	acceleration
	 * 			The accelration to check.
	 * @return	... //TODO
	 * 			| result == (acceleration >= 0)
	 */
	public static boolean isValidAcceleration(double acceleration) {
		return acceleration >= 0;
	}
	
	private final double acceleration;

	
	
	private double movingTime;
	
	/**
	 * Initializes movement to the given direction.
	 * 
	 * @param direction
	 */
	public void startMove(String direction) {	
		this.setDirection(direction);
		if  (movingTime == 0) {
			this.setVelocity(vinitial);
			this.changeHorizontalPosition();
		} else {
			if (isValidVelocity(this.getVelocity() + this.getAcceleration()*movingTime, this.getMaximumVelocity(), this.getInitialVelocity())) {
			movingTime += timeStep;
			this.setVelocity(this.getVelocity() + this.getAcceleration()*movingTime);
			this.changeHorizontalPosition();
			}
		}
	}
	
	public void endMove() {
		this.velocity = 0;
		this.movingTime = 0;
	}
	
	public final int jumpingSpeed = 8;
	
	public void startJump(){
		if (isValidYPosition(this.getYPosition() + jumpingSpeed)) {
			this.setYPosition(this.getYPosition() + jumpingSpeed);
		}
	}
	
	public void endJump(){
		if (isValidYPosition(this.getYPosition())) {
			//TODO, Argument of above included.
		}
	}
	
	
	/**
	 *  //TODO
	 * @return
	 */
	@Basic
	public String getDirection() {
		return this.direction;
	}

	/**
	 *  //TODO
	 * @param direction
	 */
	@Basic
	public void setDirection(String direction) {
		this.direction = direction;
	}
			
	private String direction;
	
	
}
