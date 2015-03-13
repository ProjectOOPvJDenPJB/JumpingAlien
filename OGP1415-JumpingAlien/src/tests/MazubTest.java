package tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;
import jumpingalien.model.Mazub;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.*;

public class MazubTest {
	
	private Sprite[] defaultSprites;
	
	@Before
	public void setUp() {
		this.defaultSprites = spriteArrayForSize(2,2);
	}
	
	@Test
	public void extendedConstructor$LegalCase() {		
		Mazub alien = new Mazub(0, 0, defaultSprites, 5, 3);
		assertTrue(0 == alien.getXPosition());
		assertTrue(0 == alien.getYPosition());
		assertTrue(5 == alien.getMaximumHorizontalVelocity());
		assertTrue(3 == alien.getInitialHorizontalVelocity());
		assertArrayEquals(defaultSprites, alien.getSpriteArray());
	}
	
	public void extendedConstructor$NegativePositions() {
		Mazub alien = new Mazub(-6,-800,defaultSprites,5,3);
		assertTrue(0 == alien.getXPosition());
		assertTrue(0 == alien.getYPosition());
		assertTrue(5 == alien.getMaximumHorizontalVelocity());
		assertTrue(3 == alien.getInitialHorizontalVelocity());
		assertArrayEquals(defaultSprites, alien.getSpriteArray());
	}
	
	public void extendedConstructor$OutOfBoundsPositions() {
		//Positions too big (out of bounds of gameworld)
		Mazub alien = new Mazub(2000,6805,defaultSprites,5,3);
		assertTrue(1024 == alien.getXPosition());
		assertTrue(768 == alien.getYPosition());
		assertTrue(5 == alien.getMaximumHorizontalVelocity());
		assertTrue(3 == alien.getInitialHorizontalVelocity());
		assertArrayEquals(defaultSprites, alien.getSpriteArray());
	}
	
	@Test
	public void testCorrectInitialisedWithOnlySpriteArrayGiven() {
				
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		assertTrue(0 == alien.getXPosition());
		assertTrue(0 == alien.getYPosition());
		assertTrue(3 == alien.getMaximumHorizontalVelocity());
		assertTrue(1 == alien.getInitialHorizontalVelocity());
	}
	
	@Test
	public void setMovingVertical$LegalCase() throws Exception {
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		alien.setMovingVertical(true);
		assertTrue(alien.getMovingVertical());
	}
	
	@Test(expected=IllegalStateException.class)
	public void setMovingVertical$IllegalCase() throws Exception {
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		alien.setMovingVertical(false);
		assertFalse(alien.getMovingVertical());
	}
	
	
	
