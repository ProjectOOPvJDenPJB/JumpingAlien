package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;
import jumpingalien.model.Direction;
import jumpingalien.model.LivingCreatures;
import jumpingalien.model.Mazub;
import jumpingalien.model.World;
import jumpingalien.tests.util.TestUtils;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.Test;

public class LivingCreaturesTest {

private Sprite[] defaultSprites;
private World world;
	



	@Before
	public void setUp() {
		this.defaultSprites = spriteArrayForSize(2,2);
		this.world = new World(10,20,15,100,70,180,10);
	}


	//@Test(expected = IllegalXPositionException.class)
	//public void testSetXposition$IllegalCase() throws Exception{
	//	Piemels alien = new Piemels(spriteArrayForSize(2, 2));
	//	alien.setXPosition(-5);
	//	assertTrue(alien.getXPosition() == -5);
	//}
	@Test
	public void testSetXposition$LegalCase() throws Exception{
		Piemels alien = new Piemels(spriteArrayForSize(2, 2));
		alien.setXPosition(5);
		assertTrue(alien.getXPosition() == 5);
	}


	//@Test(expected = IllegalXPositionException.class)
	//public void testSetXposition$IllegalCase() throws Exception{
	//		Piemels alien = new Piemels(spriteArrayForSize(2, 2));
	//		alien.setXPosition(-5);
	//		assertTrue(alien.getXPosition() == -5);
	//}	
	@Test
	public void testSetYposition$LegalCase() throws Exception{
		Piemels alien = new Piemels(spriteArrayForSize(2, 2));
		alien.setYPosition(5);
		assertTrue(alien.getYPosition() == 5);
	}
	
