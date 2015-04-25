package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;
import jumpingalien.model.Plant;
import jumpingalien.model.World;
import jumpingalien.util.Sprite;

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
}



