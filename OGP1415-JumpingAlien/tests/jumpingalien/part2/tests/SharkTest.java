package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;
import jumpingalien.model.LivingCreatures;
import jumpingalien.model.Shark;
import jumpingalien.model.World;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.Test;

public class SharkTest {

	private Sprite[] defaultSprites;
	private World defaultWorld;
	private Shark shark;
	
	@Before
	public void setUp() {
		this.defaultWorld = new World(10,10,10,10,10,9,9);
		this.defaultSprites = spriteArrayForSize(2,2);
		this.shark = new Shark(0, 0, 0, 0, defaultWorld,defaultSprites,100);
	}

	@Test
	public void extendedConstructor$LegalCase() {	
		Shark shark = new Shark(5, 10, 1, 20,defaultWorld,defaultSprites,100);
		assertTrue(shark.getXPosition() == 5);
		assertTrue(shark.getYPosition() == 10);
		assertTrue(shark.getHorizontalVelocity() == 1);
		assertTrue(shark.getVerticalVelocity() ==20);
		assertTrue(shark.getHorizontalAcceleration()==1.5);
		assertTrue(shark.getVerticalAcceleration() == 0);
		assertTrue(shark.getWorld() == defaultWorld);
		assertTrue(shark.getHP() == 100);
		assertArrayEquals(defaultSprites, shark.getSpriteArray());
	}
	
	@Test
	public void extendedConstructor$OutOfBoundXPosition() {
		//Positions too big (out of bounds of gameworld)
		Shark shark = new Shark(300,0,0,0,defaultWorld,defaultSprites,100);
		assertTrue(shark.getHP() == 0);
		assertTrue(shark.getWorld() == null);
		assertTrue(shark.isDead());
	}
	
	@Test
	public void extendedConstructor$OutOfBoundYPosition() {
		//Positions too big (out of bounds of gameworld)
		Shark shark = new Shark(0,6805,0,0,defaultWorld,defaultSprites,100);
		assertTrue(shark.getHP() == 0);
		assertTrue(shark.getWorld() == null);
		assertTrue(shark.isDead());
	}
	
	@Test
	public void testCorrectInitialisedWithPositionAndSprites() {
				
		Shark shark = new Shark(0,0,defaultSprites);
		assertTrue(shark.getWorld() ==null);
		assertTrue(shark.getHP() == 100);
		assertTrue(shark.getHorizontalVelocity() == 0);
		assertTrue(shark.getVerticalVelocity() == 0);
	}
	
	@Test
	public void testCorrectInitialisedWithOnlySprites() {
				
		Shark shark = new Shark(defaultSprites);
		assertTrue(shark.getXPosition()==0);
		assertTrue(shark.getYPosition()==0);
	}
	
	@Test
	public void testTerminteWhenTerminatedWithinTheBounds(){
		assertTrue(shark.isAlive());
		shark.addHP(-100);
		//Terminates when HP goes below 0 HP
		assertTrue(shark.getHP() == 0);
		assertTrue(shark.isDying());
		for (int i=0; i<5; i += 1){
			shark.advanceTime(0.199);
		}
		assertTrue(shark.isDead());
		assertTrue(shark.getWorld() == null);		
	}
	
	@Test(expected=IllegalStateException.class)
	public void testTerminteWhenAliveAndWithinTheBounds() throws Exception{
		assertTrue(shark.isAlive());
		shark.terminate();
		assertTrue(shark.isAlive());
	}
	
	@Test
	public void testTerminteWhenOutsideTheBounds(){
		shark.setPosition(0, 666);
		//when going out of bound the slime is immediately removed.
		assertTrue(shark.isDead());
		assertTrue(shark.getWorld() == null);
	}
	
	//TODO
	
	
	//@Test(expected = IllegalXPositionException.class)
	//public void testSetXposition$IllegalCase() throws Exception{
	//	shark alien = new shark(spriteArrayForSize(2, 2));
	//	alien.setXPosition(-5);
	//	assertTrue(alien.getXPosition() == -5);
	//}
	@Test
	public void testSetXposition$LegalCase() throws Exception{
		Shark shark = new Shark(0,0,spriteArrayForSize(2, 2));
		shark.setXPosition(5);
		assertTrue(shark.getXPosition() == 5);
	}


	//@Test(expected = IllegalXPositionException.class)
	//public void testSetXposition$IllegalCase() throws Exception{
	//		shark alien = new shark(spriteArrayForSize(2, 2));
	//		alien.setXPosition(-5);
	//		assertTrue(alien.getXPosition() == -5);
	//}	
	@Test
	public void testSetYposition$LegalCase() throws Exception{
		Shark shark = new Shark(0,0,spriteArrayForSize(2, 2));
		shark.setYPosition(5);
		assertTrue(shark.getYPosition() == 5);
	}
	
	@Test
	public void testSetHorizontalVelocity() {
		Shark creature = new Shark(0,0,spriteArrayForSize(2, 2));
		double velocity;
		
		creature.setHorizontalVelocity(2);
		velocity = creature.getHorizontalVelocity();
		assertEquals(2.0,velocity,Util.DEFAULT_EPSILON);
		
		creature.setHorizontalVelocity(5);
		velocity = creature.getHorizontalVelocity();
		assertEquals(4,velocity,Util.DEFAULT_EPSILON);
		
		creature.setHorizontalVelocity(-5);
		velocity = creature.getHorizontalVelocity();
		assertEquals(0.0,velocity,Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void testSetVerticalVelocity() {
		Shark creature = new Shark(0,0,spriteArrayForSize(2, 2));
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
		assertTrue(shark.hasAsWorld(defaultWorld));
	}
	
	@Test
	public void hasAsWorld_False(){
		shark.setWorld(null);
		assertFalse(shark.hasAsWorld(defaultWorld));
	}
	
	@Test
	public void canHaveAsWorld_True(){
		assertTrue(shark.canHaveAsWorld(defaultWorld));
	}
	
	@Test
	public void canHaveAsWorld_False(){
		assertFalse(shark.canHaveAsWorld(null));
	}
	
	@Test
	public void isValidSpriteArray_True() {
		assertTrue(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 2),shark));
	}
	
