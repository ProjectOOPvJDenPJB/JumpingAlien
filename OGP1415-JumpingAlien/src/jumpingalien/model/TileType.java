package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Value;

/**
 * Enumeration of TileType
 * Link to repository: https://github.com/ProjectOOPvJDenPJB/JumpingAlien
 * 
 * @author Joren Dhont (Ingenieurswetenschappen: Computerwetenschappen - Elektrotechniek) 
 * 	& Pieterjan Beerden (Ingenieurswetenschappen: Elektrotechniek - Computerwetenschappen)
 * @version 0.1
 */
@Value
public enum TileType {
	
	AIR {
		/**
		 * Return whether air is passable or not.
		 * @return	TODO
		 * 			| result == true
		 */
		public final boolean getPassable() {
			return true;
		}
		
		/**
		 * Return the integer linked to the type.
		 */
		public final int getInt() {
			return 0;
		}
	},
	
	WATER {
		/**
		 * Return whether water is passable or not.
		 * @return	TODO
		 * 			| result == true
		 */
		public final boolean getPassable() {
			return true;
		}
		
		/**
		 * Return the integer linked to the type.
		 */
		public final int getInt() {
			return 2;
		}
	},
	
	MAGMA {
		/**
		 * Return whether magma is passable or not.
		 * @return	TODO
		 * 			| result == true
		 */
		public final boolean getPassable() {
			return true;
		}
		
		/**
		 * Return the integer linked to the type.
		 */
		public final int getInt() {
			return 3;
		}
	},
	
	GROUND {
		/**
		 * Return whether ground is passable or not.
		 * @return	TODO
		 * 			| result == false
		 */
		public final boolean getPassable() {
			return false;
		}
		
		/**
		 * Return the integer linked to the type.
		 */
		public final int getInt() {
			return 1;
		}
	};
	
	/**
	 * Return whether this tileType is passable.
	 */
	public abstract boolean getPassable();
	
	/**
	 * Return the integer related to this tileType.
	 */
	public abstract int getInt();

}
