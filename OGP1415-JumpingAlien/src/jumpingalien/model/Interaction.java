package jumpingalien.model;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.util.Util;

public class Interaction{

	public Interaction(){
	}
	
	private final static Map<String,Plant> plants = new HashMap<String,Plant>();
	
	private final static Map<String,Slime> slimes = new HashMap<String,Slime>();

	private final static Map<String,Shark> sharks = new HashMap<String,Shark>();


	public static boolean collidesWithCreature(LivingCreatures creature1, LivingCreatures creature2) {
		if ((creature1.getXPosition() + (creature1.getSize()[0] - 1) < creature2.getXPosition()) || 
				(creature2.getXPosition() + (creature2.getSize()[0] - 1) < creature1.getXPosition()) ||
				(creature1.getYPosition() + (creature1.getSize()[1] - 1) <creature2.getYPosition()) ||
				(creature2.getYPosition() + (creature2.getSize()[1] - 1) < creature1.getYPosition())) {
			return false;
		}
		return true;
	}
	
	public static void interactWithOtherCreatures(LivingCreatures creature){
		for (String key : plants.keySet()) {
			if (collidesWithCreature(creature,plants.get(key))){
				interactWithPlant(creature,plants.get(key));
			}
			
		}
		for (String key : slimes.keySet()) {
			if (collidesWithCreature(creature,slimes.get(key))){
				interactWithSlime(creature, slimes.get(key));
			}
		}
		for (String key : sharks.keySet()) {
			if (collidesWithCreature(creature,sharks.get(key))){
				interactWithShark(creature, sharks.get(key));
			}
		}
	}
	
	//COMMENT Zowel bij slime als shark moet er nog een timer bij, dat er maar 1 keer per 0.6 seconden hp wordt afgetrokken;
	
	
	public static void interactWithPlant(LivingCreatures creature, Plant plant){
		if (creature instanceof Mazub){
			Mazub mazub = (Mazub) creature;
			if (mazub.getHP() < 500){
				assert plant.isEatablePlant();
				mazub.addHP(mazub.getHealAmount());
				plant.terminate();
			}
		}
	}
	
	public static void interactWithSlime(LivingCreatures creature, Slime slime){
		if (!(creature instanceof Plant)){
			creature.setMovementBlocked(true);
		}
		if (!(creature instanceof Slime)){
			if (Util.fuzzyLessThanOrEqualTo(creature.getHitTimer(),0.6)){
				creature.addHP(-50);
				creature.setHitTimer(0);
			}
			if (Util.fuzzyLessThanOrEqualTo(slime.getHitTimer(),0.6)){
				slime.addHP(-50);
				slime.setHitTimer(0);
				//terminate zit in setHP die in addHPzit
			}
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
	
	
	
	

