package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;
import jumpingalien.model.Direction;
import jumpingalien.model.LivingCreatures;
import jumpingalien.model.Slime;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.Test;

public class SlimeTest {
	
	private Sprite[] defaultSprites;
	private World defaultWorld,bigWorld;
	private School school1;
	private School school2;
	private Slime slime, slime2;
	
	@Before
	public void setUp() {
		this.defaultWorld = new World(10,10,10,10,10,9,9);
		this.bigWorld = new World(10,100,100,500,500,700,700);

		this.defaultSprites = spriteArrayForSize(2,2);
		this.school1 = new School();
		this.school2 = new School();
		this.slime = new Slime(0, 0, 0, 0, 0, defaultSprites, defaultWorld,school1,100);
		this.slime2 = new Slime(5, 5, 0, 0, 0, defaultSprites, defaultWorld,school2,100);
	}

	@Test
	public void extendedConstructor$LegalCase() {	
		Slime slime = new Slime(5, 10, 1, 20, 25,defaultSprites, defaultWorld,school1,100);
		assertTrue(slime.getXPosition() == 5);
		assertTrue(slime.getYPosition() == 10);
		assertTrue(slime.getHorizontalVelocity() == 1);
		assertTrue(slime.getVerticalVelocity() ==20);
		assertTrue(slime.getHorizontalAcceleration()==25);
		assertTrue(slime.getVerticalAcceleration() == 0);
		assertTrue(slime.getWorld() == defaultWorld);
		assertTrue(slime.getSchool() == school1);
		assertTrue(slime.getHP() == 100);
		assertArrayEquals(defaultSprites, slime.getSpriteArray());
	}
	
	@Test
	public void extendedConstructor$OutOfBoundXPosition() {
		//Positions too big (out of bounds of gameworld)
		Slime slime = new Slime(300,0,0,0,0,defaultSprites,defaultWorld,school1,100);
		assertTrue(slime.getHP() == 0);
		assertTrue(slime.getWorld() == null);
		assertTrue(slime.getSchool() == null);
		assertTrue(slime.isDead());
	}
	
	@Test
	public void extendedConstructor$OutOfBoundYPosition() {
		//Positions too big (out of bounds of gameworld)
		Slime slime = new Slime(0,6805,0,0,0,defaultSprites,defaultWorld,school1,100);
		assertTrue(slime.getHP() == 0);
		assertTrue(slime.getWorld() == null);
		assertTrue(slime.getSchool() == null);
		assertTrue(slime.isDead());
	}
	
	@Test
	public void testCorrectInitialisedWithOutWorldAndHitPointsGiven() {
				
		Slime slime = new Slime(0,0,0,0,defaultSprites,school1);
		assertTrue(slime.getWorld() ==null);
		assertTrue(slime.getHP() == 100);
		assertTrue(slime.getHorizontalAcceleration() == 0);
	}
	
	@Test
	public void testCorrectInitialisedWithOutVelocities_WorldAndHitPointsGiven() {
				
		Slime slime = new Slime(0,0,defaultSprites,school1);
		assertTrue(slime.getHorizontalVelocity() ==0);
		assertTrue(slime.getVerticalVelocity()==0);
	}
	
	@Test
	public void testCorrectInitialisedWithOnlySpritesAndSchoolGiven() {
				
		Slime slime = new Slime(defaultSprites,school1);
		assertTrue(slime.getXPosition() ==0);
		assertTrue(slime.getYPosition()==0);
	}
	
	//@Test
	//TODO Uncomment to test
	public void testStartMoveOpposite(){
		//When initialized the direction for a slime is to the right
		slime2.startMoveOpposite();
		assertTrue(slime2.getMoving() == true);
		assertTrue(slime2.getHorizontalVelocity() == 0);
		assertTrue(slime2.getHorizontalAcceleration() == 0.7);
		slime.advanceTime(0.199);
		// xPos == 5 - 0.7*50*0.199**2 = 5 - 1.386 =3.614
		// xVel == 0.7 * 0.1 = 0.07
		assertTrue(Util.fuzzyEquals(slime2.getXPosition(),3.614));
		assertTrue(slime2.getHorizontalVelocity() == 0.07);
		assertTrue(slime2.getDirection() == Direction.LEFT);
		}
	
