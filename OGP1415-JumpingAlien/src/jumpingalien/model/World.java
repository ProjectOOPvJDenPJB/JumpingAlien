package jumpingalien.model;

import java.util.HashSet;

/**
 * @author Joren Dhont (Ingenieurswetenschappen: Computerwetenschappen - Elektrotechniek) 
 * 	& Pieterjan Beerden (Ingenieurswetenschappen: Elektrotechniek - Computerwetenschappen)
 * @version 0.1
 */
public class World {

	/**
	 * Return the width of this World.
	 * 	The width is expressed in pixels.
	 */
	public int getPixelWidth() {
		return pixelWidth;
	}
	/**
	 * Variable registering the width of this World measured in pixels.
	 */
	public int pixelWidth;
	/**
	 * Set the width of this World to the given width.
	 * @param 	pixelWidth
	 * 			The new width of this World expressed in pixels.
	 * @post	The new width of this World expressed in pixels is equal to the given pixelWidth.
	 */
	public void setPixelWidth(int pixelWidth) {
		this.pixelWidth = pixelWidth;
	}
	/**
	 * Return the height of this World
	 * 	The height is expressed in pixels.
	 */
	public int getPixelHeight() {
		return pixelHeight;
	}
	/**
	 * Variable registering the Height of this World measured in pixels.
	 */
	public int pixelHeight;
	/**
	 * Set the height of this World to the given height.
	 * @param 	pixelHeight
	 *  		The new height of this World expressed in pixels.
	 * @post	The new height of this World expressed in pixels is equal to the given pixelHeight.
	 */
	public void setPixelHeight(int pixelHeight) {
		this.pixelHeight = pixelHeight;
	}
	/**
	 * Return the length of the tiles in this World.
	 * 	The length is expressed in pixels.
	 */
	public int getTileLength() {
		return tileLength;
	}
	/**
	 * Checks whether a given tileLength is a valid tileLength.
	 * @param 	tileLength
	 * 			The length of tiles to check.
	 * @param 	width
	 * 			The width of this World to check against.
	 * @param 	height
	 * 			The height of this World to check against.
	 * @return	...
	 * 			| result ==
	 * 			|	((width % tileLength) == 0) && ((height % tileLength) == 0)
	 */
	public static boolean isValidTileLength(int tileLength, int width, int height) {
		return ((width % tileLength) == 0) && ((height % tileLength) == 0);
	}
	/**
	 * Variable registering the length of the tiles in this World.
	 * 	The length is expressed in pixels.
	 */
	public int tileLength;
	/**
	 * Set the length of tiles in this world to the given length.
	 * @param 	tileLength
	 * 			The new length of the tiles in this World expressed in pixels.
	 * @post	The new length of the tiles in this World expressed in pixels is equal to the given tileLength.
	 */
	public void setTileLength(int tileLength) {
		this.tileLength = tileLength;
	}
	/**
	 * 
	 */
	public HashSet<?> objects;
}
