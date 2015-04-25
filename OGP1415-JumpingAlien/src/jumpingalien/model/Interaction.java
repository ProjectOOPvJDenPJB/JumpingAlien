package jumpingalien.model;


import jumpingalien.util.Util;

public class Interaction{

	public Interaction(){
		
	}

	public static boolean collidesWithCreature(LivingCreatures creature1, LivingCreatures creature2) {
		if ((creature1.getXPosition() + (creature1.getSize()[0] - 1) < creature2.getXPosition()) || 
				(creature2.getXPosition() + (creature2.getSize()[0] - 1) < creature1.getXPosition()) ||
				(creature1.getYPosition() + (creature1.getSize()[1] - 1) <creature2.getYPosition()) ||
				(creature2.getYPosition() + (creature2.getSize()[1] - 1) < creature1.getYPosition())) {
			return false;
		}
		return true;
	}
	
//	public static int collidesWithTerrain(LivingCreatures creature){
//			for (int x = (int) creature.getXPosition(); x<= creature.getXPosition() + creature.getSize()[0] -2; x = x+1){
//				for (int y = (int) creature.getYPosition(); y<= creature.getYPosition() + creature.getSize()[1] -2; y = y+1 ){
//					World world = creature.getWorld();
//					if (Util.fuzzyGreaterThanOrEqualTo(x, world.getPixelWidth()))
//						x = world.getPixelWidth() -1;
//					if (Util.fuzzyGreaterThanOrEqualTo(y, world.getPixelHeight()))
//						y = world.getPixelHeight() -1;
////					System.out.println(x+".."+y);
//					int tileType = world.getTileType(x, y);
//				    if (tileType == 3){
//						return 3;
//					}
//				    else if ((tileType == 2) && (!(creature instanceof Shark))){
//						return 2;
//					}
//				    else if ((tileType == 0) && (creature instanceof Shark)){
//				    	return 0;
//				    }
//				}		
//			}
//			if (creature instanceof Shark){
//				return 2;
//			}else{
//			return 0;
//			}
	/**
	 * @return whether or not the living creature collides with the terrain
	 */
	public static int collidesWithTerrain(LivingCreatures creature) {
		World world = creature.getWorld();
		int[][] tiles;
		try{tiles =  world.getOccupiedTiles((int)creature.getXPosition(),(int)creature.getYPosition(),
				(int)creature.getXPosition()+creature.getCurrentSprite().getWidth(),(int)creature.getYPosition()+
				creature.getCurrentSprite().getHeight());
		}catch(NullPointerException exc){
			return 0;
		}
		
		for (int[] tile : tiles) {
			int tileType = world.getTileType(tile[0], tile[1]);
			    if (tileType == 3){
					return 3;
				}
			    else if ((tileType == 2) && (!(creature instanceof Shark))){
					return 2;
				}
			    else if ((tileType == 0) && (creature instanceof Shark)){
			    	return 0;
			    }
		}
		if (creature instanceof Shark){
			return 2;
		} else {
			return 0;
		}
	}
	
	
	public static void interactWithOtherCreatures(LivingCreatures creature){
		World world =creature.getWorld();
		for (Plant plant : world.getPlants()) {
			if (collidesWithCreature(creature,plant)){
				interactWithPlant(creature,plant);
			}
			
		}
		for (Slime slime : world.getSlimes()) {
			if (collidesWithCreature(creature,slime)){
				interactWithSlime(creature, slime);
			}
		}
		for (Shark shark : world.getSharks()) {
			if (collidesWithCreature(creature,shark)){
				interactWithShark(creature, shark);
			}
		}
	}	
	
	public static boolean interactWithMovementBlockingCreature (LivingCreatures creature, World world){
			for (Plant plant : world.getPlants()) {
				if (collidesWithCreature(creature,plant)){
					return true;
				}
				
			}
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
				assert plant.isEatablePlant();
				mazub.addHP(mazub.getHealAmount());
				plant.addHP(-1);
				plant.terminate();
			}
		}
	}
	
	public static void interactWithSlime(LivingCreatures creature, Slime slime){
		if (!(creature instanceof Plant)){
			creature.setMovementBlocked(true);
		}
		if (!(creature instanceof Slime)){
			if (Util.fuzzyGreaterThanOrEqualTo(creature.getHitTimer(),0.6)){
				creature.addHP(-50);
				creature.setHitTimer(0);
			}
			if (Util.fuzzyGreaterThanOrEqualTo(slime.getHitTimer(),0.6)){
				slime.addHP(-50);
				slime.setHitTimer(0);
				//terminate zit in setHP die in addHPzit
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
		if (!(creature instanceof Plant)){
			creature.setMovementBlocked(true);
		}		
		if (!(creature instanceof Shark)){
			if (Util.fuzzyLessThanOrEqualTo(creature.getHitTimer(),0.6)){
				creature.addHP(-50);
				creature.setHitTimer(0);
			}
			if (Util.fuzzyLessThanOrEqualTo(shark.getHitTimer(),0.6)){
				shark.addHP(-50);
				shark.setHitTimer(0);
			//Terminate zit in setHp
			}
		}
	}
}
	
	
	
	

