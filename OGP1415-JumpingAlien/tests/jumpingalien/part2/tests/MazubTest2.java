package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;

import java.lang.Thread.State;

import jumpingalien.model.Direction;
import jumpingalien.model.LivingCreatures;
import jumpingalien.model.Mazub;
import jumpingalien.model.World;
import jumpingalien.tests.util.TestUtils;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.Test;

public class MazubTest2 {

private Sprite[] defaultSprites;
private World world;
private Mazub mazub;
	
	@Before
	public void setUp() {
		this.defaultSprites = spriteArrayForSize(2,2);
		this.world = new World(10,20,15,100,70,180,10);
		this.mazub = new Mazub(5,10,defaultSprites,5,3,world,80);
	}
	
	@Test
	public void extendedConstructor$LegalCase() {		
		Mazub alien = new Mazub(1, 2, defaultSprites, 5, 3, world, 100);
		assertTrue(1 == alien.getXPosition());
		assertTrue(2 == alien.getYPosition());
		assertTrue(5 == alien.getMaximumHorizontalVelocity());
		assertTrue(3 == alien.getInitialHorizontalVelocity());
		assertTrue(world == alien.getWorld());
		assertTrue(100 == alien.getHP());
		assertArrayEquals(defaultSprites, alien.getSpriteArray());
	}
	
	public void extendedConstructor$NegativePositions() {
		Mazub alien = new Mazub(-6,-800,defaultSprites,5,3, world, 80);
		assertTrue(0 == alien.getXPosition());
		assertTrue(0 == alien.getYPosition());
		assertTrue(5 == alien.getMaximumHorizontalVelocity());
		assertTrue(3 == alien.getInitialHorizontalVelocity());
		assertTrue(world == alien.getWorld());
		assertTrue(80 == alien.getHP());
		assertArrayEquals(defaultSprites, alien.getSpriteArray());
	}
	
	public void extendedConstructor$OutOfBoundsPositions() {
		//Positions too big (out of bounds of gameworld)
		Mazub alien = new Mazub(2000,6805,defaultSprites,5,3, world, 80);
		assertTrue(1024 == alien.getXPosition());
		assertTrue(768 == alien.getYPosition());
		assertTrue(5 == alien.getMaximumHorizontalVelocity());
		assertTrue(3 == alien.getInitialHorizontalVelocity());
		assertTrue(world == alien.getWorld());
		assertTrue(80 == alien.getHP());
		assertArrayEquals(defaultSprites, alien.getSpriteArray());
	}
	
	@Test
	public void testCorrectInitialisedWithOnlySpriteArrayAndPositionGiven() {
				
		Mazub alien = new Mazub(5,7,defaultSprites);
		assertTrue(5 == alien.getXPosition());
		assertTrue(7 == alien.getYPosition());
		assertTrue(3 == alien.getMaximumHorizontalVelocity());
		assertTrue(1 == alien.getInitialHorizontalVelocity());
		assertTrue(null == alien.getWorld());
		assertTrue(100 == alien.getHP());
		assertArrayEquals(defaultSprites, alien.getSpriteArray());
	}
	
	
	@Test
	public void testCorrectInitialisedWithOnlySpriteArrayGiven() {
				
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		assertTrue(0 == alien.getXPosition());
		assertTrue(0 == alien.getYPosition());
	}
	
	@Test
	public void setMovingVertical$LegalCase() throws Exception {
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		alien.setMovingVertical(true);
		assertTrue(alien.getMovingVertical());
	}
	
	@Test 
	public void canStandUp$True(){
		
	}
	
	@Test
	public void startMoveTest(){
		Mazub alien = new Mazub(defaultSprites);
		alien.startMove(1);
		assertTrue(alien.getHorizontalAcceleration() == 1);
		assertTrue(alien.getHorizontalVelocity() == 1);
		assertTrue(alien.getMoving());		
	}
	
	@Test
	public void EndMoveTest(){
		Mazub alien = new Mazub(defaultSprites);
		alien.startMove(1);
		alien.endMove(0);	
		assertTrue(alien.getHorizontalAcceleration() == 0);
		assertTrue(alien.getHorizontalVelocity() == 0);
		assertFalse(alien.getMoving());		
	}
	
