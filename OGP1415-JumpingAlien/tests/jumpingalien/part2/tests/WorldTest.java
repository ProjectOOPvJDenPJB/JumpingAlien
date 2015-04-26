package jumpingalien.part2.tests;

import static org.junit.Assert.*;
import jumpingalien.model.World;

import org.junit.Before;
import org.junit.Test;

public class WorldTest {

	private World world;
	
	@Before
	public void setUp() {
		this.world = new World(10,20,15,100,80,135,85);
	}
	
	@Test
	public void extendedConstructor$LegalCase() {	
		World world = new World(5, 10, 15, 30,25,90,130);
		assertTrue(world.getTileSize() == 5);
		assertTrue(world.getNbTilesX() == 10);
		assertTrue(world.getNbTilesY() == 15);
		assertTrue(world.getVisibleWindowWidth() ==30);
		assertTrue(world.getVisibleWindowHeight()==25);
		assertTrue(world.getTargetTileX() == 90);
		assertTrue(world.getTargetTileY() == 130);
	}

	@Test
	public void isValidTileSize_True(){
		assertTrue(World.isValidTileSize(5, 15, 20));
		assertTrue(World.isValidTileSize(3, 9, 27));
	}
	
	@Test
	public void isValidTileSize_False(){
		assertFalse(World.isValidTileSize(15, 5, 20));
		assertFalse(World.isValidTileSize(3, 10, 27));
		assertFalse(World.isValidTileSize(3, 9, 26));
		assertFalse(World.isValidTileSize(5, 9, 26));
	}
	
	@Test
	public void getTilePosition_WithinBounds(){
		assertEquals(world.getTilePosition(0,0)[0],0);
		assertEquals(world.getTilePosition(0,0)[1],0);
		
		assertEquals(world.getTilePosition(5,5)[0],0);
		assertEquals(world.getTilePosition(5,5)[1],0);
		
		assertEquals(world.getTilePosition(78,43)[0],7);
		assertEquals(world.getTilePosition(78,43)[1],4);
		
		assertEquals(world.getTilePosition(100,150)[0],10);
		assertEquals(world.getTilePosition(100,150)[1],15);
	}
	
	@Test
	public void getTilePosition_OutOfBounds(){
		assertEquals(world.getTilePosition(-5,0)[0],0);
		assertEquals(world.getTilePosition(0,-5)[1],0);
		
		assertEquals(world.getTilePosition(-15,-25)[0],0);
		assertEquals(world.getTilePosition(-15,-25)[1],0);
		
		assertEquals(world.getTilePosition(201,151)[0],20);
		assertEquals(world.getTilePosition(201,151)[1],15);
		
		assertEquals(world.getTilePosition(221,171)[0],20);
		assertEquals(world.getTilePosition(221,171)[1],15);
	}
	
	@Test
	public void isValidTilePosition_True(){
		assertTrue(World.isValidTilePosition(45, 15, 5));
		assertTrue(World.isValidTilePosition(27, 9, 3));
	}
	
	@Test
	public void isValidTilePosition_False(){
		assertFalse(World.isValidTilePosition(43, 15, 5));
		assertFalse(World.isValidTilePosition(27, 10, 3));
		
		assertFalse(World.isValidTilePosition(43, 13, 5));
		assertFalse(World.isValidTilePosition(25, 10, 45));
	}
	
	@Test
	public void isValidPosition_True(){
		assertTrue(World.isValidPosition(45, 15, world));
		assertTrue(World.isValidPosition(27, 9, world));
	}
	
	@Test
	public void isValidPosition_False(){
		assertFalse(World.isValidPosition(-5, 15, world));
		assertFalse(World.isValidPosition(27, -9, world));
		assertFalse(World.isValidPosition(-7, -9, world));
	}
	
}
