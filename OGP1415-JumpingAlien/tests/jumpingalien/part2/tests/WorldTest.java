package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;

import java.lang.Thread.State;
import java.util.Arrays;

import jumpingalien.model.IllegalXPositionException;
import jumpingalien.model.IllegalYPositionException;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.Position;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.part2.internal.tmxfile.data.ImageTile.TileType;
import jumpingalien.util.Sprite;

import org.junit.Before;
import org.junit.Test;

public class WorldTest {

	private World world,bigWorld;
	private Mazub mazub;
	private Sprite[] defaultSprites;
	
	
	@Before
	public void setUp() {
		this.world = new World(10,20,15,100,80,135,85);
		this.bigWorld = new World(10,200,150,400,400,1800,1400);
		this.defaultSprites = spriteArrayForSize(2,2);
		this.mazub = new Mazub(1000,700,defaultSprites,3,1,bigWorld,100);
		bigWorld.setMazub(mazub);


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
		assertTrue(World.isValidPosition(-5, 15, world));
		assertTrue(World.isValidPosition(27, -9, world));
		assertTrue(World.isValidPosition(-7, -9, world));

	}
	
	@Test
	public void isValidPosition_False(){
		assertFalse(World.isValidPosition(45, 15, world));
		assertFalse(World.isValidPosition(27, 9, world));
	}
	
	@Test
	public void getTileType$LegalCase(){
		world.addTileType(1,1,3);
		assertTrue(world.getTileType(1,1) == TileType.AIR.getValue());
		assertTrue(world.getTileType(5,5)==TileType.AIR.getValue());
	}
	
	@Test(expected=IllegalXPositionException.class)
	public void getTileType$IllegalXPosition(){
		world.getTileType(-5, 10);
	}
	
	@Test(expected=IllegalYPositionException.class)
	public void getTileType$IllegalYPosition(){
		world.getTileType(5, -10);
	}
	
	@Test
	public void isPassable_True(){
		world.addTileType(3, 3, 0);
		world.addTileType(2, 2, 2);
		world.addTileType(6, 6, 3);
		
		assertTrue(world.isPassable(6, 6));
		assertTrue(world.isPassable(2, 2));
		assertTrue(world.isPassable(3, 3));
	}
	

	@Test
	public void getOccupiedTilesTest(){
		//TODO
	}
	
	@Test
	public void isValidVisibleWindow$True(){
		assertTrue(world.isValidVisibleWindow(5, 5));
		assertTrue(world.isValidVisibleWindow(200, 150));
		assertTrue(world.isValidVisibleWindow(0, 0));
	}
	
	@Test
	public void isValidVisibleWindow$False(){
		assertFalse(world.isValidVisibleWindow(-1, 0));
		assertFalse(world.isValidVisibleWindow(0, -1));
		assertFalse(world.isValidVisibleWindow(-1, -1));
		assertFalse(world.isValidVisibleWindow(201, 150));
		assertFalse(world.isValidVisibleWindow(200, 151));
		assertFalse(world.isValidVisibleWindow(201, 151));
	}
	
	@Test 
	public void testSetWindowPosition$LegalCase(){
		world.setWindowPosition(5,15);
		assertTrue(world.getVisibleWindowPositionArray()[0] == 5);
		assertTrue(world.getVisibleWindowPositionArray()[1] == 15);	
		world.setWindowPosition(100,70);
		assertTrue(world.getVisibleWindowPositionArray()[0] == 100);
		assertTrue(world.getVisibleWindowPositionArray()[1] == 70);
	}
	
	@Test 
	public void testSetWindowPosition$IllegalLeftX(){
		world.setWindowPosition(-1,15);
		assertTrue(world.getVisibleWindowPositionArray()[0] == 0);
		assertTrue(world.getVisibleWindowPositionArray()[1] == 15);	
		world.setWindowPosition(101,70);
		assertTrue(world.getVisibleWindowPositionArray()[0] == 100);
		assertTrue(world.getVisibleWindowPositionArray()[1] == 70);
	}
	
	@Test 
	public void testSetWindowPosition$IllegalBottomY(){
		world.setWindowPosition(5,-1);
		assertTrue(world.getVisibleWindowPositionArray()[0] == 5);
		assertTrue(world.getVisibleWindowPositionArray()[1] == 0);	
		world.setWindowPosition(100,71);
		assertTrue(world.getVisibleWindowPositionArray()[0] == 100);
		assertTrue(world.getVisibleWindowPositionArray()[1] == 70);
	}
	