	@Test
	public void testSetHorizontalVelocity() {
		Piemels creature = new Piemels(spriteArrayForSize(2, 2));
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
		Piemels creature = new Piemels(spriteArrayForSize(2, 2));
		double velocity;
		
		creature.setVerticalVelocity(10);
		velocity = creature.getVerticalVelocity();
		//The maximum speed is limited by 8 [m/s]
		assertEquals(8,velocity,Util.DEFAULT_EPSILON);
		
		creature.setVerticalVelocity(5);
		velocity = creature.getVerticalVelocity();
		assertEquals(5,velocity,Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void hasAsWorld_True(){
		assertTrue(Piemels.hasAsWorld(world));
	}
	
	@Test
	public void hasAsWorld_False(){
		assertFalse(Piemels.hasAsWorld(world));
	}
	
	@Test
	public void canHaveAsWorld_True(){
		assertTrue(Piemels.canHaveAsWorld(world));
	}
	
	@Test
	public void canHaveAsWorld_False(){
		assertFalse(Piemels.canHaveAsWorld(null));
	}
	
	@Test
	public void isValidSpriteArray_True() {
		assertTrue(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2),Piemels));
		assertTrue(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 10),Piemels));
		assertTrue(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 12),Piemels));
		assertTrue(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 32),Piemels));
	}
	
	@Test
	public void isValidSpriteArray_False() {
		assertFalse(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 0),Piemels));
		assertFalse(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 13),Piemels));
		assertFalse(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 8),Piemels));
	}
	
	@Test
	public void getCurrentSprite$Right(){
		Piemels.setDirection(1);
		assertTrue(Piemels.getCurrentSprite() == 1);
		}
	
	@Test
	public void getCurrentSprite$Left(){
		Piemels.setDirection(-1);
		assertTrue(Piemels.getCurrentSprite() == 0);
		}
	
	@Test
	public void setHP_NormalCase(){
		Piemels.setHP(Piemels.getMinHP());
		assertTrue(Piemels.getHP() == Piemels.getMinHP());
		Piemels.setHP(Piemels.getMaxHP()/2);
		assertTrue(Piemels.getHP() == Piemels.getMaxHP()/2);
		Piemels.setHP(Piemels.getMaxHP());
		assertTrue(Piemels.getHP() == Piemels.getMaxHP());
	}
	
	@Test
	public void setHP_SpecialCase(){
		Piemels.setHP(Piemels.getMinHP() - 5);
		assertTrue(Piemels.getHP() == Piemels.getMinHP()-5);

		Piemels.setHP(Piemels.getMaxHP()+5);
		assertTrue(Piemels.getHP() == Piemels.getMaxHP()+5);
	}
	
	@Test
	public void applyMagmaDamageTest_NotRecentlyHit(){
		Piemels.setHP(100);
		Piemels.setTerrainTimer(1);
		Piemels.applyMagmaDamage(0.1);
		assertTrue(Piemels.getHP() == 50);
		assertTrue(Piemels.getTerrainTimer() == 0);
	}
	
	@Test
	public void applyMagmaDamageTest_RecentlyHit(){
		Piemels.setHP(100);
		Piemels.setTerrainTimer(0);
		Piemels.applyMagmaDamage(0.1);
		assertTrue(Piemels.getHP() == 100);
		assertTrue(Piemels.getTerrainTimer() == 0.1);
	}
	
	@Test
	public void applyWaterDamageTest_NotRecentlyHit(){
		Piemels.setHP(100);
		Piemels.setTerrainTimer(1);
		Piemels.applyMagmaDamage(0.1);
		assertTrue(Piemels.getHP() == 98);
		assertTrue(Piemels.getTerrainTimer() == 0);
	}
	
	@Test
	public void applyWaterDamageTest_RecentlyHit(){
		Piemels.setHP(100);
		Piemels.setTerrainTimer(0);
		Piemels.applyMagmaDamage(0.1);
		assertTrue(Piemels.getHP() == 100);
		assertTrue(Piemels.getTerrainTimer() == 0.1);
	}
	
	@Test
	public void applyAirDamageTest_NotRecentlyHit(){
		Piemels.setHP(100);
		Piemels.setTerrainTimer(1);
		Piemels.applyMagmaDamage(0.1);
		assertTrue(Piemels.getHP() == 94);
		assertTrue(Piemels.getTerrainTimer() == 0);
	}
	
	@Test
	public void applyAirDamageTest_RecentlyHit(){
		Piemels.setHP(100);
		Piemels.setTerrainTimer(0);
		Piemels.applyMagmaDamage(0.1);
		assertTrue(Piemels.getHP() == 100);
		assertTrue(Piemels.getTerrainTimer() == 0.1);
	}
	
	@Test
	public void isDead_True(){
		Piemels.setState(state.DEAD);
		assertTrue(Piemels.isDead());
	}
	
	@Test
	public void isDead_False(){
		Piemels.setState(state.ALIVE);
		assertFalse(Piemels.isDead());
		Piemels.setState(state.DYING);
		assertFalse(Piemels.isDead());
	}
	
	@Test
	public void isDying_True(){
		Piemels.setState(state.DYING);
		assertTrue(Piemels.isDying());
	}
	
	@Test
	public void isDying_False(){
		Piemels.setState(state.ALIVE);
		assertFalse(Piemels.isDying());
		Piemels.setState(state.DEAD);
		assertFalse(Piemels.isDying());
	}
	
	@Test
	public void isAlive_True(){
		Piemels.setState(state.Alive);
		assertTrue(Piemels.isAlive());
	}
	
	@Test
	public void isAlive_False(){
		Piemels.setState(state.DYING);
		assertFalse(Piemels.isAlive());
		Piemels.setState(state.DEAD);
		assertFalse(Piemels.isAlive());
	}
	
	//TODO Above is implemented
	@Test
	public void changeHorizontalPosition(){
		Piemel piemel = new Piemel(0,0,5,0,2,0,spriteArrayForSize(2, 2));
		piemel.setDirection(1);
		piemel.changeHorizontalPosition(0.1);
		assertEquals(piemel.getXPosition(),51,Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void changeVerticalPosition(){
		Piemels piemels = new Piemels(0,200,0,5,0,2,spriteArrayForSize(2, 2));
		piemels.setDirection(1);
		piemels.changeVerticalPosition(0.1);
		assertEquals(piemels.getXPosition(),251,Util.DEFAULT_EPSILON);
		
		Piemels piemels = new Piemels(0,200,0,-5,0,-2,spriteArrayForSize(2, 2));
		piemels.setDirection(1);
		piemels.changeVerticalPosition(0.1);
		assertEquals(piemels.getXPosition(),149,Util.DEFAULT_EPSILON);
	}
	
	@Test 
	public void startJump_whileNotMovingVertical(){
		piemels.startJump(7,-2);
		assertEquals(piemels.getVerticalVelocity() == 5);
		assertEquals(piemels.getVerticalAcceleration() == -2);
		assertTrue(piemels.getMovingVertical);
	}
	
	@Test 
	public void startJump_WhileMovingVertical(){
		Piemels piemels = new Piemels(0,200,0,-5,0,-2,spriteArrayForSize(2, 2));
		piemels.startJump(5,9);
		assertEquals(piemels.getVerticalVelocity() == -5);
		assertEquals(piemels.getVerticalAcceleration() == -2);
		assertTrue(piemels.getMovingVertical);
	}
	
	@Test
	public void endJump_ableToEndJump(){
		Piemels piemels = new Piemels(0,200,0,5,0,2,spriteArrayForSize(2, 2));
		piemels.endJump();
		assertEquals(piemels.getVerticalVelocity() == 0);
		assertEquals(piemels.getVerticalAcceleration() == 0);
	}
	
	@Test 
	public void endJump_unableToEnd(){
		Piemels piemels = new Piemels(0,200,0,-5,0,-2,spriteArrayForSize(2, 2));
		piemels.endJump();
		assertEquals(piemels.getVerticalVelocity() == -5);
		assertEquals(piemels.getVerticalAcceleration() == -2);
	}
	
	
	//TODO 
	@Test
	public void isValidInitialVelocity_True(){
		assertTrue(LivingCreatures.isValidInitialVelocity(3, 5));
	}
	
	@Test
	public void isValidInitialVelocity_False(){
		assertFalse(LivingCreatures.isValidInitialVelocity(3, 2));
		assertFalse(LivingCreatures.isValidInitialVelocity(0, 2));
	}	
	
	@Test
	public void isValidHorizontalAcceleration_True() {
		assertTrue(LivingCreatures.isValidHorizontalAcceleration(0));
		assertTrue(LivingCreatures.isValidHorizontalAcceleration(800));
		assertTrue(LivingCreatures.isValidHorizontalAcceleration(27));
	}
	
	@Test
	public void isValidHorizontalAcceleration_False() {
		assertFalse(LivingCreatures.isValidHorizontalAcceleration(-0.1));
		assertFalse(LivingCreatures.isValidHorizontalAcceleration(-9));
		assertFalse(LivingCreatures.isValidHorizontalAcceleration(-531));
	}
	
	@Test
	public void isValidVerticalAcceleration_True(){
		assertTrue(LivingCreatures.isValidHorizontalAcceleration(0));
		assertTrue(LivingCreatures.isValidHorizontalAcceleration(-10));
	}
	
	@Test
	public void isValidVerticalAcceleration_False(){
		assertTrue(LivingCreatures.isValidHorizontalAcceleration(5));
		assertTrue(LivingCreatures.isValidHorizontalAcceleration(-5));
	}
	
	@Test
	public void isValidTimerValue_True(){
		assertTrue(LivingCreatures.isValidTimerValue(5));
		assertTrue(LivingCreatures.isValidTimerValue(0));
	}
	
	@Test
	public void isValidTimerValue_False(){
		assertTrue(LivingCreatures.isValidTimerValue(-5));
	}	
	
	@Test
	public void isValidTimeInterval_True() {
		assertTrue(LivingCreatures.isValidTimeInterval(0));
		assertTrue(LivingCreatures.isValidTimeInterval(0.2));
		assertTrue(LivingCreatures.isValidTimeInterval(0.12));
	}
	
	@Test
	public void isValidTimeInterval_False() {
		assertFalse(LivingCreatures.isValidTimeInterval(-0.1));
		assertFalse(LivingCreatures.isValidTimeInterval(-3));
		assertFalse(LivingCreatures.isValidTimeInterval(0.21));
		assertFalse(LivingCreatures.isValidTimeInterval(6));
	}
	
	@Test
	public void isValidMaximumHorizontalVelocity_True(){
		assertTrue(LivingCreatures.isValidMaximumHorizontalVelocity(3, 2));
	}
	
	@Test
	public void isValidMaximumHorizontalVelocity_False(){
		assertFalse(LivingCreatures.isValidMaximumHorizontalVelocity(2, 3));
	}
	
	
	@Test
	public void isValidSize_True() {
		assertTrue(LivingCreatures.isValidSize(TestUtils.intArray(0,0)));
		assertTrue(LivingCreatures.isValidSize(TestUtils.intArray(8,8)));
		assertTrue(LivingCreatures.isValidSize(TestUtils.intArray(37,87)));
	}
	
	@Test
	public void isValidSize_False() {
		assertFalse(LivingCreatures.isValidSize(TestUtils.intArray(-1,-1)));
		assertFalse(LivingCreatures.isValidSize(TestUtils.intArray(-8,42)));
		assertFalse(LivingCreatures.isValidSize(TestUtils.intArray(42,-68)));
	}
	
	
//TODO
	
	
	@Test
	public void setDirectionLeft(){
		Piemels creature = new Piemels(spriteArrayForSize(2, 2));
		creature.setDirection(Direction.LEFT);
		assertTrue(creature.getDirection() == Direction.LEFT);
	}
	
	
	@Test
	public void setDirectionRight(){
		Piemels creature = new Piemels(spriteArrayForSize(2, 2));
		creature.setDirection(Direction.RIGHT);
		assertTrue(creature.getDirection() == Direction.RIGHT);
	}
	
	@Test
	public void setVerticalAcceleration$LegalCase() {
		Piemels creature = new Piemels(defaultSprites);
		
		creature.setVerticalAcceleration(0);
		assertEquals(0,creature.getVerticalAcceleration(),Util.DEFAULT_EPSILON);
		
		creature.setVerticalAcceleration(-10);
		assertEquals(-10,creature.getVerticalAcceleration(),Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void setVerticalAcceleration$IllegalCase() {
		Piemels creature = new Piemels(defaultSprites);
		
		creature.setVerticalAcceleration(100);
		assertEquals(0, creature.getVerticalAcceleration(),Util.DEFAULT_EPSILON);
		
		creature.setVerticalAcceleration(-506);
		assertEquals(0, creature.getVerticalAcceleration(),Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void changeHorizontalPosition_validPositionAtNormalSpeed(){
		Piemels creature = new Piemels(spriteArrayForSize(2, 2));
		creature.setDirection(Direction.RIGHT);
		creature.setHorizontalVelocity(1);
		creature.setHorizontalAcceleration(1);
		
		creature.changeHorizontalPosition(1);
		// newPosition = 0 + (100 * 1*1) + 50 * 1*1^2) = 150;
		assertTrue(creature.getXPosition() == 150);
	}
	
	@Test
	public void changeHorizontalPosition_validPositionAtMaxSpeed(){
		World world = new World(70, 50, 50, 1024, 768, 20, 20);
		Piemels creature = new Piemels(0, 0, spriteArrayForSize(2, 2),3, 1, world, 100);
		creature.setDirection(Direction.RIGHT);
		creature.setHorizontalVelocity(3);
		creature.setHorizontalAcceleration(1);
		
		creature.changeHorizontalPosition(1);
		System.out.println(creature.getXPosition());
		System.out.println(creature.getHorizontalAcceleration());
		// newPosition = 0 + (100 * 3*1) + 0) = 300;
		assertTrue(creature.getXPosition() == 300);
		assertTrue(creature.getHorizontalAcceleration() == 0);
	}
	
	@Test
	public void changeHorizontalPosition_inValidPosition(){
		Piemels creature1 = new Piemels(1024,0,spriteArrayForSize(2, 2));
		creature1.setDirection(Direction.RIGHT);
		creature1.setHorizontalVelocity(1);
		creature1.setHorizontalAcceleration(1);
		
		creature1.changeHorizontalPosition(1);
		assertTrue(creature1.getXPosition() == 1024);
		
		
		Piemels creature2 = new Piemels(0,0,spriteArrayForSize(2, 2));
		creature2.setDirection(Direction.LEFT);
		creature2.setHorizontalVelocity(1);
		creature2.setHorizontalAcceleration(1);
		
		creature2.changeHorizontalPosition(1);
		assertTrue(creature2.getXPosition() == 0);
	}
	
	
	@Test
	public void changeVerticalPosition_validPositionAndMovingVertically(){
		Piemels creature = new Piemels(spriteArrayForSize(2, 2));
		creature.setMovingVertical(true);
		creature.setVerticalVelocity(8);
		creature.setVerticalAcceleration(-10);
		
		creature.changeVerticalPosition(1);
		// newPosition = 0 + (100 * 8 * 1) - 50 * 10*1^2) = 300;
		assertTrue(creature.getYPosition() == 300);
	}
	
	@Test
	public void changeVerticalPosition_MovingOverTheUpperBorder(){
		Piemels creature = new Piemels(0,768,spriteArrayForSize(2, 2));
		creature.setMovingVertical(true); 
		creature.setVerticalVelocity(1);
		creature.setVerticalAcceleration(1);
		
		creature.changeVerticalPosition(1);
		assertTrue(creature.getYPosition() == 768);
	}
	
	@Test
	public void changeVerticalPosition_MovingOverTheBottomBorder(){
		Piemels creature = new Piemels(0,0,spriteArrayForSize(2, 2));
		creature.setMovingVertical(true); 
		creature.setVerticalVelocity(-1);
		creature.setVerticalAcceleration(-1);
		
		creature.changeVerticalPosition(1);
		assertTrue(creature.getYPosition() == 0);
	}
	
	@Test
	public void changeVerticalPosition_validPositionButNotMoving(){
		Piemels creature = new Piemels(0,500,spriteArrayForSize(2, 2));
		creature.setVerticalVelocity(-10);
		creature.setVerticalAcceleration(-10);
		
		creature.changeVerticalPosition(1);
		assertTrue(creature.getYPosition() == 500);
	}
}
