package jumpingalien.part2.facade;

import java.util.Collection;

import jumpingalien.model.IllegalSizeException;
import jumpingalien.model.IllegalTileException;
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

class Facade implements IFacadePart2  {

	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) throws jumpingalien.util.ModelException {
		try {
			return new Mazub(pixelLeftX, pixelBottomY, sprites);
		} catch (IllegalXPositionException | IllegalYPositionException exc) {
			throw new jumpingalien.util.ModelException("Illegal positions", exc);
		}
	}

	public int[] getLocation(Mazub alien) {
		return alien.getPosition();	
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
			return Mazub.getSize(alien.getCurrentSprite());
		} catch (IllegalSizeException exc) {
			throw new jumpingalien.util.ModelException("Illegal size", exc);
		}
	}

	public Sprite getCurrentSprite(Mazub alien) {
		return alien.getCurrentSprite();
	}

	public void startJump(Mazub alien) {
		alien.startJump();
	}

	public void endJump(Mazub alien) {
		alien.endJump();
	}

	public void startMoveLeft(Mazub alien) {
		alien.setDirection("left");
		alien.startMove(0.9);
	}

	public void endMoveLeft(Mazub alien) {
		if (alien.getDirection() == -1) {
			alien.endMove(0);
		}
	}

	public void startMoveRight(Mazub alien) {
		alien.setDirection("right");
		alien.startMove(0.9);
	}

	public void endMoveRight(Mazub alien) {
		if (alien.getDirection() == 1) {
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public World createWorld(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isGameOver(World world) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean didPlayerWin(World world) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void advanceTime(World world, double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getVisibleWindow(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getBottomLeftPixelOfTile(World world, int tileX, int tileY) {
		return world.getTile(tileX, tileY);
	}

	@Override
	public int[][] getTilePositionsIn(World world, int pixelLeft,
			int pixelBottom, int pixelRight, int pixelTop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGeologicalFeature(World world, int pixelX, int pixelY)
			throws ModelException {
		try {
			return world.getTileType(pixelX, pixelY);
		} catch (IllegalTileException e) {
			throw new ModelException("There exists no tile with these coördinates", e);
		}
	}

	@Override
	public void setGeologicalFeature(World world, int tileX, int tileY,
			int tileType) {
		Tile tile = new Tile(tileX,tileY,tileType);
		world.addTileType(tile);
	}

	@Override
	public void setMazub(World world, Mazub alien) {
		world.setMazub(alien);
	}

	@Override
	public boolean isImmune(Mazub alien) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPlant(World world, Plant plant) {
		world.addPlant(plant);
	}

	@Override
	public Collection<Plant> getPlants(World world) {
		return world.getPlants();
	}

	@Override
	public int[] getLocation(Plant plant) {
		return plant.getPosition();
	}

	@Override
	public Sprite getCurrentSprite(Plant plant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addShark(World world, Shark shark) {
		world.addShark(shark);
	}

	@Override
	public Collection<Shark> getSharks(World world) {
		return world.getSharks();
	}

	@Override
	public int[] getLocation(Shark shark) {
		return shark.getPosition();
	}

	@Override
	public Sprite getCurrentSprite(Shark shark) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSlime(World world, Slime slime) {
		world.addSlime(slime);
	}

	@Override
	public Collection<Slime> getSlimes(World world) {
		return world.getSlimes();
	}

	@Override
	public int[] getLocation(Slime slime) {
		return slime.getPosition();
	}

	@Override
	public Sprite getCurrentSprite(Slime slime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plant createPlant(int x, int y, Sprite[] sprites) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shark createShark(int x, int y, Sprite[] sprites) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Slime createSlime(int x, int y, Sprite[] sprites, School school) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public School createSchool() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public School getSchool(Slime slime) {
		// TODO Auto-generated method stub
		return null;
	}

}
