package jumpingalien.model;

import jumpingalien.util.Util;

public class Interaction{

	public Interaction(){	
	}

	public static boolean collidesWithCreature(LivingCreatures creature1, LivingCreatures creature2) {
		if (creature1 == creature2)
			return false;
		if ((creature1.getXPosition() + (creature1.getSize()[0] - 1) < creature2.getXPosition()) || 
				(creature2.getXPosition() + (creature2.getSize()[0] - 1) < creature1.getXPosition()) ||
				(creature1.getYPosition() + (creature1.getSize()[1] - 1) < creature2.getYPosition()) ||
				(creature2.getYPosition() + (creature2.getSize()[1] - 1) < creature1.getYPosition())) {
			return false;
		}
		else
			return true;
	}
	
	/**
	 * @return whether or not the living creature collides with the terrain
	 */
	public static boolean collidesWithTerrain(LivingCreatures creature, int type) {
		return collidesWithTerrainHorizontal(creature, type)
				|| collidesWithTerrainVertical(creature, type);
	}
	
	public static boolean collidesWithTerrainVertical(LivingCreatures creature, int type) {
		return collidesWithTerrainBottomSide(creature, type)
				|| collidesWithTerrainTopSide(creature, type);
	}
	
	public static boolean collidesWithTerrainHorizontal(LivingCreatures creature, int type) {
		return collidesWithTerrainLeftSide(creature, type)
				|| collidesWithTerrainRightSide(creature, type);
	}
	
	public static boolean collidesWithTerrainLeftSide(LivingCreatures creature, int type) {
		World world = creature.getWorld();
		int[][] tiles =  world.getOccupiedTiles(
				(int)creature.getXPosition() + 1,
				(int)creature.getYPosition() + 1, 
				(int)creature.getXPosition(),
				(int)creature.getYPosition() + creature.getSize()[1]);
		return getCollidingTiles(tiles, world, type);
	}
	
	public static boolean collidesWithTerrainRightSide(LivingCreatures creature, int type) {
		World world = creature.getWorld();
		int[][] tiles =  world.getOccupiedTiles(
				(int)creature.getXPosition() + creature.getSize()[0],
				(int)creature.getYPosition() + 1,
				(int)creature.getXPosition() + creature.getSize()[0],
				(int)creature.getYPosition() + creature.getSize()[1]);
		return getCollidingTiles(tiles, world, type);
	}
	
	public static boolean collidesWithTerrainBottomSide(LivingCreatures creature, int type) {
		World world = creature.getWorld();
		int[][] tiles =  world.getOccupiedTiles(
				(int)creature.getXPosition() + 1,
				(int)creature.getYPosition() + 1,
				(int)creature.getXPosition() + creature.getSize()[0],
				(int)creature.getYPosition() + 1);
		return getCollidingTiles(tiles, world, type);
	}
	
	public static boolean collidesWithTerrainTopSide(LivingCreatures creature, int type) {
		World world = creature.getWorld();
		int[][] tiles =  world.getOccupiedTiles(
				(int)creature.getXPosition() + 1,
				(int)creature.getYPosition() + creature.getSize()[1],
				(int)creature.getXPosition() + creature.getSize()[0],
				(int)creature.getYPosition() + creature.getSize()[1]);
		return getCollidingTiles(tiles, world, type);
	}
	
	private static boolean getCollidingTiles(int[][] tiles, World world,int type) {
		for(int i = 0; i < tiles.length; i++) {
			int tileType = world.getTileType(tiles[i][0] * world.getTileSize(), tiles[i][1] * world.getTileSize());
			    if (tileType == type)
			    	return true;
		}
		return false;
	}
		
	public static void interactWithOtherCreatures(LivingCreatures creature){
		World world =creature.getWorld();
		try{
			for (Plant plant : world.getPlants()) {
				if (collidesWithCreature(creature,plant)){
					interactWithPlant(creature,plant);
				}	
			}
		}catch(NullPointerException exc){
		}
		try{
			for (Slime slime : world.getSlimes()) {
				if (collidesWithCreature(creature,slime)){
					interactWithSlime(creature, slime);
				}
			}
		}catch(NullPointerException exc){
		}
		try{
			for (Shark shark : world.getSharks()) {
				if (collidesWithCreature(creature,shark)){
					interactWithShark(creature, shark);
				}
			}
		}catch(NullPointerException exc){
		}
	}	
	
	public static boolean interactWithMovementBlockingCreature(LivingCreatures creature, World world){
			for (Slime slime : world.getSlimes()) {
				if (collidesWithCreature(creature,slime)){
					return true;
				}
			}
			for (Shark shark : world.getSharks()) {
				if (collidesWithCreature(creature,shark)){
					return true;
				}
			}
			Mazub mazub = world.getMazub();
			if (mazub != null){
				if (collidesWithCreature(creature,world.getMazub())){
					return true;
				}
			}
			return false;
		}

	
	public static void interactWithPlant(LivingCreatures creature, Plant plant){
		if (creature instanceof Mazub){
			Mazub mazub = (Mazub) creature;
			if (mazub.getHP() < 500){
				if (plant.isEatablePlant()) {
				mazub.addHP(mazub.getHealAmount());
				plant.setHP(0);
				}
			}
		}
	}
	
	public static void interactWithSlime(LivingCreatures creature, Slime slime){
		if (!(creature instanceof Slime)){
			if (Util.fuzzyGreaterThanOrEqualTo(creature.getHitTimer(),0.6)){
				creature.addHP(-50);
				creature.setHitTimer(0);
			}
			if (Util.fuzzyGreaterThanOrEqualTo(slime.getHitTimer(),0.6)){
				slime.addHP(-50);
				slime.setHitTimer(0);
			}
		}
		if(creature instanceof Slime){
			changeSchool((Slime)creature,slime);
		}
	}

	/**
	 * Changes the school of the slime with the lowest amount 
	 * of slime to the school of the other slime.
	 * @param slime1
	 * 		  the first slime to change school or not.
	 * @param slime2
	 * 		  the second slime to change school or not
	 * @post  The current school of slime1 contains less slimes
	 * 		  then the school of slime2 so slime 1 changes to the
	 * 		  school of slime2
	 * 		  | if (school1.getAmountSlimes() < school2.getAmountSlimes())
			  | new.slime1.getSchool() == slime2.getSchool()
	 * @post  The current school of slime2 contains less slimes
	 * 		  then the school of slime1 so slime 2 changes to the
	 * 		  school of slime1
	 * 		  | if (school1.getAmountSlimes() > school2.getAmountSlimes())
			  | new.slime2.getSchool() == slime1.getSchool()	
	 */
	public static void changeSchool(Slime slime1,Slime slime2){
		School school1 = slime1.getSchool();
		School school2 = slime2.getSchool();
		if (school1.getAmountSlimes() < school2.getAmountSlimes()){
			school2.addSlime(slime1);
		}
		
		else if (slime1.getSchool().getAmountSlimes() > slime2.getSchool().getAmountSlimes()){
			school1.addSlime(slime2);
		}
	}
	
	public static void interactWithShark(LivingCreatures creature, Shark shark){	
		if (!(creature instanceof Shark)){
			if (Util.fuzzyGreaterThanOrEqualTo(creature.getHitTimer(),0.6)){
				creature.addHP(-50);
				creature.setHitTimer(0);
			}
			if (Util.fuzzyGreaterThanOrEqualTo(shark.getHitTimer(),0.6)){
				shark.addHP(-50);
				shark.setHitTimer(0);
			}
		}
	}
}
	
	
	
	

