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
	public Mazub(int positionLeftX,int positionBottomY,int width,int height,double horizontalVelocity, double verticalVelocity,
			double horizontalMaximumVelocity,double horizontalAcceleration,double horizontalInitialVelocity,
			boolean moving,boolean movingUpwards, boolean falling) {
		assert isValidInitialVelocity(horizontalInitialVelocity, horizontalMaximumVelocity);
		assert isValidMaximumVelocity(horizontalMaximumVelocity, horizontalInitialVelocity);
		assert isValidHorizontalAcceleration(horizontalAcceleration);
		this.horizontalInitialVelocity = horizontalInitialVelocity;
		this.horizontalMaximumVelocity = horizontalMaximumVelocity;
		this.horizontalAcceleration = horizontalAcceleration;
		this.moving = false;
		this.movingUpwards = false;
		this.falling = false;
		this.horizontalVelocity = 0;
		this.verticalVelocity = 0;
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
	
	public boolean moving;
	private boolean movingUpwards;
	private boolean falling;
	
	/**
	 *  //TODO
	 * @return
	 */
	public boolean getMoving(){
		return this.moving;
	}
	
	/**
	 *  //TODO
	 * @param moving
	 */
	public void setMoving(boolean moving){
		this.moving = moving;
	}
	
	/**
	 *  //TODO
	 * @return
	 */
	public boolean getMovingUpwards(){
		return this.movingUpwards;
	}
	
	/**
	 *  //TODO
	 * @return
	 */
	public boolean getFalling(){
		return this.falling;
	}
	
	/**
	 *  //TODO
	 * @param verticalMovement
	 */
	public void setMovingUpwards(boolean upwardsMovement){
		this.movingUpwards = upwardsMovement;
	}
	
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	
	private double horizontalVelocity;
	private double verticalVelocity;
	
	/**
	 *  //TODO
	 * @return
	 */
	@Basic
	public double getHorizontalVelocity() {
		return this.horizontalVelocity;
	}
	
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
	 *  //TODO
	 * @return
	 */
	public double getVerticalVelocity(){
		return this.verticalVelocity;
	}
	
	/**
	 *  //TODO
	 * @param velocity
	 */
	public void setVerticalVelocity(double velocity){
		this.verticalVelocity = velocity;
	}
	
	/**
	 *  //TODO
	 * @return
	 */
	public double getVerticalAcceleration(){
		return this.verticalAcceleration;
	}
	
	private double verticalAcceleration = -10;
	
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
		return ((Util.fuzzyGreaterThanOrEqualTo(Math.abs(velocity),horizontalInitialVelocity) && Util.fuzzyLessThanOrEqualTo(Math.abs(velocity),horizontalMaximumVelocity)) ||(velocity == 0));	
	}
	
	
	/**
	 *	//TODO
	 * @throws IllegalXPositionException
	 * comment PJ: of wel is de snelheid positief of negatief, dus kunt ge da in 1 regel gieten om uw nieuwe pos te berekenen.
	 * commet Joren: Ge zijt wel vergeten rekening te houden me de acceleratie, dus moet da toch.
	 */
	public void changeHorizontalPosition(double timeInterval) throws IllegalXPositionException {
		double newPosition = this.getXPosition() + 100 * this.getHorizontalVelocity()*timeInterval 
				+ 50 * this.getHorizontalAcceleration()*timeInterval*timeInterval;
			if (!isValidXPosition(newPosition))
					throw new IllegalXPositionException(newPosition);
			this.setXPosition(newPosition );
	}
	 /**
	  *  //TODO
	  *  
	  * @param timeInterval
	  */
	public void changeVerticalPosition(double timeInterval) throws IllegalYPositionException{
		if (this.getMovingUpwards() == true){
			double newPosition = this.getYPosition() 
					+ 100 * this.getVerticalVelocity() * timeInterval;
			if (newPosition > 768){
				newPosition = 768;
			}
			if (!isValidYPosition(newPosition))
				throw new IllegalYPositionException(newPosition);
			this.setYPosition(newPosition);
		}
		if (this.getFalling() == true) {
			double newPosition = this.getYPosition() 
					+ 50 * this.getVerticalAcceleration() * timeInterval * timeInterval;
			if (newPosition < 0) {
				newPosition = 0;
				this.setFalling(false);
			}
			if (!isValidYPosition(newPosition))
				throw new IllegalYPositionException(newPosition);
			this.setYPosition(newPosition);
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
	public double getHorizontalAcceleration() {
		return this.horizontalAcceleration;	
	}
			
	/**
	 * Comment aan PJ : Dit moet weg, alles met acceleratie: totaal!	
	 * 
	 * @param 	horizontalAcceleration
	 * 			The acceleration to check.
	 * @return	... //TODO
	 * 			| result == (horizontalAcceleration >= 0)
	 */
	public static boolean isValidHorizontalAcceleration(double horizontalAcceleration) {
		return horizontalAcceleration >= 0;
	}
	
	private double horizontalAcceleration;
	
	/**
	 *  //TODO Dit moet eigenlijk weg en vervangen worden door trug direction te gebruiken
	 * @param horizontalAcceleration
	 * 
	 * @post	...
	 * 			| if (horizontalAcceleration >= 0)
	 * 			|	then new.horizontalAcceleration = horizontalAcceleration
	 * @post	...
	 * 			| if (horizontalAcceleration < 0)
	 * 			|	then new.horizontalAcceleration = 0
	 */
	public void setHorizontalAcceleration(double horizontalAcceleration) {
		if (horizontalAcceleration < 0)
			this.horizontalAcceleration = horizontalAcceleration;
		else
			this.horizontalAcceleration = 0;
	}
	
	/**
	 * Initializes movement to the given direction.
	 * 
	 * Comment to PJ: Mazub klasse moet ni toegevoegd worden. 
	 * In de tests worden deze methoden opgeroepen
	 * uit de facade klasse, niet de mazub klasse. :)
	 * Dus deze twee right & left moeten ook nog in één startMove worden gegoten
	 * eigenlijk volgens de opdracht.
	 * 
	 //TODO
	  * ik heb de ganse move structuur aangepast, ik moet nu nog enkele de verticale beweging nog tegoei afwerken.
	  * ook moet nog overal documentatie bij.
	 */
	public void startMoveRight(){
		this.setHorizontalAcceleration(Math.abs(this.getHorizontalAcceleration()));
		this.setHorizontalVelocity(this.getInitialVelocity(), this.getMaximumHorizontalVelocity());
		this.setMoving(true);	
	}
	
	public void startMoveLeft(){
		this.setHorizontalAcceleration(-Math.abs(this.getHorizontalAcceleration()));
		this.setHorizontalVelocity(-this.getInitialVelocity(), this.getMaximumHorizontalVelocity());
		this.setMoving(true);	
	}
	
	//TODO
	public void endMove() {
		this.setHorizontalVelocity(0,this.getMaximumHorizontalVelocity());
		this.setMoving(false);
	}
	
	public final int jumpingSpeed = 8;
	
	/**
	 * Defensief?
	 */
	public void startJump(){
		this.setVerticalVelocity(jumpingSpeed);
		this.setMovingUpwards(true);
	}
	
	/**
	 * Defensief?
	 */
	public void endJump(){
		this.setVerticalVelocity(0);
		this.setMovingUpwards(false);
		this.setFalling(true);
	}
	
	public void advanceTime(Mazub mazub,double timeInterval){
		if (this.getMoving() == true){
			changeHorizontalPosition(timeInterval);
			if ((this.getHorizontalVelocity() < 0) && moving){
				this.setHorizontalVelocity(this.getHorizontalVelocity() - this.getHorizontalAcceleration()*timeInterval, this.getMaximumHorizontalVelocity());
			}
			if ((this.getHorizontalVelocity() > 0) && moving){
				this.setHorizontalVelocity(this.getHorizontalVelocity() + this.getHorizontalAcceleration()*timeInterval, this.getMaximumHorizontalVelocity());
			}
		}
		
		if ((this.getMovingUpwards() == true) | (this.getFalling() == true)){
			double Yposition = this.getYPosition();
			changeVerticalPosition(timeInterval);
			double newYposition = this.getYPosition();
			if ((newYposition < Yposition) && (newYposition != 0 )){
				this.setVerticalVelocity(this.getVerticalVelocity() - verticalAcceleration*timeInterval);
			}
		}
	}
	
}