	@Test
	public void getCurrentSriteArray_WhileNotMoving_andNotMovedLastSeconde(){
		Mazub alien = new Mazub(spriteArrayForSize(10,10));
		assertEquals(alien.getCurrentSprite(), alien.getSpriteArray()[0]);
		alien.startDuck();
		assertEquals(alien.getCurrentSprite(), alien.getSpriteArray()[1]);
	}
	
	@Test
	public void getCurrentSriteArray_WhileNotMoving_MovedLastSecondeToTheRight(){
		Mazub alien = new Mazub(spriteArrayForSize(10,10));
		alien.setDirection(1);
		alien.startMove(1);
		alien.endMove(0);
		assertEquals(alien.getCurrentSprite(), alien.getSpriteArray()[2]);
		alien.startDuck();
		assertEquals(alien.getCurrentSprite(), alien.getSpriteArray()[6]);
	}
	
	@Test
	public void getCurrentSriteArray_WhileNotMoving_MovedLastSecondeToTheLeft(){
		Mazub alien = new Mazub(spriteArrayForSize(10,10));
		alien.setDirection(-1);
		alien.startMove(1);
		alien.endMove(0);
		assertEquals(alien.getCurrentSprite(), alien.getSpriteArray()[3]);
		alien.startDuck();
		assertEquals(alien.getCurrentSprite(), alien.getSpriteArray()[7]);
	}
	
	@Test
	public void getCurrentSriteArray_WhileMovingAndDucking(){
		Mazub alien = new Mazub(spriteArrayForSize(10,10));
		alien.setDirection(1);
		alien.startMove(1);
		alien.startDuck();
		assertEquals(alien.getCurrentSprite(), alien.getSpriteArray()[6]);
		alien.setDirection(-1);
		assertEquals(alien.getCurrentSprite(), alien.getSpriteArray()[7]);
	}
	
	@Test
	public void getCurrentSriteArray_WhileMovingAndJumping(){
		Mazub alien = new Mazub(spriteArrayForSize(10,10));
		alien.setDirection(1);
		alien.startMove(1);
		alien.startJump(10,-10);
		assertEquals(alien.getCurrentSprite(), alien.getSpriteArray()[4]);
		alien.setDirection(-1);
		assertEquals(alien.getCurrentSprite(), alien.getSpriteArray()[5]);
	}
	
	@Test
	public void getRunningSprite(){
		//TODO
	}
	
	@Test
	public void isValidSpriteArrayMazub_True() {
		assertTrue(Mazub.isValidSpriteArray(spriteArrayForSize(2, 2)));
		assertTrue(Mazub.isValidSpriteArray(spriteArrayForSize(2, 2, 10)));
		assertTrue(Mazub.isValidSpriteArray(spriteArrayForSize(2, 2, 12)));
		assertTrue(Mazub.isValidSpriteArray(spriteArrayForSize(2, 2, 32)));
	}
	
	@Test
	public void isValidSpriteArrayMazub_False() {
		assertFalse(Mazub.isValidSpriteArray(spriteArrayForSize(2, 2, 0)));
		assertFalse(Mazub.isValidSpriteArray(spriteArrayForSize(2, 2, 13)));
		assertFalse(Mazub.isValidSpriteArray(spriteArrayForSize(2, 2, 8)));
	}
	
	@Test
	public void testTerminteWhenTerminatedWithinTheBounds(){
		Mazub alien = new Mazub(1, 2, defaultSprites, 5, 3, world, 100);
		assertTrue(alien.isAlive());
		alien.addHP(-100);
		//Terminates when HP goes below 0 HP
		assertTrue(alien.getHP() == 0);
		assertTrue(alien.isDying());
		for (int i=0; i<5; i += 1){
			alien.advanceTime(0.199);
		}
		assertTrue(alien.isDead());
		assertTrue(alien.getWorld() == null);		
	}
	
	@Test(expected=IllegalStateException.class)
	public void testTerminteWhenAliveAndWithinTheBounds() throws Exception{
		Mazub alien = new Mazub(1, 2, defaultSprites, 5, 3, world, 100);
		world.setMazub(alien);
		assertTrue(alien.isAlive());
		alien.terminate();
		assertTrue(alien.isAlive());
	}
	
