package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;
import jumpingalien.model.LivingCreatures;
import jumpingalien.model.Plant;
import jumpingalien.model.World;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.Test;

public class PlantTest {

	private Sprite[] defaultSprites;
	private World defaultWorld;
	private Plant plant;
	
	@Before
	public void setUp() {
		this.defaultWorld = new World(10,10,10,10,10,9,9);
		this.defaultSprites = spriteArrayForSize(2,2);
		this.plant = new Plant(5,5,0.5,defaultSprites,defaultWorld,1);
	}
	
	@Test
	public void extendedConstructor$LegalCase() {	
		Plant plant = new Plant(1, 2, 0.3, defaultSprites, defaultWorld,100);
		assertTrue(plant.getXPosition() == 1);
		assertTrue(plant.getYPosition() == 2);
		assertTrue(plant.getHorizontalVelocity() == 0.3);
		assertTrue(plant.getMaximumHorizontalVelocity() == 0.5);
		assertTrue(plant.getWorld() == defaultWorld);
		assertTrue(plant.getHP() == 1);
		assertArrayEquals(defaultSprites, plant.getSpriteArray());
	}
	
	@Test
	public void extendedConstructor$OutOfBoundXPosition() {
		//Positions too big (out of bounds of gameworld)
		Plant plant = new Plant(300,0,0,defaultSprites,defaultWorld,1);
		assertTrue(plant.getHP() == 0);
		assertTrue(plant.getWorld() == null);
		assertTrue(plant.isDead());
	}
	
	@Test
	public void extendedConstructor$OutOfBoundYPosition() {
		//Positions too big (out of bounds of gameworld)
		Plant plant = new Plant(0,6805,0,defaultSprites,defaultWorld,1);
		assertTrue(plant.getHP() == 0);
		assertTrue(plant.getWorld() == null);
		assertTrue(plant.isDead());
	}
	
	@Test
	public void testCorrectInitialisedWithPositionAndSpritesGiven() {
				
		Plant plant = new Plant(0,0,defaultSprites);
		assertTrue(plant.getWorld() ==null);
		assertTrue(plant.getHP() == 1);
		assertTrue(plant.getHorizontalVelocity() == 0.5);
	}
	
	@Test
	public void testCorrectInitialisedWithOnlySpritesGiven() {
		Plant plant = new Plant(defaultSprites);
		assertTrue(plant.getXPosition() ==0);
		assertTrue(plant.getYPosition()==0);
	}

	@Test
	public void testTerminteWhenTerminatedWithinTheBounds(){
		assertTrue(plant.isAlive());
		plant.addHP(-100);
		//Terminates when HP goes below 0 HP
		assertTrue(plant.getHP() == 0);
		assertTrue(plant.isDying());
		for (int i=0; i<5; i += 1){
			plant.advanceTime(0.199);
		}
		assertTrue(plant.isDead());
		assertTrue(plant.getWorld() == null);		
	}
	
	@Test(expected=IllegalStateException.class)
	public void testTerminteWhenAliveAndWithinTheBounds() throws Exception{
		assertTrue(plant.isAlive());
		plant.terminate();
		assertTrue(plant.isAlive());
	}
	
	@Test
	public void testTerminteWhenOutsideTheBounds(){
		plant.setPosition(0, 666);
		//when going out of bound the slime is immediately removed.
		assertTrue(plant.isDead());
		assertTrue(plant.getWorld() == null);
	}
	//TODO advancetime
	
	//@Test(expected = IllegalXPositionException.class)
	//public void testSetXposition$IllegalCase() throws Exception{
	//	plant alien = new plant(spriteArrayForSize(2, 2));
	//	alien.setXPosition(-5);
	//	assertTrue(alien.getXPosition() == -5);
	//}
	@Test
	public void testSetXposition$LegalCase() throws Exception{
		Plant plant = new Plant(spriteArrayForSize(2, 2));
		plant.setXPosition(5);
		assertTrue(plant.getXPosition() == 5);
	}


	//@Test(expected = IllegalXPositionException.class)
	//public void testSetXposition$IllegalCase() throws Exception{
	//		plant alien = new plant(spriteArrayForSize(2, 2));
	//		alien.setXPosition(-5);
	//		assertTrue(alien.getXPosition() == -5);
	//}	
	@Test
	public void testSetYposition$LegalCase() throws Exception{
		Plant plant = new Plant(spriteArrayForSize(2, 2));
		plant.setYPosition(5);
		assertTrue(plant.getYPosition() == 5);
	}
	
	@Test
	public void testSetHorizontalVelocity() {
		Plant creature = new Plant(spriteArrayForSize(2, 2));
		double velocity;
		
		creature.setHorizontalVelocity(2);
		velocity = creature.getHorizontalVelocity();
		assertEquals(0.5,velocity,Util.DEFAULT_EPSILON);

		creature.setHorizontalVelocity(-5);
		velocity = creature.getHorizontalVelocity();
		assertEquals(0.0,velocity,Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void testSetVerticalVelocity() {
		Plant creature = new Plant(spriteArrayForSize(2, 2));
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
		assertTrue(plant.hasAsWorld(defaultWorld));
	}
	
	@Test
	public void hasAsWorld_False(){
		plant.setWorld(null);
		assertFalse(plant.hasAsWorld(defaultWorld));
	}
	
	@Test
	public void canHaveAsWorld_True(){
		assertTrue(plant.canHaveAsWorld(defaultWorld));
	}
	
	@Test
	public void canHaveAsWorld_False(){
		assertFalse(plant.canHaveAsWorld(null));
	}
	
	@Test
	public void isValidSpriteArray_True() {
		assertTrue(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2,2),plant));
	}
	
