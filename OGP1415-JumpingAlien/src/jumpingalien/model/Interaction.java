package jumpingalien.model;

import java.util.HashMap;
import java.util.Map;

public class Interaction{

	public Interaction(){
	}
	
	private final Map<String,Plant> plants = new HashMap<String,Plant>();
	
	private final Map<String,Slime> slimes = new HashMap<String,Slime>();

	private final Map<String,Shark> sharks = new HashMap<String,Shark>();


	
	public void interactWithOtherCreatures(Mazub mazub){
		for (String key : plants.keySet()) {
			if (plants.get(key).getPosition()== mazub.getPosition()){
				interactWithPlant(mazub,plants.get(key));
			}
			
		}
		for (String key : slimes.keySet()) {
			if (slimes.get(key).getPosition() == mazub.getPosition()){
				interactWithSlime(mazub, slimes.get(key));
			}
		}
		for (String key : sharks.keySet()) {
			if (sharks.get(key).getPosition() == mazub.getPosition()){
				interactWithShark(mazub, sharks.get(key));
			}
		}
	}
	
	//COMMENT Zowel bij slime als shark moet er nog een timer bij, dat er maar 1 keer per 0.6 seconden hp wordt afgetrokken;
	
	
	public void interactWithPlant(Mazub mazub, Plant plant){
		if (mazub.getHP() < 500){
			assert plant.isEatablePlant();
			mazub.addHP(mazub.getHealAmount());
			plant.terminate();
		}
	}
	
	public void interactWithSlime(Mazub mazub, Slime slime){
		mazub.addHP(-50);
		if (mazub.getHP() <= 0){
			//gameOver()
		}
		slime.addHP(-50);
		//terminate zit in setHP
	}
	
	public void interactWithShark(Mazub mazub, Shark shark){
		mazub.addHP(-50);
		if (mazub.getHP() <= 0){
			//gameOver()
		}
		shark.addHP(-50);
		//Terminate zit in setHp
	}



}
	
	
	
	

