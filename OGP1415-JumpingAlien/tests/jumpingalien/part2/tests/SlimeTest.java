package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;
import jumpingalien.model.Direction;
import jumpingalien.model.IllegalTimeIntervalException;
import jumpingalien.model.Mazub;
import jumpingalien.model.School;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.Test;

public class SlimeTest {
	
	private Sprite[] defaultSprites;
	private World defaultWorld;
	private School school1;
	private School school2;
	private Slime slime1, slime2;
	
	@Before
	public void setUp() {
		this.defaultWorld = new World(10,10,10,10,10,9,9);
		this.defaultSprites = spriteArrayForSize(2,2);
		this.school1 = new School();
		this.school2 = new School();
		this.slime1 = new Slime(0, 0, 0, 0, 0, defaultSprites, defaultWorld,school1,100);
		this.slime2 = new Slime(5, 5, 0, 0, 0, defaultSprites, defaultWorld,school2,100);
	}

	@Test
	public void extendedConstructor$LegalCase() {	
		Slime slime = new Slime(0, 0, 0, 0, 0, defaultSprites, defaultWorld,school1,100);
		assertTrue(slime.getXPosition() == 0);
		assertTrue(slime.getYPosition() == 0);
		assertTrue(slime.getHorizontalVelocity() == 0);
		assertTrue(slime.getVerticalVelocity() ==0);
		assertTrue(slime.getHorizontalAcceleration()==0);
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
		slime1.advanceTime(0.199);
		// xPos == 5 - 0.7*50*0.199**2 = 5 - 1.386 =3.614
		// xVel == 0.7 * 0.1 = 0.07
		assertTrue(Util.fuzzyEquals(slime2.getXPosition(),3.614));
		assertTrue(slime2.getHorizontalVelocity() == 0.07);
		assertTrue(slime2.getDirection() == Direction.LEFT);
		}
	
	@Test
	public void testTerminteWhenTerminatedWithinTheBounds(){
		assertTrue(slime1.isAlive());
		slime1.addHP(-100);
		//Terminates when HP goes below 0 HP
		assertTrue(slime1.getHP() == 0);
		assertTrue(slime1.isDying());
		for (int i=0; i<5; i += 1){
			slime1.advanceTime(0.199);
		}
		assertTrue(slime1.isDead());
		assertTrue(slime1.getWorld() == null);
		assertTrue(slime1.getSchool() == null);
		
	}
	
	@Test(expected=IllegalStateException.class)
	public void testTerminteWhenAliveAndWithinTheBounds() throws Exception{
		assertTrue(slime1.isAlive());
		slime1.terminate();
		assertTrue(slime1.isAlive());
	}
	
	@Test
	public void testTerminteWhenOutsideTheBounds(){
		slime1.setPosition(0, 666);
		//when going out of bound the slime is immediately removed.
		assertTrue(slime1.isDead());
		assertTrue(slime1.getWorld() == null);
		assertTrue(slime1.getSchool() == null);
	}
	
	@Test(expected=jumpingalien.model.IllegalTimeIntervalException.class)
	public void testAdvanceTime$illegalCase()throws Exception{
		slime1.advanceTime(3);
		assertTrue(slime1.getXPosition() == 0);
		assertTrue(slime1.getHorizontalVelocity() == 0);
	}
	
	@Test
	public void testAdvanceTimeNormalCase(){
		slime2.startMoveOpposite();
		slime2.startMoveOpposite();
		for (int i = 0; i < 2; i+=1){
			slime2.advanceTime(0.1);
		}
		//TODO
	}
	

}
