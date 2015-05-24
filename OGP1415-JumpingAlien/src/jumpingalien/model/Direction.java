package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Value;

/**
 * An enumeration of Directions.
 * 		In its current implementation, the class only distinguishes
 * 		between left and right.
 * Link to repository: https://github.com/ProjectOOPvJDenPJB/JumpingAlien
 * 
 * @author Joren Dhont (Ingenieurswetenschappen: Computerwetenschappen - Elektrotechniek) 
 * 	& Pieterjan Beerden (Ingenieurswetenschappen: Elektrotechniek - Computerwetenschappen)
 * @version 0.1
 */
@Value
public enum Direction {
	
	LEFT {
		
		/**
		 * Return the integer associated with the left direction.
		 * 	This integer is used to compute changes in positions.
		 * 
		 * @return	The negative integer 1
		 * 			| result == -1
		 */
		public final int getInt() {
			return -1;
		}
	},
	
	RIGHT {
		
		/**
		 * Return the integer associated with the right direction.
		 * 	This integer is used to compute changes in positions.
		 * 
		 * @return	The positive integer 1
		 * 			| result == 1
		 */
		public final int getInt() {
			return 1;
		}
	}, 
	
	UP {
		
		/**
		 * Return the integer associated with the UP direction.
		 * 
		 * @return	The positive integer 2
		 * 			| result == 2
		 */
		@Override
		public int getInt() {
			return 2;
		}
	}, 
	
	DOWN {
		
		/**
		 * Return the integer associated with the UP direction.
		 * 
		 * @return	The positive integer -2
		 * 			| result == -2
		 */
		@Override
		public int getInt() {
			// TODO Auto-generated method stub
			return -2;
		}
	};
	
	/**
	 * Return the integer associated with the direction.
	 */
	public abstract int getInt();
	
	/**
	 * Return the opposite direction of this direction.
	 * 
	 * @pre		This direction must be a valid direction.
	 * 			| (this != null)
	 * @return	RIGHT if this direction is LEFT
	 * 			| if (this == LEFT)
	 * 			|	then result == RIGHT
	 * 			Otherwise LEFT is returned. Since this direction has to be
	 * 			a valid direction, this means this direction is automatically
	 * 			RIGHT since Direction only consists of LEFT and RIGHT.
	 * 			| else
	 * 			|	result == RIGHT
	 */
	public Direction oppositeDirection() {
		assert (this != null);
		if (this == Direction.LEFT)
			return Direction.RIGHT;
		else
			return Direction.LEFT;
	}

}
