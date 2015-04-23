package jumpingalien.part2.facade;

import java.util.Collection;

import jumpingalien.model.Direction;
import jumpingalien.model.IllegalSizeException;
import jumpingalien.model.IllegalTimeIntervalException;
import jumpingalien.model.IllegalXPositionException;
import jumpingalien.model.IllegalYPositionException;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.Tile;
import jumpingalien.model.World;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Facade implements IFacadePart2  {

	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) throws jumpingalien.util.ModelException {
		try {
			return new Mazub(pixelLeftX, pixelBottomY, sprites);
		} catch (IllegalXPositionException | IllegalYPositionException exc) {
			throw new jumpingalien.util.ModelException("Illegal positions", exc);
		}
	}

	public int[] getLocation(Mazub alien) {
		return alien.getPosition().getPosition();	
	}

	public double[] getVelocity(Mazub alien) {
		double[] velocity = new double[2];
		velocity[0] = alien.getHorizontalVelocity();
		velocity[1] = alien.getVerticalVelocity();
		return velocity;
	}

	public double[] getAcceleration(Mazub alien) {
		double[] acceleration = new double[2];
		acceleration[0] = alien.getHorizontalAcceleration();
		acceleration[1] = alien.getVerticalAcceleration();
		return acceleration;
	}

	public int[] getSize(Mazub alien) throws jumpingalien.util.ModelException {
		try {
			return alien.getSize();
		} catch (IllegalSizeException exc) {
			throw new jumpingalien.util.ModelException("Illegal size", exc);
		}
	}

	public Sprite getCurrentSprite(Mazub alien) {
		return alien.getCurrentSprite();
	}

	public void startJump(Mazub alien) {
		alien.startJump(alien.getInitialVerticalVelocity(),-10);
	}

	public void endJump(Mazub alien) {
		alien.endJump();
	}

	public void startMoveLeft(Mazub alien) {
		alien.setDirection(Direction.LEFT);
		alien.startMove(0.9);
	}

	public void endMoveLeft(Mazub alien) {
		if (alien.getDirection() == Direction.LEFT) {
			alien.endMove(0);
		}
	}

	public void startMoveRight(Mazub alien) {
		alien.setDirection(Direction.RIGHT);
		alien.startMove(0.9);
	}

	public void endMoveRight(Mazub alien) {
		if (alien.getDirection() == Direction.RIGHT) {
			alien.endMove(0);
		}
	}

	public void startDuck(Mazub alien) {
		alien.startDuck();
	}

	public void endDuck(Mazub alien) {
		alien.endDuck();
	}

 public void advanceTime(Mazub alien, double dt) throws jumpingalien.util.ModelException {
		try {
			alien.advanceTime(dt);
		} catch (IllegalTimeIntervalException exc) {
			throw new jumpingalien.util.ModelException("Illegal time interval", exc);
		}
	}

	@Override
	public int getNbHitPoints(Mazub alien) {
		return alien.getHP();
	}

	@Override
	public World createWorld(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) {
		return new World(tileSize,nbTilesX,nbTilesY,visibleWindowWidth,visibleWindowHeight,
				targetTileX,targetTileY);
	}

	@Override
	public int[] getWorldSizeInPixels(World world) {
		return new int[]{world.getPixelWidth(),world.getPixelHeight()};
	}

	@Override
	public int getTileLength(World world) {
		return world.getTileSize();
	}

	@Override
	public void startGame(World world) {
		world.setGameStarted(true);
	}

	@Override
	public boolean isGameOver(World world) {
		return world.isTerminated();
	}

	@Override
	public boolean didPlayerWin(World world) {
		return world.hasWonGame();
	}

	@Override
	public void advanceTime(World world, double dt) {
		try {
			world.advanceTime(dt);
		} catch (IllegalTimeIntervalException exc) {
			throw new jumpingalien.util.ModelException("Illegal time interval", exc);
		}
	}

	@Override
	public int[] getVisibleWindow(World world) {
		return world.getVisibleWindowPositionArray();
	}

	@Override
	public int[] getBottomLeftPixelOfTile(World world, int tileX, int tileY) {
		return world.getBottomLeftPixelOfTile(tileX, tileY);
	}

	@Override
	public int[][] getTilePositionsIn(World world, int pixelLeft,
			int pixelBottom, int pixelRight, int pixelTop) {
		return world.getOccupiedTiles(pixelLeft, pixelBottom, pixelRight, pixelTop);
	}

	@Override
	public int getGeologicalFeature(World world, int pixelX, int pixelY)
			throws ModelException {
		try {
			return world.getTileType(pixelX, pixelY);
		} catch (IllegalXPositionException | IllegalYPositionException e) {
			throw new ModelException("Coordinates out of bounds.", e);
		}
	}

	@Override
	public void setGeologicalFeature(World world, int tileX, int tileY,
			int tileType) {
		if (!world.gameStarted()){
			world.addTileType(tileX,tileY,tileType);
		}
	}

	@Override
	public void setMazub(World world, Mazub alien) {
		world.setMazub(alien);
	}

	@Override
	public boolean isImmune(Mazub alien) {
		if (alien.getHitTimer() < 0.6){
			return true;
		}else{
		return false;
		}
	}

	@Override
	public void addPlant(World world, Plant plant) {
		if (!world.gameStarted()){
			world.addPlant(plant);
		}
	}

	@Override
	public Collection<Plant> getPlants(World world) {
		return world.getPlants();
	}

	@Override
	public int[] getLocation(Plant plant) {
		return plant.getPosition().getPosition();
	}

	@Override
	public Sprite getCurrentSprite(Plant plant) {
		return plant.getCurrentSprite();
	}

	@Override
	public void addShark(World world, Shark shark) {
		if (!world.gameStarted()){
			System.out.println("WTF");
			world.addShark(shark);
		}
	}

	@Override
	public Collection<Shark> getSharks(World world) {
		return world.getSharks();
	}

	@Override
	public int[] getLocation(Shark shark) {
		return shark.getPosition().getPosition();
	}

	@Override
	public Sprite getCurrentSprite(Shark shark) {
		return shark.getCurrentSprite();
	}

	@Override
	public void addSlime(World world, Slime slime) {
		if (!world.gameStarted()){
			world.addSlime(slime);
		}
	}

	@Override
	public Collection<Slime> getSlimes(World world) {
		return world.getSlimes();
	}

	@Override
	public int[] getLocation(Slime slime) {
		return slime.getPosition().getPosition();
	}

	@Override
	public Sprite getCurrentSprite(Slime slime) {
		return slime.getCurrentSprite();
	}

	@Override
	public Plant createPlant(int x, int y, Sprite[] sprites) {
			return new Plant(x, y, sprites);
	}

	@Override
	public Shark createShark(int x, int y, Sprite[] sprites) {
		return new Shark(x, y, sprites);
	}

	@Override
	public Slime createSlime(int x, int y, Sprite[] sprites, School school) {
		return new Slime(x, y, sprites, school);
	}

	@Override
	public School createSchool() {
		return new School();
	}


	@Override
	public School getSchool(Slime slime) {
		return slime.getSchool();
	}

}
