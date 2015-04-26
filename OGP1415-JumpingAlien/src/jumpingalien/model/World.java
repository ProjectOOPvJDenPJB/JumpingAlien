package jumpingalien.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * @author Joren Dhont (Ingenieurswetenschappen: Computerwetenschappen - Elektrotechniek) 
 * 	& Pieterjan Beerden (Ingenieurswetenschappen: Elektrotechniek - Computerwetenschappen)
 * @version 0.1
 */
public class World {
	
	public World(int tileSize, int nbTilesX, int nbTilesY, int visibleWindowWidth, int visibleWindowHeight,
			int targetTileX, int targetTileY) {
		assert isValidTileSize(tileSize, nbTilesX * tileSize, nbTilesY * tileSize);
		assert isValidTilePosition(targetTileX, targetTileY, tileSize);
		// Aangezien we me nbTiles werken, is dit eigenlijk ni nodig.
		this.tileSize = tileSize;
		this.nbTilesX = nbTilesX;
		this.nbTilesY = nbTilesY;
		assert isValidVisibleWindow(visibleWindowWidth, visibleWindowHeight);
		this.visibleWindowWidth = visibleWindowWidth;
		this.visibleWindowHeight = visibleWindowHeight;
		this.targetTileX = targetTileX;
		this.targetTileY = targetTileY;
		this.tileTypes = new int[getNbTilesX()][getNbTilesY()];
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
	public final int nbTilesX;

	/**
	 * Return the amount of tiles on the Y axis of this world.
	 */
	public int getNbTilesY() {
		return nbTilesY;
	}
	/**
	 * Variable registering the amount of tiles on the Y axis of this world.
	 */
	public final int nbTilesY;

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
	 * Return the X position of the target tile in this World.
	 */
	public int getTargetTileX() {
		return this.targetTileX;
	}
	
	/**
	 * Variable registering the X position of the target tile of this World.
	 */
	private final int targetTileX;
	
	/**
	 * Return the Y position of the target tile in this World.
	 */
	public int getTargetTileY() {
		return this.targetTileY;
	}
	
	/**
	 * Variable registering the Y position of the target tile of this World.
	 */
	private final int targetTileY;
		
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
	 * Return the position of the bottom left pixel of the given tile.
	 * @param 	tileX
	 * @param 	tileY
	 * @return 	...
	 * 			| [tileX * getTileSize(), tileY * getTileSize()]
	 */
	public int[] getBottomLeftPixelOfTile(int tileX, int tileY) {
		return new int[]{tileX*getTileSize(), tileY*getTileSize()};
	}
	
	/**
	 *  Returns the position of the tile in which the given position is situated.
	 * @param 	pixelX
	 * 			The X coordinate of the given position.
	 * @param 	pixelY
	 * 			The Y coordinate of the given position.
	 * @post	if (pixelX > getPixelWidth())
	 * 				then (position[0] = getPixelWidth())
	 * @post	if (pixelY > getPixelHeight())
	 * 				then (position[1] = getPixelHeigth())
	 * @return	An array of integers where position[0] is the x_T position
	 * 			and position [1] is the x_Y position
	 */
	public int[] getTilePosition(int pixelX,int pixelY) {
		if (Util.fuzzyGreaterThanOrEqualTo(pixelX, this.getPixelWidth()))
			pixelX = this.getPixelWidth();
		if (Util.fuzzyGreaterThanOrEqualTo(pixelY, this.getPixelHeight()))
			pixelY = this.getPixelHeight();
		int[] position = new int[2];
		position[0] = (pixelX / getTileSize());
		position[1] = (pixelY / getTileSize());
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
	 * Check whether the given position is a valid position in the world.
	 * @param 	leftX
	 * @param 	bottomY
	 * @param 	world
	 * @return 	...
	 * 			| result ==
	 * 			|	(leftX < 0) || (leftX > world.getPixelWidth()) || (bottomY < 0) || 
				|	(bottomY > world.getPixelHeight())
	 */
	public static boolean isValidPosition(int leftX, int bottomY, World world) {
		return ((leftX < 0) || (leftX > world.getPixelWidth()) || (bottomY < 0) || 
				(bottomY > world.getPixelHeight()));
	}
	
	/**
	 * Adds a tile type to the map of tile types.
	 * @param 	tile
	 * 			The tile of which the tile type has to be added.
	 * @pre		...
	 * 			| isValidTilePosition(tileX, tileY, getTileSize)
	 * @post	...
	 * 			| new.getTileType(tile.getLeftX(),tile.getBottomY()) == tile.getType
	 */
	@Raw
	public void addTileType(int tileX, int tileY,int tileType) {
		assert isValidTilePosition(tileX, tileY, getTileSize());
		Tile tile = new Tile(tileX,tileY,tileType);
		tileTypes[tileX][tileY] =  tileType;
		tiles.add(tile);
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
	 */
	public int getTileType(int leftX, int bottomY) throws IllegalXPositionException, IllegalYPositionException {
		if (!Position.isValidXPosition(leftX, this.getPixelWidth()))
			throw new IllegalXPositionException(leftX);
		if (!Position.isValidYPosition(bottomY, this.getPixelHeight()))
			throw new IllegalYPositionException(bottomY);
		int[] position = getTilePosition(leftX, bottomY);
		return this.tileTypes[position[0]][position[1]];
	}
	
	/**
	 * Return whether the tile of the given position is passable.
	 * @param 	leftX
	 * @param 	bottomY
	 * @return	...
	 * 			| return (tileTypes[tile_X][tile_Y] != 1)
	 */
	public boolean isPassable(int leftX,int bottomY){
		int[] position = getTilePosition(leftX, bottomY);
		return (this.tileTypes[position[0]][position[1]] != 1);
	}
	
	/**
	 * Variable registering the different tileTypes for each tile in the this World.
	 */
	private int[][] tileTypes;
	
	
	private final Set<Tile> tiles = new HashSet<Tile>();

	public Collection<Tile> getTiles(){
		Collection<Tile> elements = tiles;
		return new ArrayList<Tile>(elements);
	}
	/**
	 * Return the positions of tiles occupied by the given region.
	 * @param 	leftX
	 * @param 	bottomY
	 * @param 	rightX
	 * @param 	topY
	 * @return	...
	 * 			| return tilesInt[][]
	 * 			|	where (tilesInt[i][0] == tile_X)
	 * 			|	and (tilesInt[i][1] == tile_Y)
	 */
	public int[][] getOccupiedTiles1(int leftX, int bottomY, int rightX, int topY) {
		List<int[]> tiles = new ArrayList<int[]>();
		int amountXTiles = getAmountOfOccupiedTiles(leftX, getTileSize(), rightX-leftX );
		int amountYTiles = getAmountOfOccupiedTiles(bottomY, getTileSize(), topY - bottomY);
		int[] botLeftTile = getTilePosition(leftX, bottomY);
		for (int i = botLeftTile[1]; i < amountYTiles; i++) {
			for (int j = botLeftTile[0]; j < amountXTiles; j++) {
				int[] position = new int[]{ j, i};
				tiles.add(position);
			}
		}
		int[][] tilesInt = new int[tiles.size()][2];
		for (int i = 0; i < tiles.size(); i++) {
		    tilesInt[i][0] = tiles.get(i)[0];
		    tilesInt[i][1] = tiles.get(i)[1];
		}
		return tilesInt;
	}	
		
	
	public Collection<Slime> getSlihsdhmes(){
		Collection<Slime> elements = slimes;
		return new ArrayList<Slime>(elements);
	}
	
	public List<Tile> getOccupiedTiles(int leftX, int bottomY, int rightX, int topY){
		List<Tile> occupiedTiles = new ArrayList<Tile>();
		for  (Tile tile : this.getTiles()){
			if ((((tile.getLeftX() >= leftX) && (tile.getLeftX() <= rightX)) 
					&& (((tile.getBottomY() >= bottomY) && (tile.getBottomY() < topY) )
					|| ((tile.getBottomY() + this.getTileSize() > bottomY) && (tile.getBottomY() + this.getTileSize() <= topY)))) 
					|| 
					(   ((tile.getLeftX() +this.getTileSize() >= leftX) && (tile.getLeftX()+this.getTileSize() <= rightX)) 
					&& (((tile.getBottomY() >= bottomY) && (tile.getBottomY() < topY)) 
					|| ((tile.getBottomY() + this.getTileSize() > bottomY) && (tile.getBottomY() + this.getTileSize() <= topY))))){
				occupiedTiles.add(tile);
			}	
		}
		System.out.println(occupiedTiles);

		return occupiedTiles;
	}
	
	/**
	 * Return the amount of occupied tiles with given startposition
	 * 	 and length and tileLength.
	 * @param 	position
	 * @param 	tileLength
	 * @param 	length
	 * @return	| if (position % tileLength == 0)
	 *			|	return length / tileLength + 1
	 *			| else
	 *			|	return length / tileLength
	 */
	public static int getAmountOfOccupiedTiles(int position, int tileLength, int length) {
		if (position % tileLength == 0) {
			return (length / tileLength) + 1;
		}
		else
			return length / tileLength;
	}
	
	/**
	 * Checks whether the given window with given width and height is a valid
	 * 	visible window.
	 * @param 	windowWidth
	 * @param 	windowHeight
	 * @return	...
	 * 			| return ((winwowWidth <= getPixelWidth()) && (windowHeight <= getPixelHeight())
	 * 			|	&& (windowWidth => 0) && (windowHeight >= 0))
	 */
	public boolean isValidVisibleWindow(int windowWidth, int windowHeight) {
		return ((Util.fuzzyLessThanOrEqualTo(windowWidth, getPixelWidth())) && (Util.fuzzyLessThanOrEqualTo(windowHeight, getPixelHeight()))
				&& (Util.fuzzyGreaterThanOrEqualTo(windowWidth, 0)) && (Util.fuzzyGreaterThanOrEqualTo(windowHeight, 0)));
	}
	
	/**
	 * Return the width of the visible window of this World.
	 */
	public int getVisibleWindowWidth() {
		return this.visibleWindowWidth;
	}
	
	/**
	 * Variable registering the width of the visible window of this World.
	 */
	private final int visibleWindowWidth;
	
	/**
	 * Return the height of the visible window of this World.
	 */
	public int getVisibleWindowHeight() {
		return this.visibleWindowHeight;
	}
	
	/**
	 * Variable registering the height of the visible window of this World.
	 */
	private final int visibleWindowHeight;
	
	/**
	 * Return the position of the visible window of this World.
	 */
	public Position getVisibleWindowPosition() {
		return this.visibleWindowPosition;
	}
	
	/**
	 * Variable registering the position of the visible window of this World.
	 */
	private Position visibleWindowPosition = new Position(0,0,this);
	
	/**
	 * Set the new position of the visible window to the given position.
	 * @param 	leftX
	 * @param 	bottomY
	 * @post	...
	 * 			| if (leftX < 0)
	 * 			|	then new.getVisibleWindowPosition().getXPosition() == 0
	 * @post	...
	 * 			| if (leftX > (getPixelWidth() - getVisibleWindowWidth())
	 * 			|	then new.getVisibleWindowPosition().getXPosition() 
	 * 			|		== getPixelWidth() - getVisibleWindowWidth()
	 * @post	...
	 * 			| if (bottomY < 0)
	 * 			|	then new.getVisibleWindowPosition().getYPosition() == 0
	 * @post	...
	 * 			| if (bottomY > (getPixelHeight() - getVisibleWindowHeight())
	 * 			|	then new.getVisibleWindowPosition().getYPosition() 
	 * 			|		== getPixelHeight() - getVisibleWindowHeight()
	 * @post	...
	 * 			| if (leftX > 0) && (leftX < (getPixelWidth() - getVisibleWindowWidth())
	 * 			|		&& (BottomY > 0) && (bottomY < (getPixelHeight() - getVisibleWindowHeight())
	 * 			| 	then new.getVisibleWindowPosition().getPosition() == [leftX,bottom]
	 */
	public void setWindowPosition(double leftX, double bottomY) {
		if (leftX < 0)
			leftX = 0;
		else if (leftX > (getPixelWidth() - getVisibleWindowWidth()))
			leftX = (getPixelWidth() - getVisibleWindowWidth());
		if (bottomY < 0)
			bottomY = 0;
		else if (bottomY > (getPixelHeight() - getVisibleWindowHeight()))
			bottomY = (getPixelHeight() - getVisibleWindowHeight());
		visibleWindowPosition = new Position(leftX, bottomY, this);
	}
	
	/**
	 * Updates the visible window position based on the position of Mazub.
	 * @post	...
	 * 			| if (getMazub().getXPosition() < getVisibleWindowPosition().getXPosition() + 200)
	 * 			|	then new.getVisibleWindowPosition.getXPosition == 
	 * 			|		getMazub().getXPosition() - 200
	 * @post	...
	 * 			| if (getMazub().getXPosition() > (getVisibleWindowPosition().getXPosition()
	 * 			|		+ getVisibleWindowWidth() - 200))
	 * 			|	then new.getVisibleWindowPosition.getXPosition ==
	 * 			|		getMazub().getXPosition() - (getVisibleWindowPosition().getXPosition()
	 * 			|		+ getVisibleWindowWidth() - 200)
	 * @post	...
	 * 			| if (getMazub().getYposition() < getVisibleWindowPosition().getYPosition() + 200)
	 * 			|	then new.getVisibleWindowPosition.getYPosition == 
	 * 			|		getMazub().getYPosition() - 200
	 * @post	...
	 * 			| if (getMazub().getYPosition() > (getVisibleWindowPosition().getYPosition()
	 * 			|		+ getVisibleWindowHeight() - 200))
	 * 			|	then new.getVisibleWindowPosition.getYPosition ==
	 * 			|		getMazub().getYPosition() - (getVisibleWindowPosition().getYPosition()
	 * 			|		+ getVisibleWindowHeight() - 200)
	 */
	public void updateWindowPosition() {
		Position mazubPos = getMazub().getPosition();
		Position windowPos = getVisibleWindowPosition();
		if (mazubPos.getXPosition() < windowPos.getXPosition() + 200) {
			setWindowPosition(mazubPos.getXPosition() - 200, windowPos.getYPosition());
		}
		else if (mazubPos.getXPosition() > (windowPos.getXPosition() + getVisibleWindowWidth()) - 200) {
			setWindowPosition(windowPos.getXPosition() +
					(mazubPos.getXPosition() -
							(windowPos.getXPosition() + getVisibleWindowWidth() - 200)), 
					windowPos.getYPosition());
		}
		windowPos = getVisibleWindowPosition();
		if (mazubPos.getYPosition() < windowPos.getYPosition() +200){
			setWindowPosition(windowPos.getXPosition(), mazubPos.getYPosition() - 200);
		}
		else if (mazubPos.getYPosition() > (windowPos.getYPosition() + getVisibleWindowHeight() -200))
			setWindowPosition(windowPos.getXPosition(), mazubPos.getYPosition() + 200);
	}
	
	/**
	 * Return the positions of the visible window.
	 * @return	...
	 * 			| pos[]
	 * 			| where
	 * 			|	pos[0] == getVisibleWindowPosition().getXPosition()
	 * 			|	pos[1] == getVisibleWindowPosition().getYPosition()
	 * 			|	pos[2] == getVisibleWindowPosition().getXPosition() + getVisibleWindowWidth()
	 * 			|	pos[3] == getVisibleWindowPosition().getYPosition() + getVisibleWindowHeight()
	 * 			
	 */
	public int[] getVisibleWindowPositionArray() {
		int[] pos = new int[] {(int) getVisibleWindowPosition().getXPosition(),
			(int) getVisibleWindowPosition().getYPosition(),
			(int) getVisibleWindowPosition().getXPosition()+getVisibleWindowWidth(),
			(int) getVisibleWindowPosition().getYPosition()+getVisibleWindowHeight()
		};
		return pos;
	}
	
	/**
	 * Return the amount of objects currently in this world.
	 */
	public int getAmountOfObjects() {
		return getSlimes().size() + getPlants().size() + getSharks().size() + 1;
	}
	
	/**
	 * Checks whether the given world has a valid amount of objects.
	 * @param 	world
	 * @return	...
	 * 			| return (world.getAmountOfObjects() < 100) &&
	 * 			|	(world.getAmountOfObjects() >=0)
	 */
	public static boolean hasValidAmountOfObjects(World world) {
		int size = world.getAmountOfObjects();
		return (size < 100) && (size >= 0);
	}
	
	/**
	 * Return the plants currently in this world in the form of a collection.
	 */
	public Collection<Plant> getPlants(){
		Collection<Plant> elements = plants;
		return new ArrayList<Plant>(elements);
	}
	
	/**
	 * Checks whether this world contains the given plant.
	 * @param 	plant
	 * @return	...
	 * 			| return plants.contains(plant)
	 */
    public boolean hasAsPlant(Plant plant) {
       return plants.contains(plant);
    }
	
    /**
     * Variable registering the plants currently in this world.
     */
	private final Set<Plant> plants = new HashSet<Plant>();
	
	/**
	 * Adds the given plant to this world.
	 * @param 	plant
	 * @pre		...
	 * 			| plant.canHaveAsWorld(this)
	 * @post	...
	 * 			| new.hasAsPlant(plant) == true
	 * @post	...
	 * 			| plant.getWorld() == this
	 */
	public void addPlant(Plant plant) {
		assert plant.canHaveAsWorld(this);
		plants.add(plant);
		plant.setWorld(this);
	}
	
	/**
	 * Removes the given plant from this world.
	 * @param 	plant
	 * @pre		...
	 * 			| (hasAsPlant(plant) && plant.hasAsWorld(this))
	 * @post	...
	 * 			| new.hasAsPlant(plant) == false
	 */
	public void removePlant(Plant plant) {
		assert this.hasAsPlant(plant) && (plant.hasAsWorld(this));
		plants.remove(plant);
	}
	
	/**
	 * Return the slimes currently in this world in the form of a collection.
	 */
	public Collection<Slime> getSlimes(){
		Collection<Slime> elements = slimes;
		return new ArrayList<Slime>(elements);
	}
	
	/**
	 * Checks whether this world contains the given slime.
	 * @param 	slime
	 * @return	...
	 * 			| return slimes.contains(slime)
	 */
	public boolean hasAsSlime(Slime slime) {
        return slimes.contains(slime);

    }
	
	/**
     * Variable registering the slimes currently in this world.
     */
	private final Set<Slime> slimes = new HashSet<Slime>();
	
	/**
	 * Adds the given slime to this world.
	 * @param 	slime
	 * @pre		...
	 * 			| slime.canHaveAsWorld(this)
	 * @post	...
	 * 			| new.hasAsSlime(slime) == true
	 * @post	...
	 * 			| slime.getWorld() == this
	 */
	public void addSlime(Slime slime) {
		assert slime.canHaveAsWorld(this);
		if (!Interaction.interactWithMovementBlockingCreature(slime,this)){
			slimes.add(slime);
			slime.setWorld(this);
		}
	}
	
	/**
	 * Removes the given slime from this world.
	 * @param 	slime
	 * @pre		...
	 * 			| (hasAsSlime(slime) && slime.hasAsWorld(this))
	 * @post	...
	 * 			| new.hasAsSlime(slime) == false
	 */
	public void removeSlime(Slime slime) {
		assert this.hasAsSlime(slime) && (slime.hasAsWorld(this));
		slimes.remove(slime);
	}
	
	/**
	 * Return the sharks currently in this world in the form of a collection.
	 */
	public Collection<Shark> getSharks(){
		Collection<Shark> elements = sharks;
		return new ArrayList<Shark>(elements);
	}
	
	/**
	 * Checks whether this world contains the given shark.
	 * @param 	shark
	 * @return	...
	 * 			| return sharks.contains(shark)
	 */
	public boolean hasAsShark(Shark shark) {
          return sharks.contains(shark);
    }
	
	/**
     * Variable registering the sharks currently in this world.
     */
	private final Set<Shark> sharks = new HashSet<Shark>();
	
	/**
	 * Adds the given shark to this world.
	 * @param 	shark
	 * @pre		...
	 * 			| shark.canHaveAsWorld(this)
	 * @post	...
	 * 			| new.hasAsShark(shark) == true
	 * @post	...
	 * 			| shark.getWorld() == this
	 */
	public void addShark(Shark shark) {
		assert shark.canHaveAsWorld(this);
		sharks.add(shark);
		shark.setWorld(this);
	}
	
	/**
	 * Removes the given shark from this world.
	 * @param 	shark
	 * @pre		...
	 * 			| (hasAsShark(shark) && shark.hasAsWorld(this))
	 * @post	...
	 * 			| new.hasAsShark(shark) == false
	 */
	public void removeShark(Shark shark) {
		assert this.hasAsShark(shark) && (shark.hasAsWorld(this));
		sharks.remove(shark);
	}
	
	/**
	 * Check whether the given object is of a valid type for this world.
	 * @param 	object
	 * @return	If the given object is of a valid type for this world,
	 * 			true is returned. Otherwise false is returned.
	 */
	public boolean isValidObject(Object object) {
		return (object instanceof LivingCreatures);
	}
	
	/**
	 * Checks whether the given object can be in this world.
	 * @param 	object
	 * @pre		...
	 * 			| isValidObject(object)
	 * @return 	...
	 *			| if (this.isTerminated())
	 *			|	return object == null;
	 *			| else
	 * 			|	 return ((object != null) && 
	 * 			|		((object.canHaveAsWorld(this) &&
	 * 			|		hasValidAmountOfObjects(this)
	 */
	public boolean canHaveAsObject(Object object) {
		assert isValidObject(object);
		if (isTerminated())
			return object == null;
		else
			return (object != null) && (((LivingCreatures) object).canHaveAsWorld(this)) 
					&& (hasValidAmountOfObjects(this));
	}
	
	/**
	 * Return the Mazub currently in this world.
	 */
	public Mazub getMazub() {
		return this.mazub;
	}
	
	/**
	 * Variable registering the Mazub in this world.
	 * 	The default value is null.
	 */
	private Mazub mazub = null;
		
	/**
	 * Sets the given Mazub in this world.
	 * @param 	alien
	 * @pre		...
	 * 			| canHaveAsMazub(alien)
	 * @post	...
	 * 			| new.getMazub() == alien
	 * @post	...
	 * 			| alien.getWorld() == this
	 */
	public void setMazub(Mazub alien) {
		assert canHaveAsMazub(alien);
		mazub = alien;
		mazub.setWorld(this);
	}
	
	/**
	 * Checks whether this world can have the given Mazub as its Mazub.
	 * @param 	alien
	 * @return 	...
	 *			| if (this.isTerminated())
	 *			|	return alien == null;
	 *			| else
	 * 			|	 return ((alien != null) && 
	 * 			|		((alien.canHaveAsWorld(this) &&
	 * 			|		(getMazub() == null)
	 */
	public boolean canHaveAsMazub(Mazub alien) {
		if (isTerminated())
			return alien == null;
		return (alien != null) && (alien.canHaveAsWorld(this) && (getMazub() == null));
	}
	
	/**
	 * Check whether this world is terminated.
	 */
	@Basic @Raw
	public boolean isTerminated() {
		return this.getState() == State.TERMINATED;
	}
	
	/**
	 * In-class enumeration of the different States of a world.
	 */
	private static enum State {
		INITIALISED, STARTED, TERMINATED;
	}
	
	/**
	 * Return the state of this world.
	 */
	@Raw
	private State getState() {
		return this.state;
	}
	

	/**
	 * Variable registering the state of this world.
	 */
	private State state = State.INITIALISED;
	
	/**
	 * Set the state of this world to the given state.
	 * 
	 * @param  state
	 *         The new state for this world.
	 * @pre    The given state must be effective.
	 *       | state != null
	 * @post   The state of this world is the same as the
	 *         given state.
	 *       | new.getState() == state
	 */
	private void setState(State state) {
		assert (state != null);
		this.state = state;
	}
	
	/**
	 * Advances the time with a given timeInterval and changes every time-related attribute of this World
	 * 	accordingly.
	 * @param 	dt
	 * @post	AdvanceTime of the Mazub of this world was executed.
	 * @post	The visible window position was updated.
	 * @post	For every living creature in this world, advanceTime was executed.
	 */
	public void advanceTime(double dt) {
		//System.out.println("MAZUB");
		mazub.advanceTime(dt);
		updateWindowPosition();
		for (Plant plant : this.getPlants()) {
//			System.out.println("PLANT?");
			plant.advanceTime(dt);
		}
		for (Slime slime : this.getSlimes()) {
			slime.advanceTime(dt);
		}
		for (Shark shark : this.getSharks()) {
			shark.advanceTime(dt);
		}
	}
	public boolean isTerminating(){
		return this.terminating;
	}
	
	private boolean terminating = false;
	
	public void setTerminating(boolean flag){
		this.terminating = true;
	}
	
	/**
	 * Terminates this world.
	 * TODO
	 */
	public void terminate() {
		if (! isTerminated()) {
			this.setTerminating(true);
			for (Plant plant : getPlants()) {
				plant.terminate();
			}
			for (Slime slime : getSlimes()) {
				slime.terminate();
			}
			for (Shark shark : getSharks()) {
				shark.terminate();
			}
			
			this.setState(State.TERMINATED);
				
		}
	}
	
	/**
	 * Checks whether the player (Mazub) has won the game.
	 * @return	If Mazub occupies the target tile of this World, true is returned
	 * 			otherwise false is returned.
	 */
	public boolean hasWonGame() {
		int[] mazubPos = getMazub().getPosition().getPosition();
		int[] mazubTile = getTilePosition(mazubPos[0], mazubPos[1]);
		if ((getTargetTileX() == mazubTile[0]) && (getTargetTileY() == mazubTile[1]))
			return true;
		else
			return false;
	}
	
	//Dit moet eigenlijk nog met State.INITIALISED gedaan worden
	public void setGameStarted(boolean flag){
		this.gameStarted = flag;
	}
	
	private boolean gameStarted = false;
	
	public boolean gameStarted(){
		return this.gameStarted;
	}
	
}
