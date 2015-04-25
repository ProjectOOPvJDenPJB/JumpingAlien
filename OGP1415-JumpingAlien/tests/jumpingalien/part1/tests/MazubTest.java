package jumpingalien.part1.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;

import java.util.Arrays;

import jumpingalien.model.Direction;
import jumpingalien.model.IllegalSizeException;
//import jumpingalien.model.IllegalXPositionException;
//import jumpingalien.model.IllegalYPositionException;
import jumpingalien.model.Mazub;
import jumpingalien.model.World;
import jumpingalien.tests.util.TestUtils;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.*;

/**
 * Test file for the Mazub class. This test file doesn't include methods implemented in the Facade class which are just
 * merely called in the Facade class without further adjustments.
 * These tests are all self-written.
 *
 */
public class MazubTest {
	
	private Sprite[] defaultSprites;
	
	@Before
	public void setUp() {
		this.defaultSprites = spriteArrayForSize(2,2);
	}
	
	@Test
	public void extendedConstructor$LegalCase() {		
		Mazub alien = new Mazub(0, 0, defaultSprites, 5, 3, null, 100);
		assertTrue(0 == alien.getXPosition());
		assertTrue(0 == alien.getYPosition());
		assertTrue(5 == alien.getMaximumHorizontalVelocity());
		assertTrue(3 == alien.getInitialHorizontalVelocity());
		assertArrayEquals(defaultSprites, alien.getSpriteArray());
	}
	
	public void extendedConstructor$NegativePositions() {
		Mazub alien = new Mazub(-6,-800,defaultSprites,5,3, null, 100);
		assertTrue(0 == alien.getXPosition());
		assertTrue(0 == alien.getYPosition());
		assertTrue(5 == alien.getMaximumHorizontalVelocity());
		assertTrue(3 == alien.getInitialHorizontalVelocity());
		assertArrayEquals(defaultSprites, alien.getSpriteArray());
	}
	