	@Test
	public void testSetHorizontalVelocity() {
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		double velocity;
		
		alien.setHorizontalVelocity(2);
		velocity = alien.getHorizontalVelocity();
		assertEquals(2.0,velocity,Util.DEFAULT_EPSILON);
		
		alien.setHorizontalVelocity(5);
		velocity = alien.getHorizontalVelocity();
		assertEquals(3.0,velocity,Util.DEFAULT_EPSILON);
		
		alien.setHorizontalVelocity(-5);
		velocity = alien.getHorizontalVelocity();
		assertEquals(0.0,velocity,Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void testSetVerticalVelocity() {
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		double velocity;
		
		alien.setVerticalVelocity(10);
		velocity = alien.getVerticalVelocity();
		//The maximum speed is limited by 8 [m/s]
		assertEquals(8,velocity,Util.DEFAULT_EPSILON);
		
		alien.setVerticalVelocity(5);
		velocity = alien.getVerticalVelocity();
		assertEquals(5,velocity,Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void isValidInitialVelocity_True(){
		assertTrue(Mazub.isValidInitialVelocity(3, 5));
	}
	
	
	@Test
	public void isValidInitialVelocity_False(){
		assertFalse(Mazub.isValidInitialVelocity(3, 2));
		assertFalse(Mazub.isValidInitialVelocity(0, 2));
	}
	
	@Test
	public void isValidMaximumHorizontalVelocity_True(){
		assertTrue(Mazub.isValidMaximumHorizontalVelocity(3, 2));
	}
	
	@Test
	public void isValidMaximumHorizontalVelocity_False(){
		assertFalse(Mazub.isValidMaximumHorizontalVelocity(2, 3));
	}
	
	@Test
	public void setDirectionLeft(){
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		alien.setDirection("left");
		assertTrue(alien.getDirection() == -1);
	}
	
	
	@Test
	public void setDirectionRight(){
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		alien.setDirection("right");
		assertTrue(alien.getDirection() == 1);
	}
	
	@Test
	public void changeHorizontalPosition_validPositionAtNormalSpeed(){
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		alien.setDirection("right");
		alien.setHorizontalVelocity(1);
		alien.setHorizontalAcceleration(1);
		
		alien.changeHorizontalPosition(1);
		// newPosition = 0 + (100 * 1*1) + 50 * 1*1^2) = 150;
		assertTrue(alien.getXPosition() == 150);
	}
	
	@Test
	public void changeHorizontalPosition_validPositionAtMaxSpeed(){
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		alien.setDirection("right");
		alien.setHorizontalVelocity(3);
		alien.setHorizontalAcceleration(1);
		
		alien.changeHorizontalPosition(1);
		// newPosition = 0 + (100 * 3*1) + 0) = 300;
		assertTrue(alien.getXPosition() == 300);
		assertTrue(alien.getHorizontalAcceleration() == 0);
	}
	
	@Test
	public void changeHorizontalPosition_inValidPosition(){
		Mazub alien1 = new Mazub(1024,0,spriteArrayForSize(2, 2));
		alien1.setDirection("right");
		alien1.setHorizontalVelocity(1);
		alien1.setHorizontalAcceleration(1);
		
		alien1.changeHorizontalPosition(1);
		assertTrue(alien1.getXPosition() == 1024);
		
		
		Mazub alien2 = new Mazub(0,0,spriteArrayForSize(2, 2));
		alien2.setDirection("left");
		alien2.setHorizontalVelocity(1);
		alien2.setHorizontalAcceleration(1);
		
		alien2.changeHorizontalPosition(1);
		assertTrue(alien2.getXPosition() == 0);
	}
	
	
	@Test
	public void changeVerticalPosition_validPositionAndMovingVertically(){
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		alien.setMovingVertical(true);
		alien.setVerticalVelocity(1);
		alien.setVerticalAcceleration(-1);
		
		alien.changeVerticalPosition(1);
		// newPosition = 0 + (100 * 1*1) - 50 * 1*1^2) = 50;
		assertTrue(alien.getYPosition() == 50);
	}
	
	@Test
	public void changeVerticalPosition_MovingOverTheUpperBorder(){
		Mazub alien = new Mazub(0,768,spriteArrayForSize(2, 2));
		alien.setMovingVertical(true); 
		alien.setVerticalVelocity(1);
		alien.setVerticalAcceleration(1);
		
		alien.changeVerticalPosition(1);
		assertTrue(alien.getYPosition() == 768);
	}
	
	@Test
	public void changeVerticalPosition_MovingOverTheBottomBorder(){
		Mazub alien = new Mazub(0,0,spriteArrayForSize(2, 2));
		alien.setMovingVertical(true); 
		alien.setVerticalVelocity(-1);
		alien.setVerticalAcceleration(-1);
		
		alien.changeVerticalPosition(1);
		assertTrue(alien.getYPosition() == 0);
	}
	
	@Test
	public void changeVerticalPosition_validPositionButNotMoving(){
		Mazub alien = new Mazub(0,500,spriteArrayForSize(2, 2));
		alien.setVerticalVelocity(-10);
		alien.setVerticalAcceleration(-10);
		
		alien.changeVerticalPosition(1);
		assertTrue(alien.getYPosition() == 500);
	}
	
}
