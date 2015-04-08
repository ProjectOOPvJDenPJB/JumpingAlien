package jumpingalien.model;

import java.util.HashMap;
import java.util.Map;

import be.kuleuven.cs.som.annotate.*;

/**
 * @author Joren Dhont (Ingenieurswetenschappen: Computerwetenschappen - Elektrotechniek) 
 * 	& Pieterjan Beerden (Ingenieurswetenschappen: Elektrotechniek - Computerwetenschappen)
 * @version 0.1
 */
public class World {
	
	public World(int tileSize, int nbTilesX, int nbTilesY) {
		assert isValidTileSize(tileSize, nbTilesX * tileSize, nbTilesY * tileSize);
		// Aangezien we me nbTiles werken, is dit eigenlijk ni nodig.
		this.tileSize = tileSize;
		setNbTilesX(nbTilesX);
		setNbTilesY(nbTilesY);
	}

	/**
	 * Return the amount of tiles on the X axis of this world.
	 */
	public int getNbTilesX() {
		return nbTilesX;
	}
	/**
	 * Variable registering the amount of tiles on the X axis of this world.
	 */
	public int nbTilesX;
	/**
	 * Set the amount of tiles on the X axis of this world to a given amount.
	 * @param 	nbTilesX
	 * 			The new amount of tiles on the X axis of this world.
	 * @post	The new amount of tiles on the X axis of this world is equal to the
	 * 			given amount of tiles.
	 */
	public void setNbTilesX(int nbTilesX) {
		this.nbTilesX = nbTilesX;
	}
	/**
	 * Return the amount of tiles on the Y axis of this world.
	 */
	public int getNbTilesY() {
		return nbTilesY;
	}
	/**
	 * Variable registering the amount of tiles on the Y axis of this world.
	 */
	public int nbTilesY;
	/**
	 * Set the amount of tiles on the Y axis of this world to a given amount.
	 * @param 	nbTilesY
	 *  		The new amount of tiles on the Y axis of this world.
	 * @post	The new amount of tiles on the Y axis of this world is equal to the
	 * 			given amount of tiles.
	 */
	public void setNbTilesY(int nbTilesY) {
		this.nbTilesY = nbTilesY;
	}
	/**
	 * Return the length of the tiles in this World.
	 * 	The length is expressed in pixels.
	 */
	public int getTileSize() {
		return tileSize;
	}
	/**
	 * Checks whether a given tileSize is a valid tileSize.
	 * @param 	tileSize
	 * 			The length of tiles to check.
	 * @param 	width
	 * 			The width of this World to check against.
	 * @param 	height
	 * 			The height of this World to check against.
	 * @return	...
	 * 			| result ==
	 * 			|	((width % tileSize) == 0) && ((height % tileSize) == 0)
	 */
	public static boolean isValidTileSize(int tileSize, int width, int height) {
		return ((width % tileSize) == 0) && ((height % tileSize) == 0);
	}
	/**
	 * Variable registering the length of the tiles in this World.
	 * 	The length is expressed in pixels.
	 */
	public final int tileSize;

		
	/**
	 * Return the width of this world expressed in pixels.
	 * @return	...
	 * 			| result == this.getNbTilesX() * this.tileSize
	 */
	public int getPixelWidth() {
		return getNbTilesX() * tileSize;
	}
	
	/**
	 * Return the height of this world expressed in pixels.
	 * @return	...
	 * 			| result == this.getNbTilesY() * this.tileSize
	 */
	public int getPixelHeight() {
		return getNbTilesY() * tileSize;
	}
	
	/**
	 *  Returns the position of the tile in which the given position is situated.
	 * @param 	pixelX
	 * 			The X coordinate of the given position.
	 * @param 	pixelY
	 * 			The Y coordinate of the given position.
	 */
	public int[] getTile(int pixelX,int pixelY) {
		int[] position = new int[2];
		position[0] = (pixelX - (pixelX % tileSize));
		position[1] = (pixelY - (pixelY % tileSize));
		return position;
	}
	
	/**
	 * Check whether the given position of a tile is a valid position of a tile.
	 * 
	 * @param 	tileX
	 * 			The X coordinate of the tile to check, expressed in pixels.
	 * @param	tileY
	 * 			The Y coordinate of the tile to check, expressed in pixels.
	 * @param 	tileSize
	 * 			The size of the tile to check against.
	 * @return	...
	 * 			| result ==
	 * 			|	(tileX % tileSize == 0) && (tileY % tileSize == 0)
	 */
	public static boolean isValidTilePosition(int tileX, int tileY, int tileSize) {
		return (tileX % tileSize == 0) && (tileY % tileSize == 0);
	}
	
	/**
	 * Adds a tile type to the map of tile types.
	 * @param 	tile
	 * 			The tile of which the tile type has to be added.
	 * @post	...
	 * 			| new.getTileType(tile.getLeftX(),tile.getBottomY()) == tile.getType
	 */
	@Raw
	public void addTileType(Tile tile) {
		tileTypes.put(tile.getPosition(), tile.getType());
	}
	
	/**
	 * Return the tile type of the tile with given position.
	 * @param 	leftX
	 * 			The X position of the tile to get the type from.
	 * @param 	bottomY
	 * 			The Y position of the tile to get the type from.
	 * @throws	IllegalTileException
	 * 			The given position is not a valid position for a tile in this World.
	 * 			| ! isValidTilePosition(leftX,bottomY)
	 * 			
	 */
	public int getTileType(int leftX, int bottomY) throws IllegalTileException {
		if (!isValidTilePosition(leftX, bottomY, getTileSize()))
			throw new IllegalTileException(leftX, bottomY);
		int[] position = new int[]{leftX,bottomY};
		return tileTypes.get(position).getInt();
	}
	
	/**
	 * Variable registering the different tileTypes for each tile in the this World.
	 */
	private final Map<int[], TileType> tileTypes = new HashMap<int[], TileType>();
}