package jumpingalien.model;

import java.util.HashMap;
import java.util.Map;

public class Interaction{

	public Interaction(){
	}
	
	private final static Map<String,Plant> plants = new HashMap<String,Plant>();
	
	private final static Map<String,Slime> slimes = new HashMap<String,Slime>();

	private final static Map<String,Shark> sharks = new HashMap<String,Shark>();


	
	public static void interactWithOtherCreatures(LivingCreatures creature){
		for (String key : plants.keySet()) {
			if (plants.get(key).getPosition()== creature.getPosition()){
				interactWithPlant(creature,plants.get(key));
			}
			
		}
		for (String key : slimes.keySet()) {
			if (slimes.get(key).getPosition() == creature.getPosition()){
				interactWithSlime(creature, slimes.get(key));
			}
		}
		for (String key : sharks.keySet()) {
			if (sharks.get(key).getPosition() == creature.getPosition()){
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
		if (!(creature instanceof Slime)){
			creature.addHP(-50);
			creature.setHitTimer(0);
			if (creature.getHP() <= 0){
				//gameOver()
			}
			slime.addHP(-50);
			slime.setHitTimer(0);
			//terminate zit in setHP
		}
	}
	
	public static void interactWithShark(LivingCreatures creature, Shark shark){
		if (!(creature instanceof Shark)){
			creature.addHP(-50);
			creature.setHitTimer(0);
			if (creature.getHP() <= 0){
				//gameOver()
			}
			shark.addHP(-50);
			shark.setHitTimer(0);
			//Terminate zit in setHp
		}
	}
}
	
	
	
	

