package jumpingalien.model;
import jumpingalien.util.*;
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
			boolean moving,boolean movingVertical,Sprite[] spriteArray,String direction,boolean ducking) {
		assert isValidInitialVelocity(horizontalInitialVelocity, horizontalMaximumVelocity);
		assert isValidMaximumVelocity(horizontalMaximumVelocity, horizontalInitialVelocity);
		assert isValidHorizontalAcceleration(horizontalAcceleration);
		assert isValidSpriteArray(spriteArray);
		this.horizontalInitialVelocity = horizontalInitialVelocity;
		this.setMaximumHorizontalVelocity(horizontalMaximumVelocity);
		this.horizontalAcceleration = horizontalAcceleration;
		this.moving = false;
		this.movingVertical = false;
		this.horizontalVelocity = 0;
		this.verticalVelocity = 0;
		this.spriteArray = spriteArray;
	}
	
	public Mazub(int positionLeftX, int positionBottomY, Sprite[] spriteArray) {
		this.horizontalInitialVelocity = 1;
		this.horizontalVelocity = 0;
		this.verticalVelocity = 0;
		this.setMaximumHorizontalVelocity(3);
		this.moving = false;
		this.movingVertical = false;
		this.setDirection("right");
		this.ducking = false;
		this.setXPosition(positionLeftX);
		this.setYPosition(positionBottomY);
		this.spriteArray = spriteArray;
	}
	
	/**
	 * Return the X position of this mazub.
	 * 	The position is the actual position of the left X pixel of
	 * 	the Mazub character in the 1024*768 gameworld.
	 */
	@Basic
	public double getXPosition() {
		return this.positionLeftX;
	}
	
	/**
	 * Return the Y position of this mazub.
	 * 	The position is the actual position of the bottom Y pixl of
	 * 	the Mazub character in the 1024*768 gameworld.
	 */
	@Basic
	public double getYPosition () {
		return this.positionBottomY;
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
	private boolean movingVertical;
	
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
	public boolean getMovingVertical(){
		return this.movingVertical;
	}
	
	/**
	 *  //TODO
	 * @param verticalMovement
	 */
	public void setMovingVertical(boolean verticalMovement){
		this.movingVertical = verticalMovement;
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
		return ((Util.fuzzyGreaterThanOrEqualTo(velocity,horizontalInitialVelocity) && 
				Util.fuzzyLessThanOrEqualTo(velocity,horizontalMaximumVelocity)) ||(velocity == 0));	
	}
	
	
	/**
	 *	//TODO
	 * @throws IllegalXPositionException
	 * comment PJ: of wel is de snelheid positief of negatief, dus kunt ge da in 1 regel gieten om uw nieuwe pos te berekenen.
	 * commet Joren: Ge zijt wel vergeten rekening te houden me de acceleratie, dus moet da toch.
	 */
	public void changeHorizontalPosition(double timeInterval) throws IllegalXPositionException {
		double newPosition = this.getXPosition() + this.getDirection() * (100 * this.getHorizontalVelocity()*timeInterval 
				+ 50 * this.getHorizontalAcceleration()*timeInterval*timeInterval);
			if (!isValidXPosition(newPosition))
					throw new IllegalXPositionException(newPosition);
			this.setXPosition(newPosition);
	}
	 /**
	  *  //TODO
	  *  comment Joren: Geldt eigenlijk voor beide horizontaal & verticaal.
	  *  Zit het defensieve niet al in setX/YPosition? Of moet da hier dan ook nog expliciet.
	  * @param timeInterval
	  */
	public void changeVerticalPosition(double timeInterval) throws IllegalYPositionException{
		if (this.getMovingVertical() == true){
			double newPosition = this.getYPosition() 
					+ 100 * this.getVerticalVelocity() * timeInterval
					+ 50 * this.getVerticalAcceleration() * timeInterval * timeInterval;
			if (Util.fuzzyLessThanOrEqualTo(newPosition, 0, timeInterval * 1e-4)) {
				this.endJump();
				this.setMovingVertical(false);
			}
			if (!isValidYPosition(newPosition))
				throw new IllegalYPositionException(newPosition);
			this.setYPosition(newPosition);
		}
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
		return (Util.fuzzyGreaterThanOrEqualTo(horizontalInitialVelocity, 1)) && (horizontalInitialVelocity < horizontalMaximumVelocity);
	}
	
	private final double horizontalInitialVelocity;


	
		
	/**
	 * //TODO
	 * @return
	 */
	@Basic
	public double getMaximumHorizontalVelocity() {
		return this.horizontalMaximumVelocity;
	}
	
	public void setMaximumHorizontalVelocity(double maximumHorizontalVelocity) {
		this.horizontalMaximumVelocity = maximumHorizontalVelocity;
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
	
	private double horizontalMaximumVelocity;
	
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
	public void startMove(){
		this.setHorizontalAcceleration(this.getHorizontalAcceleration());
		this.setHorizontalVelocity(this.getInitialVelocity(), this.getMaximumHorizontalVelocity());
		this.setMoving(true);	
		this.setRunTimer(0);
	}
	
	//TODO
	public void endMove() {
		this.setHorizontalVelocity(0,this.getMaximumHorizontalVelocity());
		this.setMoving(false);
		this.setRunTimer(0);
	}
	
	private final int jumpingSpeed = 8;
	
	/**
	 * Defensief?
	 */
	public void startJump(){
		this.setVerticalVelocity(jumpingSpeed);
		this.setMovingVertical(true);
	}
	
	/**
	 * Defensief?
	 */
	public void endJump(){
		this.setVerticalVelocity(0);
	}
	
	private boolean ducking;
	
	public void startDuck() {
		this.setDucking(true);
	}
	
	public void endDuck() {
		this.setDucking(false);
	}
	
	/**
	 * Dit moe wel nog defensief
	 *  &&. Moeten de changepositions voor of na de setvelocity's?
	 * @param timeInterval
	 */
	public void advanceTime(double timeInterval){
		double vHmax = this.getMaximumHorizontalVelocity();
		if (getDucking() == true) {
			this.setMaximumHorizontalVelocity(1);
		}
		if (this.getMoving() == true){
			changeHorizontalPosition(timeInterval);
			this.setHorizontalVelocity(this.getHorizontalVelocity() + this.getHorizontalAcceleration()*timeInterval, this.getMaximumHorizontalVelocity());
		}
		
		if (this.getMovingVertical() == true) {
			double Yposition = this.getYPosition();
			changeVerticalPosition(timeInterval);
			double newYposition = this.getYPosition();
			if ((newYposition < Yposition) && (!Util.fuzzyLessThanOrEqualTo(newYposition,0,timeInterval*1e-4))){
				this.setVerticalVelocity(this.getVerticalVelocity() + this.getVerticalAcceleration()*timeInterval);
			}
		}
		this.setMaximumHorizontalVelocity(vHmax);
		this.setRunTimer(this.getRunTimer() + timeInterval);
	}
	
	private double runTimer = 0;
	
	/**
	 * 
	 * @param spriteArray
	 * @return
	 */
	public static boolean isValidSpriteArray(Sprite[] spriteArray) {
		int length = spriteArray.length;
		return (length >= 10) && (length % 2 == 0);
	}
	
	private Sprite[] spriteArray;
	
	/**
	 *	//TODO die return moet deftig, kan zijn da we methode moete aanpassen.
	 * @return
	 */
	public Sprite getCurrentSprite() {
		int Sprite;
		if (this.getMoving() == false) {
			Sprite = this.getNotMovingSprite();
 		}
		else {
			Sprite = this.getMovingSprite();
		}
		return this.spriteArray[Sprite];
	}
	
	public int getNotMovingSprite() {
		int Sprite;
		if (Util.fuzzyGreaterThanOrEqualTo(this.getRunTimer(),1)) {
			if (this.getDucking() == true)
				Sprite = 1;
			else
				Sprite = 0;
		}
		if (this.getDirection() == 1)
			if (this.getDucking() == true)
				Sprite = 6;
			else
				Sprite = 2;
		else
			if (this.getDucking() == true)
				Sprite = 7;
			else
				Sprite = 3;
		return Sprite;
	}
	
	public int getMovingSprite() {
		int Sprite = 0;
		if (this.getMovingVertical() == true) {
			if (this.getDirection() == 1)
				Sprite = 4;
			else
				Sprite = 5;
		}
		if (this.getDucking() == true) {
			if (this.getDirection() == 1)
				Sprite = 6;
			else
				Sprite = 7;
		}
		else {
			int m = ((this.spriteArray.length - 8) / 2) - 1;
			if (this.getDirection() == 1) {
				if ((this.previousSprite < 8) || (this.previousSprite > 8 + m))
					Sprite = 8;
			}
			if (this.getDirection() == -1){
				if ((this.previousSprite < 9+m)|| (this.previousSprite > 9+ 2*m))
					Sprite = 9 + m;
			}
			if (Util.fuzzyGreaterThanOrEqualTo(runTimer,0.075))
				Sprite = this.runningSprite(m);
		}	
		return Sprite;
	}
	
	/**
	 * 		
	 * @return
	 */
	public int runningSprite(int m) {
		int Sprite;
		if (this.getDirection() == 1) {
			if (this.previousSprite == 8 + m)
				Sprite = 8;
			else
				Sprite = this.previousSprite + 1;
		}
		else {
			if (this.previousSprite == 9 + 2*m)
				Sprite = 9 + m;
			else
				Sprite = this.previousSprite + 1;
		}
		this.setRunTimer(0); 
		return Sprite;			
	}
	
	public int[] getSize(Sprite sprite) {
		int[] size = new int[1];
		size[0] = sprite.getHeight();
		size[1] = sprite.getWidth();
		return size;
	}
	
	private int previousSprite = 0;

	public double getRunTimer() {
		return this.runTimer;
	}

	public void setRunTimer(double runTimer) {
		this.runTimer = runTimer;
	}
	
	public int getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		if (direction == "left")
			this.direction = -1;
		else
			this.direction = 1;
	}

	public boolean getDucking() {
		return this.ducking;
	}

	public void setDucking(boolean ducking) {
		this.ducking = ducking;
	}

	private int direction;
	
	
}
