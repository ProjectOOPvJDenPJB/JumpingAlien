package jumpingalien.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
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
	
	/**
	 * Sets the amount of Slimes for this school to the given amount.
	 * 
	 * @param	amount
	 * 			The new amount of slimes for this school
	 * @post	The new amount of slimes for this school is equal to
	 * 			the given amount.	
	 * 			| new.getAmountSlimes() == amount
	 */
	private void setAmountSlimes(int amount){
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
	
	private final Set<Slime> slimes = new HashSet<Slime>();
	
	/**
	 * Return the slimes currently in this school in the form of a collection.
	 */
	public Collection<Slime> getSlimes(){
		Collection<Slime> elements = slimes;
		return new ArrayList<Slime>(elements);
	}
	
	/**
	 * Adds the given slime to this school.
	 * @param 	slime
	 * 			The slime to be added to this school.
	 * @post	If this school can accept new slimes and this slime is effective, 
	 * 			then this slime is added to this school.
	 * 			| if (this.getAmountSlimes() < 10 && slime.isAlive())
	 * 			|	then newSlime.getSchool == this
	 * 			|		&& slime is in new.getSlimes()
	 * 			|		&& new.getAmountOfSlimes() == old.getAmountOfSlimes() + 1
	 * 			If this slime can be added to this school and
	 * 			if the old school the given slime belonged to is effective then
	 * 			the given slime is removed from the old school and the given slime
	 * 			transfers 1 hitpoint from himself to each of the slimes in his old school.
	 * 			The slimes in the given slime's new school each transfer one hitpoint to
	 * 			the given slime.
	 * 			| if (oldSlime.getSchool != null)
	 * 			|	then oldSlime.getSchool().removeSlime(slime)
	 * 			|	&& (
	 * 			|		for each slimeOS in oldSlime.getSchool().getSlimes()
	 * 			|			slimeOS.addHP(1) && slime.addHP(-1)
	 * 			|		for each slimeNS in getSlimes()
	 * 			|			slimeNS.addHP(-1) && slime.addHP(1)
	 * 			|	)
	 */
	public void addSlime(Slime slime){
		if (this.getAmountSlimes() < 10 && slime.isAlive()){
			School oldSchool = slime.getSchool();
			if (oldSchool != null){
				oldSchool.removeSlime(slime);

				for (Slime oldSlimeMates : oldSchool.getSlimes()) {	
					oldSlimeMates.addHP(1);
					slime.addHP(-1);
				}
				for (Slime newSlimeMates: this.getSlimes()){
					newSlimeMates.addHP(-1);
					slime.addHP(1);
				}
			}
			slime.setSchool(this);
			slimes.add(slime);
			this.setAmountSlimes(this.getAmountSlimes() + 1);

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
			this.setAmountSlimes(this.getAmountSlimes() - 1);
			slimes.remove(slime);
			slime.setSchool(null);
	}
	
}

