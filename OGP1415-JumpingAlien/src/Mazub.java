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
 * NAKIJKEN: Op welke manier elke methode moet geïmplementeerd worden.
 * 
 * comment naar joren: 
 * moeten we horizontalAcceleration, horizontalMaximumVelocity, en horizontalInitialVelocity wel aan ons object zelf toevoegen? En nie gewoon een waarde binnen de code?
 * Want die waardes veranderen niet, zijn statisch. 
 * Comment naar PJ:
 * 	Ja da dacht ik al ma kzei al da die constructor op nixen trekt! :)
 */
public class Mazub {
	
	public Mazub(int positionLeftX,int positionBottomY,int width,int height,double horizontalVelocity,
			double horizontalMaximumVelocity,double horizontalAcceleration,double horizontalInitialVelocity, int direction,
			double movingTime,double timeInterval) {
		assert isValidInitialVelocity(horizontalInitialVelocity, horizontalMaximumVelocity);
		assert isValidMaximumVelocity(horizontalMaximumVelocity, horizontalInitialVelocity);
		assert isValidhorizontalAcceleration(horizontalAcceleration);
		this.horizontalInitialVelocity = horizontalInitialVelocity;
		this.horizontalMaximumVelocity = horizontalMaximumVelocity;
		this.horizontalAcceleration = horizontalAcceleration;
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
	 * @param positionX
	 * @return	True if the given position is a valid position:
	 * 			| result ==
	 * 			|	(position <= 1024) && (position >= 0)
	 */
	public static boolean isValidXPosition(double positionX) {
		return Util.fuzzyLessThanOrEqualTo(positionX,1024) && Util.fuzzyGreaterThanOrEqualTo(positionX, 0);
	}
	
	/**
	 *	//TODO
	 * @param positionY
	 * 			//TODO
	 * @return	True if the given Y position is a valid Y position:
	 * 			| result ==
	 * 			|	(position <= 768) && (position >= 0)
	 */
	public static boolean isValidYPosition(double positionY) {
		return Util.fuzzyLessThanOrEqualTo(positionY,768) && Util.fuzzyGreaterThanOrEqualTo(positionY,0);
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
	
	/**
	 *	//TODO
	 */
	@Basic
	public double getTimeInterval() {
		return this.timeInterval;
	}
	
	public double timeInterval;
	
	/**
	 * @effect	setHorizontalVelocity
	 * 			| //TODO
	 */
	public void changeCurrentVelocity(){
		this.setHorizontalVelocity(this.getHorizontalVelocity() + this.gethorizontalAcceleration() * this.getTimeInterval(), this.getMaximumHorizontalVelocity());
	}
	
	/**
	 *	//TODO
	 * @throws IllegalXPositionException
	 */
	public void changeHorizontalPosition() throws IllegalXPositionException {
		double newPosition = this.getXPosition() - 100 * this.getHorizontalVelocity()*this.getTimeInterval() 
				- 50 * this.gethorizontalAcceleration()*this.getTimeInterval()*this.getTimeInterval();
		if (this.getDirection() == "left") {
			if (!isValidXPosition(newPosition))
					throw new IllegalXPositionException(newPosition);
			this.setXPosition(newPosition );
		}
		if (this.getDirection() == "right") {
			if (! isValidXPosition(newPosition))
				throw new IllegalXPositionException(newPosition);		
			this.setXPosition(newPosition);
		}
	}
	
	
	
	
	/**
	 * Return the size of this mazub. And his current image
	 * 	The size is given by height and width
	 *
	 * Comment to Joren, getHeight en getWidth zijn voor geprogrammeerd in de gegeven class Sprite
	 * Comment to PJ: die gaan wel over een sprite-klasse en zijn niet bruikbaar egen de mazub klasse. Dus da moet anders
	 * 	geïmplementeerd worden ;). Ik kijk da ergens deze week wel na alsk tijd heb
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
		return this.horizontalInitialVelocity;
	}
	
	/**
	 * 
	 * @param 	horizontalInitialVelocity
	 * 			The initial velocity to check.
	 * @param	horizontalMaximumVelocity
	 * 			The maximum velocity to check the initial velocity against.
	 * @return 	...//TODO
	 * 			| result ==
	 * 			|	(horizontalInitialVelocity >= 1) && (horizontalInitialVelocity < horizontalMaximumVelocity)
	 * 
	 */
	public static boolean isValidInitialVelocity(double horizontalInitialVelocity, double horizontalMaximumVelocity) {
		return (horizontalInitialVelocity >= 1) && (horizontalInitialVelocity < horizontalMaximumVelocity);
	}
	
	private final double horizontalInitialVelocity;

	
	@Basic
	public double getHorizontalVelocity() {
		return this.horizontalVelocity;
	}
	
	/**
	 * 
	 * @param 	velocity
	 * 			The velocity to check.
	 * @param 	horizontalMaximumVelocity
	 * 			The maximum velocity to check the velocity against.
	 * @param	horizontalInitialVelocity
	 * 			The initial velocity to check the velocity against.
	 * @return	... //TODO
	 * 			| result ==
	 * 			|	((velocity >= horizontalInitialVelocity) && (velocity <= horizontalMaximumVelocity)) || (velocity == 0)
	 */
	public static boolean isValidVelocity(double velocity,double horizontalMaximumVelocity,double horizontalInitialVelocity) {
		return ((Util.fuzzyGreaterThanOrEqualTo(velocity,horizontalInitialVelocity) && Util.fuzzyLessThanOrEqualTo(velocity,horizontalMaximumVelocity)) ||(velocity == 0));	
	}
	
	private double horizontalVelocity;
	
	/**
	 * 
	 * @post	...
	 * 			| if (horizontalVelocity >= 0) 
	 * 			|	&& (horizontalVelocity <= maximumHorizontalVelocity)
	 * 			|	then new.getHorizontalVelocity() == horizontalVelocity
	 * @post	...
	 * 			| if (horizontalVelocity  < 0)
	 * 			|	then new.getHorizontalVelocity() == 0
	 * @post	...
	 * 			| if (horizontalVelocity > maximumHorizontalVelocity)
	 * 			|	then new.getHorizontalVelocity() == maximumHorizontalVelocity
	 */
	public void setHorizontalVelocity(double horizontalVelocity,double maximumHorizontalVelocity) {
		if (horizontalVelocity  < 0) {
			this.horizontalVelocity = 0;
		}
		if (horizontalVelocity > maximumHorizontalVelocity) {
			this.horizontalVelocity = maximumHorizontalVelocity;
		}
		else {
			this.horizontalVelocity = horizontalVelocity;
		}
	}
		
	/**
	 * //TODO
	 * @return
	 */
	@Basic @Immutable
	public double getMaximumHorizontalVelocity() {
		return this.horizontalMaximumVelocity;
	}
	
	/**
	 * 
	 * @param 	horizontalMaximumVelocity
	 * 			The maximum velocity to check.
	 * @param	horizontalInitialVelocity
	 * 			The initial velocity to check the maximum velocity against.
	 * @return 	... //TODO
	 * 			| result == (horizontalMaximumVelocity > horizontalInitialVelocity)
	 */
	public static boolean isValidMaximumVelocity(double horizontalMaximumVelocity, double horizontalInitialVelocity) {
		return horizontalMaximumVelocity > horizontalInitialVelocity;
	}
	
	private final double horizontalMaximumVelocity;
	
	/**
	 * //TODO
	 * @return
	 */
	@Basic @Immutable
	public double gethorizontalAcceleration() {
		return this.horizontalAcceleration;	
	}
			
	/**
	 * 
	 * @param 	horizontalAcceleration
	 * 			The accelration to check.
	 * @return	... //TODO
	 * 			| result == (horizontalAcceleration >= 0)
	 */
	public static boolean isValidhorizontalAcceleration(double horizontalAcceleration) {
		return horizontalAcceleration >= 0;
	}
	
	private final double horizontalAcceleration;

	
	
	private double movingTime;
	
	/**
	 * Initializes movement to the given direction.
	 * 
	 * @param direction
	 */
	public void startMove(String direction) {	
		this.setDirection(direction);
		if  (movingTime == 0) {
			this.setHorizontalVelocity(horizontalInitialVelocity);
			this.changeHorizontalPosition();
		} else {
			if (isValidVelocity(this.getHorizontalVelocity() + this.gethorizontalAcceleration()*movingTime, this.getMaximumHorizontalVelocity(), this.getInitialVelocity())) {
			movingTime += timeInterval;
			this.setHorizontalVelocity(this.getHorizontalVelocity() + this.gethorizontalAcceleration()*movingTime);
			this.changeHorizontalPosition();
			}
		}
	}
	
	public void endMove() {
		this.horizontalVelocity = 0;
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
