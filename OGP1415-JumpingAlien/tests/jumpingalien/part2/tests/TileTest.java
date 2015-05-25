package jumpingalien.part2.tests;

import static org.junit.Assert.*;
import jumpingalien.model.Tile;
import jumpingalien.model.TileType;

import org.junit.Test;

public class TileTest {

	@Test
	public void extendedConstructor_WithTileType() {	
		Tile tile = new Tile(1, 2, TileType.AIR, null);
		assertTrue(tile.getLeftX() == 1);
		assertTrue(tile.getBottomY() == 2);
		assertTrue(tile.getType() == TileType.AIR);
	}
	
	@Test
	public void extendedConstructor_WithoutTileType(){	
		Tile tile1 = new Tile(1, 2, 0, null);
		assertTrue(tile1.getType() == TileType.AIR);
		
		Tile tile2 = new Tile(1, 2, 1, null);
		assertTrue(tile2.getType() == TileType.GROUND);
		
		Tile tile3 = new Tile(1, 2, 2, null);
		assertTrue(tile3.getType() == TileType.WATER);
		
		Tile tile4 = new Tile(1, 2, 3, null);
		assertTrue(tile4.getType() == TileType.MAGMA);
	}
	
}