	public void extendedConstructor$OutOfBoundsPositions() {
		//Positions too big (out of bounds of gameworld)
		Mazub alien = new Mazub(2000,6805,defaultSprites,5,3, null, 100);
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
	public void testSetXposition$LegalCase() throws Exception{
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		alien.setXPosition(5);
		assertTrue(alien.getXPosition() == 5);
	}
	
//	@Test(expected = IllegalXPositionException.class)
//	public void testSetXposition$IllegalCase() throws Exception{
//		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
//		alien.setXPosition(-5);
//		assertTrue(alien.getXPosition() == -5);
//	}
	
	@Test
	public void testSetYposition$LegalCase() throws Exception{
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		alien.setYPosition(5);
		assertTrue(alien.getYPosition() == 5);
	}
	
//	@Test(expected = IllegalYPositionException.class)
//	public void testSetYposition$IllegalCase() throws Exception{
//		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
//		alien.setYPosition(-5);
//		assertTrue(alien.getYPosition() == -5);
//	}
	
	@Test
	public void isValidHorizontalAcceleration_True() {
		assertTrue(Mazub.isValidHorizontalAcceleration(0));
		assertTrue(Mazub.isValidHorizontalAcceleration(800));
		assertTrue(Mazub.isValidHorizontalAcceleration(27));
	}
	
	@Test
	public void isValidHorizontalAcceleration_False() {
		assertFalse(Mazub.isValidHorizontalAcceleration(-0.1));
		assertFalse(Mazub.isValidHorizontalAcceleration(-9));
		assertFalse(Mazub.isValidHorizontalAcceleration(-531));
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
	public void setVerticalAcceleration$LegalCase() {
		Mazub alien = new Mazub(defaultSprites);
		
		alien.setVerticalAcceleration(0);
		assertEquals(0,alien.getVerticalAcceleration(),Util.DEFAULT_EPSILON);
		
		alien.setVerticalAcceleration(-10);
		assertEquals(-10,alien.getVerticalAcceleration(),Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void setVerticalAcceleration$IllegalCase() {
		Mazub alien = new Mazub(defaultSprites);
		
		alien.setVerticalAcceleration(100);
		assertEquals(0, alien.getVerticalAcceleration(),Util.DEFAULT_EPSILON);
		
		alien.setVerticalAcceleration(-506);
		assertEquals(0, alien.getVerticalAcceleration(),Util.DEFAULT_EPSILON);
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
		alien.setDirection(Direction.LEFT);
		assertTrue(alien.getDirection() == Direction.LEFT);
	}
	
	
	@Test
	public void setDirectionRight(){
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		alien.setDirection(Direction.RIGHT);
		assertTrue(alien.getDirection() == Direction.RIGHT);
	}
	
	@Test
	public void isValidSpriteArray_True() {
		assertTrue(Mazub.isValidSpriteArray(spriteArrayForSize(2, 2)));
		assertTrue(Mazub.isValidSpriteArray(spriteArrayForSize(2, 2, 10)));
		assertTrue(Mazub.isValidSpriteArray(spriteArrayForSize(2, 2, 12)));
		assertTrue(Mazub.isValidSpriteArray(spriteArrayForSize(2, 2, 32)));
	}
	
	@Test
	public void isValidSpriteArray_False() {
		assertFalse(Mazub.isValidSpriteArray(spriteArrayForSize(2, 2, 0)));
		assertFalse(Mazub.isValidSpriteArray(spriteArrayForSize(2, 2, 13)));
		assertFalse(Mazub.isValidSpriteArray(spriteArrayForSize(2, 2, 8)));
	}
	
	@Test
	public void changeHorizontalPosition_validPositionAtNormalSpeed(){
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		alien.setDirection(Direction.RIGHT);
		alien.setHorizontalVelocity(1);
		alien.setHorizontalAcceleration(1);
		
		alien.changeHorizontalPosition(1);
		// newPosition = 0 + (100 * 1*1) + 50 * 1*1^2) = 150;
		assertTrue(alien.getXPosition() == 150);
	}
	
	@Test
	public void changeHorizontalPosition_validPositionAtMaxSpeed(){
		World world = new World(70, 50, 50, 1024, 768, 20, 20);
		Mazub alien = new Mazub(0, 0, spriteArrayForSize(2, 2),3, 1, world, 100);
		alien.setDirection(Direction.RIGHT);
		alien.setHorizontalVelocity(3);
		alien.setHorizontalAcceleration(1);
		
		alien.changeHorizontalPosition(1);
		System.out.println(alien.getXPosition());
		System.out.println(alien.getHorizontalAcceleration());
		// newPosition = 0 + (100 * 3*1) + 0) = 300;
		assertTrue(alien.getXPosition() == 300);
		assertTrue(alien.getHorizontalAcceleration() == 0);
	}
	
	@Test
	public void changeHorizontalPosition_inValidPosition(){
		Mazub alien1 = new Mazub(1024,0,spriteArrayForSize(2, 2));
		alien1.setDirection(Direction.RIGHT);
		alien1.setHorizontalVelocity(1);
		alien1.setHorizontalAcceleration(1);
		
		alien1.changeHorizontalPosition(1);
		assertTrue(alien1.getXPosition() == 1024);
		
		
		Mazub alien2 = new Mazub(0,0,spriteArrayForSize(2, 2));
		alien2.setDirection(Direction.LEFT);
		alien2.setHorizontalVelocity(1);
		alien2.setHorizontalAcceleration(1);
		
		alien2.changeHorizontalPosition(1);
		assertTrue(alien2.getXPosition() == 0);
	}
	
	
	@Test
	public void changeVerticalPosition_validPositionAndMovingVertically(){
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		alien.setMovingVertical(true);
		alien.setVerticalVelocity(8);
		alien.setVerticalAcceleration(-10);
		
		alien.changeVerticalPosition(1);
		// newPosition = 0 + (100 * 8 * 1) - 50 * 10*1^2) = 300;
		assertTrue(alien.getYPosition() == 300);
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
	
	@Test
	public void isValidTimeInterval_True() {
		assertTrue(Mazub.isValidTimeInterval(0));
		assertTrue(Mazub.isValidTimeInterval(0.2));
		assertTrue(Mazub.isValidTimeInterval(0.12));
	}
	
	@Test
	public void isValidTimeInterval_False() {
		assertFalse(Mazub.isValidTimeInterval(-0.1));
		assertFalse(Mazub.isValidTimeInterval(-3));
		assertFalse(Mazub.isValidTimeInterval(0.21));
		assertFalse(Mazub.isValidTimeInterval(6));
	}

	
	// TODO Testen hervormen naar nieuwe vorm getsize.
//	@Test
//	public void getSize$LegalCase() throws Exception {
//		Sprite sprite1 = new Sprite("Sprite test1",2,2);
//		Sprite sprite2 = new Sprite("Sprite test2", 0, 0);
//		Sprite sprite3 = new Sprite("Sprite test3",50,50);
//		
//		assertEquals(Arrays.toString(TestUtils.intArray(2,2)), Arrays.toString(Mazub.getSize(sprite1)));
//		assertEquals(Arrays.toString(TestUtils.intArray(0,0)), Arrays.toString(Mazub.getSize(sprite2)));
//		assertEquals(Arrays.toString(TestUtils.intArray(50,50)), Arrays.toString(Mazub.getSize(sprite3)));
//	}
//	
//	@Test(expected=IllegalSizeException.class)
//	public void getSize$IllegalCase() throws Exception {
//		Sprite sprite1 = new Sprite("Sprite test1",-1,-1);
//		Sprite sprite2 = new Sprite("Sprite test2",-5,80);
//		Sprite sprite3 = new Sprite("Sprite test3",20,-8);
//		
//		Mazub.getSize(sprite1);
//		Mazub.getSize(sprite2);
//		Mazub.getSize(sprite3);
//	}
	
	@Test
	public void isValidSize_True() {
		assertTrue(Mazub.isValidSize(TestUtils.intArray(0,0)));
		assertTrue(Mazub.isValidSize(TestUtils.intArray(8,8)));
		assertTrue(Mazub.isValidSize(TestUtils.intArray(37,87)));
	}
	
	@Test
	public void isValidSize_False() {
		assertFalse(Mazub.isValidSize(TestUtils.intArray(-1,-1)));
		assertFalse(Mazub.isValidSize(TestUtils.intArray(-8,42)));
		assertFalse(Mazub.isValidSize(TestUtils.intArray(42,-68)));
	}
	
}
