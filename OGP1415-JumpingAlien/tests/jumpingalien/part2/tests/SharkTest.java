package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;
import jumpingalien.model.Shark;
import jumpingalien.model.World;
import jumpingalien.util.Sprite;

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
		assertTrue(shark.getHorizontalAcceleration()==0);
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

}
