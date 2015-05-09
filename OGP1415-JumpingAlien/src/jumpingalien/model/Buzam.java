package jumpingalien.model;

import jumpingalien.util.Sprite;

public class Buzam extends Mazub {

	public Buzam(int positionLeftX, int positionBottomY, Sprite[] sprites,
			double maximumHorizontalVelocity, double initialHorizontalVelocity,
			World world, int hitpoints) {
		super(positionLeftX, positionBottomY, sprites, maximumHorizontalVelocity,
				initialHorizontalVelocity, world, hitpoints);
	}
	
	public Buzam(int positionLeftX, int positionBottomY, Sprite[] sprites) {
		this(positionLeftX,positionBottomY,sprites,3,1,null,500);
	}
	
}