	@Test 
	public void testSetWindowPosition$EverythingIllegal(){
		world.setWindowPosition(-1,-1);
		assertTrue(world.getVisibleWindowPositionArray()[0] == 0);
		assertTrue(world.getVisibleWindowPositionArray()[1] == 0);	
		world.setWindowPosition(101,71);
		assertTrue(world.getVisibleWindowPositionArray()[0] == 100);
		assertTrue(world.getVisibleWindowPositionArray()[1] == 70);
	}
	
	@Test
	public void UpdateWindowPosition_XPosition(){
		//Position Mazub at start (1000,700)
		bigWorld.setWindowPosition(0,200);
		bigWorld.updateWindowPosition();
		assertTrue(bigWorld.getVisibleWindowPositionArray()[0] == 800);
		//visible window (400,400)
		bigWorld.setWindowPosition(1800,200);
		bigWorld.updateWindowPosition();
		assertTrue(bigWorld.getVisibleWindowPositionArray()[0] == 800);
	}
	
	@Test
	public void UpdateWindowPosition_YPosition(){
		//Position Mazub at start (1000,700)
		bigWorld.setWindowPosition(800,0);
		bigWorld.updateWindowPosition();
		assertTrue(bigWorld.getVisibleWindowPositionArray()[1] == 500);
		//visible window (400,400)
		bigWorld.setWindowPosition(1800,1300);
		bigWorld.updateWindowPosition();
		assertTrue(bigWorld.getVisibleWindowPositionArray()[1] == 500);
	}
	
	@Test
	public void UpdateWindowPosition_XUndYPosition(){
		//Position Mazub at start (1000,700)
		bigWorld.setWindowPosition(0,0);
		bigWorld.updateWindowPosition();
		assertTrue(bigWorld.getVisibleWindowPositionArray()[0] == 800);
		assertTrue(bigWorld.getVisibleWindowPositionArray()[1] == 500);
		//visible window (400,400)
		bigWorld.setWindowPosition(1800,1300);
		bigWorld.updateWindowPosition();
		assertTrue(bigWorld.getVisibleWindowPositionArray()[0] == 800);
		assertTrue(bigWorld.getVisibleWindowPositionArray()[1] == 500);
	}
	
	@Test
	public void addPlantTest(){
		Plant plant = new Plant(defaultSprites);
		world.addPlant(plant);
		assertTrue(plant.getWorld() == world);
		assertTrue(world.hasAsPlant(plant));
	}
	
	@Test
	public void removePlantTest(){
		Plant plant = new Plant(defaultSprites);
		world.addPlant(plant);
		world.removePlant(plant);
		assertTrue(plant.getWorld() == null);
		assertFalse(world.hasAsPlant(plant));
	}
	
	@Test
	public void addSlimeTest(){
		School school = new School();
		Slime slime = new Slime(0,0,defaultSprites,school);
		world.addSlime(slime);
		assertTrue(slime.getWorld() == world);
		assertTrue(world.hasAsSlime(slime));
	}
	
	@Test
	public void removeSlimeTest(){
		School school = new School();
		Slime slime = new Slime(0,0,defaultSprites,school);
		world.addSlime(slime);
		world.removeSlime(slime);
		assertTrue(slime.getWorld() == null);
		assertFalse(world.hasAsSlime(slime));
	}
	
	@Test
	public void addSharkTest(){
		Shark shark = new Shark(defaultSprites);
		world.addShark(shark);
		assertTrue(shark.getWorld() == world);
		assertTrue(world.hasAsShark(shark));
	}
	
	@Test
	public void removeSharkTest(){
		Shark shark = new Shark(defaultSprites);
		world.addShark(shark);
		world.removeShark(shark);
		assertTrue(shark.getWorld() == null);
		assertFalse(world.hasAsShark(shark));
	}
	
	@Test
	public void isValidObject$True(){
		Shark shark = new Shark(defaultSprites);		
		School school = new School();
		Slime slime = new Slime(0,0,defaultSprites,school);
		Plant plant = new Plant(defaultSprites);
		
		assertTrue(world.isValidObject(plant));
		assertTrue(world.isValidObject(shark));
		assertTrue(world.isValidObject(slime));
		assertTrue(world.isValidObject(mazub));
	}
	
	@Test
	public void isValidObject$False(){
		School school = new School();
		Position position = new Position();

		assertFalse(world.isValidObject(school));
		assertFalse(world.isValidObject(position));
	}
	
}
