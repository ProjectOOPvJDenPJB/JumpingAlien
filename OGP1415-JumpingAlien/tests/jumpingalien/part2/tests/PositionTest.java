package jumpingalien.part2.tests;

import static org.junit.Assert.*;
import jumpingalien.model.Position;
import jumpingalien.model.World;

import org.junit.Before;
import org.junit.Test;

public class PositionTest {

	private World world;
	
	@Before
	public void setUp() {
		this.world = new World(10,100,100,100,100,500,500);

	}
	
	@Test
	public void extendedConstructor$LegalCase() {	
		Position position = new Position(1, 2,world);
		assertTrue(position.getXPosition() == 1);
		assertTrue(position.getYPosition() == 2);
	}
	@Test(expected=jumpingalien.model.IllegalXPositionException.class)
	public void extendedConstructor$IllegalCase_ForXPosition() {	
		Position position = new Position(-5, 2,world);
	}
	
	@Test(expected=jumpingalien.model.IllegalYPositionException.class)
	public void extendedConstructor$IllegalCase_ForYPosition() {	
		Position position = new Position(5, -2,world);
	}
	
	@Test
	public void isValidXposition_True(){
		assertTrue(Position.isValidXPosition(5, 7));
		assertTrue(Position.isValidXPosition(0, 7));
		assertTrue(Position.isValidXPosition(7, 7));
	}
	
	@Test
	public void isValidXposition_False(){
		assertFalse(Position.isValidXPosition(8, 7));
		assertFalse(Position.isValidXPosition(-1, 7));
	}
	
	@Test
	public void isValidYposition_True(){
		assertTrue(Position.isValidYPosition(5, 7));
		assertTrue(Position.isValidYPosition(0, 7));
		assertTrue(Position.isValidYPosition(7, 7));
	}
	
	@Test
	public void isValidYposition_False(){
		assertFalse(Position.isValidYPosition(8, 7));
		assertFalse(Position.isValidYPosition(-1, 7));
	}
	
	@Test
	public void getWidth_WithWorld(){
		Position position = new Position(5,5,world);
		assertEquals(position.getWidth(),1000);
	}
	
	@Test(expected=IllegalStateException.class)
	public void getWidth_NoWorld(){
		Position position = new Position(5,5);
		position.getWidth();
	}
	
	@Test
	public void getHeight_WithWorld(){
		Position position = new Position(5,5,world);
		assertEquals(position.getHeight(),1000);
	}
	
	@Test(expected=IllegalStateException.class)
	public void getHeight_NoWorld(){
		Position position = new Position(5,5);
		position.getHeight();
	}
}
