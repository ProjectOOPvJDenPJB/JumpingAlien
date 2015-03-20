package jumpingalien.model;

public class Tile {
	
	public Tile(int length, int leftX, int bottomY, TileType tileType) {
		this.length = length;
		this.leftX = leftX;
		this.bottomY = bottomY;
		this.type = tileType;
	}
	
	/**
	 * Return the length of this tile.
	 */
	public int getLength() {
		return this.length;
	}
	/**
	 * Variable registering the length of the tile.
	 */
	public final int length;
	/**
	 * Return the X position of the leftmost pixel of this tile.
	 */
	public int getLeftX() {
		return this.leftX;
	}
	/**
	 * Variable registering the X position of the leftmost pixel of this tile.
	 */
	public final int leftX;
	/**
	 * Return the Y position of the bottom pixel of this tile.
	 */
	public int getBottomY() {
		return this.bottomY;
	}
	/**
	 * Variable registering the T position of the bottom pixel of this tile.
	 */
	public final int bottomY;
	/**
	 * Return the type of this tile.
	 */
	public TileType getType() {
		return this.type;
	}
	/**
	 * Variable registering the type of this tile.
	 */
	public final TileType type;
	
	/**
	 * TODO
	 * @param position
	 * @return
	 */
	public boolean getInTile(int[] position) {
		return ((position[0] >= leftX) && (position[0] < leftX + length)) &&
				((position[1] >= bottomY) && (position[1] < bottomY + length));
	}
}