	@Test
	public void testTerminteWhenTerminatedWithinTheBounds(){
		assertTrue(slime.isAlive());
		slime.addHP(-100);
		//Terminates when HP goes below 0 HP
		assertTrue(slime.getHP() == 0);
		assertTrue(slime.isDying());
		for (int i=0; i<5; i += 1){
			slime.advanceTime(0.199);
		}
		assertTrue(slime.isDead());
		assertTrue(slime.getWorld() == null);
		assertTrue(slime.getSchool() == null);
		
	}
	
	@Test(expected=IllegalStateException.class)
	public void testTerminteWhenAliveAndWithinTheBounds() throws Exception{
		assertTrue(slime.isAlive());
		slime.terminate();
		assertTrue(slime.isAlive());
	}
	
	@Test
	public void testTerminteWhenOutsideTheBounds(){
		slime.setPosition(0, 666);
		//when going out of bound the slime is immediately removed.
		assertTrue(slime.isDead());
		assertTrue(slime.getWorld() == null);
		assertTrue(slime.getSchool() == null);
	}
	
	@Test(expected=jumpingalien.model.IllegalTimeIntervalException.class)
	public void testAdvanceTime$illegalCase()throws Exception{
		slime.advanceTime(3);
		assertTrue(slime.getXPosition() == 0);
		assertTrue(slime.getHorizontalVelocity() == 0);
	}
		
	
	//@Test(expected = IllegalXPositionException.class)
	//public void testSetXposition$IllegalCase() throws Exception{
	//	slime alien = new slime(spriteArrayForSize(2, 2));
	//	alien.setXPosition(-5);
	//	assertTrue(alien.getXPosition() == -5);
	//}
	@Test
	public void testSetXposition$LegalCase() throws Exception{
		Slime slime = new Slime(0,0,spriteArrayForSize(2, 2),school1);
		slime.setXPosition(5);
		assertTrue(slime.getXPosition() == 5);
	}


	//@Test(expected = IllegalXPositionException.class)
	//public void testSetXposition$IllegalCase() throws Exception{
	//		slime alien = new slime(spriteArrayForSize(2, 2));
	//		alien.setXPosition(-5);
	//		assertTrue(alien.getXPosition() == -5);
	//}	
	@Test
	public void testSetYposition$LegalCase() throws Exception{
		Slime slime = new Slime(0,0,spriteArrayForSize(2, 2),school1);
		slime.setYPosition(5);
		assertTrue(slime.getYPosition() == 5);
	}
	
	@Test
	public void testSetHorizontalVelocity() {
		Slime creature = new Slime(0,0,spriteArrayForSize(2, 2),school1);
		double velocity;
		
		creature.setHorizontalVelocity(2);
		velocity = creature.getHorizontalVelocity();
		assertEquals(2.0,velocity,Util.DEFAULT_EPSILON);
		
		creature.setHorizontalVelocity(5);
		velocity = creature.getHorizontalVelocity();
		assertEquals(2.5,velocity,Util.DEFAULT_EPSILON);
		
		creature.setHorizontalVelocity(-5);
		velocity = creature.getHorizontalVelocity();
		assertEquals(0.0,velocity,Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void testSetVerticalVelocity() {
		Slime creature = new Slime(0,0,spriteArrayForSize(2, 2),school1);
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
		assertTrue(slime.hasAsWorld(defaultWorld));
	}
	
	@Test
	public void hasAsWorld_False(){
		slime.setWorld(null);
		assertFalse(slime.hasAsWorld(defaultWorld));
	}
	
	@Test
	public void canHaveAsWorld_True(){
		assertTrue(slime.canHaveAsWorld(defaultWorld));
	}
	
	@Test
	public void canHaveAsWorld_False(){
		assertFalse(slime.canHaveAsWorld(null));
	}
	
	@Test
	public void isValidSpriteArray_True() {
		assertTrue(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 2),slime));
	}
	
