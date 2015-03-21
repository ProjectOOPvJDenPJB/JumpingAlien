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
	 * TODO
	 * @param pixelX
	 * @param pixelY
	 * @return
	 */
	public int[] getTile(int pixelX,int pixelY) {
		int[] position = new int[2];
		position[0] = (pixelX - (pixelX % tileSize));
		position[1] = (pixelY - (pixelY % tileSize));
		return position;
	}
	
	/**
	 * TODO
	 * @param tileX
	 * @param tileY
	 * @param tileSize
	 * @return
	 */
	public static boolean isValidTilePosition(int tileX, int tileY, int tileSize) {
		return (tileX % tileSize == 0) && (tileY % tileSize == 0);
	}
	
	/**
	 * TODO
	 * @param tile
	 */
	@Raw
	public void addTileType(Tile tile) {
		tileTypes.put(tile.getPosition(), tile.getType());
	}
	
	/**
	 * TODO
	 * @param leftX
	 * @param bottomY
	 * @return
	 */
	public int getTileType(int leftX, int bottomY) throws IllegalTileException {
		if (!isValidTilePosition(leftX, bottomY, getTileSize()))
			throw new IllegalTileException(leftX, bottomY);
		int[] position = new int[]{leftX,bottomY};
		return tileTypes.get(position).getInt();
	}
	
	/**
	 * TODO
	 */
	private final Map<int[], TileType> tileTypes = new HashMap<int[], TileType>();
}