	@Test
	public void testTerminteWhenOutsideTheBounds(){
		Mazub alien = new Mazub(1, 2, defaultSprites, 5, 3, world, 100);
		alien.setPosition(0, 666);
		//when going out of bound the slime is immediately removed.
		assertTrue(alien.isDead());
		assertTrue(alien.getWorld() == null);
	}

	//TODO advanceTime
	
	//@Test(expected = IllegalXPositionException.class)
	//public void testSetXposition$IllegalCase() throws Exception{
	//	Mazub alien = new Mazub(spriteArrayForSize(2, 2));
	//	alien.setXPosition(-5);
	//	assertTrue(alien.getXPosition() == -5);
	//}
	@Test
	public void testSetXposition$LegalCase() throws Exception{
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		alien.setXPosition(5);
		assertTrue(alien.getXPosition() == 5);
	}


	//@Test(expected = IllegalXPositionException.class)
	//public void testSetXposition$IllegalCase() throws Exception{
	//		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
	//		alien.setXPosition(-5);
	//		assertTrue(alien.getXPosition() == -5);
	//}	
	@Test
	public void testSetYposition$LegalCase() throws Exception{
		Mazub alien = new Mazub(spriteArrayForSize(2, 2));
		alien.setYPosition(5);
		assertTrue(alien.getYPosition() == 5);
	}
	
