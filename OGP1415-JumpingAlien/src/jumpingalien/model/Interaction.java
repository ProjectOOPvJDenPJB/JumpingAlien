package jumpingalien.model;

import jumpingalien.util.Util;

public class Interaction{

	public Interaction(){	
	}
	
	/**
	 * Checks whether the given creatures collide.
	 * @param 	creature1
	 * 			The first creature to check.
	 * @param 	creature2
	 * 			The second creature to check.
	 * @return	True if the two creatures collide, false otherwise.
	 */
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
	 * Checks whether the given creature collides with a given tileType.
	 * @param	creature
	 * 			The creature to check.
	 * @param	type
	 * 			The tileType to check.
	 * @effect	The creature collides with the terrain if it collides with the terrain
	 * 			horizontally or vertically.
	 * 			| collidesWithTerrainHorizontal(creature,type)
	 * 			|	|| collidesWithTerrainVertical(creature,type)
	 */
	public static boolean collidesWithTerrain(LivingCreatures creature, int type) {
		return collidesWithTerrainHorizontal(creature, type)
				|| collidesWithTerrainVertical(creature, type);
	}
	
	/**
	 * Checks whether the given creature collides vertically with a given tileType.
	 * @param	creature
	 * 			The creature to check.
	 * @param	type
	 * 			The tileType to check.
	 * @effect	The creature collides with the terrain vertically if it collides with the terrain
	 * 			with its bottom border or top border.
	 * 			| collidesWithTerrainBottomSide(creature,type)
	 * 			|	|| collidesWithTerrainTopSide(creature,type)
	 */
	public static boolean collidesWithTerrainVertical(LivingCreatures creature, int type) {
		return collidesWithTerrainBottomSide(creature, type)
				|| collidesWithTerrainTopSide(creature, type);
	}
	
	/**
	 * Checks whether the given creature collides horizontally with a given tileType.
	 * @param	creature
	 * 			The creature to check.
	 * @param	type
	 * 			The tileType to check.
	 * @effect	The creature collides with the terrain horizontally if it collides with the terrain
	 * 			with its left border or right border.
	 * 			| collidesWithTerrainLeftSide(creature,type)
	 * 			|	|| collidesWithTerrainRightSide(creature,type)
	 */
	public static boolean collidesWithTerrainHorizontal(LivingCreatures creature, int type) {
		return collidesWithTerrainLeftSide(creature, type)
				|| collidesWithTerrainRightSide(creature, type);
	}
	
	/**
	 * Checks whether the given creature collides with a given tileType on its left border.
	 * @param	creature
	 * 			The creature to check.
	 * @param	type
	 * 			The tileType to check.
	 * @return	True if the left border of this creature collides with a tile of the given type,
	 * 			false otherwise.
	 */
	public static boolean collidesWithTerrainLeftSide(LivingCreatures creature, int type) {
		World world = creature.getWorld();
		int[][] tiles =  world.getOccupiedTiles(
				(int)creature.getXPosition() + 1,
				(int)creature.getYPosition() + 1, 
				(int)creature.getXPosition(),
				(int)creature.getYPosition() + creature.getSize()[1]);
		return getCollidingTiles(tiles, world, type);
	}
	
	/**
	 * Checks whether the given creature collides with a given tileType on its right border.
	 * @param	creature
	 * 			The creature to check.
	 * @param	type
	 * 			The tileType to check.
	 * @return	True if the right border of this creature collides with a tile of the given type,
	 * 			false otherwise.
	 */
	public static boolean collidesWithTerrainRightSide(LivingCreatures creature, int type) {
		World world = creature.getWorld();
		int[][] tiles =  world.getOccupiedTiles(
				(int)creature.getXPosition() + creature.getSize()[0],
				(int)creature.getYPosition() + 1,
				(int)creature.getXPosition() + creature.getSize()[0],
				(int)creature.getYPosition() + creature.getSize()[1]);
		return getCollidingTiles(tiles, world, type);
	}
	
