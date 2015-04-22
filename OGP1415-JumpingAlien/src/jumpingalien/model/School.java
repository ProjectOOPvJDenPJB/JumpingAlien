package jumpingalien.model;

import java.util.HashMap;
import java.util.Map;
/**
 * A class of School.
 * Link to repository: https://github.com/ProjectOOPvJDenPJB/JumpingAlien
 * 
 * @author Joren Dhont (Ingenieurswetenschappen: Computerwetenschappen - Elektrotechniek) 
 * 	& Pieterjan Beerden (Ingenieurswetenschappen: Elektrotechniek - Computerwetenschappen)
 * @version 0.1
 */
public class School {
	/**
	 * Initializes a new school of slimes.
	 */
	public School(){
		
	}
	
	private final static Map<String,Slime> slimes = new HashMap<String,Slime>();

	/**
	 * Sets the amount of Slimes for this school to the given amount.
	 * 
	 * @param	amount
	 * 			The new amount of slimes for this school
	 * @post	The new amount of slimes for this school is equal to
	 * 			the given amount.	
	 * 			| new.getAmountSlimes() == amount
	 */
	public void setAmountSlimes(int amount){
		this.AmountOfSlimes = amount;
	}
	
	private int AmountOfSlimes = 0;
	
	/*
	 * return the amount of slimes in this school
	 */
	public int getAmountSlimes(){
		return this.AmountOfSlimes;
	}
	
	/**
	 * Adds the slime to this school if this school contains 
	 * less then 10 slimes
	 * @param slime
	 * 		  The slime that will be add to this school
	 * @post  if this school contains less then 10 other slimes
	 * 		  add the given slime to this school, with all the HP 
	 * 		  conversions taken into account.
	 * 		  | if (this.getAmountSlimes() < 10)
	 * 		  | new.getSchool() == this
	 * 		  |  new.slimesFromOldSchool.getHP() ==  old.slimesFromOldSchool.getHP() - 1 
	 * 		  |  new.slimesFromNewSchool.getHP() ==  old.slimesFromNewSchool.getHP() + 1 
	 */
	public void addSlime(Slime slime){
		if (this.getAmountSlimes() < 10){
			School oldSchool = slime.getSchool();
			slime.setSchool(this);
			this.setAmountSlimes(this.getAmountSlimes() + 1);
			for (String key : slimes.keySet()) {
				if (slimes.get(key).getSchool() == oldSchool){
					slimes.get(key).addHP(1);
					slime.addHP(-1);
				}
				if (slimes.get(key).getSchool() == slime.getSchool()){
					slimes.get(key).addHP(-1);
					slime.addHP(1);
				}
			}
		}
	}
	
	/**
	 * removes the given dying slime from this school
	 * 
	 * @param slime
	 * 		  the given slime to be removed from this school 
	 * 		 | if slime.isDying
	 * 		 | 		new.slime.getSchool() == null
	 */
	public void removeSlime(Slime slime){
		if (slime.isDying()){
			slime.setSchool(null);
			this.setAmountSlimes(this.getAmountSlimes() - 1);

		}
	}
	
}