	@Test
	public void testSetHorizontalVelocity() {
		Mazub creature = new Mazub(spriteArrayForSize(2, 2));
		double velocity;
		
		creature.setHorizontalVelocity(2);
		velocity = creature.getHorizontalVelocity();
		assertEquals(2.0,velocity,Util.DEFAULT_EPSILON);
		
		creature.setHorizontalVelocity(5);
		velocity = creature.getHorizontalVelocity();
		assertEquals(3.0,velocity,Util.DEFAULT_EPSILON);
		
		creature.setHorizontalVelocity(-5);
		velocity = creature.getHorizontalVelocity();
		assertEquals(0.0,velocity,Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void testSetVerticalVelocity() {
		Mazub creature = new Mazub(spriteArrayForSize(2, 2));
		double velocity;
		
		creature.setVerticalVelocity(10);
		velocity = creature.getVerticalVelocity();
		//The maximum speed is limited by 8 [m/s]
		assertEquals(10,velocity,Util.DEFAULT_EPSILON);
		
		creature.setVerticalVelocity(5);
		velocity = creature.getVerticalVelocity();
		assertEquals(5,velocity,Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void hasAsWorld_True(){
		assertTrue(mazub.hasAsWorld(world));
	}
	
	@Test
	public void hasAsWorld_False(){
		mazub.setWorld(null);
		assertFalse(mazub.hasAsWorld(world));
	}
	
	@Test
	public void canHaveAsWorld_True(){
		assertTrue(mazub.canHaveAsWorld(world));
	}
	
	@Test
	public void canHaveAsWorld_False(){
		assertFalse(mazub.canHaveAsWorld(null));
	}
	
	@Test
	public void isValidSpriteArray_True() {
		assertTrue(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2),mazub));
		assertTrue(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 10),mazub));
		assertTrue(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 12),mazub));
		assertTrue(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 32),mazub));
	}
	
	@Test
	public void isValidSpriteArray_False() {
		assertFalse(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 0),mazub));
		assertFalse(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 13),mazub));
		assertFalse(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 8),mazub));
	}
	
	@Test
	public void setHP_NormalCase(){
		mazub.setHP(mazub.getMinHP());
		assertTrue(mazub.getHP() == mazub.getMinHP());
		mazub.setHP(mazub.getMaxHP()/2);
		assertTrue(mazub.getHP() == mazub.getMaxHP()/2);
		mazub.setHP(mazub.getMaxHP());
		assertTrue(mazub.getHP() == mazub.getMaxHP());
	}
	
	@Test
	public void setHP_SpecialCase(){
		mazub.setHP(mazub.getMinHP() - 5);
		assertEquals(mazub.getHP(), mazub.getMinHP());

		mazub.setHP(mazub.getMaxHP()+5);
		assertTrue(mazub.getHP() == mazub.getMaxHP());
	}
	
	@Test
	public void isDead_True(){
		mazub.setState(Mazub.State.DEAD);
		assertTrue(mazub.isDead());
	}
	
	@Test
	public void isDead_False(){
		mazub.setState(Mazub.State.ALIVE);
		assertFalse(mazub.isDead());
		mazub.setState(Mazub.State.DYING);
		assertFalse(mazub.isDead());
	}
	
	@Test
	public void isDying_True(){
		mazub.setState(Mazub.State.DYING);
		assertTrue(mazub.isDying());
	}
	
	@Test
	public void isDying_False(){
		mazub.setState(Mazub.State.ALIVE);
		assertFalse(mazub.isDying());
		mazub.setState(Mazub.State.DEAD);
		assertFalse(mazub.isDying());
	}
	
	@Test
	public void isAlive_True(){
		mazub.setState(Mazub.State.ALIVE);
		assertTrue(mazub.isAlive());
	}
	
	@Test
	public void isAlive_False(){
		mazub.setState(State.Mazub.DYING);
		assertFalse(mazub.isAlive());
		mazub.setState(State.Mazub.DEAD);
		assertFalse(mazub.isAlive());
	}
	
	@Test
	public void changeHorizontalPosition(){
		Mazub mazub = new Mazub(0,0,spriteArrayForSize(2, 2));
		mazub.setHorizontalVelocity(5);
		mazub.setHorizontalAcceleration(2);
		mazub.setDirection(1);
		mazub.changeHorizontalPosition(0.1);
		assertEquals(mazub.getXPosition(),51,Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void changeVerticalPosition(){
		Mazub mazub = new Mazub(0,0,spriteArrayForSize(2, 2));
		mazub.setVerticalVelocity(5);
		mazub.setVerticalAcceleration(2);
		mazub.setDirection(1);
		mazub.changeVerticalPosition(0.1);
		assertEquals(mazub.getXPosition(),251,Util.DEFAULT_EPSILON);
		
		Mazub mazub = new Mazub(0,0,spriteArrayForSize(2, 2));
		mazub.setVerticalVelocity(-5);
		mazub.setVerticalAcceleration(-2);
		mazub.setDirection(1);
		mazub.changeVerticalPosition(0.1);
		assertEquals(mazub.getXPosition(),149,Util.DEFAULT_EPSILON);
	}
	
	@Test 
	public void startJump_whileNotMovingVertical(){
		mazub.startJump(7,-2);
		assertEquals(mazub.getVerticalVelocity(),7,Util.DEFAULT_EPSILON);
		assertEquals(mazub.getVerticalAcceleration(),-2,Util.DEFAULT_EPSILON);
		assertTrue(mazub.getMovingVertical());
	}
	
	@Test 
	public void startJump_WhileMovingVertical(){
		Mazub mazub = new Mazub(0,0,spriteArrayForSize(2, 2));
		mazub.setVerticalVelocity(-5);
		mazub.setVerticalAcceleration(-2);	
		mazub.setMovingVertical(true);
		mazub.startJump(5,9);
		assertEquals(mazub.getVerticalVelocity(),-5,Util.DEFAULT_EPSILON);
		assertEquals(mazub.getVerticalAcceleration(),-2,Util.DEFAULT_EPSILON);
		assertTrue(mazub.getMovingVertical());
	}
	
	@Test
	public void endJump_ableToEndJump(){
		Mazub mazub = new Mazub(0,0,spriteArrayForSize(2, 2));
		mazub.setVerticalVelocity(5);
		mazub.setVerticalAcceleration(-2);
		mazub.endJump();
		assertEquals(mazub.getVerticalVelocity(),0,Util.DEFAULT_EPSILON);
		assertEquals(mazub.getVerticalAcceleration(),-2,Util.DEFAULT_EPSILON);
	}
	
	@Test 
	public void endJump_unableToEnd(){
		Mazub mazub = new Mazub(0,0,spriteArrayForSize(2, 2));
		mazub.setVerticalVelocity(-5);
		mazub.setVerticalAcceleration(-2);	
		mazub.endJump();
		assertEquals(mazub.getVerticalVelocity(),-5,Util.DEFAULT_EPSILON);
		assertEquals(mazub.getVerticalAcceleration(),-2,Util.DEFAULT_EPSILON);
	}
}
