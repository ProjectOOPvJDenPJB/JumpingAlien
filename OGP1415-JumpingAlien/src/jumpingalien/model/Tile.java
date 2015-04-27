package jumpingalien.model;



public class Tile {
	
	/**
	 * Initializes a new tile with given position and tileType.
	 * @param 	leftX
	 * 			The X position of this tile.
	 * @param 	bottomY
	 * 			The Y position of this tile.
	 * @param 	tileType
	 * 			The tiletype of this tile.
	 * @post	The position of this tile is set to the given position.
	 * 			| new.getLeftX() == leftX
	 * 			|	&& new.getBottomY() == bottomY
	 * @post	The tileType of this tile is set to the given tileType
	 * 			| new.getType() == tileType
	 */
	public Tile(int leftX, int bottomY, TileType tileType) {
		this.leftX = leftX;
		this.bottomY = bottomY;
		this.type = tileType;
	}
	
	/**
	 * Initializes a new tile with given position and type in the form of an integer.
	 * @param 	leftX
	 * 			The X position of this tile.
	 * @param 	bottomY
	 * 			The Y position of this tile.
	 * @param 	tileType
	 * 			The tiletype of this tile.
	 * @post	The position of this tile is set to the given position.
	 * 			| new.getLeftX() == leftX
	 * 			|	&& new.getBottomY() == bottomY
	 * @post	If the given type is 1, the type of this tile is set to ground.
	 * 			| if (tileType == 1)
	 * 			|	then new.getType() == TileType.GROUND
	 * @post	If the given type is 2, the type of this tile is set to water.
	 * 			| if (tileType == 2)
	 * 			|	then new.getType() == TileType.WATER
	 * @post	If the given type is 3, the type of this tile is set to magma.
	 * 			| if (tileType == 3)
	 * 			|	then new.getType() == TileType.MAGMA
	 * @post	If the given type not 1,2 or 3 then the type of this tile is set to air.
	 * 			| if ((tileType !=1) && (tileType !=2) && (tileType !=3))
	 * 			|	then new.getType() == TileType.AIR
	 */
	public Tile(int leftX, int bottomY, int tileType) {
		this.leftX = leftX;
		this.bottomY = bottomY;
		if (tileType == 1) {
			this.type = TileType.GROUND;
		}
		else if (tileType == 2) {
			this.type = TileType.WATER;
		}
		else if (tileType == 3) {
			this.type = TileType.MAGMA;
		}
		else {

			this.type = TileType.AIR;
		}
	}
	
	/**
	 * Initializes this tile with given position.
	 * @param 	leftX
	 * 			The X position of this tile.
	 * @param 	bottomY
	 * 			The Y position of this tile.
	 * @effect	Initializes this tile with given position.
	 * 			The type is set to air.
	 * 			| this(leftX,bottomY,0)
	 */
	public Tile(int leftX, int bottomY) {
		this(leftX,bottomY,0);
	}
	
	/**
	 * Return the X position of the leftmost pixel of this tile.
	 */
	public final int getLeftX() {
		return this.leftX;
	}
	/**
	 * Variable registering the X position of the leftmost pixel of this tile.
	 */
	private final int leftX;
	/**
	 * Return the Y position of the bottom pixel of this tile.
	 */
	public final int getBottomY() {
		return this.bottomY;
	}
	/**
	 * Variable registering the T position of the bottom pixel of this tile.
	 */
	private final int bottomY;
	/**
	 * Return the type of this tile.
	 */
	public final TileType getType() {
		return this.type;
	}
	/**
	 * Variable registering the type of this tile.
	 */
	private final TileType type;
	
	/**
	 * Return the position of this tile in an int array of the form [leftX, bottomY].
	 */
	public final String getPositionString() {
		return ("("+getLeftX()+","+getBottomY()+")");
	}
}
