package jumpingalien.model;

public class Tile {
	
	public Tile(int leftX, int bottomY, TileType tileType) {
		this.leftX = leftX;
		this.bottomY = bottomY;
		this.type = tileType;
	}
	
	public Tile(int leftX, int bottomY, int tileType) {
		if (tileType == 1) {
			this.leftX = leftX;
			this.bottomY = bottomY;
			this.type = TileType.GROUND;
		}
		else if (tileType == 2) {
			this.leftX = leftX;
			this.bottomY = bottomY;
			this.type = TileType.WATER;
		}
		else if (tileType == 3) {
			this.leftX = leftX;
			this.bottomY = bottomY;
			this.type = TileType.MAGMA;
		}
		else {
			this.leftX = leftX;
			this.bottomY = bottomY;
			this.type = TileType.AIR;
		}
	}
	
	public Tile(int leftX, int bottomY) {
		this.leftX = leftX;
		this.bottomY = bottomY;
		this.type = TileType.AIR;
	}
	
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
	 * Return the position of this tile in an int array of the form [leftX, bottomY].
	 */
	public int[] getPosition() {
		int[] position = new int[]{this.getLeftX(),this.getBottomY()};
		return position;
	}
}
