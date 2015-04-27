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