	/**
	 * Checks whether the given creature collides with a given tileType on its bottom border.
	 * @param	creature
	 * 			The creature to check.
	 * @param	type
	 * 			The tileType to check.
	 * @return	True if the bottom border of this creature collides with a tile of the given type,
	 * 			false otherwise.
	 */
	public static boolean collidesWithTerrainBottomSide(LivingCreatures creature, int type) {
		World world = creature.getWorld();
		int[][] tiles =  world.getOccupiedTiles(
				(int)creature.getXPosition() + 1,
				(int)creature.getYPosition() + 1,
				(int)creature.getXPosition() + creature.getSize()[0],
				(int)creature.getYPosition() + 1);
		return getCollidingTiles(tiles, world, type);
	}
	
	/**
	 * Checks whether the given creature collides with a given tileType on its top border.
	 * @param	creature
	 * 			The creature to check.
	 * @param	type
	 * 			The tileType to check.
	 * @return	True if the top border of this creature collides with a tile of the given type,
	 * 			false otherwise.
	 */
	public static boolean collidesWithTerrainTopSide(LivingCreatures creature, int type) {
		World world = creature.getWorld();
		int[][] tiles =  world.getOccupiedTiles(
				(int)creature.getXPosition() + 1,
				(int)creature.getYPosition() + creature.getSize()[1],
				(int)creature.getXPosition() + creature.getSize()[0],
				(int)creature.getYPosition() + creature.getSize()[1]);
		return getCollidingTiles(tiles, world, type);
	}
	
	/**
	 * Checks whether there is a tile of given type in the collection of given tiles in the given world.
	 * @param 	tiles
	 * 			The collection of tiles to check in the form of a nested integer array
	 * 			where tiles[i] is a counter and tiles[][0] is the t_X position of a tile
	 * 			and tiles[][1] is the t_Y position of a tile.
	 * @param 	world
	 * 			The world the given tiles are located in.
	 * @param 	type
	 * 			The tileType to check.
	 * @return	True if the given tileType is the tileType of one of the given tiles, false otherwise.
	 * 			| result ==
	 * 			| 	!(for each I in 1..tiles.length
	 * 			|		(world.getTileType(tiles[I][0] * world.getTileSize(), tiles[I][1] * world.getTileSize())
	 * 			|			!= type))
	 */
	private static boolean getCollidingTiles(int[][] tiles, World world,int type) {
		for(int i = 0; i < tiles.length; i++) {
			int tileType = world.getTileType(tiles[i][0] * world.getTileSize(), tiles[i][1] * world.getTileSize());
			    if (tileType == type)
			    	return true;
		}
		return false;
	}
	
	/**
	 * Checks whether the given creature interacts with any other creature in the given creature's world.
	 * @param 	creature
	 * 			The creature to check interactions of.
	 * @effect	If any of the creatures in the given creature's world collide
	 * 			with the given creature, they interact.
	 * 			| for each plant in creature.getWorld().getPlants()
	 * 			|	if (collidesWithCreature(creature,plant)
	 * 			|		interactWithPlant(creature,plant)
	 * 			| for each slime in creature.getWorld().getSlimes()
	 * 			|	if (collidesWithCreature(creature,slime)
	 * 			|		interactWithPlant(creature,slime)
	 * 			| for each shark in creature.getWorld().getSharks()
	 * 			|	if (collidesWithCreature(creature,shark)
	 * 			|		interactWithPlant(creature,shark)
	 * @note	Note that the Mazub type is not checked in this function because
	 * 			Mazub is the dominant class in the interactions.
	 */
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
	
