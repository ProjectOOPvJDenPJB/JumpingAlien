package jumpingalien.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		setNbTilesX(nbTilesX);
		setNbTilesY(nbTilesY);
		assert isValidVisibleWindow(visibleWindowWidth, visibleWindowHeight);
		this.visibleWindowWidth = visibleWindowWidth;
		this.visibleWindowHeight = visibleWindowHeight;
		this.targetTileX = targetTileX;
		this.targetTileY = targetTileY;
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
	

	public void setWindowPosition(double leftX, double bottomY) throws IllegalXPositionException, IllegalYPositionException {
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
	
	public int[] getVisibleWindowPositionArray() {
		int[] pos = new int[] {(int) getVisibleWindowPosition().getXPosition(),
			(int) getVisibleWindowPosition().getYPosition(),
			(int) getVisibleWindowPosition().getXPosition()+getVisibleWindowWidth(),
			(int) getVisibleWindowPosition().getYPosition()+getVisibleWindowHeight()
		};
		return pos;
	}
	
	/**
	 *  Returns the position of the tile in which the given position is situated.
	 * @param 	pixelX
	 * 			The X coordinate of the given position.
	 * @param 	pixelY
	 * 			The Y coordinate of the given position.
	 */
	public int[] getTilePosition(int pixelX,int pixelY) {
		if (Util.fuzzyGreaterThanOrEqualTo(pixelX, this.getPixelWidth()))
			pixelX = this.getPixelWidth() - 1;
		if (Util.fuzzyGreaterThanOrEqualTo(pixelY, this.getPixelHeight()))
			pixelY = this.getPixelHeight() -1;
		int[] position = new int[2];
		position[0] = (pixelX - (pixelX % getTileSize()));
		position[1] = (pixelY - (pixelY % getTileSize()));
		return position;
	}
	
	public Tile getTile() {
		return null;
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
	
	public static boolean isValidPosition(int leftX, int bottomY, World world) {
		return ((leftX < 0) || (leftX > world.getPixelWidth()) || (bottomY < 0) || 
				(bottomY > world.getPixelHeight()));
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
		tileTypes.put(tile.getPositionString(), tile.getType());
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
	public int getTileType(int leftX, int bottomY) throws IllegalXPositionException, IllegalYPositionException {
		if (!Position.isValidXPosition(leftX, this.getPixelWidth()))
			throw new IllegalXPositionException(leftX);
		if (!Position.isValidYPosition(bottomY, this.getPixelHeight()))
			throw new IllegalYPositionException(bottomY);
		int[] position = getTilePosition(leftX, bottomY);
		String posString = ("("+position[0]+","+position[1]+")");
		return tileTypes.get(posString).getInt();
	}
	
	public boolean isPassable(int leftX,int bottomY){
		int[] position = getTilePosition(leftX, bottomY);
		String posString = ("("+position[0]+","+position[1]+")");
		if (!tileTypes.get(posString).getPassable()) {
			if ((bottomY % getTileSize() == getTileSize()-1) &&
					(isPassable(leftX, bottomY+1)))
				return true;
			else
				return false;
		}
		else
			return true;
	}
	
	/**
	 * Variable registering the different tileTypes for each tile in the this World.
	 */
	private final Map<String, TileType> tileTypes = new HashMap<String, TileType>();
	
	
	public static boolean hasValidAmountOfObjects(World world) {
		int size = world.slimes.size() + world.plants.size() + world.sharks.size();
		return (size < 100) && (size >= 0);
	}
	
	public Collection<Plant> getPlants(){
		Collection<Plant> elements = plants.values();
		return new ArrayList<Plant>(elements);
	}
	
    public boolean hasAsPlant(Plant plant) {
        try {
            return plants.get(plant.getKey()) == plant;
        }
        catch (NullPointerException exc) {
        	// The plant either does not exist or is not a yet assigned
        	// to a world thus does not have a key.
            assert (plant == null) || (plant.getKey() == null);
            return false;
        }
    }
	
	private final Map<String,Plant> plants = new HashMap<String,Plant>();
	
	public void addPlant(Plant plant) {
		assert plant.canHaveAsWorld(this);
		plant.setKey("Plant"+plants.size());
		plants.put(plant.getKey(), plant);
		plant.setWorld(this);
	}
	
	public void removePlant(Plant plant) {
		assert this.hasAsPlant(plant) && (plant.hasAsWorld(this));
		plants.remove(plant.getKey());
	}
	
	public Collection<Slime> getSlimes(){
		Collection<Slime> elements = slimes.values();
		return new ArrayList<Slime>(elements);
	}
	
	public boolean hasAsSlime(Slime slime) {
        try {
            return slimes.get(slime.getKey()) == slime;
        }
        catch (NullPointerException exc) {
        	// The slime either does not exist or is not yet assigned
        	// to a world thus does not have a key.
            assert (slime == null) || (slime.getKey() == null);
            return false;
        }
    }
	
	private final Map<String,Slime> slimes = new HashMap<String,Slime>();
	
	public void addSlime(Slime slime) {
		assert slime.canHaveAsWorld(this);
		slime.setKey("Slime"+slimes.size());
		slimes.put(slime.getKey(), slime);
		slime.setWorld(this);
	}
	
	public void removeSlime(Slime slime) {
		assert this.hasAsSlime(slime) && (slime.hasAsWorld(this));
		slimes.remove(slime.getKey());
	}
	
	public Collection<Shark> getSharks(){
		Collection<Shark> elements = sharks.values();
		return new ArrayList<Shark>(elements);
	}
	
	public boolean hasAsShark(Shark shark) {
        try {
            return sharks.get(shark.getKey()) == shark;
        }
        catch (NullPointerException exc) {
        	// The shark either does not exist or is not yet assigned
        	// to a world thus does not have a key.
            assert (shark == null) || (shark.getKey() == null);
            return false;
        }
    }
	
	private final Map<String,Shark> sharks = new HashMap<String,Shark>();
	
	public void addShark(Shark shark) {
		assert shark.canHaveAsWorld(this);
		shark.setKey("Shark"+sharks.size());
		sharks.put(shark.getKey(), shark);
		shark.setWorld(this);
	}
	
	public void removeShark(Shark shark) {
		assert this.hasAsShark(shark) && (shark.hasAsWorld(this));
		sharks.remove(shark.getKey());
	}
	
	public boolean canHaveAsObject(Object object) {
		assert hasValidAmountOfObjects(this);
		assert (object instanceof LivingCreatures);
		if (isTerminated())
			return object == null;
		return (object != null) && (((LivingCreatures) object).canHaveAsWorld(this));
	}
	
	private Mazub getMazub() {
		return this.mazub;
	}
	
	private Mazub mazub = null;
		
	public void setMazub(Mazub alien) {
		assert canHaveAsMazub(alien);
		mazub = alien;
		mazub.setWorld(this);
	}
	
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
	
	public void advanceTime(double dt) {
//		System.out.println("MAZUB");
		mazub.advanceTime(dt);
		updateWindowPosition();
		for (String key : plants.keySet()) {
//			System.out.println("PLANT?");
			plants.get(key).advanceTime(dt);
		}
		for (String key : slimes.keySet()) {
			slimes.get(key).advanceTime(dt);
		}
		for (String key : sharks.keySet()) {
//			System.out.println("shark");
			sharks.get(key).advanceTime(dt);
		}
	}

	public void terminate() {
		if (! isTerminated()) {
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
	
	public boolean hasWonGame() {
		int[] mazubPos = getMazub().getPosition().getPosition();
		int[] mazubTile = getTilePosition(mazubPos[0], mazubPos[1]);
		if ((getTargetTileX() == mazubTile[0]) && (getTargetTileY() == mazubTile[1]))
			return true;
		else
			return false;
	}
	
	public int[][] getOccupiedTiles(int leftX, int bottomY, int rightX, int topY) {
		List<int[]> tiles = new ArrayList<int[]>();
		int amountXTiles = getAmountOfOccupiedTiles(leftX, getTileSize(), rightX-leftX );
		int amountYTiles = getAmountOfOccupiedTiles(bottomY, getTileSize(), topY - bottomY);
		for (int i = 0; i < amountYTiles; i++) {
			for (int j = 0; j < amountXTiles; j++) {
				int[] position = new int[]{leftX + (j* getTileSize()),bottomY + (i*getTileSize())};
				tiles.add(position);
			}
		}
		int[][] tilesInt = new int[tiles.size()][];
		for (int i = 0; i < tiles.size(); i++) {
		    int[] row = tiles.get(i);
		    tilesInt[i] = row;
		}
		return tilesInt;
	}
	
	public static int getAmountOfOccupiedTiles(int position, int tileLength, int length) {
		if (position % tileLength == 0) {
			return (length / tileLength) + 1;
		}
		else
			return length / tileLength;
	}
	
	public void setGameStarted(boolean flag){
		this.gameStarted = flag;
	}
	
	private boolean gameStarted = false;
	
	public boolean gameStarted(){
		return this.gameStarted;
	}
	
}
