package jumpingalien.model;

import jumpingalien.part3.programs.Program;

public abstract class GameObject {
	
	public abstract double getXPosition();
	public abstract double getYPosition();
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract World getWorld();
	public abstract Program getProgram();

}