	/**
	 * Checks whether or not the given creature has its movement blocked through collision 
	 * 	with any other creature in the given world.
	 * @param	creature
	 * 			The creature to check the blocked state of.
	 * @param	world
	 * 			The world to check collisions in.
	 * @return	If any of the creatures in the given creature's world has its movement blocked
	 * 			through collision with the given creature, true is returned.
	 * 			| result != 
	 * 			| 	for each slime in world.getSlimes()
	 * 			|		!(collidesWithCreature(creature,slime)
	 * 			| 	for each shark in world.getSharks()
	 * 			|		!(collidesWithCreature(creature,shark)
	 * 			|	!(collidesWithCreature(creature,world.getMazub())
	 * @note	Note that the Plant type is not checked because plants do not
	 * 			block movement.
	 */
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

	
	/**
	 * Makes the given creature interact with the given plant.
	 * @param 	creature
	 * 			The creature to interact with the given plant.
	 * @param 	plant
	 * 			The plant to to interact with the given creature.
	 * @effect	If the creature is of type Mazub and the Mazub has not reached
	 * 			his maximum hitpoints yet, the plant will be eaten if it is eatable and the
	 * 			plants hitpoints will be set to zero..
	 * 			This results in healing Mazub for 50 hitpoints.
	 * 			| if (this instanceof Mazub) && (this.getHP() < this.getMaxHP()
	 * 			|	&& (plant.isEatable)
	 * 			|		then mazub.addHP(50) && plant.setHP(0)
	 */
	public static void interactWithPlant(LivingCreatures creature, Plant plant){
		if (creature instanceof Mazub){
			Mazub mazub = (Mazub) creature;
			if (mazub.getHP() < mazub.getMaxHP()){
				if (plant.isEatablePlant()) {
				mazub.addHP(mazub.getHealAmount());
				plant.setHP(0);
				}
			}
		}
	}
	
	/**
	 * Makes the given creature interact with the given slime.
	 * @param 	creature
	 * 			The creature to interact with the given slime.
	 * @param 	slime
	 * 			The slime to interact with the given creature.
	 * @post	If the creature has a valid hitTimer and is not of type Slime,
	 * 				then the creature's hitTimer is reset to zero.
	 * 			| if (creature.getHitTimer >= 0.6) && !(creature instanceof Slime)
	 * 			|	then newCreature.getHitTimer() == 0
	 * @post	If the slime has a valid hitTimer and the creature is not of type Slime,
	 * 				then the slime's hitTimer is reset to zero.
	 * 			| if (slime.getHitTimer >= 0.6) && !(creature instanceof Slime)
	 * 			|	then newSlime.getHitTimer() == 0
	 * @effect	If the creature has a valid hitTimer and is not of type Slime,
	 * 				then the creature's hitpoints will be substracted by 50.
	 * 			| if (creature.getHitTimer >= 0.6) && !(creature instanceof Slime)
	 * 			|	then creature.addHP(-50)
	 * @effect	If the slime has a valid hitTimer and the creature is not of type Slime,
	 * 				then the slime's hitpoints will be substracted by 50.
	 * 			| if (slime.getHitTimer >= 0.6) && !(creature instanceof Slime)
	 * 			|	then slime.addHP(-50)
	 * @effect	If the creature is of type Slime, then interaction will
	 * 			invoke changeSchool
	 * 			| if (creature instanceof Slime)
	 * 			|	then changeSchool(creature,slime)
	 */
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
	 * Makes the given creature interact with the given shark.
	 * @param 	creature
	 * 			The creature to interact with the given shark.
	 * @param 	shark
	 * 			The shark to interact with the given creature.
	 * @post	If the creature has a valid hitTimer and is not of type Shark,
	 * 				then the creature's hitTimer is reset to zero.
	 * 			| if (creature.getHitTimer >= 0.6) && !(creature isinstanceof Shark)
	 * 			|	then newCreature.getHitTimer() == 0
	 * @post	If the shark has a valid hitTimer and the creature is not of type Shark,
	 * 				then the shark's hitTimer is reset to zero.
	 * 			| if (shark.getHitTimer >= 0.6) && !(creature isinstanceof Shark)
	 * 			|	then newShark.getHitTimer() == 0
	 * @effect	If the creature has a valid hitTimer and is not of type Shark,
	 * 				then the creature's hitpoints will be substracted by 50.
	 * 			| if (creature.getHitTimer >= 0.6) && !(creature instanceof Shark)
	 * 			|	then creature.addHP(-50)
	 * @effect	If the shark has a valid hitTimer and the creature is not of type Shark,
	 * 				then the shark's hitpoints will be substracted by 50.
	 * 			| if (shark.getHitTimer >= 0.6) && !(creature instanceof Shark)
	 * 			|	then shark.addHP(-50)
	 */
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
}
	
	
	
	

