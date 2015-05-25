package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.IProgramFactory.Direction;

public class DirectionExpression extends BasicExpression<Direction> {
	
	/**
	 * Initializes this direction expression.
	 * 
	 * @param 	direction
	 * 			The direction to set this direction expression to.
	 */
	public DirectionExpression(Direction direction){
		this.setDirection(direction);
	}
	
	/**
	 * Sets the direction of this direction expression.
	 * 
	 * @param	direction
	 * 			The direction to be set.
	 */
	public void setDirection(Direction direction){
		this.direction = direction;
	}
	
	/**
	 * Variable registering the direction of this direction expression.
	 */
	private Direction direction;
	
	/**
	 * Returns the direction of this direction expression.
	 */
	public Direction getDirection(){
		return this.direction;
	}
	
	/**
	 * Evaluates this direction expression
	 * @return	The direction of this direction expression.
	 * 			| result ==
	 * 			|	this.getDirection()
	 */
	@Override
	public Direction evaluate(Program program) {
		return this.direction;
	}
	
	/**
	 * Returns the opposite direction of this direction expression.
	 * @return	If the direction is UP, DOWN is returned
	 * 			| if (this.getDirection() == UP)
	 * 			|	then result == DOWN
	 * 			If the direction is DOWN, UP is returned
	 * 			| if (this.getDirection() == DOWN)
	 * 			|	then result == UP
	 * 			If the direction is RIGHT, LEFT is returned
	 * 			| if (this.getDirection() == RIGHT)
	 * 			|	then result == LEFT
	 * 			If the direction is LEFT, RIGHT is returned
	 * 			| if (this.getDirection() == LEFT)
	 * 			|	then result == RIGHT
	 */
	public Direction getOpposite() {
		Direction direction = this.getDirection();
		if (direction == Direction.UP){
			return Direction.DOWN;
		}else if (direction == Direction.DOWN){
			return Direction.UP;
		}else if (direction == Direction.RIGHT){
			return Direction.LEFT;
		}else{
			return Direction.RIGHT;
		}
	}
	
	public jumpingalien.model.Direction evaluateForCreature() {
		Direction direction = this.getDirection();
		if (direction == Direction.UP){
			return jumpingalien.model.Direction.UP;
		}else if (direction == Direction.DOWN){
			return jumpingalien.model.Direction.DOWN;
		}else if (direction == Direction.RIGHT){
			return jumpingalien.model.Direction.RIGHT;
		}else{
			return jumpingalien.model.Direction.LEFT;
		}
	}
}