	@Test
	public void isValidSpriteArray_False() {
		assertFalse(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 0),plant));
		assertFalse(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 13),plant));
		assertFalse(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 8),plant));
	}
	
	@Test
	public void getCurrentSprite$Right(){
		plant.setDirection(1);
		assertTrue(plant.getCurrentSprite() == plant.getSpriteArray()[1]);
		}
	
	@Test
	public void getCurrentSprite$Left(){
		plant.setDirection(-1);
		assertTrue(plant.getCurrentSprite() == plant.getSpriteArray()[0]);
		}
	
	@Test
	public void setHP_NormalCase(){
		plant.setHP(plant.getMinHP());
		assertTrue(plant.getHP() == plant.getMinHP());
		plant.setHP(plant.getMaxHP()/2);
		assertTrue(plant.getHP() == plant.getMaxHP()/2);
		plant.setHP(plant.getMaxHP());
		assertTrue(plant.getHP() == plant.getMaxHP());
	}
	
	@Test
	public void setHP_SpecialCase(){
		plant.setHP(plant.getMinHP()-5);
		assertTrue(plant.getHP() == plant.getMinHP());

		plant.setHP(plant.getMaxHP()+5);
		assertTrue(plant.getHP() == plant.getMaxHP());
	}
	
	@Test
	public void isDead_True(){
		plant.setState(Plant.State.DEAD);
		assertTrue(plant.isDead());
	}
	
	@Test
	public void isDead_False(){
		plant.setState(Plant.State.ALIVE);
		assertFalse(plant.isDead());
		plant.setState(Plant.State.DYING);
		assertFalse(plant.isDead());
	}
	
	@Test
	public void isDying_True(){
		plant.setState(Plant.State.DYING);
		assertTrue(plant.isDying());
	}
	
	@Test
	public void isDying_False(){
		plant.setState(Plant.State.ALIVE);
		assertFalse(plant.isDying());
		plant.setState(Plant.State.DEAD);
		assertFalse(plant.isDying());
	}
	
	@Test
	public void isAlive_True(){
		plant.setState(Plant.State.Alive);
		assertTrue(plant.isAlive());
	}
	
	@Test
	public void isAlive_False(){
		plant.setState(Plant.State.DYING);
		assertFalse(plant.isAlive());
		plant.setState(Plant.State.DEAD);
		assertFalse(plant.isAlive());
	}
	
	@Test
	public void changeHorizontalPosition(){
		Plant plant = new Plant(0,0,spriteArrayForSize(2, 2));
		plant.setHorizontalVelocity(5);
		plant.setHorizontalAcceleration(2);
		plant.setDirection(1);
		plant.changeHorizontalPosition(0.1);
		assertEquals(plant.getXPosition(),51,Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void changeVerticalPosition(){
		Plant plant = new Plant(0,0,spriteArrayForSize(2, 2));
		plant.setVerticalVelocity(5);
		plant.setVerticalAcceleration(2);
		plant.setDirection(1);
		plant.changeVerticalPosition(0.1);
		assertEquals(plant.getXPosition(),251,Util.DEFAULT_EPSILON);
		
		Plant plant1 = new Plant(0,0,spriteArrayForSize(2, 2));
		plant1.setVerticalVelocity(-5);
		plant1.setVerticalAcceleration(-2);
		plant1.setDirection(1);
		plant1.changeVerticalPosition(0.1);
		assertEquals(plant1.getXPosition(),149,Util.DEFAULT_EPSILON);
	}
	
	@Test 
	public void startJump_whileNotMovingVertical(){
		plant.startJump(7,-2);
		assertEquals(plant.getVerticalVelocity(),7,Util.DEFAULT_EPSILON);
		assertEquals(plant.getVerticalAcceleration(),-2,Util.DEFAULT_EPSILON);
		assertTrue(plant.getMovingVertical());
	}
	
	@Test 
	public void startJump_WhileMovingVertical(){
		Plant plant = new Plant(0,0,spriteArrayForSize(2, 2));
		plant.setVerticalVelocity(-5);
		plant.setVerticalAcceleration(-2);	
		plant.setMovingVertical(true);
		plant.startJump(5,9);
		assertEquals(plant.getVerticalVelocity(),-5,Util.DEFAULT_EPSILON);
		assertEquals(plant.getVerticalAcceleration(),-2,Util.DEFAULT_EPSILON);
		assertTrue(plant.getMovingVertical());
	}
	
	@Test
	public void endJump_ableToEndJump(){
		Plant plant = new Plant(0,0,spriteArrayForSize(2, 2));
		plant.setVerticalVelocity(5);
		plant.setVerticalAcceleration(-2);
		plant.endJump();
		assertEquals(plant.getVerticalVelocity(),0,Util.DEFAULT_EPSILON);
		assertEquals(plant.getVerticalAcceleration(),-2,Util.DEFAULT_EPSILON);
	}
	
	@Test 
	public void endJump_unableToEnd(){
		Plant plant = new Plant(0,0,spriteArrayForSize(2, 2));
		plant.setVerticalVelocity(-5);
		plant.setVerticalAcceleration(-2);	
		plant.endJump();
		assertEquals(plant.getVerticalVelocity(),-5,Util.DEFAULT_EPSILON);
		assertEquals(plant.getVerticalAcceleration(),-2,Util.DEFAULT_EPSILON);
	}
}



