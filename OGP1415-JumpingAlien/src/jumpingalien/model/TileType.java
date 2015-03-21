package jumpingalien.model;

public enum TileType {
	
	AIR {
		/**
		 * Return whether air is passable or not.
		 * @return	TODO
		 * 			| result == true
		 */
		public boolean getPassable() {
			return true;
		}
		
		/**
		 * Return the integer linked to the type.
		 */
		public int getInt() {
			return 0;
		}
	},
	
	WATER {
		/**
		 * Return whether water is passable or not.
		 * @return	TODO
		 * 			| result == true
		 */
		public boolean getPassable() {
			return true;
		}
		
		/**
		 * Return the integer linked to the type.
		 */
		public int getInt() {
			return 2;
		}
	},
	
	MAGMA {
		/**
		 * Return whether magma is passable or not.
		 * @return	TODO
		 * 			| result == true
		 */
		public boolean getPassable() {
			return true;
		}
		
		/**
		 * Return the integer linked to the type.
		 */
		public int getInt() {
			return 3;
		}
	},
	
	GROUND {
		/**
		 * Return whether ground is passable or not.
		 * @return	TODO
		 * 			| result == false
		 */
		public boolean getPassable() {
			return false;
		}
		
		/**
		 * Return the integer linked to the type.
		 */
		public int getInt() {
			return 1;
		}
	};
	
	
	public abstract boolean getPassable();
	public abstract int getInt();

}