	@Test
	public void isValidSpriteArray_False() {
		assertFalse(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 0),slime));
		assertFalse(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 13),slime));
		assertFalse(LivingCreatures.isValidSpriteArray(spriteArrayForSize(2, 2, 8),slime));
	}
	
	@Test
	public void getCurrentSprite$Right(){
		slime.setDirection(1);
		assertTrue(slime.getCurrentSprite() == slime.getSpriteArray()[1]);
		}
	
	@Test
	public void getCurrentSprite$Left(){
		slime.setDirection(-1);
		assertTrue(slime.getCurrentSprite() == slime.getSpriteArray()[0]);
		}
	
	@Test
	public void setHP_NormalCase(){
		slime.setHP(slime.getMinHP());
		assertTrue(slime.getHP() == slime.getMinHP());
		slime.setHP(slime.getMaxHP()/2);
		assertTrue(slime.getHP() == slime.getMaxHP()/2);
		slime.setHP(slime.getMaxHP());
		assertTrue(slime.getHP() == slime.getMaxHP());
	}
	
	@Test
	public void setHP_SpecialCase(){
		slime.setHP(slime.getMinHP() - 5);
		assertTrue(slime.getHP() == slime.getMinHP());

		slime.setHP(slime.getMaxHP()+5);
		assertTrue(slime.getHP() == slime.getMaxHP());
	}
	
	@Test
	public void changeHorizontalPosition(){
		Slime slime = new Slime(0,0,spriteArrayForSize(2, 2),school1);
		slime.setHorizontalVelocity(2);
		slime.setHorizontalAcceleration(2);
		assertEquals(slime.getHorizontalAcceleration(),2,Util.DEFAULT_EPSILON);

		slime.setDirection(1);
		slime.changeHorizontalPosition(0.1,2);
		assertEquals(slime.getXPosition(),21,Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void changeVerticalPosition(){
		Slime slime = new Slime(0,0,spriteArrayForSize(2, 2),school1);
		slime.setVerticalVelocity(5);
		slime.setVerticalAcceleration(2);
		slime.setDirection(1);
		slime.changeVerticalPosition(0.1);
		assertEquals(slime.getXPosition(),0,Util.DEFAULT_EPSILON);
		
		Slime slime1 = new Slime(0,0,spriteArrayForSize(2, 2),school1);
		slime1.setVerticalVelocity(-5);
		slime1.setVerticalAcceleration(-2);
		slime1.setDirection(1);
		slime1.changeVerticalPosition(0.1);
		assertEquals(slime.getXPosition(),0,Util.DEFAULT_EPSILON);
	}
	
	@Test 
	public void startJump_whileNotMovingVertical(){
		slime.startJump(7,-2);
		assertEquals(slime.getVerticalVelocity(),7,Util.DEFAULT_EPSILON);
		assertEquals(slime.getVerticalAcceleration(),-2,Util.DEFAULT_EPSILON);
		assertTrue(slime.getMovingVertical());
	}
	
	@Test 
	public void startJump_WhileMovingVertical(){
		Slime slime = new Slime(0,0,spriteArrayForSize(2, 2),school1);
		slime.setVerticalVelocity(-5);
		slime.setVerticalAcceleration(-2);	
		slime.setMovingVertical(true);
		slime.startJump(5,9);
		assertEquals(slime.getVerticalVelocity(),-5,Util.DEFAULT_EPSILON);
		assertEquals(slime.getVerticalAcceleration(),-2,Util.DEFAULT_EPSILON);
		assertTrue(slime.getMovingVertical());
	}
	
	@Test
	public void endJump_ableToEndJump(){
		Slime slime = new Slime(0,0,spriteArrayForSize(2, 2),school1);
		slime.setVerticalVelocity(5);
		slime.setVerticalAcceleration(-2);
		slime.endJump();
		assertEquals(slime.getVerticalVelocity(),0,Util.DEFAULT_EPSILON);
		assertEquals(slime.getVerticalAcceleration(),-2,Util.DEFAULT_EPSILON);
	}
	
	@Test 
	public void endJump_unableToEnd(){
		Slime slime = new Slime(0,0,spriteArrayForSize(2, 2),school1);
		slime.setVerticalVelocity(-5);
		slime.setVerticalAcceleration(-2);	
		slime.endJump();
		assertEquals(slime.getVerticalVelocity(),-5,Util.DEFAULT_EPSILON);
		assertEquals(slime.getVerticalAcceleration(),-2,Util.DEFAULT_EPSILON);
	}

}
