/**
 * 
 */
package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Value;

/**
 * @author Joren
 *
 */
@Value
public enum Direction {
	
	LEFT {
		public final int getInt() {
			return -1;
		}
	},
	
	RIGHT {
		public final int getInt() {
			return 1;
		}
	};
	
	public abstract int getInt();
	
	public Direction oppositeDirection() {
		assert (this != null);
		if (this == Direction.LEFT)
			return Direction.RIGHT;
		else
			return Direction.LEFT;
	}

}