	@Test
	public void isValidSpriteArray_False() {
		assertFalse(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 0),shark));
		assertFalse(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 13),shark));
		assertFalse(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 8),shark));
	}
	
	@Test
	public void getCurrentSprite$Right(){
		shark.setDirection(1);
		assertTrue(shark.getCurrentSprite() == shark.getSpriteArray()[1]);
		}
	
	@Test
	public void getCurrentSprite$Left(){
		shark.setDirection(-1);
		assertTrue(shark.getCurrentSprite() == shark.getSpriteArray()[0]);
		}
	
	@Test
	public void setHP_NormalCase(){
		shark.setHP(shark.getMinHP());
		assertTrue(shark.getHP() == shark.getMinHP());
		shark.setHP(shark.getMaxHP()/2);
		assertTrue(shark.getHP() == shark.getMaxHP()/2);
		shark.setHP(shark.getMaxHP());
		assertTrue(shark.getHP() == shark.getMaxHP());
	}
	
	@Test
	public void setHP_SpecialCase(){
		shark.setHP(shark.getMinHP() - 5);
		assertTrue(shark.getHP() == shark.getMinHP());

		shark.setHP(shark.getMaxHP()+5);
		assertTrue(shark.getHP() == shark.getMaxHP());
	}
	
	@Test
	public void isDead_True(){
		shark.setState(shark.State.DEAD);
		assertTrue(shark.isDead());
	}
	
	@Test
	public void isDead_False(){
		shark.setState(shark.State.ALIVE);
		assertFalse(shark.isDead());
		shark.setState(shark.State.DYING);
		assertFalse(shark.isDead());
	}
	
	@Test
	public void isDying_True(){
		shark.setState(shark.State.DYING);
		assertTrue(shark.isDying());
	}
	
	@Test
	public void isDying_False(){
		shark.setState(shark.State.ALIVE);
		assertFalse(shark.isDying());
		shark.setState(shark.State.DEAD);
		assertFalse(shark.isDying());
	}
	
	@Test
	public void isAlive_True(){
		shark.setState(shark.State.Alive);
		assertTrue(shark.isAlive());
	}
	
	@Test
	public void isAlive_False(){
		shark.setState(shark.State.DYING);
		assertFalse(shark.isAlive());
		shark.setState(shark.State.DEAD);
		assertFalse(shark.isAlive());
	}

	@Test
	public void changeHorizontalPosition(){
		Shark shark = new Shark(0,0,spriteArrayForSize(2, 2));
		shark.setHorizontalVelocity(5);
		shark.setHorizontalAcceleration(2);
		shark.setDirection(1);
		shark.changeHorizontalPosition(0.1);
		assertEquals(shark.getXPosition(),51,Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void changeVerticalPosition(){
		Shark shark = new Shark(0,0,spriteArrayForSize(2, 2));
		shark.setVerticalVelocity(5);
		shark.setVerticalAcceleration(2);
		shark.setDirection(1);
		shark.changeVerticalPosition(0.1);
		assertEquals(shark.getXPosition(),251,Util.DEFAULT_EPSILON);
		
		Shark shark1 = new Shark(0,0,spriteArrayForSize(2, 2));
		shark1.setVerticalVelocity(-5);
		shark1.setVerticalAcceleration(-2);
		shark1.setDirection(1);
		shark1.changeVerticalPosition(0.1);
		assertEquals(shark.getXPosition(),149,Util.DEFAULT_EPSILON);
	}
	
	@Test 
	public void startJump_whileNotMovingVertical(){
		shark.startJump(7,-2);
		assertEquals(shark.getVerticalVelocity(),7,Util.DEFAULT_EPSILON);
		assertEquals(shark.getVerticalAcceleration(),-2,Util.DEFAULT_EPSILON);
		assertTrue(shark.getMovingVertical());
	}
	
	@Test 
	public void startJump_WhileMovingVertical(){
		Shark shark = new Shark(0,0,spriteArrayForSize(2, 2));
		shark.setVerticalVelocity(-5);
		shark.setVerticalAcceleration(-2);	
		shark.setMovingVertical(true);
		shark.startJump(5,9);
		assertEquals(shark.getVerticalVelocity(),-5,Util.DEFAULT_EPSILON);
		assertEquals(shark.getVerticalAcceleration(),-2,Util.DEFAULT_EPSILON);
		assertTrue(shark.getMovingVertical());
	}
	
	@Test
	public void endJump_ableToEndJump(){
		Shark shark = new Shark(0,0,spriteArrayForSize(2, 2));
		shark.setVerticalVelocity(5);
		shark.setVerticalAcceleration(-2);
		shark.endJump();
		assertEquals(shark.getVerticalVelocity(),0,Util.DEFAULT_EPSILON);
		assertEquals(shark.getVerticalAcceleration(),-2,Util.DEFAULT_EPSILON);
	}
	
	@Test 
	public void endJump_unableToEnd(){
		Shark shark = new Shark(0,0,spriteArrayForSize(2, 2));
		shark.setVerticalVelocity(-5);
		shark.setVerticalAcceleration(-2);	
		shark.endJump();
		assertEquals(shark.getVerticalVelocity(),-5,Util.DEFAULT_EPSILON);
		assertEquals(shark.getVerticalAcceleration(),-2,Util.DEFAULT_EPSILON);
	}

	
	
}
